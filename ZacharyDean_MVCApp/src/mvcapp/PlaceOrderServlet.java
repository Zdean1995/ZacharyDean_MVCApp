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
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrderServlet() {
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
			
			String orderSelect = "SELECT itemId, itemName, price "
					+ "FROM Shoes WHERE shoeSize = ? AND category = ?";
			PreparedStatement orderGet = con.prepareStatement(orderSelect);
			
			String shoeSize = request.getParameter("size");
			String category = request.getParameter("category");
			Shoe shoe = new Shoe();
			
			orderGet.setString(1, shoeSize);
			orderGet.setString(2, category);
			
			ResultSet rs = orderGet.executeQuery();
			
			if(rs.next())
			{
				String itemId;
				String itemName;
				String price;
				

				do {
					itemId = rs.getString(1);
					itemName = rs.getString(2);
					price = rs.getString(3);
					
					shoe.setShoeInfo(shoe.getShoeInfo()
							+ "<tr><td><div class = \"shoebox\">"
							+ "<table><tr><td>item ID</td><td>Item Name</td>"
							+ "<td>Category</td><td>Shoe Size</td><td>Price</td>"
							+ "<td><form action=\"OrderProcessServlet\""
							+ "method=\"post\"><input type=\"hidden\" name=\"shoeID\" "
							+ "value = \""	+ itemId + "\">Quantity</td><td colspan=2>"
							+ "<input type=\"submit\" value=\"Order\"</tr>"
							+ "<tr><td>" + itemId + "</td><td>" + itemName
							+ "</td><td>" + category + "</td><td>" + shoeSize
							+ "</td><td>" + price + "</td><td><input"
							+ " type=\"text\" name=\"quantity\"></form></tr></table>"
							+ "</div></td></tr>");
					
				}while(rs.next());
				request.setAttribute("shoe", shoe);
				RequestDispatcher rd=
			    		   request.getRequestDispatcher("csrManageShoes.jsp");  
				rd.forward(request, response);
			}
			else
			{
				RequestDispatcher rd=
			    		   request.getRequestDispatcher("orderNoShoes.jsp");  
				rd.forward(request, response);
			}
			
			
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
