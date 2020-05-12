import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")

public class elegirCita extends HttpServlet {
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
		String DiaRecibido = req.getParameter("id");
		int id=Integer.parseInt(DiaRecibido);
		String MesRecibido = req.getParameter("id2");
		int id2=Integer.parseInt(MesRecibido);
		String AnnoRecibido = req.getParameter("id3");
		int id3=Integer.parseInt(AnnoRecibido);
		String fecha="";
		if (id<10 && id2<10) {
			fecha= "0" + id + "/0" + id2 + "/" + id3;
		} else if (id<10 && id2>10) {
			fecha= "0" + id + "/" + id2 + "/" + id3;
		} else if (id>10 && id2<10) {
			fecha= id + "/0" + id2 + "/" + id3;
		} else {
			fecha= id + "/" + id2 + "/" + id3;
		}
		String Appointment = req.getParameter("id4");
		String Vehicle = req.getParameter("id5");
		String Garage = req.getParameter("id6");
        toClient.println(Utils.header(username));
		toClient.println("<body><center><h1>AVAILABLE HOURS</h1></center><br/><br/>");
		if (Appointment.equals("Repair")) {
			toClient.println("<form action='Repair' method='GET'><br>");
		} else if (Appointment.equals("Exchange")) {
			toClient.println("<form action='Exchange' method='GET'><br>");
		} else {
			toClient.println("<form action='Customization' method='GET'><br>");
		}
		toClient.println("<input type='hidden' id='fecha' name='Fecha' value=" + fecha + ">");
		toClient.println("<input type='hidden' id='appointment' name='Appointment' value=" + Appointment + ">");
		toClient.println("<input type='hidden' id='vehicle' name='Vehicle' value=" + Vehicle + ">");
		toClient.println("<input type='hidden' id='garage' name='Garage' value=" + Garage + ">");
        toClient.println("<p><b>" + username + "</b>, the appointment day you have chosen is the " + id + "th of the " + id2 + "th month</p>");
		toClient.println("<br>Please, now select the hour for your appointment:</br>");
		toClient.println("<table class='w3-table'>");	
		Vector<ShowHoursData> HourListDatabase;
		HourListDatabase = ShowHoursData.getHourList(connection, fecha, Garage);
			
		String[] HorasTrabajoTaller = {"9:00:00","10:00:00","11:00:00","12:00:00","13:00:00","14:00:00","15:00:00","16:00:00","17:00:00"};
		int count=0;
		for(int i=0; i< HorasTrabajoTaller.length; i++){
			count=0;
			String hora1 = HorasTrabajoTaller[i];
			for(int j=0; j< HourListDatabase.size(); j++){
				ShowHoursData hora2 = HourListDatabase.elementAt(j);
				String hora = "" + hora2.hour + "";
				if (hora1.equals(hora)) {
					count=count+1;
				}
			}
			if (count==0) {
				toClient.println("<tr>");
                toClient.println("<td><input style='margin-left:50px' type='radio' name='Hour' value=" + hora1 + ">" + hora1 + "</td>");
                toClient.println("</tr>");
			}
		}
		
		toClient.println("</table>");
		toClient.println("<center><button type='submit'>NEXT</button></center></form>");
		toClient.println(Utils.footer());
        toClient.close();
		
		

				
                
    }
}