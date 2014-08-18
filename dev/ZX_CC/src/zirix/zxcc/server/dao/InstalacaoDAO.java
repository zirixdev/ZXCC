package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class InstalacaoDAO extends GenericDAO<InstalacaoDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public InstalacaoDAO(PkList pkList) {
        super(TABLENAME,pkList);
        
    }

    public InstalacaoDAO(){
    	super(TABLENAME);
    }
        
    public static PkList createKey(String name,int value) {
		
		PkList key = new PkList();		
		key.put(name, new Integer(value));
		
		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_MODULO", res.getInt("COD_MODULO"));
    	setAttValueFor("COD_UNIDADE", res.getInt("COD_UNIDADE"));
    	setAttValueFor("DELETED", res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return ClienteDAO.createKey("COD_INSTALACAO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "INSTALACAO";
}