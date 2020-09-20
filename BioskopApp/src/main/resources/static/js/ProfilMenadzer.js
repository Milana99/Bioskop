//prikaz menadzera
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
//MENADZER - MENADZER KONTROLER

$(document).on('click', '.btnBioskopi', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#sale").hide();
	$("#repertoar").hide();
	$(".sakrij").empty();
	$("#Izmena-sala").hide();
	$("#Izmena-repertoar").hide();
	
	$.ajax({
		 type: "GET",
		url: "http://localhost:3050/api/bioskopi/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data) {
			for(i=0;i<data.length;i++){
				var row="<tr class='sakrij'>";
				row+="<td>"+data[i]['naziv']+"</td>";
				row+="<td>"+data[i]['adresa']+"</td>";
				row+="<td>"+data[i]['brojCentrale']+"</td>";
				row+="<td>"+data[i]['eMail']+"</td>";
		
				var btn = "<button class='btnSale btn btn-info' id = " + data[i]['id'] + ">Pregled sala</button>";
				var btn1 = "<button class='btnRepertoar btn btn-info' id = " + data[i]['id'] + ">Repertoar</button>";
				row += "<td>" + btn + "</td>"; 
				row += "<td>" + btn1+ "</td>"; 
		        row+="</tr>";
		         
		              
		              $('#tabela4').append(row);
		              
		              $("#bioskopi").removeClass("d-none").show();
		              
			}                          
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnSale', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$("#repertoar").hide();
	$(".sakrij").empty();
	$("#Izmena-sala").hide();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:3050/api/sale/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data) {
		
			for(i=0;i<data.length;i++){
				var row="<tr class='sakrij'>";
				row+="<td>"+data[i]['bioskop']+"</td>";
				row+="<td>"+data[i]['oznakaSale']+"</td>";
				row+="<td>"+data[i]['kapacitet']+"</td>";
				
				var btn = "<button class='btnIzmeniSalu btn btn-info' id = " + data[i]['id'] + ">Izmeni</button>";
				var btn1 = "<button class='btnUkloniSalu btn btn-info' id = " + data[i]['id'] + ">Ukloni</button>";
	             row += "<td>" + btn + "</td>"; 
	             row += "<td>" + btn1 + "</td>"; 
			
		
		        row+="</tr>";
		         
		        $('#tabela5').append(row);
				$("#sale").removeClass("d-none").show(); 
          
			} 
			var btn2 = "<a href='DodajSalu.html'><button class='btnDodajSalu btn btn-info'>Dodaj novu salu</button></a>";
            
			row="<tr class='sakrij'><td colspan='5'>"+btn2+"</td></tr>";
			 $('#tabela5').append(row);
			$("#sale").removeClass("d-none").show();
			
		   
		},
		error: function (data) {
			alert("Nije  pronadjena nijedna sala!");
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '.btnUkloniSalu', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$("#repertoar").hide();
	$("#sale").hide();
	$(".sakrij").empty();
	$("#Izmena-sala").hide();
	
	$.ajax({
		type: "GET",
		url: "http://localhost:3050/api/saleUkloni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			alert("Uspesno uklonjena sala!");
		},
		error: function (data) {
			alert("Neuspesno, pokusajte ponovo!");
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnIzmeniSalu', function () {        
		$("#kek").empty();
		$("#Izmena-sala").hide();
	
	$.ajax({
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/sala-izmeni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){
			
			document.getElementById("kapacitet").defaultValue =data['kapacitet'];
			document.getElementById("oznakaSale").defaultValue =data['oznakaSale'];
            
		var	red="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='salaId' placeholder='Izabrani id' value="+data['id']+"  disabled='disabled'></div>"
             $('#kek').append(red);
             $("#Izmena-sala").removeClass("d-none").show();
			 
			
		   
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="ProfilMenadzer.html";
		    console.log("ERROR : ", data);
		    }
		});
});


$(document).on('click', '#izmeni1', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#Izmena-sala").hide();
	
	var kapacitet=$("#kapacitet").val();
	var oznakaSale=$("#oznakaSale").val();
	var idSale=$("#salaId").val();
   
	var newSalaJSON=formToJSON2(kapacitet,oznakaSale,idSale);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/sala/izmenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newSalaJSON,
		success:function(data){
			alert("Uspesno ste izmenili salu!");
			$("#sale").hide(); 
			window.location.href="ProfilMenadzer.html";
			
			
		},
		error:function(data){
			
			alert("Greska!");
			window.location.href="ProfilMenadzer.html";
        }
    });
});

