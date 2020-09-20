$(document).on("submit","form",function(event){
	event.preventDefault();
	
	var bioskop=$("#nazivBioskopa").val();
	var film=$("#nazivFilma").val();
	var datum=$("#datumOdrzavanja").val();
	var pocetak=$("#vremePocetka").val();
	var cena=$("#cena").val();
	var sala=$("#oznakaSale").val();
	
	var newRasporedJSON=formToJSON(bioskop,film,datum,pocetak,cena,sala);
	
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/add-raspored",
		dataType:"json",
		contentType:"application/json",
		data:newRasporedJSON,
		success:function(){
			alert("Uspesno je dodat novi raspored");
			 window.location.href="ProfilMenadzer.html";
		
		},
		error:function(data){
			alert("Greska");
			
		}
	});
});

function formToJSON(bioskop,film,datum,pocetak,cena,sala){
	return JSON.stringify({
		"bioskop":bioskop,
		"naziv":film,
		"datumOdrzavanja":datum,
		"vremePocetka":pocetak,
		"cena":cena,
		"salaOznaka":sala
	});
}