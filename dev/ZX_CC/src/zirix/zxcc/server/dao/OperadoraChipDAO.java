package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class OperadoraChipDAO extends GenericDAO<OperadoraChipDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public OperadoraChipDAO(PkList pkList) {
        super(TABLENAME,pkList);
        
    }

    public OperadoraChipDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();		
		key.put(name, new Integer(value));

		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME_OPERADORA", res.getString("NOME_OPERADORA"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return OperadoraChipDAO.createKey("COD_OPERADORA", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "OPERADORA_CHIP";
}