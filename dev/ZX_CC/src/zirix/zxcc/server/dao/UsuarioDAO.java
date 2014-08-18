package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class UsuarioDAO extends GenericDAO<UsuarioDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public UsuarioDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public UsuarioDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME_USUARIO",res.getString("NOME_USUARIO"));
    	setAttValueFor("LOGIN",res.getString("LOGIN"));
    	setAttValueFor("SENHA",res.getString("SENHA"));
    	setAttValueFor("COD_GRUPO",res.getInt("COD_GRUPO"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {
    	return UsuarioDAO.createKey("COD_USUARIO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "USUARIO";
   
        
}