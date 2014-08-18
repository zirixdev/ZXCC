package zirix.zxccmock;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class MockServletTemplate
 */
@WebServlet("/cadastro")
public class MockCadastroServlet extends HttpServlet {
	
	
	
	
	private static final long serialVersionUID = 1L;

	private static final int WORK_ID = 0;
	
    /**
     * Default constructor. 
     */
    public MockCadastroServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Set response content type
		   response.setContentType("text/html");

		   PrintWriter out = response.getWriter();
		   out.println("IT WORKS !!!");
		   
		   try {
			   
			   String query = "SELECT * FROM ZX_CC_PROD.USUARIO";
			   Connection con = getConnection();   
	
			   PreparedStatement stmt = con.prepareStatement(query);
			   
			   ResultSet set = stmt.executeQuery();
			   
			   while (set.next()) {
				   
				   String LOGIN = set.getString("LOGIN");
				   out.println(LOGIN);
			   }
			   			   			  
			   out.println("FINE !!!");
			   
	    	} catch (Exception ex) {
	    		ex.printStackTrace();
	    	} finally  {
	    		
	    		out.println("DONE MAN !!!");
	    	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

    public Connection getConnection() throws Exception {
        try {        	
        	// Look up the JNDI data source only once at init time
  	      Context envCtx = (Context) new InitialContext().lookup("java:comp/env"); 	      
  	      DataSource src = (DataSource) envCtx.lookup("jdbc/poolConn");
  	      
  	      return src.getConnection();
        }
        catch(Exception e) { throw e; }
    }

	public Connection getLocalConnection() throws SQLException {

    	String url="jdbc:mysql://192.168.0.31/ZX_CC_PROD";    	
    	Connection conn = null;

    	try {

    		Class.forName ("com.mysql.jdbc.Driver").newInstance ();
    		conn = DriverManager.getConnection (url, "root", "");

    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		return conn;
    	}
    }

}
