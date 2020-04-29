import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CustomerSurveyUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
		PrintWriter toClient = res.getWriter();
        String idStr = req.getParameter("IDSurvey");
		String idcustomer = req.getParameter("IDCustomer");
		String idqone = req.getParameter("Question1");
		String idqtwo = req.getParameter("Question2");
		String idqthree = req.getParameter("Question3");
		String idqfour = req.getParameter("Question4");
        SurveyData survey = new SurveyData(
                    Integer.parseInt(idStr),
					Integer.parseInt(idcustomer),
                    req.getParameter(idqone),
					Integer.parseInt(idqtwo),
					req.getParameter(idqthree),
                    req.getParameter(idqfour)
                );
        int n = SurveyData.updateSurvey(connection, survey);
		
		
		res.sendRedirect("CustomerSurveyList");
        toClient.close();

        
        
    }
}