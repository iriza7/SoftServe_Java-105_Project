<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<section id="content">
	<h1 align="center">Edit Transport</h1>

	<form action="/editTransport" method="post" name="transport">
		<table style="align: center">
			<tr>
				<td>ID:</td>
				<td><input type="text" name="transportId"
					value="${transport.getTransportId()}"></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.transportcode" /></td>
				<td><input type="text" name="transportCode"
					value="${transport.getTransportCode()}"></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.starttime" /></td>
				<td><input type="text" type="text" name="startTime"
					value="${transport.getStartTime()}"></td>
			</tr>
			<tr>
				<td><spring:message code="label.routes.routecode" /></td>
				<td><input type="text" name="routes"
					value="${transport.getRoutes().getRouteCode()}" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass1" /></td>
				<td><input type="text" name="seatclass1"
					value="${transport.getSeatclass1()}" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass2" /></td>
				<td><input type="text" name="seatclass2"
					value="${transport.getSeatclass2()}" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.seatclass3" /></td>
				<td><input type="text" name="seatclass3"
					value="${transport.getSeatclass3()}" /></td>
			</tr>
			<tr>
				<td><spring:message code="label.transport.genprice" /></td>
				<td><input type="text" name="genprice"
					value="${transport.getGenPrice()}" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update"></td>
			</tr>
		</table>
	</form>
</section>