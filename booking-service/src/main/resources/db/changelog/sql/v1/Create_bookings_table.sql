CREATE TABLE bookings (
    booking_id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    room_number INT NOT NULL,
    room_status VARCHAR(50) NOT NULL CHECK (room_status IN ('PENDING', 'CONFIRMED', 'CANCELLED')),
    created_at DATE NOT NULL DEFAULT CURRENT_DATE
)