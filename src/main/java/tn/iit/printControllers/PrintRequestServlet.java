package tn.iit.printControllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tn.iit.dao.RequestDAO;
import tn.iit.dao.SubjectDAO;
import tn.iit.printModels.RequestPrint;
import tn.iit.printModels.Subject;

@WebServlet("/printRequest")
public class PrintRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SubjectDAO subjectDAO = new SubjectDAO();
	RequestDAO requestDAO = new RequestDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		List<Subject> listSubject = subjectDAO.selectAllSubjects();
		request.setAttribute("listSubject", listSubject);
		RequestDispatcher dispatcher = request.getRequestDispatcher("printRequest.jsp");
		dispatcher.forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String getParam = (String) request.getAttribute("get");
		if (getParam != null && !getParam.isEmpty()) {
		    // If the "get" parameter is null or empty, invoke doGet
		    doGet(request, response);
		}
    	//HttpSession session = request.getSession();
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        String documentName = request.getParameter("documentName");
        String arrivalDate = request.getParameter("arrivalDate");
        int numCopies = Integer.parseInt(request.getParameter("numCopies"));
        
        // Assuming the username is stored in the session attribute "username"
        //String username = (String) session.getAttribute("uname");
        
        // Connection con = null;
        try {
//            Class.forName("com.mysql.jdbc.Driver"); 
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/imprimante?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");
//            PreparedStatement pst = con.prepareStatement("INSERT INTO print_requests (subject_id, document_name, arrival_date, num_copies) VALUES (?, ?, ?, ?)");
//            pst.setString(1, subjectId);
//            pst.setString(2, documentName);
//            pst.setString(3, arrivalDate);
//            pst.setInt(4, numCopies);
//            pst.executeUpdate();
            requestDAO.insertRequest(new RequestPrint(subjectId, documentName, arrivalDate, numCopies));
            // Redirect to a success page
            response.sendRedirect("printRequest.jsp");
        } catch ( SQLException e) {
            e.printStackTrace();
            // Redirect back to the print request page with error message
            response.sendRedirect("print-request.jsp?error=1");
        } finally {
            
        }
        
	}
}
