import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.*;

@SuppressWarnings("serial")
public class MyVehiclesUpdate extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
		HttpSession session =req.getSession(false);
		String username=(String)session.getAttribute("login");
		String licencia = req.getParameter("LicensePlate");
		String BuyDate=req.getParameter("BuyDate");
		String OilDate=req.getParameter("OilDate");
		String ITVDate=req.getParameter("ITVDate");
		String FilterDate=req.getParameter("FilterDate");


                  
				


                
				//leemos la información q nos deja el usuario y le llamamos productId porque es como le hemos llamado en el productEdit a los inputs del form
        int n = MyVehiclesData.MyVehiclesUpdate(connection, licencia,OilDate,ITVDate,FilterDate);//es un method del ProductData

		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();



        
toClient.println(Utils.header(username));

        Vector <MyVehiclesData> MyVehicles;
		MyVehicles=MyVehiclesData.getMyVehicleList(connection,username);


toClient.println("<head><meta charset='UTF-8'><title >My Vehicles</title><link rel='stylesheet' type='text/css' href='css.css'><link rel='icon type='image/x-icon' href='LOGOE3E3E3.jpeg'></head>");
toClient.println("<body style='padding: 20px'><h1 class='title' style='font-size:2.4em'><STRONG>My Vehicles</STRONG></h1><div class='tabContainer' ><div class='buttonContainer'>");
		for(int i=0; i< MyVehicles.size(); i++){
                MyVehiclesData vehicle = MyVehicles.elementAt(i);
				int a=i+1;
toClient.println("<button  class='w3-button w3-light-grey w3-section' onclick=showPanel("+ i + ",'#e3e3e3')>Vehicle " + a + "</button>");
  

}
toClient.println("<button class='w3-button w3-light-grey w3-section' onclick=showPanel("+ MyVehicles.size() +",'#e3e3e3')>Add a new car</button>");
  toClient.println("</div>");

  	for(int i=0; i< MyVehicles.size(); i++){
		MyVehiclesData vehicle = MyVehicles.elementAt(i);
		
toClient.println("<div class='tabPanel' ><div id='seccion"+ i +"'>");

toClient.println("<br><U><STRONG>Model:</STRONG></U> "+ vehicle.Model + "<br><U><STRONG>Car Number:</STRONG></U> " + vehicle.LicensePlate + " <br><U><STRONG>Buy Date:</STRONG></U> " + vehicle.BuyDate + " <br><U><STRONG>Oil Date:</STRONG></U> " + vehicle.OilDate + " <br><U><STRONG>ITV Date:</STRONG></U> " + vehicle.ITVDate + " <br><U><STRONG>Filter Date:</STRONG></U> " + vehicle.FilterDate +"<br>");
toClient.println("<button  onclick=\"editar('seccion"+ i +"','"+ vehicle.Model + "','"+ vehicle.LicensePlate +"','"+ vehicle.OilDate + "','"+  vehicle.ITVDate +"','"+ vehicle.FilterDate+ "')\" class='w3-button w3-light-grey w3-section' border='1' type='submit'>Edit</button>&nbsp;<A HREF ='MyVehiclesDelete?id=" + vehicle.LicensePlate +"'><button class='w3-button w3-light-grey w3-section' border='1'>Delete</button></A>");
toClient.println("</div></div>");
	}
	
	
	toClient.println("<div class='tabPanel style='height: 524px''>");
	toClient.println("<form action='insertMyVehicle' method='GET'>");
	toClient.println("<table><tr><td></td><td><input NAME='Model' class='w3-input  w3-padding-small' style='width:300px' placeholder='Model*'  required></td></tr>");
	toClient.println("<tr><td></td><td ><input id='LicensePlate' onChange='comprobarLicensePlate()' NAME='LicensePlate' class='w3-input   w3-padding-small' style='width:300px' placeholder='Car Number*'  required></td><td ><div id='dondeaviso' style='color: #1C62C4;font-weight:bold'></div></td></tr>");
	toClient.println("<tr><td><center>Buy Date*:</center></td><td><input NAME='BuyDate' class='w3-input  w3-padding-small' style='width:300px' placeholder='BuyDate*' type='date' required></td></tr>");
	toClient.println("<tr><td><center>Oil Date*: </center></td><td><input NAME='OilDate' class='w3-input  w3-padding-small' style='width:300px' placeholder='OilDate*' type='date' required></td></tr>");
	toClient.println("<tr><td><center>ITV Date*: </center></td><td><input NAME='ITVDate' class='w3-input  w3-padding-small' style='width:300px ' placeholder='ITVDate*' type='date' required></td></tr>");
	toClient.println("<tr><td><center>Filter Date*: </center></td><td><input NAME='FilterDate' class='w3-input  w3-padding-small' style='width:300px' placeholder='FilterDate*' type='date' required></td></tr>");

	toClient.println("<tr><td></td><td><center><button class='w3-button w3-light-grey w3-section' type='submit'  >Add</button></center></td></tr></table>");
	toClient.println("</div>");
	toClient.println("<script src='myVehicles.js'></script><script src='edit.js'></script><script src='Licenseplate.js'></script></body>");

        toClient.println(Utils.footer());
        toClient.close();
    }
}