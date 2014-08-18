package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class InfoContatoDAO extends GenericDAO<InfoContatoDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public InfoContatoDAO(PkList pkList) {
        super(TABLENAME,pkList);
        
    }

    public InfoContatoDAO(){
    	super(TABLENAME);
    }
        
    public static PkList createKey(String name,int value) {
		
		PkList key = new PkList();		
		key.put(name, new Integer(value));
		
		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME_GRAU", res.getString("NOME_GRAU"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return InfoContatoDAO.createKey("COD_GRAU", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "INFO_CONTATO";
          
}