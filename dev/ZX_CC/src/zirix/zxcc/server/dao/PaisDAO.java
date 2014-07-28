package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public class PaisDAO extends GenericDAO<PaisDAO> {

    public PaisDAO(PkList pkList) {
        super(TABLENAME,pkList);
        setCanDelete(true);
    }

    public PaisDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME_PAIS", res.getString("NOME_PAIS"));
    }

    public Set<String> getPkNamesSet() {    	
    	return PaisDAO.createKey("COD_PAIS", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();    	    	
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.PAIS";
          
}