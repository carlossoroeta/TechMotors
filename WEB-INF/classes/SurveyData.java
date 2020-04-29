import java.util.Vector;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SurveyData {
    int    IDSurvey;
	int	   IDCustomer;
    String Question1;
    int Question2;
	String Question3;
	String Question4;
    
    SurveyData (int IDSurvey, int IDCustomer, String Question1, int Question2, String Question3, String Question4) {
        this.IDSurvey    = IDSurvey;
		this.IDCustomer    = IDCustomer;
        this.Question1  = Question1;
        this.Question2 = Question2;
		this.Question3  = Question3;
        this.Question4 = Question4;
    }
    public static Vector<SurveyData> getSurveyList(Connection connection) {
        Vector<SurveyData> vec = new Vector<SurveyData>();
        String sql = "SELECT IDSurvey, IDCustomer, Question1, Question2, Question3, Question4 FROM SurveyOfApplication";
        System.out.println("getSurveyList: " + sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                SurveyData survey = new SurveyData(
                    Integer.parseInt(result.getString("IDSurvey")),
					Integer.parseInt(result.getString("IDCustomer")),
                    result.getString("Question1"),
					Integer.parseInt(result.getString("Question2")),
					result.getString("Question3"),
                    result.getString("Question4")
                );
                vec.addElement(survey);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getSurveyList: " + sql + " Exception: " + e);
        }
        return vec;
    }
    
    public static SurveyData getSurvey(Connection connection, int id) {
        String sql = "SELECT IDSurvey, IDCustomer, Question1, Question2, Question3, Question4 FROM SurveyOfApplication";
        sql += " WHERE IDSurvey=?";
        System.out.println("getSurvey: " + sql);
        SurveyData survey = null;;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                survey = new SurveyData(
                    Integer.parseInt(result.getString("IDSurvey")),
					Integer.parseInt(result.getString("IDCustomer")),
                    result.getString("Question1"),
					Integer.parseInt(result.getString("Question2")),
					result.getString("Question3"),
                    result.getString("Question4")
                );
            }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in getSurvey: " + sql + " Exception: " + e);
        }
        return survey;
    }
    
    public static int updateSurvey(Connection connection, SurveyData survey) {
        String sql ="UPDATE SurveyOfApplication "
            + "SET IDCustomer = ?, Question1 = ?, Question2 = ?, Question3 = ?, Question4 = ?"
            + " WHERE IDSurvey = ?";
        System.out.println("updateSurvey: " + sql);
        int n = 0;
        try {
            PreparedStatement stmtUpdate= connection.prepareStatement(sql);
			stmtUpdate.setInt(1,survey.IDCustomer);
            stmtUpdate.setString(2,survey.Question1);
            stmtUpdate.setInt(3,survey.Question2);
			stmtUpdate.setString(4,survey.Question3);
            stmtUpdate.setString(5,survey.Question4);
            stmtUpdate.setInt(6,survey.IDSurvey);
            n = stmtUpdate.executeUpdate();
            stmtUpdate.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error in updateSurvey: " + sql + " Exception: " + e);
        }
        return n;
    }
}