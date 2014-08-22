/*ZIRIX CONTROL CENTER - MAIN PAGE
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES & MÁRIO DE SÁ VERA
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import zirix.zxcc.server.dao.ClienteDAO;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.dao.PkList;
import zirix.zxcc.server.ZXCCConstants;

public class ClienteServiceBean {

	private ClienteDAO dao_ = null;
	private Integer COD_CLIENTE_ = null;

	public ClienteServiceBean(String[] pkVal) {
		setPk(pkVal);

	    System.err.println("CRIEI O BEAN PKVAL");
	}

	public ClienteServiceBean() {

	    System.err.println("CRIEI O BEAN");
	}

	public void setPk(Object[] pkVal) {

		COD_CLIENTE_ = new Integer((String)pkVal[0]);
		PkList pkList = new PkList();
	    pkList.put("COD_CLIENTE",COD_CLIENTE_);
	    dao_ = new ClienteDAO(pkList);
	    try {
	    	dao_.read();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {
	    }
	}

	public Integer getCodVendedor(){
	    System.err.println("\ngetCodVendedor");

		Integer codVendedor = (Integer)dao_.getAttValueFor("COD_VENDEDOR");
		return codVendedor;
	}

	public Integer getTipo(){
	    System.err.println("\ngetTipo");

		Integer tipo = (Integer)dao_.getAttValueFor("TIPO");
		return tipo;
	}

	public String getNome() {
	    System.err.println("\ngetNome");

    	String nome = (String)dao_.getAttValueFor("NOME");
    	return nome;
	}

	public String getNomeFantasia() {
	    System.err.println("\ngetNomeFantasia");

    	String nomeFantasia = (String)dao_.getAttValueFor("NOME_FANTASIA");
    	return nomeFantasia;
	}

	public String getDtNascimento() {
	    System.err.println("\ngetDtNascimento");

		String dtNascimento = dao_.getAttValueFor("DATA_NASCIMENTO").toString();
    	return dtNascimento;
	}

	public String getSite() {
	    System.err.println("\ngetSite");

    	String site = (String)dao_.getAttValueFor("SITE");
    	return site;
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getEnd() {
	    System.err.println("\ngetEnd");

	    Vector<String[]> endClienteList = new Vector<String[]>();
	    try {
		    ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT ENDERECO, BAIRRO, CIDADE, UF, COD_PAIS, COMPLEMENTO, CEP, COD_ENDERECO "
		    		+ "							   FROM " + ZXCCConstants.db_name + "ENDERECO_CLIENTE "
		    		+ "							  WHERE COD_CLIENTE = " + COD_CLIENTE_);

		    for (int i=0;i < values.size();i++) {
			    String[] attList = new String[8]; // pois eu sei que sao 8 atributos de fato !
			    attList[0] = (String) values.get(i)[0];
			    attList[1] = (String) values.get(i)[1];
			    attList[2] = (String) values.get(i)[2];
			    attList[3] = values.get(i)[3].toString();
			    attList[4] = values.get(i)[4].toString();
			    attList[5] = (String) values.get(i)[5];
			    attList[6] = (String) values.get(i)[6];
			    attList[7] = values.get(i)[7].toString();
			    endClienteList.add(attList);
		    }
	    } catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return endClienteList;
	    }
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getContato(){
	    System.err.println("\ngetContato");

		Vector<String[]> contatoClienteList = new Vector<String[]>();
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT " + ZXCCConstants.db_name + "TIPO_CONTATO.NOME_TIPO "
					+ "                                                              , " + ZXCCConstants.db_name + "CONTATO_CLIENTE.DDD "
					+ "                                                              , " + ZXCCConstants.db_name + "CONTATO_CLIENTE.NUMERO "
					+ "                                                              , " + ZXCCConstants.db_name + "CONTATO_CLIENTE.COD_PAIS "
					+ "                                                              , " + ZXCCConstants.db_name + "CONTATO_CLIENTE.NOME "
					+ "                                                              , " + ZXCCConstants.db_name + "INFO_CONTATO.NOME_GRAU "
		    		+ "							   FROM " + ZXCCConstants.db_name + "CONTATO_CLIENTE "
					+ "                               , " + ZXCCConstants.db_name + "INFO_CONTATO "
					+ "                               , " + ZXCCConstants.db_name + "TIPO_CONTATO "
		    		+ "							  WHERE " + ZXCCConstants.db_name + "INFO_CONTATO.COD_GRAU = " + ZXCCConstants.db_name + "CONTATO_CLIENTE.COD_GRAU "
		    		+ "                             AND " + ZXCCConstants.db_name + "TIPO_CONTATO.COD_CONTATO = " + ZXCCConstants.db_name + "CONTATO_CLIENTE.COD_CONTATO "
		    		+ "                             AND " + ZXCCConstants.db_name + "CONTATO_CLIENTE.COD_CLIENTE = " + COD_CLIENTE_);

		    for (int i=0;i < values.size();i++) {
			    String[] attList = new String[6]; // pois eu sei que sao 6 atributos de fato !
			    attList[0] = (String) values.get(i)[0];
			    attList[1] = (String) values.get(i)[1];
			    attList[2] = (String) values.get(i)[2];
			    attList[3] = values.get(i)[3].toString();
			    attList[4] = (String) values.get(i)[4];
			    attList[5] = (String) values.get(i)[5];
			    contatoClienteList.add(attList);
		    }
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return contatoClienteList;
		}
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getDocumento(){
	    System.err.println("\ngetDocumento");

		Vector<String[]> documentoClienteList = new Vector<String[]>();
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT " + ZXCCConstants.db_name + "DOCUMENTO_CLIENTE.NUMERO "
					+ ",							    " + ZXCCConstants.db_name + "TIPO_DOCUMENTO.NOME "
					+ ",                                " + ZXCCConstants.db_name + "DOCUMENTO_CLIENTE.DATA_EMISSAO "
					+ ",                                " + ZXCCConstants.db_name + "DOCUMENTO_CLIENTE.ORGAO_EMISSOR "
		    		+ "							   FROM " + ZXCCConstants.db_name + "DOCUMENTO_CLIENTE "
		    		+ "							      , " + ZXCCConstants.db_name + "TIPO_DOCUMENTO "
		    		+ "							  WHERE " + ZXCCConstants.db_name + "TIPO_DOCUMENTO.COD_DOCUMENTO = " + ZXCCConstants.db_name + "DOCUMENTO_CLIENTE.COD_DOCUMENTO "
		    		+ "                             AND " + ZXCCConstants.db_name + "DOCUMENTO_CLIENTE.COD_CLIENTE = " + COD_CLIENTE_);

		    for (int i=0;i < values.size();i++) {
			    String[] attList = new String[4]; // pois eu sei que sao 4 atributos de fato !
			    attList[0] = (String) values.get(i)[0];
			    attList[1] = (String) values.get(i)[1];
		    	attList[2] = ((java.sql.Date) values.get(i)[2]).toLocalDate().toString();
			    attList[3] = (String) values.get(i)[3];
			    documentoClienteList.add(attList);
		    }
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return documentoClienteList;
		}
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getEmail(){
	    System.err.println("\ngetEmail");

		Vector<String[]> emailClienteList = new Vector<String[]>();
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT EMAIL "
		    		+ "							   FROM " + ZXCCConstants.db_name + "EMAIL_CLI_VEN "
		    		+ "							  WHERE TIPO_CLI_VEN = 0 "
		    		+ "                             AND COD_CLI_VEN = " + COD_CLIENTE_);

		    for (int i=0;i < values.size();i++) {
			    String[] attList = new String[1]; // pois eu sei que e 1 atributo de fato !
			    attList[0] = (String) values.get(i)[0];
			    emailClienteList.add(attList);
		    }
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return emailClienteList;
		}
	}
}