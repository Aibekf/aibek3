package service;

import exception.InvalidInputException;
import model.Book;
import Repository.AuthorRepository;
import Repository.BookRepository;
import Repository.interfaces.CrudRepository;

import java.util.List;

public class BookService {

    private final CrudRepository<Book> bookRepo;
    private final AuthorRepository authorRepo;

    public BookService(CrudRepository<Book> bookRepo, AuthorRepository authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }

    public void create(Book book) {
        validateBook(book);
        bookRepo.create(book);
    }

    public List<Book> getAll() {
        return bookRepo.getAll();
    }

    public Book getById(int id) {
        if (id <= 0) throw new InvalidInputException("ID must be greater than 0");
        return bookRepo.getById(id);
    }

    public void update(int id, Book book) {
        if (id <= 0) throw new InvalidInputException("ID must be greater than 0");
        validateBook(book);
        bookRepo.update(id, book);
    }

    public void delete(int id) {
        if (id <= 0) throw new InvalidInputException("ID must be greater than 0");
        bookRepo.delete(id);
    }
    public List<Book> getAllSortedByPrice() {
        List<Book> books = bookRepo.getAll();
        books.sort((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice()));
        return books;
    }


    public List<Book> getByAuthor(String authorName) {
        if (authorName == null || authorName.isBlank()) {
            throw new InvalidInputException("Author name cannot be empty");
        }
        if (!(bookRepo instanceof BookRepository br)) {
            throw new InvalidInputException("Repository does not support findByAuthor");
        }
        return br.findByAuthor(authorName);
    }

    private void validateBook(Book book) {
        if (book == null) throw new InvalidInputException("Book cannot be null");
        try {
            book.validate(book);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid book data");
        }
        if (book.getAuthor() == null || book.getAuthor().getId() <= 0) {
            throw new InvalidInputException("Author is required");
        }
        if (!authorRepo.existsById(book.getAuthor().getId())) {
            throw new InvalidInputException("Author does not exist in DB");
        }
    }
}
