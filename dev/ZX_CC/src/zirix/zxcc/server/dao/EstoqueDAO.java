package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class EstoqueDAO extends GenericDAO<EstadoModuloDAO> {

    public EstoqueDAO(PkList pkList) {
        super(TABLENAME,pkList);
        
    }

    public EstoqueDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();		
		key.put(name, new Integer(value));

		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_CLIENTE", res.getInt("COD_CLIENTE"));
    	setAttValueFor("COD_INSTALACAO", res.getInt("COD_INSTALACAO"));
    }

    public Set<String> getPkNamesSet() {    	
    	return EstoqueDAO.createKey("COD_ESTOQUE", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

    public final static String TABLENAME = "ZX_CC_DEV.dbo.ESTOQUE";
          
}