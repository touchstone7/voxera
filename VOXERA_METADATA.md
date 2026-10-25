# Voxera Metadata

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

- Generated at UTC: 2026-10-25T15:16:43.235487+00:00
- Git commit: `36ca442`
- Git working tree status: `?? docs/
?? scripts/`
- Main Java files found: 38
- Test Java files found: 15

## Important limitation

The generator extracts structural information using Python regular expressions;
it is not a Java compiler and cannot prove runtime behavior, authorization
correctness, or exact API semantics. Verify ambiguous entries against source.
