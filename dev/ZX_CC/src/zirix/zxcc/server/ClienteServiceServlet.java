package zirix.zxcc.server;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zirix.zxcc.server.dao.ClienteDAO;
import zirix.zxcc.server.dao.ContatoClienteDAO;
import zirix.zxcc.server.dao.DocumentoClienteDAO;
import zirix.zxcc.server.dao.EmailCliVenDAO;
import zirix.zxcc.server.dao.EnderecoClienteDAO;
import zirix.zxcc.server.dao.PkList;

/**
 * Servlet implementation class ClienteService
 */
	@WebServlet( name="ClienteService", urlPatterns = {"/services/cliente"}, loadOnStartup=1)
	public class ClienteServiceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServiceServlet() {
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

		   try {
			   ClienteDAO daoCliente = new ClienteDAO();
			   PkList pkList;

			   if ((OP_CODE.compareTo("UPDATE") == 0) || (OP_CODE.compareTo("CREATE") == 0)) {

				   String TIPO = request.getParameter("TIPO");
				   daoCliente.setAttValueFor("TIPO", TIPO);

				   String NOME = request.getParameter("NOME");
				   daoCliente.setAttValueFor("NOME", NOME);

				   String NOME_FANTASIA = request.getParameter("NOME_FANTASIA");
				   daoCliente.setAttValueFor("NOME_FANTASIA", NOME_FANTASIA);

				   String SITE = request.getParameter("SITE");
				   daoCliente.setAttValueFor("SITE", SITE);

				   String DATA_NASCIMENTO = request.getParameter("DATA_NASCIMENTO");
				   daoCliente.setAttValueFor("DATA_NASCIMENTO", DATA_NASCIMENTO);

				   if (OP_CODE.compareTo("CREATE") == 0){
					   String DATA_INGRESSO = request.getParameter("DATA_INGRESSO");
					   daoCliente.setAttValueFor("DATA_INGRESSO", DATA_INGRESSO);
				   }

				   String COD_VENDEDOR = request.getParameter("COD_VENDEDOR");
				   daoCliente.setAttValueFor("COD_VENDEDOR", COD_VENDEDOR);

				   if (OP_CODE.compareTo("UPDATE") == 0){

					   String COD_CLIENTE = request.getParameter("COD_CLIENTE");
					   pkList = ClienteDAO.createKey("COD_CLIENTE", Integer.parseInt(COD_CLIENTE));

					   daoCliente.setPkList(pkList);
					   daoCliente.update();
				   }
				   else
				   {
					   pkList = daoCliente.create();

					   int pkListValue = pkList.values().iterator().next();

					   HttpSession session = request.getSession(true);

					   String[] myJsonDocumento = (String[]) session.getAttribute("jSonDocumento");
					   String[] myJsonEndereco = (String[]) session.getAttribute("jSonEndereco");
					   String[] myJsonContato = (String[]) session.getAttribute("jSonContato");
					   String[] myJsonEmail = (String[]) session.getAttribute("jSonEmail");

					   session.invalidate();

					   int arraysize = myJsonDocumento.length / 4;
					   int count = 0;
					   for(int d = 0 ; d < arraysize ; d++){
						   DocumentoClienteDAO daoDocumentoCliente = new DocumentoClienteDAO();
						   daoDocumentoCliente.setAttValueFor("COD_CLIENTE",pkListValue);
						   daoDocumentoCliente.setAttValueFor("COD_DOCUMENTO",myJsonDocumento[d+count]);
						   count++;
						   daoDocumentoCliente.setAttValueFor("NUMERO",myJsonDocumento[d+count]);
						   count++;
						   daoDocumentoCliente.setAttValueFor("DATA_EMISSAO",myJsonDocumento[d+count]);
						   count++;
						   daoDocumentoCliente.setAttValueFor("ORGAO_EMISSOR",myJsonDocumento[d+count]);
						   daoDocumentoCliente.create();
					   }

					   arraysize = myJsonEndereco.length / 8;
					   count = 0;
					   for(int d = 0 ; d < arraysize ; d++){
						   EnderecoClienteDAO daoEnderecoCliente = new EnderecoClienteDAO();
						   daoEnderecoCliente.setAttValueFor("COD_CLIENTE",pkListValue);
						   daoEnderecoCliente.setAttValueFor("ENDERECO",myJsonEndereco[d+count]);
						   count++;
						   daoEnderecoCliente.setAttValueFor("COMPLEMENTO",myJsonEndereco[d+count]);
						   count++;
						   daoEnderecoCliente.setAttValueFor("BAIRRO",myJsonEndereco[d+count]);
						   count++;
						   daoEnderecoCliente.setAttValueFor("CIDADE",myJsonEndereco[d+count]);
						   count++;
						   daoEnderecoCliente.setAttValueFor("UF",myJsonEndereco[d+count]);
						   count++;
						   daoEnderecoCliente.setAttValueFor("COD_PAIS",myJsonEndereco[d+count]);
						   count++;
						   daoEnderecoCliente.setAttValueFor("CEP",myJsonEndereco[d+count]);
						   count++;
						   daoEnderecoCliente.setAttValueFor("COD_ENDERECO",myJsonEndereco[d+count]);
						   daoEnderecoCliente.create();
					   }

					   arraysize = myJsonContato.length / 6;
					   count = 0;
					   for(int d = 0 ; d < arraysize ; d++){
						   ContatoClienteDAO daoContatoCliente = new ContatoClienteDAO();
						   daoContatoCliente.setAttValueFor("COD_CLIENTE",pkListValue);
						   daoContatoCliente.setAttValueFor("DDD",myJsonContato[d+count]);
						   count++;
						   daoContatoCliente.setAttValueFor("NUMERO",myJsonContato[d+count]);
						   count++;
						   daoContatoCliente.setAttValueFor("COD_CONTATO",myJsonContato[d+count]);
						   count++;
						   daoContatoCliente.setAttValueFor("COD_PAIS",myJsonContato[d+count]);
						   count++;
						   daoContatoCliente.setAttValueFor("NOME",myJsonContato[d+count]);
						   count++;
						   daoContatoCliente.setAttValueFor("COD_GRAU",myJsonContato[d+count]);
						   daoContatoCliente.create();
					   }

					   arraysize = myJsonEmail.length;
					   for(int d = 0 ; d < arraysize ; d++){
						   if(myJsonEmail[d].trim().compareTo("CONTROLE") != 0){
							   EmailCliVenDAO daoEmailCliVen = new EmailCliVenDAO();
							   daoEmailCliVen.setAttValueFor("COD_CLI_VEN",pkListValue);
							   daoEmailCliVen.setAttValueFor("TIPO_CLI_VEN",0);
							   daoEmailCliVen.setAttValueFor("EMAIL",myJsonEmail[d]);
							   daoEmailCliVen.create();
						   }
					   }
				   }
				   // TODO CRIAR PÁGINA DE REDIRECIONAMENTO OU ALERT DE INGRESSO REALIZADO
				   response.sendRedirect("http://localhost:8080/zxcc/zx_cc.jsp");
			   }

			   else if (OP_CODE.compareTo("DELETE") == 0){

				   String COD_CLIENTE = request.getParameter("COD_CLIENTE");
				   pkList = ClienteDAO.createKey("COD_CLIENTE", Integer.parseInt(COD_CLIENTE));

				   daoCliente.setPkList(pkList);
				   daoCliente.delete();
			   }
		   } catch (Exception e) {
				   out.println("Error on ClienteServiceServlet... " + ' ' + e.getMessage());
		   }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
