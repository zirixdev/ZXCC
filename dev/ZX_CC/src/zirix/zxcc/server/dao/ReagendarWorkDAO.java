/*ZIRIX CONTROL CENTER - REAGENDAR DAO
DESENVOLVIDO POR ZIRIX SOLUCOES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import zirix.zxcc.server.*;

public class ReagendarWorkDAO extends GenericDAO<ReagendarWorkDAO> {


    public ReagendarWorkDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public ReagendarWorkDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("COD_PEDIDO",res.getInt("COD_PEDIDO"));
    	setAttValueFor("DEFINED_WORK_ID",res.getInt("DEFINED_WORK_ID"));
    }

    public Set<String> getPkNamesSet() {
    	return ReagendarWorkDAO.createKey("COD_REAGENDAR", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = ZXMain.DB_NAME_ + "REAGENDAR_WORK";
}