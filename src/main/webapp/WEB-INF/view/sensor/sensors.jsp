<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello</title>
</head>
<body>
	<h2>Sensors</h2>

	<table>
		<c:forEach items="${sensorList}" var="sensor">
			<s:url value="/edit/${sensorId}" var="sensorEditUrl">
				<s:param name="sensorId" value="${sensor.id}"/>
			</s:url>
			<tr>
				<td>${sensor.id}</td>
				<td>${sensor.name}</td>
				<td>${sensor.type}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>