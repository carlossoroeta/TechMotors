import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@SuppressWarnings("serial")
public class CheckLogin extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        String user = req.getParameter("User");
        String password = req.getParameter("Password");
		String type = req.getParameter("type");
        
		String logged = check(connection,user,password,type);
     
		System.out.println("check Login username: " + logged);
		System.out.println("As " + type);
        System.out.println("check Login login, password: " + user + " " + password);
        if (logged != null) {
            HttpSession session = req.getSession(true);
            session.setAttribute("login", logged);
			if (type.equals("Customers")) {
				res.sendRedirect("CustomerMenu");
			} else {
				res.sendRedirect("ManagerMenu");
			} 
            
        } else {
            PrintWriter toClient = res.getWriter();
            if (user != null) {
              toClient.println("<h1>Login incorrect</h1>");
            }
            toClient.println(loginForm());
            toClient.println(Utils.footer());
            toClient.close();
        }
    }
    
    String check(Connection connection, String user, String password, String type) {
        String sql="";
		System.out.println("check type: " + type);
		if (type.equals("Customers")) {
		    sql = "Select Username FROM Customers";
		    sql += " WHERE Username=? and Password=?";
		} else {
			sql = "Select Username FROM Managers";
			sql += " WHERE Username=? and Password=?";
		}
	
		
        
        System.out.println("check Login: " + sql);
        String name = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            ResultSet result = pstmt.executeQuery();
            if(result.next()) {
                name = result.getString("Username");
           }
            result.close();
            pstmt.close();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException in check login: " + sql + " Exception: " + e);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("Exception in check login. Exception: " + e);
        }
        return name;
    }

    String loginForm() {
        String out = "";
        out += "<html><HEAD><link rel='stylesheet' href='w3.css'></HEAD><body>";
        out += "<P style='margin-top:40px;margin-left:10px;font-size:1.6em'><a href='index.html'><IMG SRC='atrasbien_opt.png' class='w3-bar-item' ></a> &nbsp &nbsp <STRONG>HOME</STRONG></P>";
        out += "<div class='w3-container w3-padding-64' ><h1>LOG IN</h1>";
        out += "<form id='formulario' action='CheckLogin'>";
        out += "<p type='User:'><input class='w3-input w3-padding-16' type='text' placeholder='User' required='' name='User'></p>";
        out += "<p type='Password:'><input class='w3-input w3-padding-16' type='text' placeholder='Password' required='' name='Password'></p>";
        out += "User type:<select class='w3-select' name='type' id='type'><option value='Customers'>Customer</option><option value='Managers'>Garage manager</option></select>";
		out += "<p><A href=''>Have you forgotten your password?</A></p>";
		out += "<p><button class='w3-button w3-light-grey w3-section' type='submit'>ENTER</button></p>";
        out += "</form></div>";
        return out;
    }
}