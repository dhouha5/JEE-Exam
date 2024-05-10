package tn.iit.print;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/printRequest")
public class PrintRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String subjectId = request.getParameter("subjectId");
        String documentName = request.getParameter("documentName");
        String arrivalDate = request.getParameter("arrivalDate");
        int numCopies = Integer.parseInt(request.getParameter("numCopies"));
        
        // Assuming the username is stored in the session attribute "username"
        String username = (String) session.getAttribute("username");
        
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imprimante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
            PreparedStatement pst = con.prepareStatement("INSERT INTO print_requests (subject_id, document_name, arrival_date, num_copies) VALUES (?, ?, ?, ?)");
            pst.setString(1, subjectId);
            pst.setString(2, documentName);
            pst.setString(3, arrivalDate);
            pst.setInt(4, numCopies);
            pst.executeUpdate();
            
            // Redirect to a success page
            response.sendRedirect("success.jsp");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Redirect back to the print request page with error message
            response.sendRedirect("print-request.jsp?error=1");
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
