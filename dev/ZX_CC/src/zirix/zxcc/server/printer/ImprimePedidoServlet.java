package zirix.zxcc.server.printer;

import javax.servlet.*; 
import javax.servlet.http.*; 
import java.util.*; 
import java.io.*;
import java.sql.*;

import antena.printer.*;

import zirix.zxcc.server.dao.*;
import zirix.zxcc.server.*;

public class ImprimePedidoServlet extends HttpServlet { 

  public void doGet(HttpServletRequest request, 

	HttpServletResponse response) throws ServletException { 
 
    	try {

		String COD_PEDIDO = request.getParameter("COD_PEDIDO").trim();

        	ByteArrayOutputStream output = new ByteArrayOutputStream();

		Vector<String> txtList = fetchDocumentData(COD_PEDIDO);

        	output = ADPDFCreator.createPDF(null,null,ADFont.HELVETICA,txtList);

		response.setContentType("application/pdf");
        	response.setHeader("Content-Disposition", "attachment; filename=yourFile.pdf");
        	response.getOutputStream().write(output.toByteArray());

    	} catch (Exception ex) {            
		ex.printStackTrace();
    	}   
  }

  protected Vector<String> fetchDocumentData(String COD_PEDIDO) { 


	Vector<String> clienteData = fetchClienteData(COD_PEDIDO);
	Vector<String> documentoClienteData = fetchDocumentoClienteData(COD_PEDIDO);
	Vector<String> enderecoClienteData = fetchEnderecoClienteData(COD_PEDIDO);
	Vector<String> pedidoData = fetchPedidoData(COD_PEDIDO);


	Vector<String> docData = new Vector<String>();

	docData.addAll(clienteData);
	docData.addAll(documentoClienteData);
	docData.addAll(enderecoClienteData);
	docData.addAll(clienteData);

	return docData;
  }

  protected Vector<String> fetchClienteData(String COD_PEDIDO) { 

	Vector<String> docData = new Vector<String>();
	
	PkList pkList = PedidoDAO.createKey("COD_PEDIDO", Integer.parseInt(COD_PEDIDO));
	PedidoDAO pedido = new PedidoDAO(pkList);

	try {

		pedido.read();

		String COD_CLIENTE = pedido.getAttValueFor("COD_CLIENTE").toString();
		pkList = ClienteDAO.createKey("COD_CLIENTE", Integer.parseInt(COD_CLIENTE));
		ClienteDAO cliente = new ClienteDAO(pkList);

		cliente.read();

		String COD_VENDEDOR = cliente.getAttValueFor("COD_VENDEDOR").toString();
		pkList = VendedorDAO.createKey("COD_VENDEDOR", Integer.parseInt(COD_VENDEDOR));
		VendedorDAO vendedor = new VendedorDAO(pkList);

		vendedor.read();

		String nome_vendedor = vendedor.getAttValueFor("NOME").toString();


		String nome_cliente = cliente.getAttValueFor("NOME").toString();
		String fantasia_cliente = cliente.getAttValueFor("NOME_FANTASIA").toString();
		String data_nascimento = cliente.getAttValueFor("DATA_NASCIMENTO").toString();
		String site = cliente.getAttValueFor("SITE").toString();

		docData.add(nome_vendedor);
		docData.add(nome_cliente);	
		docData.add(fantasia_cliente);
		docData.add(data_nascimento);
		docData.add(site);

	} catch (SQLException sql) {

		sql.printStackTrace();
	
	}
		
	return docData;
  }

