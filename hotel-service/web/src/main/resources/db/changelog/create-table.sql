--liquibase formatted sql

--changeset vadim:create_hotel_statuses
CREATE TABLE hotel_statuses (
    id UUID PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL
);

--changeset vadim:create_room_statuses
CREATE TABLE room_statuses (
    id UUID PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL
);

--changeset vadim:create_room_types
CREATE TABLE room_types (
    id UUID PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    bed_count INT,
    area INT
);

--changeset vadim:create_amenity_statuses
CREATE TABLE amenity_statuses (
    id UUID PRIMARY KEY,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL
);

--changeset vadim:create_addresses
CREATE TABLE addresses (
    id UUID PRIMARY KEY,
    country VARCHAR(50) NOT NULL,
    state VARCHAR(100),
    city VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    postal_code VARCHAR(10) NOT NULL
);

--changeset vadim:create_hotels
CREATE TABLE hotels (
    id UUID PRIMARY KEY,
    address_id UUID REFERENCES addresses(id) NOT NULL,
    hotel_status_id UUID REFERENCES hotel_statuses(id) NOT NULL,
    name VARCHAR(255) NOT NULL,
    star_rating INT CHECK (star_rating >= 1 AND star_rating <= 5) NOT NULL,
    average_rating DECIMAL(3,2),
    email VARCHAR(100),
    phone_number VARCHAR(20),
    website VARCHAR(100)
);

--changeset vadim:create_rooms
CREATE TABLE rooms (
        id UUID PRIMARY KEY,
        hotel_id UUID REFERENCES hotels(id) NOT NULL,
        room_type_id UUID REFERENCES room_types(id) NOT NULL,
        room_status_id UUID REFERENCES room_statuses(id) NOT NULL,
        room_number INT NOT NULL,
        price_per_day NUMERIC(10,2) NOT NULL,
        floor INT NOT NULL,
        description TEXT
);

--changeset vadim:create_reviews
CREATE TABLE reviews (
    id UUID PRIMARY KEY,
    client_id UUID NOT NULL,
    hotel_id UUID REFERENCES hotels(id) NOT NULL,
    room_id UUID REFERENCES rooms(id),
    rating INT NOT NULL CHECK (rating >= 0 AND rating <= 5),
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

--changeset vadim:create_amenities
CREATE TABLE amenities (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    is_free BOOLEAN NOT NULL,
    amenity_status_id UUID REFERENCES amenity_statuses(id) NOT NULL
);

--changeset vadim:create_hotel_amenities
CREATE TABLE hotel_amenities (
    hotel_id UUID NOT NULL REFERENCES hotels(id),
    amenity_id UUID NOT NULL REFERENCES amenities(id),
    PRIMARY KEY (hotel_id, amenity_id)
);

--changeset vadim:create_room_amenities
CREATE TABLE room_amenities (
    room_id UUID NOT NULL REFERENCES rooms(id),
    amenity_id UUID NOT NULL REFERENCES amenities(id),
    PRIMARY KEY (room_id, amenity_id)
);
