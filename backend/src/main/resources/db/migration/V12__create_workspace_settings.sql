CREATE TABLE workspace_settings (
    settings_id INTEGER PRIMARY KEY CHECK (settings_id = 1),
    workspace_name VARCHAR(150) NOT NULL,
    organization VARCHAR(150) NOT NULL,
    timezone VARCHAR(100) NOT NULL,
    ticket_prefix VARCHAR(10) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    version BIGINT NOT NULL DEFAULT 0
);

INSERT INTO workspace_settings (
    settings_id,
    workspace_name,
    organization,
    timezone,
    ticket_prefix,
    updated_at,
    version
) VALUES (
    1,
    'Voxera Support',
    'Voxera Technologies',
    'Asia/Kolkata',
    'VXR',
    CURRENT_TIMESTAMP,
    0
);
