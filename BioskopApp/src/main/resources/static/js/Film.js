//prikaz filmova
$(document).ready(function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:3050/api/filmovi",
		dataType:"json",
		success:function(data){
			
			
			console.log("SUCESS:",data);
			
			for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a href='#' class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg' alt='Movie'></a>";
				col+="<div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['srednjaOcena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#table').append(col);
			}
			
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});



$(document).on('click', '.sortNaziv', function () {           
    var employeesDiv = $("#table");                    
    employeesDiv.hide();   
    $("#sortiraniOcena").hide();
    $("#sortiraniTrajanje").hide();


    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/sortNaziv", 
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg'></a>";
				col+="<div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['srednjaOcena']+"</td></p>";
				
				col+="</div></div></div>";
				
				$('#sortirani').append(col);
				$('#sortirani').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});


$(document).on('click', '.sortOcena', function () {            
    var employeesDiv = $("#table");                      
    employeesDiv.hide(); 
    $("#sortirani").hide();
    $("#sortiraniTrajanje").hide();


    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/sortOcena",  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg'></a>";
				col+="<div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['srednjaOcena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#sortiraniOcena').append(col);
				$('#sortiraniOcena').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});

$(document).on('click', '.sortTrajanje', function () {             
    var employeesDiv = $("#table");                     
    employeesDiv.hide();       
    $("#sortirani").hide();
    $("#sortiraniOcena").hide();

    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/sortTrajanje",  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<a class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg'></a>";
				col+="<div class=\"card-body\"> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
				col+="<br><b>Trajanje:</b> <td>"+data[i]['trajanje']+"</td>min";
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['srednjaOcena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#sortiraniTrajanje').append(col);
				$('#sortiraniTrajanje').show();
			}
			    
		},
		error:function(data){
			console.log("ERROR:",data);
		}
    });
});


$(document).on('click', '.btnSlika', function () {           
	$(".top").hide();
	$("#kartica1").hide();

	$.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/" + this.id,  
        dataType: "json",
        success: function (data) {        	
        	for(i=0;i<data.length;i++){

				var row="<tr>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datumOdrzavanja']+"</td>";
        		row+="<td>"+data[i]['vremePocetka']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['brojRezervacija']+"</td>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['salaOznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnRezervisi btn btn-info' id = " + data[i]['id'] + ">Rezerviši</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela').append(row);
	              $("#filmPrikaz").removeClass("d-none").show();
	              
        	}   
        },
        error: function (data) {
        	alert("Za izabrani film jos uvek nema projekcija");
        	window.location.href="Film.html";
            console.log("ERROR : ", data);
        }
    });
});



$(document).on('click', '.btnRezervisi', function () {            
	//$("#lista").hide();
	$("#kartica1").hide();
        $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/rezervisi/" + this.id,  
        dataType: "json",
        success: function (data) {

        		var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
	             red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrnai id' value="+data['id']+"  disabled='disabled'></div>"
	              $('#filmic').append(red);
	              $("#kartica1").removeClass("d-none").show();                     
           
        },
        error: function (data) {
        	alert("Neuspesno, pokusajte opet!");
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '#rezervacija', function () {          
	event.preventDefault();
	$("#kartica1").hide();
	
	var korisnickoIme=$("#korisnickoIme").val();
	var lozinka=$("#lozinka").val();
	var id=$("#podatak").val();
   
	var newKorisnikJSON=formToJSON1(korisnickoIme,lozinka,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/filmovi/rezervacija",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			alert("Uspesno ste rezervisali!");
			window.location.href="Film.html";
			
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unetim podacima je nepostojeci ili za datu projekciju nema vise slobodnih mjesta. Pokusajte opet!");
			window.location.href="Film.html";
        }
    });
});

function formToJSON1(korisnickoIme,lozinka,id){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka,
		"id":id
		
	});
}
