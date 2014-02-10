<%@ page language="java" contentType="text/html; charset=UTF8"
	pageEncoding="UTF8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<table style="align: center">
<tr>
<th><spring:message code="label.transport.transportcode"/></th>
<th><spring:message code="label.trips.remseatclass1"/></th>
<th><spring:message code="label.trips.remseatclass2"/></th>
<th><spring:message code="label.trips.remseatclass3"/></th>
<th><spring:message code="label.trips.date"/></th>
<th><spring:message code="label.transport.starttime"/></th>
</tr>
<c:forEach items="${tripsList}" var="trip">
<tr>
<td align="center">${trip.getTransport().getTransportCode()}</td>
<td align="center">${trip.getRemSeatClass1()}</td>
<td align="center">${trip.getRemSeatClass2()}</td>
<td align="center">${trip.getRemSeatClass3()}</td>
<td align="center">${dateFormat.format(trip.getStartDate())}</td>
<td align="center">${trip.getTransport().getStartTime()}</td>
</tr>
</c:forEach>
</table>


<h1>Ajax ${pageNumber} ${resultsPerPage}</h1>


<div class="pagination"><ul class="bootpag">
<c:if test = "${pageNumber>1}">
<li class="prev"><a href="javascript:void(0);" onclick="showTripsPage(1, ${resultsPerPage})"> « </a></li>
<li class="prev"><a href="javascript:void(0);" onclick="showTripsPage(${pageNumber-1},${resultsPerPage})"> <spring:message code="label.prev"/> </a></li>
</c:if>
<c:if test = "${pageNumber==1}">
<li  class="prev disabled"><a href="javascript:void(0);"> « </a></li>
<li  class="prev disabled"><a href="javascript:void(0);"> <spring:message code="label.prev"/> </a></li>
</c:if>
<c:forEach var="i" begin="${firstPage}" end="${lastPage}" step="1" varStatus ="status">
<c:if test = "${pageNumber!=i}">
<li><a href="javascript:void(0);" onclick="showTripsPage(${i},${resultsPerPage})">  ${i} </a></li>
</c:if>
<c:if test = "${pageNumber==i}">
<li class="disabled"><a href="javascript:void(0);">  ${i} </a></li>
</c:if>
</c:forEach>

<c:if test = "${pageNumber<maxPages}">
<li class="next"><a href="javascript:void(0);" onclick="showTripsPage(${pageNumber+1},${resultsPerPage})"> <spring:message code="label.next"/> </a></li>
<li class="next"><a href="javascript:void(0);" onclick="showTripsPage(${maxPages},${resultsPerPage})"> » </a></li>
</c:if>
<c:if test = "${pageNumber==maxPages}">
<li class="next disabled"><a href="javascript:void(0);"> <spring:message code="label.next"/> </a></li>
<li class="next disabled"><a href="javascript:void(0);"> » </a></li>
</c:if>
</ul></div>