CREATE TABLE ticket_categories (
    category_id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    name_key VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT uk_ticket_categories_name_key UNIQUE (name_key)
);

INSERT INTO ticket_categories (
    category_id,
    name,
    name_key,
    active,
    created_at,
    updated_at
)
VALUES
    ('a1000000-0000-4000-8000-000000000001',
     'Hardware', 'hardware', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('a1000000-0000-4000-8000-000000000002',
     'Software', 'software', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('a1000000-0000-4000-8000-000000000003',
     'Network', 'network', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('a1000000-0000-4000-8000-000000000004',
     'Access', 'access', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('a1000000-0000-4000-8000-000000000005',
     'Security', 'security', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('a1000000-0000-4000-8000-000000000006',
     'Account', 'account', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

    ('a1000000-0000-4000-8000-000000000007',
     'Other', 'other', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);