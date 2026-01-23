package model;

import interfaces.Validatable;
import interfaces.PricedItem;

public class Book extends BaseEntity implements Validatable, PricedItem {

    protected double price;
    protected Author author;

    public Book(int id, String name, double price, Author author) {
        super(id, name);
        this.price = price;
        this.author = author;
    }


    public Author getAuthor() {
        return author;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public void validate() {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Book name is empty");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
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
}

