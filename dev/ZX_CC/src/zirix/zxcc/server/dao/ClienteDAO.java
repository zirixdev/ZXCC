package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class ClienteDAO extends GenericDAO<ClienteDAO> {

    public ClienteDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public ClienteDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    /*
     * Temos de tratar a FK
     * @see zirix.zxcc.server.dao.GenericDAO#delete()
     */
    /*public void delete() throws SQLException {
    	String query1 = "DELETE FROM ZX_CC_DEV.dbo.CONTATO_CLIENTE WHERE COD_CLIENTE=\'" + getPkValueFor("COD_CLIENTE") + "\'";
    	String query2 = "DELETE FROM ZX_CC_DEV.dbo.DOCUMENTO_CLIENTE WHERE COD_CLIENTE=\'" + getPkValueFor("COD_CLIENTE") + "\'";
    	String query3 = "DELETE FROM ZX_CC_DEV.dbo.VEICULO WHERE COD_CLIENTE=\'" + getPkValueFor("COD_CLIENTE") + "\'";
    	String query4 = "DELETE FROM ZX_CC_DEV.dbo.MODULO WHERE COD_CLIENTE=\'" + getPkValueFor("COD_CLIENTE") + "\'";

    	DAOManager.getInstance().executeQuery(query1);
    	DAOManager.getInstance().executeQuery(query2);
    	DAOManager.getInstance().executeQuery(query3);
    	DAOManager.getInstance().executeQuery(query4);

    }*/

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("NOME",res.getString("NOME"));
    	setAttValueFor("TIPO",res.getInt("TIPO"));
    	setAttValueFor("NOME_FANTASIA",res.getString("NOME_FANTASIA"));
    	setAttValueFor("SITE",res.getString("SITE"));
    	setAttValueFor("DATA_NASCIMENTO",res.getDate("DATA_NASCIMENTO")); //TODO verificar
    	setAttValueFor("DATA_INGRESSO",res.getDate("DATA_INGRESSO")); //TODO verificar
    	setAttValueFor("COD_VENDEDOR", res.getInt("COD_VENDEDOR"));     	
    }

    public Set<String> getPkNamesSet() {
    	return ClienteDAO.createKey("COD_CLIENTE", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.CLIENTE";
}