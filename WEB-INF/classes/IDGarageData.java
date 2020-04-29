import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IDGarageData {
	int IDGarage;
    String    Location;

    
    IDGarageData (int IDGarage, String    Location) {
        this.IDGarage    = IDGarage;
		this.Location    = Location;
 
    }
	
	public static IDGarageData getid(Connection con, String Location) {
	    String sql="SELECT IDGarage,Location FROM Garages";
		sql += " WHERE Location=?";
		System.out.println("Esta seria la SQL: " + sql);
		IDGarageData garage=null; //ANTES DEL TRY PREPARAMOS EL SQL y LA SALIDA DE LA FUNCIOn
	    try {
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,Location);
			ResultSet resul=prep.executeQuery();
			resul.next();
			garage=new IDGarageData(
			Integer.parseInt(resul.getString("IDGarage")),
			resul.getString("Location")
			);
			resul.close();
			prep.close();
			
			
		} catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getID: " + sql + " Exception: " + e);
        }
        return garage;
		
	}
	
}