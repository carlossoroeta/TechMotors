donde=document.getElementById("chat");

function insertarmensajes(Type,IDMessage,username,id){
	var fecha = new Date();
	var textomandado=document.getElementById("mensaje").value;
	var fechaglobal = new Date();
	if ((fecha.getMonth()+1)<10 && fecha.getHours()<10 && fecha.getMinutes<10 && fecha.getSeconds<10){
		fecha=(fecha.getDate()+"/0"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" 0"+fecha.getHours()+":0"+fecha.getMinutes()+":0"+fecha.getSeconds());
		
	}else if ((fecha.getMonth()+1)<10 && fecha.getHours()<10 && fecha.getMinutes<10){
		fecha=(fecha.getDate()+"/0"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" 0"+fecha.getHours()+":0"+fecha.getMinutes()+":"+fecha.getSeconds());
	}else if ((fecha.getMonth()+1)<10 && fecha.getHours()<10 ){
		fecha=(fecha.getDate()+"/0"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" 0"+fecha.getHours()+":"+fecha.getMinutes()+":"+fecha.getSeconds());
	}else if ((fecha.getMonth()+1)<10  ){
		fecha=(fecha.getDate()+"/0"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" "+fecha.getHours()+":"+fecha.getMinutes()+":"+fecha.getSeconds());		
	}else if ((fecha.getMonth()+1)<10 && fecha.getMinutes<10 && fecha.getSeconds<10){
		fecha=(fecha.getDate()+"/0"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" "+fecha.getHours()+":0"+fecha.getMinutes()+":0"+fecha.getSeconds());
	}else if ((fecha.getMonth()+1)<10 && fecha.getMinutes<10 ){
		fecha=(fecha.getDate()+"/0"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" "+fecha.getHours()+":0"+fecha.getMinutes()+":"+fecha.getSeconds());
	}else if ((fecha.getMonth()+1)<10 && fecha.getSeconds<10 ){
		fecha=(fecha.getDate()+"/0"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" "+fecha.getHours()+":"+fecha.getMinutes()+":0"+fecha.getSeconds());
	}else if (fecha.getMinutes<10 && fecha.getSeconds<10 ){
		fecha=(fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" "+fecha.getHours()+":0"+fecha.getMinutes()+":0"+fecha.getSeconds());
	}else if (fecha.getMinutes<10 && fecha.getSeconds<10 && fecha.getHours()<10 ){
		fecha=(fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" 0"+fecha.getHours()+":0"+fecha.getMinutes()+":0"+fecha.getSeconds());
	}else if (fecha.getSeconds<10 && fecha.getHours()<10 ){
		fecha=(fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" 0"+fecha.getHours()+":"+fecha.getMinutes()+":0"+fecha.getSeconds());
	}else if (fecha.getMinutes<10 && fecha.getHours()<10 ){
		fecha=(fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+fecha.getFullYear()+" 0"+fecha.getHours()+":0"+fecha.getMinutes()+":"+fecha.getSeconds());
	}		
	total=document.createElement("div");
	total.setAttribute("id","mensajes");
	donde.appendChild(total);
	formu=document.createElement("form");
	formu.setAttribute("name","mensajeUbicado");
	formu.setAttribute("action","messageInsert");
	formu.setAttribute("method","get");
	total.appendChild(formu);
	inputsobrante=document.createElement("input");
	inputsobrante.setAttribute("type","hidden");
	inputsobrante.setAttribute("name","Username");
	inputsobrante.setAttribute("value",username);
	formu.appendChild(inputsobrante);
	inputsobrante1=document.createElement("input");
	inputsobrante1.setAttribute("type","hidden");
	inputsobrante1.setAttribute("name","Type");
	inputsobrante1.setAttribute("value",Type);
	formu.appendChild(inputsobrante1);
	inputsobrante2=document.createElement("input");
	inputsobrante2.setAttribute("type","hidden");
	inputsobrante2.setAttribute("name","IDSend");
	inputsobrante2.setAttribute("value",id);
	formu.appendChild(inputsobrante2);
		contenido=document.createElement("input");
	contenido.setAttribute("type","hidden");
	contenido.setAttribute("name","Content");
	contenido.setAttribute("value",textomandado);
	formu.appendChild(contenido);
	fechaactual=document.createElement("input");
	fechaactual.setAttribute("type","hidden");
	fechaactual.setAttribute("name","DateHora");
	fechaactual.setAttribute("value",fecha);
	formu.appendChild(fechaactual);
		idmensaje=document.createElement("input");
	idmensaje.setAttribute("type","hidden");
	idmensaje.setAttribute("name","IDMessage");
	idmensaje.setAttribute("value",IDMessage);
	formu.appendChild(idmensaje);
	
	
	
	
	mensajeID=document.createElement("span");
	mensajeID.setAttribute("name","IDSend");
	mensajeID.style.color="#1C62C4";
	mensajeID.textContent=id+":"+username;
	formu.appendChild(mensajeID);
	mensajespacio=document.createElement("span")
	mensajespacio.textContent=" ";
	formu.appendChild(mensajespacio);
	mensajeTexto=document.createElement("span");
	mensajeTexto.setAttribute("name","Content");
	mensajeTexto.setAttribute("value",textomandado);
	mensajeTexto.style.color="#848484";
	mensajeTexto.textContent=textomandado;
	formu.appendChild(mensajeTexto);
	DateHora=document.createElement("span");
	DateHora.setAttribute("name","DateHora");
	DateHora.style.cssFloat="right";
	DateHora.textContent=fecha;
	formu.appendChild(DateHora);
	formu.submit()
	
	

}