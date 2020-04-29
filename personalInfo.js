function personalInfo() {    
    var request = new XMLHttpRequest();
    request.open('GET', 'GetManagerInfo?json', true);
    request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
		parentNode = document.getElementById('Table1');
		parentNode.innerHTML="";
        var manager = JSON.parse(request.responseText);

		formulario = document.createElement("form");
		formulario.setAttribute("action", "updatePersonal");
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
		td1.innerHTML= "Bank Number";
		r1.appendChild(td1);
				
		td2=document.createElement("td");
		r1.appendChild(td2);
		var inpu1= document.createElement("INPUT");
		inpu1.setAttribute("name", "BankNumber");
		inpu1.setAttribute("value", manager.BankNumber);
		td2.appendChild(inpu1);
	///////////////////////////////////	
		r2=document.createElement("tr");
		table.appendChild(r2);
		
		td3=document.createElement("td");
		td3.innerHTML= "Direction";
		r2.appendChild(td3);
		
		td4=document.createElement("td");
		r2.appendChild(td4);
		var inpu2= document.createElement("INPUT");
		inpu2.setAttribute("name", "Direction");
		inpu2.setAttribute("value", manager.Direction);
		td4.appendChild(inpu2);
	////////////////////////////////////
		r3=document.createElement("tr");
		table.appendChild(r3);
		
		td4=document.createElement("td");
		td4.innerHTML= "Postal Code";
		r3.appendChild(td4);
		
		td5=document.createElement("td");
		r3.appendChild(td5);
		var inpu3= document.createElement("INPUT");
		inpu3.setAttribute("name", "PostalCode");
		inpu3.setAttribute("value", manager.PostalCode);
		td5.appendChild(inpu3);
	/////////////////////////////////////
		r4=document.createElement("tr");
		table.appendChild(r4);
		
		td6=document.createElement("td");
		td6.innerHTML= "Telephone";
		r4.appendChild(td6);
		
		td7=document.createElement("td");
		r4.appendChild(td7);
		var inpu4= document.createElement("INPUT");
		inpu4.setAttribute("name", "Telephone");
		inpu4.setAttribute("value", manager.Telephone);
		td7.appendChild(inpu4);
	/////////////////////////////////////	
		boton=document.createElement("input");
		boton.setAttribute("type", "submit");
		boton.setAttribute("value", "Update");
		formulario.appendChild(boton);
				
			
      }
    };
    request.send();
}