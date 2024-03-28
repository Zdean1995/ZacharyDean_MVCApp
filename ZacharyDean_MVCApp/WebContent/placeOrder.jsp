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
        max-width:75%;
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
		text-align: left;
	}
	
	#submit {
		text-align: center;
	}
	
</style>
</head>
<body>
	<div class="navbar">
		<img id="logo" alt="logo" src="images/logo.png">
		<form action="HomeRedirectServlet" method="post">
			<input id="home" type="submit" value="Home">
		</form>
		<form action="PlaceOrderRedirectServlet" method="post">
			<input id="placeorders" type="submit" value="Place Order">
		</form>
		<form action="CustomerInfoServlet" method="post">
			<input id="customerinfo" type="submit" value="Customer and Orders Info">
		</form>
		<form action="LogOutServlet" method="post">
			<input id="logout" type="submit" value="Log Out">
		</form>
	</div>
	<div class = "main">
		<div class = "medbox">
			<form action="PlaceOrderServlet" method="post">
				<table>
					<tr>
						<td colspan=3>Category:</td>
					</tr>
					<tr>
						<td>Mens <input type="radio" name="category" value="men"></td>
						<td>Womens <input type="radio" name="category" value="women"></td>
						<td>Childrens <input type="radio" name="category" value="children"></td>
					</tr>
				</table>
				<table>
					<tr>
						<td colspan = 14>
							Sizes:
						</td>
					</tr>
					<tr>
						<td>1</td>
						<td>2</td>
						<td>3</td>
						<td>4</td>
						<td>5</td>
						<td>6</td>
						<td>7</td>
						<td>8</td>
						<td>9</td>
						<td>10</td>
						<td>11</td>
						<td>12</td>
						<td>13</td>
						<td>14</td>
					</tr>
					<tr>
						<td><input type="radio" name="size" value="1"></td>
						<td><input type="radio" name="size" value="2"></td>
						<td><input type="radio" name="size" value="3"></td>
						<td><input type="radio" name="size" value="4"></td>
						<td><input type="radio" name="size" value="5"></td>
						<td><input type="radio" name="size" value="6"></td>
						<td><input type="radio" name="size" value="7"></td>
						<td><input type="radio" name="size" value="8"></td>
						<td><input type="radio" name="size" value="9"></td>
						<td><input type="radio" name="size" value="10"></td>
						<td><input type="radio" name="size" value="11"></td>
						<td><input type="radio" name="size" value="12"></td>
						<td><input type="radio" name="size" value="13"></td>
						<td><input type="radio" name="size" value="14"></td>
					</tr>
					<tr>
						<td colspan = 14>
							<input type="submit" name="search" value="Search">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>