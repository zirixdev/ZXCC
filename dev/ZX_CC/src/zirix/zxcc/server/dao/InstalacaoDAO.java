package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class InstalacaoDAO extends GenericDAO<InstalacaoDAO> {

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
    }
    
    public Set<String> getPkNamesSet() {    	
    	return ClienteDAO.createKey("COD_INSTALACAO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.INSTALACAO";
}