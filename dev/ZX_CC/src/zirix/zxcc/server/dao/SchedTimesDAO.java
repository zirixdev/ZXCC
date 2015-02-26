/*ZIRIX CONTROL CENTER - SCHED TIMES DAO
DESENVOLVIDO POR ZIRIX SOLUCOES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import zirix.zxcc.server.*;

public class SchedTimesDAO extends GenericDAO<SchedTimesDAO> {


    public SchedTimesDAO(PkList pkList) {
        super(TABLENAME,pkList);
    }

    public SchedTimesDAO(){
    	super(TABLENAME);
    }

    public static PkList createKey(String name,int value) {

		PkList key = new PkList();
		key.put(name, new Integer(value));

		return key;
	}

    public void loadAttsFromResultSet(ResultSet res) throws SQLException {
    	setAttValueFor("DEFINED_WORK_ID",res.getInt("DEFINED_WORK_ID"));
    	setAttValueFor("TIMES_VALUE",res.getInt("TIMES_VALUE"));
    }

    public Set<String> getPkNamesSet() {
    	return SchedTimesDAO.createKey("SCHED_TIMES_ID", GenericDAO.AUTO_INCREMENT_PK_VALUE).keySet();
    }

	public final static String TABLENAME = ZXMain.DB_NAME_ + "SCHED_TIMES";
}