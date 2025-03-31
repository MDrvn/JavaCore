-- Запросы на чтение данных (SELECT)

-- 1. Получить список всех товаров
SELECT * FROM product;

-- 2. Получить список всех покупателей
SELECT * FROM customer;

-- 3. Получить все заказы с информацией о товаре и покупателе
SELECT
    o.id AS order_id,
    o.order_date,
    p.description AS product_name,
    p.price AS unit_price,
    o.quantity AS ordered_quantity,
    (p.price * o.quantity) AS total_price,
    c.first_name || ' ' || c.last_name AS customer_name
FROM "order" o
JOIN product p ON o.product_id = p.id
JOIN customer c ON o.customer_id = c.id;

-- 4. Получить топ-5 самых дорогих товаров
SELECT description, price
FROM product
ORDER BY price DESC
LIMIT 5;

-- 5. Получить покупателей, сделавших больше 1 заказа
SELECT
    c.id,
    c.first_name || ' ' || c.last_name AS customer_name,
    COUNT(o.id) AS orders_count
FROM customer c
JOIN "order" o ON c.id = o.customer_id
GROUP BY c.id
HAVING COUNT(o.id) > 1
ORDER BY orders_count DESC;

-- 6. Получить товары с остатком на складе меньше 10
SELECT description, quantity
FROM product
WHERE quantity < 10
ORDER BY quantity;

-- 7. Получить общую выручку по месяцам
SELECT
    EXTRACT(MONTH FROM o.order_date) AS month,
    EXTRACT(YEAR FROM o.order_date) AS year,
    SUM(p.price * o.quantity) AS total_revenue
FROM "order" o
JOIN product p ON o.product_id = p.id
GROUP BY year, month
ORDER BY year, month;

-- 8. Получить самый популярный товар
SELECT
    p.description,
    SUM(o.quantity) AS total_ordered
FROM product p
JOIN "order" o ON p.id = o.product_id
GROUP BY p.id
ORDER BY total_ordered DESC
LIMIT 1;

-- 9. Получить покупателей с наибольшей суммой заказов
SELECT
    c.first_name || ' ' || c.last_name AS customer_name,
    SUM(p.price * o.quantity) AS total_spent
FROM customer c
JOIN "order" o ON c.id = o.customer_id
JOIN product p ON o.product_id = p.id
GROUP BY c.id
ORDER BY total_spent DESC
LIMIT 3;


-- Запросы на изменение данных (UPDATE)

-- 10. Увеличить цену на 10% для товаров категории "Ноутбук"
UPDATE product
SET price = price * 1.1
WHERE description LIKE '%Ноутбук%';

-- 11. Обновить фамилию покупателя
UPDATE customer
SET last_name = 'Сидоров'
WHERE first_name = 'Иван' AND last_name = 'Иванов';

-- 12. Обновить количество товара после продажи
UPDATE product
SET quantity = quantity - 5
WHERE id = 3;


-- ЗАПРОСЫ НА УДАЛЕНИЕ ДАННЫХ (DELETE)

-- 13. Удалить заказы старше 2024 года
DELETE FROM "order"
WHERE order_date < '2024-01-01';

-- 14. Удалить товар, которого нет на складе
DELETE FROM product
WHERE quantity = 0;

-- 15. Удалить покупателя без заказов
DELETE FROM customer
WHERE id NOT IN (SELECT DISTINCT customer_id FROM "order");

-- Для интереса еще несколько более сложных вариантов запросов

-- 16. Получить средний чек по месяцам
SELECT
    EXTRACT(MONTH FROM order_date) AS month,
    EXTRACT(YEAR FROM order_date) AS year,
    AVG(p.price * o.quantity) AS avg_check
FROM "order" o
JOIN product p ON o.product_id = p.id
GROUP BY year, month
ORDER BY year, month;

-- 17. Получить покупателей, которые не делали заказы
SELECT *
FROM customer
WHERE id NOT IN (SELECT DISTINCT customer_id FROM "order");