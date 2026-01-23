package Repository;

import model.Book;
import model.Author;
import utils.DatabaseConnection;
import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    public void create(Book book) {
        String sql = "INSERT INTO books(name, price, author_id) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getName());
            ps.setDouble(2, book.getPrice());
            Author author = book.getAuthor();
            if (author == null) {
                throw new IllegalArgumentException("Author cannot be null");
            }
            ps.setInt(3, author.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error creating book", e);
        }
    }

    public List<Book> getAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.name, b.price, a.id AS author_id, a.name AS author_name " +
                "FROM books b LEFT JOIN authors a ON b.author_id = a.id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Author author = null;
                int authorId = rs.getInt("author_id");
                String authorName = rs.getString("author_name");
                if (authorName != null) {
                    author = new Author(authorId, authorName);
                }
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        author
                ));
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching books", e);
        }

        return books;
    }

    public Book getById(int id) {
        String sql = "SELECT b.id, b.name, b.price, a.id AS author_id, a.name AS author_name " +
                "FROM books b LEFT JOIN authors a ON b.author_id = a.id WHERE b.id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new ResourceNotFoundException("Book not found");
            }

            Author author = null;
            int authorId = rs.getInt("author_id");
            String authorName = rs.getString("author_name");
            if (authorName != null) {
                author = new Author(authorId, authorName);
            }

            return new Book(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    author
            );

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error fetching book", e);
        }
    }

    public void update(int id, Book book) {
        String sql = "UPDATE books SET name = ?, price = ?, author_id = ? WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, book.getName());
            ps.setDouble(2, book.getPrice());
            Author author = book.getAuthor();
            if (author == null) {
                throw new IllegalArgumentException("Author cannot be null");
            }
            ps.setInt(3, author.getId());
            ps.setInt(4, id);

            if (ps.executeUpdate() == 0) {
                throw new ResourceNotFoundException("Book not found");
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error updating book", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            if (ps.executeUpdate() == 0) {
                throw new ResourceNotFoundException("Book not found");
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Error deleting book", e);
        }
    }
}
