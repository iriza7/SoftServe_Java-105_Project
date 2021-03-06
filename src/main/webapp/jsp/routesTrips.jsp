<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<link rel="stylesheet" type="text/css"
	href="resources/css/jquery.datetimepicker.css" />
<link rel="stylesheet" href="resources/css/jquery-ui.css"
	type="text/css" />

<section id="content">
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.datetimepicker.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery.validate.min.js"></script>
	<script type="text/javascript"
		src="resources/js/jquery.autocomplete.js"></script>
	<script type="text/javascript" src="resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="resources/js/routesTripSearch.js"></script>


	<h2 align="center">
		<spring:message code="label.navigation.routes" />
	</h2>

	<form:form action="routesTrips" method="get">
		<div id="routesTrips">
			<div>
				<span class="pad_right"> <spring:message
						code="label.stations.stationname" /> <input type="text"
					id="stationList" name="nameStation"></input>
				</span> <span class="pad_right"> <spring:message
						code="label.navigation.minTime" /> <input type="text"
					id="timeMin" name="timeMin" size="10"></input>
				</span> <span class="pad_right"> <spring:message
						code="label.navigation.maxTime" /> <input type="text"
					id="timeMax" name="timeMax" size="10"></input>
				</span>
				<div class="pad_top_bottom">
					<spring:message code="label.navigation.arrival" />
					<input name="findBy" type="radio" value="findByArr" checked>
					<spring:message code="label.navigation.departure" />
					<input name="findBy" type="radio" value="findByDep">
				</div>
			</div>
		</div>
		<div>
			<input class="button" id="btnFindRoutes" type="submit" name="submit"
				value=<spring:message code="label.navigation.findrouts" />></input>
		</div>
	</form:form>
	<div id="result">
		<%-- Results --%>
		<c:if test="${!empty RoutesTripsList}">
			<hr />
			<table style="align: center">
				<tr>

					<th><spring:message code="label.lines.linename" /></th>
					<th><spring:message code="label.routes.routecode" /></th>
					<th><spring:message code="label.transport.starttime" /></th>
					<th><spring:message code="label.routes.routename" /></th>

				</tr>
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
		<c:if
			test="${empty RoutesTripsList && not empty param.nameStation  && not empty param.timeMin && not empty param.timeMax}">
			<hr />
			<h3>Sorry. No results was found</h3>
			<hr />
		</c:if>
		<c:if
			test="${not empty param.nameStation  && not empty param.timeMin && not empty param.timeMax}">
			<div class="pagination">
				<ul class="bootpag">
					<c:if test="${pageNumber>1}">
						<li class="prev"><a
							href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=1&resultsPerPage=${resultsPerPage}">
								« </a></li>
						<li class="prev"><a
							href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=${pageNumber-1}&resultsPerPage=${resultsPerPage}">
								<spring:message code="label.prev" />
						</a></li>
					</c:if>
					<c:if test="${pageNumber==1}">
						<li class="prev disabled"><a href="javascript:void(0);">
								« </a></li>
						<li class="prev disabled"><a href="javascript:void(0);">
								<spring:message code="label.prev" />
						</a></li>
					</c:if>
					<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1"
						varStatus="status">
						<c:if test="${pageNumber!=i}">
							<li><a
								href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=${i}&resultsPerPage=${resultsPerPage}">
									${i} </a></li>
						</c:if>
						<c:if test="${pageNumber==i}">
							<li class="disabled"><a href="javascript:void(0);"> ${i}
							</a></li>
						</c:if>
					</c:forEach>
					<c:if test="${pageNumber<maxPages}">
						<li class="next"><a
							href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=${pageNumber+1}&resultsPerPage=${resultsPerPage}">
								<spring:message code="label.next" />
						</a></li>
						<li class="next"><a
							href="?nameStation=${param.nameStation}&timeMin=${param.timeMin}&timeMax=${param.timeMax}&findBy=${param.findBy}&pageNumber=${maxPages}&resultsPerPage=${resultsPerPage}">
								» </a></li>
					</c:if>
					<c:if test="${pageNumber==maxPages}">
						<li class="next disabled"><a href="javascript:void(0);">
								<spring:message code="label.next" />
						</a></li>
						<li class="next disabled"><a href="javascript:void(0);">
								» </a></li>
					</c:if>
				</ul>
			</div>
		</c:if>
	</div>

	<c:if
		test="${not empty param.nameStation  && not empty param.timeMin && not empty param.timeMax}">
		<script>
			$(function() {
				getStationList();
			});
			function showRoutesTripPage(stationName_, timeMin_, timeMax_,
					findBy_, pageNumber_, resultsPerPage_) {
				if (stationName_ == "" || timeMin_ == "" || timeMax_ == "") {
					return;
				}
				$
						.ajax(
								{
									async : true,
									beforeSend : function() {
										$("div#result")
												.html(
														'<img id="ajaxLoadingImg" src="resources/images/loading.gif">');
									},
									type : "GET",
									url : "routesTripsPage",
									data : {
										nameStation : stationName_,
										timeMin : timeMin_,
										timeMax : timeMax_,
										findBy : findBy_,
										pageNumber : pageNumber_,
										resultsPerPage : resultsPerPage_
									}
								}).done(function(msg) {
							$("div#result").html(msg);
						});

			}
			$(window).load(
					function() {
						showRoutesTripPage("${param.nameStation}",
								"${param.timeMin}", "${param.timeMax}",
								"${param.findBy}", "${pageNumber}",
								"${resultsPerPage}");
					});
		</script>
	</c:if>
</section>
