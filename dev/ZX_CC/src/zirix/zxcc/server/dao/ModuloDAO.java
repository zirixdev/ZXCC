package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModuloDAO extends GenericDAO<ModuloDAO> {

    public ModuloDAO(PkList pkList) {
        super(TABLENAME,pkList);
        
    }

    public ModuloDAO(){
    	super(TABLENAME);
    }
        
    public static PkList createKey(String name,int value) {
		
		PkList key = new PkList();		
		key.put(name, new Integer(value));
		
		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NUMERO_MODULO",res.getString("NUMERO_MODULO"));
    	setAttValueFor("COD_CLIENTE", res.getInt("COD_CLIENTE"));
    	setAttValueFor("COD_MODELO", res.getInt("COD_MODELO"));
    	setAttValueFor("COD_CHIP", res.getInt("COD_CHIP"));
    	setAttValueFor("NFE",res.getString("NFE"));
    	setAttValueFor("COD_ESTADO", res.getInt("COD_ESTADO"));
    	setAttValueFor("COD_INSTALACAO", res.getInt("COD_INSTALACAO"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return ModuloDAO.createKey("COD_MODULO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.MODULO";
}