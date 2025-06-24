CREATE TABLE bookings (
    booking_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    room_id UUID NOT NULL REFERENCES rooms(id),
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    status_id UUID NOT NULL REFERENCES booking_status(status_id),
    created_at TIMESTAMP without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP without time zone NOT NULL DEFAULT CURRENT_TIMESTAMP
);