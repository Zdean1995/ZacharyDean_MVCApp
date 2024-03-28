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
 * Servlet implementation class ShoeManageServlet
 */
@WebServlet("/ShoeManageServlet")
public class ShoeManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoeManageServlet() {
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
			
			String shoeSelect = "SELECT * FROM Shoes";
			PreparedStatement shoeGet = con.prepareStatement(shoeSelect);
			ResultSet rs = shoeGet.executeQuery();
			
			String itemId;
			String itemName;
			String category;
			String shoeSize;
			String price;
			
			Shoe shoe = new Shoe();
			
			if (rs.next())
			{
				do {
					itemId = rs.getString(1);
					itemName = rs.getString(2);
					category = rs.getString(3);
					shoeSize = rs.getString(4);
					price = rs.getString(5);
					
					shoe.setShoeInfo(shoe.getShoeInfo()
							+ "<tr><td><div class = \"shoebox\">"
							+ "<table><tr><td>item ID</td><td>Item Name</td>"
							+ "<td>Category</td><td>Shoe Size</td><td>Price</td>"
							+ "<td colspan=2><form action=\"ShoeManageEditServlet\""
							+ "method=\"post\"><input type=\"hidden\" name=\"shoeId\" "
							+ "value = \""	+ itemId + "\"><input type=\"submit\" value"
							+ "= \"Edit\"></td></tr>"
							+ "<tr><td>" + itemId + "</td><td>" + itemName
							+ "</td><td>" + category + "</td><td>" + shoeSize
							+ "</td><td>" + price + "</td></tr></table>"
							+ "</div></td></tr>");
					
				}while(rs.next());
			}
			else
			{
				shoe.setShoeInfo(shoe.getShoeInfo()
						+ "<tr><td><div class = \"shoebox\">"
						+ "No Shoes Avalible</div></tr></td>");
			}
			request.setAttribute("shoe", shoe);
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("csrManageShoes.jsp");  
			rd.forward(request, response);			
			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
