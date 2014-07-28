package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class TipoUnidadeDAO extends GenericDAO<TipoUnidadeDAO> {

    public TipoUnidadeDAO(PkList pkList) {
        super(TABLENAME,pkList);
        setCanDelete(true);
    }

    public TipoUnidadeDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME",res.getString("NOME"));
    }

    public Set<String> getPkNamesSet() {
    	return TipoUnidadeDAO.createKey("COD_UNIDADE", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.TIPO_UNIDADE";
}