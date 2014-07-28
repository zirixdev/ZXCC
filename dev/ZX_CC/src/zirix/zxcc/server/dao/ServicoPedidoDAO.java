package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ServicoPedidoDAO extends GenericDAO<ServicoPedidoDAO> {

    public ServicoPedidoDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public ServicoPedidoDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	
    	setAttValueFor("COD_SERVICO",res.getInt("COD_USUARIO"));
    	setAttValueFor("COD_TELA",res.getInt("COD_TELA"));
    	setAttValueFor("QUANTIDADE",res.getInt("QUANTIDADE"));
    	setAttValueFor("VALOR_UNITARIO",res.getFloat("VALOR_UNITARIO")); //TODO Verificar
    }
    
    public Set<String> getPkNamesSet() {    	
    	return ServicoPedidoDAO.createKey("COD_SERV_PED", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.SERVICO_PEDIDO";
   
        
}