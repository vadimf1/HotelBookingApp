INSERT INTO booking_status (status_id, code, name) VALUES
(gen_random_uuid(), 'PENDING', 'В ожидании'),
(gen_random_uuid(), 'CONFIRMED', 'Подтверждено'),
(gen_random_uuid(), 'CANCELLED', 'Отменено');