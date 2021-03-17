

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String fullname=request.getParameter("fullname");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String password=request.getParameter("password");
		String confirmpassword=request.getParameter("cpassword");
		System.out.println(username);
		System.out.println(fullname);
		System.out.println(email);
		System.out.println(mobile);
		System.out.println(password);
		System.out.println(confirmpassword);
		
		 try {
	            Class.forName("oracle.jdbc.OracleDriver");
	            System.out.println("Driver Loaded");
	            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Pammu2002");
	            System.out.println("connected to database");
	            
	            String sql="insert into register values(?,?,?,?,?,?)";
	            PreparedStatement pstmt=con.prepareStatement(sql);
	            pstmt.setString(1,username);
	            pstmt.setString(2,fullname);
	            pstmt.setString(3,email);
	            pstmt.setString(4, mobile);
	            pstmt.setString(5, password);
	            pstmt.setString(6, confirmpassword);
	            ResultSet rs=pstmt.executeQuery();
	            
	            PreparedStatement pstmt1=con.prepareStatement("insert into login values(?,?)");
	            pstmt1.setString(1,username);
	            pstmt1.setString(2,password);
	            ResultSet rs1=pstmt1.executeQuery();
	            if(rs.next()&&rs1.next()) {
	            	RequestDispatcher rd=request.getRequestDispatcher("home.html");
	            	System.out.println("Register Succesfully");
	            	rd.forward(request, response);
	            }
	            else {
	            	RequestDispatcher rd=request.getRequestDispatcher("failr.html");
	            	System.out.println("Register Failed");
	            	rd.forward(request, response);
	            }
	            con.close();
	        }
	        catch(Exception e){
	        	RequestDispatcher rd1=request.getRequestDispatcher("exception.html");
            	System.out.println("Already have a account with same  Information");
            	rd1.forward(request, response);
	            System.out.println(e);
	            
	        }
		  
	}
}


