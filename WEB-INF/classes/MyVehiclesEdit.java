import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class MyVehiclesEdit extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
		//connection with the database para eso está el metodo init using getconection method of connectionUtils class
        super.init(config);//we are using the first one because because there is a variable de entrada
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        //we are creating a table and in each servlet one doget only
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		HttpSession session =req.getSession(false);
		String username=(String)session.getAttribute("login");
        toClient.println(Utils.header(username));
        toClient.println("<form action='MyVehiclesUpdate' method='GET'>");//cuando pulsas el botonall the information modified will be send to the database to ProductUpdate
        toClient.println("<table border='1'>");
        String LicensePlate = req.getParameter("id");//the parameter allows you showing information about just this id

		MyVehiclesData vehicle = MyVehiclesData.getVehicle(connection, LicensePlate);//you get the values from the database
        toClient.println("<tr><td>Model</td>");
        toClient.println("<td><input name='Model' value='" + vehicle.Model + "'></td></tr>");
        toClient.println("<tr><td>LicensePlate</td>");
        toClient.println("<td><input name='LicensePlate' value='" + vehicle.LicensePlate + "'></td></tr>");
        toClient.println("<tr><td>Buy Date</td>");
        toClient.println("<td><input type='date' name='BuyDate' value='" + vehicle.BuyDate + "' placeholder='"+ vehicle.BuyDate +"'></td>");
		toClient.println("<tr><td>Oil Date</td>");
        toClient.println("<td><input type='date' name='OilDate' value='" + vehicle.OilDate + "'></td>");
		toClient.println("<tr><td>ITV Date</td>");
        toClient.println("<td><input type='date' name='ITVDate' value='" + vehicle.ITVDate + "'></td>");
		toClient.println("<tr><td>Filter Date</td>");
        toClient.println("<td><input type='date' name='FilterDate' value='" + vehicle.FilterDate + "'></td>");
        toClient.println("</table>");
        toClient.println("<input type='submit' value='Save'>");
        toClient.println("</form>");
        toClient.println(Utils.footer());
        toClient.close();
		//in ex 1 we are going to add a row to show quantity product per unit
    }
}