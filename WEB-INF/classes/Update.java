import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class Update extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {    
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {    
        res.setContentType("text/html");
        HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("login");		
		String type = req.getParameter("Appointment");
		String vehicle = req.getParameter("Vehicle");
		String Matricula = UpdateData.getMatricula(connection, vehicle);
		int IDCustomer = UpdateData.getIDCustomer(connection, username); 
		int price = 30;		
		String day = req.getParameter("Fecha");
		String hour = req.getParameter("Hour");
		String garage = req.getParameter("Garage");
		int IDGarage = UpdateData.getIDGarage(connection, garage); 
		int IDAppointment = UpdateData.getMax(connection);
		int IDDefinitiva = IDAppointment + 1;
		boolean Situation = false;
							
        int n = UpdateData.updateDatabase(connection, IDDefinitiva, type, Matricula, IDCustomer, Situation, price, day, hour, IDGarage);  
        res.sendRedirect("CustomerMenu");
    }
}