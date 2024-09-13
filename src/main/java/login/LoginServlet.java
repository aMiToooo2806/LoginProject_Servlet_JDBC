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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public static final String LOAD_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/userdb";
    public static final String PASSWORD = "is the";
    public static final String USERNAME = "root";
    Connection connection;


   
    public LoginServlet() {
        
    }

	
	public void init(ServletConfig config) throws ServletException {
		try {
			connection =  DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pword = request.getParameter("pword");
		try {
			PreparedStatement ps = connection.prepareStatement("select * from uinfo where uname = ?");
			ps.setString(1, uname);
			ResultSet rs = ps.executeQuery();
			PrintWriter pw = response.getWriter();
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<style>");
			pw.println("body {");
			pw.println("    background-color: black;");
			pw.println("    color: white;");
			pw.println("    font-family: Arial, sans-serif;");
			pw.println("    display: flex;");
			pw.println("    justify-content: center;");
			pw.println("    align-items: center;");
			pw.println("    height: 100vh;");
			pw.println("    margin: 0;");
			pw.println("}");
			pw.println("div {");
			pw.println("    text-align: center;");
			pw.println("    padding: 20px;");
			pw.println("    border: 2px solid white;");
			pw.println("    border-radius: 10px;");
			pw.println("    background-color: rgba(255, 255, 255, 0.1);");
			pw.println("    box-shadow: 0 0 10px rgba(255, 255, 255, 0.5);");
			pw.println("}");
			pw.println("h1 {");
			pw.println("    font-size: 24px;");
			pw.println("    margin-bottom: 15px;");
			pw.println("}");
			pw.println("p {");
			pw.println("    font-size: 18px;");
			pw.println("}");
			pw.println("</style>");
			pw.println("</head>");
			pw.println("<body>");
			pw.println("<div>");
			pw.println("<h1>Welcome to Our Mini Demonstration Project </h1>");
						
			if(rs.next()) {
			    pw.println("<p>Welcome, " + uname + "!</p>");
			} else {
			    pw.println("<p style='color: red;'>User not found</p>");
			}
						
			pw.println("</div>");
			pw.println("</body>");
			pw.println("</html>");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
