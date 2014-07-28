package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class MarcaModuloDAO extends GenericDAO<MarcaModuloDAO> {

    public MarcaModuloDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public MarcaModuloDAO(){
    	super(TABLENAME);
    }
        
    public static PkList createKey(String name,int value) {
		
		PkList key = new PkList();		
		key.put(name, new Integer(value));
		
		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {

    	setAttValueFor("NOME_MARCA", res.getString("NOME_MARCA"));
    	
    }
    
    public Set<String> getPkNamesSet() {    	
    	return MarcaModuloDAO.createKey("COD_MARCA", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.MARCA_MODULO";
          
}