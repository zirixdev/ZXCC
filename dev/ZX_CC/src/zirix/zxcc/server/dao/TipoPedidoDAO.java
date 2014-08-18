package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class TipoPedidoDAO extends GenericDAO<TipoPedidoDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

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
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }

    public Set<String> getPkNamesSet() {
    	return TipoPedidoDAO.createKey("COD_TIPO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "TIPO_PEDIDO";
}