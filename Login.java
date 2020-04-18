import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class Login extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		toClient.println("<html><HEAD><link rel='stylesheet' href='w3.css'></HEAD><body>");
		toClient.println("<P style='margin-top:40px;margin-left:10px;font-size:1.6em'><a href='index.html'><IMG SRC='atrasbien_opt.png' class='w3-bar-item' ></a> &nbsp &nbsp <STRONG>HOME</STRONG></P>");
		toClient.println("<div class='w3-container w3-padding-64' ><h1>LOG IN</h1>");
		toClient.println("<form id='formulario' action='CheckLogin'>");
		toClient.println("<p type='User:'><input class='w3-input w3-padding-16' type='text' placeholder='User' required='' name='User'></p>");
		toClient.println("<p type='Password:'><input class='w3-input w3-padding-16' type='text' placeholder='Password' required='' name='Password'></p>");
		toClient.println("User type:<select class='w3-select' name='type' id='type'><option value='Customers'>Customer</option><option value=''>Garage manager</option></select>");
		toClient.println("<p><A href=''>Have you forgotten your password?</A></p>");
		toClient.println("<p><button class='w3-button w3-light-grey w3-section' type='submit'>ENTER</button></p>");
		toClient.println("</form></div>");
	    toClient.println(Utils.footer());
        toClient.close();
    }
}