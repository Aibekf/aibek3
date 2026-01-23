DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;

CREATE TABLE authors (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE books (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       price DOUBLE PRECISION CHECK (price > 0),
                       author_id INTEGER NOT NULL,
                       CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors(id) ON DELETE CASCADE
);

INSERT INTO authors (name) VALUES ('George Orwell'), ('J.K. Rowling');
INSERT INTO books (name, price, author_id) VALUES ('1984', 15.99, 1);
INSERT INTO books (name, price, author_id) VALUES ('Harry Potter', 25.50, 2);