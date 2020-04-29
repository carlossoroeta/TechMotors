import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class piezasXappointmentsData {

	String Category;
	String Reference;
	int Quantity;
	
    piezasXappointmentsData ( String Category, String Reference, int Quantity) {
        this.Category    = Category;
        this.Reference  = Reference;
		this.Quantity   = Quantity;

		
    }
    public static Vector<piezasXappointmentsData> getPiezas(Connection con, java.sql.Date Day, java.sql.Time Hour, int IDGarage) {
		
		Vector<piezasXappointmentsData> Lista=new Vector<piezasXappointmentsData>();
		String sql="SELECT Inventory.Category, InventoryAppointments.Reference, InventoryAppointments.Quantity FROM Inventory, InventoryAppointments ";
		sql+= " WHERE InventoryAppointments.Reference=Inventory.Reference AND InventoryAppointments.Day=? AND InventoryAppointments.Hour=? AND InventoryAppointments.IDGarage=?";
		System.out.println("Este es el sql:" + sql);
		
		try {
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setDate(1,Day);
			prep.setTime(2,Hour);
			prep.setInt(3,IDGarage);
			ResultSet resul=prep.executeQuery();
			
			while (resul.next()) {
				piezasXappointmentsData piezas=new piezasXappointmentsData(
				resul.getString("Category"),
				resul.getString("Reference"),
				Integer.parseInt(resul.getString("Quantity"))
				);
				Lista.addElement(piezas);
			}
			resul.close();
			prep.close();
		
		} catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getProductList: " + sql + " Exception: " + e);
        }
        return Lista;
	}
	
	public static int deleteInventory(Connection connection, int IDGarage, String Day, String Hour) {
        String sql ="DELETE FROM InventoryAppointments "
            + " WHERE IDGarage=? AND Day=? AND Hour=?";
        System.out.println("deleteAppointment: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtDelete= connection.prepareStatement(sql);
            stmtDelete.setInt(1,IDGarage);
			stmtDelete.setString(2,Day);
			stmtDelete.setString(3,Hour);
            n = stmtDelete.executeUpdate();
            stmtDelete.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in deleteAppointment: " + sql + " Exception: " + e);
        }
        return n;
    }
	
	

}