/*ZIRIX CONTROL CENTER - MOCK EVALUATOR
DESENVOLVIDO POR RAPHAEL B. MARQUES

CLIENTE: ZIRIX SOLU��ES EM RASTREAMENTO
TECNOLOGIAS UTILIZADAS: JAVA*/
package zirix.zxcc.server.mock;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import zirix.zxcc.server.ZXMain;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.dao.PkList;
import zirix.zxcc.server.mock.dao.SchedWorkDAO;
public class MockEvaluator {
	private SchedWorkDAO dao_ = null;
	private Integer WORK_ID_ = null;
	public MockEvaluator(int workID){
		setPk(workID);
	}
	public void setPk(int pkVal) {
		WORK_ID_ = pkVal;
		PkList pkList = new PkList();
	    pkList.put("WORK_ID",WORK_ID_);
	    dao_ = new SchedWorkDAO(pkList);
	    try {
	    	dao_.read();
	    } catch (SQLException ex) {
	    	ex.printStackTrace();
	    } finally {}
	}
	@SuppressWarnings("finally")
	public boolean endWork() throws SQLException{
		try{
			DAOManager.getInstance().executeUpdate("UPDATE " + ZXMain.DB_NAME_ + "SCHED_WORK "
					+ 							  "   SET END_TIMESTAMP = NOW() "
					+ 							  " WHERE START_TIMESTAMP IS NOT NULL "
					+ 							  "   AND END_TIMESTAMP IS NULL "
					+ 							  "   AND WORK_ID = " + WORK_ID_);
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally{
			return canChangeState();
		}
	}
	private boolean canChangeState() throws SQLException{
		startDependencyWorks();
		if(restWorksCount() > 0){
			return false;
		}else{
			return true;
		}
	}
	private void startDependencyWorks() throws SQLException{
		Vector<String[]> dependencyWorks = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT count(*) "
					+ 														   "     , " + ZXMain.DB_NAME_ + "DEFINED_WORK.DEPENDENCY_WORK_ID "
					+ 														   "  FROM " + ZXMain.DB_NAME_ + "DEFINED_WORK "
					+ 														   " WHERE " + ZXMain.DB_NAME_ + "DEFINED_WORK.DEPENDENCY_WORK_ID = " + (Integer)dao_.getAttValueFor("DEFINED_WORK_ID")
					+ 														   " GROUP BY " + ZXMain.DB_NAME_ + "DEFINED_WORK.DEPENDENCY_WORK_ID ");
			for (int i=0;i<values.size();i++) {
				String[] attList = new String[2];
				attList[0] = values.get(i)[0].toString();
				attList[1] = values.get(i)[1].toString();
				dependencyWorks.add(attList);
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}finally{
			for (int i=0;i<dependencyWorks.size();i++) {
				if(Integer.parseInt(dependencyWorks.elementAt(i)[0].trim()) > 0){
					MockSchedule.createSchedWork((Integer)dao_.getAttValueFor("PROCESS_ID"), (Integer)dao_.getAttValueFor("DEFINED_PROCESS_ID"), (Integer)dao_.getAttValueFor("PROCESS_STATE_ID"), Integer.parseInt(dependencyWorks.get(i)[1].toString()),(Integer)dao_.getAttValueFor("PK_COLUMN"));
				}
			}
		}
	}
	@SuppressWarnings("finally")
	private int restWorksCount(){
		Vector<String[]> countWorks = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT COUNT(*) "
					+ "                                                           FROM " + ZXMain.DB_NAME_ + "SCHED_WORK "
					+ "                                                          WHERE " + ZXMain.DB_NAME_ + "SCHED_WORK.END_TIMESTAMP IS NULL "
					+ "                                                            AND " + ZXMain.DB_NAME_ + "SCHED_WORK.PROCESS_STATE_ID = " + (Integer)dao_.getAttValueFor("PROCESS_STATE_ID")
					+ "                                                            AND " + ZXMain.DB_NAME_ + "SCHED_WORK.PROCESS_ID = " + (Integer)dao_.getAttValueFor("PROCESS_ID"));
			for (int i=0;i < values.size();i++) {
				String[] attList = new String[1];
				attList[0] = (String) values.get(i)[0].toString();
				countWorks.add(attList);
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}finally{
			return Integer.parseInt(countWorks.elementAt(0)[0].trim());
		}
	}
}
