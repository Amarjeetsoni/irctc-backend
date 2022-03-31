<%@page import="com.cg.IRCTC.entity.Coaches"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User View</title>
<style>
.collapsible {
  background-color: #777;
  color: white;
  cursor: pointer;
  padding: 18px;
  width: 100%;
  border: none;
  text-align: left;
  outline: none;
  font-size: 15px;
}

.active, .collapsible:hover {
  background-color: #555;
}

.content {
  padding: 0 18px;
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.2s ease-out;
  background-color: #f1f1f1;
}
Body {  
  font-family: Calibri, Helvetica, sans-serif;  
  background-color: white;  
} 
form {   
        border: 3px solid #f1f1f1;   
    } 
input[type=text], input[type=password], input[type=number] {   
        width: 100%;   
        margin: 8px 0;  
        padding: 12px 20px;   
        display: inline-block;   
        border: 2px solid green;   
        box-sizing: border-box;   
    }  
button:hover {   
        opacity: 0.7;   
    } 
</style>
</head>
<body>
	<center><h1>Welcome to User View</h1></center>
	<form id="Logout" method="get" action="/logout">  
           <center> <button type="submit">logout</button></center>
	</form>
	<b style="color:red">${ ErrorMessage }</b>
    <b style="color:green">${ SuccessMessage }</b><br>
    <table border="2" align="center">
    <tr>
    	<th>Coach Name</th>
    	<th>Total Seat</th>
    	<th>Total Available Seat</th>
    	<th>Structure<i style="background-color:green;color: white">Available</i><i style="background-color:red;color: black">Booked</i></th>
    </tr>
    <c:forEach items="${AllCoach}" var="coach">
    <tr>      
        <td>${coach.coaches_name}</td>
        <td>${coach.number_seats}</td>
        <td>${coach.number_available_seat}</td>
        <td>
        <%
		Coaches current = (Coaches)pageContext.getAttribute("coach");;
        for(int i = 0; i < current.getSeatStructure().size(); i++){
        	if(current.getSeatStructure().get(i)){ %>
        	<i style="background-color:green;color: white"><%out.println(i+1); %></i> &nbsp;
       <% 		
        	}else{
        		%>
        		<i style="background-color:red;color: black"><%out.println(i+1); %></i>&nbsp;
        		<%
        	}
        }
        %>
        </td>
    </tr>
</c:forEach>
    </table>
    <table border="2" align="center">
    <tr>
    	<th>Train Name</th>
    	<th>Train Number</th>
    	<th>Number Of Coachs</th>
    </tr>
    <c:forEach items="${allTrain}" var="trains">
    <tr>      
        <td>${trains.trainName}</td>
        <td>${trains.trainNumber}</td>
        <td>${trains.numofCoatches}</td>
    </tr>
</c:forEach>
</table>
<table border="2" align="center">
<tr>
	<th align="center">Details of the Coach</th>
</tr>
<tr>
<th>Coach Type</th>
<th>Total Seat</th>
<th>Total Available seat</th>
<th>Seat Structure <i style="background-color:green;color: white">Available</i><i style="background-color:red;color: black">Booked</i></th>
</tr>
<tr>
	<td>${ CoachDetails.coaches_name }</td>
	<td>${ CoachDetails.number_seats }</td>
	<td>${ CoachDetails.number_available_seat }</td>
	<td>
	<%
        Coaches Coach = (Coaches)request.getAttribute("CoachDetails");
		if(Coach != null)
        for(int i = 0; i < Coach.getSeatStructure().size(); i++){
        	if(Coach.getSeatStructure().get(i)){ %>
        	<i style="background-color:green;color: white"><%out.println(i+1); %></i> &nbsp;
       <% 		
        	}else{
        		%>
        		<i style="background-color:red;color: black"><%out.println(i+1); %></i>&nbsp;
        		<%
        	}
        }
     %>
	</td>
</tr>
</table>
    <button class="collapsible">Get Train Coaches</button>
	<div class="content">
  		<form id="trainDetails" method="get" action="/user/trainDetail">
			<label>trainNumber : </label>   
            <input type="text" placeholder="Enter trainNumber" name="name" required><br>  
            <button type="submit">get Train</button>
		</form>
	</div>
	<button class="collapsible">Get All Train Details</button>
	<div class="content">
  		<form id="getTrainDetail" method="get" action="/user/getTrainDetail">
            <button type="submit">Get All Train Details</button>
		</form>
	</div>
	<button class="collapsible">Get Any specific Coach Detail from A Train</button>
	<div class="content">
  		<form id="coach" method="post" action="/user/TrainCoach">
			<label>trainNumber : </label>   
            <input type="text" placeholder="Enter trainNumber" name="trainNumber" required><br>  
            <label>CoachNumber : </label>   
            <input type="number" placeholder="Enter coach Number" name="number" required><br>  
            <button type="submit">get coach</button>
		</form>
	</div>
	<button class="collapsible">Book A Ticket</button>
	<div class="content">
  		<form id="coach" method="post" action="/user/bookTicket">
			<label>trainNumber : </label>   
            <input type="text" placeholder="Enter trainNumber" name="TainNumber" required><br>  
            <label>CoachNumber : </label>   
            <input type="number" placeholder="Enter coach Number" name="coachNumber" required><br> 
            <label>Seat Number : </label>   
            <input type="number" placeholder="Enter Seat Number" name="book" required><br>  
            <button type="submit">Book Now!</button>
		</form>
	</div>
	<button class="collapsible">Book multiple Tickets</button>
	<div class="content">
		<b style="color:red">Enter multiple Seat numbers with spaces</b>
  		<form id="coach" method="post" action="/user/bookTickets">
			<label>trainNumber : </label>   
            <input type="text" placeholder="Enter trainNumber" name="TainNumber" required><br>  
            <label>CoachNumber : </label>   
            <input type="number" placeholder="Enter coach Number" name="coachNumber" required><br> 
            <label>Seat Number : </label>   
            <input type="text" placeholder="Enter Seat Number" name="book" required><br>  
            <button type="submit">Book Now!</button>
		</form>
	</div>
	  
<script>
var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.maxHeight){
      content.style.maxHeight = null;
    } else {
      content.style.maxHeight = content.scrollHeight + "px";
    } 
  });
}
</script>
</body>
</html>