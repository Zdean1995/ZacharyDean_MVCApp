/*
 * Name:Zachary Dean
 * Student Number: 991353674
 */ 
package mvcapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ManageCustomerServlet
 */
@WebServlet("/ManageCustomerServlet")
public class ManageCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="jdbc:mysql://localhost:3306/MVCDB?autoconnect"
				+ "=true&useSSL=false&allowPublicKeyRetrieval=true";
	    String DriverClass="com.mysql.jdbc.Driver";
	    String user="root";
	    String pass="kmgzkjjfagzz";
	    
	    String userID;
	    String userName;
	    String firstName;
	    String lastName;
	    
	    try {
			Class.forName(DriverClass).newInstance();
			Connection con = null;
			con = DriverManager.getConnection(url, user, pass);
			
			CustomerInfo customerInfo = new CustomerInfo();
			
			
			String customerSelect = "SELECT customerId, userName, firstName, lastName"
					+ " FROM Customers";
			PreparedStatement customerGet = con.prepareStatement(customerSelect);
			
			ResultSet rs = customerGet.executeQuery();
			
			if(rs.next())
			{	
				customerInfo.setOrders("<table><tr><td>Customer ID</td>"
						+ "<td>Username</td><td>First Name</td><td>Last Name"
						+ "</td></tr>");
				do {
					userID = rs.getString(1);
					userName = rs.getString(2);
					firstName = rs.getString(3);
					lastName = rs.getString(4);
					
					customerInfo.setOrders(customerInfo.getOrders()
							+ "<tr><form action=\"CustomerManageEditServlet\" method=\"post\""
							+ "<td>" + userID + "</td><td>" + userName
							+ "</td><td>" + firstName + "</td><td>"
							+ lastName + "</td><td><input type=\"hidden\" name=\"userID\""
							+ " value=\"" + userID + "\"><input type=\"submit\" "
							+ "value = \"Edit\"></td></form></tr>");
					
				}while(rs.next());
				customerInfo.setOrders(customerInfo.getOrders() + "</table>");
			}
			else
			{
				customerInfo.setOrders("No Customers");
			}
	    	
			request.setAttribute("customers", customerInfo);
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("editCustomers.jsp");  
			rd.forward(request, response);
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
