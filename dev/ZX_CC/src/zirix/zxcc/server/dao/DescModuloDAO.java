package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class DescModuloDAO extends GenericDAO<DescModuloDAO> {

    public DescModuloDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public DescModuloDAO(){
    	super(TABLENAME);
    }
        
    public static PkList createKey(String name,int value) {
		
		PkList key = new PkList();		
		key.put(name, new Integer(value));
		
		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	
    	setAttValueFor("NOME_MODELO",res.getString("NOME_MODELO"));
    	setAttValueFor("COD_MARCA",res.getInt("COD_MARCA"));  	        	    	   
    }
    
    public Set<String> getPkNamesSet() {    	
    	return DescModuloDAO.createKey("COD_MODELO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }
    
    public final static String TABLENAME = "ZX_CC_DEV.dbo.DESC_MODULO";
   
        
}