package tn.iit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.iit.printModels.User;

/**
 * UserDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author [Your Name]
 *
 */

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/imprimante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    private static final String INSERT_USER_SQL = "INSERT INTO users (uname, upwd, uemail, umobile, role) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID_SQL = "SELECT * FROM users WHERE id=?";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET uname=?, upwd=?, uemail=?, umobile=?, role=? WHERE id=?";

    public UserDAO() {
    }

    protected Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getUname());
            preparedStatement.setString(2, user.getUpwd());
            preparedStatement.setString(3, user.getUemail());
            preparedStatement.setString(4, user.getUmobile());
            preparedStatement.setString(5, user.getRole());
            preparedStatement.executeUpdate();
        }
    }

    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID_SQL)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String uname = rs.getString("uname");
                String upwd = rs.getString("upwd");
                String uemail = rs.getString("uemail");
                String umobile = rs.getString("umobile");
                String role = rs.getString("role");
                user = new User(id, uname, upwd, uemail, umobile, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String uname = rs.getString("uname");
                String upwd = rs.getString("upwd");
                String uemail = rs.getString("uemail");
                String umobile = rs.getString("umobile");
                String role = rs.getString("role");
                users.add(new User(id, uname, upwd, uemail, umobile, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getUname());
            statement.setString(2, user.getUpwd());
            statement.setString(3, user.getUemail());
            statement.setString(4, user.getUmobile());
            statement.setString(5, user.getRole());
            statement.setInt(6, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
