package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class VendedorDAO extends GenericDAO<VendedorDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public VendedorDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public VendedorDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {

    	setAttValueFor("NOME",res.getString("NOME"));
    	setAttValueFor("TIPO",res.getInt("TIPO"));
    	setAttValueFor("NOME_FANTASIA",res.getString("NOME_FANTASIA"));
    	setAttValueFor("SITE",res.getString("SITE"));
    	setAttValueFor("DATA_NASCIMENTO",res.getDate("DATA_NASCIMENTO"));
    	setAttValueFor("DATA_INGRESSO",res.getDate("DATA_INGRESSO"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    	
    }

    public Set<String> getPkNamesSet() {
    	return VendedorDAO.createKey("COD_VENDEDOR", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "VENDEDOR";   
}