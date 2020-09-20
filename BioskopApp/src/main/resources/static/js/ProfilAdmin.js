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