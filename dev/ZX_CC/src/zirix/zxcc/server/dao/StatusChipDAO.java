package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class StatusChipDAO extends GenericDAO<StatusChipDAO> {

    public StatusChipDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public StatusChipDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	
    	setAttValueFor("NOME_STATUS",res.getString("NOME_STATUS"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return StatusChipDAO.createKey("COD_STATUS", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.STATUS_CHIP";
   
        
}