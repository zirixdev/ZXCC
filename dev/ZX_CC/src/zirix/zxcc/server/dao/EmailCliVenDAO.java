package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import zirix.zxcc.server.ZXCCConstants;

public class EmailCliVenDAO extends GenericDAO<EmailCliVenDAO> {



    public EmailCliVenDAO(PkList pkList) {
        super(TABLENAME,pkList);
        setCanDelete(true);
    }

    public EmailCliVenDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_CLI_VEN",res.getInt("COD_CLI_VEN"));
    	setAttValueFor("TIPO_CLI_VEN",res.getInt("TIPO_CLI_VEN"));
    	setAttValueFor("EMAIL",res.getString("EMAIL"));
    }

    public Set<String> getPkNamesSet() {
    	return EmailCliVenDAO.createKey("COD_EMAIL", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

    public final static String TABLENAME = ZXCCConstants.db_name + "EMAIL_CLI_VEN";
}