  protected Vector<String> fetchDocumentoClienteData(String COD_PEDIDO) {

	Vector<String> docData = new Vector<String>();
	
	PkList pkList = PedidoDAO.createKey("COD_PEDIDO", Integer.parseInt(COD_PEDIDO));
	PedidoDAO pedido = new PedidoDAO(pkList);

	try {

		pedido.read();

		String COD_CLIENTE = pedido.getAttValueFor("COD_CLIENTE").toString();
		pkList = ClienteDAO.createKey("COD_CLIENTE", Integer.parseInt(COD_CLIENTE));
		ClienteDAO cliente = new ClienteDAO(pkList);

		cliente.read();

		Vector<DocumentoClienteDAO> docClienteList = DocumentoClienteDAOService.loadAllForCliente(COD_CLIENTE);
		for (int i=0;i < docClienteList.size();i++) {
		
			DocumentoClienteDAO documento = (DocumentoClienteDAO)docClienteList.elementAt(i);

			docData.add(documento.getAttValueFor("NUMERO").toString());
			docData.add(documento.getAttValueFor("DATA_EMISSAO").toString());
			docData.add(documento.getAttValueFor("ORGAO_EMISSOR").toString());

		}

	} catch (SQLException sql) {

		sql.printStackTrace();
	
	}
		
	return docData;
  } 
  
  protected Vector<String> fetchEnderecoClienteData(String COD_PEDIDO) {

	Vector<String> docData = new Vector<String>();
	
	PkList pkList = PedidoDAO.createKey("COD_PEDIDO", Integer.parseInt(COD_PEDIDO));
	PedidoDAO pedido = new PedidoDAO(pkList);

	try {

		pedido.read();

		String COD_CLIENTE = pedido.getAttValueFor("COD_CLIENTE").toString();
		pkList = ClienteDAO.createKey("COD_CLIENTE", Integer.parseInt(COD_CLIENTE));
		ClienteDAO cliente = new ClienteDAO(pkList);

		cliente.read();

		Vector<EnderecoClienteDAO> endClienteList = EnderecoClienteDAOService.loadAllForCliente(COD_CLIENTE);
		for (int i=0;i < endClienteList.size();i++) {
		
			EnderecoClienteDAO documento = (EnderecoClienteDAO)endClienteList.elementAt(i);

			docData.add(documento.getAttValueFor("ENDERECO").toString());
			docData.add(documento.getAttValueFor("BAIRRO").toString());
			docData.add(documento.getAttValueFor("CIDADE").toString());
			docData.add(documento.getAttValueFor("UF").toString());
			docData.add(documento.getAttValueFor("COD_PAIS").toString());
			docData.add(documento.getAttValueFor("COMPLEMENTO").toString());
			docData.add(documento.getAttValueFor("CEP").toString());
		}

	} catch (SQLException sql) {

		sql.printStackTrace();
	
	}
		
	return docData;
  }

  protected Vector<String> fetchContatoClienteData(String COD_PEDIDO) {

	Vector<String> docData = new Vector<String>();
	
	PkList pkList = PedidoDAO.createKey("COD_PEDIDO", Integer.parseInt(COD_PEDIDO));
	PedidoDAO pedido = new PedidoDAO(pkList);

	try {

		pedido.read();

		String COD_CLIENTE = pedido.getAttValueFor("COD_CLIENTE").toString();
		pkList = ClienteDAO.createKey("COD_CLIENTE", Integer.parseInt(COD_CLIENTE));
		ClienteDAO cliente = new ClienteDAO(pkList);

		cliente.read();

		Vector<ContatoClienteDAO> conClienteList = ContatoClienteDAOService.loadAllForCliente(COD_CLIENTE);
		for (int i=0;i < conClienteList.size();i++) {
		
			ContatoClienteDAO contato = (ContatoClienteDAO)conClienteList.elementAt(i);

			docData.add(contato.getAttValueFor("COD_CONTATO").toString());
			docData.add(contato.getAttValueFor("DDD").toString());
			docData.add(contato.getAttValueFor("NUMERO").toString());
			docData.add(contato.getAttValueFor("COD_PAIS").toString());
			docData.add(contato.getAttValueFor("NOME").toString());
			docData.add(contato.getAttValueFor("COD_GRAU").toString());
		}

		Vector<EmailCliVenDAO> emailClienteList = EmailCliVenDAOService.loadAllForCliente(COD_CLIENTE);
		for (int i=0;i < emailClienteList.size();i++) {
		
			EmailCliVenDAO contato = (EmailCliVenDAO)emailClienteList.elementAt(i);

			docData.add(contato.getAttValueFor("EMAIL").toString());
		}

	} catch (SQLException sql) {

		sql.printStackTrace();
	
	}
		
	return docData;
  }

