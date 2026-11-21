-- Add the new category reference column.
ALTER TABLE tickets
    ADD COLUMN category_id UUID;

-- Map existing enum values to the seeded dynamic categories.
UPDATE tickets t
SET category_id = (
    SELECT tc.category_id
    FROM ticket_categories tc
    WHERE tc.name_key = LOWER(TRIM(t.category))
);

-- Enforce mandatory category association after backfilling.
ALTER TABLE tickets
    ALTER COLUMN category_id SET NOT NULL;

-- Add the foreign key.
ALTER TABLE tickets
    ADD CONSTRAINT fk_ticket_category
    FOREIGN KEY (category_id)
    REFERENCES ticket_categories(category_id);

-- Index the foreign key for category-based queries.
CREATE INDEX idx_tickets_category_id
    ON tickets(category_id);

-- Remove the obsolete enum-backed column.
ALTER TABLE tickets
    DROP COLUMN category;