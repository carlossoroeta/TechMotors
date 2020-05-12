import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Connection;

@SuppressWarnings("serial")

public class Exchange extends HttpServlet {
	Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        connection = ConnectionUtils.getConnection(config);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
		HttpSession session = req.getSession(false);
		String username = (String)session.getAttribute("login");		
		String fecha = req.getParameter("Fecha");
		String appointment = req.getParameter("Appointment");
		String type = "Series";
		String vehicle = req.getParameter("Vehicle");
		String garage = req.getParameter("Garage");
		String hour = req.getParameter("Hour");
		Vector<ShowCategoryData> categorias=ShowCategoryData.getCategoryList(connection,type);
		toClient.println(Utils.header(username));
		toClient.println("<body><center><h1><STRONG>EXCHANGE</STRONG></h1></center><br/><br/>");
		toClient.println("<div class='w3-container w3-padding-64' id='contact'><p><fieldset align='center' class='w3-fieldset'>");
		toClient.println("<p><STRONG>Date:</STRONG> " + hour + " hour; " + fecha + "</p>");
		toClient.println("<p><STRONG>Car:</STRONG> " + vehicle + "</p>");
		toClient.println("<p><fieldset align='center' class='w3-fieldset'>");
		toClient.println("<legend align='center' style='font-size:1.4em'>What do you want to fix?</legend>");
		toClient.println("<table class='w3-table'>");
		toClient.println("<tr><TD><select class='w3-select' name='Category' id='Category' style='height:52px' onChange=SacarReferencia() >");
		for (int i=0;i<categorias.size();i++) {
			ShowCategoryData Categoria=categorias.elementAt(i);
			toClient.println("<option value='" + Categoria.Category + "'>" + Categoria.Category + "</option>");
		}
		toClient.println("</select></TD>");
		toClient.println("<TD><select class='w3-select' name='Reference' id='Reference' style='height:52px' onChange=SacarDatos()>");
		String primeracategoria= categorias.elementAt(0).Category;
		Vector<ShowCategoryData> primerasreferencias=ShowCategoryData.getPosibleReferences(connection,primeracategoria,type);
		String primerareferencia="" + primerasreferencias.elementAt(0).Reference + "";
		for (int i=0;i<primerasreferencias.size();i++) {
			ShowCategoryData referencia=primerasreferencias.elementAt(i);
			toClient.println("<option value='" + referencia.Reference + "'>" + referencia.Reference + "</option>");	
		}
 	
		toClient.println("</select></TD>");
		toClient.println("<td><input class='w3-input w3-padding-16' type='number' placeholder='Quantity' id='Quantity' required='' name='Quantity'></td><td>");
		ShowCategoryData app=ShowCategoryData.getPrice(connection,primerareferencia);
		toClient.println("<input class='w3-input w3-padding-16' type='text' value=" + app.UnitPriceSell + " disabled id='PrecioEnsenado'><input type='hidden' id='PrecioPorUnidad' name='preciounidad' value=" + app.UnitPriceSell + "> </td><td>");
		toClient.println("<button class='w3-button w3-light-grey w3-section' onclick=AddPart()>ADD</button></td></tr></table>");
		toClient.println("<P><form action='CustomerMenu' method='GET' id='formulario'>");
		toClient.println("<table id='lista' class='w3-table' style='border-collapse:separate;margin:10px;padding:10px;width:1450px' BORDER=1>");
		toClient.println("<tr class='w3-grey'><td>Category</td><td>Reference</td><td>Quantity</td><td>Price (Total)</td></tr></table></P>");
		toClient.println("<P class='w3-left'>Supplement of the service : 10%</P><P class='w3-right'><STRONG>TOTAL:</STRONG>&nbsp &nbsp &nbsp &nbsp &nbsp  <INPUT name='Price' id='total' disabled placeholder='Total'>&nbsp€ &nbsp &nbsp &nbsp </P>");
		toClient.println("<p  align='center'><button class='w3-button  w3-light-grey w3-section' type='submit' onclick=alertar()>Confirm</button></form><button class='w3-button  w3-light-grey w3-section' onclick=DelPart()>Delete Last Line</button></p>");
		toClient.println("</fieldset>&nbsp</fieldset>");
		toClient.println("</div><SCRIPT SRC='PartScript.js'></SCRIPT></body>");
		toClient.println("<SCRIPT SRC='exchange.js'></SCRIPT>");
		toClient.println("<SCRIPT SRC='alert.js'></SCRIPT>");
		toClient.println(Utils.footer());
        toClient.close();
		
		
	}
}

		