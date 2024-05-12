package tn.iit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tn.iit.printModels.Subject;

/**
 * UserDAO.java This DAO class provides CRUD database operations for the table
 * users in the database.
 * 
 * @author [Your Name]
 *
 */

public class SubjectDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/imprimante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String SELECT_ALL_SUBJECTS_SQL = "SELECT * FROM subjects";

	public SubjectDAO() {
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

	public List<Subject> selectAllSubjects() {
		List<Subject> sujects = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_SUBJECTS_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("subject_id");
				String name = rs.getString("subject_name");

				sujects.add(new Subject(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sujects;
	}

}
