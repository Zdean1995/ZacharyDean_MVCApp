<!-- 
Name: Zachary Dean
Student ID: 991353674
 -->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="mvcapp.CustomerInfo, mvcapp.User"%>
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
	.orderbox {
		background-color: lightgrey;
		border-style: solid;
    	border-width: 2px;
		border-radius: 5px;
    	padding: 10px;
        margin: 10px auto;
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
		<form>
			<input id="home" type="submit" value="Home">
		</form>
		<form action="ShoeManageServlet" method="post">
			<input type="submit" value="Manage Shoes">
		</form>
		<form action="AddShoeServlet" method="post">
			<input type="submit" value="Add Shoes">
		</form>
		<form action="ManageCustomerServlet" method="post">
			<input type="submit" value="Manage Customers">
		</form>
		<form action="LogOutServlet" method="post">
			<input id="logout" type="submit" value="Log Out">
		</form>
	</div>
	
	<div class="main">
		<table>
			<tr>
				<td>
					<div class = "medbox">
						<h3><% 
			 					User user= (User)request.getAttribute("user");
								CustomerInfo info=(CustomerInfo)request.getAttribute("custInfo");
			    					out.print(user.getUserName());
							%> Customer Information</h3>
							Name: <%out.print(user.getFirstName());%> <%out.print(user.getLastName());%><br>
							Password: <%out.print(user.getFirstName());%><br>
							Address: <%out.print(info.getAddress());%>,<%out.print(info.getCity());%>
							<%out.print(info.getPostalCode());%>
							<form action="CustomerDeleteServlet">
								<input type="hidden" value="<%out.print(user.getUserName());%>" name="userName">
								<input type="submit" value="Delete Customer Account">
							</form>
					</div>
				</td>
			</tr>
				<% 
			 		out.print(info.getOrders());  
				%>
		</table>
	</div>
</body>
</html>