ALTER TABLE workspace_settings
    ADD COLUMN require_assignment BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE workspace_settings
    ADD COLUMN allow_reopening BOOLEAN NOT NULL DEFAULT FALSE;