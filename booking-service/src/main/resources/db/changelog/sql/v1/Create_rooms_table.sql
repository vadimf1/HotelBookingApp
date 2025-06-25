CREATE TABLE rooms (
    id UUID PRIMARY KEY,
    hotel_id UUID NOT NULL,
    room_type_id UUID NOT NULL,
    room_status_id UUID NOT NULL,
    room_number INTEGER NOT NULL,
    price_per_day DOUBLE PRECISION NOT NULL,
    floor INTEGER NOT NULL,
    description TEXT,
    amenity_ids UUID[]
)