import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class ShowHoursData {   
    Time    hour;
	
	ShowHoursData (Time hour) {
        this.hour    = hour;
		
	}
	
	public static Vector<ShowHoursData> getHourList(Connection connection, String fecha, String Garage) {
        Vector<ShowHoursData> vec = new Vector<ShowHoursData>();
		String sql = "Select Hour FROM Appointments, Managers, Garages";   
		sql += " WHERE Appointments.IDGarage = Managers.IDGarage AND Managers.IDGarage = Garages.IDGarage AND Day=? AND Location=?";		
        System.out.println("getHourList: " + sql);
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,fecha);
			pstmt.setString(2,Garage);
            ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                ShowHoursData hora = new ShowHoursData(
                    result.getTime("Hour")
                
                );
                vec.addElement(hora);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getHourList: " + sql + " Exception: " + e);
        }
        return vec;
    }
}		