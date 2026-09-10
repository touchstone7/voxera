CREATE TABLE tickets (
    ticket_id UUID PRIMARY KEY,
    ticket_number VARCHAR(30) NOT NULL UNIQUE,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    status VARCHAR(30) NOT NULL,
    priority VARCHAR(30) NOT NULL,
    category VARCHAR(50) NOT NULL,

    created_by UUID NOT NULL,
    assigned_to UUID,
    assigned_team UUID,

    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    resolved_at TIMESTAMP,
    closed_at TIMESTAMP,

    version BIGINT NOT NULL,

    CONSTRAINT fk_ticket_created_by
        FOREIGN KEY (created_by) REFERENCES users(user_id),

    CONSTRAINT fk_ticket_assigned_to
        FOREIGN KEY (assigned_to) REFERENCES users(user_id),

    CONSTRAINT fk_ticket_assigned_team
        FOREIGN KEY (assigned_team) REFERENCES teams(team_id)
);

CREATE INDEX idx_tickets_created_by
    ON tickets(created_by);

CREATE INDEX idx_tickets_assigned_to
    ON tickets(assigned_to);

CREATE INDEX idx_tickets_assigned_team
    ON tickets(assigned_team);

CREATE INDEX idx_tickets_status
    ON tickets(status);

CREATE INDEX idx_tickets_priority
    ON tickets(priority);
