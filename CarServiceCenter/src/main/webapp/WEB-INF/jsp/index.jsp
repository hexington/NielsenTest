<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><spring:message code="app.header"/></title>

<link rel="stylesheet" type="text/css" href="css/index.css">

<script src="https://code.jquery.com/jquery-3.2.1.js" 
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
  	crossorigin="anonymous"></script>
  
 <script src="js/index.js"></script>
</head>
<body>
	<h1 class="center"><spring:message code="app.header"/></h1>

	<div id="container">
		<div class="left">
			<div id="createLink" class="pseudo-link"><spring:message code="app.link.createLink"/></div><br/>
			<div id="deleteLink" class="pseudo-link"><spring:message code="app.link.deleteLink"/></div><br/>
			<div id="retrieveLink" class="pseudo-link"><spring:message code="app.link.retrieveLink"/></div><br/>
			<div id="updateLink" class="pseudo-link"><spring:message code="app.link.updateLink"/></div><br/>
			<div id="rangeLink" class="pseudo-link"><spring:message code="app.link.rangeLink"/></div>
		</div>
		<div class="right">
			<div id="createDiv">
				<form action="createAppointment" method="POST" id="createform">
					<div>
						<label for="firstName">First Name:</label>
						<div>
							<input name="firstName" id="firstName" />
						</div>
						<div id="firstNameErr">Enter valid name</div>
					</div>
					<br/>
					<div>
						<label for="lastName">Last Name:</label>
						<div>
							<input name="lastName" id="lastName" />
						</div>
						<div id="lastNameErr">Enter valid name</div>
					</div>
					<br/>
					<div>
						<label for="car">Car:</label>
						<div>
							<input name="car" id="car" />
						</div>
						<div id="carErr">Enter valid car</div>
					</div>
					<br/>
					<div>
						<label for="dateOfAppt">Appointment Date:</label>
						<div>
							<input name="dateOfAppt" id="dateOfAppt" />
						</div>
						<div id="dateOfApptErr">MM/dd/yyyy format required</div>
					</div>
					<br/>
					<div>
						<label for="price">Price:</label>
						<div>
							<input name="price" id="price" />
						</div>
						<div id="priceErr">Only numbers allowed</div>
					</div>
					<br/>
					<div>
						<label for="statusOfAppt">Appointment Status:</label>
						<select name="statusOfAppt" id="statusOfAppt">
							<option value="ONTIME">On Time</option>
							<option value="DELAYED">Delayed</option>
						</select>
					</div>
					<br/>
					<button id="createBtn">Create Appointment</button>
				</form>
				<br/>
				<div id="appointmentId"></div>
			</div>
			
			<div id="deleteDiv">
				<form role="form" action="deleteAppointment" method="POST" id="deleteform">
					<div>
						<label for="deleteAppt">Enter Appointment Number to Delete:</label>
						<div>
							<input name="deleteAppt" id="deleteAppt" />
						</div>
						<div id="deleteErr">Only numbers allowed</div>
					</div>
					<br/>
					<button type="submit" id="deleteBtn">Delete Appointment</button>
				</form>
				<br/>
				<div id="deleteMsg"></div>
			</div>
			
			<div id="retrieveDiv">
				<form role="form" action="retrieveAppointment" method="POST" id="retrieveform">
					<div>
						<label for="retrieveAppt">Enter Appointment Number to Retrieve:</label>
						<div>
							<input name="retrieveAppt" id="retrieveAppt" />
						</div>
						<div id="retrieveErr">Only numbers allowed</div>
					</div>
					<br/>
					<button type="submit" id="retrieveBtn">Retrieve Appointment</button>
					<br/>
				</form>
				
				<div id="retrieveMsg"></div>
				<div id="retrieveDetails">
					<table>
						<tr>
							<td><b>Appointment Id:</b> </td>
							<td><span id="retrieveApptId"></span></td>
						</tr>
						<tr>
							<td><b>First Name:</b> </td>
							<td><span id="retrieveFirstName"></span></td>
						</tr>
						<tr>
							<td><b>Last Name:</b> </td>
							<td><span id="retrieveLastName"></span></td>
						</tr>
						<tr>
							<td><b>Car:</b> </td>
							<td><span id="retrieveCar"></span></td>
						</tr>
						<tr>
							<td><b>Date Of Appt:</b> </td>
							<td><span id="retrieveApptDate"></span></td>
						</tr>
						<tr>
							<td><b>Price:</b> </td>
							<td><span id="retrievePrice"></span></td>
						</tr>
						<tr>
							<td><b>Status Of Appt:</b> </td>
							<td><span id="retrieveStatus"></span></td>
						</tr>
					</table>
				</div>
			</div>
			
			<div id="updateDiv">
				<form role="form" action="retrieveAppointment" method="POST" id="updateapptform">
					<div>
						<label for="updateAppt">Enter Appointment Number to Update:</label>
						<div>
							<input name="updateAppt" id="updateAppt" />
						</div>
						<div id="updateErr">Only numbers allowed</div>
					</div>
					<br/>
					<button type="submit" id="updateGetBtn">Get Appointment</button>
				</form>
				<br/>
				<div id="updateMsg"></div>
				<div id="updateDetails">
					<form action="updateAppointment" method="POST" id="updateform">
					<div>
						<label for="appointmentId">Appointment Id:</label>
						<div>
							<input name="appointmentId" id="updateAppointmentId" disabled />
						</div>
					</div>
					<br/>
					<div>
						<label for="firstName">First Name:</label>
						<div>
							<input name="firstName" id="updateFirstName" />
						</div>
						<div id="updateFirstNameErr">Enter valid name</div>
					</div>
					<br/>
					<div>
						<label for="lastName">Last Name:</label>
						<div>
							<input name="lastName" id="updateLastName" />
						</div>
						<div id="updateLastNameErr">Enter valid name</div>
					</div>
					<br/>
					<div>
						<label for="car">Car:</label>
						<div>
							<input name="car" id="updateCar" />
						</div>
						<div id="updateCarErr">Enter valid car</div>
					</div>
					<br/>
					<div>
						<label for="dateOfAppt">Appointment Date:</label>
						<div>
							<input name="dateOfAppt" id="updateDateOfAppt" />
						</div>
						<div id="updateDateOfApptErr">MM/dd/yyyy format required</div>
					</div>
					<br/>
					<div>
						<label for="price">Price:</label>
						<div>
							<input name="price" id="updatePrice" />
						</div>
						<div id="updatePriceErr">Only numbers allowed</div>
					</div>
					<br/>
					<div>
						<label for="statusOfAppt">Appointment Status:</label>
						<select name="statusOfAppt" id="updateStatusOfAppt">
							<option value="ONTIME">On Time</option>
							<option value="DELAYED">Delayed</option>
						</select>
					</div>
					<br/>
					<button id="updateBtn">Update Appointment</button>
				</form>
				</div>
			</div>
			
			<div id="rangeDiv">
				<div id="rangeDetails">
					<form action="rangeAppointment" method="POST" id="rangeform">
						<div>
							<label for="firstDate">From:</label>
							<div>
								<input name="firstDate" id="firstDate" />
							</div>
							<div id="firstDateErr">MM/dd/yyyy format required</div>
						</div>
						<br/>
						<div>
							<label for="secondDate">Till:</label>
							<div>
								<input name="secondDate" id="secondDate" />
							</div>
							<div id="secondDateErr">MM/dd/yyyy format required</div>
						</div>
						<br/>
						<button id="rangeBtn">Get Appointments</button>
					</form>
					<br/>
					<div id="rangeMsg"></div>
				</div>
			</div>
			
		</div>
	</div>
	<br clear="all"/>
	<br/>
	<div id="dynamicTable"></div>
</body>
</html>