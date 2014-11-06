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
	Vector<String> documentoClienteData = fetchClienteData(COD_PEDIDO);
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

	return new Vector<String>();
  }

  protected Vector<String> fetchDocumentoClienteData(String COD_PEDIDO) {

	return new Vector<String>();
  } 
  
  protected Vector<String> fetchEnderecoClienteData(String COD_PEDIDO) {

	return new Vector<String>();
  }

  protected Vector<String> fetchContatoClienteData(String COD_PEDIDO) {

	return new Vector<String>();
  }

  protected Vector<String> fetchPedidoData(String COD_PEDIDO) {

	Vector<String> docData = new Vector<String>();
	
	PkList pkList = PedidoDAO.createKey("COD_PEDIDO", Integer.parseInt(COD_PEDIDO));
	PedidoDAO pedido = new PedidoDAO(pkList);

	try {
		pedido.read();

		docData.add(pedido.getAttValueFor("COD_TIPO").toString());
		docData.add(pedido.getAttValueFor("NUM_PEDIDO").toString());

    		// TODO : Data do Pedido

		docData.add(pedido.getAttValueFor("DATA_VENCIMENTO").toString());

    		//Boleto por e-mail?

		Vector<ServicoPedidoDAO> servPedidoList = ServicoPedidoDAOService.loadAllForPedido(COD_PEDIDO);
		for (int i=0;i < servPedidoList.size();i++) {
		
			ServicoPedidoDAO servico = (ServicoPedidoDAO)servPedidoList.elementAt(i);

			String COD_SERVICO = servico.getAttValueFor("COD_SERVICO").toString();
			pkList = TipoServicoDAO.createKey("COD_SERVICO",Integer.parseInt(COD_SERVICO));
			TipoServicoDAO tipo = new TipoServicoDAO(pkList);

			docData.add(tipo.getAttValueFor("NOME_SERVICO").toString());

		}

    		//Equipamentos e acessÃ³rios
    		//Dados para instalaÃ§Ã£o

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
