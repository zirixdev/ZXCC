package zirix.zxcc.cron;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class ZXAlerter {


	public ZXAlerter() {}

	public static void main(String[] args) {


	  try {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/zxccmock?" + "user=zirix&password=pinguim01");

		PreparedStatement stmt1 = con.prepareStatement("SELECT NOW(),start_time_stamp,sched_time_stamp,restriction_id,work_id,work_name,cod_usuario from sched_work where work_state_id != 2");
		ResultSet res1 = stmt1.executeQuery();


		String now = res1.getString(1);

		while (res1.next()) {

			String resctriction_id = res1.getString(4);

			PreparedStatement stmt2 = con.prepareStatement("SELECT restriction_value from restriction_work where restriction_id == " + resctriction_id);
			ResultSet res2 = stmt2.executeQuery();

			res2.next();

			Timestamp sched_time = res1.getTimestamp(2);
			Timestamp start_time = res1.getTimestamp(3);
			Integer work_id = res1.getInt(5);
			String work_name = res1.getString(6);
			Integer cod_usuario = res1.getInt(7);
			Timestamp restriction_val = res2.getTimestamp(1);

			if (eval(sched_time,start_time,restriction_val)) 
				notify(work_id,work_name,cod_usuario);
		}

	  } catch(Exception ex) {

		ex.printStackTrace();
	  }
	}

	private static boolean eval(Timestamp sched,Timestamp start,Timestamp rest) {

		return true;
	}

	private static void notify(Integer work_id,String work_name,Integer cod_usuario) {


	}
}
