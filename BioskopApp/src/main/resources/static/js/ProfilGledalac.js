$(document).ready(function(){
	var loginKartica=$("#kartica").hide();
	var korisnickoIme=window.localStorage.getItem("korisnickoIme");
	var lozinka=window.localStorage.getItem("lozinka");
	var newKorisnikJSON=formToJSON(korisnickoIme,lozinka);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/get-korisnik",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			console.log("SUCCESS: ",data);
			window.localStorage.setItem("korisnickoIme",data['korisnickoIme']);
			window.localStorage.setItem("lozinka",data['lozinka']);
			
			$("#ime").append(data['ime']);
			$("#korisnicko").append(data['korisnickoIme']);
			$('#loz').append(data['lozinka']);
			$('#name').append(data['ime']);
			$('#prezime').append(data['prezime']);
			$('#telefon').append(data['kontaktTelefon']);
			$('#email').append(data['email']);
			$('#datum').append(data['datumRodjenja']);
			$('#role').append(data['uloga']);
			
			  var profil=$("#profil1").removeClass("d-none").show();
			  if(data['uloga']=="Administrator"){
				  var pod=$("#admin").removeClass("d-none").show();
			  }else if(data['uloga']=="Menadzer"){
				  var m="<button class='btnBioskopi btn-outline-info btn-lg btn-block' id="+data['id']+">Pregled bioskopa</button>";
				  m+="<a href='DodajFilm.html'  style='text-decoration : none'><button class='btnDodajFilm btn-outline-info btn-lg btn-block' id="+data['id']+">Dodavanje filma</button>";
					 
				   $("#menadzer").append(m);
				   $("#menadzer").removeClass("d-none").show();
			  }
			  else{
				  var rez="<button class='btnRezervisani btn-outline-info btn-lg btn-block' id="+data['id']+">Rezervisane karte</button>";
				   rez+="<button  class='btnSvi btn-outline-info btn-lg btn-block' id="+data['id']+">Svi odgledani filmovi</button>";
				   rez+="<button class='btnNeocenjeni btn-outline-info btn-lg btn-block' id="+data['id']+">Neocenjeni filmovi</button>";
				   rez+="<button class='btnOcenjeni btn-outline-info btn-lg btn-block' id="+data['id']+">Ocenjeni filmovi</button>";
				  
				 $("#gledalac").append(rez);
				  $("#gledalac").removeClass("d-none").show();
			  }
			
		},
		error:function(data){
			var loginKartica=$("#kartica").show();
			alert("Greska! Korisnik sa unetim podacima je neposotjeći");
		}
	});
});

function formToJSON(korisnickoIme,lozinka){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka
		
	});
}

$(document).on('click', '.btnRezervisani', function () {         
    $("#odgledaniFilmovi").hide();
    $("#ocenjeniFilmovi").hide();
    $("#ocenjivanje").hide();
    $("#neocenjeniFilmovi").hide();
    $(".sakrij").empty();

    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/gledalac-rezervisaneKarte/" + this.id,  
        dataType: "json",
        success: function (data) {
        
        	for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['datumOdrzavanja']+"</td>";
        		row+="<td>"+data[i]['vremePocetka']+"</td>";
        		row+="<td>"+data[i]['cena']+"</td>";
        		row+="<td>"+data[i]['brojRezervacija']+"</td>";
        		row+="<td>"+data[i]['bioskop']+"</td>";
        		row+="<td>"+data[i]['salaOznaka']+"</td>";
        		
        		
        		 var btn = "<button class='btnOtkazi btn btn-info' value="+data[i]['gledalacId']+" id= " + data[i]['id']+ ">Otkaži</button>";
	              row += "<td>" + btn + "</td>"; 

	              var btn1 = "<button class='btnPotvrdi btn btn-info' value="+data[i]['gledalacId']+" id= " + data[i]['id']+ ">Potvrdi kupovinu</button>";
		          row += "<td>" + btn1 + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela').append(row);
	              $("#listaKarte").removeClass("d-none").show();
	              
        	}                          
           
        },
        error: function (data) {
        	alert("Neuspesno, pokusajte opet!");
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '.btnSvi', function () {           
	  $("#listaKarte").hide();
	  $("#ocenjeniFilmovi").hide();
	  $("#neocenjeniFilmovi").hide();
	  $("#ocenjivanje").hide();
	  $(".sakrij").empty();
	  
    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/gledalac-odgledaniFilmovi/" + this.id,  
        dataType: "json",
        success: function (data) {
        	
        	for(i=0;i<data.length;i++){
        		var row="<tr class='sakrij'>";
        		row+="<td>"+data[i]['naziv']+"</td>";
        		row+="<td>"+data[i]['zanr']+"</td>";
        		row+="<td>"+data[i]['opis']+"</td>";
        		row+="<td>"+data[i]['trajanje']+"</td>";
        		row+="<td>"+data[i]['srednjaOcena']+"</td>";

	              row+="</tr>";
	              
	              $('#tabela1').append(row);
	              
	              $("#odgledaniFilmovi").removeClass("d-none").show();
				  
        	}                          
        },
        error: function (data) {
        	alert("Neuspesno, pokusajte opet");
            console.log("ERROR : ", data);
        }
    });
});

