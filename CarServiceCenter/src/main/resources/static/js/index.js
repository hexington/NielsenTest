$(document).ready(function($) {
	$("#createLink").click(function(){
		hideErrors();
		$('#deleteDiv').hide();
		$('#retrieveDiv').hide();
		$('#retrieveDetails').hide();
		$('#updateDiv').hide();
		$('#rangeDiv').hide();
		$('#dynamicTable').hide();
		$('#createDiv').show();
		$('#createform').show();
		$('#appointmentId').hide();
		$('#createform')[0].reset();
	});
	
	$("#deleteLink").click(function(){
		hideErrors();
		$('#createDiv').hide();
		$('#retrieveDiv').hide();
		$('#retrieveDetails').hide();
		$('#updateDiv').hide();
		$('#rangeDiv').hide();
		$('#dynamicTable').hide();
		$('#deleteDiv').show();
		$('#deleteform').show();
		$('#deleteMsg').hide();
		$('#deleteform')[0].reset();
	});
	
	$("#retrieveLink").click(function(){
		hideErrors();
		$('#createDiv').hide();
		$('#deleteDiv').hide();
		$('#updateDiv').hide();
		$('#rangeDiv').hide();
		$('#retrieveDetails').hide();
		$('#dynamicTable').hide();
		$('#retrieveDiv').show();
		$('#retrieveform').show();
		$('#retrieveMsg').hide();
		$('#retrieveform')[0].reset();
	});
	
	$("#updateLink").click(function(){
		hideErrors();
		$('#createDiv').hide();
		$('#deleteDiv').hide();
		$('#retrieveDiv').hide();
		$('#rangeDiv').hide();
		$('#updateform').hide();
		$('#updateMsg').hide();
		$('#dynamicTable').hide();
		$('#updateapptform').show();
		$('#updateDiv').show();
		$('#updateform')[0].reset();
		$('#updateapptform')[0].reset();
	});
	
	$("#rangeLink").click(function(){
		hideErrors();
		$('#createDiv').hide();
		$('#deleteDiv').hide();
		$('#retrieveDiv').hide();
		$('#updateDiv').hide();
		$('#rangeMsg').hide();
		$('#dynamicTable').hide();
		$('#rangeDiv').show();
		$('#rangeform').show();
		$('#rangeform')[0].reset();
	});
	
	$("#createform").submit(function(e){
		 e.preventDefault();
		 enableButton(false);
		 var form = $(this);
		 if(!validateName($("#firstName").val())){
			 $("#firstNameErr").show();
			 return;
		 } else {
			 $("#firstNameErr").hide();
		 }
		 if(!validateName($("#lastName").val())){
			 $("#lastNameErr").show();
			 return;
		 } else {
			 $("#lastNameErr").hide();
		 }
		 if(!validateCar($("#car").val())){
			 $("#carErr").show();
			 return;
		 } else {
			 $("#carErr").hide();
		 }
		 if(!validateDate($("#dateOfAppt").val())){
			 $("#dateOfApptErr").show();
			 return;
		 } else {
			 $("#dateOfApptErr").hide();
		 }
		 if(!validateIsNumber($("#price").val())){
			 $("#priceErr").show();
			 return;
		 } else {
			 $("#priceErr").hide();
		 }
	     var json = { "firstName" : $('#firstName').val(), 
	    		 	  "lastName" : $('#lastName').val(),
	    		 	  "car": $('#car').val(),
	    		 	  "dateOfAppt": $('#dateOfAppt').val(),
	    		 	  "price": $('#price').val(),
	    		 	  "statusOfAppt": $('#statusOfAppt').val()
	    		 	 };
		 $.ajax({
				type : form.attr("method"),
				url : form.attr("action"),
				dataType : 'json',
				data : JSON.stringify(json),
				timeout : 100000,
				beforeSend: function(xhr) {
		            xhr.setRequestHeader("Accept", "application/json");
		            xhr.setRequestHeader("Content-Type", "application/json");
		        },
				success : function(data) {
					$('#createform').hide();
					$('#appointmentId').html(data["resultMsg"]);
					$('#appointmentId').show();
				},
				error : function(e) {
					$('#createform').hide();
					$('#appointmentId').html("There has been an error.");
					$('#appointmentId').show();
				},
				done : function(e) {
					enableButton(true);
				}
		});
	});
	
	$("#deleteform").submit(function(e){
		 e.preventDefault();
		 enableButton(false);
		 var form = $(this);
		 if(!validateIsNumber($("#deleteAppt").val())){
			 $("#deleteErr").show();
			 return;
		 } else {
			 $("#deleteErr").hide();
		 }
		 $.ajax({
				type : form.attr("method"),
				url : form.attr("action") + "/" + $("#deleteAppt").val(),
				timeout : 100000,
				success : function(data) {
					$('#deleteform').hide();
					$('#deleteMsg').html(data["resultMsg"]);
					$('#deleteMsg').show();
				},
				error : function(e) {
					$('#deleteform').hide();
					$('#deleteMsg').html("There has been an error.");
					$('#deleteMsg').show();
				},
				done : function(e) {
					enableButton(true);
				}
		});
	});
	
	$("#retrieveform").submit(function(e){
		 e.preventDefault();
		 enableButton(false);
		 var form = $(this);
		 if(!validateIsNumber($("#retrieveAppt").val())){
			 $("#retrieveErr").show();
			 return;
		 } else {
			 $("#retrieveErr").hide();
		 }
		 $.ajax({
				type : form.attr("method"),
				url : form.attr("action") + "/" + $("#retrieveAppt").val(),
				timeout : 100000,
				success : function(data) {
					$('#retrieveform').hide();
					if(!data["successful"]){
						$('#retrieveMsg').html(data["resultMsg"]);
						$('#retrieveMsg').show();
					} else{
						$("#retrieveApptId").html(data["appointmentId"]);
						$("#retrieveFirstName").html(data["firstName"]);
						$("#retrieveLastName").html(data["lastName"]);
						$("#retrieveCar").html(data["car"]);
						$("#retrieveApptDate").html(data["dateOfAppt"]);
						$("#retrievePrice").html(data["price"]);
						$("#retrieveStatus").html(data["statusOfAppt"]);
						$("#retrieveDetails").show();
					}
				},
				error : function(e) {
					$('#retrieveform').hide();
					$('#retrieveMsg').html("There has been an error.");
					$('#retrieveMsg').show();
				},
				done : function(e) {
					enableButton(true);
				}
		});
	});
	
	$("#updateapptform").submit(function(e){
		 e.preventDefault();
		 enableButton(false);
		 var form = $(this);
		 if(!validateIsNumber($("#updateAppt").val())){
			 $("#updateErr").show();
			 return;
		 } else {
			 $("#updateErr").hide();
		 }
		 $.ajax({
				type : form.attr("method"),
				url : form.attr("action") + "/" + $("#updateAppt").val(),
				timeout : 100000,
				success : function(data) {
					$('#retrieveform').hide();
					if(!data["successful"]){
						$('#updateapptform').hide();
						$('#updateMsg').html(data["resultMsg"]);
						$('#updateMsg').show();
					} else{
						$("#updateAppointmentId").val(data["appointmentId"]);
						$("#updateFirstName").val(data["firstName"]);
						$("#updateLastName").val(data["lastName"]);
						$("#updateCar").val(data["car"]);
						$("#updateDateOfAppt").val(data["dateOfAppt"]);
						$("#updatePrice").val(data["price"]);
						$("#updateStatusOfAppt").val(data["statusOfAppt"]);
						$("#updateapptform").hide();
						$("#updateform").show();
					}
				},
				error : function(e) {
					$('#updateapptform').hide();
					$('#updateMsg').html("There has been an error.");
					$('#updateMsg').show();
				},
				done : function(e) {
					enableButton(true);
				}
		});
	});
	
	$("#updateform").submit(function(e){
		 e.preventDefault();
		 enableButton(false);
		 var form = $(this);
		 if(!validateName($("#updateFirstName").val())){
			 $("#updateFirstNameErr").show();
			 return;
		 } else {
			 $("#updateFirstNameErr").hide();
		 }
		 if(!validateName($("#updateLastName").val())){
			 $("#updateLastNameErr").show();
			 return;
		 } else {
			 $("#updateLastNameErr").hide();
		 }
		 if(!validateCar($("#updateCar").val())){
			 $("#updateCarErr").show();
			 return;
		 } else {
			 $("#updateCarErr").hide();
		 }
		 if(!validateDate($("#updateDateOfAppt").val())){
			 $("#updateDateOfApptErr").show();
			 return;
		 } else {
			 $("#updateDateOfApptErr").hide();
		 }
		 if(!validateIsNumber($("#updatePrice").val())){
			 $("#updatePriceErr").show();
			 return;
		 } else {
			 $("#updatePriceErr").hide();
		 }
	     var json = { "appointmentId" : $('#updateAppointmentId').val(), 
	    		 	  "firstName" : $('#updateFirstName').val(), 
	    		 	  "lastName" : $('#updateLastName').val(),
	    		 	  "car": $('#updateCar').val(),
	    		 	  "dateOfAppt": $('#updateDateOfAppt').val(),
	    		 	  "price": $('#updatePrice').val(),
	    		 	  "statusOfAppt": $('#updateStatusOfAppt').val()
	    		 	 };
		 $.ajax({
				type : form.attr("method"),
				url : form.attr("action"),
				dataType : 'json',
				data : JSON.stringify(json),
				timeout : 100000,
				beforeSend: function(xhr) {
		            xhr.setRequestHeader("Accept", "application/json");
		            xhr.setRequestHeader("Content-Type", "application/json");
		        },
				success : function(data) {
					$('#updateform').hide();
					$('#updateMsg').html(data["resultMsg"]);
					$('#updateMsg').show();
				},
				error : function(e) {
					$('#updateform').hide();
					$('#updateMsg').html("There has been an error.");
					$('#updateMsg').show();
				},
				done : function(e) {
					enableButton(true);
				}
		});
	});
	
	$("#rangeform").submit(function(e){
		 e.preventDefault();
		 enableButton(false);
		 var form = $(this);
		 if(!validateDate($("#firstDate").val())){
			 $("#firstDateErr").show();
			 return;
		 } else {
			 $("#firstDateErr").hide();
		 }
		 if(!validateDate($("#secondDate").val())){
			 $("#secondDateErr").show();
			 return;
		 } else {
			 $("#secondDateErr").hide();
		 }
		 $.ajax({
				type : form.attr("method"),
				url : form.attr("action") + "?firstDate=" + $('#firstDate').val() + "&secondDate=" + $('#secondDate').val(),
				timeout : 100000,
				success : function(data) {
					if(data.length == 0){
						$('#dynamicTable').hide();
						$('#rangeform').hide();
						$('#rangeMsg').html("There are no matching appointments.");
						$('#rangeMsg').show();
					} else {
						$('#dynamicTable').html("");
						$('#dynamicTable').append('<table class=\"center\" id=\"rangeTable\"><tbody><tr>' +
						  '<th>Appointment Id</th><th>First Name</th><th>Last Name</th><th>Car</th><th>Date Of Appointment</th><th>Price</th><th>Status Of Appointment</th>' +
						  '</tr></tbody></table>');
						$(data).each(function(index) {
							$('#rangeTable').append('<tr><td>' + data[index].appointmentId + '</td>' + 
									  				'<td>' + data[index].firstName + '</td>' + 
									  				'<td>' + data[index].lastName + '</td>' +
									  				'<td>' + data[index].car + '</td>' +
									  				'<td>' + data[index].dateOfAppt + '</td>' +
									  				'<td>' + data[index].price + '</td>' +
									  				'<td>' + data[index].statusOfAppt + '</td></tr>');
						});
						$('#dynamicTable').show();
					}
				},
				error : function(e) {
					$('#rangeform').hide();
					$('#rangeMsg').html("There has been an error.");
					$('#rangeMsg').show();
				},
				done : function(e) {
					enableButton(true);
				}
		});
	});

});

