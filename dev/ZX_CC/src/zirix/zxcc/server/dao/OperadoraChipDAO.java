package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class OperadoraChipDAO extends GenericDAO<OperadoraChipDAO> {

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
    }
    
    public Set<String> getPkNamesSet() {    	
    	return OperadoraChipDAO.createKey("COD_OPERADORA", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.OPERADORA_CHIP";
}