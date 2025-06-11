INSERT INTO addresses (country, state, city, street, postal_code) VALUES
('USA', 'California', 'Los Angeles', 'Sunset Blvd', '90001'),
('USA', 'Nevada', 'Las Vegas', 'Main St', '88901'),
('Canada', 'Ontario', 'Toronto', 'Queen St', 'M5H'),
('Germany', 'Bavaria', 'Munich', 'Marienplatz', '80331'),
('UK', 'England', 'London', 'Baker St', 'NW1'),
('France', 'Île-de-France', 'Paris', 'Rue de Rivoli', '75001'),
('Italy', 'Lazio', 'Rome', 'Via del Corso', '00186'),
('Japan', 'Tokyo', 'Tokyo', 'Shibuya', '150-0002'),
('Australia', 'NSW', 'Sydney', 'George St', '2000'),
('Brazil', 'RJ', 'Rio de Janeiro', 'Copacabana', '22070');

INSERT INTO hotels (address_id, name, star_rating, average_rating, status, email, phone_number, website) VALUES
(1, 'Sunrise Hotel', 4, 4.5, 'ACTIVE', 'sunrise@hotel.com', '+1234567890', 'www.sunrise.com'),
(2, 'Desert Inn', 3, 3.8, 'ACTIVE', 'desert@hotel.com', '+1987654321', 'www.desertinn.com'),
(3, 'Maple Stay', 5, 4.9, 'MAINTENANCE', 'maple@stay.ca', '+1123456789', 'www.maplestay.ca'),
(4, 'Bavaria Lodge', 4, 4.1, 'ACTIVE', 'bavaria@lodge.de', '+4930123456', 'www.bavarialodge.de'),
(5, 'Royal London', 5, 4.7, 'INACTIVE', 'royal@london.co.uk', '+44123456789', 'www.royallondon.co.uk'),
(6, 'Paris Palace', 5, 4.8, 'ACTIVE', 'paris@palace.fr', '+33123456789', 'www.parispalace.fr'),
(7, 'Roma Hotel', 3, 3.2, 'ACTIVE', 'roma@hotel.it', '+3906123456', 'www.romahotel.it'),
(8, 'Tokyo Stay', 4, 4.3, 'ACTIVE', 'tokyo@stay.jp', '+81312345678', 'www.tokyostay.jp'),
(9, 'Sydney Harbor', 5, 4.6, 'ACTIVE', 'sydney@harbor.au', '+61212345678', 'www.sydneyharbor.au'),
(10, 'Copacabana Inn', 4, 4.0, 'INACTIVE', 'copa@inn.br', '+5521123456', 'www.copainn.br');

INSERT INTO rooms (hotel_id, room_type, room_number, capacity, price_per_day, status, area, floor, bed_count, description) VALUES
(1, 'STANDARD', 101, 2, 120.00, 'AVAILABLE', 25, 1, 1, 'Standard room'),
(2, 'DELUXE', 102, 3, 180.00, 'BOOKED', 35, 2, 2, 'Deluxe comfort'),
(3, 'SUITE', 201, 4, 250.00, 'AVAILABLE', 45, 2, 2, 'Luxury suite'),
(4, 'FAMILY', 301, 5, 300.00, 'MAINTENANCE', 50, 3, 3, 'Family space'),
(5, 'STANDARD', 103, 2, 100.00, 'AVAILABLE', 20, 1, 1, 'Affordable option'),
(6, 'DELUXE', 104, 2, 150.00, 'DISABLED', 30, 2, 1, 'Quiet and cozy'),
(7, 'SUITE', 202, 4, 270.00, 'BOOKED', 40, 3, 2, 'Spacious suite'),
(8, 'FAMILY', 302, 6, 320.00, 'AVAILABLE', 55, 4, 3, 'Great for families'),
(9, 'STANDARD', 105, 2, 110.00, 'AVAILABLE', 22, 1, 1, 'Compact and clean'),
(10, 'DELUXE', 106, 3, 190.00, 'BOOKED', 33, 2, 2, 'Modern style');

INSERT INTO reviews (client_id, hotel_id, room_id, rating, description) VALUES
(1, 1, 1, 5, 'Great stay!'),
(2, 2, 2, 4, 'Very good.'),
(3, 3, 3, 5, 'Excellent service.'),
(4, 4, 4, 3, 'Could be better.'),
(5, 5, 5, 2, 'Not satisfied.'),
(6, 6, 6, 4, 'Nice place.'),
(7, 7, 7, 5, 'Perfect!'),
(8, 8, 8, 3, 'Okay experience.'),
(9, 9, 9, 4, 'Loved it.'),
(10, 10, 10, 5, 'Highly recommend!');


INSERT INTO amenities (name, description, is_free, status) VALUES
('Wi-Fi', 'Free internet access', true, 'AVAILABLE'),
('Breakfast', 'Continental breakfast', true, 'AVAILABLE'),
('Parking', 'Underground parking', false, 'AVAILABLE'),
('Gym', 'Fitness center access', false, 'MAINTENANCE'),
('Pool', 'Indoor swimming pool', false, 'AVAILABLE'),
('Spa', 'Relaxing spa services', false, 'UNAVAILABLE'),
('Laundry', 'Self-service laundry', true, 'AVAILABLE'),
('Airport Shuttle', 'To/from airport', false, 'AVAILABLE'),
('TV', 'Cable TV', true, 'AVAILABLE'),
('Air Conditioning', 'Room A/C', true, 'AVAILABLE');

INSERT INTO hotel_amenities (hotel_id, amenity_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5),
(5, 6),
(6, 7),
(7, 8),
(8, 9),
(9, 10);

INSERT INTO room_amenities (room_id, amenity_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);
