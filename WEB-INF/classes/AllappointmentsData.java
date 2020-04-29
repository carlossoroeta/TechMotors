import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class AllappointmentsData {
    int IDAppointment;
	String Garage;
	Date Day;
	Time Hour;
	String Type;
	String Vehicle;

    AllappointmentsData (int IDAppointment, String Garage, Date Day, Time Hour, String Type, String Vehicle) {
        this.IDAppointment    = IDAppointment;
        this.Garage  = Garage;
		this.Day   = Day;
		this.Hour   = Hour;
		this.Type   = Type;
		this.Vehicle   = Vehicle;
		
    }
    public static Vector<AllappointmentsData> getPendantAppointments(Connection con, String username) {
		Vector<AllappointmentsData> Lista=new Vector<AllappointmentsData>();
		String sql="SELECT Appointments.IDAppointment,Garages.Location,Appointments.Day,Appointments.Hour, Appointments.Type, Appointments.Vehicle  FROM Appointments, Garages, Managers, Customers ";
		sql+= "WHERE Appointments.Situation=False AND Appointments.IDGarage=Managers.IDGarage AND Managers.IDGarage=Garages.IDGarage AND Appointments.IDCustomer=Customers.IDCustomer AND Customers.Username=?";
		System.out.println("Este es el sql:" + sql);
		
		try {
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,username);
			ResultSet resul=prep.executeQuery();
			
			while (resul.next()) {
				AllappointmentsData appointment=new AllappointmentsData(
				Integer.parseInt(resul.getString("IDAppointment")),
				resul.getString("Location"),
				resul.getDate("Day"),
				resul.getTime("Hour"),
				resul.getString("Type"),
				resul.getString("Vehicle")
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
	
	public static Vector<AllappointmentsData> getPastAppointments(Connection con, String username) {
		Vector<AllappointmentsData> Lista=new Vector<AllappointmentsData>();
		String sql="SELECT Appointments.IDAppointment,Garages.Location,Appointments.Day,Appointments.Hour, Appointments.Type, Appointments.Vehicle  FROM Appointments, Garages, Managers, Customers ";
		sql+= "WHERE Appointments.Situation=True AND Appointments.IDGarage=Managers.IDGarage AND Managers.IDGarage=Garages.IDGarage AND Appointments.IDCustomer=Customers.IDCustomer AND Customers.Username=?";
		System.out.println("Este es el sql:" + sql);
		
		try {
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,username);
			ResultSet resul=prep.executeQuery();
			
			while (resul.next()) {
				AllappointmentsData appointment=new AllappointmentsData(
				Integer.parseInt(resul.getString("IDAppointment")),
				resul.getString("Location"),
				resul.getDate("Day"),
				resul.getTime("Hour"),
				resul.getString("Type"),
				resul.getString("Vehicle")
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
	
	public static int deleteAppointment(Connection connection, int id) {
        String sql ="DELETE FROM Appointments "
            + " WHERE IDAppointment = ?";
        System.out.println("deleteAppointment: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtDelete= connection.prepareStatement(sql);
            stmtDelete.setInt(1,id);
            n = stmtDelete.executeUpdate();
            stmtDelete.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in deleteAppointment: " + sql + " Exception: " + e);
        }
        return n;
    }
	


}