import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")
public class chat extends HttpServlet {
    Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
				HttpSession session =req.getSession(false);
		String username=(String)session.getAttribute("login");
		Vector <messageData> messages;
		messages=messageData.getMessages(connection);
		
		messageData user=messageData.getMessageUser(connection,username);
		if (user.Type.equals("Customer")){
        toClient.println(Utils.header(username));
		}else{toClient.println(Utils.managerheader(username));
		}
		
		toClient.println("<head><title>TechMotors Community</title><link rel='stylesheet' type='text/css' href='estilochat.css'><link href='https://fonts.googleapis.com/css?family=Mukta+Vaani' rel='stylesheet'></head>");
        toClient.println("<body><h1 align='center' style='font-size:2.4em'><STRONG>TechCommunity-Chat</STRONG></h1>");
			toClient.println("<div id='contenedor'>");
				toClient.println("<div id='cajachat'>");
					toClient.println("<div id='chat'>");
					for(int i=0; i< messages.size(); i++){
						messageData message = messages.elementAt(i);
						toClient.println("<div id='mensajes'>");
						System.out.println(message.Type);
						if (message.Type.equals("Customer")){
						toClient.println("<span style='color: #a1cc1b'>"+ message.Username +"</span>");
						}else {
						toClient.println("<span style='color: #1C62C4';font-weight:bold>"+ message.Username +"</span>");
						}
						toClient.println("<span style='color: #848484'>"+ message.Content +"</span>");
						toClient.println("<span style='float: right'>"+ message.DateHora +"</span>");
						toClient.println("</div>");
						
					}
					toClient.println("</div>");
				toClient.println("</div>");
			System.out.println(messages.size()+"hola");
				toClient.println("<textarea id='mensaje' name='mensaje' placeholder='Write your message'></textarea>");
		
				toClient.println("<button onclick=\"insertarmensajes('"+ user.Type +"','"+ messages.size() +"','"+ username +"','"+ user.IDSend +"')\" type='submit' name='Send' style='color: #fff' >Send Message ");
				if (user.Type.equals("Customer")){
				toClient.println("<span style='color: #a1cc1b'>"+ user.Type +"</span></button>");
				}else {
					toClient.println("<span style='color:#1C62C4;font-weight:bold'>"+ user.Type +"</span></button>");
				}
				toClient.println("<script src='mensaje.js'></script>");
			
		toClient.println("</div>");
		toClient.println("</body></html>");
				
                
    }
}




	
		
			
				
	