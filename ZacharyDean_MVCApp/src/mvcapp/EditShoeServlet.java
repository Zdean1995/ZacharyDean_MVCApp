/*
 * Name:Zachary Dean
 * Student Number: 991353674
 */ 
package mvcapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditShoeServlet
 */
@WebServlet("/EditShoeServlet")
public class EditShoeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditShoeServlet() {
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
	    
	    String shoeID = request.getParameter("shoeID");
		String shoeName = request.getParameter("shoeName");
		int shoeSize = Integer.parseInt(request.getParameter("shoeSize"));
		double price = Double.parseDouble(request.getParameter("price"));
		String category = request.getParameter("category");
		Shoe shoe = new Shoe();
		
		shoe.setShoeID(shoeID);
		shoe.setShoeName(shoeName);
		
		if(shoeSize <= 0 || shoeSize >= 15)
		{
			ErrorMessage error = new ErrorMessage();
			
			error.setMessage("ShoeSize must be between 1 and 14");
			request.setAttribute("shoe", shoe);
			request.setAttribute("error", error);
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("editShoesError.jsp");  
			rd.forward(request, response);
			
		}
		
		if(price <= 0)
		{
			ErrorMessage error = new ErrorMessage();
			
			error.setMessage("Shoes Can't be Free<br>This Ain't a Charity");
			request.setAttribute("shoe", shoe);
			request.setAttribute("error", error);
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("editShoesError.jsp");  
			rd.forward(request, response);
		}
	    
	    try {
			Class.forName(DriverClass).newInstance();
			Connection con = null;
			con = DriverManager.getConnection(url, user, pass);
			
			
			PreparedStatement shoeSet;
			
			if (request.getParameter("edit") != null)
			{				
				if(category.equals("keep") || category == null)
				{
					String shoeEdit = "UPDATE Shoes " + 
							"SET itemName = ?, shoeSize = ?"
							+ ", price = ?" 
							+ "WHERE itemId = ?";
					shoeSet = con.prepareStatement(shoeEdit);
					
					shoeSet.setString(1, shoeName);
					shoeSet.setInt(2, shoeSize);
					shoeSet.setDouble(3, price);
					shoeSet.setString(4, shoeID);
				}
				else
				{
					String shoeEdit = "UPDATE Shoes " + 
							"SET itemName = ?, shoeSize = ?"
							+ ", price = ?, category = ?" 
							+ "WHERE itemId = ?";
					shoeSet = con.prepareStatement(shoeEdit);
					
					shoeSet.setString(1, shoeName);
					shoeSet.setInt(2, shoeSize);
					shoeSet.setDouble(3, price);
					shoeSet.setString(4, category);
					shoeSet.setString(5, shoeID);
				}
				shoeSet.executeUpdate();
				shoe.setShoeInfo("Edited Succesfully");
			}
			else
			{
				String ordersDelete = "DELETE FROM Orders " + 
						"WHERE itemId = ?;";
				shoeSet = con.prepareStatement(ordersDelete);
				shoeSet.setString(1, shoeID);
				shoeSet.executeUpdate();
				String shoeDelete = "DELETE FROM Shoes " + 
						"WHERE itemId = ?;";
				shoeSet = con.prepareStatement(shoeDelete);
				shoeSet.setString(1, shoeID);
				shoeSet.executeUpdate();
				
				
				shoe.setShoeInfo("Deleted Succesfully");
				
			}
			request.setAttribute("shoe", shoe);
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("editShoesConfirm.jsp");  
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
