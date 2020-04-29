import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class AccountManager extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("login");
        toClient.println(Utils.managerheader(username)); //managerheader
		ManagerData manager = ManagerData.getManagerDetail(connection, username);
		toClient.println("<div class='w3-center' style=´'width:1400px;margin:auto'");
		toClient.println("<p><IMG SRC='ManagerID" + manager.IDManager + ".png' class='w3-bar-item' style='margin-top:40px' style='width:50px'></p>");
		toClient.println("<P><h1 class='w3-xxlarge w3-center'>Hello " + manager.Name +  "</h1></P>");
		toClient.println("<br><button class='w3-button w3-light-grey w3-section' type='submit' onclick='personalInfo()'>Personal information</button><button class='w3-button w3-light-grey w3-section' type='submit' onclick='accountInfo()'>Account information</button>");
		toClient.println("<SCRIPT SRC='personalInfo.js'></SCRIPT>");
		toClient.println("<SCRIPT SRC='accountInfo.js'></SCRIPT>");
		toClient.println("<div class='w3-center' id='Table1'>");
		toClient.println("</div>");
		
        toClient.println(Utils.footer());
        toClient.close();
    }
}