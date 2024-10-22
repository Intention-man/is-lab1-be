INSERT INTO users (id, username, password, role)
VALUES (1, 'user1', 'password1', 'USER'),
       (2, 'user2', 'password2', 'USER'),
       (3, 'user3', 'password3', 'ADMIN'),
       (4, 'user4', 'password4', 'USER'),
       (5, 'user5', 'password5', 'ADMIN');

INSERT INTO Coordinates (x, y)
VALUES (500, 300),
       (200, 150),
       (100, 400);

INSERT INTO Vehicle (name, coordinates_id, type, enginePower, numberOfWheels, capacity, distanceTravelled,
                     fuelConsumption, fuelType)
VALUES ('Boeing 747', 1, 'PLANE', 50000, 18, 18370, 700000, 9000.0, 'GASOLINE'),
       ('Yamaha YZF-R1', 2, 'MOTORCYCLE', 200, 2, 150, 15000, 15.0, 'GASOLINE'),
       ('Yellow Submarine', 3, 'SUBMARINE', 1, 5000, 20000, 25000, 1000.0,
        'ALCOHOL'), -- Установите минимальное положительное значение
       ('Apollo Lunar Module', 1, 'SPACESHIP', 1500, 4, 500, 384400, NULL, 'NUCLEAR');