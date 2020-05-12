import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeOfAppointmentData {   
    String    model;
	
	TypeOfAppointmentData (String model) {
        this.model    = model;
		
	}
	
	public static Vector<TypeOfAppointmentData> getVehicleList(Connection connection, String username) {
        Vector<TypeOfAppointmentData> vec = new Vector<TypeOfAppointmentData>();
		String sql = "Select Model FROM Customers, Vehicles";   
		sql += " WHERE Customers.IDCustomer = Vehicles.IDCustomer AND Username=?";		
        System.out.println("getVehicleList: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                TypeOfAppointmentData vehicle = new TypeOfAppointmentData(
                    result.getString("Model")
                
                );
                vec.addElement(vehicle);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getVehicleList: " + sql + " Exception: " + e);
        }
        return vec;
    }
}		