# Initial db setup to comply with S1 reqs
# creates fresh localhost db using variables that match java program user inputs

-- Drops db during resets in case of changes
DROP DATABASE IF EXISTS cis2232_coffee_order_app;
CREATE DATABASE cis2232_coffee_order_app;
use cis2232_coffee_order_app;

-- Drops table during resets in case of changes
DROP TABLE IF EXISTS coffee_order;

CREATE TABLE coffee_order
(
    id             INT           AUTO_INCREMENT PRIMARY KEY,
    customer_name  VARCHAR(100)  NOT NULL,
    drink_type     VARCHAR(50)   NOT NULL,
    drink_size     VARCHAR(20)   NOT NULL,
    milk_type      VARCHAR(30)   DEFAULT 'None',
    quantity       INT           NOT NULL DEFAULT 1 CHECK (quantity >= 1),
    unit_price     DECIMAL(6, 2) NOT NULL,
    extra_shots    INT           NOT NULL DEFAULT 0,
    milk_surcharge DECIMAL(4, 2) NOT NULL DEFAULT 0.00,
    order_status   VARCHAR(20)   NOT NULL DEFAULT 'Pending'
);

INSERT INTO coffee_order
    (customer_name, drink_type, drink_size, milk_type, quantity, unit_price, extra_shots,
                          milk_surcharge, order_status)
-- Initial sample data
VALUES ('Alice Smith', 'Latte', 'Medium', 'Oat',
        2, 4.50, 1, 0.75, 'Pending'),
       ('Bob Jones', 'Americano', 'Large', 'None',
        1, 3.25, 0, 0.00, 'Preparing'),
       ('Charlie Brown', 'Cappuccino', 'Small', 'Whole',
        1, 4.00, 2, 0.00, 'Completed');