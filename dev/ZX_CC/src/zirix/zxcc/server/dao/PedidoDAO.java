package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class PedidoDAO extends GenericDAO<PedidoDAO> {

    public PedidoDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public PedidoDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_VENDEDOR",res.getInt("COD_VENDEDOR"));
    	setAttValueFor("COD_CLIENTE",res.getInt("COD_CLIENTE"));
    	setAttValueFor("NUM_PEDIDO",res.getInt("NUM_PEDIDO"));
    	setAttValueFor("COD_TIPO",res.getInt("COD_TIPO"));
    	setAttValueFor("COD_UNIDADE",res.getInt("COD_UNIDADE"));
    	setAttValueFor("TIPO_UNIDADE",res.getInt("TIPO_UNIDADE")); 	        	    	   
    }
    
    public Set<String> getPkNamesSet() {    	
    	return PedidoDAO.createKey("COD_PEDIDO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.PEDIDO";
   
        
}