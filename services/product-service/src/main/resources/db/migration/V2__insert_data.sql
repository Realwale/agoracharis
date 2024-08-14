INSERT INTO category (id, name, description)
VALUES
    (nextval('category_seq'), 'Electronics', 'Electronic devices and gadgets'),
    (nextval('category_seq'), 'Books', 'Various genres of books and literature'),
    (nextval('category_seq'), 'Clothing', 'Apparel for men, women, and children'),
    (nextval('category_seq'), 'Home Appliances', 'Appliances for home use'),
    (nextval('category_seq'), 'Toys', 'Toys and games for children');


INSERT INTO product (id, available_quantity, description, name, sku, price, category_id)
VALUES
    (nextval('product_seq'), 100, 'Smartphone with 6GB RAM', 'Smartphone', 'SKU-12345', 299.99, (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 50, 'Science Fiction novel', 'Sci-Fi Book', 'SKU-67890', 19.99, (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 200, 'Mens T-Shirt', 'T-Shirt', 'SKU-11111', 9.99, (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 30, 'Blender with multiple settings', 'Blender', 'SKU-22222', 49.99, (SELECT id FROM category WHERE name = 'Home Appliances')),
    (nextval('product_seq'), 150, 'Building Blocks Set', 'Building Blocks', 'SKU-33333', 24.99, (SELECT id FROM category WHERE name = 'Toys'));


