/*ZIRIX CONTROL CENTER - MAIN PAGE
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server;

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

import zirix.zxcc.server.dao.ClienteProspeccaoDAO;
import zirix.zxcc.server.dao.ContatoProspeccaoDAO;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.dao.EmailProspeccaoDAO;
import zirix.zxcc.server.dao.PkList;
import zirix.zxcc.server.mock.*;

/**
 * Servlet implementation class ClienteService
 */
@WebServlet( name="ClienteService", urlPatterns = {"/services/prospect"}, loadOnStartup=1)
public class ClienteProspectServiceServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ClienteProspectServiceServlet() {
	    super();
	    // TODO Auto-generated constructor stub
	}
	
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Set response content type
		   response.setContentType("text/html");
	
		   PrintWriter out = response.getWriter();
	
		   String OP_CODE = request.getParameter("OP_CODE");
		   String COD_USUARIO = request.getParameter("COD_USUARIO").trim();

		   String WORK_ID = request.getParameter("WORK_ID");	
		   try {
			   ClienteProspeccaoDAO daoClienteProspeccao = new ClienteProspeccaoDAO();
			   PkList pkList;
	
			   if ((OP_CODE.compareTo("UPDATE") == 0) || (OP_CODE.compareTo("CREATE") == 0)) {
	
				   String TIPO = request.getParameter("TIPO").trim();
				   daoClienteProspeccao.setAttValueFor("TIPO", TIPO);
	
				   String NOME = request.getParameter("NOME").trim();
				   daoClienteProspeccao.setAttValueFor("NOME", NOME);
	
				   String NOME_FANTASIA = request.getParameter("NOME_FANTASIA").trim();
				   daoClienteProspeccao.setAttValueFor("NOME_FANTASIA", NOME_FANTASIA);
	
				   if (OP_CODE.compareTo("CREATE") == 0){
					   String DATA_INGRESSO = request.getParameter("DATA_INGRESSO").trim();
					   daoClienteProspeccao.setAttValueFor("DATA_INGRESSO", DATA_INGRESSO);
				   }
	
				   String COD_VENDEDOR = request.getParameter("COD_VENDEDOR").trim();
				   daoClienteProspeccao.setAttValueFor("COD_VENDEDOR", COD_VENDEDOR);
				   
				   daoClienteProspeccao.setAttValueFor("DELETED", 0);
	
				   if (OP_CODE.compareTo("UPDATE") == 0){
	
					   String COD_CLIENTE = request.getParameter("COD_CLIENTE_PROSPECCAO").trim();
					   pkList = ClienteProspeccaoDAO.createKey("COD_CLIENTE_PROSPECCAO", Integer.parseInt(COD_CLIENTE));
	
					   daoClienteProspeccao.setPkList(pkList);
					   daoClienteProspeccao.update();
				   }
				   else
				   {
					   daoClienteProspeccao.Create();

					   int pkListValue = 0;
					   Vector<String[]> CodCliente_ = new Vector<String[]>();
					   try {

						   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT COD_CLIENTE_PROSPECCAO "
								   + " 											                 FROM " + ZXMain.DB_NAME_ + "CLIENTE_PROSPECCAO "
								   + "                                                          WHERE NOME = '" + NOME + "'");
	
						   for (int i=0;i < values.size();i++) {
							   String[] attList = new String[1];
							   attList[0] = values.get(i)[0].toString();;
							   CodCliente_.add(attList);
						   }
					   }catch (SQLException ex) {
						   ex.printStackTrace();
					   }  finally {
						   pkListValue = Integer.parseInt(CodCliente_.elementAt(0)[0].trim());
					   }

					   if(pkListValue != 0){
						   int arraysize = Integer.parseInt(request.getParameter("QCTO"));
						   for(int d = 0 ; d < arraysize ; d++){
							   ContatoProspeccaoDAO daoContatoProspeccao = new ContatoProspeccaoDAO();
							   
							   daoContatoProspeccao.setAttValueFor("COD_CLIENTE_PROSPECCAO",pkListValue);
							   
							   String DDD_ = request.getParameter("DDD_"+d).trim();
							   daoContatoProspeccao.setAttValueFor("DDD",DDD_);
							   System.err.println("\nDDD = " + DDD_);
							   
							   String NUMERO_ = request.getParameter("NUMCTO_"+d).trim();
							   daoContatoProspeccao.setAttValueFor("NUMERO",NUMERO_);
							   System.err.println("\nNumero = " + NUMERO_);
							   
							   String COD_CONTATO_ = request.getParameter("TIPOCTO_"+d).trim();
							   daoContatoProspeccao.setAttValueFor("COD_CONTATO",COD_CONTATO_);
							   System.err.println("\nCODIGO = " + COD_CONTATO_);
							   
							   String COD_PAIS_ = request.getParameter("PAISCTO_"+d).trim();
							   daoContatoProspeccao.setAttValueFor("COD_PAIS",COD_PAIS_);
							   System.err.println("\nPAIS = " + COD_PAIS_);

							   System.err.println("\nWill Create ContatoProspeccao DAO");
							   daoContatoProspeccao.Create();
							   System.err.println("\nHad Create ContatoProspeccao DAO?");
						   }
	
						   arraysize = Integer.parseInt(request.getParameter("QMAIL"));
						   for(int d = 0 ; d < arraysize ; d++){
							   EmailProspeccaoDAO daoEmailProspeccao = new EmailProspeccaoDAO();
	
							   daoEmailProspeccao.setAttValueFor("COD_CLIENTE_PROSPECCAO",pkListValue);
		
							   String EMAIL_ = request.getParameter("MAIL_"+d).trim();
							   daoEmailProspeccao.setAttValueFor("EMAIL",EMAIL_);
							   System.err.println("\nEmail_PROSPECCAO = " + EMAIL_);

							   System.err.println("\nWill Create EmailProspeccao DAO");
							   daoEmailProspeccao.Create();
							   System.err.println("\nHad Create EmailProspeccao DAO");
						   }
					   }else{
						   out.println("Error on ClienteProspectServiceServlet... " + "\nCOD_CLIENTE não encontrado! ");
					   }
				   }
				   if(WORK_ID.compareTo("0") != 0){
					   MockEvaluator eval = new MockEvaluator(Integer.parseInt(WORK_ID));
					   if(eval.endWork()){
						   
					   }
				   }
			   }

			   else if (OP_CODE.compareTo("DELETE") == 0){

				   String COD_CLIENTE = request.getParameter("COD_CLIENTE");
				   pkList = ClienteProspeccaoDAO.createKey("COD_CLIENTE", Integer.parseInt(COD_CLIENTE));

				   daoClienteProspeccao.setPkList(pkList);
				   daoClienteProspeccao.delete();
			   }
		   } catch (Exception e) {
				   out.println("Error on ClienteProspectServiceServlet... " + ' ' + e.getMessage());
		   }

		   response.sendRedirect(ZXMain.URL_ADRESS_ + "zx_cc.jsp?COD_USUARIO=" + COD_USUARIO);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