$(document).on('click', '.btnOcenjeni', function () {           
	  $("#listaKarte").hide();
	  $("#odgledaniFilmovi").hide();
	  $("#neocenjeniFilmovi").hide();
	  $(".sakrij").empty();
	  
  $.ajax({
      type: "GET",
      url: "http://localhost:3050/api/gledalac-ocenjeniFilmovi/" + this.id,  
      dataType: "json",
      success: function (data) {
     
      	for(i=0;i<data.length;i++){
      		var row="<tr class='sakrij'>";
      		row+="<td>"+data[i]['naziv']+"</td>";
      		row+="<td>"+data[i]['zanr']+"</td>";
      		row+="<td>"+data[i]['opis']+"</td>";
      		row+="<td>"+data[i]['trajanje']+"</td>";
      		row+="<td>"+data[i]['srednjaOcena']+"</td>";
			
	              row+="</tr>";
	              
	              $('#tabela2').append(row);
	              
	              $("#ocenjeniFilmovi").removeClass("d-none").show();
	              
      	}                          
         
      },
      error: function (data) {
      	alert("Neuspesno, pokusajte opet");
          console.log("ERROR : ", data);
      }
  });
});

$(document).on('click', '.btnNeocenjeni', function () {           
	  $("#listaKarte").hide();
	  $("#odgledaniFilmovi").hide();
	  $("#ocenjeniFilmovi").hide();
	  $("#ocenjivanje").hide();
	  $(".sakrij").empty();

$.ajax({
    type: "GET",
    url: "http://localhost:3050/api/gledalac-neocenjeniFilmovi/" + this.id, 
    dataType: "json",
    success: function (data) {
    	for(i=0;i<data.length;i++){
    		var row="<tr class='sakrij'>";
    		row+="<td>"+data[i]['naziv']+"</td>";
    		row+="<td>"+data[i]['zanr']+"</td>";
    		row+="<td>"+data[i]['opis']+"</td>";
    		row+="<td>"+data[i]['trajanje']+"</td>";
    		row+="<td>"+data[i]['srednjaOcena']+"</td>";
			
   		 var btn = "<button class='btnOceni btn btn-info' value="+data[i]['gledalacId']+"  id = " + data[i]['id'] + ">Oceni</button>";
             row += "<td>" + btn + "</td>"; 
             row+="</tr>";
			 
	              $('#tabela3').append(row);
	              
	              $("#neocenjeniFilmovi").removeClass("d-none").show();
	              
    	}                          
    },
    error: function (data) {
    	alert("Neuspesno, pokusajte opet");
        console.log("ERROR : ", data);
    }
});
});

$(document).on('click', '.btnOtkazi', function () {        

	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/gledalac-otkaziRezervaciju/" + this.id+"/"+this.value,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Uspesno uklonjena rezervacija");
		     window.location.href="ProfilGledalac.html"; 
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="Login.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnPotvrdi', function () {        

	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/gledalac-potvrdiRezervaciju/" + this.id+"/"+this.value,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Uspesno kupljena karta");
		     window.location.href="ProfilGledalac.html";

		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="Login.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnOceni', function () {        
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/gledalac-oceniFilm/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			//alert("Uspjesno ocijenjen film");
			var red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='podatakOcena' placeholder='Izabrani id' value="+data['id']+"  disabled='disabled'></div>"
             $('#filmic').append(red);
             $("#ocenjivanje").removeClass("d-none").show();

		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="ProfilGledalac.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '#oceniFilm', function () {        
	event.preventDefault();
	$("#kartica1").hide();
	
	var korisnickoIme=$("#korisnickoImeOcena").val();
	var lozinka=$("#lozinkaOcena").val();
	var ocena=$("#ocenaOcena").val();
	var id=$("#podatakOcena").val();
   
	var newKorisnikJSON=formToJSONOcena(korisnickoIme,lozinka,ocena,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/ocenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newKorisnikJSON,
		success:function(data){
			alert("Uspesno");
			window.location.href="ProfilGledalac.html";
			
		},
		error:function(data){
			
			alert("Greska! Korisnik sa unetim podacima je nepostojeci ili za datu projekciju nema vise slobodnih mesta. Pokusajte opet!!");
			window.location.href="Film.html";
        }
    });
});

function formToJSONOcena(korisnickoIme,lozinka,ocena,id){
	return JSON.stringify({
		"korisnickoIme":korisnickoIme,
		"lozinka":lozinka,
		"ocena":ocena,
		"id":id
		
	});
}

