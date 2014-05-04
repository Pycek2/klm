<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sform" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello</title>
</head>
<body>
	<h2>Sensors</h2>

	<sform:form modelAttribute="sensor">
		<table>
			<tr>
				<td>Sensor name</td>
				<td><sform:input id="name" path="name" /></td>
			</tr>
			<tr>
				<td>Sensor type</td>
				<td>
					<sform:select path="type">
						<sform:options items="${typeList}"/>
					</sform:select>
				</td>
			</tr>
			
		</table>
		<sform:button>SAVE</sform:button>
	</sform:form>
</body>
</html>