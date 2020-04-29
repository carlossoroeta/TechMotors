function editar(id,model,matri,OilDate,ITVDate,FilterDate){
	a=document.getElementById(id);
	a.innerHTML="";
	 formu=document.createElement("form");
	formu.setAttribute("name","newprod");
	formu.setAttribute("action","MyVehiclesUpdate");
	formu.setAttribute("method","get");
	Tabla=document.createElement("TABLE");
	Tabla.setAttribute("border","1");
	a.appendChild(formu);
	formu.appendChild(Tabla);
	fila=document.createElement("TR");
	Tabla.appendChild(fila);
	EstablecerCeldaconInput(model, "Model", fila);
	 fila1=document.createElement("TR");
	Tabla.appendChild(fila1);
	EstablecerCeldaconInput(matri, "LicensePlate", fila1);
	 fila2=document.createElement("TR");
	Tabla.appendChild(fila2);
	EstablecerCeldaconInputDeFechas(OilDate,"OilDate", fila2);
	 fila3=document.createElement("TR");
	Tabla.appendChild(fila3);
	EstablecerCeldaconInputDeFechas(ITVDate,"ITVDate", fila3);
	 fila4=document.createElement("TR");
	Tabla.appendChild(fila4);
	EstablecerCeldaconInputDeFechas(FilterDate,"FilterDate",fila4);


	 boton=document.createElement("button");
	boton.innerHTML = ("UPDATE");    
	boton.setAttribute("type","submit");
	boton.setAttribute("action","MyVehiclesUpdate");	
	formu.appendChild(boton);
}
	function EstablecerCeldaconInput(value,name,fila){
	
	 celda1=document.createElement("TD");
	fila.appendChild(celda1);
	texto=document.createTextNode(name);
	celda1.appendChild(texto);
	 celda2=document.createElement("TD");
	fila.appendChild(celda2);
	 input=document.createElement("INPUT");
	input.setAttribute("name",name);
	input.setAttribute("value",value);
	input.style.width = "216px";
	input.readOnly = true
	
	celda2.appendChild(input);

}
	function EstablecerCeldaconInputDeFechas(value,name,fila){
	
	celda1=document.createElement("TD");
	fila.appendChild(celda1);
	texto=document.createTextNode(name);
	celda1.appendChild(texto);
	celda2=document.createElement("TD");
	fila.appendChild(celda2);
	input=document.createElement("INPUT");
	input.style.width = "216px";
	
	input.setAttribute("name",name);
	input.setAttribute("value",value);
	input.setAttribute("type","date");
	
	celda2.appendChild(input);

}
                

	
	
	



