package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class TipoDocumentoDAO extends GenericDAO<TipoDocumentoDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public TipoDocumentoDAO(PkList pkList) {
        super(TABLENAME,pkList);
        setCanDelete(true);
    }

    public TipoDocumentoDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME",res.getString("NOME"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }

    public Set<String> getPkNamesSet() {
    	return TipoDocumentoDAO.createKey("COD_DOCUMENTO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "TIPO_DOCUMENTO";
}