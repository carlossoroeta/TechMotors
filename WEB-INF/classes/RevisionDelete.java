import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.*;

@SuppressWarnings("serial")
public class RevisionDelete extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        int id = Integer.parseInt(req.getParameter("id"));
        int n = AllappointmentsData.deleteAppointment(connection,id);
    /////////////////////////////////////////////////
		res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("login");
        toClient.println(Utils.header(username));
		String userType= req.getParameter("Query");
		toClient.println("<div class='w3-center' style=´'width:1400px;margin:auto'");
			toClient.println("<P><h1 class='w3-xxlarge w3-center'>Pendant appointments</h1></P>");
			Vector<EandCAppointmentsData> citasPendientes=EandCAppointmentsData.getAppointments(connection, username, userType, false );
			
			int nPendientes=citasPendientes.size();
			if (nPendientes==0) {
				toClient.println("<CENTER><font size='7' style='color:#e3e3e3' >EMPTY</font></CENTER>");
			}
			else {
			toClient.println("<TABLE class='w3-table w3-center' style='border-collapse:separate;margin:10px;padding:10px' BORDER=1>");
			toClient.println("<TR class='w3-grey'><TD>ID</TD><TD>Garage</TD><TD>Date</TD><TD>Hour</TD><TD>Type</TD><TD>Vehicle</TD><TD>Edit</TD></TR>");
				
				for (int i=0;i<citasPendientes.size();i++) {
					EandCAppointmentsData citaPendiente=citasPendientes.elementAt(i);
					toClient.println("<TR>");
					toClient.println("<TD>" + citaPendiente.IDAppointment + "</TD>");
					toClient.println("<TD>" + citaPendiente.Garage + "</TD>");
					toClient.println("<TD>" + citaPendiente.Day + "</TD>");
					toClient.println("<TD>" + citaPendiente.Hour + "</TD>");
					toClient.println("<TD>" + citaPendiente.Type + "</TD>");
					toClient.println("<TD>" + citaPendiente.Vehicle + "</TD>");
					toClient.println("<TD><A HREF=RevisionDelete?id=" + citaPendiente.IDAppointment + "&Query=" + userType + ">Delete</A></TD>");
					toClient.println("</TR>");
				}
			}
			toClient.println("</TABLE>");
			toClient.println("</div>");
			
			toClient.println("<div class='w3-center' style=´'width:1400px;margin:auto'");
			toClient.println("<P><h1 class='w3-xxlarge w3-center'>Past appointments</h1></P>");
			Vector<EandCAppointmentsData> citasPasadas=EandCAppointmentsData.getAppointments(connection, username, userType, true );
			toClient.println("<TABLE class='w3-table w3-center' style='border-collapse:separate;margin:10px;padding:10px' BORDER=1>");
			toClient.println("<TR class='w3-grey'><TD>ID</TD><TD>Garage</TD><TD>Date</TD><TD>Hour</TD><TD>Type</TD><TD>Vehicle</TD></TR>");
			int nPasadas=citasPasadas.size();
			if (nPasadas==0) {
				toClient.println("<CENTER><font size='7' style='color:#e3e3e3' >EMPTY</font></CENTER>");
			}
			else {
				
				for (int i=0;i<citasPasadas.size();i++) {
					EandCAppointmentsData citaPasada=citasPasadas.elementAt(i);
					toClient.println("<TR>");
					toClient.println("<TD>" + citaPasada.IDAppointment + "</TD>");
					toClient.println("<TD>" + citaPasada.Garage + "</TD>");
					toClient.println("<TD>" + citaPasada.Day + "</TD>");
					toClient.println("<TD>" + citaPasada.Hour + "</TD>");
					toClient.println("<TD>" + citaPasada.Type + "</TD>");
					toClient.println("<TD>" + citaPasada.Vehicle + "</TD>");
					toClient.println("</TR>");
				}
			}
			toClient.println("</TABLE>");
			toClient.println("</div>");
			toClient.println("<center><A href='TypeofAppointment'><button>Take a new appointment</button></A></center>");
		
				
			
        toClient.println(Utils.footer());
        toClient.close();
    }
}
