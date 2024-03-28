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
 * Servlet implementation class CSRHomeRedirectServlet
 */
@WebServlet("/CSRHomeRedirectServlet")
public class CSRHomeRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CSRHomeRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="jdbc:mysql://localhost:3306/mvcdb?autoconnect"
				+ "=true&useSSL=false";
	    String DriverClass="com.mysql.jdbc.Driver";
	    String user="root";
	    String pass="kmgzkjjfagzz";
	    
	    try {
			Class.forName(DriverClass).newInstance();
			Connection con = null;
			con = DriverManager.getConnection(url, user, pass);
			
			Cookie[] cookies = request.getCookies();
			
			Cookie c = cookies[0];
			
			
			String selectSQL = "SELECT firstName, lastName, employeeId FROM CSR "
					+ "WHERE userName = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
				
			String userName = c.getValue();
			
			pstmt.setString(1, c.getValue());
			
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			User loginUser = new User();
			loginUser.setUserName(userName);
			loginUser.setFirstName(rs.getString(1));
			loginUser.setLastName(rs.getString(2));
			loginUser.setUserID(rs.getString(3));
				
			
			request.setAttribute("user",loginUser);
			
			
				
			RequestDispatcher rd=
		    		   request.getRequestDispatcher("csrHome.jsp");  
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
