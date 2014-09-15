/*ZIRIX CONTROL CENTER - MOCK FLUX SERVICE SERVLET
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server.mock;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zirix.zxcc.server.ZXMain;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.mock.dao.*;
/**
 * Servlet implementation class MockFluxServiceServlet
 */
@WebServlet( name="MockFluxServiceServlet", urlPatterns = {"/services/startservlet"}, loadOnStartup=1)
public class MockFluxServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MockFluxServiceServlet() {
	    super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();

	   int PROCESS_ID;
	   String OP_CODE = request.getParameter("OP_CODE");
	   String COD_USUARIO = request.getParameter("COD_USUARIO").trim();
	   Vector<String[]> CodProcessWork = new Vector<String[]>();
	   Vector<String[]> NameProcess = new Vector<String[]>();
	   SchedProcessDAO daoSchedProcess = new SchedProcessDAO();
	   
	   try{
		   if (OP_CODE.compareTo("STARTFLUX") == 0) {
			   int DEFINED_PROCESS_ID = Integer.parseInt(request.getParameter("PROCESS_ID"));
			   
			   try{
				   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT PROCESS_NAME FROM " + ZXMain.DB_NAME_ + "DEFINED_PROCESS WHERE PROCESS_ID = " + DEFINED_PROCESS_ID);
				   for (int i=0;i < values.size();i++) {
					   String[] attList = new String[1];
					   attList[0] = values.get(i)[0].toString();
					   NameProcess.add(attList);
				   }
			   }catch (SQLException ex) {
				   out.println("Error on MockFluxServiceServlet -> SELECT SCOPE_IDENTITY() ... " + ex.getMessage());
			   }finally {
				   daoSchedProcess.setAttValueFor("PROCESS_NAME", (NameProcess.elementAt(0)[0].trim()).toString());
			   }
			   daoSchedProcess.setAttValueFor("STATE_ID", 1);
			   daoSchedProcess.Create();

			   if(ZXMain.LOCAL_.compareTo("PROD")==0){
				   try{
					   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT LAST_INSERT_ID();");
					   for (int i=0;i < values.size();i++) {
						   String[] attList = new String[1];
						   attList[0] = values.get(i)[0].toString();
						   CodProcessWork.add(attList);
					   }
				   }catch (SQLException ex) {
					   out.println("Error on MockFluxServiceServlet... " + ex.getMessage());
				   }finally{
					   PROCESS_ID = Integer.parseInt(CodProcessWork.elementAt(0)[0].trim());
				   }
			   }else{
				   try{
					   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT MAX(PROCESS_ID) FROM " + ZXMain.DB_NAME_ + "SCHED_PROCESS;");
					   for (int i=0;i < values.size();i++) {
						   String[] attList = new String[1];
						   attList[0] = values.get(i)[0].toString();
						   CodProcessWork.add(attList);
					   }
				   }catch (SQLException ex) {
					   out.println("Error on MockFluxServiceServlet... " + ex.getMessage());
				   }finally{
					   PROCESS_ID = Integer.parseInt(CodProcessWork.elementAt(0)[0].trim());
				   }
			   }
			   MockSchedule.createSchedWork(PROCESS_ID,DEFINED_PROCESS_ID,1,0);
			   response.sendRedirect(ZXMain.URL_ADRESS_ + "zx_cc.jsp?COD_USUARIO=" + COD_USUARIO);
		   }
		   /********SERVER APENAS PARA FORÇAR UM ENDWORK********/
		   /******************FINALIDADE TESTE******************/
		   else if (OP_CODE.compareTo("ENDWORK") == 0) {
			   int WORK_ID = Integer.parseInt(request.getParameter("WORK_ID"));
			   MockEvaluator eval = new MockEvaluator(WORK_ID);
			   if(eval.endWork()){
				   MockSchedule sched = new MockSchedule(WORK_ID);
				   sched.changeState();
			   }
			   response.sendRedirect(ZXMain.URL_ADRESS_ + "zx_cc.jsp?COD_USUARIO=" + COD_USUARIO);
		   }
		   /****************************************************/
	   }
	   catch (Exception e){
		   out.println("Error on MockFluxServiceServlet... " + e.getMessage());
	   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
