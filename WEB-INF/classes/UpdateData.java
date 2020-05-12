import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class UpdateData {   
    int    	  IDAppointment;
    String    Type;
    String    Vehicle;
	int 	  IDCustomer;
	boolean   Situation;
	int 	  Price;
	Date      Day;
	Time      Hour;
	int       IDGarage;
	

    
    UpdateData (int IDAppointment, String Type, String Vehicle, int IDCustomer, boolean Situation, int Price, Date Day, Time Hour, int IDGarage ) {  
        this.IDAppointment    = IDAppointment;
        this.Type  = Type;
        this.Vehicle   = Vehicle;
		this.IDCustomer   = IDCustomer;
		this.Situation   = Situation;
        this.Price   = Price;
        this.Day   = Day;
        this.Hour   = Hour;
        this.IDGarage   = IDGarage;

		
    }
	
	
	public static String getMatricula(Connection connection, String vehicle) {
        String sql ="SELECT LicensePlate As Matricula FROM Vehicles WHERE Model=?";
        System.out.println("getMatricula: " + sql);
		String matricula = "";;
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,vehicle);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                matricula = result.getString("Matricula");
				
                
            }
			
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getMatricula: " + sql + " Exception: " + e);
			System.out.println("Matricula: " + matricula);
        }
        return matricula;
    }
	
	public static int getIDCustomer(Connection connection, String username) {
        String sql ="SELECT IDCustomer As IDCustomerH FROM Customers WHERE Username=?";
        System.out.println("getIDCustomer: " + sql);
		int idcustomer = 0;;
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,username);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                idcustomer = Integer.parseInt(result.getString("IDCustomerH"));
                
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getIDCustomer: " + sql + " Exception: " + e);
			System.out.println("IDCustomer: " + idcustomer);
        }
        return idcustomer;
    }
	
	public static int getIDGarage(Connection connection, String garage) {
        String sql ="SELECT IDGarage As IDGarageH FROM Garages WHERE Location=?";
        System.out.println("getIDGarage: " + sql);
		int idgarage = 0;;
        try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,garage);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                idgarage = Integer.parseInt(result.getString("IDGarageH"));
                
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getIDGarage: " + sql + " Exception: " + e);
        }
        return idgarage;
    }
	
	public static int getMax(Connection connection) {  
        String sql = "Select max(IDAppointment) As Maximo FROM Appointments";  
        System.out.println("getMax: " + sql);
		int id = 0 ;;
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if(result.next()) {
                id = Integer.parseInt(result.getString("Maximo"));
                       
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getMax: " + sql + " Exception: " + e);
        }
        return id;
    }
	
	public static int updateDatabase(Connection connection, int IDDefinitiva,  String Type, String Matricula, int IDCustomer, boolean Situation, int Price, String Day, String Hour, int IDGarage) {
        String sql = "INSERT INTO Appointments (IDAppointment, Type, Vehicle, IDCustomer, Situation, Price, Day, Hour, IDGarage) VALUES (?,?,?,?,?,?,?,?,?)";
        System.out.println("updateDatabase: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
            stmtUpdate.setInt(1,IDDefinitiva);
            stmtUpdate.setString(2,Type);
            stmtUpdate.setString(3,Matricula);
			stmtUpdate.setInt(4,IDCustomer);
			stmtUpdate.setBoolean(5,Situation);
			stmtUpdate.setInt(6,Price);
			stmtUpdate.setString(7,Day);
			stmtUpdate.setString(8,Hour);
			stmtUpdate.setInt(9,IDGarage);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateDatabase: " + sql + " Exception: " + e);
			System.out.println(IDDefinitiva);
			System.out.println(Type);
			System.out.println(Matricula);
			System.out.println(IDCustomer);
			System.out.println(Situation);
			System.out.println(Price);
			System.out.println(Day);
			System.out.println(Hour);
			System.out.println(IDGarage);
        }
        return n;
    }
	
}	