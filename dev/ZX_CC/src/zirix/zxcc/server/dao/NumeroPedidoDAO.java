package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class NumeroPedidoDAO extends GenericDAO<NumeroPedidoDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public NumeroPedidoDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public NumeroPedidoDAO(){
    	super(TABLENAME);
    }
        
    public static PkList createKey(String name,int value) {
		
		PkList key = new PkList();		
		key.put(name, new Integer(value));
		
		return key;				
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_USUARIO",res.getInt("COD_USUARIO"));
    	setAttValueFor("DATA_GERACAO",res.getDate("DATA_GERACAO"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {    	
    	return NumeroPedidoDAO.createKey("NUM_PEDIDO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "NUMERO_PEDIDO";
   
        
}