package controller;

import model.Author;
import model.Book;
import model.EBook;
import model.PrintedBook;
import service.BookService;
import exception.*;

import java.util.List;

public class BookController{

    public static void main(String[] args) {

        BookService bookService = new BookService();
        Author author = new Author(1, "George Orwell");

        try {
            System.out.println("=== CREATE BOOKS ===");

            Book book1 = new EBook(0, "1984", 15.99, author, "PDF");
            Book book2 = new PrintedBook(0, "Animal Farm", 20.50, author, 120);

            bookService.create(book1);
            bookService.create(book2);

            System.out.println("Books created successfully\n");

            System.out.println("=== GET ALL BOOKS ===");
            List<Book> books = bookService.getAll();
            for (Book b : books) {
                System.out.println(
                        b.info()
                                + " | Type: " + b.getType()
                                + " | Price: " + b.getPrice()
                );
            }

            System.out.println("\n=== GET BOOK BY ID ===");
            Book foundBook = bookService.getById(1);
            System.out.println(foundBook.info());

            System.out.println("\n=== UPDATE BOOK ===");
            Book updatedBook = new Book(1, "1984 (Updated)", 18.99, author);
            bookService.update(1, updatedBook);
            System.out.println("Book updated successfully");

            System.out.println("\n=== DELETE BOOK ===");
            bookService.delete(2);
            System.out.println("Book deleted successfully");

            System.out.println("\n=== VALIDATION ERROR DEMO ===");
            Book invalidBook = new Book(0, "", -5, author);
            bookService.create(invalidBook);

        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());

        } catch (ResourceNotFoundException e) {
            System.out.println("Resource not found: " + e.getMessage());

        } catch (DatabaseOperationException e) {
            System.out.println("Database error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        System.out.println("\n=== PROGRAM FINISHED ===");
    }
}
