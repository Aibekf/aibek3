package model;

public class EBook extends Book {

    private final String format;

    public EBook(int id, String name, double price, Author author, String format) {
        super(id, name, price, author);
        this.format = format;
    }

    @Override
    public String getType() {
        return "EBOOK";
    }
}
