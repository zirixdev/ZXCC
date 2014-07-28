package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class TipoPedidoDAO extends GenericDAO<TipoPedidoDAO> {

    public TipoPedidoDAO(PkList pkList) {
        super(TABLENAME,pkList);
        setCanDelete(true);
    }

    public TipoPedidoDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME_TIPO",res.getString("NOME_TIPO"));
    	setAttValueFor("DESCRICAO",res.getString("DESCRICAO"));
    }

    public Set<String> getPkNamesSet() {
    	return TipoPedidoDAO.createKey("COD_TIPO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.TIPO_PEDIDO";
}