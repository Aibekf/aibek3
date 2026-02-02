package model;

import interfaces.PricedItem;
import interfaces.Validatable;

public class Book extends BaseEntity implements Validatable<Book>, PricedItem {

    protected double price;
    protected Author author;

    public Book(int id, String name, double price, Author author) {
        super(id, name);
        this.price = price;
        this.author = author;
    }

    @Override
    public void validate(Book b) {
        Validatable.notBlank(b.name);
        Validatable.positive(b.price);
        if (b.author == null || b.author.getId() <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getType() {
        return "BOOK";
    }

    @Override
    public double calculateValue() {
        return price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public Author getAuthor() {
        return author;
    }
}
