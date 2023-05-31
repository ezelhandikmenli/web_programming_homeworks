package net.javaguides.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import net.javaguides.usermanagement.model.User;


public class UserDAO {
private String jdbcURL = "jdbc: mysql://localhost: 3306/demo?useSSL=false";
private String jdbcUsername = "root";
private String jdbcPassword = "root";
private static final String INSERT_USERS_SQL = "INSERT INTO users" + " (name, email, country) VALUES "
+" (?, ?, ?);";
private static final String SELECT_USER_BY_ID = "select id, name, email, country from users where id =?";
private static final String SELECT_ALL_USERS = "select * from users";
private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";
protected Connection getConnection () {
Connection connection = null;
try {
Class.forName("com.mysql.jdbc.Driver");
connection = DriverManager.getConnection (jdbcURL, jdbcUsername, jdbcPassword);
} catch (SOLOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (ClassNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
return connection;
}

public boolean updateUser (User user) throws SQLException {
boolean rowUpdated;
try (Connection connection = getConnection();
PreparedStatement statement = connection.prepareStatement (INSERT_USERS_SQL);) {
statement.setString(1, user.getName());
statement.setString(2, user.getEmail());
statement.setString(3, user.getCountry());
statement.setInt (4, user.getId());
rowUpdated = statement.executeUpdate() > 0;
}
return rowUpdated;
}
}