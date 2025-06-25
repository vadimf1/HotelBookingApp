--liquibase formatted sql

--changeset vadim:create-user-service-schema
CREATE SCHEMA IF NOT EXISTS user_service;

--changeset vadim:create-hotel-service-schema
CREATE SCHEMA IF NOT EXISTS hotel_service;

--changeset vadim:create-users
CREATE TABLE user_service.users (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

--changeset vadim:create-hotels
CREATE TABLE hotel_service.hotels (
    id UUID PRIMARY KEY
);

--changeset vadim:create-rooms
CREATE TABLE hotel_service.rooms (
    id UUID PRIMARY KEY
);
