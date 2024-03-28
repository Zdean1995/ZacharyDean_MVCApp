<!-- 
Name: Zachary Dean
Student ID: 991353674
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<style>
	body {
		background-color: grey;
	}
	
	.navbar {
		overflow: hidden;
		float: left;
		background-color: #333;
		position: fixed;
		top: 0;
		width: 100%;
		box-shadow: 10px 0px 8px #888888
	}
	
	.navbar input {
		float:left;
		display: block;
		color: #f2f2f2;
		text-align: center;
		padding: 14px 16px;
		text-decoration: none;
		font-size: 17px;
		background-color: #333; /* Green */
		border: none;
		color: white;
		display: inline-block;
		cursor: pointer;
		font-family: Arial, Helvetica, sans-serif;
		top: 0;
	}
	
	html, body {margin: 0; height: 100%; overflow: hidden}
	
	h4 {text-align:center;}
	
	.medbox {
		background-color: lightgrey;
		border-style: solid;
    	border-width: 2px;
		border-radius: 5px;
    	padding: 10px;
        margin: auto;
        margin-top: 110px;
        max-width:75%;
		transform: translateY(-50%);
	}
	
	#logout {
		float: right;
	}
	
	.navbar input:hover {
		background: #ddd;
		color: black;
	}
	
	.main {
		padding: 100px;
		font-family: Arial, Helvetica, sans-serif;
		
	}
	
	#logo {
		height: 43px;
		float: left;
		margian: 1px;
	}
	table {	
		margin-left:auto;
		margin-right:auto;
	}
	
	td {
		text-align: center;
	}
	
	#submit {
		text-align: center;
	}
</style>
</head>
<body>
	<div class="navbar">
		<img id="logo" alt="logo" src="images/logo.png">
		<form action="LogInRedirectServlet" method="post">
			<input id="home" type="submit" value="Home">
		</form>
		<form action="RegisterRedirectServlet" method="post">
			<input id="createaccount" type="submit" value="Create Account">
		</form>
	</div>
	
	<div class="main">
		<div class="medbox">
		<form action="RegisterServlet" method="post">
			<h4>Customer Registration</h4>
			<table>
				<tr>
					<td>Username: </td><td><input type="text" name="username" required></td>
				</tr>
				<tr>
					<td>Password: </td><td><input type="text" name="password" required></td>
				</tr>
				<tr>
					<td>Confirm Password: </td><td><input type="text" name="passwordconfirm" required></td>
				</tr>
				<tr>	
					<td>First Name: </td><td><input type="text" name="firstname" required></td>
				</tr>
				<tr>
					<td>Last Name: </td><td><input type="text" name="lastname" required></td>
				</tr>
				<tr>
					<td>Address: </td><td><input type="text" name="address" required></td>
				</tr>
				<tr>
					<td>City: </td><td><input type="text" name="city" required></td>
				</tr>
				<tr>
					<td>Postal Code: </td><td><input type="text" name="postalcode" required></td>
				</tr>
				<tr>
					<td colspan=2><input type="submit" value="Register"></td>
				</tr>
			</table>
		</form>
		</div>
	</div>
</body>
</html>