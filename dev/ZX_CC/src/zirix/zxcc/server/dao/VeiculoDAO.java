package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;


public class VeiculoDAO extends GenericDAO<VeiculoDAO> {

	private static ZXCCConstants AMBIENTE_ = new ZXCCConstants();

    public VeiculoDAO(PkList pkList) {
        super(TABLENAME,pkList);
        
    }

    public VeiculoDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}
            
    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_CLIENTE",res.getInt("COD_CLIENTE"));
    	setAttValueFor("PLACA",res.getString("PLACA"));
    	setAttValueFor("ANO_FAB",res.getInt("ANO_FAB"));
    	setAttValueFor("ANO_MOD",res.getInt("ANO_MOD"));
    	setAttValueFor("COD_MARCA",res.getInt("COD_MARCA"));
    	setAttValueFor("MODELO",res.getString("MODELO"));
    	setAttValueFor("COR",res.getString("COR"));
    	setAttValueFor("CHASSI",res.getString("CHASSI"));
    	setAttValueFor("RENAVAN",res.getString("RENAVAN"));
    	setAttValueFor("DATA_INGRESSO",res.getDate("DATA_INGRESSO")); //TODO verificar
    	setAttValueFor("COD_COMBUSTIVEL", res.getInt("COD_COMBUSTIVEL")); 
    	setAttValueFor("VOLT", res.getInt("VOLT")); 
    	setAttValueFor("KM",res.getString("KM"));
    	setAttValueFor("DATA_ULT_VISTORIA",res.getDate("DATA_ULT_VISTORIA")); //TODO verificar
    	setAttValueFor("COD_INSTALACAO", res.getInt("COD_INSTALACAO"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }

    public Set<String> getPkNamesSet() {
    	return VeiculoDAO.createKey("COD_VEICULO", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = AMBIENTE_.db_name + "VEICULO";
       
}