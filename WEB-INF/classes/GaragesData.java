import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;


public class GaragesData {
	int    IDGarage;
    String    Location;
    String Manager;
    Time    OpeningTime;
    Time ClosingTime;

	
    //Tipos de GaragesDatas
    GaragesData (int    IDGarage, String    Location, String Manager, Time    OpeningTime, Time ClosingTime) {
        this.IDGarage    = IDGarage;
        this.Location  = Location;
        this.Manager   = Manager;
        this.OpeningTime = OpeningTime;
        this.ClosingTime = ClosingTime;
    }
	
	public static Vector<GaragesData> getGarageList(Connection connection) {
        Vector<GaragesData> vec = new Vector<GaragesData>();
        String sql = "Select IDGarage, Location, Manager, OpeningTime, ClosingTime FROM Garages";
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                GaragesData garage = new GaragesData(
                    Integer.parseInt(result.getString("IDGarage")),
                    result.getString("Location"),
                    result.getString("Manager"),
                    result.getTime("OpeningTime"),
					result.getTime("ClosingTime")
                );
                vec.addElement(garage);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getGarageList: " + sql + " Exception: " + e);
        }
        return vec;
    }
}	
	
	
	
	