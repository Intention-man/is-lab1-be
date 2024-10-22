CREATE OR REPLACE FUNCTION delete_vehicles_by_fuel_consumption(target_fuelConsumption DOUBLE PRECISION) RETURNS VOID AS
$$
BEGIN
    DELETE FROM Vehicle WHERE fuelConsumption = target_fuelConsumption;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION delete_one_vehicle_by_fuel_consumption(target_fuelConsumption DOUBLE PRECISION) RETURNS VOID AS
$$
BEGIN
    DELETE
    FROM Vehicle
    WHERE id = (SELECT id FROM Vehicle WHERE fuelConsumption = target_fuelConsumption LIMIT 1);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION unique_fuel_consumptions() RETURNS SETOF DOUBLE PRECISION AS
$$
BEGIN
    RETURN QUERY SELECT DISTINCT fuelConsumption FROM Vehicle;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION find_vehicles_by_type(target_type VARCHAR)
    RETURNS TABLE
            (
                vehicle_id   INTEGER,
                vehicle_name VARCHAR
            )
AS
$$
BEGIN
    RETURN QUERY
        SELECT id, name
        FROM Vehicle
        WHERE type = target_type;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION add_wheels_to_vehicle(target_id INTEGER, additional_wheels BIGINT) RETURNS VOID AS $$
BEGIN
    UPDATE Vehicle
    SET numberOfWheels = numberOfWheels + additional_wheels
    WHERE id = target_id;
END;
$$ LANGUAGE plpgsql;