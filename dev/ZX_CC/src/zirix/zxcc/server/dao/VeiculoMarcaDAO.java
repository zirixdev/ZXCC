package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class VeiculoMarcaDAO extends GenericDAO<VeiculoMarcaDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

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
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }

    public Set<String> getPkNamesSet() {
    	return VeiculoMarcaDAO.createKey("COD_MARCA", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "VEICULO_MARCA";
}