function validateIsNumber(input){
	var reg = new RegExp('^[0-9]+$');
	if(!input.match(reg)){
		return false;
	}
	
	return true;
};

function validateDate(testdate) {
    var date_regex = /^(0?[1-9]|1[0-2])\/(0?[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/ ;
    return date_regex.test(testdate);
};

function validateName(name) {
    var name_regex = /^\s*[a-zA-Z][a-zA-Z '-]*$/ ;
    return name_regex.test(name);
};

function validateCar(car) {
    var car_regex = /^\s*[0-9a-zA-Z][0-9a-zA-Z '-]*$/ ;
    return car_regex.test(car);
};

function hideErrors(){
	$("#firstNameErr").hide();
	$("#lastNameErr").hide();
	$("#dateOfApptErr").hide();
	$("#priceErr").hide();
	$("#carErr").hide();
	$("#deleteErr").hide();
	$("#updateErr").hide();
	$("#retrieveErr").hide();
	$("#updateFirstNameErr").hide();
	$("#updateLastNameErr").hide();
	$("#updateCarErr").hide();
	$("#updateDateOfApptErr").hide();
	$("#updatePriceErr").hide();
	$("#firstDateErr").hide();
	$("#secondDateErr").hide();
};

function enableButton(flag) {
	$("#createBtn").prop("disabled", flag);
	$("#deleteBtn").prop("disabled", flag);
	$("#retrieveBtn").prop("disabled", flag);
	$("#updateGetBtn").prop("disabled", flag);
	$("#updateBtn").prop("disabled", flag);
	$("#rangeBtn").prop("disabled", flag);
};