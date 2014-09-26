/*ZIRIX CONTROL CENTER - MOCK SCHEDULE BEAN
DESENVOLVIDO POR RAPHAEL B. MARQUES

CLIENTE: ZIRIX SOLUÇÕES EM RASTREAMENTO
TECNOLOGIAS UTILIZADAS: JAVA*/
package zirix.zxcc.server.mock;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import zirix.zxcc.server.ZXMain;
import zirix.zxcc.server.dao.DAOManager;
public class MockScheduleBean {
	private Integer COD_USUARIO_ = null;
	public MockScheduleBean(String[] pkVal) {
		COD_USUARIO_ = new Integer(pkVal[0]);
	}
	public void setPk(Object pkVal) {
	}
	@SuppressWarnings("finally")
	public Vector<String[]> getWork(){
		Vector<String[]> work = new Vector<String[]>();
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT " + ZXMain.DB_NAME_ + "SCHED_WORK.WORK_ID "
					+ 														   "     , " + ZXMain.DB_NAME_ + "SCHED_WORK.WORK_NAME "
					+ 														   "     , " + ZXMain.DB_NAME_ + "SCHED_PROCESS.PROCESS_NAME "
					+ 														   "     , ISNULL(" + ZXMain.DB_NAME_ + "SCHED_WORK.COD_USUARIO,0) "
					+ 														   "  FROM " + ZXMain.DB_NAME_ + "SCHED_WORK "
					+ 														   "     , " + ZXMain.DB_NAME_ + "SCHED_PROCESS "
					+ 														   "     , " + ZXMain.DB_NAME_ + "WORK_USER "
					+ 														   " WHERE " + ZXMain.DB_NAME_ + "WORK_USER.WORK_GROUP_ID = " + ZXMain.DB_NAME_ + "SCHED_WORK.WORK_GROUP_ID "
					+ 														   "   AND " + ZXMain.DB_NAME_ + "SCHED_WORK.PROCESS_ID = " + ZXMain.DB_NAME_ + "SCHED_PROCESS.PROCESS_ID "
					+ 														   "   AND " + ZXMain.DB_NAME_ + "SCHED_WORK.END_TIMESTAMP IS NULL "
					+ 														   "   AND " + ZXMain.DB_NAME_ + "WORK_USER.COD_USUARIO = " + COD_USUARIO_);
			for (int i=0;i < values.size();i++) {
				String[] attList = new String[4];
				attList[0] = (String) values.get(i)[0].toString();
				attList[1] = (String) values.get(i)[1];
				attList[2] = (String) values.get(i)[2];
				attList[3] = (String) values.get(i)[3].toString();
				work.add(attList);
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return work;
		}
	}
	@SuppressWarnings("finally")
	public Vector<String[]> getWorkService(String workName, String processName){
		Vector<String[]> workService = new Vector<String[]>();
		try {
			ArrayList<Object[]> values = DAOManager.getInstance().executeQuery("SELECT  " + ZXMain.DB_NAME_ + "WORK_SERVICE.SERVICE_NAME "
					+ " 											 FROM  " + ZXMain.DB_NAME_ + "WORK_SERVICE "
					+ " 											    ,  " + ZXMain.DB_NAME_ + "DEFINED_WORK "
					+ " 											    ,  " + ZXMain.DB_NAME_ + "DEFINED_PROCESS "
					+ "                                             WHERE  " + ZXMain.DB_NAME_ + "DEFINED_WORK.PROCESS_ID =  " + ZXMain.DB_NAME_ + "WORK_SERVICE.PROCESS_ID "
					+ "                                               AND  " + ZXMain.DB_NAME_ + "DEFINED_WORK.WORK_ID =  " + ZXMain.DB_NAME_ + "WORK_SERVICE.WORK_ID "
					+ "                                               AND  " + ZXMain.DB_NAME_ + "DEFINED_PROCESS.PROCESS_NAME = N'" + processName + "' "
					+ "                                               AND  " + ZXMain.DB_NAME_ + "DEFINED_WORK.WORK_NAME = N'" + workName + "' ");

			for (int i=0;i < values.size();i++) {
				String[] attList = new String[1];
				attList[0] = (String) values.get(i)[0];
				workService.add(attList);
			}
		}catch (SQLException ex) {
    		ex.printStackTrace();
		}  finally {
			return workService;
		}
	}
	public void setStartTimestamp(String work_id){
		if(work_id.compareTo("0") != 0){
			try {
				DAOManager.getInstance().executeQuery("UPDATE " + ZXMain.DB_NAME_ + "SCHED_WORK "
						+ 							  "   SET START_TIMESTAMP = GETDATE() "
						+ 							  "     , COD_USUARIO = " + COD_USUARIO_
						+ 							  " WHERE START_TIMESTAMP IS NULL AND WORK_ID = " + work_id);
			}catch (SQLException ex) {
				ex.printStackTrace();
			}finally{}
		}
	}
}