#!/usr/bin/env python3
"""Generate Voxera's structural metadata using Python's standard library."""

from pathlib import Path
import re
import subprocess
from datetime import datetime, timezone

ROOT = Path(__file__).resolve().parent.parent
META = ROOT / "docs" / "metadata"
# Support both a repository-root Java project and Voxera's backend/ layout.
if (ROOT / "backend" / "src" / "main" / "java").is_dir():
    MAIN = ROOT / "backend" / "src" / "main" / "java"
    TEST = ROOT / "backend" / "src" / "test" / "java"
else:
    MAIN = ROOT / "src" / "main" / "java"
    TEST = ROOT / "src" / "test" / "java"


def read(path):
    try:
        return path.read_text(encoding="utf-8")
    except (OSError, UnicodeError):
        return ""


def write(path, content):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(content.rstrip() + "\n", encoding="utf-8")


def git_info(*args):
    try:
        return subprocess.run(
            ["git", *args], cwd=ROOT, capture_output=True,
            text=True, check=True
        ).stdout.strip()
    except (OSError, subprocess.CalledProcessError):
        return "unavailable"


def java_files(folder):
    return sorted(folder.rglob("*.java")) if folder.exists() else []


def relative(path):
    return path.relative_to(ROOT).as_posix()


def class_name(source):
    match = re.search(
        r"\b(?:class|interface|enum|record)\s+(\w+)", source
    )
    return match.group(1) if match else "Unknown"


def method_signatures(source, owner):
    """Approximate Java method/constructor declarations; not a full parser."""
    pattern = re.compile(
        r"(?m)^[ \t]*"
        r"(?:(?:public|protected|private)\s+)?"
        r"(?:(?:static|final|abstract|synchronized|default|native)\s+)*"
        r"([\w.$<>?, \[\]@]+?)\s+"
        r"(\w+)\s*\(([^;{}]*)\)\s*"
        r"(?:throws\s+[\w., ]+\s*)?\{"
    )
    found = []
    for match in pattern.finditer(source):
        return_type = " ".join(match.group(1).split())
        name = match.group(2)
        params = " ".join(match.group(3).split())
        if name == owner:
            signature = f"{name}({params})"
        else:
            signature = f"{return_type} {name}({params})"
        if signature not in found:
            found.append(signature)
    return found


def file_header(title):
    return (
        f"# {title}\n\n"
        f"> Generated: {datetime.now(timezone.utc).isoformat()}\n"
        f"> Git commit: `{git_info('rev-parse', '--short', 'HEAD')}`\n"
        f"> Generator: `scripts/generate_metadata.py`\n\n"
    )


def build_structure(files):
    lines = [file_header("Project Structure and Java Inventory")]
    lines.append("## Source and test files\n")
    for path in files:
        lines.append(f"- `{relative(path)}`")
    lines.append("\n## Java classes and method signatures\n")
    for path in files:
        source = read(path)
        owner = class_name(source)
        package = re.search(r"(?m)^\s*package\s+([\w.]+)\s*;", source)
        lines.append(f"\n### `{owner}`")
        lines.append(f"- File: `{relative(path)}`")
        if package:
            lines.append(f"- Package: `{package.group(1)}`")
        signatures = method_signatures(source, owner)
        if signatures:
            lines.extend(f"- `{item}`" for item in signatures)
        else:
            lines.append("- No method signatures extracted.")
    return "\n".join(lines)


def build_api(files):
    lines = [file_header("API Contracts")]
    lines.append(
        "> Extracted from Spring mapping annotations. Verify request/response "
        "semantics and status codes against controller code and tests.\n"
    )
    mapping = re.compile(
        r"@(Get|Post|Put|Patch|Delete|Request)Mapping\b"
        r"(?:\s*\(([^)]*)\))?"
    )
    class_mapping = re.compile(r"@RequestMapping\s*\(([^)]*)\)")
    method_pattern = re.compile(
        r"(?s)^\s*(?:(?:public|protected|private)\s+)?"
        r"[\w.$<>?, \[\]@]+\s+(\w+)\s*\(([^)]*)\)"
    )

    any_found = False
    for path in files:
        source = read(path)
        if not re.search(r"@RestController|@Controller", source):
            continue

        base = ""
        class_match = class_mapping.search(source)
        if class_match:
            value = re.search(
                r'(?:value|path)\s*=\s*"([^"]*)"|"([^"]*)"',
                class_match.group(1)
            )
            if value:
                base = value.group(1) or value.group(2) or ""

        lines.append(f"\n## `{class_name(source)}`")
        lines.append(f"- File: `{relative(path)}`")
        lines.append(f"- Class-level path: `{base or '(none detected)'}`")

        for match in mapping.finditer(source):
            annotation = match.group(0)
            args = match.group(2) or ""
            verb = match.group(1).upper()
            if verb == "REQUEST":
                verb = "REQUEST (inspect annotation for HTTP method)"
            elif verb == "PATCH":
                verb = "PATCH"

            path_match = re.search(r'"([^"]*)"', args)
            endpoint = path_match.group(1) if path_match else ""
            after = source[match.end():match.end() + 1800]
            method = method_pattern.search(after)
            if not method:
                continue

            method_name = method.group(1)
            params = " ".join(method.group(2).split())
            lines.append(
                f"- **{verb} `{base}{endpoint}`** — "
                f"`{method_name}({params})`"
            )
            lines.append(f"  - Mapping: `{annotation}`")
            any_found = True

    if not any_found:
        lines.append("\nNo controller mappings were confidently extracted.")
    lines.append(
        "\n## Contract verification checklist\n"
        "- Confirm authentication requirements and role/resource permissions.\n"
        "- Confirm request DTO fields and validation constraints.\n"
        "- Confirm response DTO shape and HTTP status codes.\n"
        "- Confirm error response format using the exception handler.\n"
    )
    return "\n".join(lines)


