package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class GrupoDAO extends GenericDAO<GrupoDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public GrupoDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public GrupoDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME_GRUPO",res.getString("NOME_GRUPO"));
    	setAttValueFor("GRUPO",res.getString("GRUPO"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {
    	return GrupoDAO.createKey("COD_GRUPO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "GRUPO";
   
        
}