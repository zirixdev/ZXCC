package zirix.zxcc.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.ZXCCConstants;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet( name="LoginServlet", urlPatterns = {"/services/login"}, loadOnStartup=1)
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
		String userLoginName = request.getParameter("login_login");
		String userPassword = request.getParameter("senha_login");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			String query = "SELECT " + ZXCCConstants.db_name + "USUARIO.COD_USUARIO FROM " + ZXCCConstants.db_name + "USUARIO WHERE LOGIN=\'" + userLoginName + "\'"	+ " AND SENHA=\'" + userPassword + "\'";

		    ArrayList<Object[]> values = DAOManager.getInstance().executeQuery(query);
		    Object[] retVal = (Object[])values.get(0);

		    if (((Integer)retVal[0]) != null){
		    	response.sendRedirect(ZXCCConstants.url_adress + "zx_cc.jsp?COD_USUARIO="+((Integer)retVal[0]));
		    }
		    else
		    	out.println("<h1> ERROR TRIM ... </h1>");
	    } catch (Exception ex) {
	    	response.sendRedirect(ZXCCConstants.url_adress + "index.jsp?LOGIN_FAILED=FAIL");
    		ex.printStackTrace(); // TODO zx_cc Error page !
		}  finally {
		}		 		   		   
		// TODO Auto-generated method stub
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
