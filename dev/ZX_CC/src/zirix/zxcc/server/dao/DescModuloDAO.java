package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class DescModuloDAO extends GenericDAO<DescModuloDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

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
    	setAttValueFor("DELETED",res.getInt("DELETED"));      	    	   
    }
    
    public Set<String> getPkNamesSet() {    	
    	return DescModuloDAO.createKey("COD_MODELO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }
    
    public final static String TABLENAME = AMBIENTE_.db_name + "DESC_MODULO";
   
        
}