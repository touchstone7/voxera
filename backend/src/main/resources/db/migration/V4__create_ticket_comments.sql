CREATE TABLE ticket_comments (
    comment_id UUID PRIMARY KEY,
    ticket_id UUID NOT NULL,
    author_id UUID NOT NULL,
    content TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    CONSTRAINT fk_comment_ticket
        FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id),

    CONSTRAINT fk_comment_author
        FOREIGN KEY (author_id) REFERENCES users(user_id)
);

CREATE INDEX idx_ticket_comments_ticket_id
    ON ticket_comments(ticket_id);
