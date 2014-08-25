/*ZIRIX CONTROL CENTER
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import zirix.zxcc.server.dao.ChipDAO;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.dao.PkList;
import zirix.zxcc.server.ZXCCConstantsServlet;

public class ChipServiceBean {

	private ChipDAO dao_ = null;
	private Integer COD_CHIP_ = null;
	private Integer COD_MODULO_ = null;

	public ChipServiceBean(String[] pkVal) {
		setPk(pkVal);
	}

	public ChipServiceBean() {

	}

	public void setPk(Object[] pkVal) {

		COD_CHIP_ = new Integer((String)pkVal[0]);

		PkList pkList = new PkList();
	    pkList.put("COD_CHIP",COD_CHIP_);
	    dao_ = new ChipDAO(pkList);

	    try {
	    	dao_.read();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {}
	}

	public Integer getCodModulo(){
		Integer codModulo = (Integer)dao_.getAttValueFor("COD_MODULO");
		COD_MODULO_ = codModulo;
		return codModulo;
	}

	public String getNfe() {
    	String nfe = (String)dao_.getAttValueFor("NFE");	    	
    	return nfe;
	}

	public String getIccid() {
    	String iccid = (String)dao_.getAttValueFor("ICCID");	    	
    	return iccid;
	}

	public Integer getCodOperadora(){
		Integer codOperadora = (Integer)dao_.getAttValueFor("COD_OPERADORA");
		return codOperadora;
	}

	public String getApn() {
    	String apn = (String)dao_.getAttValueFor("APN");	    	
    	return apn;
	}

	public String getTecnologia() {
    	String tecnologia = (String)dao_.getAttValueFor("TECNOLOGIA");	    	
    	return tecnologia;
	}

	public Integer getCodStatus(){
		Integer codStatus = (Integer)dao_.getAttValueFor("COD_STATUS");
		return codStatus;
	}

	public String getDdd() {
    	String ddd = (String)dao_.getAttValueFor("DDD");	    	
    	return ddd;
	}

	public String getNumeroChip() {
    	String numeroChip = (String)dao_.getAttValueFor("NUMERO_CHIP");	    	
    	return numeroChip;
	}

	public String getDtVigencia() {

		String dtVigencia = dao_.getAttValueFor("DATA_VIGENCIA").toString();
    	return dtVigencia;
	}

	public Integer getCodConta(){
		Integer codConta = (Integer)dao_.getAttValueFor("COD_CONTA");
		return codConta;
	}

	public Integer getCodPacote(){
		Integer codPacote = (Integer)dao_.getAttValueFor("COD_PACOTE");
		return codPacote;
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getNumeroModulo(){
		Vector<String[]> NumeroModulo = new Vector<String[]>();
		
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT NUMERO_MODULO "
					+ " 											 FROM " + ZXCCConstantsServlet.DB_NAME_ + "MODULO "
					+ "                                             WHERE COD_MODULO = " + COD_MODULO_);

			for (int i=0;i < values.size();i++) {
				String[] attList = new String[1];
				attList[0] = values.get(i)[0].toString();
				NumeroModulo.add(attList);
			}
			
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return NumeroModulo;
		}
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getModeloModulo(){
		Vector<String[]> ModeloModulo = new Vector<String[]>();

		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT RTRIM(LTRIM(" + ZXCCConstantsServlet.DB_NAME_ + "MARCA_MODULO.nome_marca)) + ' - ' + RTRIM(LTRIM(" + ZXCCConstantsServlet.DB_NAME_ + "DESC_MODULO.nome_modelo)) "
					+ " 											 FROM " + ZXCCConstantsServlet.DB_NAME_ + "MARCA_MODULO "
					+ " 											    , " + ZXCCConstantsServlet.DB_NAME_ + "DESC_MODULO "
					+ " 											    , " + ZXCCConstantsServlet.DB_NAME_ + "MODULO "
					+ "                                             WHERE " + ZXCCConstantsServlet.DB_NAME_ + "MARCA_MODULO.COD_MARCA = " + ZXCCConstantsServlet.DB_NAME_ + "DESC_MODULO.COD_MARCA "
					+ "                                               AND " + ZXCCConstantsServlet.DB_NAME_ + "DESC_MODULO.COD_MODELO = " + ZXCCConstantsServlet.DB_NAME_ + "MODULO.COD_MODELO "
					+ "                                               AND " + ZXCCConstantsServlet.DB_NAME_ + "MODULO.COD_MODULO = " + COD_MODULO_);

			for (int i=0;i < values.size();i++) {
				String[] attList = new String[1];
				attList[0] = (String) values.get(i)[0];
				ModeloModulo.add(attList);
			}

		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return ModeloModulo;
		}
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getNomeCliente(){
		Vector<String[]> NomeCliente = new Vector<String[]>();

		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT " + ZXCCConstantsServlet.DB_NAME_ + "CLIENTE.NOME "
					+ " 											 FROM " + ZXCCConstantsServlet.DB_NAME_ + "CLIENTE "
					+ " 											    , " + ZXCCConstantsServlet.DB_NAME_ + "MODULO "
					+ "                                             WHERE " + ZXCCConstantsServlet.DB_NAME_ + "CLIENTE.COD_CLIENTE = " + ZXCCConstantsServlet.DB_NAME_ + "MODULO.COD_CLIENTE "
					+ "                                               AND " + ZXCCConstantsServlet.DB_NAME_ + "MODULO.COD_MODULO = " + COD_MODULO_);

			for (int i=0;i < values.size();i++) {
				String[] attList = new String[1];
				attList[0] = (String) values.get(i)[0];
				NomeCliente.add(attList);
			}

		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return NomeCliente;
		}
	}
}