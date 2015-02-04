/*ZIRIX CONTROL CENTER - CHIP SERVICE SERVLET
DESENVOLVIDO POR ZIRIX SOLU��ES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zirix.zxcc.server.dao.*;

@WebServlet( name="ChipService", urlPatterns = {"/services/chip"}, loadOnStartup=1)
public class ChipServiceServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private String ICCID_ = new String();
	private String NFE_ = new String();
	private String COD_OPERADORA_ = new String();
	private String APN_ = new String();
	private String TECNOLOGIA_ = new String();
	private String COD_STATUS_ = new String();
	private String DDD_ = new String();
	private String NUMERO_CHIP_ = new String();
	private String COD_PACOTE_ = new String();
	private String COD_CONTA_ = new String();
	private String PREFIXO_ICCID_ = new String();
	private Integer SUFIXO_ICCID_ = null;

    public ChipServiceServlet(){
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	   response.setContentType("text/html");
	   PrintWriter out = response.getWriter();
	   String OP_CODE = request.getParameter("OP_CODE");
	   String MASSIVO = request.getParameter("MASSIVO");
	   if(MASSIVO.compareTo("MASSIVO") == 0){
		   try{
			   ChipDAO daoChip = new ChipDAO();
			   String ICCID_INICIAL = request.getParameter("ICCID_INICIAL");
			   String ICCID_FINAL = request.getParameter("ICCID_FINAL");
			   Integer sufix_ini = Integer.parseInt(ICCID_INICIAL.substring(16,20));
			   Integer sufix_fin = Integer.parseInt(ICCID_FINAL.substring(16,20));
			   Integer quant_chips = ((sufix_fin - sufix_ini)/10)+1;
			   PREFIXO_ICCID_ = ICCID_INICIAL.substring(0,16);
			   SUFIXO_ICCID_ = Integer.parseInt(ICCID_INICIAL.substring(16,20));
			   NFE_ = request.getParameter("NFE");
			   COD_OPERADORA_ = request.getParameter("OPERADORA");
			   APN_ = request.getParameter("APN");
			   TECNOLOGIA_ = request.getParameter("TECNOLOGIA");
			   COD_STATUS_ = request.getParameter("ESTADO");
			   COD_PACOTE_ = request.getParameter("COD_PACOTE");
			   COD_CONTA_ = request.getParameter("COD_CONTA");
			   for(int i=0; i<quant_chips; i++){
				   daoChip.setAttValueFor("COD_MODULO", 0);
				   daoChip.setAttValueFor("NFE", NFE_);
				   ICCID_ = PREFIXO_ICCID_.concat(SUFIXO_ICCID_.toString());
				   daoChip.setAttValueFor("ICCID", ICCID_);
				   SUFIXO_ICCID_ = SUFIXO_ICCID_ + 10;
				   daoChip.setAttValueFor("COD_OPERADORA", COD_OPERADORA_);
				   daoChip.setAttValueFor("APN", APN_);
				   daoChip.setAttValueFor("TECNOLOGIA", TECNOLOGIA_);
				   daoChip.setAttValueFor("COD_STATUS", COD_STATUS_);
				   daoChip.setAttValueFor("DDD", "21");
				   daoChip.setAttValueFor("NUMERO_CHIP", "999999999");
				   daoChip.setAttValueFor("COD_PACOTE", COD_PACOTE_);
				   daoChip.setAttValueFor("COD_CONTA", COD_CONTA_);
				   daoChip.setAttValueFor("DELETED", 0);
				   daoChip.Create();
			   }
		   }catch(Exception e){
			   out.println("Error on ChipServiceServlet... " + ' ' + e.getMessage());
		   }
	   }else{
		   try{
			   ChipDAO daoChip = new ChipDAO();
			   PkList pkList;
			   if((OP_CODE.compareTo("UPDATE") == 0) || (OP_CODE.compareTo("CREATE") == 0)){
				   daoChip.setAttValueFor("COD_MODULO", 0);
				   NFE_ = request.getParameter("NFE");
				   daoChip.setAttValueFor("NFE", NFE_);
				   ICCID_ = request.getParameter("ICCID");
				   daoChip.setAttValueFor("ICCID", ICCID_);
				   COD_OPERADORA_ = request.getParameter("OPERADORA");
				   daoChip.setAttValueFor("COD_OPERADORA", COD_OPERADORA_);
				   APN_ = request.getParameter("APN");
				   daoChip.setAttValueFor("APN", APN_);
				   TECNOLOGIA_ = request.getParameter("TECNOLOGIA");
				   daoChip.setAttValueFor("TECNOLOGIA", TECNOLOGIA_);
				   COD_STATUS_ = request.getParameter("ESTADO");
				   daoChip.setAttValueFor("COD_STATUS", COD_STATUS_);
				   DDD_ = request.getParameter("DDD");
				   daoChip.setAttValueFor("DDD", DDD_);
				   NUMERO_CHIP_ = request.getParameter("NUMERO");
				   daoChip.setAttValueFor("NUMERO_CHIP", NUMERO_CHIP_);
				   COD_PACOTE_ = request.getParameter("COD_PACOTE");
				   daoChip.setAttValueFor("COD_PACOTE", COD_PACOTE_);
				   COD_CONTA_ = request.getParameter("COD_CONTA");
				   daoChip.setAttValueFor("COD_CONTA", COD_CONTA_);
				   daoChip.setAttValueFor("DELETED", 0);
				   if(OP_CODE.compareTo("UPDATE") == 0){
					   String COD_CHIP = request.getParameter("COD_CHIP");
					   pkList = ChipDAO.createKey("COD_CHIP", Integer.parseInt(COD_CHIP));
					   daoChip.setPkList(pkList);
					   daoChip.update();
				   }else{
					   daoChip.Create();
				   }
			   }else if(OP_CODE.compareTo("DELETE") == 0){
				   String COD_CHIP = request.getParameter("COD_CHIP");
				   pkList = ChipDAO.createKey("COD_CHIP", Integer.parseInt(COD_CHIP));
				   daoChip.setPkList(pkList);
				   daoChip.delete();
			   }
		   }catch(Exception e){
			   out.println("Error on ChipServiceServlet... " + ' ' + e.getMessage());
		   }
	   }
	   response.sendRedirect(ZXMain.URL_ADRESS_ + "zx_cc.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
	}
}
