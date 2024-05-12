package tn.iit.printControllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uemail = request.getParameter("username");
        String upwd = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher = null;
        Connection con = null;
        try {
            // **Assuming you're using MySQL Connector/J (replace if necessary)**
            Class.forName("com.mysql.jdbc.Driver"); // Update class name based on your driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imprimante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
            PreparedStatement pst = con.prepareStatement("select * from users where uemail = ? and upwd= ?");
            pst.setString(1, uemail);
            pst.setString(2, upwd);
            ResultSet rs = pst.executeQuery();
            
            if (rs.next()) {
            	String role = rs.getString("role");
            	if( role.equals("Teacher") ) {
            		dispatcher = request.getRequestDispatcher("printRequest");
            		request.setAttribute("get", "1");
            	}else if (role.equals("Admin") ) {
            		dispatcher = request.getRequestDispatcher("manageUser");
            	}else {
            		dispatcher = request.getRequestDispatcher("manageUser");
            	}
                session.setAttribute("name", rs.getString("uname"));
            } else {
                // If login fails, redirect back to the login page with an error message
                dispatcher = request.getRequestDispatcher("login.jsp");
                request.setAttribute("loginError", "Invalid email or password");
            }
            dispatcher.forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Redirect back to login page with error message
            dispatcher = request.getRequestDispatcher("login.jsp");
            request.setAttribute("loginError", "An error occurred while processing your request. Please try again later.");
            dispatcher.forward(request, response);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
