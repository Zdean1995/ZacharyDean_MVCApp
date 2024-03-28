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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderProcessServlet
 */
@WebServlet("/OrderProcessServlet")
public class OrderProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderProcessServlet() {
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
	    
		String quantity = request.getParameter("quantity");
		String shoeID = request.getParameter("shoeID");
		
		Cookie[] cookies = request.getCookies();
		
		Cookie c = cookies[0];
		
		String userName = c.getValue();
		
		if(quantity == null || Double.parseDouble(quantity)<= 0)
		{
			ErrorMessage error = new ErrorMessage();
			
			error.setMessage("No Quantity Entered");
			request.setAttribute("error", error);
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("orderError.jsp");  
			rd.forward(request, response);
		}
	    
	    try {
			Class.forName(DriverClass).newInstance();
			Connection con = null;
			con = DriverManager.getConnection(url, user, pass);
			
			String selectSQL = "SELECT customerId FROM Customers "
					+ "WHERE userName = ?";
			PreparedStatement orderSet = con.prepareStatement(selectSQL);
			
			orderSet.setString(1, userName);
			ResultSet rs = orderSet.executeQuery();
			rs.next();
			
			String userID = rs.getString(1);
			
			
			Date date = new Date();
			String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
			
			String orderAdd = "INSERT INTO Orders (customerId, itemId"
					+ ", orderDate, quantity, status) VALUES ( ?, ?, ?, ?, ?)";
			orderSet = con.prepareStatement(orderAdd);	
			orderSet.setString(1, userID);
			orderSet.setString(2, shoeID);
			orderSet.setString(3, modifiedDate);
			orderSet.setString(4, quantity);
			orderSet.setString(5, "Order Placed");
			orderSet.executeUpdate();
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("orderConfirm.jsp");  
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
