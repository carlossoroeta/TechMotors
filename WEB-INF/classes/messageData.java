import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
	public class messageData {
    int    IDMessage;
    String    Type;
    String 	DateHora;
    String    Content;
	int IDSend;
	String Username;

    
    messageData (int IDMessage, String Type, String DateHora, String Content, int IDSend, String Username) {
        this.IDMessage    = IDMessage;
        this.Type   = Type;
        this.DateHora = DateHora;
        this.Content = Content;
		this.IDSend=IDSend;
		this.Username=Username;
    }
	
	public static Vector<messageData>getMessages(Connection connection){
		Vector<messageData> vec = new Vector<messageData>();
        String sql = "Select IDMessage, Type, DateHora, Content, IDSend,Username FROM Messages";
        System.out.println("getMessages: " + sql);

        try {
           PreparedStatement pstmt = connection.prepareStatement(sql);
		   
		   ResultSet result = pstmt.executeQuery();
            while(result.next()) {
                messageData message = new messageData(
                    
                    result.getInt("IDMessage"),
                    result.getString("Type"),
					result.getString("DateHora"),
					result.getString("Content"),
					result.getInt("IDSend"),
					result.getString("Username")

                );
                vec.addElement(message);
				
            }
			System.out.println(vec.size());
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getMessages: " + sql + " Exception: " + e);
        }
        return vec;
		
		
		}

		
		
		public static messageData getMessageUser(Connection connection,String username){
		
        String sql = "Select  Type, ID FROM MessageUsers WHERE MessageUsers.Username=?";
       messageData user = null;;
		System.out.println("getMessages: " + sql);
		
        try {
           PreparedStatement pstmt = connection.prepareStatement(sql);
		   pstmt.setString(1, username);
		   ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                 user = new messageData(
                    
                    0,
                    result.getString("Type"),
					null,
					null,
					result.getInt("ID"),
					null

                );
              
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getMessageUser: "+ username + "" + sql + " Exception: " + e);
        }
        return user;
		
		
		}
		 public static int messageSave(Connection connection, messageData usermessage) {
        String sql ="INSERT INTO Messages (IDMessage,Type,DateHora,Content,IDSend,Username) "
            + "VALUES ( ?, ?, ?, ?, ?, ?) ";
        System.out.println("messageSave: " + sql);
		System.out.println(usermessage.Type);
		System.out.println(usermessage.Type);
		  
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
			stmtUpdate.setInt(1,(usermessage.IDMessage+1));
			stmtUpdate.setString(2,usermessage.Type);
			stmtUpdate.setString(3,usermessage.DateHora);
            stmtUpdate.setString(4,usermessage.Content);
			stmtUpdate.setInt(5,usermessage.IDSend);
			stmtUpdate.setString(6,usermessage.Username);
			
			
			n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in messageSave: " + sql + " Exception: " + e);
			System.out.println(usermessage.IDMessage);
			System.out.println(usermessage.Type);
			System.out.println(usermessage.DateHora);
			System.out.println(usermessage.Content);
			System.out.println(usermessage.IDSend);
			System.out.println(usermessage.Username);
			

			
			}
        return n;
    }
	}


	

