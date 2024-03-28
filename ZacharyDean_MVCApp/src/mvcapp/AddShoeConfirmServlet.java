/*
 * Name:Zachary Dean
 * Student Number: 991353674
 * To whoever marks this, I'm genuinely sorry but I finished at 11:50pm on Sunday before it was due and don't have
 * time to comment the code at all.  God help you.
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
 * Servlet implementation class AddShoeConfirmServlet
 */
@WebServlet("/AddShoeConfirmServlet")
public class AddShoeConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShoeConfirmServlet() {
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
	    
		String shoeName = request.getParameter("shoeName");
		String shoeSize[] = request.getParameterValues("sizes");
		String price = request.getParameter("price");
		String category = request.getParameter("category");
		Shoe shoe = new Shoe();
		shoe.setShoeName(shoeName);
		
		if(shoeSize == null)
		{
			ErrorMessage error = new ErrorMessage();
			
			error.setMessage("No Shoe Size Selected");
			request.setAttribute("error", error);
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("addShoesError.jsp");  
			rd.forward(request, response);
			
		}
		
		if(Double.parseDouble(price)<= 0)
		{
			ErrorMessage error = new ErrorMessage();
			
			error.setMessage("Shoes Can't be Free<br>This Ain't a Charity");
			request.setAttribute("error", error);
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("addShoesError.jsp");  
			rd.forward(request, response);
		}
		
		if(category == null)
		{
			ErrorMessage error = new ErrorMessage();
			
			error.setMessage("No Category Selected");
			request.setAttribute("error", error);
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("addShoesError.jsp");  
			rd.forward(request, response);
		}
	    
	    try {
			Class.forName(DriverClass).newInstance();
			Connection con = null;
			con = DriverManager.getConnection(url, user, pass);
			
			
			PreparedStatement shoeSet;
			
			
			String shoeAdd = "INSERT INTO Shoes (itemName, category"
					+ ", shoeSize, price) VALUES ( ?, ?, ?, ?)";
			shoeSet = con.prepareStatement(shoeAdd);
			for(int x = 0; x < shoeSize.length; x++)
			{
				shoeSet.setString(1, shoeName);
				shoeSet.setString(2, category);
				shoeSet.setString(3, shoeSize[x]);
				shoeSet.setString(4, price);
				shoeSet.executeUpdate();
			}
			request.setAttribute("shoe", shoe);
			
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("addShoesConfirm.jsp");  
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
