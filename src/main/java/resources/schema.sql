-- Удаляем таблицы в правильном порядке (сначала зависимые, потом основные)
DROP TABLE IF EXISTS "order";
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS customer;

-- Создаем таблицу товаров:
CREATE TABLE IF NOT EXISTS product (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    quantity INTEGER NOT NULL
);

COMMENT ON TABLE product IS 'Таблица для хранения информации о товарах';
COMMENT ON COLUMN product.id IS 'Уникальный идентификатор товара';
COMMENT ON COLUMN product.description IS 'Описание товара';
COMMENT ON COLUMN product.price IS 'Стоимость товара за единицу';
COMMENT ON COLUMN product.quantity IS 'Количество товара на складе';

-- Создаем таблицу покупателей:
CREATE TABLE IF NOT EXISTS customer (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL
);

COMMENT ON TABLE customer IS 'Таблица для хранения информации о покупателях';
COMMENT ON COLUMN customer.id IS 'Уникальный идентификатор покупателя';
COMMENT ON COLUMN customer.first_name IS 'Имя покупателя';
COMMENT ON COLUMN customer.last_name IS 'Фамилия покупателя';

-- Создаем таблицу заказов:
CREATE TABLE IF NOT EXISTS "order" (
    id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL,
    customer_id INTEGER NOT NULL,
    order_date DATE NOT NULL DEFAULT CURRENT_DATE,
    quantity INTEGER NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);

COMMENT ON TABLE "order" IS 'Таблица для хранения информации о заказах';
COMMENT ON COLUMN "order".id IS 'Уникальный идентификатор заказа';
COMMENT ON COLUMN "order".product_id IS 'Идентификатор товара (внешний ключ)';
COMMENT ON COLUMN "order".customer_id IS 'Идентификатор покупателя (внешний ключ)';
COMMENT ON COLUMN "order".order_date IS 'Дата заказа';
COMMENT ON COLUMN "order".quantity IS 'Количество заказанного товара';

-- Заполняем таблицу товаров:
INSERT INTO product (description, price, quantity)
VALUES
    ('Ноутбук Lenovo IdeaPad', 45000.00, 15),
    ('Смартфон Samsung Galaxy S23', 79990.00, 8),
    ('Наушники Sony WH-1000XM4', 19990.00, 20),
    ('Планшет Apple iPad Air', 54990.00, 12),
    ('Монитор Dell 27"', 28990.00, 10),
    ('Клавиатура Logitech MX Keys', 8990.00, 25),
    ('Мышь беспроводная Razer', 4990.00, 30),
    ('Фитнес-браслет Xiaomi Mi Band 7', 2990.00, 40),
    ('Внешний жесткий диск Seagate 1TB', 4990.00, 18),
    ('Роутер TP-Link Archer AX10', 5990.00, 15);

-- Заполняем таблицу покупателей:
INSERT INTO customer (first_name, last_name)
VALUES
    ('Иван', 'Иванов'),
    ('Петр', 'Петров'),
    ('Сергей', 'Сергеев'),
    ('Анна', 'Кузнецова'),
    ('Елена', 'Смирнова'),
    ('Дмитрий', 'Васильев'),
    ('Ольга', 'Новикова'),
    ('Алексей', 'Федоров'),
    ('Мария', 'Морозова'),
    ('Андрей', 'Волков');

-- Заполняем таблицу заказов:
INSERT INTO "order" (product_id, customer_id, order_date, quantity)
VALUES
    (1, 1, '2023-01-15', 1),
    (2, 2, '2023-01-16', 1),
    (3, 3, '2023-01-17', 2),
    (4, 4, '2023-01-18', 1),
    (5, 5, '2023-01-19', 1),
    (6, 6, '2023-01-20', 1),
    (7, 7, '2023-01-21', 3),
    (8, 8, '2023-01-22', 2),
    (9, 9, '2023-01-23', 1),
    (10, 10, '2023-01-24', 1);