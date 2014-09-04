package zirix.zxcc.server.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import zirix.zxcc.server.ZXMain;
import zirix.zxcc.server.dao.DAOManager;

public class MockEvaluator {

	public MockEvaluator(int workID){
		setWorkID(workID);
	}

	public MockEvaluator(){
		super();
	}

	public boolean endWork(){
		try{
			DAOManager.getInstance().executeQuery("UPDATE " + ZXMain.DB_NAME_ + "SCHED_WORK SET END_TIMESTAMP = GETDATE() "
			   		                            + " WHERE START_TIMESTAMP IS NOT NULL AND END_TIMESTAMP IS NULL AND WORK_ID = " + workID_);
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally{}
		if(canChageState()){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean canChageState(){
		int restWorks;
		getIDs();
		restWorks = restWorks();
		if(restWorks > 0){
			return false;
		}else{
			return true;
		}
		
	}

	private void getIDs(){
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT " + ZXMain.DB_NAME_ + "SCHED_WORK.PROCESS_STATE_ID "
					+ "                                                              , " + ZXMain.DB_NAME_ + "SCHED_WORK.PROCESS_ID "
					+ "                                                           FROM " + ZXMain.DB_NAME_ + "SCHED_WORK "
					+ "                                                          WHERE " + ZXMain.DB_NAME_ + "SCHED_WORK.WORK_ID = " + workID_);
			for (int i=0;i < values.size();i++) {
				String[] attList = new String[2];
				attList[0] = (String) values.get(i)[0];
				setProcessStateID(Integer.parseInt(attList[0]));
				attList[1] = (String) values.get(i)[1];
				setProcessID(Integer.parseInt(attList[1]));
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}finally{}
	}
	
	@SuppressWarnings("finally")
	private int restWorks(){
		Vector<String[]> countWorks = new Vector<String[]>();
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT COUNT(*) "
					+ "                                                           FROM " + ZXMain.DB_NAME_ + "SCHED_WORK "
					+ "                                                          WHERE " + ZXMain.DB_NAME_ + "SCHED_WORK.END_TIMESTAMP IS NULL "
					+ "                                                            AND " + ZXMain.DB_NAME_ + "SCHED_WORK.PROCESS_STATE_ID = " + processStateID_
					+ "                                                            AND " + ZXMain.DB_NAME_ + "SCHED_WORK.PROCESS_ID = " + processID_);
			for (int i=0;i < values.size();i++) {
				String[] attList = new String[1];
				attList[0] = (String) values.get(i)[0];
				countWorks.add(attList);
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}finally{
			return Integer.parseInt(countWorks.elementAt(0)[0].trim());
		}
	}

	private int workID_;
	private int processID_;
	private int processStateID_;

    public void setWorkID(int workID) {workID_ = workID;}
    public void setProcessID(int processID) {processID_ = processID;}
    public void setProcessStateID(int processStateID){processStateID_ = processStateID;}
    
}
