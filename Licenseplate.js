function comprobarLicensePlate() {    
    var data ="";

    var request = new XMLHttpRequest();
    var LicensePlate=document.getElementById("LicensePlate").value;
    request.open('GET', 'LicensePlateJSON?json&LicensePlate=' + LicensePlate, true);
    request.onload = function() {
      if (request.status >= 200 && request.status < 400) {
        var data = JSON.parse(request.responseText);
       
var dondeavisouser=document.getElementById("dondeaviso");




   if (data.LicensePlate!="null") {
var aviso=document.createElement("P");
dondeavisouser.appendChild(aviso);
       aviso.innerHTML="This Car Number already exists";
aviso.setAttribute("style","color:#fa1414");
} else {
   
dondeavisouser.innerHTML="";
}


 }
       
    };
    request.send();
}