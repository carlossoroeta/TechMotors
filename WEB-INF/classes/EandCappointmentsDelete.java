import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.*;

@SuppressWarnings("serial")
public class EandCappointmentsDelete extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));
		String garage= req.getParameter("Garage");
		IDGarageData garageInfo=IDGarageData.getid(connection, garage);
		int garaID= garageInfo.IDGarage;
		String day=req.getParameter("Day");
		String hour=req.getParameter("Hour");
		
		int x = piezasXappointmentsData.deleteInventory(connection,garaID,day,hour);
        int n = AllappointmentsData.deleteAppointment(connection,id);
		
		////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("login");
		String userType= req.getParameter("userType");
        toClient.println(Utils.header(username)); 
		Boolean pendant= false; 
	
		Vector<EandCAppointmentsData> ListaPendientes=EandCAppointmentsData.getAppointments(connection, username, userType, pendant);
		int numeroPendientes=ListaPendientes.size();
		if (numeroPendientes==0) {
			toClient.println("<fieldset class='w3-fieldset' >");
			toClient.println("<legend align='center' style='font-size:1.4em'>Pendant appointments</legend>");
			toClient.println("<CENTER><font size='7' style='color:#e3e3e3'>EMPTY</font></CENTER>");
			toClient.println("</fieldset>");
		}
		else {
		toClient.println("<fieldset class='w3-fieldset'  >");
		toClient.println("<P><legen align='center' style='font-size:1.4em'd>Pendant Appointments</legend></P>");
							
			for (int i=0;i<ListaPendientes.size();i++) {
				EandCAppointmentsData appointment=ListaPendientes.elementAt(i);
				int IDGarage=appointment.IDGarage;
				toClient.println("<TABLE class='w3-table w3-center'><tr><td>");
				toClient.println("<form name='formulario" + appointment.IDAppointment + "' action='EandCappointmentsDelete' method='GET'>");
				toClient.println("<p style='margin-left:100px'>ID: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><input NAME='id' value=" + appointment.IDAppointment + " disabled><input Type='hidden' NAME='id' value=" + appointment.IDAppointment + "></br></p>");
				toClient.println("<p style='margin-left:100px'>Garage:&nbsp; <br><input NAME='garage' value=" + appointment.Garage + " disabled><input Type='hidden' NAME='Garage' value=" + appointment.Garage + "></br></p>");
				toClient.println("<p style='margin-left:100px'>Day:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br><input NAME='Day' value=" + appointment.Day + " disabled><input Type='hidden' NAME='Day' value=" + appointment.Day + "></br></p>");
				toClient.println("<p style='margin-left:100px'>Hour:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br><input NAME='Hour' value=" + appointment.Hour + " disabled><input Type='hidden' NAME='Hour' value=" + appointment.Hour + "></br></p>");
				toClient.println("<p style='margin-left:100px'>Vehicle: &nbsp;&nbsp; <br><input NAME='vehicle' value=" + appointment.Vehicle + " disabled></br></p>");
				toClient.println("<input type='hidden' name='userType' value='" + userType + "'>");
				toClient.println("<center><button class='w3-button w3-light-grey w3-section ' type='submit'>DELETE</button></center></td></form>");
				toClient.println("<td><div class='w3-container w3-padding-64 w3-right' id='contact'>");
				java.sql.Date Day= appointment.Day;
				java.sql.Time Hora=appointment.Hour;
				Vector<piezasXappointmentsData> ListaPiezasPendientes=piezasXappointmentsData.getPiezas(connection, Day, Hora, IDGarage);
				toClient.println("<table style ='margin-right:300px' class='w3-table' style='border-collapse:separate;margin:10px;width:1450px' border='1' >");
				toClient.println("<tr class='w3-grey'><td><b>Category</b></td><td><b>Reference</b></td><td><b>Quantity</b></td></tr>");
				for (int j=0;j<ListaPiezasPendientes.size();j++) {
				piezasXappointmentsData piezas=ListaPiezasPendientes.elementAt(j);
				toClient.println("<TR>");
				toClient.println("<TD>" + piezas.Category + "</TD>");
				toClient.println("<TD>" + piezas.Reference + "</TD>");
				toClient.println("<TD>" + piezas.Quantity + "</TD>");
				}
				toClient.println("</TR></TABLE></div>");
				//Ahora hay que meter las piezas que pertenecen a cada cita
				toClient.println("</td></tr></TABLE></fieldset>");
		}
	toClient.println("</fieldset>");
	}
