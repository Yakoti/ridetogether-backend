CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255),
                       email VARCHAR(255),
                       home_address VARCHAR(255),
                       office_address VARCHAR(255),
                       preferred_arrival_start TIME,
                       preferred_arrival_end TIME,
                       flexibility_minutes INTEGER,
                       flexibility_km DOUBLE PRECISION,
                       role VARCHAR(255)
);

CREATE TABLE driver (
                        id BIGINT PRIMARY KEY REFERENCES users(id),
                        available_seats INTEGER
);

CREATE TABLE passenger (
                           id BIGINT PRIMARY KEY REFERENCES users(id)
);

-- Common office address
-- Cherni Vrah Blvd 51, Sofia, Bulgaria

-- Insert into users
INSERT INTO users (id, name, email, home_address, office_address, preferred_arrival_start,
                   preferred_arrival_end, flexibility_minutes, flexibility_km, role)
VALUES
-- Drivers
(1, 'Alice Driver', 'alice@car.com', 'Tsarigradsko Shose 115, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:00', '09:00', 15, 5.0, 'DRIVER'),
(2, 'Bob Driver', 'bob@car.com', 'Slivnitsa Blvd 150, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:00', '09:00', 20, 7.0, 'DRIVER'),
(3, 'Carol Driver', 'carol@car.com', 'Mladost 1, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:15', '09:15', 10, 4.0, 'DRIVER'),
(4, 'David Driver', 'david@car.com', 'Nadezhda 2, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:00', '09:00', 15, 6.0, 'DRIVER'),
(5, 'Eve Driver', 'eve@car.com', 'Obelya, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:10', '09:10', 12, 5.5, 'DRIVER'),

-- Passengers
(6, 'Tom Passenger', 'tom@bus.com', 'Lozenets, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:30', '09:30', 10, 3.0, 'PASSENGER'),
(7, 'Jane Passenger', 'jane@bus.com', 'Druzhba 1, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:20', '09:20', 8, 2.5, 'PASSENGER'),
(8, 'Ivan Passenger', 'ivan@bus.com', 'Gotse Delchev, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:00', '09:00', 15, 4.5, 'PASSENGER'),
(9, 'Maria Passenger', 'maria@bus.com', 'Studentski Grad, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:05', '09:05', 10, 3.2, 'PASSENGER'),
(10, 'Peter Passenger', 'peter@bus.com', 'Hadzhi Dimitar, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:15', '09:15', 12, 4.0, 'PASSENGER'),
(11, 'Anna Passenger', 'anna@bus.com', 'Lyulin 3, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:00', '09:00', 20, 6.0, 'PASSENGER'),
(12, 'Nikolay Passenger', 'nikolay@bus.com', 'Banishora, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:10', '09:10', 12, 4.8, 'PASSENGER'),
(13, 'Elena Passenger', 'elena@bus.com', 'Geo Milev, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:25', '09:25', 7, 2.8, 'PASSENGER'),
(14, 'Todor Passenger', 'todor@bus.com', 'Zapaden Park, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:00', '09:00', 15, 5.5, 'PASSENGER'),
(15, 'Kristina Passenger', 'kristina@bus.com', 'Ovcha Kupel, Sofia, Bulgaria', 'Cherni Vrah Blvd 51, Sofia, Bulgaria', '08:10', '09:10', 10, 3.6, 'PASSENGER');

-- Insert into drivers
INSERT INTO driver (id, available_seats) VALUES
                                             (1, 3),
                                             (2, 2),
                                             (3, 4),
                                             (4, 3),
                                             (5, 2);

-- Insert into passengers
INSERT INTO passenger (id) VALUES
                               (6), (7), (8), (9), (10),
                               (11), (12), (13), (14), (15);
