import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class CustomerSurveyList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String IDSurvey = req.getParameter("id");

        toClient.println(Utils.header("Survey Of Application"));
		toClient.println("<br>");
		toClient.println("<br>");
        printsurveyTable(toClient, connection);
        toClient.println(Utils.footer());
        toClient.close();
    }

    public static void printsurveyTable(PrintWriter toClient, Connection connection) {
        
		toClient.println("<div style='text-align:center'>");
		toClient.println("<tittle><big> List of Surveys from Customers </big><tittle>");
		toClient.println("<br><br>");
		toClient.println("<table border='1' style='margin: 0 auto'>");
		toClient.println("<thead>");
		toClient.println("<tr>");
		toClient.println("<th class='w3-grey'>&nbsp Survey &nbsp</th>");
		toClient.println("<th class='w3-grey'>&nbsp Id Customer &nbsp</th>");
		
        Vector<SurveyData> SurveyList;
        SurveyList = SurveyData.getSurveyList(connection);
		
        for(int i=0; i< SurveyList.size(); i++){
                SurveyData survey = SurveyList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>&nbsp Perform Survey of &nbsp </td>");
				toClient.println("<td>&nbsp" + survey.IDCustomer + "&nbsp </td>");
              
                toClient.println("<td><a href='CustomerSurveyEdit?id=" + survey.IDSurvey + "'>Edit</a></td>");
                toClient.println("</tr>");
			
        }
		toClient.println("</tr>");
		toClient.println("</thead>");
        toClient.println("</table>");
		
		
		toClient.println("</div>");
    }
	
}