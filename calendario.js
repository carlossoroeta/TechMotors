appointment=document.getElementById("appointment").value;
vehicle=document.getElementById("vehicle").value;
garage=document.getElementById("garage").value;
//Arrays de datos:
meses=["January","February","March","April","May","June","July","August","September","October","November","December"];
lasemana=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"]
diassemana=["mon","tue","wed","thu","fri","sat","sun"];
//Tras cargarse la página ...
window.onload = function() {
//fecha actual
hoy=new Date(); //objeto fecha actual
diasemhoy=hoy.getDay(); //dia semana actual
diahoy=hoy.getDate(); //dia mes actual
meshoy=hoy.getMonth(); //mes actual
annohoy=hoy.getFullYear(); //año actual
// Elementos del DOM: en cabecera de calendario 
tit=document.getElementById("titulos"); //cabecera del calendario
ant=document.getElementById("anterior"); //mes anterior
pos=document.getElementById("posterior"); //mes posterior
// Elementos del DOM en primera fila
f0=document.getElementById("fila0");
//Pie de calendario
pie=document.getElementById("fechaactual");
pie.innerHTML+=lasemana[diasemhoy]+", "+ meses[meshoy] +" "+ diahoy + "th , "+annohoy;
//formulario: datos iniciales:

// Definir elementos iniciales:
mescal = meshoy; //mes principal
annocal = annohoy //año principal
//iniciar calendario:
cabecera() 
primeralinea()
escribirdias()
}
//FUNCIONES de creación del calendario:
//cabecera del calendario
function cabecera() {
         tit.innerHTML=meses[mescal]+" of "+annocal;
         mesant=mescal-1; //mes anterior
         mespos=mescal+1; //mes posterior
         if (mesant<0) {mesant=11;}
         if (mespos>11) {mespos=0;}
         ant.innerHTML=meses[mesant]
         pos.innerHTML=meses[mespos]
         } 
//primera línea de tabla: días de la semana.
function primeralinea() {
         for (i=0;i<7;i++) {
             celda0=f0.getElementsByTagName("th")[i];
             celda0.innerHTML=diassemana[i]
             }
         }
//rellenar celdas con los días
function mesdespues() {
         nuevomes=new Date() //nuevo obejto fecha
         tiempounix=primeromes.getTime() //fecha de primero mes
         tiempounix=tiempounix+(45*24*60*60*1000) //le añadimos 45 días 
         nuevomes.setTime(tiempounix) //fecha con mes posterior.
         mescal=nuevomes.getMonth() //cambiamos variables 
         annocal=nuevomes.getFullYear()
         cabecera() //escribir la cabecera 
         escribirdias() //escribir la tabla
         }
		 //Ver mes anterior
function mesantes() {
         nuevomes=new Date() //nuevo objeto de fecha
         primeromes--; //Restamos un día al 1 del mes visualizado
         nuevomes.setTime(primeromes) //cambiamos fecha al mes anterior
         mescal=nuevomes.getMonth() //cambiamos las variables que usarán las funciones
         annocal=nuevomes.getFullYear()
         cabecera() //llamada a funcion de cambio de cabecera
         escribirdias() //llamada a funcion de cambio de tabla.
         }
function escribirdias() {
         //Buscar dia de la semana del dia 1 del mes:
         primeromes=new Date(annocal,mescal,"1") //buscar primer día del mes
         prsem=primeromes.getDay() //buscar día de la semana del día 1
         prsem--; //adaptar al calendario español (empezar por lunes)
         if (prsem==-1) {prsem=6;}
         //buscar fecha para primera celda:
         diaprmes=primeromes.getDate() 
         prcelda=diaprmes-prsem; //restar días que sobran de la semana
         empezar=primeromes.setDate(prcelda) //empezar= tiempo UNIX 1ª celda
         diames=new Date() //convertir en fecha
         diames.setTime(empezar); //diames=fecha primera celda.
         //Recorrer las celdas para escribir el día:
         for (i=1;i<7;i++) { //localizar fila
             fila=document.getElementById("fila"+i);
             for (j=0;j<7;j++) {
                 midia=diames.getDate() 
                 mimes=diames.getMonth()
                 mianno=diames.getFullYear()
                 celda=fila.getElementsByTagName("td")[j];
                 celda.innerHTML="<a href='elegirCita?id=" + midia + "&id2=" + (mimes+1) + "&id3=" + mianno + "&id4=" + appointment + "&id5=" + vehicle + "&id6=" + garage +"'>"+ midia +"</a>";
                 //Recuperar estado inicial al cambiar de mes:
                 celda.style.backgroundColor="#9bf5ff";
                 celda.style.color="#492736";
                 //domingos en rojo
                 if (j==6) { 
                    celda.style.color="#f11445";
                    }
                 //dias restantes del mes en gris
                 if (mimes!=mescal) { 
                    celda.style.color="#a0babc";
                    }
                 //destacar la fecha actual
                 if (mimes==meshoy && midia==diahoy && mianno==annohoy ) { 
                    celda.style.backgroundColor="#f0b19e";
                    celda.innerHTML="<cite title='Fecha Actual'>"+midia+"</cite>";
					celda.innerHTML="<a href='elegirCita?id=" + midia + "&id2=" + (mimes+1) + "&id3=" + mianno + "&id4=" + appointment + "&id5=" + vehicle + "&id6=" + garage +"'>"+ midia +"</a>";
                    }
                 //pasar al siguiente día
                 midia=midia+1;
                 diames.setDate(midia); 
                 }
             }
         }
