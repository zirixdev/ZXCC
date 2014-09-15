/*ZIRIX CONTROL CENTER - MOCK SCHEDULE
DESENVOLVIDO POR ZIRIX SOLUÇÕES EM RASTREAMENTO LTDA.

DESENVOLVEDOR: RAPHAEL B. MARQUES
TECNOLOGIAS UTILIZADAS: JAVA*/

package zirix.zxcc.server.mock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import zirix.zxcc.server.ZXMain;
import zirix.zxcc.server.dao.DAOManager;
import zirix.zxcc.server.dao.PkList;
import zirix.zxcc.server.mock.dao.*;

public class MockSchedule {
	private SchedWorkDAO dao_ = null;
	private Integer WORK_ID_ = null;

	public MockSchedule(int workID){
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

	public void changeState() throws SQLException{
		try{
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT " + ZXMain.DB_NAME_ + "DEFINED_PROCESS_STATE.NEXT_STATE_ID "
					+ "                                                           FROM " + ZXMain.DB_NAME_ + "DEFINED_PROCESS_STATE "
					+ "                                                          WHERE " + ZXMain.DB_NAME_ + "DEFINED_PROCESS_STATE.STATE_NUM = " + (Integer)dao_.getAttValueFor("PROCESS_STATE_ID")
					+ "                                                            AND " + ZXMain.DB_NAME_ + "DEFINED_PROCESS_STATE.PROCESS_ID = " + (Integer)dao_.getAttValueFor("DEFINED_PROCESS_ID"));
			for (int i=0;i < values.size();i++) {
				String[] attList = new String[1];
				attList[0] = (String) values.get(i)[0].toString();
				setNextState(Integer.parseInt(attList[0]));
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}finally{}
		
		createSchedWork((Integer)dao_.getAttValueFor("PROCESS_ID"),(Integer)dao_.getAttValueFor("DEFINED_PROCESS_ID"),nextState_,0);
		
		try{
			DAOManager.getInstance().executeQuery("UPDATE " + ZXMain.DB_NAME_ + "SCHED_PROCESS SET STATE_ID = " + nextState_
			   		                            + " WHERE PROCESS_ID = " + (Integer)dao_.getAttValueFor("PROCESS_ID"));
		}catch (SQLException ex) {
			ex.printStackTrace();
		}finally{}
	}
	
	public static void createSchedWork(int PROCESS_ID, int DEFINED_PROCESS_ID, int STATE_ID, int DEPENDENCY_WORK_ID) throws SQLException{
	   SchedWorkDAO daoSchedWork = new SchedWorkDAO();
	   Vector<String[]> DefinedWork = new Vector<String[]>();
	   try {
		   ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT WORK_NAME, RESTRICTION_ID, PROCESS_STATE_ID, WORK_ALERT_ID, DEPENDENCY_WORK_ID, WORK_GROUP_ID, WORK_ID "
		   		+ " FROM " + ZXMain.DB_NAME_ + "DEFINED_WORK WHERE PROCESS_STATE_ID=" + STATE_ID + " AND PROCESS_ID=" + DEFINED_PROCESS_ID + " AND DEPENDENCY_WORK_ID=" + DEPENDENCY_WORK_ID + ";");
		   for (int i=0;i < values.size();i++) {
			   String[] attList = new String[7];
			   attList[0] = (String) values.get(i)[0];
			   attList[1] = values.get(i)[1].toString();
			   attList[2] = values.get(i)[2].toString();
			   attList[3] = values.get(i)[3].toString();
			   attList[4] = values.get(i)[4].toString();
			   attList[5] = values.get(i)[5].toString();
			   attList[6] = values.get(i)[5].toString();
			   DefinedWork.add(attList);
		   }
	   }catch (SQLException ex) {
		   ex.printStackTrace();
	   }finally {
		   for(int i=0; i<DefinedWork.size();i++){
			   daoSchedWork.setAttValueFor("WORK_NAME",(DefinedWork.elementAt(i)[0].trim()).toString());
			   daoSchedWork.setAttValueFor("RESTRICTION_ID",Integer.parseInt(DefinedWork.elementAt(i)[1].trim()));
			   daoSchedWork.setAttValueFor("PROCESS_STATE_ID",Integer.parseInt(DefinedWork.elementAt(i)[2].trim()));
			   daoSchedWork.setAttValueFor("WORK_ALERT_ID",Integer.parseInt(DefinedWork.elementAt(i)[3].trim()));
			   daoSchedWork.setAttValueFor("DEPENDENCY_WORK_ID",Integer.parseInt(DefinedWork.elementAt(i)[4].trim()));
			   daoSchedWork.setAttValueFor("WORK_GROUP_ID",Integer.parseInt(DefinedWork.elementAt(i)[5].trim()));
			   daoSchedWork.setAttValueFor("DEFINED_WORK_ID",Integer.parseInt(DefinedWork.elementAt(i)[6].trim()));
			   daoSchedWork.setAttValueFor("PROCESS_ID",PROCESS_ID);
			   daoSchedWork.setAttValueFor("DEFINED_PROCESS_ID",DEFINED_PROCESS_ID);

			   daoSchedWork.Create();
		   }
	   }
	}
	private int nextState_;

    public void setNextState(int nextState) {nextState_ = nextState;}
}
