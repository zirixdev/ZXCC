package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class StatusChipDAO extends GenericDAO<StatusChipDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

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
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return StatusChipDAO.createKey("COD_STATUS", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "STATUS_CHIP";
   
        
}