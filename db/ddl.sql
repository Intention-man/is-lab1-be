CREATE TABLE IF NOT EXISTS users
(
    id       BIGSERIAL PRIMARY KEY, -- Use BIGSERIAL for automatic id generation for compatibility
    username VARCHAR(100) NOT NULL, -- Username column with a maximum length of 100
    password VARCHAR(255) NOT NULL, -- Password column with a maximum length of 255
    role     VARCHAR(20)  NOT NULL  -- Role column
);

CREATE TABLE IF NOT EXISTS admin_request
(
    id           BIGSERIAL PRIMARY KEY,          -- Auto-incrementing primary key
    requester_id BIGINT,                         -- Foreign key referencing the User entity
    is_approved  BOOLEAN NOT NULL,               -- Boolean column for approval status
    CONSTRAINT fk_requester FOREIGN KEY (requester_id)
        REFERENCES users (id) ON DELETE SET NULL -- Set requester_id to NULL if the user is deleted
);

CREATE TABLE IF NOT EXISTS Coordinates
(
    id SERIAL PRIMARY KEY,
    x  BIGINT NOT NULL CHECK (x <= 625),
    y  BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS Vehicle
(
    id                SERIAL PRIMARY KEY,
    name              VARCHAR(255) NOT NULL CHECK (name <> ''),
    coordinates_id    INT          NOT NULL,
    creationDate      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    type              VARCHAR(50),
    enginePower       INT CHECK (enginePower > 0),
    numberOfWheels    BIGINT       NOT NULL CHECK (numberOfWheels > 0),
    capacity          BIGINT       NOT NULL CHECK (capacity > 0),
    distanceTravelled BIGINT CHECK (distanceTravelled > 0),
    fuelConsumption   DOUBLE PRECISION CHECK (fuelConsumption > 0),
    fuelType          VARCHAR(50),
    FOREIGN KEY (coordinates_id) REFERENCES Coordinates (id)
);