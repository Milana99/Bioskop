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
				
				 var btn = "<button class='btnUkloni btn btn-info' id = " + data[i]['id'] + ">Ukloni</button>";
	              row += "<td>" + btn + "</td>"; 
	              var btn1 = "<button class='btnIzmeni btn btn-info' id = " + data[i]['id'] + ">Izmeni</button>";
	              row += "<td>" + btn1 + "</td>"; 
	              row+="</tr>";
	             row+="<br>";
	             
	             $('#tabela').append(row);
	             $("#bioskopi").removeClass("d-none").show();
				 
			}
		},
			error:function(data){
			console.log("ERROR:",data);
		}
	});
});

$(document).on('click', '.btnUkloni', function () {           

    $.ajax({
        type: "GET",
        url: "http://localhost:3050/api/bioskopi/ukloni/" + this.id,  
        dataType: "json",
        success: function (data) {
            console.log("SUCCESS : ", data);
           alert("Uspesno,bioskop se i dalje nalazi na listi,ali njegov repertoar i sale su izbrisane");
           window.location.href="Bioskop.html"; 
        },
        error: function (data) {
        	alert("Greska");
            console.log("ERROR : ", data);
           
        }
    });
});

$(document).on('click', '.btnIzmeni', function () {        
	$("#repertoar").hide();
	$("#kek").empty();
	
	$.ajax({  //BioiskopController
		    type: "GET",
		    //uzimam podatke za gledaoca i od te terminske liste
		url: "http://localhost:3050/api/bioskop-izmeni/" + this.id, 
		dataType: "json",
		success: function (data){
			
            var red="Id odabranog bioskopa";
			
			red+="<div class='input-group form-group'><div class='input-group-prepend'><span class='input-group-text'><i class='fa fa-film'></i></span></div>";
            red+="<input type='text' class='form-control' id='bioskopId' placeholder='Izabrani id' value="+data['id']+"  disabled='disabled'></div>"
          
            
			document.getElementById("naziv").defaultValue =data['naziv'];
			document.getElementById("adresa").defaultValue =data['adresa'];
			document.getElementById("brojCentrale").defaultValue =data['brojCentrale'];
			document.getElementById("email").defaultValue =data['email'];
			$('#kek').append(red);
             $("#Izmena-bioskop").removeClass("d-none").show(); 
		},
		error: function (data) {
			alert("Neuspesno, pokusajte opet");
			 window.location.href="ProfilAdmin.html";
		    console.log("ERROR : ", data);
		    }
		});
});

//potvrda forme za imjenjivanje bioskopa
$(document).on('click', '#izmeni', function () {          
	event.preventDefault();
	$("#Izmena-bioskop").hide();
	
	var naziv=$("#naziv").val();
	var adresa=$("#adresa").val();
	var broj=$("#brojCentrale").val();
	var mail=$("#email").val()
	var id=$("#bioskopId").val();
	

   
	var newBioskopJSON=formToJSON3(naziv,adresa,broj,mail,id);
	$.ajax({
		type:"POST",
		url:"http://localhost:3050/api/bioskop/izmenjivanje",
		dataType:"json",
		contentType:"application/json",
		data:newBioskopJSON,
		success:function(data){
			alert("Uspesno ste uklonili bioskop!");
			window.location.href="Ukloni_Bioskop.html";
			
			
		},
		error:function(data){
			
			alert("Greska!");
			window.location.href="Ukloni_Bioskop.html";
        }
    });
});

function formToJSON3(naziv,adresa,broj,mail,id){
	return JSON.stringify({
		"naziv":naziv,
		"adresa":adresa,
		"brojCentrale":broj,
		"email":mail,
		"id":id
		
		
	});
}

