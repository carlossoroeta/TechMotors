import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class SurveyList extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        // String IDSurvey = req.getParameter("id");

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
		toClient.println("<th class='w3-grey'>&nbsp State &nbsp</th>");
		toClient.println("<th class='w3-grey'>&nbsp Mark &nbsp</th>");
		toClient.println("<th class='w3-grey'>&nbsp Came again &nbsp</th>");
		toClient.println("<th class='w3-grey'>&nbsp Suggestion &nbsp</th>");
        Vector<SurveyData> SurveyList;
        SurveyList = SurveyData.getSurveyList(connection);
		float sum=0;
		float mean=0;
        for(int i=0; i< SurveyList.size(); i++){
                SurveyData survey = SurveyList.elementAt(i);
                toClient.println("<tr>");
                toClient.println("<td>&nbsp" + survey.IDSurvey + "&nbsp </td>");
				toClient.println("<td>&nbsp" + survey.IDCustomer + "&nbsp </td>");
                toClient.println("<td>&nbsp" + survey.Question1 + "&nbsp </td>");
                toClient.println("<td>&nbsp" + survey.Question2 + "&nbsp </td>");
				toClient.println("<td>&nbsp" + survey.Question3 + "&nbsp </td>");
				toClient.println("<td>&nbsp" + survey.Question4 + "&nbsp </td>");
                // toClient.println("<td><a href='ProductList?id=" + survey.IDSurvey + "'>Products</a></td>");
                // toClient.println("<td><a href='surveyEdit?id=" + survey.IDSurvey + "'>Edit</a></td>");
                toClient.println("</tr>");
				sum=sum + survey.Question2;
        }
		toClient.println("</tr>");
		toClient.println("</thead>");
        toClient.println("</table>");
		toClient.println("<br>");
		toClient.println("<br>");
		toClient.println("<td><big> Mean of Reviews </big></td>");
		toClient.println("<br>");

		mean=sum/SurveyList.size();
		toClient.println(mean);
		
		toClient.println("</div>");
    }
	
}