////////////////////////////////////////////
	Vector<EandCAppointmentsData> ListaPast=EandCAppointmentsData.getAppointments(connection, username, userType, true);
		int numeroPast=ListaPast.size();
		if (numeroPast==0) {
			toClient.println("<fieldset class='w3-fieldset' >");
			toClient.println("<legend align='center' style='font-size:1.4em'>Past appointments</legend>");
			toClient.println("<CENTER><font size='7' style='color:#e3e3e3'>EMPTY</font></CENTER>");
			toClient.println("</fieldset>");
		}
		else {
		toClient.println("<fieldset class='w3-fieldset'  >");
		toClient.println("<P><legen align='center' style='font-size:1.4em'd>Past Appointments</legend></P>");
							
			for (int y=0;y<ListaPast.size();y++) {
				EandCAppointmentsData PastAppointment=ListaPast.elementAt(y);
				int IDGarage=PastAppointment.IDGarage;
				toClient.println("<TABLE class='w3-table w3-center'><tr><td>");
				toClient.println("<div class='w3-container w3-padding-64 w3-left'  id='contact'>");
				toClient.println("<p style='margin-left:100px'>ID: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br><input NAME='nombre' value=" + PastAppointment.IDAppointment + " disabled><input NAME='id' type='hidden' value=" + PastAppointment.Hour + " ></br></p>");
				toClient.println("<p style='margin-left:100px'>Garage:&nbsp; <br><input NAME='garage' value=" + PastAppointment.Garage + " disabled><input NAME='garage' type='hidden' value=" + PastAppointment.Garage + " ></br></p>");
				toClient.println("<p style='margin-left:100px'>Day:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br><input NAME='Day' value=" + PastAppointment.Day + " disabled><input NAME='Day' type='hidden' value=" + PastAppointment.Day + " ></br></p>");
				toClient.println("<p style='margin-left:100px'>Hour:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <br><input NAME='Hour' value=" + PastAppointment.Hour + " disabled><input NAME='Hour' type='hidden' value=" + PastAppointment.Hour + " ></br></p>");
				toClient.println("<p style='margin-left:100px'>Vehicle: &nbsp;&nbsp; <br>" + PastAppointment.Vehicle + "</br></p>");
				toClient.println("</div>");
				toClient.println("<td><div class='w3-container w3-padding-64 w3-right' id='contact'>");
				java.sql.Date Day= PastAppointment.Day;
				java.sql.Time Hora=PastAppointment.Hour;
				Vector<piezasXappointmentsData> ListaPiezasPasadas=piezasXappointmentsData.getPiezas(connection, Day, Hora, IDGarage);
				toClient.println("<table style ='margin-right:300px' class='w3-table' style='border-collapse:separate;margin:10px;width:1450px' border='1' >");
				toClient.println("<tr class='w3-grey'><td><b>Category</b></td><td><b>Reference</b></td><td><b>Quantity</b></td></tr>");
				for (int z=0;z<ListaPiezasPasadas.size();z++) {
				piezasXappointmentsData piezas=ListaPiezasPasadas.elementAt(z);
				toClient.println("<TR>");
				toClient.println("<TD>" + piezas.Category + "</TD>");
				toClient.println("<TD>" + piezas.Reference + "</TD>");
				toClient.println("<TD>" + piezas.Quantity + "</TD>");
				}
				toClient.println("</TR></TABLE>");
				//Ahora hay que meter las piezas que pertenecen a cada cita
				toClient.println("</td></tr></TABLE></fieldset>");
		}
	toClient.println("</fieldset>");
	}


	toClient.println(Utils.footer());
	toClient.close();
	}
}
		