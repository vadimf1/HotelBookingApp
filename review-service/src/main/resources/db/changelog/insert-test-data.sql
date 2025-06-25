--liquibase formatted sql

--changeset vadim:insert_users
INSERT INTO user_service.users (id, name)
VALUES
    (gen_random_uuid(), 'name1'),
    (gen_random_uuid(), 'name2'),
    (gen_random_uuid(), 'name3'),
    (gen_random_uuid(), 'name4'),
    (gen_random_uuid(), 'name5'),
    (gen_random_uuid(), 'name6'),
    (gen_random_uuid(), 'name7'),
    (gen_random_uuid(), 'name8'),
    (gen_random_uuid(), 'name9'),
    (gen_random_uuid(), 'name10');

--changeset vadim:insert_hotels
INSERT INTO hotel_service.hotels (id)
VALUES
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid());

--changeset vadim:insert_rooms
INSERT INTO hotel_service.rooms (id)
VALUES
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid()),
    (gen_random_uuid());

--changeset vadim:insert_reviews
INSERT INTO review_service.reviews (id, user_id, hotel_id, room_id, rating, description, created_at)
VALUES
    (gen_random_uuid(), (SELECT id FROM user_service.users LIMIT 1), (SELECT id FROM hotel_service.hotels LIMIT 1), (SELECT id FROM hotel_service.rooms LIMIT 1), 5, 'Excellent stay!', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 1 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 1 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 1 LIMIT 1), 4, 'Very good.', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 2 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 2 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 2 LIMIT 1), 3, 'Average.', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 3 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 3 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 3 LIMIT 1), 5, 'Loved it!', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 4 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 4 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 4 LIMIT 1), 2, 'Not great.', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 5 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 5 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 5 LIMIT 1), 4, 'Comfortable.', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 6 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 6 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 6 LIMIT 1), 5, 'Perfect experience.', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 7 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 7 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 7 LIMIT 1), 3, 'Okayish.', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 8 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 8 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 8 LIMIT 1), 4, 'Nice room.', NOW()),
    (gen_random_uuid(), (SELECT id FROM user_service.users OFFSET 9 LIMIT 1), (SELECT id FROM hotel_service.hotels OFFSET 9 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 9 LIMIT 1), 5, 'Awesome service.', NOW());
