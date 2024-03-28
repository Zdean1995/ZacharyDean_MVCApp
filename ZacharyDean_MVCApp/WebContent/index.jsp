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
<title>Fictional Shoe Company</title>
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
		box-shadow: 10px 0px 8px #333333;
		z-index: 99 !important;
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
		position:relative;
		
	}
	
	#logout {
		float: right;
	}
	
	.navbar input:hover {
		background: #ddd;
		color: black;
	}
	
	.main {
		margin-top: 20px;
		font-family: Arial, Helvetica, sans-serif;
		padding:100px;
		
	}
	
	#logo {
		height: 43px;
		float: left;
		margian: 1px;
	}
	
	.medbox {
		background-color: lightgrey;
		border-style: solid;
    	border-width: 2px;
		border-radius: 5px;
    	padding: 10px;
        margin: auto;
        margin-top: 50px;
        overflow:auto;
        text-align: center;
		top: 50%;
		transform: translateY(-50%);
	}
	
	h3 {
		text-align: center;
	}
	
	table {	
		margin-left:auto;
		margin-right:auto;
	}
	
	th {
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
		<div class = "medbox">
				<h3>Online Shopping Log in</h3>
				<form action="LoginServlet" method="post">
					<table>
						<tr>
							<th colspan=2>Username:</th>
						</tr>
						<tr>
							<td colspan=2><input type="text" name="username" required></td>
						</tr>
						<tr>
							<th colspan=2>Password:</th>
						</tr>
						<tr>
							<td colspan=2><input type="text" name="password" required></td>
						</tr>
						<tr>
								<td>Shopper: <input type="radio" name="usertype" value="customer" checked></td>
								<td>Employee: <input type="radio" name="usertype" value="employee"></td>
						</tr>
					</table>
					<div id="submit">
						<input type="submit" value="Log In">
					</div>
				</form>
			</div>

	</div>
</body>
</html>