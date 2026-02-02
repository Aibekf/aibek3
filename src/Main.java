import Repository.AuthorRepository;
import Repository.BookRepository;
import Repository.interfaces.CrudRepository;
import model.*;
import service.BookService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        CrudRepository<Book> bookRepo = new BookRepository();
        AuthorRepository authorRepo = new AuthorRepository();
        BookService bookService = new BookService(bookRepo, authorRepo);

        Author author = new Author(1, "George Orwell");

        try {
            System.out.println("=== CREATE ===");
            Book book1 = new EBook(0, "1984", 15.99, author, "PDF");
            Book book2 = new PrintedBook(0, "Animal Farm", 20.50, author, 120);

            bookService.create(book1);
            bookService.create(book2);

            System.out.println("\n=== READ ALL ===");
            List<Book> books = bookService.getAll();
            for (Book b : books) {
                System.out.println(b.info() + " | Type: " + b.getType());
            }

            System.out.println("\n=== GET BY ID ===");
            Book one = bookService.getById(1);
            System.out.println(one.info());

            System.out.println("\n=== UPDATE ===");
            Book updated = new Book(0, "1984 (Updated)", 18.99, author);
            bookService.update(1, updated);

            System.out.println("\n=== DELETE ===");
            bookService.delete(1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n=== FINISHED ===");
    }
}
