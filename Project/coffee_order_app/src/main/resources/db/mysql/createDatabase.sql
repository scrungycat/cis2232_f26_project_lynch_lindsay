# Initial db setup to comply with S1 reqs
# creates fresh localhost db using variables that match java program user inputs
# CIS-22332 | Coffee Order Application Project Database
# Author: LRML
# Since:
# Purpose: Basic filler information for database

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
    order_status   VARCHAR(20)   NOT NULL DEFAULT 'Pending',
    order_date     VARCHAR(20)   NOT NULL
);

INSERT INTO coffee_order
    (customer_name, drink_type, drink_size, milk_type, quantity, unit_price, extra_shots,
                          milk_surcharge, order_status, order_date)
-- Initial sample data
VALUES
    ('Alice Smith', 'Latte', 'Large', 'Oat', 2, 4.00, 1, 0.75, 'Completed', '2026-09-02'),
    ('Bob Jones', 'Cappuccino', 'Medium', 'Whole', 1, 3.00, 0, 0.00, 'Completed', '2026-09-04'),
    ('Charlie Brown', 'Americano', 'Small', 'None', 3, 2.00, 2, 0.00, 'Completed', '2026-09-05'),
    ('Diana Prince', 'Latte', 'Large', 'Almond', 1, 4.00, 1, 0.75, 'Completed', '2026-09-08'),
    ('Evan Wright', 'Brewed coffee', 'Medium', 'Whole', 2, 3.00, 0, 0.00, 'Completed', '2026-09-10'),
    ('Fiona Gallagher', 'Americano', 'Small', 'Oat', 1, 2.00, 1, 0.75, 'Completed', '2026-09-12'),
    ('George Clark', 'Brewed coffee', 'Large', 'Oat', 2, 4.00, 0, 0.75, 'Completed', '2026-09-15'),
    ('Hannah Abbott', 'Cappuccino', 'Medium', 'Whole', 1, 3.00, 1, 0.00, 'Completed', '2026-09-16'),
    ('Ian Malcolm', 'Latte', 'Large', 'Almond', 3, 4.00, 0, 0.75, 'Completed', '2026-09-18'),
    ('Julia Roberts', 'Cappuccino', 'Small', 'None', 1, 2.00, 0, 0.00, 'Completed', '2026-09-20'),
    ('Kevin Bacon', 'Americano', 'Medium', 'None', 2, 3.00, 1, 0.00, 'Completed', '2026-09-22'),
    ('Laura Croft', 'Latte', 'Large', 'Oat', 1, 4.00, 2, 0.75, 'Completed', '2026-09-24'),
    ('Michael Scott', 'Latte', 'Medium', 'Whole', 4, 3.00, 0, 0.00, 'Completed', '2026-09-25'),
    ('Nancy Wheeler', 'Brewed coffee', 'Small', 'Almond', 1, 2.00, 0, 0.75, 'Completed', '2026-09-28'),
    ('Oscar Martinez', 'Americano', 'Small', 'None', 2, 2.00, 1, 0.00, 'Completed', '2026-09-30'),
    ('Pam Beesly', 'Cappuccino', 'Large', 'Oat', 1, 4.00, 0, 0.75, 'Completed', '2026-10-02'),
    ('Quentin Tarantino', 'Latte', 'Medium', 'Whole', 3, 3.00, 1, 0.00, 'Completed', '2026-10-05'),
    ('Rachel Green', 'Cappuccino', 'Small', 'Whole', 1, 2.00, 0, 0.00, 'Completed', '2026-10-07'),
    ('Steve Rogers', 'Americano', 'Large', 'None', 2, 4.00, 2, 0.00, 'Completed', '2026-10-10'),
    ('Tony Stark', 'Latte', 'Large', 'Oat', 5, 4.00, 3, 0.75, 'Completed', '2026-10-12'),
    ('Uma Thurman', 'Brewed coffee', 'Small', 'Almond', 1, 2.00, 0, 0.75, 'Completed', '2026-10-15'),
    ('Victor Vance', 'Brewed coffee', 'Medium', 'Whole', 2, 3.00, 0, 0.00, 'Completed', '2026-10-18'),
    ('Wanda Maximoff', 'Cappuccino', 'Large', 'Oat', 1, 4.00, 1, 0.75, 'Completed', '2026-10-20'),
    ('Yoda Jedi', 'Americano', 'Small', 'None', 1, 2.00, 2, 0.00, 'Completed', '2026-10-22'),
    ('Zack Snyder', 'Latte', 'Large', 'Oat', 2, 4.00, 1, 0.75, 'Completed', '2026-10-24'),
    ('Bruce Wayne', 'Cappuccino', 'Large', 'Almond', 1, 4.00, 2, 0.75, 'Completed', '2026-10-26'),
    ('Clark Kent', 'Brewed coffee', 'Medium', 'Whole', 3, 3.00, 0, 0.00, 'Completed', '2026-10-28');