--liquibase formatted sql

--changeset vadim:create-review-service-schema
CREATE SCHEMA IF NOT EXISTS review_service;

--changeset vadim:create_reviews
CREATE TABLE review_service.reviews (
    id UUID PRIMARY KEY,
    user_id UUID REFERENCES user_service.users NOT NULL,
    hotel_id UUID REFERENCES hotel_service.hotels(id) NOT NULL,
    room_id UUID REFERENCES hotel_service.rooms(id),
    rating INT NOT NULL CHECK (rating >= 0 AND rating <= 5),
    description TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
