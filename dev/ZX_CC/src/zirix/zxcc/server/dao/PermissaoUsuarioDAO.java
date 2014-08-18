package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class PermissaoUsuarioDAO extends GenericDAO<PermissaoUsuarioDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public PermissaoUsuarioDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public PermissaoUsuarioDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_USUARIO",res.getInt("COD_USUARIO"));
    	setAttValueFor("COD_TELA",res.getInt("COD_TELA"));
    	setAttValueFor("CHAVE",res.getInt("CHAVE"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }
    
    public Set<String> getPkNamesSet() {
    	return PermissaoUsuarioDAO.createKey("COD_PERMISSAO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "PERMISSAO_USUARIO";
   
        
}