$(document).on("submit","form",function(event){
	event.preventDefault();
	$("#kartica").hide();
	$("#kartica1").hide();
	
	var naziv=$("#naziv").val();
	var zanr=$("#zanr").val();
	var opis=$("#opis").val();
	var srednjaOcena=$("#srednja_ocena").val();
	var cena=$("#cena").val();
	var datumOdrzavanja=$("#datum").val();
	
	
	var newFilmJSON=formToJSON(naziv,zanr,opis,srednjaOcena,cena,datumOdrzavanja);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/filmovi/pretraga",
		dataType:"json",
		contentType:"application/json",
		data:newFilmJSON,
		success:function(data){
			for(i=0;i<data.length;i++){
				var col=" <div class=\" col-sm-6 col-lg-3\" style=\"float:left\">"
				col+="<div class=\"card mb-2\">";
				col+="<a href='#' class='btnSlika' id=" + data[i]['id'] + "><img class=\"card-img-top\" src='images/"+data[i]['naziv']+".jpg' alt='movie'></a>";
				col+="<div class=\"card-body\" id='filmKartica'> <h4 class=\"card-title\">"+data[i]['naziv']+"</h4>";
				col+="<p class=\"card-text\"><b>Žanr:</b>  <td>"+data[i]['zanr']+"</td>";
	
				col+="<br><b>Opis:</b><td>"+data[i]['opis']+"</td>";
				col+="<br><b>Ocena:</b><td>"+data[i]['srednjaOcena']+"</td></p>";
				col+="</div></div></div>";
				
				$('#table').append(col);
				$("#lista").removeClass("d-none").show();
			}
			
		},
		error:function(data){
			alert("Nema pronadjenih filmova!");
			window.location.href="Pretraga_Filmova.html";
			
		}
		
	});
	
});

function formToJSON(naziv,zanr,opis,srednjaOcena,cena,datumOdrzavanja){
	return JSON.stringify({
		"naziv":naziv,
		"zanr":zanr,
		"opis":opis,
		"srednjaOcjena":srednjaOcena,
		"cijena":cena,
		"datumOdrzavanja":datumOdrzavanja
	});
}

$(document).on('click', '.btnSlika', function () {           
	$("#lista").hide();
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
        	alert("Za izabrani film jos uvek nepostoji projekcija!");
        	window.location.href="Pretraga.html";
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '.btnRezervisi', function () {            
	$("#lista").hide();
	$("#filmic").empty();
	$("#kartica1").hide();
        $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/filmovi/rezervisi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	
        		
        		var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
	             red+="<input type='text' class='form-control' id='podatak' placeholder='Izabrani id' value="+data['id']+"></div>"
	              $('#filmic').append(red);
	              $("#kartica1").removeClass("d-none").show();
 
        },
        error: function (data) {
        	alert("Neuspesno, pokusajte opet");
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
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unetim podacima je neposotjeći");
			window.location.href="Pretraga.html";
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