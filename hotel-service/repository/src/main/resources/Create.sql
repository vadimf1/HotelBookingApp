CREATE TYPE hotel_status_enum AS ENUM ('ACTIVE', 'INACTIVE', 'MAINTENANCE');
CREATE TYPE room_status_enum AS ENUM ('AVAILABLE', 'BOOKED', 'MAINTENANCE', 'DISABLED');
CREATE TYPE room_type_enum AS ENUM ('STANDARD', 'DELUXE', 'SUITE', 'FAMILY');
CREATE TYPE amenity_status_enum AS ENUM ('AVAILABLE', 'UNAVAILABLE', 'MAINTENANCE');

CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    country VARCHAR(50) NOT NULL,
    state VARCHAR(100),
    city VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL
);

CREATE TABLE hotels (
    id SERIAL PRIMARY KEY,
    address_id INT REFERENCES addresses(id) NOT NULL,
    name VARCHAR(255) NOT NULL,
    star_rating INT CHECK (star_rating >= 1 AND star_rating <= 5),
    average_rating DECIMAL(3,2),
    status hotel_status_enum NOT NULL DEFAULT 'ACTIVE',
    email VARCHAR(100),
    phone_number VARCHAR(20),
    website VARCHAR(100)
);

CREATE TABLE rooms (
    id SERIAL PRIMARY KEY,
    hotel_id INT REFERENCES hotels(id) NOT NULL,
    room_type room_type_enum NOT NULL,
    room_number INT NOT NULL,
    capacity INT NOT NULL,
    price_per_day NUMERIC(10,2) NOT NULL,
    status room_status_enum NOT NULL DEFAULT 'AVAILABLE',
    area INT,
    floor INT,
    bed_count INT,
    description TEXT
);

CREATE TABLE reviews (
    id SERIAL PRIMARY KEY,
    client_id INT NOT NULL,
    hotel_id INT REFERENCES hotels(id) NOT NULL,
    room_id INT REFERENCES rooms(id),
    rating INT NOT NULL CHECK (rating >= 0 AND rating <= 5),
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE amenities (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    is_free BOOLEAN NOT NULL,
    status amenity_status_enum NOT NULL DEFAULT 'AVAILABLE'
);

CREATE TABLE hotel_amenities (
    hotel_id INT NOT NULL REFERENCES hotels(id),
    amenity_id INT NOT NULL REFERENCES amenities(id),
    PRIMARY KEY (hotel_id, amenity_id)
);

CREATE TABLE room_amenities (
    room_id INT NOT NULL REFERENCES rooms(id),
    amenity_id INT NOT NULL REFERENCES amenities(id),
    PRIMARY KEY (room_id, amenity_id)
);
