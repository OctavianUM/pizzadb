delete from delivery; alter table delivery auto_increment = 0; select * from delivery;
delete from orderItem; alter table orderItem auto_increment = 0; select * from orderItem;
delete from `order`; alter table `order` auto_increment = 0; select * from `order`;
delete from delivery; alter table delivery auto_increment = 0; select * from delivery;
delete from orderItem; alter table orderItem auto_increment = 0; select * from orderItem;


-- return all menuitems ordered together with amount and total price
SELECT 
    mi.name AS item_name,
    SUM(oi.quantity) AS total_quantity_ordered,
    recipe_cost.total_item_price AS menuitem_price,
    (SUM(oi.quantity) * recipe_cost.total_item_price) AS total_price
FROM 
    orderitem oi
JOIN 
    menuitem mi ON oi.menuItemID = mi.menuItemID
JOIN 
    `order` o ON oi.orderID = o.orderID
JOIN 
    (
        SELECT 
            r.menuItemID,
            SUM(i.price * r.amount) AS total_item_price
        FROM 
            recipe r
        JOIN 
            ingredient i ON r.ingredientID = i.ingredientID
        GROUP BY 
            r.menuItemID
    ) recipe_cost ON mi.menuItemID = recipe_cost.menuItemID
WHERE 
    MONTH(o.orderTime) = 10
    AND YEAR(o.orderTime) = 2024
GROUP BY 
    mi.menuItemID
ORDER BY 
    total_quantity_ordered DESC;

-- returns the total amount of orders made in a month together with price
SELECT 
    COUNT(DISTINCT o.orderID) AS total_orders,
    SUM(oi.quantity * recipe_cost.total_item_price) AS total_revenue
FROM 
    `order` o
JOIN 
    orderitem oi ON o.orderID = oi.orderID
JOIN 
    menuitem mi ON oi.menuItemID = mi.menuItemID
JOIN 
    (
        -- gets recipe cost for each menuitem
        SELECT 
            r.menuItemID,
            SUM(i.price * r.amount) AS total_item_price
        FROM 
            recipe r
        JOIN 
            ingredient i ON r.ingredientID = i.ingredientID
        GROUP BY 
            r.menuItemID
    ) recipe_cost ON mi.menuItemID = recipe_cost.menuItemID
WHERE 
    MONTH(o.orderTime) = 10
    AND YEAR(o.orderTime) = 2024;

-- return orders made with total combined price for a month filter by gender, age, postal
SELECT 
    COUNT(DISTINCT o.orderID) AS total_orders,
    SUM(oi.quantity * recipe_cost.total_item_price) AS total_revenue
FROM 
    `order` o
JOIN 
    orderitem oi ON o.orderID = oi.orderID
JOIN 
    menuitem mi ON oi.menuItemID = mi.menuItemID
JOIN 
    customer c ON o.customerID = c.customerID
JOIN 
    adress a ON c.adressID = a.adressId
JOIN 
    (
        SELECT 
            r.menuItemID,
            SUM(i.price * r.amount) AS total_item_price
        FROM 
            recipe r
        JOIN 
            ingredient i ON r.ingredientID = i.ingredientID
        GROUP BY 
            r.menuItemID
    ) recipe_cost ON mi.menuItemID = recipe_cost.menuItemID
WHERE 
    MONTH(o.orderTime) = 10  
    AND YEAR(o.orderTime) = 2024
    AND TIMESTAMPDIFF(YEAR, c.birthdate, CURDATE()) BETWEEN 18 AND 30;
    AND a.postal = 'your_postal_code'
    AND c.gender = 'M' 
