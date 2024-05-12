package tn.iit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tn.iit.printModels.RequestPrint;

/**
 * UserDAO.java This DAO class provides CRUD database operations for the table
 * users in the database.
 * 
 * @author [Your Name]
 *
 */

public class RequestDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/imprimante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String SELECT_ALL_REQUESTS_SQL = "SELECT * FROM print_requests";
	private static final String INSERT_REQUESTS_SQL = "INSERT INTO print_requests (subject_id,document_name,arrival_date,num_copies) VALUES (?, ?, ?, ?)";

	public RequestDAO() {
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

	public List<RequestPrint> selectAllRequests() {
		List<RequestPrint> requests = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_REQUESTS_SQL)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				
				int idSubject = rs.getInt("subject_id");
				String document = rs.getString("document_name");
				String date = rs.getString("arrival_date");
				int copies = rs.getInt("num_copies");

				requests.add(new RequestPrint( idSubject,document,date,copies));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}
	
	public void insertRequest(RequestPrint request) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUESTS_SQL)) {
            preparedStatement.setInt(1, request.getSubject_id());
            preparedStatement.setString(2, request.getDocument_name());
            preparedStatement.setString(3, request.getArrival_date());
            preparedStatement.setInt(4, request.getNum_copies());

            preparedStatement.executeUpdate();
        }
    }

}
