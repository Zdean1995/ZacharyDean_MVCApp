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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CustomerInfoServlet
 */
@WebServlet("/CustomerInfoServlet")
public class CustomerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerInfoServlet() {
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
			
			Cookie[] cookies = request.getCookies();
			
			Cookie c = cookies[0];
			
			
			String selectSQL = "SELECT firstName, lastName, customerId FROM Customers "
					+ "WHERE userName = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
				
			String userName = c.getValue();
			
			pstmt.setString(1, userName);
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			User loginUser = new User();
			loginUser.setUserName(userName);
			loginUser.setFirstName(rs.getString(1));
			loginUser.setLastName(rs.getString(2));
			loginUser.setUserID(rs.getString(3));
			
			
			
			String orderSelect = "SELECT * FROM Orders "
					+ "WHERE customerId = ?";
			
			String shoeSelect = "SELECT * FROM Shoes "
					+ "WHERE itemId = ?";
			
			
			PreparedStatement infoGet = con.prepareStatement(orderSelect);
			PreparedStatement shoeInfoGet = con.prepareStatement(shoeSelect);
			
			infoGet.setString(1, loginUser.getUserID());
			
			ResultSet infoResult = infoGet.executeQuery();
			ResultSet shoeResult;
			
			CustomerInfo custInfo = new CustomerInfo();
			if(infoResult.next())
			{
				String orderID;
				String itemID;
				String itemName;
				String category;
				String shoeSize;
				String price;
				String orderDate;
				String quantity;
				double totalPrice;
				String status;
				do
				{
					orderID = infoResult.getString(1);
					itemID = infoResult.getString(3);
					orderDate = infoResult.getString(4);
					quantity = infoResult.getString(5);
					status = infoResult.getString(6);

					shoeInfoGet.setString(1, itemID);
					shoeResult = shoeInfoGet.executeQuery();
					
					shoeResult.next();
					
					itemName = shoeResult.getString(2);
					category = shoeResult.getString(3);
					shoeSize = shoeResult.getString(4);
					price = shoeResult.getString(5);
					totalPrice = (Double.parseDouble(quantity) * 
							Double.parseDouble(price));
					
					custInfo.setOrders(custInfo.getOrders()
							+ "<tr><td><div class=orderbox><table>"
							+ "<tr><td>Order ID</td><td>Item ID </td>"
							+ "<td>Item Name</td><td>Category</td>"
							+ "<td>Size</td>"
							+ "<td>Order Date</td>"
							+ "<td>Price per Item</td>"
							+ "<td>Quantity</td><td>Total Price</td>"
							+ "<td>Order Status</td>"
							+ "<td rowspan=2>"
							+ "<form action=\"EditOrderServlet\" method=\"post\">"
							+ "<input type=\"hidden\" name=\"orderID\" value=\"" + orderID
							+ "\"><input type=\"submit\" value=\"Edit Order "
							+ orderID + "\"");
							if (!(status.equals("Order Placed")))
							{
								custInfo.setOrders(custInfo.getOrders() + "disabled");
							}
							custInfo.setOrders(custInfo.getOrders() 
							+ "></form></td></tr>"
							+ "<tr><td>" + orderID + "</td>"
							+ "<td>" + itemID + "</td>"
							+ "<td>" + itemName + "</td>"
							+ "<td>" + category + "</td>"
							+ "<td>" + shoeSize + "</td>"
							+ "<td>" + orderDate + "</td>"
							+ "<td>" + price + "</td>"
							+ "<td>" + quantity + "</td>"
							+ "<td>" + totalPrice + "</td>"
							+ "<td>" + status + "</td></tr>"
							+ "</table></div></td></tr>");
				}while((infoResult.next()));			
			}
			else
			{
				custInfo.setOrders("<tr><td><div class=\"orderbox\""
						+ ">No Orders Placed</div></td><tr>");
			}
			
			String userSelect = "SELECT address, city, postalCode FROM Customers "
					+ "WHERE userName = ?";
			
			PreparedStatement custGet = con.prepareStatement(userSelect);
			custGet.setString(1, c.getValue());
			
			ResultSet userResult = custGet.executeQuery();
			userResult.next();
			
			custInfo.setAddress(userResult.getString(1));
			custInfo.setCity(userResult.getString(2));
			custInfo.setPostalCode(userResult.getString(3));
			
			request.setAttribute("custInfo",custInfo);
			request.setAttribute("user",loginUser);
			
			con.close();
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("customerViewInfo.jsp");  
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
