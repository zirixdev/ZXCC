/*ZIRIX CONTROL CENTER - AGENDAMENTO SERVICE SERVLET
DESENVOLVIDO POR RAPHAEL B. MARQUES

CLIENTE: ZIRIX SOLU��ES EM RASTREAMENTO
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
import zirix.zxcc.server.dao.*;
import zirix.zxcc.server.mock.MockEvaluator;
import zirix.zxcc.server.mock.MockSchedule;
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
		String WORK_ID = request.getParameter("WORK_ID");
		int PK_COLUMN = 0;
		try {
			AgendamentoDAO daoAgendamento = new AgendamentoDAO();
			PkList pkList;
			if ((OP_CODE.compareTo("UPDATE") == 0) || (OP_CODE.compareTo("CREATE") == 0)) {
			   String COD_PEDIDO = request.getParameter("CODPEDIDO").trim();
			   daoAgendamento.setAttValueFor("COD_PEDIDO", COD_PEDIDO);
			   String DATA_AGENDAMENTO = request.getParameter("DATAAGEND").trim();
			   daoAgendamento.setAttValueFor("DATA_AGENDAMENTO", DATA_AGENDAMENTO);
			   String HORA_AGENDAMENTO = request.getParameter("HORAAGEND").trim();
			   daoAgendamento.setAttValueFor("HORA_AGENDAMENTO", HORA_AGENDAMENTO);
			   daoAgendamento.setAttValueFor("ESTADO", 0);
			   daoAgendamento.setAttValueFor("DELETED", 0);
			   String END_AGEND = request.getParameter("END_AGEND").trim();
			   if(END_AGEND.compareTo("sim") == 0){
				   String COD_DADOS_INSTALACAO = request.getParameter("CODDADOINST").trim();
				   daoAgendamento.setAttValueFor("COD_DADOS_INSTALACAO", COD_DADOS_INSTALACAO);
			   }
			   if(OP_CODE.compareTo("UPDATE") == 0){
				   String COD_AGENDAMENTO = request.getParameter("COD_AGENDAMENTO").trim();
				   pkList = AgendamentoDAO.createKey("COD_AGENDAMENTO", Integer.parseInt(COD_AGENDAMENTO));
				   daoAgendamento.setPkList(pkList);
				   daoAgendamento.update();
			   }else{
				   daoAgendamento.Create();
				   int pkListValue = 0;
				   Vector<String[]> CodAgendamento_ = new Vector<String[]>();
				   try {
					   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT MAX(COD_AGENDAMENTO) "
							   + " 											                 FROM " + ZXMain.DB_NAME_ + "AGENDAMENTO "
							   + "                                                          WHERE COD_PEDIDO = " + COD_PEDIDO);
					   for (int i=0;i < values.size();i++) {
						   String[] attList = new String[1];
						   attList[0] = values.get(i)[0].toString();;
						   CodAgendamento_.add(attList);
					   }
				   }catch (SQLException ex) {
					   ex.printStackTrace();
				   }  finally {
					   pkListValue = Integer.parseInt(CodAgendamento_.elementAt(0)[0].trim());
					   PK_COLUMN = pkListValue;
				   }
				   if(pkListValue != 0){
					   int arraysize = Integer.parseInt(request.getParameter("QOBS").trim());
					   for(int d = 0 ; d < arraysize ; d++){
						   ObsAgendamentoDAO daoObsAgendamento = new ObsAgendamentoDAO();
						   daoObsAgendamento.setAttValueFor("COD_AGENDAMENTO",pkListValue);
						   daoObsAgendamento.setAttValueFor("INDICE",d);
						   String OBSERVACAO = request.getParameter("OBSERVACAO_"+d);
						   daoObsAgendamento.setAttValueFor("OBSERVACAO",OBSERVACAO);
						   daoObsAgendamento.setAttValueFor("CHAVE",0);
						   daoObsAgendamento.setAttValueFor("DELETED",0);
						   daoObsAgendamento.Create();
					   }
					   arraysize = Integer.parseInt(request.getParameter("TOTALUNIDAGEND").trim());
					   for(int d = 0 ; d < arraysize ; d++){
						   UnidadesAgendadasDAO daoUnidadesAgendadas = new UnidadesAgendadasDAO();
						   daoUnidadesAgendadas.setAttValueFor("COD_AGENDAMENTO",pkListValue);
						   String COD_UNIDADE = request.getParameter("CODVEIC_"+d);
						   daoUnidadesAgendadas.setAttValueFor("COD_UNIDADE",COD_UNIDADE);
						   daoUnidadesAgendadas.setAttValueFor("TIPO_UNIDADE",2);
						   daoUnidadesAgendadas.setAttValueFor("ESTADO",0);
						   daoUnidadesAgendadas.setAttValueFor("DELETED",0);
						   daoUnidadesAgendadas.Create();
					   }
					   if(END_AGEND.compareTo("nao") == 0){
						   DadosAgendamentoDAO daoDadosAgendamento = new DadosAgendamentoDAO();
						   String ENDERECO = request.getParameter("ENDINST");
						   daoDadosAgendamento.setAttValueFor("ENDERECO",ENDERECO);
						   String BAIRRO = request.getParameter("BAIRROINST");
						   daoDadosAgendamento.setAttValueFor("BAIRRO",BAIRRO);
						   String CIDADE = request.getParameter("CIDADEINST");
						   daoDadosAgendamento.setAttValueFor("CIDADE",CIDADE);
						   String UF = request.getParameter("UFINST");
						   daoDadosAgendamento.setAttValueFor("UF",UF);
						   String COD_PAIS = request.getParameter("PAISINST");
						   daoDadosAgendamento.setAttValueFor("COD_PAIS",COD_PAIS);
						   String COMPLEMENTO = request.getParameter("COMPINST");
						   daoDadosAgendamento.setAttValueFor("COMPLEMENTO",COMPLEMENTO);
						   String CEP = request.getParameter("CEPINST");
						   daoDadosAgendamento.setAttValueFor("CEP",CEP);
						   String REFERENCIA = request.getParameter("REFERINST");
						   daoDadosAgendamento.setAttValueFor("REFERENCIA",REFERENCIA);
						   String DDD = request.getParameter("DDDINST");
						   daoDadosAgendamento.setAttValueFor("DDD",DDD);
						   String NUMERO = request.getParameter("NUMEROINST");
						   daoDadosAgendamento.setAttValueFor("NUMERO",NUMERO);
						   String NOMEINST = request.getParameter("NOMEINST");
						   daoDadosAgendamento.setAttValueFor("NOME",NOMEINST);
						   daoDadosAgendamento.setAttValueFor("COD_AGENDAMENTO",pkListValue);
						   daoDadosAgendamento.setAttValueFor("DELETED",0);
						   daoDadosAgendamento.Create();
					   }
				   }else{
					   out.println("Error on AgendamentoServiceServlet... " + "\nIngresso do Agendamento! ");
				   }
			   }
			   System.err.println("\n WORK_ID = " + WORK_ID);
			   if(WORK_ID.compareTo("0") != 0){
				   MockEvaluator eval = new MockEvaluator(Integer.parseInt(WORK_ID));
				   if(eval.endWork()){
					   int TOTALUNID = Integer.parseInt(request.getParameter("TOTALUNID"));
					   int TOTALUNIDAGEND = Integer.parseInt(request.getParameter("TOTALUNIDAGEND"));
					   if(TOTALUNID > TOTALUNIDAGEND){
						   MockSchedule.createWork(Integer.parseInt(WORK_ID));
					   }else{
						   MockSchedule sched = new MockSchedule(Integer.parseInt(WORK_ID));
						   sched.changeState(PK_COLUMN);
					   }
				   }
			   }
			}else if (OP_CODE.compareTo("DELETE") == 0){
				String COD_AGENDAMENTO = request.getParameter("COD_AGENDAMENTO").trim();
				pkList = AgendamentoDAO.createKey("COD_AGENDAMENTO", Integer.parseInt(COD_AGENDAMENTO));
				daoAgendamento.setPkList(pkList);
				daoAgendamento.delete();
			}
		}catch(Exception e){
			out.println("Error on AgendamentoServiceServlet... " + ' ' + e.getMessage());
		}
		response.sendRedirect(ZXMain.URL_ADRESS_ + "zx_cc.jsp?COD_USUARIO=" + COD_USUARIO);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}