def field_inventory(source, owner):
    fields = []
    # Entity/class instance fields. Excludes methods and most local variables.
    field_pattern = re.compile(
        r"(?m)^[ \t]*"
        r"(?:(?:private|protected|public)\s+)"
        r"(?:(?:static|final|transient|volatile)\s+)*"
        r"([\w.$<>?, \[\]]+?)\s+(\w+)\s*"
        r"(?:=\s*[^;]+)?;"
    )
    for match in field_pattern.finditer(source):
        field_type = " ".join(match.group(1).split())
        name = match.group(2)
        if name not in {owner}:
            fields.append((name, field_type, match.start()))

    # Java record components.
    record = re.search(
        r"\brecord\s+" + re.escape(owner) + r"\s*\((.*?)\)\s*\{", source, re.S
    )
    if record:
        for component in record.group(1).split(","):
            component = " ".join(component.split())
            if component and not component.startswith("@"):
                bits = component.split()
                if len(bits) >= 2:
                    fields.append((bits[-1], " ".join(bits[:-1]), -1))

    unique = {}
    for name, field_type, position in fields:
        unique[name] = (field_type, position)
    return [(name, *value) for name, value in unique.items()]


def build_domain(files):
    lines = [file_header("Domain Model, DTOs, and Enums")]
    for path in files:
        source = read(path)
        owner = class_name(source)
        is_entity = bool(re.search(r"@Entity\b", source))
        is_enum = bool(re.search(r"\benum\s+" + re.escape(owner), source))
        is_dto = (
            "dto" in path.parts
            or owner.endswith(("Request", "Response", "Dto", "DTO"))
            or bool(re.search(r"\brecord\s+" + re.escape(owner), source))
        )
        if not (is_entity or is_enum or is_dto):
            continue

        lines.append(f"\n## `{owner}`")
        lines.append(f"- File: `{relative(path)}`")
        if is_entity:
            lines.append("- Kind: JPA entity")
            for annotation in re.findall(
                r"(?m)^\s*@(?:Table|Id|Column|JoinColumn|ManyToOne|"
                r"OneToMany|OneToOne|ManyToMany|Enumerated|Version)\b"
                r"[^\n]*", source
            ):
                lines.append(f"- Persistence annotation: `{annotation.strip()}`")
        elif is_enum:
            lines.append("- Kind: enum")
            body = re.search(r"\benum\s+\w+\s*\{(.*?)\}", source, re.S)
            if body:
                constants = body.group(1).split(";")[0]
                constants = re.sub(r"//.*|/\*.*?\*/", "", constants, flags=re.S)
                values = re.findall(r"\b[A-Z][A-Z0-9_]*\b", constants)
                lines.append("- Values: " + (
                    ", ".join(f"`{v}`" for v in dict.fromkeys(values))
                    or "(inspect source)"
                ))
        else:
            lines.append("- Kind: DTO / request / response")

        fields = field_inventory(source, owner)
        if fields:
            lines.append("- Fields/components:")
            for name, field_type, position in fields:
                lines.append(f"  - `{name}: {field_type}`")
                if position >= 0:
                    before = source[max(0, position - 300):position]
                    annotations = re.findall(r"@[A-Za-z]\w*(?:\([^)]*\))?", before)
                    if annotations:
                        lines.append(
                            "    - Nearby annotations (verify scope): "
                            + ", ".join(f"`{a}`" for a in annotations[-3:])
                        )
        else:
            lines.append("- No fields/components extracted.")
    lines.append(
        "\n> This is a structural inventory, not a guarantee of database "
        "constraints or runtime validation. Verify annotations in source."
    )
    return "\n".join(lines)


