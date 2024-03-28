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
 * Servlet implementation class CSREditOrderServlet
 */
@WebServlet("/CSREditOrderServlet")
public class CSREditOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSREditOrderServlet() {
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
	    
	    try {
			Class.forName(DriverClass).newInstance();
			Connection con = null;
			con = DriverManager.getConnection(url, user, pass);
			
			String orderSelect = "SELECT itemID, quantity, status FROM Orders "
					+ "WHERE orderId = ?";
			
			String shoeSelect = "SELECT shoeSize FROM Shoes "
					+ "WHERE itemId = ?";
			
			PreparedStatement infoGet = con.prepareStatement(orderSelect);
			PreparedStatement shoeInfoGet = con.prepareStatement(shoeSelect);
			
			infoGet.setString(1, orderID);
			ResultSet rs = infoGet.executeQuery();
			rs.next();
			
			String shoeID = rs.getString(1);
			String quantity = rs.getString(2);
			String status = rs.getString(3);
			
			shoeInfoGet.setString(1, shoeID);
			
			rs = shoeInfoGet.executeQuery();
			rs.next();
			
			String shoeSize = rs.getString(1);
			
			Order order = new Order();
			
			order.setOrderID(orderID);
			order.setQuantity(quantity);
			order.setShoeSize(shoeSize);
			order.setStatus(status);
			
			request.setAttribute("order", order);

			RequestDispatcher rd=
		    		   request.getRequestDispatcher("csrEditOrder.jsp");  
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
