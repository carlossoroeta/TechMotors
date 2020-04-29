import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class SurveyUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String idStr = req.getParameter("IDSurvey");
        SurveyData survey = new SurveyData(
                    Integer.parseInt(idStr),
					Integer.parseInt("IDCustomer"),
                    req.getParameter("Question1"),
					Integer.parseInt("Question2"),
					req.getParameter("Question3"),
                    req.getParameter("Question4")
                );
        int n = SurveyData.updateSurvey(connection, survey);
		
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        String IDSurvey = req.getParameter("id");

        toClient.println(Utils.header("Survey Of Application"));
		SurveyList.printsurveyTable(toClient, connection);
        toClient.println(Utils.footer());
        toClient.close();

        // res.sendRedirect("SurveyList");
        //res.sendRedirect("SurveyEdit?id=" + idStr + "&a=" + Math.random());
    }
}