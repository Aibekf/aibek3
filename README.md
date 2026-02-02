# 📚 Library Management API

**Java • JDBC • PostgreSQL • OOP**

---

## Project Overview

Library Management API is a console-based Java application designed to manage books and authors using **JDBC** and **PostgreSQL**.

The project demonstrates:

* Object-Oriented Programming principles
* Working with a relational database via JDBC
* Layered application architecture
* Custom exception handling
* CRUD operations

---

## Project Structure

The application follows a layered architecture:

```
Controller → Service → Repository → Database
```

* **Controller** – entry point of the application, handles user interaction
* **Service** – contains business logic and validation
* **Repository** – responsible for database operations (JDBC)
* **Model** – domain entities and OOP hierarchy
* **Exception** – custom exceptions
* **Interfaces** – reusable contracts
* **Utils** – database connection utility

---

## OOP Concepts Used

### Inheritance

* `Book` extends `BaseEntity`
* `EBook` and `PrintedBook` extend `Book`

### Polymorphism

Methods are overridden and called through base class references at runtime.

### Abstraction

* `BaseEntity` is an abstract class
* Interfaces are used to define behavior

### Interfaces

* `Validatable` – validation logic
* `PricedItem` – price-related behavior

---

## Database Design

The project uses **two tables**:

### authors

* `id` – primary key
* `name` – author name

### books

* `id` – primary key
* `name` – book title
* `price` – book price
* `author_id` – foreign key referencing `authors(id)`

Relationship type: **One-to-Many**
(one author can have multiple books)

---

## JDBC Usage

The application uses JDBC to:

* connect to PostgreSQL
* execute SQL queries
* perform CRUD operations

`PreparedStatement` is used instead of `Statement` to improve security and performance.

---

## CRUD Operations

The following operations are implemented:

* Create book
* Read all books
* Read book by ID
* Update book
* Delete book

All operations are executed through the service layer.

---

## Exception Handling

Custom exceptions are implemented:

* `InvalidInputException`
* `DuplicateResourceException`
* `ResourceNotFoundException`
* `DatabaseOperationException`

Exceptions are thrown in the service and repository layers and handled in the controller.

---

## How to Run

### Requirements

* Java 17+
* PostgreSQL
* pgAdmin
* PostgreSQL JDBC Driver

### Steps

1. Create a PostgreSQL database
2. Execute `schema.sql` in pgAdmin
3. Configure database credentials in `DatabaseConnection.java`
4. Run `Main` or `BookController`

---

## 📸 Screenshots

Below are screenshots demonstrating the functionality and structure of the project:

1. **Project Structure (IntelliJ IDEA)**
   [https://github.com/Aibekf/aibekasig3/blob/master/docs/screenshots/01_project_structure.png](https://github.com/Aibekf/aibekasik3/blob/master/docs/screenshots/01_project_structure.png)

2. **Database Schema (schema.sql)**
   [https://github.com/Aibekf/aibekasig3/blob/master/docs/screenshots/02_schema_sql.png](https://github.com/Aibekf/aibekasik3/blob/master/docs/screenshots/02_schema_sql.png)

3. **PostgreSQL Tables (pgAdmin)**
   [https://github.com/Aibekf/aibekasig3/blob/master/docs/screenshots/03_pgadmin_tables.png](https://github.com/Aibekf/aibekasik3/blob/master/docs/screenshots/03_pgadmin_tables.png)

4. **PostgreSQL Data View (books table)**
   [https://github.com/Aibekf/aibekasig3/blob/master/docs/screenshots/04_pgadmin_data.png](https://github.com/Aibekf/aibekasik3/blob/master/docs/screenshots/04_pgadmin_data.png)

5. **Console Output – CREATE & READ Operations**
   [https://github.com/Aibekf/aibekasig3/blob/master/docs/screenshots/05_console_create_read.png](https://github.com/Aibekf/aibekasik3/blob/master/docs/screenshots/05_console_create_read.png)

6. **Polymorphism Demonstration (BaseEntity → EBook)**
   [https://github.com/Aibekf/aibekasig3/blob/master/docs/screenshots/06_polymorphism.png](https://github.com/Aibekf/aibekasik3/blob/master/docs/screenshots/07_polymorphism.png)


These screenshots confirm correct implementation of:

* layered architecture
* JDBC database interaction
* CRUD operations
* OOP principles (inheritance, polymorphism, abstraction)
* exception handling

---

## Conclusion

This project demonstrates practical usage of:

* Java OOP concepts
* JDBC database interaction
* layered architecture
* exception handling

The application works correctly and fully meets the assignment requirements.

---

**Project Status:** Completed ✅
**Ready for submission**
