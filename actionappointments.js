function cambioaction() {
	val0=document.getElementsByName("Query")[0].checked;
	val1=document.getElementsByName("Query")[1].checked;
	val2=document.getElementsByName("Query")[2].checked;
	val3=document.getElementsByName("Query")[3].checked;
	formu=document.getElementById("formulario");
	if (val0==true || val3==true) {
	    formu.setAttribute("action","AllAppoinmentsList");
	} else if (val1==true || val2==true) {
		formu.setAttribute("action","ExchangeAndCustomizationAppointments");
		
	}
	
}