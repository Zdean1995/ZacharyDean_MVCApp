<!-- 
Name: Zachary Dean
Student ID: 991353674
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="mvcapp.Shoe"%>
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
		<form action="HomeDirectServlet" method="post">
			<input id="home" type="submit" value="Home">
		</form>
		<form>
			<input id="placeorders" type="submit" value="Place Order">
		</form>
		<form>
			<input id="myorders" type="submit" value="My Orders">
		</form>
		<form action="CustomerInfoServlet" method="post">
			<input id="customerinfo" type="submit" value="Customer Info">
		</form>
		<form action="LogOutServlet" method="post">
			<input id="logout" type="submit" value="Log Out">
		</form>
	</div>
	
	<div class="main">
		<div class = "medbox">
		<% 
	 	Shoe shoe=(Shoe)request.getAttribute("shoeEdit"); 
		%>
			<form action="EditShoeServlet" method="post">
				<table>
					<tr>
						<td>Shoe ID</td>
						<td>Shoe Name</td>
						<td>Shoe Size</td>
						<td>Price</td>
					</tr>
					<tr>
						<td><%out.print(shoe.getShoeID());%>
							<input type=hidden value="<%out.print(shoe.getShoeID());%>" name="shoeID">
						</td>
						<td><input type=text value="<%out.print(shoe.getShoeName());%>" name="shoeName" required></td>
						<td><input type=number value="<%out.print(shoe.getShoeSize());%>" name="shoeSize" required></td>
						<td><input type=number value="<%out.print(shoe.getPrice());%>" name="price" required></td>
					</tr>
				</table>
				<table>
					<tr>
						<td colspan=4>Category: <%out.print(shoe.getCategory());%></td>
					</tr>
					<tr>
						<td>Keep Same <input type="radio" name="category" value="keep" checked></td>
						<td>Mens <input type="radio" name="category" value="men"></td>
						<td>Womens <input type="radio" name="category" value="women"></td>
						<td>Childrens <input type="radio" name="category" value="children"></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><input type="submit" name="edit" value="Confirm Edit"></td>
						<td><input type="submit" name="delete" value="Delete Product"></td>
					</tr>
					<tr>
						<td colspan=2>Note: Deleting Shoes will Remove all Customer<br>
						Orders of that Shoe!</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>