import model.*;
import service.BookService;
import exception.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        BookService bookService = new BookService();

        Author author = new Author(1, "George Orwell");

        try {
            System.out.println("=== CREATE ===");
            Book book1 = new EBook(0, "1984", 15.99, author, "PDF");
            Book book2 = new PrintedBook(0, "Animal Farm", 20.50, author, 120);

            bookService.create(book1);
            bookService.create(book2);

            System.out.println("Books created successfully\n");

            System.out.println("=== READ ALL ===");
            List<Book> books = bookService.getAll();
            for (Book b : books) {
                System.out.println(
                        b.info()
                                + " | Type: " + b.getType()
                                + " | Price: " + b.getPrice()
                );
            }

            System.out.println("\n=== READ BY ID ===");
            Book found = bookService.getById(1);
            System.out.println(found.info());

            System.out.println("\n=== UPDATE ===");
            Book updated = new Book(1, "1984 (Updated)", 18.99, author);
            bookService.update(1, updated);
            System.out.println("Book updated");

            System.out.println("\n=== DELETE ===");
            bookService.delete(2);
            System.out.println("Book deleted");

            System.out.println("\n=== VALIDATION ERROR DEMO ===");
            Book invalidBook = new Book(0, "", -5, author);
            bookService.create(invalidBook);

        } catch (InvalidInputException e) {
            System.out.println("Invalid input: " + e.getMessage());

            System.out.println("\n=== VALIDATION  ===");

        } catch (ResourceNotFoundException e) {
            System.out.println("Not found: " + e.getMessage());

        } catch (DatabaseOperationException e) {
            System.out.println("Database error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        System.out.println("\n=== PROGRAM FINISHED ===");
    }
}
