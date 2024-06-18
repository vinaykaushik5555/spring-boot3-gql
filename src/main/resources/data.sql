-- Create the products table
CREATE TABLE products (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    stock INT,
    category VARCHAR(255),
    manufacturer VARCHAR(255),
    created_date TIMESTAMP
);

-- Insert some real-world products
INSERT INTO products (name, description, price, stock, category, manufacturer, created_date)
VALUES
('Apple iPhone 13', 'Latest model of the Apple iPhone series', 999.99, 50, 'Electronics', 'Apple Inc.', CURRENT_TIMESTAMP),
('Samsung Galaxy S21', 'Newest release in the Samsung Galaxy line', 799.99, 75, 'Electronics', 'Samsung', CURRENT_TIMESTAMP),
('Sony WH-1000XM4', 'Industry-leading noise-canceling headphones', 349.99, 100, 'Audio', 'Sony', CURRENT_TIMESTAMP),
('Dell XPS 13', 'Powerful and compact laptop from Dell', 1199.99, 30, 'Computers', 'Dell', CURRENT_TIMESTAMP),
('Nintendo Switch', 'Popular gaming console from Nintendo', 299.99, 150, 'Gaming', 'Nintendo', CURRENT_TIMESTAMP),
('Amazon Echo Dot', 'Smart speaker with Alexa', 49.99, 200, 'Smart Home', 'Amazon', CURRENT_TIMESTAMP);
