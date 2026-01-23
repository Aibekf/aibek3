package model;

public class PrintedBook extends Book {

    private int pages;

    public PrintedBook(int id, String name, double price, Author author, int pages) {
        super(id, name, price, author);
        this.pages = pages;
    }

    @Override
    public String getType() {
        return "PRINTED_BOOK";
    }

    public int getPages() {
        return pages;
    }
}
