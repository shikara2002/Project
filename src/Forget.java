import java.io.IOException;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Forget
 */
@WebServlet("/Forget")
public class Forget extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Forget() {
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
		String password=request.getParameter("cpass");
		String cpassword=request.getParameter("repass");
		System.out.println(username);
		System.out.println(password);
		System.out.println(cpassword);
		
		 try {
	            Class.forName("oracle.jdbc.OracleDriver");
	            System.out.println("Driver Loaded");
	            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","Pammu2002");
	            System.out.println("connected to database");
	            String sql="update register set password= ?,confirmpassword= ? where  username=?";
	            String sql1="update login set password=? where username=?";
	            PreparedStatement pstmt=con.prepareStatement(sql);
	            pstmt.setString(1,password);
	            pstmt.setString(2,cpassword);
	            pstmt.setString(3,username);
	            ResultSet rs=pstmt.executeQuery();
	            PreparedStatement pstmt1=con.prepareStatement(sql1);
	            pstmt1.setString(1,password);
	            pstmt1.setString(2,username);
	            ResultSet rs1=pstmt1.executeQuery();
	            if(rs.next() && rs1.next()) {
	            	RequestDispatcher rd=request.getRequestDispatcher("login.html");
	            	System.out.println("Password Changed Succesfully");
	            	rd.forward(request, response);
	            }
	            else {
	            	RequestDispatcher rd=request.getRequestDispatcher("forgetf.html");
	            	System.out.println("Password Change  Failed");
	            	rd.forward(request, response);
	            }
	            con.close();
	        }
	        catch(Exception e){
	        	
	            System.out.println(e);
	            
	        }
	}
	

}
