package controller;

import model.*;
import Repository.AuthorRepository;
import Repository.BookRepository;
import service.BookService;

public class BookController {

    public static void main(String[] args) {

        BookService service =
                new BookService(new BookRepository(), new AuthorRepository());

        Author author = new Author(1, "George Orwell");

        BaseEntity e = new EBook(0, "1984", 10, author, "PDF");
        System.out.println(e.getType());

        service.create(new PrintedBook(0, "Animal Farm", 20, author, 120));

        service.getAllSortedByPrice()
                .forEach(b -> System.out.println(b.info()));
    }
}
