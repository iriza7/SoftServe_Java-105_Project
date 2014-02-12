<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>
	<%int i = 0;%>
<h1 >Lines Search</h1> 
<h1>"${stationName}"</h1>
<c:if test="${!empty linesbystationlist}">
	<div id = "maxPageCount" style="display : none;">${maxPageCount}</div>
	<div id = "resultsPerPage" style="display : none;">${resultsPerPage}</div>
		<hr />
		<table>
		
			<c:forEach var="lines" items="${linesbystationlist}">
				<tr>
					<td><%=++i%></td>
					<td>${lines.getLineName()}</td>
					<!--  <td><a href="stationsoncertainline/${lines.getLineName()}">Show stations</a></td>-->
				</tr>
			</c:forEach>
		</table>
		<hr />
	</c:if>
	<c:if test="${empty linesbystationlist}">
	<p>No results.</p>
	</c:if>