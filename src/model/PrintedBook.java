package model;

public class PrintedBook extends Book {

    private final int pages;

    public PrintedBook(int id, String name, double price, Author author, int pages) {
        super(id, name, price, author);
        this.pages = pages;
    }

    @Override
    public String getType() {
        return "PRINTED_BOOK";
    }
}
