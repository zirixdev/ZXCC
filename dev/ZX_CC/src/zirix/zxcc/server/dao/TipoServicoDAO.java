package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class TipoServicoDAO extends GenericDAO<TipoServicoDAO> {

    public TipoServicoDAO(PkList pkList) {
        super(TABLENAME,pkList);
        setCanDelete(true);
    }

    public TipoServicoDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {

    	setAttValueFor("NOME_SERVICO",res.getString("NOME_SERVICO"));
    }

    public Set<String> getPkNamesSet() {
    	return TipoServicoDAO.createKey("COD_SERVICO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.TIPO_SERVICO";
}