/*ZIRIX CONTROL CENTER - OS DAO
DESENVOLVIDO POR ZIRIX SOLU��ES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.*;

public class OsDAO extends GenericDAO<PedidoDAO> {


    public OsDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public OsDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NUM_OS",res.getInt("NUM_OS"));
    	setAttValueFor("COD_TECNICO",res.getInt("COD_TECNICO"));
    	setAttValueFor("ARRAIVE_TIME",res.getTime("ARRAIVE_TIME"));
    	setAttValueFor("LEAVE_TIME",res.getTime("LEAVE_TIME"));
    	setAttValueFor("FRUSTRADA",res.getInt("FRUSTRADA"));
    	setAttValueFor("TIPO_OS",res.getInt("TIPO_OS"));
    	setAttValueFor("HAVE_TEST",res.getInt("HAVE_TEST"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));        	    	   
    }
    
    public Set<String> getPkNamesSet() {    	
    	return PedidoDAO.createKey("COD_OS", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = ZXMain.DB_NAME_ + "OS";
   
        
}