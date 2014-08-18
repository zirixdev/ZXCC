package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class ContatoVendedorDAO extends GenericDAO<ContatoVendedorDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public ContatoVendedorDAO(PkList pkList) {
        super(TABLENAME,pkList);
        setCanDelete(true);
    }

    public ContatoVendedorDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();		
		key.put(name, new Integer(value));

		return key;				
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {

    	setAttValueFor("COD_VENDEDOR",res.getInt("COD_VENDEDOR"));
    	setAttValueFor("COD_CONTATO",res.getInt("COD_CONTATO"));
    	setAttValueFor("DDD",res.getString("DDD"));
    	setAttValueFor("NUMERO",res.getString("NUMERO"));
    	setAttValueFor("COD_PAIS",res.getInt("COD_PAIS"));
    	setAttValueFor("NOME",res.getString("NOME"));
    }

    public Set<String> getPkNamesSet() {    	
    	return ContatoVendedorDAO.createKey("COD_CONTATO_VEN", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

    public final static String TABLENAME = AMBIENTE_.db_name + "CONTATO_VENDEDOR";

}