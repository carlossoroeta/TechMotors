
import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerData {
	int IDManager;
    String    Name;
    String    Location;
    int Telephone;
    String    Username;
	String Password;
	String BankNumber;
	int PostalCode;
	String Direction;
    
    ManagerData (int IDManager, String    Name, String    Location,  int Telephone,String Username, String Password, String BankNumber, int PostalCode, String Direction) {
        this.IDManager    = IDManager;
		this.Name    = Name;
        this.Location   = Location;
        this.Telephone = Telephone;
        this.Username = Username;
		this.Password = Password;
		this.BankNumber = BankNumber;
		this.PostalCode = PostalCode;
		this.Direction = Direction;
    }
	
	public static ManagerData getManagerDetail(Connection con, String username) {

        String sql = "Select Managers.IDManager, Managers.Name, Garages.Location, Managers.Telephone, Managers.Username, Managers.Password, Managers.BankNumber, Managers.PostalCode, Managers.Direction FROM Managers, Garages";
        sql += " WHERE Managers.IDGarage=Garages.IDGarage AND Managers.Username=?";
		System.out.println("Esta seria la SQL: " + sql);
        //System.out.println("getManagerDetail: " + sql);	
		ManagerData manager=null;
		System.out.println(username );
        try {
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,username);
			ResultSet resul=prep.executeQuery();
			resul.next();
			manager=new ManagerData(
			Integer.parseInt(resul.getString("IDManager")),
					resul.getString("Name"),
					resul.getString("Location"),
                    Integer.parseInt(resul.getString("Telephone")),
                    resul.getString("Username"),
                    resul.getString("Password"),
					resul.getString("BankNumber"),
					Integer.parseInt(resul.getString("PostalCode")),
					resul.getString("Direction")
			);
			resul.close();
			prep.close();
			
			
		} catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getManagerDetail: " + sql + " Exception: " + e);
        }
        return manager;
		
	}
	public static int updateManagerPersonal(Connection con,ManagerData manager) {
		int n=0;
		String sql="UPDATE Managers ";
		sql += "SET BankNumber=?, Direction=?, PostalCode=?, Telephone=? ";
		sql += "WHERE Username=?";
		try {
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,manager.BankNumber);
			prep.setString(2,manager.Direction);
			prep.setInt(3,manager.PostalCode);
			prep.setInt(4,manager.Telephone);
			prep.setString(5,manager.Username);
			n=prep.executeUpdate();
			prep.close();
			
		}catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateManagerPersonal: " + sql + " Exception: " + e);
        }
        return n;
				
		
	}
	
		public static String checkManagers(Connection connection, String user) {
		
     
		String sql = "Select Username FROM Managers";
		sql += " WHERE Username=?";
	
		
        
        System.out.println("checkusernames: " + sql);
        String name = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                name = result.getString("Username");
           }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in checkusernames: " + sql + " Exception: " + e);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception in checkusernames Exception: " + e);
        }
        return name;
    }
	
	public static int updateManagerAccount(Connection con,ManagerData manager, String pastUser) {
		int n=0;
		String sql="UPDATE Managers ";
		sql += "SET Username=?, Password=? ";
		sql += "WHERE Username=?";
		try {
			PreparedStatement prep=con.prepareStatement(sql);
			prep.setString(1,manager.Username);
			prep.setString(2,manager.Password);
			prep.setString(3,pastUser);
			n=prep.executeUpdate();
			prep.close();
			
		}catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateManagerPersonal: " + sql + " Exception: " + e);
        }
        return n;
				
		
	}
    }
		
		
