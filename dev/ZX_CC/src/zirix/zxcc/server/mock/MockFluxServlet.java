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
 * Servlet implementation class MockFluxServlet
 */
@WebServlet( name="MockFluxServlet", urlPatterns = {"/services/startservlet"}, loadOnStartup=1)
public class MockFluxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MockFluxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   response.setContentType("text/html");
		   PrintWriter out = response.getWriter();

		   int ProcessIdKey;
		   String Query = "";
		   String OP_CODE = request.getParameter("OP_CODE");
		   String COD_USUARIO = request.getParameter("COD_USUARIO").trim();
		   Vector<String[]> CodProcessWork = new Vector<String[]>();
		   Vector<String[]> NameProcess = new Vector<String[]>();
		   SchedProcessDAO daoSchedProcess = new SchedProcessDAO();
		   
		   try{
			   if (OP_CODE.compareTo("STARTFLUX") == 0) {
				   String PROCESS_ID = request.getParameter("PROCESS_ID");
				   
				   try{
					   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT PROCESS_NAME FROM " + ZXMain.DB_NAME_ + "DEFINED_PROCESS WHERE PROCESS_ID = " + PROCESS_ID);
					   for (int i=0;i < values.size();i++) {
						   String[] attList = new String[1];
						   attList[0] = values.get(i)[0].toString();
						   NameProcess.add(attList);
					   }
				   }catch (SQLException ex) {
					   out.println("Error on MockFluxServlet -> SELECT SCOPE_IDENTITY() ... " + ex.getMessage());
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
						   out.println("Error on MockFluxServlet... " + ex.getMessage());
					   }finally{
						   ProcessIdKey = Integer.parseInt(CodProcessWork.elementAt(0)[0].trim());
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
						   out.println("Error on MockFluxServlet... " + ex.getMessage());
					   }finally{
						   ProcessIdKey = Integer.parseInt(CodProcessWork.elementAt(0)[0].trim());
					   }
				   }
				   createSchedWork(ProcessIdKey,PROCESS_ID,response,1);
				   response.sendRedirect(ZXMain.URL_ADRESS_ + "zx_cc.jsp?COD_USUARIO=" + COD_USUARIO);
			   }
		   }
		   catch (Exception e){
			   out.println("Error on MockFluxServlet... " + e.getMessage());
		   }
		   
	}
	
	public void createSchedWork(int ProcessIdKey, String PROCESS_ID, HttpServletResponse response, int stateID) throws IOException, SQLException{
		   PrintWriter out = response.getWriter();
		   SchedWorkDAO daoSchedWork = new SchedWorkDAO();
		   Vector<String[]> DefinedWork = new Vector<String[]>();
		   try {
			   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT WORK_NAME, RESTRICTION_ID, PROCESS_STATE_ID, WORK_ALERT_ID, DEPENDENCY_WORK_ID, WORK_GROUP_ID "
			   		+ " FROM " + ZXMain.DB_NAME_ + "DEFINED_WORK WHERE PROCESS_STATE_ID=" + stateID + " AND PROCESS_ID=" + PROCESS_ID);
			   for (int i=0;i < values.size();i++) {
				   String[] attList = new String[6];
				   attList[0] = (String) values.get(i)[0];
				   attList[1] = values.get(i)[1].toString();
				   attList[2] = values.get(i)[2].toString();
				   attList[3] = values.get(i)[3].toString();
				   attList[4] = values.get(i)[4].toString();
				   attList[5] = values.get(i)[5].toString();
				   DefinedWork.add(attList);
			   }
		   }catch (SQLException ex) {
			   out.println("Error on MockFluxServlet -> SELECT LAST_INSERT_ID() ... " + ex.getMessage());
		   }finally {
			   for(int i=0; i<DefinedWork.size();i++){
				   daoSchedWork.setAttValueFor("WORK_NAME",(DefinedWork.elementAt(i)[0].trim()).toString());
				   daoSchedWork.setAttValueFor("RESTRICTION_ID",Integer.parseInt(DefinedWork.elementAt(i)[1].trim()));
				   daoSchedWork.setAttValueFor("PROCESS_STATE_ID",Integer.parseInt(DefinedWork.elementAt(i)[2].trim()));
				   daoSchedWork.setAttValueFor("WORK_ALERT_ID",Integer.parseInt(DefinedWork.elementAt(i)[3].trim()));
				   daoSchedWork.setAttValueFor("DEPENDENCY_WORK_ID",Integer.parseInt(DefinedWork.elementAt(i)[4].trim()));
				   daoSchedWork.setAttValueFor("WORK_GROUP_ID",Integer.parseInt(DefinedWork.elementAt(i)[5].trim()));
				   daoSchedWork.setAttValueFor("PROCESS_ID",ProcessIdKey);
				   daoSchedWork.setAttValueFor("DEFINED_PROCESS_ID",PROCESS_ID);
			    	
				   daoSchedWork.Create();
			   }
		   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
