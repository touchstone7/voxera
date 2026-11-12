INSERT INTO users (
    user_id,
    employee_id,
    name,
    email,
    department,
    role,
    status,
    created_at,
    updated_at
)
VALUES (
    '22222222-2222-2222-2222-222222222222',
    'AGT001',
    'Alex Morgan',
    'agt001@voxera.local',
    'IT',
    'AGENT',
    'ACTIVE',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

INSERT INTO user_credentials (
    user_id,
    password_hash,
    created_at,
    updated_at
)
VALUES (
    '22222222-2222-2222-2222-222222222222',
    '$2a$10$pTy/zwNvIFrcnHtZYX2GZuxH6mpmBB2zirPHC/f1lbggMzSygufpy',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);