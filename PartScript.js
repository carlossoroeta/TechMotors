function DelPart() {
	tabla=document.getElementById("lista");
	numfilasantes=tabla.getElementsByTagName("TR").length;
	tabla.deleteRow(numfilasantes-1);
	
	numfilas=tabla.getElementsByTagName("TR").length;
	preciototal=0;
		for (i=1;i<numfilas;i++) {
			fila=tabla.getElementsByTagName("TR")[i];
			col=fila.getElementsByTagName("TD")[3].innerHTML; 
			preciototal+=  parseFloat(col);
			
		}
		preciototal*=1.1;
		total=document.getElementById("total")
		total.setAttribute("value",preciototal);
}

function AddPart() {
	category=document.getElementById("Category").value;
	reference=document.getElementById("Reference").value;
	quantity=document.getElementById("Quantity").value;
	unitPrice=document.getElementById("PrecioPorUnidad").value;
	totalPrice=unitPrice*quantity;
	if (quantity!="" && unitPrice!="") {
		var table=document.getElementById("lista");
		var tr=document.createElement("TR");
		table.appendChild(tr);
		numfilas=table.getElementsByTagName("TR").length;
		tr.setAttribute("id",numfilas-1);
		var td1=document.createElement("TD");
		tr.appendChild(td1);
		td1.innerHTML=category;
		
		var td2=document.createElement("TD");
		tr.appendChild(td2);
		td2.innerHTML=reference;
		var inpref=document.createElement("INPUT");
		inpref.setAttribute("type","hidden");
		inpref.setAttribute("name","ref" + (numfilas-1));
		inpref.setAttribute("id","ref" + (numfilas-1));
		inpref.setAttribute("value",reference);
		td2.appendChild(inpref);
		
		var td3=document.createElement("TD");
		tr.appendChild(td3);
		td3.innerHTML=quantity;
		var inpquan=document.createElement("INPUT");
		inpquan.setAttribute("type","hidden");
		inpquan.setAttribute("name","quan" + (numfilas-1));
		inpquan.setAttribute("id","quan" + (numfilas-1));
		inpquan.setAttribute("value",quantity);
		td3.appendChild(inpquan);
		
		var td4=document.createElement("TD");
		tr.appendChild(td4);
		td4.innerHTML=totalPrice;
		
		
		
		
		
		
		preciototal=0;
		for (i=1;i<numfilas;i++) {
			fila=table.getElementsByTagName("TR")[i];
			col=fila.getElementsByTagName("TD")[3].innerHTML;
			preciototal+=  parseFloat(col);
			
			
		}
		preciototal*=1.1;
		total=document.getElementById("total")
		total.setAttribute("value",preciototal);
		document.getElementById("Quantity").value="";
    }    
}


