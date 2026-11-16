-- Additional development accounts.
-- Existing EMP001 and AGT001 accounts are preserved.

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
) VALUES
(
    '33333333-3333-3333-3333-333333333333',
    'EMP002',
    'Jordan Lee',
    'emp002@voxera.local',
    'Finance',
    'EMPLOYEE',
    'ACTIVE',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    '44444444-4444-4444-4444-444444444444',
    'AGT002',
    'Taylor Reed',
    'agt002@voxera.local',
    'IT',
    'AGENT',
    'ACTIVE',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    '55555555-5555-5555-5555-555555555555',
    'ADM001',
    'Morgan Blake',
    'admin001@voxera.local',
    'IT',
    'ADMIN',
    'ACTIVE',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    '66666666-6666-6666-6666-666666666666',
    'ADM002',
    'Casey Morgan',
    'admin002@voxera.local',
    'IT',
    'ADMIN',
    'ACTIVE',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);

-- Development-only password: VoxeraDev123!
-- Reuses the BCrypt hash already used by V8 and V9.

INSERT INTO user_credentials (
    user_id,
    password_hash,
    created_at,
    updated_at
) VALUES
(
    '33333333-3333-3333-3333-333333333333',
    '$2a$10$pTy/zwNvIFrcnHtZYX2GZuxH6mpmBB2zirPHC/f1lbggMzSygufpy',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    '44444444-4444-4444-4444-444444444444',
    '$2a$10$pTy/zwNvIFrcnHtZYX2GZuxH6mpmBB2zirPHC/f1lbggMzSygufpy',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    '55555555-5555-5555-5555-555555555555',
    '$2a$10$pTy/zwNvIFrcnHtZYX2GZuxH6mpmBB2zirPHC/f1lbggMzSygufpy',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
),
(
    '66666666-6666-6666-6666-666666666666',
    '$2a$10$pTy/zwNvIFrcnHtZYX2GZuxH6mpmBB2zirPHC/f1lbggMzSygufpy',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP
);