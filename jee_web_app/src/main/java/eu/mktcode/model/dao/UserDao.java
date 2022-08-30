package eu.mktcode.model.dao;

import eu.mktcode.app.datasource.database.ConnectionPool;
import eu.mktcode.app.datasource.database.PooledConnection;
import eu.mktcode.model.entities.User;
import eu.mktcode.model.entities.enums.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {


    private static User getUserFromRs(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(Dao.first));
        user.setRole(Role.valueOf(rs.getString(Dao.second).toUpperCase()));
        user.setEmail(rs.getString(Dao.third));
        user.setPassword(rs.getString(Dao.fourth));
        user.setCreateTime(rs.getTimestamp(Dao.fifth).toLocalDateTime());
        user.setLanguage(rs.getInt(Dao.sixth));
        return user;
    }

    @Override
    public Optional<User> get(int id) {
        String query = "SELECT id, role, email, password, create_time, language FROM user WHERE id = ?";

        try (PooledConnection con = ConnectionPool.get().getPooledConnection();
             PreparedStatement statement = con.getConnection().prepareStatement(query)) {

            statement.setInt(Dao.first, id);

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(getUserFromRs(rs));
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO logging
            return Optional.empty();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, role, email, password, create_time, language FROM user ORDER BY id asc";

        try (PooledConnection con = ConnectionPool.get().getPooledConnection();
             PreparedStatement statement = con.getConnection().prepareStatement(query)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(getUserFromRs(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //TODO logging
        }
        return users;
    }

    @Override
    public void save(User user) {
        String query = "INSERT INTO user \n" +
                "(role,email,password,language)\n" +
                "VALUES (?,?,?,?)";

        try (PooledConnection con = ConnectionPool.get().getPooledConnection();
             PreparedStatement statement = con.getConnection().prepareStatement(query,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(Dao.first, user.getRole().getOrdinal());
            statement.setString(Dao.second, user.getEmail());
            statement.setString(Dao.third, user.getPassword());
            statement.setInt(Dao.fourth, user.getLanguage());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(Dao.first));
                //TODO get insert time?
            } else {
                //TODO logging and action
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //TODO logging
        }
    }

    @Override
    public void update(User user) {
        String query = "UPDATE user SET " +
                "role = ?," +
                "email = ?," +
                "password = ?," +
                "language = ? " +
                "WHERE id = ?";

        try (PooledConnection con = ConnectionPool.get().getPooledConnection();
             PreparedStatement statement = con.getConnection().prepareStatement(query)) {

            statement.setInt(Dao.first, user.getRole().getOrdinal());
            statement.setString(Dao.second, user.getEmail());
            statement.setString(Dao.third, user.getPassword());
            statement.setInt(Dao.fourth, user.getLanguage());
            statement.setInt(Dao.fifth, user.getId());

            statement.executeUpdate();
            if (statement.getUpdateCount() > 0) {
                //TODO all good
            } else {
                //TODO logging fail
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //TODO logging
        }
    }

    @Override
    public void delete(User user) {
        String query = "DELETE user " +
                "WHERE id = ?";

        try (PooledConnection con = ConnectionPool.get().getPooledConnection();
             PreparedStatement statement = con.getConnection().prepareStatement(query)) {

            statement.setInt(Dao.first, user.getId());

            statement.executeUpdate();
            if (statement.getUpdateCount() > 0) {
                //TODO all good
            } else {
                //TODO logging fail
            }

        } catch (SQLException e) {
            e.printStackTrace();
            //TODO logging
        }
    }
}
