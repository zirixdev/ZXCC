/*ZIRIX CONTROL CENTER - SEPARAÇÃO EQUIPAMENTO SERVICE SERVLET
DESENVOLVIDO POR RAPHAEL B. MARQUES

CLIENTE: ZIRIX SOLU��ES EM RASTREAMENTO
TECNOLOGIAS UTILIZADAS: JAVA*/
package zirix.zxcc.server;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zirix.zxcc.server.dao.ModuloUnidadeAgendadaDAO;

@WebServlet( name="SeparacaoEquipamentoServiceServlet", urlPatterns = {"/services/separacaoequipamentoservlet"}, loadOnStartup=1)
public class SeparacaoEquipamentoServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SeparacaoEquipamentoServiceServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   try{
		   String QTD_UNIDADE = request.getParameter("QTD_UNIDADE").trim();
		   for(int i=0;i<Integer.parseInt(QTD_UNIDADE);i++){
			   ModuloUnidadeAgendadaDAO daoModuloUnidadeAgendada = new ModuloUnidadeAgendadaDAO();
			   String COD_MODULO = request.getParameter("COD_MODULO_"+i).trim();
			   String COD_VEICULO = request.getParameter("COD_VEICULO_"+i).trim();
			   String TIPO_UNIDADE = request.getParameter("TIPO_UNIDADE_"+i).trim();
			   daoModuloUnidadeAgendada.setAttValueFor("COD_MODULO", COD_MODULO);
			   daoModuloUnidadeAgendada.setAttValueFor("COD_UNIDADE", COD_VEICULO);
			   daoModuloUnidadeAgendada.setAttValueFor("TIPO_UNIDADE", TIPO_UNIDADE);
			   daoModuloUnidadeAgendada.Create();
		   }
		   int WORK_ID = Integer.parseInt(request.getParameter("WORK_ID"));
		   int PK_COLUMN = Integer.parseInt(request.getParameter("PK_COLUMN"));
		   Evaluator eval = new Evaluator(WORK_ID);
		   if(eval.endWork()){
			   Schedule sched = new Schedule(WORK_ID);
			   sched.changeState(PK_COLUMN);
		   }
	   }catch (Exception e){
		   out.println("Error on SeparacaoEquipamentoServiceServlet... " + e.getMessage());
	   }
	   response.sendRedirect(ZXMain.URL_ADRESS_ + "zx_cc.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
