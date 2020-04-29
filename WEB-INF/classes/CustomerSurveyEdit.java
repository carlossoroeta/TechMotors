import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CustomerSurveyEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println(Utils.header(" Survey Realization "));
        int idStr = Integer.parseInt(req.getParameter("id"));
        SurveyData survey = SurveyData.getSurvey(connection, idStr);
        toClient.println("<form action='CustomerSurveyUpdate' method='GET'>");
        toClient.println("<input name='IDSurvey' type='hidden' value='" + survey.IDSurvey + "'></input><br><br>");
        
        

        
        toClient.println("<td><input name='IDCustomer' value='" + survey.IDCustomer + "'></td></tr>");
        toClient.println("<br><tr><td> What is your general opinion about the workshop?</td><br><br>");
        toClient.println("<td><input name='Question1' value='" + survey.Question1 + "'></td></tr><br><br><br>");
        toClient.println("<br><tr><td> What note from 1 to 10 would you give to the workshop?</td><br><br>");
		toClient.println("<td><input name='Question2' value='" + survey.Question2 + "'></td></tr><br><br><br>");
        toClient.println("<br><tr><td> Whould you come to the workshop again?</td><br><br>");
		toClient.println("<td><input name='Question3' value='" + survey.Question3 + "'></td></tr><br><br><br>");
        toClient.println("<br><tr><td> What is your improvement proposal for the workshop?</td><br><br>");
		toClient.println("<td><input name='Question4' value='" + survey.Question4 + "'></td></tr><br><br>");
        
        toClient.println("<input type='submit' value='enviar'>");
        toClient.println("</form>");
		
        toClient.println(Utils.footer());
		
		
        toClient.close();
    }
}