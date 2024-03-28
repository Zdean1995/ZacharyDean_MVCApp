/*
 * Name:Zachary Dean
 * Student Number: 991353674
 */ 
package mvcapp;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordconfirm");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String postalCode = request.getParameter("postalcode");
		
		ErrorMessage error = new ErrorMessage();
		if (!(password.equals(passwordConfirm)))
		{
			error.setMessage("Passwords do not Match");
			request.setAttribute("error",error);
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("registerError.jsp");  
	        rd.forward(request, response); 
		}
		else
		{
			String url="jdbc:mysql://localhost:3306/MVCDB?autoconnect"
					+ "=true&useSSL=false&allowPublicKeyRetrieval=true";
		    String DriverClass="com.mysql.jdbc.Driver";
		    String user="root";
		    String pass="kmgzkjjfagzz";
		    
		    try {
				Class.forName(DriverClass).newInstance();
				Connection con = null;
				con = DriverManager.getConnection(url, user, pass);
				
				String selectSQL = "SELECT userName FROM Customers "
						+ "WHERE userName = ?";
				PreparedStatement userCheck = con.prepareStatement(selectSQL);

				userCheck.setString(1, username);
				
				ResultSet rs = userCheck.executeQuery();
				
				if(rs.next())
				{
					error.setMessage("Username in Use");
					request.setAttribute("error",error);
					RequestDispatcher rd=
				    		   request.getRequestDispatcher("registerError.jsp");  
			        rd.forward(request, response); 
				}
				else
				{
					String insertSQL = "INSERT INTO Customers" + 
							"(userName,password,firstName,lastName,"
							+ "address, city, postalCode)" + 
							"VALUES(?, ?, ?, ?, ?, ?, ?);";
					PreparedStatement pstmt = con.prepareStatement(insertSQL);
					pstmt.setString(1, username);
					pstmt.setString(2, password);
					pstmt.setString(3, firstName);
					pstmt.setString(4, lastName);
					pstmt.setString(5, address);
					pstmt.setString(6, city);
					pstmt.setString(7, postalCode);
					pstmt.executeUpdate();
					
					error.setMessage(username);
					request.setAttribute("error",error);
					
					
					RequestDispatcher rd=
				    		   request.getRequestDispatcher("registerConfirm.jsp");  
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
