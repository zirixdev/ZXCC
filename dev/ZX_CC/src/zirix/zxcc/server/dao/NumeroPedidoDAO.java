package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class NumeroPedidoDAO extends GenericDAO<NumeroPedidoDAO> {

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
    }
    
    public Set<String> getPkNamesSet() {    	
    	return NumeroPedidoDAO.createKey("NUM_PEDIDO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.NUMERO_PEDIDO";
   
        
}