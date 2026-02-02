package Repository;

import exception.DatabaseOperationException;
import exception.ResourceNotFoundException;
import model.Author;
import Repository.interfaces.CrudRepository;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository implements CrudRepository<Author> {

    @Override
    public void create(Author author) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("INSERT INTO authors(name) VALUES (?)")) {
            ps.setString(1, author.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }

    @Override
    public List<Author> getAll() {
        List<Author> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT id,name FROM authors");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Author(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
        return list;
    }

    @Override
    public Author getById(int id) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT id,name FROM authors WHERE id=?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) throw new ResourceNotFoundException("");
            return new Author(rs.getInt(1), rs.getString(2));
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }

    @Override
    public void update(int id, Author author) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("UPDATE authors SET name=? WHERE id=?")) {
            ps.setString(1, author.getName());
            ps.setInt(2, id);
            if (ps.executeUpdate() == 0) throw new ResourceNotFoundException("");
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("DELETE FROM authors WHERE id=?")) {
            ps.setInt(1, id);
            if (ps.executeUpdate() == 0) throw new ResourceNotFoundException("");
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }

    public boolean existsById(int id) {
        try (Connection c = DatabaseConnection.getConnection();
             PreparedStatement ps = c.prepareStatement("SELECT 1 FROM authors WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new DatabaseOperationException("", e);
        }
    }
}
