--liquibase formatted sql

--changeset vadim:insert_hotel_statuses
INSERT INTO hotel_service.hotel_statuses (id, code, name)
VALUES
    (gen_random_uuid(), 'ACTIVE', 'Active'),
    (gen_random_uuid(), 'INACTIVE', 'Inactive'),
    (gen_random_uuid(), 'MAINTENANCE', 'Under Maintenance');

--changeset vadim:insert_room_statuses
INSERT INTO hotel_service.room_statuses (id, code, name)
VALUES
    (gen_random_uuid(), 'AVAILABLE', 'Available'),
    (gen_random_uuid(), 'BOOKED', 'Booked'),
    (gen_random_uuid(), 'MAINTENANCE', 'Under Maintenance'),
    (gen_random_uuid(), 'DISABLED', 'Disabled');

--changeset vadim:insert_amenity_statuses
INSERT INTO hotel_service.amenity_statuses (id, code, name)
VALUES
    (gen_random_uuid(), 'AVAILABLE', 'Available'),
    (gen_random_uuid(), 'UNAVAILABLE', 'Unavailable'),
    (gen_random_uuid(), 'MAINTENANCE', 'Under Maintenance');

--changeset vadim:insert_room_types
INSERT INTO hotel_service.room_types (id, code, name, capacity, bed_count, area)
VALUES
    (gen_random_uuid(), 'STANDARD', 'Standard Room', 2, 1, 20),
    (gen_random_uuid(), 'DELUXE', 'Deluxe Room', 2, 1, 30),
    (gen_random_uuid(), 'SUITE', 'Suite', 3, 2, 50),
    (gen_random_uuid(), 'FAMILY', 'Family Room', 4, 3, 60);

--changeset vadim:insert_addresses
INSERT INTO hotel_service.addresses (id, country, state, city, street, postal_code)
VALUES
    (gen_random_uuid(), 'USA', 'California', 'Los Angeles', 'Sunset Blvd', '90001'),
    (gen_random_uuid(), 'USA', 'Nevada', 'Las Vegas', 'Fremont St', '88901'),
    (gen_random_uuid(), 'UK', 'England', 'London', 'Baker Street', 'NW1 6XE'),
    (gen_random_uuid(), 'Germany', 'Bavaria', 'Munich', 'Marienplatz', '80331'),
    (gen_random_uuid(), 'France', 'Île-de-France', 'Paris', 'Rue de Rivoli', '75001'),
    (gen_random_uuid(), 'Italy', 'Lazio', 'Rome', 'Via Veneto', '00187'),
    (gen_random_uuid(), 'Spain', 'Madrid', 'Madrid', 'Calle Mayor', '28013'),
    (gen_random_uuid(), 'Canada', 'Quebec', 'Montreal', 'Saint Catherine St', 'H3B 1B1'),
    (gen_random_uuid(), 'Japan', 'Tokyo', 'Tokyo', 'Shinjuku', '160-0022'),
    (gen_random_uuid(), 'Australia', 'NSW', 'Sydney', 'Pitt Street', '2000');

--changeset vadim:insert_hotels
INSERT INTO hotel_service.hotels (id, address_id, hotel_status_id, name, star_rating, average_rating, email, phone_number, website)
VALUES
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'ACTIVE' LIMIT 1), 'Hotel One', 4, 4.5, 'one@example.com', '1111111111', 'www.hotelone.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 1 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'ACTIVE' LIMIT 1), 'Hotel Two', 5, 4.8, 'two@example.com', '2222222222', 'www.hoteltwo.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 2 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'INACTIVE' LIMIT 1), 'Hotel Three', 3, 3.9, 'three@example.com', '3333333333', 'www.hotelthree.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 3 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'MAINTENANCE' LIMIT 1), 'Hotel Four', 2, 3.5, 'four@example.com', '4444444444', 'www.hotelfour.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 4 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'ACTIVE' LIMIT 1), 'Hotel Five', 4, 4.3, 'five@example.com', '5555555555', 'www.hotelfive.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 5 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'ACTIVE' LIMIT 1), 'Hotel Six', 3, 4.0, 'six@example.com', '6666666666', 'www.hotelsix.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 6 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'ACTIVE' LIMIT 1), 'Hotel Seven', 5, 4.9, 'seven@example.com', '7777777777', 'www.hotelseven.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 7 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'INACTIVE' LIMIT 1), 'Hotel Eight', 2, 3.2, 'eight@example.com', '8888888888', 'www.hoteleight.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 8 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'MAINTENANCE' LIMIT 1), 'Hotel Nine', 4, 4.6, 'nine@example.com', '9999999999', 'www.hotelnine.com'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.addresses OFFSET 9 LIMIT 1), (SELECT id FROM hotel_service.hotel_statuses WHERE code = 'ACTIVE' LIMIT 1), 'Hotel Ten', 5, 5.0, 'ten@example.com', '0000000000', 'www.hotelten.com');

