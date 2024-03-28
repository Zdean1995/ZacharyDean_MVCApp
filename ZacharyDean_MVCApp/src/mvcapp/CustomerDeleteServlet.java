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
 * Servlet implementation class CustomerDeleteServlet
 */
@WebServlet("/CustomerDeleteServlet")
public class CustomerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDeleteServlet() {
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
	    
	    try {
			Class.forName(DriverClass).newInstance();
			Connection con = null;
			con = DriverManager.getConnection(url, user, pass);
			
			String userName = request.getParameter("userName");
			
			String customerSelect = "SELECT customerId FROM Customers "
					+ "WHERE userName = ?";
			String ordersDelete = "DELETE FROM Orders " + 
					"WHERE customerId = ?;";
			String customerDelete = "DELETE FROM Customers " + 
					"WHERE customerId = ?;";
			
			PreparedStatement customersGet = con.prepareStatement(customerSelect);
			customersGet.setString(1, userName);
			
			ResultSet rs = customersGet.executeQuery();
			rs.next();
			
			String userId = rs.getString(1);
			
			PreparedStatement ordersSet = con.prepareStatement(ordersDelete);
			ordersSet.setString(1, userId);
			PreparedStatement customersSet = con.prepareStatement(customerDelete);
			customersSet.setString(1, userId);
			
			ordersSet.executeUpdate();
			customersSet.executeUpdate();
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("deleteCustomersConfirm.jsp");  
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
