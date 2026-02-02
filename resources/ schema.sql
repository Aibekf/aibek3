CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(100)
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(150),
                       price DOUBLE PRECISION,
                       author_id INT,
                       FOREIGN KEY (author_id) REFERENCES authors(id)
);

INSERT INTO authors (name) VALUES ('George Orwell');

INSERT INTO books (name, price, author_id)
VALUES ('1984', 15.99, 1);

INSERT INTO books (name, price, author_id)
VALUES ('Animal Farm', 20.50, 1);
