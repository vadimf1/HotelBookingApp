CREATE TABLE booking_status (
    status_id UUID PRIMARY KEY ,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL
);