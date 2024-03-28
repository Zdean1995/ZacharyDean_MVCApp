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
<title>Insert title here</title>
<style>
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
	
	.medbox {
		background-color: lightgrey;
		border-style: solid;
    	border-width: 2px;
		border-radius: 5px;
    	padding: 10px;
        margin: auto;
        max-width:75%;
        overflow:auto;
		top: 50%;
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
		background-color: grey;
		
	}
	
	#logo {
		height: 43px;
		float: left;
		margian: 1px;
	}
</style>
</head>
<body>
<div class="navbar">
	<img id="logo" alt="logo" src="images/logo.png">
</div>
<div class="main">
	<div class="medbox">
		<% 
		 Shoe shoe=(Shoe)request.getAttribute("shoe");  
	     out.print(shoe.getShoeName());
		%> Added Successfully.
	<form action="AddShoeServlet" method="post">
		<input type="submit" value="Add More">
	</form>
	<form action="CSRHomeRedirectServlet" method="post">
		<input type="submit" value="Home">
	</form>
	</div>
</div>
</body>
</html>