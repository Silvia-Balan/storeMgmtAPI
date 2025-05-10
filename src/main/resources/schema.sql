CREATE TABLE IF NOT EXISTS `products` (
    `id` int AUTO_INCREMENT  PRIMARY KEY,
    `name` varchar(100) NOT NULL,
    `description` TEXT,
    `price` DECIMAL(10,2) NOT NULL,
    `quantity` INT NOT NULL,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );