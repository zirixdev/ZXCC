package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class EstoqueDAO extends GenericDAO<EstadoModuloDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

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
    	setAttValueFor("COD_INSTALACAO", res.getInt("COD_INSTALACAO"));
    	setAttValueFor("DELETED", res.getInt("DELETED"));
    }

    public Set<String> getPkNamesSet() {    	
    	return EstoqueDAO.createKey("COD_ESTOQUE", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

    public final static String TABLENAME = AMBIENTE_.db_name + "ESTOQUE";
          
}