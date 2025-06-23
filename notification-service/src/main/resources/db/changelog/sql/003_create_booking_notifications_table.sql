CREATE TABLE booking_notifications
(
    id             UUID PRIMARY KEY,
    user_id        UUID NOT NULL,
    room_id        UUID NOT NULL,
    check_in_date  DATE NOT NULL,
    check_out_date DATE NOT NULL,
    created_at     TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);
