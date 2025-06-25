CREATE table locale_mappings
(
    id           SERIAL PRIMARY KEY,
    country_code VARCHAR(2) NOT NULL UNIQUE,
    locale_code  VARCHAR(2) NOT NULL
)