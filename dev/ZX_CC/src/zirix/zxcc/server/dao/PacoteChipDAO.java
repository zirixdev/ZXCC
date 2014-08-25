package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstantsServlet;

public class PacoteChipDAO extends GenericDAO<ChipDAO> {


    public PacoteChipDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public PacoteChipDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_CONTA",res.getInt("COD_CONTA"));
    	setAttValueFor("NOME_PACOTE",res.getString("NOME_PACOTE"));
    	setAttValueFor("INFO_PACOTE",res.getString("INFO_PACOTE"));
    	setAttValueFor("COMPARTILHADO",res.getInt("COMPARTILHADO"));
    	setAttValueFor("DELETED",res.getInt("DELETED"));
    }

    public Set<String> getPkNamesSet() {
    	return PacoteChipDAO.createKey("COD_PACOTE", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = ZXCCConstantsServlet.DB_NAME_ + "PACOTE_CHIP";
}