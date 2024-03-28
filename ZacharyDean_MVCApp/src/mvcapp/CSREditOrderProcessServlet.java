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
 * Servlet implementation class CSREditOrderProcessServlet
 */
@WebServlet("/CSREditOrderProcessServlet")
public class CSREditOrderProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSREditOrderProcessServlet() {
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
		String orderID = request.getParameter("orderID");
		Connection con = null;
		try {
		    if (request.getParameter("edit") != null){
		    String shoeSize = request.getParameter("shoeSize");
			String quantity = request.getParameter("quantity");
			RequestDispatcher rd;
			ErrorMessage error = new ErrorMessage();
			
			if(Double.parseDouble(shoeSize) <= 0 || Double.parseDouble(shoeSize) >= 15)
			{
				error.setMessage("ShoeSize must be between 1 and 14");
				
				rd = request.getRequestDispatcher("csrEditOrderError.jsp");  
				rd.forward(request, response);
				
			}
			if(Double.parseDouble(quantity)<= 0)
			{
				error.setMessage("Quantity Must be More Than Zero");
				request.setAttribute("error", error);
				
				rd = request.getRequestDispatcher("csrEditOrderError.jsp");  
				rd.forward(request, response);
			}
		    
				Class.forName(DriverClass).newInstance();
				
				con = DriverManager.getConnection(url, user, pass);
				
				
				String orderSelect = "SELECT itemID FROM Orders "
						+ "WHERE orderId = ?";
				
				String shoeSelect = "SELECT itemName FROM Shoes "
						+ "WHERE itemId = ?";
				
				PreparedStatement infoGet = con.prepareStatement(orderSelect);
				PreparedStatement shoeInfoGet = con.prepareStatement(shoeSelect);
				
				infoGet.setString(1, orderID);
				ResultSet rs = infoGet.executeQuery();
				rs.next();
				
				String shoeID = rs.getString(1);
				
				shoeInfoGet.setString(1, shoeID);
				
				rs = shoeInfoGet.executeQuery();
				rs.next();
				
				String shoeName = rs.getString(1);
				
				String shoeSwitch = "SELECT itemId FROM Shoes "
						+ "WHERE shoeSize = ? AND itemName = ?";
				
				PreparedStatement shoeSizeSwitch = con.prepareStatement(shoeSwitch);
				
				shoeSizeSwitch.setString(1, shoeSize);
				shoeSizeSwitch.setString(2, shoeName);
				
				rs = shoeSizeSwitch.executeQuery();
				
				if(rs.next()) {
					String newShoeID = rs.getString(1);
					String orderEdit;
					PreparedStatement orderSet;
					if(request.getParameter("status").equals("keep"))
							{
					orderEdit = "UPDATE Orders " + 
							"SET itemId = ?, "
							+ "quantity = ? " 
							+ "WHERE orderId = ?";
	
					orderSet = con.prepareStatement(orderEdit);
					orderSet.setString(1, newShoeID);
					orderSet.setString(2, quantity);
					orderSet.setString(3, orderID);
							}
					else
					{
						String status = request.getParameter("status");
						orderEdit = "UPDATE Orders " + 
								"SET itemId = ?, "
								+ "quantity = ?, "
								+ "status = ? "
								+ "WHERE orderId = ?";
		
						orderSet = con.prepareStatement(orderEdit);
						orderSet.setString(1, newShoeID);
						orderSet.setString(2, quantity);
						orderSet.setString(3, status);
						orderSet.setString(4, orderID);
					}
					orderSet.executeUpdate();
					rd = request.getRequestDispatcher("csrEditOrderConfirm.jsp");  
					rd.forward(request, response);
				}
				else
				{
					error.setMessage("No Version of " + shoeName
							+ " in Size " + shoeSize);
					
					rd = request.getRequestDispatcher("csrEditOrderError.jsp");  
					rd.forward(request, response);
				}
			}
		    else
			    {
			    	Class.forName(DriverClass).newInstance();
					con = null;
					con = DriverManager.getConnection(url, user, pass);
					
					String ordersDelete = "DELETE FROM Orders " + 
							"WHERE orderId = ?;";
					PreparedStatement orderSet = con.prepareStatement(ordersDelete);
					orderSet.setString(1, orderID);
					orderSet.executeUpdate();
					
					RequestDispatcher rd
							= request.getRequestDispatcher("csrEditOrderDelete.jsp");  
					rd.forward(request, response);
					
			    }
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
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
