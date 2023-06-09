CREATE TABLE products (
    id serial primary key,
    product_type VARCHAR not null,

    CONSTRAINT check_product_type CHECK (product_type in ('game','console')),
    UNIQUE (id, product_type)
);

ALTER TABLE games
ADD COLUMN product_type VARCHAR DEFAULT 'game' NOT NULL;

ALTER TABLE consoles
ADD COLUMN product_type VARCHAR DEFAULT 'console' NOT NULL;