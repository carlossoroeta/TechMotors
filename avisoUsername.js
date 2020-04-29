function comprobarusernamecambio() {    
    var data ="";

    var request = new XMLHttpRequest();
    var user=document.getElementById("UserName").value;
    request.open('GET', 'UsernamesJSON?json&user=' + user, true);
    request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        var data = JSON.parse(request.responseText);
       
		var dondeavisouser=document.getElementById("dondeaviso");




		if (data.Username!="null") {
		var aviso=document.createElement("P");
		dondeavisouser.appendChild(aviso);
			   aviso.innerHTML="Este username ya existe";
		aviso.setAttribute("style","color:#fa1414");
		} else {
		   
		dondeavisouser.innerHTML="";
		}
	 }
		   
		};
    request.send();
}