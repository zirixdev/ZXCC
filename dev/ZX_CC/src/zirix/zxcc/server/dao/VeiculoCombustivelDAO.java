package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class VeiculoCombustivelDAO extends GenericDAO<VeiculoCombustivelDAO> {

    public VeiculoCombustivelDAO(PkList pkList) {
        super(TABLENAME,pkList);
        setCanDelete(true);
    }

    public VeiculoCombustivelDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	
    	setAttValueFor("NOME_COMBUSTIVEL",res.getString("NOME_COMBUSTIVEL"));
    }

    public Set<String> getPkNamesSet() {
    	return VeiculoCombustivelDAO.createKey("COD_COMBUSTIVEL", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.VEICULO_COMBUSTIVEL";
}