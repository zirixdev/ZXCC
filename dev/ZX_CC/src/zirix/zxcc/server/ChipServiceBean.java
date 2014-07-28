/*ZIRIX CONTROL CENTER - MAIN PAGE
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
		Integer CodModulo = (Integer)dao_.getAttValueFor("COD_MODULO");
		COD_MODULO_ = CodModulo;
		return CodModulo;
	}

	public String getNfe() {
    	String nfe = (String)dao_.getAttValueFor("NFE");	    	
    	return nfe;
	}

	public String getIccid() {
    	String Iccid = (String)dao_.getAttValueFor("ICCID");	    	
    	return Iccid;
	}

	public Integer getCodOperadora(){
		Integer CodOperadora = (Integer)dao_.getAttValueFor("COD_OPERADORA");
		return CodOperadora;
	}

	public String getApn() {
    	String apn = (String)dao_.getAttValueFor("APN");	    	
    	return apn;
	}

	public String getTecnologia() {
    	String Tecnologia = (String)dao_.getAttValueFor("TECNOLOGIA");	    	
    	return Tecnologia;
	}

	public Integer getCodStatus(){
		Integer CodStatus = (Integer)dao_.getAttValueFor("COD_STATUS");
		return CodStatus;
	}

	public String getDdd() {
    	String ddd = (String)dao_.getAttValueFor("DDD");	    	
    	return ddd;
	}

	public String getNumeroChip() {
    	String NumeroChip = (String)dao_.getAttValueFor("NUMERO_CHIP");	    	
    	return NumeroChip;
	}

	@SuppressWarnings("finally")
	public Vector<String[]> getNumeroModulo(){
		Vector<String[]> NumeroModulo = new Vector<String[]>();
		
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT NUMERO_MODULO "
					+ " 											 FROM ZX_CC_DEV.dbo.MODULO "
					+ "                                             WHERE COD_MODULO = " + COD_MODULO_);

			for (int i=0;i < values.size();i++) {
				String[] attList = new String[1];
				attList[0] = values.get(i)[0].toString();;
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
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT RTRIM(LTRIM(ZX_CC_DEV.dbo.MARCA_MODULO.nome_marca)) + ' - ' + RTRIM(LTRIM(ZX_CC_DEV.dbo.DESC_MODULO.nome_modelo)) "
					+ " 											 FROM ZX_CC_DEV.dbo.MARCA_MODULO "
					+ " 											    , ZX_CC_DEV.dbo.DESC_MODULO "
					+ " 											    , ZX_CC_DEV.dbo.MODULO "
					+ "                                             WHERE ZX_CC_DEV.dbo.MARCA_MODULO.COD_MARCA = ZX_CC_DEV.dbo.DESC_MODULO.COD_MARCA "
					+ "                                               AND ZX_CC_DEV.dbo.DESC_MODULO.COD_MODELO = ZX_CC_DEV.dbo.MODULO.COD_MODELO "
					+ "                                               AND ZX_CC_DEV.dbo.MODULO.COD_MODULO = " + COD_MODULO_);

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
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT ZX_CC_DEV.dbo.CLIENTE.NOME "
					+ " 											 FROM ZX_CC_DEV.dbo.CLIENTE "
					+ " 											    , ZX_CC_DEV.dbo.MODULO "
					+ "                                             WHERE ZX_CC_DEV.dbo.CLIENTE.COD_CLIENTE = ZX_CC_DEV.dbo.MODULO.COD_CLIENTE "
					+ "                                               AND ZX_CC_DEV.dbo.MODULO.COD_MODULO = " + COD_MODULO_);

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