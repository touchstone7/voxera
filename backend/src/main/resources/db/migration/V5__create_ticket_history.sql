CREATE TABLE ticket_history (
    history_id UUID PRIMARY KEY,
    ticket_id UUID NOT NULL,

    actor_type VARCHAR(30) NOT NULL,
    actor_id UUID,

    action VARCHAR(100) NOT NULL,
    old_value TEXT,
    new_value TEXT,

    created_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_history_ticket
        FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
);

CREATE INDEX idx_ticket_history_ticket_id
    ON ticket_history(ticket_id);

CREATE INDEX idx_ticket_history_created_at
    ON ticket_history(created_at);
