<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<title>Shopping bag</title>
<section id="content">
<form:form id = "bag" action= "/SoftServe_Java-105/bag" method="post" >
	
<c:if test="${!empty ticketsList}">
		<table>
			<thead>
				<tr>
					<th align="center">Customer info</th>
					<th align="center">Route</th>
					<th align="center">Date</th>
					<th align="center">Price</th>
				</tr>
			</thead>
			
					<c:forEach var="ticket" items="${ticketsList}">
			<tbody>
				<tr>
						<td align="center">${ticket.getCustomerInfo()}</td>
						<td align="center">${ticket.getTrip().getTransport().getRoutes().getRouteName()}</td>
						<td align="center">${ticket.getTrip().getStartDate()}</td>
						<td align="center">${ticket.getTrip().getTransport().getGenPrice()}</td>
						<td align="center"><a href="delete/${ticket.getTicketName()}/${ticket.getTrip().getTripId()}"><input
								id="delete" type="button" name="delete"
								value="delete"/>
						</a></td>
						</tr>

			</tbody>
</c:forEach>
		</table>
		<form:form action="bag" method="post">
			<table>
				<tr align=center>
					<td align=center>
						<p>
							Credit Cart Number: <input type="text" name="credit_card_number"><br>
							Card holder first Name: <input id="firstName" type="text"
								name="firstName"><br>
						<p>
							Card holder last Name: <input id="lastName" type="text"
								name="lastName"><br>
						<p>
							Card holder billing adress:<input type="text" name="adress"><br>
						<p>
							Security code: <input type="text" name="securityCode"> <input
								type="submit" value="Pay">
						</p>
					</td>
				</tr>
			</table>
		</form:form>
	</c:if>
	</form:form>
	
	<c:if test="${empty ticketsList }">
		<p>Your shopping bag is empty.</p>
	</c:if>
</section>