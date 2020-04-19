public class Utils {
    public static String header(String username) {
        StringBuilder str = new StringBuilder();
        str.append("<!DOCTYPE HTML>");
        str.append("<html>");
        str.append("<head><link rel='stylesheet' href='w3.css'></head>");
        str.append("<body>");
        str.append("<div class='w3-bar w3-grey w3-padding w3-card' style='letter-spacing:4px;'><A HREF=''><IMG SRC='LOGOE3E3E3.jpeg' class='w3-bar-item'></A><P></P><IMG SRC='atrasbien_opt.png' class='w3-bar-item' style='margin-top:40px'><div class='w3-right w3-hide-small' style='font-size:1.6em'><a href='' class='w3-bar-item' style='margin-top:50px'>" + username + "</a><A HREF=''><IMG SRC='logout.png' class='w3-bar-item' style='margin-top:50px'></A></div></div>");
        str.append("<div class='w3-bar w3-white w3-padding w3-card' style='letter-spacing:4px;'>");
        str.append("<div class='w3-right w3-hide-small'><a href='' class='w3-bar-item w3-button'>Services</a><a href='' class='w3-bar-item w3-button'>Garages</a><a href='' class='w3-bar-item w3-button'>INFO</a><a href='' class='w3-bar-item w3-button'>Contact</a></div>");
        str.append("</div>");
        return str.toString();
    }

    public static String footer() {
        StringBuilder str = new StringBuilder();
        str.append("</body>");
		str.append("<br><br>");
		str.append("<footer class='w3-container w3-padding-32 w3-grey'><style type='text/css'>a.nounderline:link{text-decoration:none;}</style><div class='w3-row-padding'><div class='w3-third'><h3>SPONSORS</h3><p>We make sure that the work done is of high quality. To achive this, we have different sponsors who provide us whit the necessary resources.</p></div><div class='w3-third'><h3> </h3><ul class='w3-ul'><a href='https://www.ferrari.com/es-ES/auto/gama' class='nounderline'><li class='w3-padding-16 w3-hover-black' ><img src='ferrarilogo.png' class='w3-left w3-margin-right' style='width:50px'><span class='w3-large'>Ferrari</span><br><span>Automobile manufacturer</span></li></a><a href='https://www.volkswagen.es/es.html' class='nounderline'><li class='w3-padding-16 w3-hover-black'><img src='vwlogo.png' class='w3-left w3-margin-right' style='width:50px'><span class='w3-large'>Volkswagen</span><br><span>Automobile manufacturer</span></li></a> </ul></div>");
        str.append("<div class='w3-third'><h3> </h3><ul class='w3-ul'><a href='https://www.nemak.com/' class='nounderline'><li class='w3-padding-16 w3-hover-black'><img src='NEMAK.png' class='w3-left w3-margin-right' style='width:50px'><span class='w3-large'>Nemak</span><br><span>Manufacture of automotive components</span></li></a><a href='https://es.linkedin.com/in/tecnun-motorsport-a004a744' class='nounderline'><li class='w3-padding-16 w3-hover-black'><img src='Tecnun.png' class='w3-left w3-margin-right' style='width:50px'><span class='w3-large'>Tecnun eRacing</span><br><span>Team Formula Student Tecnun</span></li></a> </ul></div></div></footer>");
		str.append("</html>");
        return str.toString();
    }
}