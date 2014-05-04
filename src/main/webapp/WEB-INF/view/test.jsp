<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hello</title>
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.1.0.js"></script>
</head>
<body>
	<script type="text/javascript">
	function display(data) {
		$("#cons").text("");
		if (data) {
			for (var key in data) {
				$("#cons").append(key +" => " + JSON.stringify(data[key]));
				$("#cons").append("\n");
			}
		}
	}	
	
	function getUserList() {
		$.ajax({ 
             type: "GET",
             dataType: "json",
             url: "admin/users",
             success: function(data){        
                display(data);
             }
         });
	}
	
	function getUserDetails() {
		$.ajax({ 
             type: "GET",
             dataType: "json",
             url: "admin/users/1",
             success: function(data){        
                display(data);
             }
         });
	}
	
	function postUserDetails() {
		$.ajax({ 
             type: "POST",
             contentType: "application/json; charset=utf-8",
             data: JSON.stringify({"id":$("#uid"),"login":"admin","password":"admin","firstName":"admin_fn","lastName":"admin_ln","disabled":false}), 
             dataType: "json",
             url: "admin/users/1",
             success: function(data){        
                display(data);
             }
         });
	}
	
	function checkUserExists() {
		$.ajax({ 
             type: "POST",
             contentType: "application/json; charset=utf-8",
             data: JSON.stringify({"login":"admin","password":"admin"}), 
             dataType: "json",
             url: "admin/users/verify",
             success: function(data){        
                display(data);
             }
         });
	}
	
	</script>
	
	<h2>TEST</h2>
	
	<a id="userList" href="#" onclick="getUserList()">/admin/users</a><br />
	
	<a id="userList" href="#" onclick="getUserDetails()">/admin/users/1</a><br />
	
	<fieldset>
		<table>
			<tr><td>Id</td><td><input id="uid" value="0"/> </td></tr>
			<tr><td>Login</td><td><input id="login"/> </td></tr>
			<tr><td>Imie</td><td><input id="fname"/> </td></tr>
			<tr><td>Nazwisko</td><td><input id="lname"/> </td></tr>
			<tr><td>Email</td><td><input id="email"/> </td></tr>
		</table>
	</fieldset>
	
	<textarea id="cons" rows="10" cols="100"></textarea>
</body>
</html>