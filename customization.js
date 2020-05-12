function SacarReferencia() {    
    var data ="";

    var request = new XMLHttpRequest();
	var categoriaescogida=document.getElementById("Category").value;
    request.open('GET', 'ReferenceJSON?json&type=Tunning&category=' + categoriaescogida, true);  
    request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        var data = JSON.parse(request.responseText);
        var selection=document.getElementById("Reference");
		selection.innerHTML="";
		
		for (i=0;i<data.length;i++) {
			var opcion=document.createElement("option");
			opcion.setAttribute("value",data[i].Reference);
			opcion.innerHTML=data[i].Reference;
			selection.appendChild(opcion);   
			
		}
		SacarDatos();
	  }
        
    };
    request.send();
}

function SacarDatos() { 
	  
	  var data ="";

	  var referenciaescogida=document.getElementById("Reference").value;
	  
      var request = new XMLHttpRequest();
      request.open('GET', 'CategoryReferenceJSON?json&type=Tunning&reference=' + referenciaescogida, true);
      request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
          var data = JSON.parse(request.responseText);
		  var precioEnsenado =document.getElementById("PrecioEnsenado");
		  precioEnsenado.setAttribute("value",data.PrecioVenta);
		  var precioUnidad =document.getElementById("PrecioPorUnidad");
		  precioUnidad.setAttribute("value",data.PrecioVenta);

		  
		  
		  
		  
     }
	  };
	  request.send();
  }