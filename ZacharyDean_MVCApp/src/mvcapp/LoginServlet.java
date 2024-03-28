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
 * Servlet implementation class LoginSevlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("usertype");
		
		String url="jdbc:mysql://localhost:3306/mvcdb?autoconnect"
				+ "=true&useSSL=false";
	    String DriverClass="com.mysql.jdbc.Driver";
	    String user="root";
	    String pass="kmgzkjjfagzz";
	    
	    try {
			Class.forName(DriverClass).newInstance();
			Connection con = null;
			con = DriverManager.getConnection(url, user, pass);
			
			
			
			if(userType.equals("customer"))
			{
				String selectSQL = "SELECT firstName, lastName, customerId FROM Customers "
						+ "WHERE userName = ? AND password = ?";
				PreparedStatement pstmt = con.prepareStatement(selectSQL);

				pstmt.setString(1, userName);
				pstmt.setString(2, password);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					User loginUser = new User();
					loginUser.setUserName(userName);
					loginUser.setFirstName(rs.getString(1));
					loginUser.setLastName(rs.getString(2));
					loginUser.setUserID(rs.getString(3));
					
					
					request.setAttribute("user",loginUser);
					
					Cookie c = new Cookie("userName", userName);
					
					response.addCookie(c);
					
					RequestDispatcher rd=
				    		   request.getRequestDispatcher("customerHome.jsp");  
					rd.forward(request, response);
				}
				else
				{
					ErrorMessage loginError = new ErrorMessage();
					loginError.setMessage("Incorrect Username or Password");
					
					request.setAttribute("loginError",loginError);
					
					RequestDispatcher rd=
				    		   request.getRequestDispatcher("loginError.jsp");  
					rd.forward(request, response);
				}
				
			}
			else
			{
				String selectSQL = "SELECT firstName, lastName, employeeId FROM CSR "
						+ "WHERE userName = ? AND password = ?";
				PreparedStatement pstmt = con.prepareStatement(selectSQL);

				pstmt.setString(1, userName);
				pstmt.setString(2, password);
				
				ResultSet rs = pstmt.executeQuery();
				
				if(rs.next())
				{
					User loginUser = new User();
					loginUser.setUserName(userName);
					loginUser.setFirstName(rs.getString(1));
					loginUser.setLastName(rs.getString(2));
					loginUser.setUserID(rs.getString(3));
					
					
					request.setAttribute("user",loginUser);
					
					Cookie c = new Cookie("userName", userName);
					
					response.addCookie(c);
					
					RequestDispatcher rd=
				    		   request.getRequestDispatcher("csrHome.jsp");  
					rd.forward(request, response);
				}
				else
				{
					ErrorMessage loginError = new ErrorMessage();
					loginError.setMessage("Incorrect Username or Password");
					
					request.setAttribute("loginError",loginError);
					
					RequestDispatcher rd=
				    		   request.getRequestDispatcher("loginError.jsp");  
					rd.forward(request, response);
				}
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
