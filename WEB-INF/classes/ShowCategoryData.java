import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class ShowCategoryData {   
    String    Category;
	String    Reference;
	float       UnitPriceSell;
	
	ShowCategoryData (String Category, String Reference, float UnitPriceSell) {
        this.Category    = Category;
		this.Reference    = Reference;
		this.UnitPriceSell    = UnitPriceSell;
		
	}
	
	public static Vector<ShowCategoryData> getCategoryList(Connection connection, String type) {
        Vector<ShowCategoryData> vec = new Vector<ShowCategoryData>();
		String sql = "Select Category FROM Inventory";   
		sql += " WHERE Type=? GROUP BY Category";		
        System.out.println("getCategoryList: " + sql);
		try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
			pstmt.setString(1,type);
			ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                ShowCategoryData categoria = new ShowCategoryData(
                    result.getString("Category"),
					null,
					0
                
                );
                vec.addElement(categoria);
            }
			} catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getCategoryList: " + sql + " Exception: " + e);
        }
        return vec;
    }
	
	public static Vector<ShowCategoryData> getPosibleReferences(Connection connection, String primeracategoria, String type) {	
		Vector<ShowCategoryData> vec=new Vector<ShowCategoryData>();
		String sql="SELECT Reference FROM Inventory";
		sql += " WHERE Category=? AND Type=? GROUP BY Reference";
		System.out.println("getPosibleReferences: " + sql);
		try {
            PreparedStatement pstmt=connection.prepareStatement(sql);
            pstmt.setString(1,primeracategoria);
			pstmt.setString(2,type);
            ResultSet result = pstmt.executeQuery();
			while(result.next()) {
                ShowCategoryData referencia = new ShowCategoryData(
					null,
					result.getString("Reference"),
					0
                
                );
                vec.addElement(referencia);
            }
			} catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getPosibleReferences: " + sql + " Exception: " + e);
        }
        return vec;
    }
	
	public static ShowCategoryData getPrice(Connection connection,String primerareferencia) {
		ShowCategoryData app= null;
		String sql="SELECT UnitPriceSell FROM Inventory";
		sql += " WHERE Reference=?";
		System.out.println("getPrice: " + sql);
		try {
			PreparedStatement prep=connection.prepareStatement(sql);
			prep.setString(1,primerareferencia);
			ResultSet result=prep.executeQuery();
			if(result.next()) {
                app = new ShowCategoryData(
					null,
					null,
					Float.parseFloat(result.getString("UnitPriceSell"))
                
                );
              
            }
			} catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getPrice: " + sql + " Exception: " + e);
        }
        return app;
    }
			
			
			
}		