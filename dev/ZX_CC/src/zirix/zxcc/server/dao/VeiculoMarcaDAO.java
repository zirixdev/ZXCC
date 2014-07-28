package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public class VeiculoMarcaDAO extends GenericDAO<VeiculoMarcaDAO> {

    public VeiculoMarcaDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public VeiculoMarcaDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	
    	setAttValueFor("NOME_MARCA",res.getString("NOME_MARCA"));
    }

    public Set<String> getPkNamesSet() {
    	return VeiculoMarcaDAO.createKey("COD_MARCA", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = "ZX_CC_DEV.dbo.VEICULO_MARCA";
}