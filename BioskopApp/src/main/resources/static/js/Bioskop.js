//prikaz svih bioskopa
$(document).ready(function(){
	$.ajax({
		type:"GET",
		url:"http://localhost:3050/api/bioskopi",
		dataType:"json",
		success:function(data){
			console.log("SUCCESS:",data);
			for(i=0;i<data.length;i++){
				var row="<tr>";
			
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['adresa']+"</td>";
				row+="<td>"+data[i]['brojCentrale']+"</td>";
				row+="<td>"+data[i]['eMail']+"</td>";
				
				 var btn = "<button class='btnRepertoar btn btn-info' id = " + data[i]['id'] + ">Repertoar</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	             row+="<br>";
	             
	             $('#tabela').append(row);

			}
		},
		error:function(data){
			console.log("ERROR:",data);
		}
	});
});

//terminske liste kad se klikne na dugme repertoar
$(document).on('click', '.btnRepertoar', function () {           
	$("#bioskopi").hide(); 
	$(".sakrij").empty();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:3050/api/repertoar/" + this.id,  
		dataType: "json",
		success: function (data){
			for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datumOdrzavanja']+"</td>";
        		row+="<td>"+data[i]['vremePocetka']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['brojRezervacija']+"</td>";
        		row+="<td>"+data[i]['salaOznaka']+"</td>";
        		
        		 var btn = "<button class='btnRezervisi btn btn-info' id = " + data[i]['id'] + ">Rezerviši</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela1').append(row);
	             
	              $("#repertoar").removeClass("d-none").show();
        	}       
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnRezervisi', function () {            
	$("#kartica1").hide();
        $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/rezervisi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	
        	
        	//deo koji se doda da bi se zapamtio id od izabrane terminske liste
        		
        		var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
	             red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrani id' value="+data['id']+"  disabled='disabled'></div>"
	              $('#filmic').append(red);
	              $("#kartica1").removeClass("d-none").show();                       
        },
        error: function (data) {
        	alert("Neuspesno, pokusajte opet!");
            console.log("ERROR : ", data);
        }
    });
});

//kada se klikne na dugme rezervisi nakon popunjene forme
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
			window.location.href="Bioskop.html";
			
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unetim podacima je nepostojeci ili za datu projekciju nema vise slobodnih mesta. Pokusajte opet!");
			window.location.href="Bioskop.html";
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
