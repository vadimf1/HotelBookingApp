CREATE
EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE login_notifications
(
    id         UUID PRIMARY KEY     DEFAULT gen_random_uuid(),
    ip         VARCHAR(45) NOT NULL,
    country    VARCHAR(100),
    city       VARCHAR(100),
    user_agent VARCHAR(255),
    user_id    UUID        NOT NULL,
    logged_at  TIMESTAMP   NOT NULL,
    created_at TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);