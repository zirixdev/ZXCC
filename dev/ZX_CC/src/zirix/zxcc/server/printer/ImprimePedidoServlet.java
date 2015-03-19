package zirix.zxcc.server.printer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import zirix.zxcc.server.ZXMain;
import zirix.zxcc.server.dao.ClienteDAO;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.dao.PedidoDAO;
import zirix.zxcc.server.dao.PkList;


@WebServlet( name="ImprimePedidoServlet", urlPatterns = {"/services/imprimepedido"}, loadOnStartup=1)
public class ImprimePedidoServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private RelatorioPedidoData docData = new RelatorioPedidoData();
	private String NumPedido = new String();

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		try{
			String COD_PEDIDO = request.getParameter("COD_PEDIDO").trim();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			ServletContext cntx= getServletContext();
			List lista = new ArrayList();

			fetchDocumentData(COD_PEDIDO);

			lista.add(docData);

			String jaspername = cntx.getRealPath("/reports/ZXPedReport.jasper");

			File jasperfile = new File(jaspername);

			JasperReport jasper = (JasperReport)JRLoader.loadObject(jasperfile);
			
			JasperPrint jasperprint = JasperFillManager.fillReport(jasper, null, new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfStream(jasperprint, output);

			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "attachment; filename=Pedido_" + NumPedido + ".pdf");
			response.getOutputStream().write(output.toByteArray());
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	protected void fetchDocumentData(String COD_PEDIDO){ 
		PkList pkList = PedidoDAO.createKey("COD_PEDIDO", Integer.parseInt(COD_PEDIDO));
		PedidoDAO pedido = new PedidoDAO(pkList);
		try{
			pedido.read();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		String COD_CLIENTE = pedido.getAttValueFor("COD_CLIENTE").toString();
		
		fetchClienteData(COD_CLIENTE);
		fetchDocumentoClienteData(COD_CLIENTE);
		fetchEnderecoClienteData(COD_CLIENTE);
		fetchContatoClienteData(COD_CLIENTE);
		fetchEmailClienteData(COD_CLIENTE);
		fetchPedidoData(COD_PEDIDO);
		fetchEquipAcessorios(COD_PEDIDO);
		fetchServico(COD_PEDIDO);
		fetchDadosInstalacao(COD_PEDIDO);
		fetchUnidadePedido(COD_PEDIDO);
		fetchObservacaoPedido(COD_PEDIDO);

	}

	protected void fetchClienteData(String COD_CLIENTE){

		PkList pkList = ClienteDAO.createKey("COD_CLIENTE", Integer.parseInt(COD_CLIENTE));
		ClienteDAO cliente = new ClienteDAO(pkList);
		try{
			cliente.read();

			String nome_cliente = cliente.getAttValueFor("NOME").toString().trim();
			String fantasia_cliente = cliente.getAttValueFor("NOME_FANTASIA").toString().trim();
			String data_nascimento = cliente.getAttValueFor("DATA_NASCIMENTO").toString().trim();
			String site = cliente.getAttValueFor("SITE").toString().trim();
			String tipo = cliente.getAttValueFor("TIPO").toString().trim();

			docData.setNomeCliente(" " + nome_cliente);
			docData.setNascimentoCliente(" " + data_nascimento);
			docData.setSiteCliente(" " + site);
			if (Integer.parseInt(tipo)==1){
				docData.setFantasiaCliente(" " + fantasia_cliente);
				docData.setTipoCliente("PESSOA JURÍDICA");
			}else{
				docData.setTipoCliente("PESSOA FÍSICA");
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}

	protected void fetchDocumentoClienteData(String COD_CLIENTE){

		String documento = new String();
		Vector<String[]> documentoCliente_ = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT TIPO_DOCUMENTO.NOME "
					+                                                          "     , DOCUMENTO_CLIENTE.NUMERO "
					+                                                          "     , DOCUMENTO_CLIENTE.DATA_EMISSAO "
					+                                                          "     , DOCUMENTO_CLIENTE.ORGAO_EMISSOR "
					+                                                          "  FROM " + ZXMain.DB_NAME_ + "DOCUMENTO_CLIENTE "
					+                                                          "     , " + ZXMain.DB_NAME_ + "TIPO_DOCUMENTO "
					+                                                          " WHERE DOCUMENTO_CLIENTE.COD_CLIENTE = " + COD_CLIENTE
					+                                                          "   AND TIPO_DOCUMENTO.COD_DOCUMENTO = DOCUMENTO_CLIENTE.COD_DOCUMENTO");
			for (int i=0;i < values.size();i++){
				String[] attList = new String[4];
				attList[0] = values.get(i)[0].toString();
				attList[1] = values.get(i)[1].toString();
				attList[2] = values.get(i)[2].toString();
				attList[3] = values.get(i)[3].toString();
				documentoCliente_.add(attList);
			}
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		for(int i=0;i<documentoCliente_.size();i++){
			String tipoDoc = documentoCliente_.elementAt(i)[0].trim();
			String numDoc = documentoCliente_.elementAt(i)[1].trim();
			String dtDoc = documentoCliente_.elementAt(i)[2].trim();
			if(dtDoc.trim().compareTo("5000-12-31")==0){
				dtDoc = "";
			}
			String ogDoc = documentoCliente_.elementAt(i)[3].trim();
			if(ogDoc.trim().compareTo("VAZIO")==0){
				ogDoc = "";
			}
			documento = documento +  " " + tipoDoc + ": " + numDoc + " " + dtDoc + " " + ogDoc;
			if(i+1<documentoCliente_.size()){
				documento = documento + "\n";
			}
		}
		docData.setDocCliente(documento);
	}

	protected void fetchEnderecoClienteData(String COD_CLIENTE){

		String endereco = new String();
		Vector<String[]> enderecoCliente_ = new Vector<String[]>();
		try{
		    ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT TIPO_ENDERECO.NOME "
		    		+														   "     , ENDERECO_CLIENTE.ENDERECO "
		    		+														   "     , ENDERECO_CLIENTE.COMPLEMENTO "
		    		+														   "     , ENDERECO_CLIENTE.BAIRRO "
		    		+														   "     , ENDERECO_CLIENTE.CIDADE "
		    		+														   "     , UF.SIGLA "
		    		+														   "     , PAIS.NOME_PAIS "
		    		+														   "     , ENDERECO_CLIENTE.CEP "
		    		+														   "  FROM " + ZXMain.DB_NAME_ + "ENDERECO_CLIENTE "
		    		+														   "     , " + ZXMain.DB_NAME_ + "PAIS "
		    		+														   "     , " + ZXMain.DB_NAME_ + "TIPO_ENDERECO "
		    		+														   "     , " + ZXMain.DB_NAME_ + "UF "
		    		+														   " WHERE ENDERECO_CLIENTE.COD_CLIENTE = " + COD_CLIENTE
		    		+														   "   AND PAIS.COD_PAIS = ENDERECO_CLIENTE.COD_PAIS "
		    		+														   "   AND UF.COD_UF = ENDERECO_CLIENTE.UF "
		    		+														   "   AND TIPO_ENDERECO.COD_ENDERECO = ENDERECO_CLIENTE.COD_ENDERECO ");

		    for (int i=0;i<values.size();i++){
			    String[] attList = new String[8];
				attList[0] = values.get(i)[0].toString();
				attList[1] = values.get(i)[1].toString();
				attList[2] = values.get(i)[2].toString();
				attList[3] = values.get(i)[3].toString();
				attList[4] = values.get(i)[4].toString();
				attList[5] = values.get(i)[5].toString();
				attList[6] = values.get(i)[6].toString();
				attList[7] = values.get(i)[7].toString();
			    enderecoCliente_.add(attList);
		    }
	    }catch(SQLException ex){
    		ex.printStackTrace();
		}
		for(int i=0;i<enderecoCliente_.size();i++){
			String tipoEnd = enderecoCliente_.elementAt(i)[0].trim();
			String endEnd = enderecoCliente_.elementAt(i)[1].trim();
			String comEnd = enderecoCliente_.elementAt(i)[2].trim();
			String baiEnd = enderecoCliente_.elementAt(i)[3].trim();
			String cidEnd = enderecoCliente_.elementAt(i)[4].trim();
			String ufEnd = enderecoCliente_.elementAt(i)[5].trim();
			String paisEnd = enderecoCliente_.elementAt(i)[6].trim();
			String cepEnd = enderecoCliente_.elementAt(i)[7].trim();

			endereco = endereco +  " " + tipoEnd + ": " + endEnd + " " + comEnd + " - " + baiEnd;
			endereco = endereco + " " + cidEnd + " - " + ufEnd + " - " + paisEnd + " - CEP.: " + cepEnd.substring(0, 5) + "-" + cepEnd.substring(6, 8);

			if(i+1<enderecoCliente_.size()){
				endereco = endereco + "\n";
			}
		}
		docData.setEndCliente(endereco);
	}

	protected void fetchContatoClienteData(String COD_CLIENTE){

		String contato = new String();
		Vector<String[]> contatoCliente_ = new Vector<String[]>();
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT TIPO_CONTATO.NOME_TIPO "
					+														   "     , CONTATO_CLIENTE.DDD "
					+														   "     , CONTATO_CLIENTE.NUMERO "
					+														   "     , CONTATO_CLIENTE.COD_PAIS "
					+														   "     , CONTATO_CLIENTE.NOME "
					+														   "     , INFO_CONTATO.NOME_GRAU "
		    		+														   "  FROM " + ZXMain.DB_NAME_ + "CONTATO_CLIENTE "
					+														   "     , " + ZXMain.DB_NAME_ + "INFO_CONTATO "
					+														   "     , " + ZXMain.DB_NAME_ + "TIPO_CONTATO "
		    		+														   " WHERE " + ZXMain.DB_NAME_ + "INFO_CONTATO.COD_GRAU = " + ZXMain.DB_NAME_ + "CONTATO_CLIENTE.COD_GRAU "
		    		+														   "   AND " + ZXMain.DB_NAME_ + "TIPO_CONTATO.COD_CONTATO = " + ZXMain.DB_NAME_ + "CONTATO_CLIENTE.COD_CONTATO "
		    		+														   "   AND " + ZXMain.DB_NAME_ + "CONTATO_CLIENTE.COD_CLIENTE = " + COD_CLIENTE);
		    for (int i=0;i < values.size();i++) {
			    String[] attList = new String[6];
				attList[0] = values.get(i)[0].toString();
				attList[1] = values.get(i)[1].toString();
				attList[2] = values.get(i)[2].toString();
				attList[3] = values.get(i)[3].toString();
				attList[4] = values.get(i)[4].toString();
				attList[5] = values.get(i)[5].toString();
			    contatoCliente_.add(attList);
		    }
		}catch(SQLException ex){
    		ex.printStackTrace();
		}
		for(int i=0;i<contatoCliente_.size();i++){
			String tipoCto = contatoCliente_.elementAt(i)[0].trim();
			String dddCto = contatoCliente_.elementAt(i)[1].trim();
			String numCto = contatoCliente_.elementAt(i)[2].trim();
			String paisCto = contatoCliente_.elementAt(i)[3].trim();
			String nomeCto = contatoCliente_.elementAt(i)[4].trim();
			String grauCto = contatoCliente_.elementAt(i)[5].trim();

			contato = contato + " " +  tipoCto + ": +" + paisCto + " (" + dddCto + ")" + numCto + " - " + nomeCto + " - " + grauCto;

			if(i+1<contatoCliente_.size()){
				contato = contato + "\n";
			}
		}
		docData.setContatoCliente(contato);
	}

	protected void fetchEmailClienteData(String COD_CLIENTE){
		
		String email = new String();
		Vector<String[]> emailCliente_ = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT EMAIL "
		    		+                                                          "  FROM " + ZXMain.DB_NAME_ + "EMAIL_CLI_VEN "
		    		+                                                          " WHERE TIPO_CLI_VEN = 0 "
		    		+                                                          "   AND COD_CLI_VEN = " + COD_CLIENTE);
		    for(int i=0;i < values.size();i++){
			    String[] attList = new String[1];
			    attList[0] = values.get(i)[0].toString();
			    emailCliente_.add(attList);
		    }
		}catch(SQLException ex){
    		ex.printStackTrace();
		}
		for(int i=0;i<emailCliente_.size();i++){
			String mail = emailCliente_.elementAt(i)[0].trim();

			email = email + " " + mail;

			if(i+1<emailCliente_.size()){
				email = email + "\n";
			}
		}
		docData.setEmailCliente(email);
	}

	protected void fetchPedidoData(String COD_PEDIDO){

		Vector<String[]> pedidoCliente_ = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT VENDEDOR.NOME "
					+														   "     , PEDIDO.NUM_PEDIDO "
					+														   "     , TIPO_PEDIDO.NOME_TIPO "
					+														   "     , PEDIDO.DATA_VENCIMENTO "
					+														   "     , IFNULL(PEDIDO.INFO_PEDIDO,'VAZIO') "
					+														   "     , PEDIDO.BOLETO_EMAIL "
		    		+                                                          "  FROM " + ZXMain.DB_NAME_ + "PEDIDO "
					+ 														   "     , " + ZXMain.DB_NAME_ + "TIPO_PEDIDO "
					+ 														   "     , " + ZXMain.DB_NAME_ + "VENDEDOR "
		    		+                                                          " WHERE VENDEDOR.COD_VENDEDOR = PEDIDO.COD_VENDEDOR "
		    		+                                                          "   AND TIPO_PEDIDO.COD_TIPO = PEDIDO.COD_TIPO"
		    		+                                                          "   AND PEDIDO.COD_PEDIDO = " + COD_PEDIDO);
		    for(int i=0;i < values.size();i++){
			    String[] attList = new String[6];
			    attList[0] = values.get(i)[0].toString().trim();
			    attList[1] = values.get(i)[1].toString().trim();
			    attList[2] = values.get(i)[2].toString().trim();
			    attList[3] = values.get(i)[3].toString().trim();
			    attList[4] = values.get(i)[4].toString().trim();
			    attList[5] = values.get(i)[5].toString().trim();
			    pedidoCliente_.add(attList);
		    }
		}catch(SQLException ex){
    		ex.printStackTrace();
		}
		if(pedidoCliente_.size()>0){
			NumPedido = pedidoCliente_.elementAt(0)[1];
			docData.setNumPed(NumPedido);
			docData.setNomeVendedor(pedidoCliente_.elementAt(0)[0]);
			if(pedidoCliente_.elementAt(0)[4].compareTo("VAZIO")==0){
				docData.setTipoPedido(pedidoCliente_.elementAt(0)[2]);
			}else{
				docData.setTipoPedido(pedidoCliente_.elementAt(0)[2] + " " + pedidoCliente_.elementAt(0)[4]);
			}
			if(pedidoCliente_.elementAt(0)[5].compareTo("0")==0){
				docData.setFlagYesNo("não ");
			}else{
				docData.setFlagYesNo("");
			}
			docData.setDtVencimento(pedidoCliente_.elementAt(0)[3]);
		}
	}

	protected void fetchEquipAcessorios(String COD_PEDIDO){

		String equipAcessorio = new String();
		String quantidade = new String();
		String valorUnitario = new String();
		String valorEquipAcessorioTotal = new String();
		String valorTotal = new String();
		Vector<String[]> equipAcessoriosPedido_ = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT TIPO_EQUIP_ACESSORIO.NOME_EQUIP_ACESSORIO "
					+ 														   "     , EQUIP_ACESSORIO_PEDIDO.QUANTIDADE "
					+														   "     , EQUIP_ACESSORIO_PEDIDO.VALOR_UNITARIO "
					+														   "     , EQUIP_ACESSORIO_PEDIDO.QUANTIDADE * EQUIP_ACESSORIO_PEDIDO.VALOR_UNITARIO AS VALOR_TOTAL "
					+ 														   "     , (SELECT SUM(EQUIP_ACESSORIO_PEDIDO.QUANTIDADE * EQUIP_ACESSORIO_PEDIDO.VALOR_UNITARIO) "
					+ 														   "          FROM EQUIP_ACESSORIO_PEDIDO "
					+ 														   "         WHERE EQUIP_ACESSORIO_PEDIDO.COD_PEDIDO = " + COD_PEDIDO + ") "
		    		+                                                          "  FROM " + ZXMain.DB_NAME_ + "EQUIP_ACESSORIO_PEDIDO "
					+														   "     , " + ZXMain.DB_NAME_ + "TIPO_EQUIP_ACESSORIO "
		    		+                                                          " WHERE TIPO_EQUIP_ACESSORIO.COD_EQUIP_ACESSORIO = EQUIP_ACESSORIO_PEDIDO.COD_EQUIP_ACESSORIO "
		    		+                                                          "   AND EQUIP_ACESSORIO_PEDIDO.COD_PEDIDO = " + COD_PEDIDO);
		    for(int i=0;i < values.size();i++){
			    String[] attList = new String[5];
			    attList[0] = values.get(i)[0].toString();
			    attList[1] = values.get(i)[1].toString();
			    attList[2] = values.get(i)[2].toString();
			    attList[3] = values.get(i)[3].toString();
			    attList[4] = values.get(i)[4].toString();
			    equipAcessoriosPedido_.add(attList);
		    }
		}catch(SQLException ex){
    		ex.printStackTrace();
		}
		for(int i=0;i<equipAcessoriosPedido_.size();i++){
			equipAcessorio = equipAcessorio + " " +  equipAcessoriosPedido_.elementAt(i)[0].trim();
			quantidade = quantidade + " " +  equipAcessoriosPedido_.elementAt(i)[1].trim();
			valorUnitario = valorUnitario + " " +  equipAcessoriosPedido_.elementAt(i)[2].trim();
			valorEquipAcessorioTotal = valorEquipAcessorioTotal + " " +  equipAcessoriosPedido_.elementAt(i)[3].trim();
			valorTotal = " " +  equipAcessoriosPedido_.elementAt(i)[4].trim();

			if(i+1<equipAcessoriosPedido_.size()){
				equipAcessorio = equipAcessorio + "\n";
				quantidade = quantidade + "\n";
				valorUnitario = valorUnitario + "\n";
				valorEquipAcessorioTotal = valorEquipAcessorioTotal + "\n";
			}
		}
		docData.setNomeEquiAce(equipAcessorio);
		docData.setQtdEquiAce(quantidade);
		docData.setVlrUnEquiAce(valorUnitario);
		docData.setVlrEquiAce(valorEquipAcessorioTotal);
		docData.setVlrTotalEquiAce(valorTotal);	
	}

	protected void fetchServico(String COD_PEDIDO){

		String solucao = new String();
		String quantidade = new String();
		String valorUnitario = new String();
		String valorSolucaoTotal = new String();
		String valorTotal = new String();
		Vector<String[]> servicoPedido_ = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT TIPO_SERVICO.NOME_SERVICO "
					+ 														   "     , SERVICO_PEDIDO.QUANTIDADE "
					+														   "     , SERVICO_PEDIDO.VALOR_UNITARIO "
					+														   "     , SERVICO_PEDIDO.QUANTIDADE * SERVICO_PEDIDO.VALOR_UNITARIO AS VALOR_TOTAL "
					+ 														   "     , (SELECT SUM(SERVICO_PEDIDO.QUANTIDADE * SERVICO_PEDIDO.VALOR_UNITARIO) "
					+ 														   "          FROM SERVICO_PEDIDO "
					+ 														   "         WHERE SERVICO_PEDIDO.COD_PEDIDO = " + COD_PEDIDO + ") "
		    		+                                                          "  FROM " + ZXMain.DB_NAME_ + "SERVICO_PEDIDO "
					+														   "     , " + ZXMain.DB_NAME_ + "TIPO_SERVICO "
		    		+                                                          " WHERE TIPO_SERVICO.COD_SERVICO = SERVICO_PEDIDO.COD_SERVICO "
		    		+                                                          "   AND SERVICO_PEDIDO.COD_PEDIDO = " + COD_PEDIDO);
		    for(int i=0;i < values.size();i++){
			    String[] attList = new String[5];
			    attList[0] = values.get(i)[0].toString();
			    attList[1] = values.get(i)[1].toString();
			    attList[2] = values.get(i)[2].toString();
			    attList[3] = values.get(i)[3].toString();
			    attList[4] = values.get(i)[4].toString();
			    servicoPedido_.add(attList);
		    }
		}catch(SQLException ex){
    		ex.printStackTrace();
		}
		for(int i=0;i<servicoPedido_.size();i++){
			solucao = solucao + " " +  servicoPedido_.elementAt(i)[0].trim();
			quantidade = quantidade + " " +  servicoPedido_.elementAt(i)[1].trim();
			valorUnitario = valorUnitario + " " +  servicoPedido_.elementAt(i)[2].trim();
			valorSolucaoTotal = valorSolucaoTotal + " " +  servicoPedido_.elementAt(i)[3].trim();
			valorTotal = " " +  servicoPedido_.elementAt(i)[4].trim();

			if(i+1<servicoPedido_.size()){
				solucao = solucao + "\n";
				quantidade = quantidade + "\n";
				valorUnitario = valorUnitario + "\n";
				valorSolucaoTotal = valorSolucaoTotal + "\n";
			}
		}
		docData.setSolucao(solucao);
		docData.setQtdSolucao(quantidade);
		docData.setVlrAssinat(valorUnitario);
		docData.setVlrSolucao(valorSolucaoTotal);
		docData.setVlrTotalSolucao(valorTotal);	

	}

	protected void fetchDadosInstalacao(String COD_PEDIDO){

		Vector<String[]> dadosInstalacao_ = new Vector<String[]>();
		try{
		    ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT DADOS_INSTALACAO.ENDERECO "
		    		+														   "     , DADOS_INSTALACAO.COMPLEMENTO "
		    		+														   "     , DADOS_INSTALACAO.BAIRRO "
		    		+														   "     , DADOS_INSTALACAO.CIDADE "
		    		+														   "     , UF.SIGLA "
		    		+														   "     , PAIS.NOME_PAIS "
		    		+														   "     , DADOS_INSTALACAO.CEP "
		    		+														   "     , DADOS_INSTALACAO.REFERENCIA "
		    		+														   "     , DADOS_INSTALACAO.DDD "
		    		+														   "     , DADOS_INSTALACAO.NUMERO "
		    		+														   "     , DADOS_INSTALACAO.NOME "
		    		+														   "  FROM " + ZXMain.DB_NAME_ + "DADOS_INSTALACAO "
		    		+														   "     , " + ZXMain.DB_NAME_ + "PAIS "
		    		+														   "     , " + ZXMain.DB_NAME_ + "UF "
		    		+														   " WHERE DADOS_INSTALACAO.COD_PEDIDO = " + COD_PEDIDO
		    		+														   "   AND PAIS.COD_PAIS = DADOS_INSTALACAO.COD_PAIS "
		    		+														   "   AND UF.COD_UF = DADOS_INSTALACAO.UF ");

		    for (int i=0;i<values.size();i++){
			    String[] attList = new String[11];
				attList[0] = values.get(i)[0].toString();
				attList[1] = values.get(i)[1].toString();
				attList[2] = values.get(i)[2].toString();
				attList[3] = values.get(i)[3].toString();
				attList[4] = values.get(i)[4].toString();
				attList[5] = values.get(i)[5].toString();
				attList[6] = values.get(i)[6].toString();
				attList[7] = values.get(i)[7].toString();
				attList[8] = values.get(i)[8].toString();
				attList[9] = values.get(i)[9].toString();
				attList[10] = values.get(i)[10].toString();
			    dadosInstalacao_.add(attList);
		    }
	    }catch(SQLException ex){
    		ex.printStackTrace();
		}
		for(int i=0;i<dadosInstalacao_.size();i++){
			docData.setEndInstalacao(dadosInstalacao_.elementAt(i)[0].trim());
			docData.setCompInstalacao(dadosInstalacao_.elementAt(i)[1].trim());
			docData.setBairroInstalacao(dadosInstalacao_.elementAt(i)[2].trim());
			docData.setCidadeInstalacao(dadosInstalacao_.elementAt(i)[3].trim());
			docData.setEstadoInstalacao(dadosInstalacao_.elementAt(i)[4].trim());
			docData.setPaisInstalacao(dadosInstalacao_.elementAt(i)[5].trim());
			String cepDadosInstalacao = dadosInstalacao_.elementAt(i)[6].trim();
			docData.setCepInstalacao(cepDadosInstalacao.substring(0, 5) + "-" + cepDadosInstalacao.substring(6, 8));
			docData.setReferenciaInstalacao(dadosInstalacao_.elementAt(i)[7].trim());
			docData.setTelefoneInstalacao("("+dadosInstalacao_.elementAt(i)[8].trim() +")"+ dadosInstalacao_.elementAt(i)[9].trim());
			docData.setContatoInstalacao(dadosInstalacao_.elementAt(i)[10].trim());
		}
	}


	protected void fetchUnidadePedido(String COD_PEDIDO){

		Vector<String[]> codUnidades = new Vector<String[]>();
		String cod_veiculo = new String();
		String unidadesPedido = new String();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT COD_VEICULO "
					+ 														   "  FROM " + ZXMain.DB_NAME_ + "VEICULO "
					+ 														   " WHERE COD_PEDIDO = " + COD_PEDIDO);
			for (int i=0;i<values.size();i++) {
				String[] attList = new String[1];
				attList[0] = values.get(i)[0].toString();
				codUnidades.add(attList);
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}
		for (int i=0;i<codUnidades.size();i++) {
			try{
				cod_veiculo = codUnidades.elementAt(i)[0].toString();
				ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT VEICULO.PLACA "
						+														   "     , VEICULO.CHASSI "
						+														   "     , VEICULO.RENAVAN "
						+														   "     , VEICULO.ANO_FAB "
						+														   "     , VEICULO.ANO_MOD "
						+														   "     , VEICULO.MODELO "
						+														   "     , VEICULO_MARCA.NOME_MARCA "
						+														   "     , VEICULO.COR "
						+														   "     , VEICULO_COMBUSTIVEL.NOME_COMBUSTIVEL "
						+														   "     , VEICULO.DATA_ULT_VISTORIA "
						+														   "     , IFNULL(VEICULO.COD_INSTALACAO,'Veículo não instalado') "	
						+ 														   "  FROM " + ZXMain.DB_NAME_ + "VEICULO "
						+ 														   "     , " + ZXMain.DB_NAME_ + "VEICULO_MARCA "
						+ 														   "     , " + ZXMain.DB_NAME_ + "VEICULO_COMBUSTIVEL "
						+ 														   " WHERE VEICULO.COD_VEICULO = " + cod_veiculo
						+                                                          "   AND VEICULO_MARCA.COD_MARCA = VEICULO.COD_MARCA "
						+                                                          "   AND VEICULO_COMBUSTIVEL.COD_COMBUSTIVEL = VEICULO.COD_COMBUSTIVEL ");
				for (int y=0;y<values.size();y++) {
					String[] attList = new String[10];
					attList[0] = values.get(i)[0].toString().trim();
					attList[1] = values.get(i)[1].toString().trim();
					attList[2] = values.get(i)[2].toString().trim();
					attList[3] = values.get(i)[3].toString().trim();
					attList[4] = values.get(i)[4].toString().trim();
					attList[5] = values.get(i)[5].toString().trim();
					attList[6] = values.get(i)[6].toString().trim();
					attList[7] = values.get(i)[7].toString().trim();
					attList[8] = values.get(i)[8].toString().trim();
					attList[9] = values.get(i)[9].toString().trim();
					unidadesPedido = unidadesPedido + "Placa: " + attList[0] + " Chassi: " + attList[1] + " Renavan: " + attList[2];
					unidadesPedido = unidadesPedido + "\n" + " Ano Fabricação/Modelo: " + attList[3] + "/" + attList[4];
					unidadesPedido = unidadesPedido + " Modelo: " + attList[5] + " Marca: " + attList[6] + " Cor: " + attList[7];
					unidadesPedido = unidadesPedido + "\n" + "Combustível: " + attList[8] + " Data da Última Vistoria: " + attList[9];
					unidadesPedido = unidadesPedido + "\n";
				}
			}catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
		
		docData.setUnidadesRastreadas(unidadesPedido);
	}

	protected void fetchObservacaoPedido(String COD_PEDIDO){

		String obs = new String();
		Vector<String[]> obsPedido_ = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT OBSERVACAO "
					+ 														   "  FROM " + ZXMain.DB_NAME_ + "OBS_PEDIDO "
					+ 														   " WHERE COD_PEDIDO = " + COD_PEDIDO
					+														   " ORDER BY INDICE ASC ");
			for (int i=0;i<values.size();i++) {
				String[] attList = new String[1];
				attList[0] = (String) values.get(i)[0];
				obsPedido_.add(attList);;
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}
		for(int i=0;i<obsPedido_.size();i++){
			obs = obs + obsPedido_.elementAt(i)[0].trim();
		}
		docData.setObservacaoPedido(obs);
	}
}
