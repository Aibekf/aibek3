package service;

import model.Book;
import Repository.BookRepository;
import exception.InvalidInputException;

import java.util.List;

public class BookService {

    private final BookRepository repository = new BookRepository();

    public void create(Book book) {
        validate(book);
        repository.create(book);
    }

    public List<Book> getAll() {
        return repository.getAll();
    }

    public Book getById(int id) {
        if (id <= 0) {
            throw new InvalidInputException("ID must be greater than 0");
        }
        return repository.getById(id);
    }

    public void update(int id, Book book) {
        if (id <= 0) {
            throw new InvalidInputException("ID must be greater than 0");
        }
        validate(book);
        repository.update(id, book);
    }

    public void delete(int id) {
        if (id <= 0) {
            throw new InvalidInputException("ID must be greater than 0");
        }
        repository.delete(id);
    }

    private void validate(Book book) {
        if (book == null) {
            throw new InvalidInputException("Book cannot be null");
        }

        if (book.getName() == null || book.getName().trim().isEmpty()) {
            throw new InvalidInputException("Book name cannot be empty");
        }

        if (book.getPrice() <= 0) {
            throw new InvalidInputException("Price must be greater than 0");
        }

        if (book.getAuthor() == null) {
            throw new InvalidInputException("Author is required");
        }
    }
}
