import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class MyVehiclesData{
	String Model;
	String LicensePlate;
	Date BuyDate;
	Date OilDate;
	Date ITVDate;
	Date FilterDate;

	
	MyVehiclesData(String Model, String LicensePlate, Date BuyDate, Date OilDate, Date ITVDate, Date FilterDate){
		this.Model=Model;
		this.LicensePlate=LicensePlate;
		this.BuyDate=BuyDate;
		this.OilDate=OilDate;
		this.ITVDate=ITVDate;
		this.FilterDate=FilterDate;
		

	}
	public static Vector<MyVehiclesData>getMyVehicleList(Connection connection,String username){
		Vector<MyVehiclesData> vec = new Vector<MyVehiclesData>();
        String sql = "Select Model, LicensePlate, BuyDate, OilDate, ITVDate, FilterDate FROM Vehicles,Customers WHERE Vehicles.IDCustomer=Customers.IDCustomer AND Customers.Username=?";
        System.out.println("getMyVehicleList: " + sql);
        try {
           PreparedStatement pstmt = connection.prepareStatement(sql);
		   pstmt.setString(1, username);
		   ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                MyVehiclesData vehicle = new MyVehiclesData(
                    
                    result.getString("Model"),
                    result.getString("LicensePlate"),
					result.getDate("BuyDate"),
					result.getDate("OilDate"),
					result.getDate("ITVDate"),
					result.getDate("FilterDate")
                );
                vec.addElement(vehicle);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getMyVehicleList: " + sql + " Exception: " + e);
        }
        return vec;
	}
	    public static MyVehiclesData getVehicle(Connection connection, String LicensePlate) {
		
        String sql = "Select Model, LicensePlate, BuyDate, OilDate, ITVDate, FilterDate FROM Vehicles";
        sql += " WHERE LicensePlate=?";
        System.out.println("getVehicle: " + sql);
        MyVehiclesData vehicle = null;;
		
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, LicensePlate);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                vehicle = new MyVehiclesData(
                    result.getString("Model"),
                    result.getString("LicensePlate"),
					result.getDate("BuyDate"),
					result.getDate("OilDate"),
					result.getDate("ITVDate"),
					result.getDate("FilterDate")

				
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getVehicle: " + sql + " Exception: " + e);
        }
        return vehicle;
    }
	    public static int MyVehiclesUpdate(Connection connection, String LicensePlate,String OilDate, String ITVDate, String FilterDate) {
        String sql ="UPDATE Vehicles "
            + "SET  OilDate = ?, ITVDate = ?, FilterDate = ?"
            + " WHERE LicensePlate = ?";
        System.out.println("MyVehiclesUpdate: " + sql);
		System.out.println(LicensePlate);
		System.out.println(OilDate);
		System.out.println(ITVDate);
		System.out.println(FilterDate);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            
			
			stmtUpdate.setString(1,OilDate);
			stmtUpdate.setString(2,ITVDate);
			stmtUpdate.setString(3,FilterDate);
			stmtUpdate.setString(4,LicensePlate);
			
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in MyVehiclesUpdate: " + sql + " Exception: " + e);
        }
        return n;
    }
	public static int MyVehiclesDelete(Connection connection, String LicensePlate) {
        String sql ="DELETE FROM Vehicles WHERE "
            + "LicensePlate=?";
        System.out.println("deleteVehicle: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setString(1,LicensePlate);

			
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in MyVehiclesDelete: " + sql + " Exception: " + e);
        }
        return n;
    }
		public static int getIDCustomer(Connection connection, String username) {
		String sql ="Select IDCustomer FROM Customers WHERE  Customers.Username=?";
        System.out.println("getMyVehicleList: " + sql);
		
		int n=0;
        try {
           PreparedStatement pstmt = connection.prepareStatement(sql);
		   pstmt.setString(1, username);
		   ResultSet result = pstmt.executeQuery();
		   if (result.next()){
			   n=result.getInt("IDCustomer");
		   }

                  result.close();
				  pstmt.close();
				  
                    

         
            
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getIDCustomer: " + sql + " Exception: " + e);
			  System.out.println("ID: " + n); 
		}
        return n;
	}
	public static String checkLicensePlate(Connection connection, String LicensePlate) {
		
     
		String sql = "Select LicensePlate FROM Vehicles";
		sql += " WHERE LicensePlate=?";
	
		
        
        System.out.println("checkLicensePlate: " + sql);
        String name = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, LicensePlate);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                name = result.getString("LicensePlate");
           }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in checkLicensePlate: " + sql + " Exception: " + e);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception in checkLicensePlate Exception: " + e);
        }
        return name;
    }

	    public static int insertMyVehicle(Connection connection, int n1, String Model, String LicensePlate, String BuyDate, String OilDate, String ITVDate, String FilterDate) {
        String sql ="INSERT INTO Vehicles (Model, LicensePlate, BuyDate, OilDate, ITVDate, FilterDate, IDCustomer) "
            + "VALUES (?, ?, ?, ?, ?,?,?) ";
        System.out.println("insertMyVehicle: " + sql);
		  
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
			
			stmtUpdate.setString(1,Model);
			stmtUpdate.setString(2,LicensePlate);
            stmtUpdate.setString(3,BuyDate);
			stmtUpdate.setString(4,OilDate);
			stmtUpdate.setString(5,ITVDate);
			stmtUpdate.setString(6,FilterDate);
			stmtUpdate.setInt(7,n1);
			n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in insertOrderDetail: " + sql + " Exception: " + e);
			System.out.println("error" + n1);
			System.out.println("error" + Model);
			System.out.println("error" + LicensePlate);
			System.out.println("error" + BuyDate);
			System.out.println("error" + OilDate);
			System.out.println("error" + ITVDate);
			System.out.println("error" + FilterDate);
			
			}
        return n;
    }
}