--changeset vadim:insert_rooms
INSERT INTO hotel_service.rooms (id, hotel_id, room_type_id, room_status_id, room_number, price_per_day, floor, description)
VALUES
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'STANDARD' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'AVAILABLE' LIMIT 1), 101, 100.00, 1, 'Standard room'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 1 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'DELUXE' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'BOOKED' LIMIT 1), 102, 150.00, 1, 'Deluxe room'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 2 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'SUITE' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'MAINTENANCE' LIMIT 1), 201, 200.00, 2, 'Suite'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 3 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'FAMILY' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'DISABLED' LIMIT 1), 202, 180.00, 2, 'Family room'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 4 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'STANDARD' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'AVAILABLE' LIMIT 1), 301, 120.00, 3, 'Standard'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 5 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'DELUXE' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'BOOKED' LIMIT 1), 302, 140.00, 3, 'Deluxe'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 6 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'SUITE' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'MAINTENANCE' LIMIT 1), 401, 210.00, 4, 'Suite'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 7 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'FAMILY' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'DISABLED' LIMIT 1), 402, 170.00, 4, 'Family'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 8 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'STANDARD' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'AVAILABLE' LIMIT 1), 501, 130.00, 5, 'Standard'),
    (gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 9 LIMIT 1), (SELECT id FROM hotel_service.room_types WHERE code = 'DELUXE' LIMIT 1), (SELECT id FROM hotel_service.room_statuses WHERE code = 'BOOKED' LIMIT 1), 502, 160.00, 5, 'Deluxe');

--changeset vadim:insert_reviews
INSERT INTO hotel_service.reviews (id, client_id, hotel_id, room_id, rating, description, created_at)
VALUES
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels LIMIT 1), (SELECT id FROM hotel_service.rooms LIMIT 1), 5, 'Excellent stay!', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 1 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 1 LIMIT 1), 4, 'Very good.', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 2 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 2 LIMIT 1), 3, 'Average.', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 3 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 3 LIMIT 1), 5, 'Loved it!', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 4 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 4 LIMIT 1), 2, 'Not great.', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 5 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 5 LIMIT 1), 4, 'Comfortable.', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 6 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 6 LIMIT 1), 5, 'Perfect experience.', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 7 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 7 LIMIT 1), 3, 'Okayish.', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 8 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 8 LIMIT 1), 4, 'Nice room.', NOW()),
    (gen_random_uuid(), gen_random_uuid(), (SELECT id FROM hotel_service.hotels OFFSET 9 LIMIT 1), (SELECT id FROM hotel_service.rooms OFFSET 9 LIMIT 1), 5, 'Awesome service.', NOW());

--changeset vadim:insert_amenities
INSERT INTO hotel_service.amenities (id, name, description, is_free, amenity_status_id)
VALUES
    (gen_random_uuid(), 'Wi-Fi', 'Free Wi-Fi throughout the hotel', true, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'AVAILABLE' LIMIT 1)),
    (gen_random_uuid(), 'Parking', 'On-site parking available', true, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'AVAILABLE' LIMIT 1)),
    (gen_random_uuid(), 'Swimming Pool', 'Outdoor pool', false, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'MAINTENANCE' LIMIT 1)),
    (gen_random_uuid(), 'Gym', 'Fitness center', false, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'AVAILABLE' LIMIT 1)),
    (gen_random_uuid(), 'Spa', 'Relaxing spa services', false, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'UNAVAILABLE' LIMIT 1)),
    (gen_random_uuid(), 'Bar', 'Cocktail bar', false, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'AVAILABLE' LIMIT 1)),
    (gen_random_uuid(), 'Restaurant', 'On-site dining', false, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'AVAILABLE' LIMIT 1)),
    (gen_random_uuid(), 'Room Service', 'Meals delivered to rooms', false, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'MAINTENANCE' LIMIT 1)),
    (gen_random_uuid(), 'Pet Friendly', 'Pets allowed', true, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'AVAILABLE' LIMIT 1)),
    (gen_random_uuid(), 'Conference Room', 'Business meetings space', false, (SELECT id FROM hotel_service.amenity_statuses WHERE code = 'UNAVAILABLE' LIMIT 1));

--changeset vadim:insert_hotel_amenities
INSERT INTO hotel_service.hotel_amenities (hotel_id, amenity_id)
VALUES
    ((SELECT id FROM hotel_service.hotels LIMIT 1), (SELECT id FROM hotel_service.amenities LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 1 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 1 LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 2 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 2 LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 3 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 3 LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 4 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 4 LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 5 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 5 LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 6 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 6 LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 7 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 7 LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 8 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 8 LIMIT 1)),
    ((SELECT id FROM hotel_service.hotels OFFSET 9 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 9 LIMIT 1));

--changeset vadim:insert_room_amenities
INSERT INTO hotel_service.room_amenities (room_id, amenity_id)
VALUES
    ((SELECT id FROM hotel_service.rooms LIMIT 1), (SELECT id FROM hotel_service.amenities LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 1 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 1 LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 2 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 2 LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 3 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 3 LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 4 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 4 LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 5 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 5 LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 6 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 6 LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 7 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 7 LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 8 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 8 LIMIT 1)),
    ((SELECT id FROM hotel_service.rooms OFFSET 9 LIMIT 1), (SELECT id FROM hotel_service.amenities OFFSET 9 LIMIT 1));
