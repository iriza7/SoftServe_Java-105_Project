<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<c:if test="${!empty linesByStationList}">
	<hr />
	<table>
		<thead>
			<tr>
				<td>Number</td>
				<td><spring:message code="label.lines.linename" /> <a
					href="javascript:void(0);"
					onclick="showLinesByStationPage('${param.stationName}',${pageNumber},${resultsPerPage}, '1')"><img
						alt="^" src="resources/images/downarrow.png"></a> <a
					href="javascript:void(0);"
					onclick="showLinesByStationPage('${param.stationName}',${pageNumber},${resultsPerPage}, '2')"><img
						alt="^" src="resources/images/uparrow.png"></a></td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="lines" items="${linesByStationList}">
				<tr>
					<td id="generate"></td>
					<td>${lines.getLineName()}</td>
					<td><a href="stationsoncertainline/${lines.getLineName()}">Show
							stations</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<hr />
</c:if>
<c:if test="${empty linesByStationList}">
	<p>No results.</p>
</c:if>
<div class="pagination">
	<ul class="bootpag">
		<c:if test="${pageNumber>1}">
			<li class="prev"><a href="javascript:void(0);"
				onclick="showLinesByStationPage('${param.stationName}',1, ${resultsPerPage}, '${param.sortOrder}')">
					« </a></li>
			<li class="prev"><a href="javascript:void(0);"
				onclick="showLinesByStationPage('${param.stationName}',${pageNumber-1},${resultsPerPage}, '${param.sortOrder}')">
					<spring:message code="label.prev" />
			</a></li>
		</c:if>
		<c:if test="${pageNumber==1}">
			<li class="prev disabled"><a href="javascript:void(0);"> « </a></li>
			<li class="prev disabled"><a href="javascript:void(0);"> <spring:message
						code="label.prev" />
			</a></li>
		</c:if>
		<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
			varStatus="status">
			<c:if test="${pageNumber!=i}">
				<li><a href="javascript:void(0);"
					onclick="showLinesByStationPage('${param.stationName}',${i},${resultsPerPage}, '${param.sortOrder}')">
						${i} </a></li>
			</c:if>
			<c:if test="${pageNumber==i}">
				<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
			</c:if>
		</c:forEach>

		<c:if test="${pageNumber<maxPages}">
			<li class="next"><a href="javascript:void(0);"
				onclick="showLinesByStationPage('${param.stationName}',${pageNumber+1},${resultsPerPage}, '${param.sortOrder}')">
					<spring:message code="label.next" />
			</a></li>
			<li class="next"><a href="javascript:void(0);"
				onclick="showLinesByStationPage('${param.stationName}',${maxPages},${resultsPerPage}, '${param.sortOrder}')">
					» </a></li>
		</c:if>
		<c:if test="${pageNumber==maxPages}">
			<li class="next disabled"><a href="javascript:void(0);"> <spring:message
						code="label.next" />
			</a></li>
			<li class="next disabled"><a href="javascript:void(0);"> » </a></li>
		</c:if>
	</ul>
</div>