  protected Vector<String> fetchPedidoData(String COD_PEDIDO) {

	Vector<String> docData = new Vector<String>();
	
	PkList pkList = PedidoDAO.createKey("COD_PEDIDO", Integer.parseInt(COD_PEDIDO));
	PedidoDAO pedido = new PedidoDAO(pkList);

	try {


		pedido.read();

		pkList = NumeroPedidoDAO.createKey("COD_PEDIDO", Integer.parseInt(COD_PEDIDO));
		NumeroPedidoDAO numero = new NumeroPedidoDAO(pkList);

		numero.read();

		docData.add(numero.getAttValueFor("DATA_GERACAO").toString());
		docData.add(pedido.getAttValueFor("BOLETO_EMAIL").toString());
		docData.add(pedido.getAttValueFor("COD_TIPO").toString());
		docData.add(pedido.getAttValueFor("NUM_PEDIDO").toString());

		docData.add(pedido.getAttValueFor("DATA_VENCIMENTO").toString());

		Vector<ServicoPedidoDAO> servPedidoList = ServicoPedidoDAOService.loadAllForPedido(COD_PEDIDO);
		for (int i=0;i < servPedidoList.size();i++) {
		
			ServicoPedidoDAO servico = (ServicoPedidoDAO)servPedidoList.elementAt(i);

			String COD_SERVICO = servico.getAttValueFor("COD_SERVICO").toString();
			pkList = TipoServicoDAO.createKey("COD_SERVICO",Integer.parseInt(COD_SERVICO));
			TipoServicoDAO tipo = new TipoServicoDAO(pkList);

			tipo.read();

			docData.add(tipo.getAttValueFor("NOME_SERVICO").toString());

		}

		//Equipamentos e acessórios -> tabela EQUIP_ACESSORIO_PEDIDO (a descrição do equipamento está na tabela TIPO_EQUIP_ACESSORIO)
		Vector<EquipAcessorioPedidoDAO> equipAcessorios = EquipAcessorioPedidoDAOService.loadAllForPedido(COD_PEDIDO);
		for (int i=0;i < equipAcessorios.size();i++) {
		
			EquipAcessorioPedidoDAO equip = (EquipAcessorioPedidoDAO)equipAcessorios.elementAt(i);
			docData.add(equip.getAttValueFor("COD_EQUIP_ACESSORIO_PEDIDO").toString());

		}

    		//Dados para instalacao
		Vector<DadosInstalacaoDAO> dadosInstalacao = DadosInstalacaoDAOService.loadAllForPedido(COD_PEDIDO);
		for (int i=0;i < dadosInstalacao.size();i++) {
		
			DadosInstalacaoDAO dados = (DadosInstalacaoDAO)dadosInstalacao.elementAt(i);
			docData.add(dados.getAttValueFor("COD_EQUIP_ACESSORIO_PEDIDO").toString());

		}

		Vector<TipoUnidadeDAO> unidadeList = TipoUnidadeDAOService.loadAllForPedido(COD_PEDIDO);
		for (int i=0;i < unidadeList.size();i++) {
		
			TipoUnidadeDAO unidade = (TipoUnidadeDAO)unidadeList.elementAt(i);
			docData.add(unidade.getAttValueFor("NOME").toString());
		}
		
    		//ObservaÃ§Ã£o do pedido

	} catch (SQLException sql) {

		sql.printStackTrace();
	}

	return docData;
  }
} 
