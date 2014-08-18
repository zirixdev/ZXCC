package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class TelaDAO extends GenericDAO<TelaDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public TelaDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public TelaDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME_TELA",res.getString("NOME_TELA"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {
    	return TelaDAO.createKey("COD_TELA", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "TELA";
   
        
}