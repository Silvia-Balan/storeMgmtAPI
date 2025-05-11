CREATE TABLE IF NOT EXISTS `products` (
    `id` INT AUTO_INCREMENT  PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `description` VARCHAR(500),
    `price` DECIMAL(10,2) NOT NULL,
    `quantity` INT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

INSERT INTO `products` (name, description, price, quantity)
    VALUES
    ('Wireless Mouse', 'Ergonomic wireless mouse with USB receiver', 25.99, 100),
    ('Mechanical Keyboard', 'RGB mechanical keyboard with blue switches', 89.50, 50),
    ('Laptop Stand', 'Adjustable aluminum stand for laptops', 39.99, 75),
    ('USB-C Hub', '7-in-1 USB-C hub with HDMI and card reader', 29.99, 120),
    ('Noise Cancelling Headphones', 'Over-ear wireless headphones with ANC', 149.99, 30);

CREATE TABLE IF NOT EXISTS `users` (
    `username` VARCHAR(50) NOT NULL PRIMARY KEY,
    `password` VARCHAR(68) NOT NULL,
    `enabled` TINYINT NOT NULL
);

INSERT INTO `users`
    VALUES
    ('client','{bcrypt}$2a$10$4eKHZfFucxxXgXZPELHdSeIiisEkLwDb4L3shAdTqOys9DtVdxPQ2',1),
    ('vendor','{bcrypt}$2a$10$.gL069Wo7vsV9quv2rI7murgSaABH6XMD36CWMScbb8DasgIgf6wC',1),
    ('admin','{bcrypt}$2a$10$cHnRVIcXA6g0iJh0LBFjx.w9QB2ifK80iq4hIPWDjguTraw9Gl3im',1);

CREATE TABLE IF NOT EXISTS `authorities` (
    `username` VARCHAR(50) NOT NULL,
    `authority` VARCHAR(50) NOT NULL,
    CONSTRAINT `unique_user_authority` UNIQUE (`username`, `authority`),
    CONSTRAINT `fk_user` FOREIGN KEY (`username`) REFERENCES `users`(`username`)
 );

 INSERT INTO `authorities`
     VALUES
     ('client','ROLE_CLIENT'),
     ('vendor','ROLE_CLIENT'),
     ('vendor','ROLE_VENDOR'),
     ('admin','ROLE_CLIENT'),
     ('admin','ROLE_VENDOR'),
     ('admin','ROLE_ADMIN');