function formToJSON2(kapacitet,oznakaSale,id){
	return JSON.stringify({
		"kapacitet":kapacitet,
		"oznakaSale":oznakaSale,
		"id":id
		
	});
}

$(document).on('click', '.btnRepertoar', function () {            // kada je button (čija je class = btnSeeMore) kliknuti
	$("#bioskopi").hide(); 
	$(".sakrij").empty();
	  $("#Izmena-repertoar").hide();
	
	$.ajax({
		    type: "GET",
		url: "http://localhost:3050/api/repertoar/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
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
				
        		 var btn = "<button class='btnIzmeniRepertoar btn btn-info' id = " + data[i]['id'] + ">Izmeni</button>";
	              row += "<td>" + btn + "</td>"; 
	              row+="</tr>";
	              
	              $('#tabela6').append(row);
	             
	              $("#repertoar").removeClass("d-none").show();
        	}       
			var btn2 = "<a href='DodajRaspored.html'><button class='btnDodajRepertoar btn btn-info'>Dodaj novi raspored(repertoar)</button></a>";
            
			row="<tr class='sakrij'><td colspan='8'>"+btn2+"</td></tr>";
			 $('#tabela6').append(row);
			$("#repertoar").removeClass("d-none").show();
			 

		},
		error: function (data) {
			alert("Neuspesno,ovaj bioskop nema dostupnih projekcija");
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '.btnIzmeniRepertoar', function () {        
	//$("#repertoar").hide();
	  $('#hehe').empty();
	  $("#Izmena-repertoar").hide();
		 
	
	$.ajax({  //BioiskopController
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/repertoar-izmeni/" + this.id,  // this.id je button id, a kao button id je postavljen id zaposlenog
		dataType: "json",
		success: function (data){

			document.getElementById("naziv").defaultValue =data['naziv'];
			document.getElementById("datumOdrzavanja").defaultValue =data['datumOdrzavanja'];
			document.getElementById("vremePocetka").defaultValue =data['vremePocetka'];
			document.getElementById("cena").defaultValue =data['cena'];
			document.getElementById("salaOznaka").defaultValue =data['salaOznaka'];

            var red="Id repertoara";
			red+="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='repertoarId' placeholder='Izabrani id' value="+data['id']+"  disabled='disabled'></div>"
             $('#hehe').append(red);
             $("#Izmena-repertoar").removeClass("d-none").show();

		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="ProfilMenadzer.html";
		    console.log("ERROR : ", data);
		    }
		});
});

$(document).on('click', '#izmeni2', function () {            // kada je button (čija je class = btnSeeMore) kliknut
	event.preventDefault();
	$("#Izmena-repertoar").hide();
	
	var naziv=$("#naziv").val();
	var datum=$("#datumOdrzavanja").val();
	var vreme=$("#vremePocetka").val();
	var cena=$("#cena").val();
	var sala=$("#salaOznaka").val();
	var id=$("#repertoarId").val()
	
	

   
	var newRepertoarJSON=formToJSON3(naziv,datum,vreme,cena,sala,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/repertoar/izmenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newRepertoarJSON,
		success:function(data){
			alert("Uspesno");
			window.location.href="ProfilMenadzer.html";
		},
		error:function(data){
			
			alert("Greska! Uneli ste nepostojeci film ili salu. Pokusajte ponovo");
		
        }
    });
});

function formToJSON3(naziv,datum,vreme,cena,sala,id){
	return JSON.stringify({
		"naziv":naziv,
		"datumOdrzavanja":datum,
		"vremePocetka":vreme,
		"cena":cena,
		"salaOznaka":sala,
		"id":id
	});
}



