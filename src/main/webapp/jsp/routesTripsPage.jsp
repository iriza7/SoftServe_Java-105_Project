<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%-- Results --%>
<c:if test="${!empty RoutesTripsList}">
			<hr />
				<table style="align: center">
						<th><spring:message code="label.lines.linename" /></th>
						<th><spring:message code="label.routes.routecode" /></th>
						<th><spring:message code="label.transport.starttime" /></th>
						<th><spring:message code="label.routes.routename" /></th>
						
					<c:forEach var="routetrip" items="${RoutesTripsList}">
						<tr>
							<td align="center">${routetrip.getRoute().getLineId().getLineName()}</td>
							<td align="center">${routetrip.getRoute().getRouteCode()}</td>
							<td align="center">${routetrip.getStartTime()}</td>
							<td align="center">${routetrip.getRoute().getRouteName()}</td>
						</tr>
					</c:forEach>
				</table>
				<hr />
</c:if>
<c:if test="${empty RoutesTripsList && not empty param.nameStation  && not empty param.timeMin && not empty param.timeMax}">
		<hr />
			 <h3>Sorry. No results was found</h3>
		<hr />
</c:if>
	

	<div class="pagination">
		<ul class="bootpag">
			<c:if test="${pageNumber>1}">
				<li class="prev"><a href="javascript:void(0);"
					onclick="showRoutesTripPage('${param.nameStation}','${param.timeMin}','${param.timeMax}','${param.findBy}',1, ${resultsPerPage})">
						« </a></li>
				<li class="prev"><a href="javascript:void(0);"
					onclick="showRoutesTripPage('${param.nameStation}','${param.timeMin}','${param.timeMax}','${param.findBy}',${pageNumber-1},${resultsPerPage})">
						<spring:message code="label.prev" />
				</a></li>
			</c:if>
			<c:if test="${pageNumber==1}">
				<li class="prev disabled"><a href="javascript:void(0);"> «
				</a></li>
				<li class="prev disabled"><a href="javascript:void(0);"> <spring:message
							code="label.prev" />
				</a></li>
			</c:if>
			<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
				varStatus="status">
				<c:if test="${pageNumber!=i}">
					<li><a href="javascript:void(0);"
						onclick="showRoutesTripPage('${param.nameStation}','${param.timeMin}','${param.timeMax}','${param.findBy}',${i},${resultsPerPage})">
							${i} </a></li>
				</c:if>
				<c:if test="${pageNumber==i}">
					<li class="disabled"><a href="javascript:void(0);"> ${i} </a></li>
				</c:if>
			</c:forEach>

			<c:if test="${pageNumber<maxPages}">
				<li class="next"><a href="javascript:void(0);"
					onclick="showRoutesTripPage('${param.nameStation}','${param.timeMin}','${param.timeMax}','${param.findBy}',${pageNumber+1},${resultsPerPage})">
						<spring:message code="label.next" />
				</a></li>
				<li class="next"><a href="javascript:void(0);"
					onclick="showRoutesTripPage('${param.nameStation}','${param.timeMin}','${param.timeMax}','${param.findBy}',${maxPages},${resultsPerPage})">
						» </a></li>
			</c:if>
			<c:if test="${pageNumber==maxPages}">
				<li class="next disabled"><a href="javascript:void(0);"> <spring:message
							code="label.next" />
				</a></li>
				<li class="next disabled"><a href="javascript:void(0);"> »
				</a></li>
			</c:if>
		</ul>
	</div>
