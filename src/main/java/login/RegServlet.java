package login;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Servlet implementation class RegServlet
 */
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 public static final String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
	    public static final String URL = "jdbc:mysql://localhost:3306/userdb";
	    public static final String PASSWORD = "is the";
	    public static final String USERNAME = "root";
	    Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
    public void init(ServletConfig config) throws ServletException {
		try {
			connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String uname = request.getParameter("uname");
		String pword = request.getParameter("pword");
		try {
			PreparedStatement ps = connection.prepareStatement("insert into uinfo values (?,?,?,?)");
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, uname);
			ps.setString(4, pword);
			
			ps.executeUpdate();
			
			PrintWriter pw = response.getWriter();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<style>");
			pw.println("body {");
			pw.println("  display: flex;");
			pw.println("  justify-content: center;");
			pw.println("  align-items: center;");
			pw.println("  height: 100vh;");
			pw.println("  margin: 0;");
			pw.println("  background-color: #f0f0f0;");
			pw.println("  color: black;");
			pw.println("}");
			pw.println("h2 {");
			pw.println("  color: #4CAF50;"); // You can change this to any color you prefer
			pw.println("  font-family: Arial, sans-serif;");
			pw.println("  font-size: 2em;");
			pw.println("  margin-bottom: 20px;");
			pw.println("}");
			pw.println("a {");
			pw.println("  display: inline-block;");
			pw.println("  text-decoration: none;");
			pw.println("  background-color: #008CBA;");
			pw.println("  color: white;");
			pw.println("  padding: 10px 20px;");
			pw.println("  border-radius: 5px;");
			pw.println("  font-family: Arial, sans-serif;");
			pw.println("  font-size: 1.2em;");
			pw.println("  transition: background-color 0.3s ease;");
			pw.println("}");
			pw.println("a:hover {");
			pw.println("  background-color: #005f6b;");
			pw.println("}");
			pw.println("</style>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<center>");
			pw.println("<h2>User Registration Successful!</h2>");
			pw.println("<a href='login.html'>Login</a>");
			pw.println("</center>");
			pw.println("</body>");
			pw.println("</html>");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
