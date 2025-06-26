--liquibase formatted sql

--changeset vadim:create-user-service-schema
CREATE SCHEMA IF NOT EXISTS user_service;

--changeset vadim:create-clients
CREATE TABLE user_service.clients (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);