def build_tests(files):
    lines = [file_header("Test Map")]
    for path in files:
        source = read(path)
        tests = re.findall(
            r"(?s)@Test\b(?:\s*@\w+(?:\([^)]*\))?)*\s*"
            r"(?:public\s+|protected\s+|private\s+)?"
            r"(?:void|[\w.$<>?, \[\]]+)\s+(\w+)\s*\(",
            source
        )
        lines.append(f"\n## `{class_name(source)}`")
        lines.append(f"- File: `{relative(path)}`")
        lines.append(f"- Test methods detected: {len(tests)}")
        lines.extend(f"  - `{name}()`" for name in tests)
    lines.append(
        "\n> Test names indicate intended coverage, not proof that a behavior "
        "is correctly tested. Use test source and test results for verification."
    )
    return "\n".join(lines)


def create_manual_docs():
    security = META / "SECURITY_AND_RULES.md"
    if not security.exists():
        write(security, """# Security and Business Rules

> Manually maintained decisions. Verify implementation before treating these as current behavior.

## Authentication
- Record the current login, token validation, and active-user policy here.

## Authorization policy
- EMPLOYEE: access tickets they created; may cancel their own tickets.
- AGENT: access and work only on tickets assigned to them.
- ADMIN: may access all tickets.
- Team-based authorization is deferred.
- Ticket assignment remains manual; no automatic assignment.

## Ticket lifecycle
- OPEN -> IN_PROGRESS -> PENDING / RESOLVED -> CLOSED, subject to implementation rules.
- IN_PROGRESS may also transition to RESOLVED.
- Cancellation and terminal-state rules must match the current entity implementation.

## Open questions
- Record unresolved policy decisions here.
""")

    status = META / "PROJECT_STATUS.md"
    if not status.exists():
        write(status, """# Voxera Project Status

> Manually maintained. Update after meaningful implementation milestones.

## Current checkpoint
- Last verified commit: update after checking Git.
- Build/test status: not verified by this metadata generator.
- Active milestone: resource-based ticket authorization.

## Decisions
- Assignment remains manual.
- Agent access is limited to individually assigned tickets.
- Team-based authorization is deferred.
- Avoid unnecessary expansion of tests; prioritize meaningful regression coverage.

## Completed
- Update from the current roadmap and verified repository state.

## In progress
- Resource-based ticket authorization and role-scoped ticket listing.

## Next
- Verify focused authorization tests, then run the full test suite.
- Refresh metadata and record the resulting checkpoint.
""")


def main():
    META.mkdir(parents=True, exist_ok=True)
    main_files = java_files(MAIN)
    test_files = java_files(TEST)
    all_files = sorted(main_files + test_files)

    create_manual_docs()

    write(META / "PROJECT_STRUCTURE.md", build_structure(all_files))
    write(META / "API_CONTRACTS.md", build_api(main_files))
    write(META / "DOMAIN_MODEL.md", build_domain(main_files))
    write(META / "TEST_MAP.md", build_tests(test_files))

    index = f"""# Voxera Metadata

This is the entry point for understanding the current Voxera repository.

## Refresh

From the repository root, run:

```bash
python3 scripts/generate_metadata.py
```

Structural documents are regenerated from the checked-out Java source. The
security/rules and project-status documents are preserved for manual editing.

## Metadata documents

- [Project structure](docs/metadata/PROJECT_STRUCTURE.md)
- [API contracts](docs/metadata/API_CONTRACTS.md)
- [Domain model, DTOs, and enums](docs/metadata/DOMAIN_MODEL.md)
- [Security and business rules](docs/metadata/SECURITY_AND_RULES.md)
- [Test map](docs/metadata/TEST_MAP.md)
- [Project status](docs/metadata/PROJECT_STATUS.md)

## Repository checkpoint

- Generated at UTC: {datetime.now(timezone.utc).isoformat()}
- Git commit: `{git_info('rev-parse', '--short', 'HEAD')}`
- Git working tree status: `{git_info('status', '--short') or 'clean'}`
- Main Java files found: {len(main_files)}
- Test Java files found: {len(test_files)}

## Important limitation

The generator extracts structural information using Python regular expressions;
it is not a Java compiler and cannot prove runtime behavior, authorization
correctness, or exact API semantics. Verify ambiguous entries against source.
"""
    write(ROOT / "VOXERA_METADATA.md", index)

    print("Voxera metadata refreshed.")
    print(f"Repository: {ROOT}")
    print(f"Main Java files: {len(main_files)}")
    print(f"Test Java files: {len(test_files)}")
    print(f"Metadata directory: {META}")
    print("Manual documents were preserved if they already existed.")


if __name__ == "__main__":
    main()
