package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ObsPedidoDAO extends GenericDAO<ObsPedidoDAO> {

    public ObsPedidoDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public ObsPedidoDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();		
		key.put(name, new Integer(value));

		return key;				
	} 
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_PEDIDO",res.getInt("COD_PEDIDO"));
    	setAttValueFor("COD_CLIENTE",res.getInt("COD_CLIENTE"));
    	setAttValueFor("INDICE",res.getInt("INDICE"));
    	setAttValueFor("OBSERVACAO",res.getString("OBSERVACAO"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return ObsPedidoDAO.createKey("COD_OBS", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.OBS_PEDIDO";
   
        
}