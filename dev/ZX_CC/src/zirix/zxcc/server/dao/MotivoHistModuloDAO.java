package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstantsServlet;

public class MotivoHistModuloDAO extends GenericDAO<MotivoHistModuloDAO> {


    public MotivoHistModuloDAO(PkList pkList) {
        super(TABLENAME,pkList);
        
    }

    public MotivoHistModuloDAO(){
    	super(TABLENAME);
    }
        
    public static PkList createKey(String name,int value) {
		
		PkList key = new PkList();		
		key.put(name, new Integer(value));
		
		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME",res.getString("NOME"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return MotivoHistModuloDAO.createKey("COD_MOTIVO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = ZXCCConstantsServlet.DB_NAME_ + "MOTIVO_HIST_MODULO";
}