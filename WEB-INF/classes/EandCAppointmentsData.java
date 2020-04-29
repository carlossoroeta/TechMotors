import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class EandCAppointmentsData {
	
    int IDAppointment;
	String Garage;
	java.sql.Date Day;
	java.sql.Time Hour;
	String Type;
	String Vehicle;
	int IDGarage;

    EandCAppointmentsData (int IDAppointment, String Garage, java.sql.Date Day, java.sql.Time Hour, String Type, String Vehicle, int IDGarage) {
        this.IDAppointment    = IDAppointment;
        this.Garage  = Garage;
		this.Day   = Day;
		this.Hour   = Hour;
		this.Type   = Type;
		this.Vehicle   = Vehicle;
		this.IDGarage   = IDGarage;
		
    }
    public static Vector<EandCAppointmentsData> getAppointments(Connection con, String username, String userType, Boolean situation) {
		Vector<EandCAppointmentsData> Lista=new Vector<EandCAppointmentsData>();
		String sql="SELECT Appointments.IDAppointment,Garages.Location,Appointments.Day,Appointments.Hour, Appointments.Type, Appointments.Vehicle, Garages.IDGarage  FROM Appointments, Garages, Managers, Customers ";
		sql+= "WHERE  Appointments.IDGarage=Managers.IDGarage AND Managers.IDGarage=Garages.IDGarage AND Appointments.IDCustomer=Customers.IDCustomer AND Customers.Username=? AND Appointments.Type=? AND Appointments.Situation=?";
		System.out.println("Este es el sql:" + sql);
		
		try {
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,username);
			prep.setString(2,userType);
			prep.setBoolean(3,situation);
			ResultSet resul=prep.executeQuery();
			
			while (resul.next()) {
				EandCAppointmentsData appointment=new EandCAppointmentsData(
				Integer.parseInt(resul.getString("IDAppointment")),
				resul.getString("Location"),
				resul.getDate("Day"),
				resul.getTime("Hour"),
				resul.getString("Type"),
				resul.getString("Vehicle"),
				Integer.parseInt(resul.getString("IDGarage"))
				);
				Lista.addElement(appointment);
			}
			resul.close();
			prep.close();
		
		} catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProductList: " + sql + " Exception: " + e);
        }
        return Lista;
	}
	
	

}