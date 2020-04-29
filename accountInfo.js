function accountInfo() {    
    var request = new XMLHttpRequest();
    request.open('GET', 'GetManagerInfo?json', true);
    request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
		parentNode = document.getElementById('Table1');
		parentNode.innerHTML="";
        var manager = JSON.parse(request.responseText);

		formulario = document.createElement("form");
		formulario.setAttribute("action", "updateAccount");
		formulario.setAttribute("method" , "GET");
		parentNode.appendChild(formulario);
		
		var table = document.createElement("TABLE");
		table.setAttribute("class", "w3-table w3-center");
		table.setAttribute("style", "border-collapse:separate;margin:10px;padding:10px");
		table.setAttribute("border", "1");
		formulario.appendChild(table);
		
		
		r1= document.createElement("tr");
		table.appendChild(r1);
		
		td1=document.createElement("td");
		td1.innerHTML= "Username";
		r1.appendChild(td1);
				
		td2=document.createElement("td");
		r1.appendChild(td2);
		var inpu1= document.createElement("INPUT");
		inpu1.setAttribute("name", "Username");
		inpu1.setAttribute("id", "repitUser");
		inpu1.setAttribute("value", manager.Username);
		inpu1.setAttribute("onchange", "repiteUsername()");
		td2.appendChild(inpu1);
		
		var donde=document.createElement("div");
		donde.setAttribute("id", "avisoWhere");
		td2.appendChild(donde);
	///////////////////////////////////	
		r2=document.createElement("tr");
		table.appendChild(r2);
		
		td3=document.createElement("td");
		td3.innerHTML= "Current password";
		r2.appendChild(td3);
		
		td4=document.createElement("td");
		r2.appendChild(td4);
		var inpu2= document.createElement("INPUT");
		inpu2.setAttribute("name", "Password");
		inpu2.setAttribute("value", manager.Password);
		td4.appendChild(inpu2);
	/////////////////////////////////////	
		boton=document.createElement("input");
		boton.setAttribute("type", "submit");
		boton.setAttribute("value", "Update");
		formulario.appendChild(boton);
		
		
		
		
				
			
      }
    };
    request.send();
}

function repiteUsername() {    
    var data ="";

    var request = new XMLHttpRequest();
    var user=document.getElementById("repitUser").value;
    request.open('GET', 'ManagerJSON?json&user=' + user, true);
    request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        var data = JSON.parse(request.responseText);
       
		var sacarAviso=document.getElementById("avisoWhere");


		if (data.Username!="null") {
			var aviso=document.createElement("P");
			sacarAviso.appendChild(aviso);
			aviso.innerHTML="Este username ya existe";
			aviso.setAttribute("style","color:#fa1414");
		} else {
		   
		sacarAviso.innerHTML="";
		}
	 }
		   
		};
    request.send();
}