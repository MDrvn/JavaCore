DROP TABLE IF EXISTS order_item;
DROP TABLE IF EXISTS pizza_order;
DROP TABLE IF EXISTS pizza;
DROP TABLE IF EXISTS customer;

CREATE TABLE pizza (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    is_deleted BOOLEAN DEFAULT false
);

CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN DEFAULT false
);

CREATE TABLE pizza_order (
    id SERIAL PRIMARY KEY,
    customer_id INTEGER NOT NULL REFERENCES customer(id),
    order_date DATE NOT NULL DEFAULT CURRENT_DATE,
    status VARCHAR(50) DEFAULT 'NEW',
    is_deleted BOOLEAN DEFAULT false
);

CREATE TABLE order_item (
    id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL REFERENCES pizza_order(id),
    pizza_id INTEGER NOT NULL REFERENCES pizza(id),
    quantity INTEGER NOT NULL CHECK (quantity > 0),
    is_deleted BOOLEAN DEFAULT false
);