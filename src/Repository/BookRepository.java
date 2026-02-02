package Repository;

import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.Author;
import model.Book;
import Repository.interfaces.CrudRepository;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements CrudRepository<Book> {

    @Override
    public void create(Book book) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO books(name,price,author_id) VALUES (?,?,?)")) {
            ps.setString(1, book.getName());
            ps.setDouble(2, book.getPrice());
            ps.setInt(3, book.getAuthor().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }

    @Override
    public List<Book> getAll() {
        List<Book> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT b.id,b.name,b.price,a.id,a.name FROM books b JOIN authors a ON b.author_id=a.id");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        new Author(rs.getInt(4), rs.getString(5))
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
        return list;
    }

    @Override
    public Book getById(int id) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT b.id,b.name,b.price,a.id,a.name FROM books b JOIN authors a ON b.author_id=a.id WHERE b.id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) throw new ResourceNotFoundException("");
            return new Book(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getDouble(3),
                    new Author(rs.getInt(4), rs.getString(5))
            );
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }

    @Override
    public void update(int id, Book book) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "UPDATE books SET name=?,price=?,author_id=? WHERE id=?")) {
            ps.setString(1, book.getName());
            ps.setDouble(2, book.getPrice());
            ps.setInt(3, book.getAuthor().getId());
            ps.setInt(4, id);
            if (ps.executeUpdate() == 0) throw new ResourceNotFoundException("");
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM books WHERE id=?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) throw new ResourceNotFoundException("");
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }

    public List<Book> findByAuthor(String name) {
        List<Book> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT b.id,b.name,b.price,a.id,a.name FROM books b JOIN authors a ON b.author_id=a.id WHERE a.name=?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Book(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getDouble(3),
                        new Author(rs.getInt(4), rs.getString(5))
                ));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
        return list;
    }
}

