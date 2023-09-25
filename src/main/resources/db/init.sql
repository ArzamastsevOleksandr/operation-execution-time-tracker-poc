CREATE TABLE IF NOT EXISTS stats
(
    id               BIGSERIAL PRIMARY KEY,
    operation_name   VARCHAR,
    time_taken_nanos BIGINT,
    operation_uuid   UUID,
    tag              VARCHAR,
    created_at       TIMESTAMP
);

CREATE TABLE IF NOT EXISTS sport
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR,
    type       VARCHAR,
    created_at TIMESTAMP DEFAULT now()
);

INSERT INTO sport
    (name, type)
VALUES ('Soccer', 'REGULAR'),
       ('Basketball', 'REGULAR'),
       ('Golf', 'MULTI_COMPETITOR'),
       ('Formula 1', 'MULTI_COMPETITOR');
