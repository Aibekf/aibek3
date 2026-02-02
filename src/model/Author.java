package model;

public class Author extends BaseEntity {

    public Author(int id, String name) {
        super(id, name);
    }

    @Override
    public String getType() {
        return "AUTHOR";
    }

    @Override
    public double calculateValue() {
        return 0;
    }
}

