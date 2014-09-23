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

import zirix.zxcc.server.dao.*;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.dao.PkList;

@WebServlet( name="AgendamentoServiceServlet", urlPatterns = {"/services/agendamento"}, loadOnStartup=1)
public class AgendamentoServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AgendamentoServiceServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String OP_CODE = request.getParameter("OP_CODE");
		String COD_USUARIO = request.getParameter("COD_USUARIO").trim();
		try {
			AgendamentoDAO daoAgendamento = new AgendamentoDAO();
			PkList pkList;
			if ((OP_CODE.compareTo("UPDATE") == 0) || (OP_CODE.compareTo("CREATE") == 0)) {
			   String COD_PEDIDO = request.getParameter("CODPEDIDO").trim();
			   daoAgendamento.setAttValueFor("COD_PEDIDO", COD_PEDIDO);
			   
			   
			   
			   String END_AGEND = request.getParameter("END_AGEND").trim();
			   if(END_AGEND.compareTo("nao") == 0){
				   
			   }
			   if (OP_CODE.compareTo("UPDATE") == 0){
				   String COD_AGENDAMENTO = request.getParameter("COD_AGENDAMENTO").trim();
				   pkList = AgendamentoDAO.createKey("COD_AGENDAMENTO", Integer.parseInt(COD_AGENDAMENTO));
				   daoAgendamento.setPkList(pkList);
				   daoAgendamento.update();
			   }else{
				   daoAgendamento.Create();
				   int pkListValue = 0;
				   Vector<String[]> CodAgendamento_ = new Vector<String[]>();
				   try {
					   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT COD_CLIENTE "
							   + " 											                 FROM " + ZXMain.DB_NAME_ + "CLIENTE "
							   + "                                                          WHERE NOME = ");
					   for (int i=0;i < values.size();i++) {
						   String[] attList = new String[1];
						   attList[0] = values.get(i)[0].toString();;
						   CodAgendamento_.add(attList);
					   }
				   }catch (SQLException ex) {
					   ex.printStackTrace();
				   }  finally {
					   pkListValue = Integer.parseInt(CodAgendamento_.elementAt(0)[0].trim());
				   }
				   if(pkListValue != 0){
					   int arraysize = Integer.parseInt(request.getParameter("QDOC"));
					   for(int d = 0 ; d < arraysize ; d++){
						   DocumentoClienteDAO daoDocumentoCliente = new DocumentoClienteDAO();

						   daoDocumentoCliente.setAttValueFor("COD_CLIENTE",pkListValue);

						   String COD_DOCUMENTO_ = request.getParameter("TIPODOC_"+d).trim();
						   daoDocumentoCliente.setAttValueFor("COD_DOCUMENTO",COD_DOCUMENTO_);

						   String NUMERO_ = request.getParameter("NUMDOC_"+d).trim();
						   daoDocumentoCliente.setAttValueFor("NUMERO",NUMERO_);

						   String DATA_EMISSAO_ = request.getParameter("DTDOC_"+d).trim();
						   daoDocumentoCliente.setAttValueFor("DATA_EMISSAO",DATA_EMISSAO_);

						   String ORGAO_EMISSOR_ = request.getParameter("ORGDOC_"+d).trim();
						   daoDocumentoCliente.setAttValueFor("ORGAO_EMISSOR",ORGAO_EMISSOR_);

						   daoDocumentoCliente.setAttValueFor("DELETED",0);
						   daoDocumentoCliente.Create();
					   }
				   }else{
					   out.println("Error on ClienteServiceServlet... " + "\nCOD_CLIENTE não encontrado! ");
				   }
			   }
			   response.sendRedirect(ZXMain.URL_ADRESS_ + "/zx_cc.jsp?COD_USUARIO=" + COD_USUARIO);
		   }else if (OP_CODE.compareTo("DELETE") == 0){
			   String COD_AGENDAMENTO = request.getParameter("COD_AGENDAMENTO").trim();
			   pkList = AgendamentoDAO.createKey("COD_AGENDAMENTO", Integer.parseInt(COD_AGENDAMENTO));
			   daoAgendamento.setPkList(pkList);
			   daoAgendamento.delete();
		   }
		}catch(Exception e){
			out.println("Error on AgendamentoServiceServlet... " + ' ' + e.getMessage());
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
