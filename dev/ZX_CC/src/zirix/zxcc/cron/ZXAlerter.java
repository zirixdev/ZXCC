package zirix.zxcc.cron;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.sql.Time;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import antena.mailer.ADGoogleMailer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.mail.*;
import javax.mail.internet.*;


public class ZXAlerter {


	public static String OVERTIME_SUBJECT = "Tarefa com tempo excedido";
	public static String RISK_SUBJECT = "Tarefa com risco alto de tempo excedido";

	public static String SCHEDED_WORK_FLAG = "0";
	public static String STARTED_WORK_FLAG = "1";
	public static String FINISHED_WORK_FLAG_ = "2";


	public ZXAlerter() {}

	public static void main(String[] args) {

	  try {
		// This will load the MySQL driver, each DB has its own driver
		Class.forName("com.mysql.jdbc.Driver");
		// Setup the connection with the DB
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/zxccmock?" + "user=zirix&password=pinguim01");

		// SCHEDED_WORKs
		PreparedStatement stmt0 = con.prepareStatement("SELECT NOW(),sched_time_stamp,restriction_id,work_id,work_name,cod_usuario from sched_work where work_state_id = " + SCHEDED_WORK_FLAG);
		ResultSet res0 = stmt0.executeQuery();

		// STARTED_WORKs
		PreparedStatement stmt1 = con.prepareStatement("SELECT NOW(),sched_time_stamp,restriction_id,work_id,work_name,cod_usuario from sched_work where work_state_id = " + STARTED_WORK_FLAG);
		ResultSet res1 = stmt1.executeQuery();


		while (res0.next()) {

			String now = res0.getString(1);
			Timestamp now_time = Timestamp.valueOf(now);

			String resctriction_id = res0.getString(3);

			PreparedStatement stmt2 = con.prepareStatement("SELECT restriction_value from restriction_work where restriction_id = " + resctriction_id);
			ResultSet res2 = stmt2.executeQuery();

			Timestamp sched_time = res0.getTimestamp(2);
			Integer work_id = res0.getInt(4);
			String work_name = res0.getString(5);
			Integer cod_usuario = res0.getInt(6);

			res2.next();
			Time restriction_val = res2.getTime(1);

			if (eval0(now_time,sched_time,restriction_val)) 
				notify(work_id,work_name,cod_usuario,RISK_SUBJECT);
		}

		while (res1.next()) {

			String now = res1.getString(1);
			Timestamp now_time = Timestamp.valueOf(now);

			String resctriction_id = res1.getString(3);

			PreparedStatement stmt2 = con.prepareStatement("SELECT restriction_value from restriction_work where restriction_id = " + resctriction_id);
			ResultSet res2 = stmt2.executeQuery();

			Timestamp sched_time = res1.getTimestamp(2);
			Integer work_id = res1.getInt(4);
			String work_name = res1.getString(5);
			Integer cod_usuario = res1.getInt(6);

			res2.next();
			Time restriction_val = res2.getTime(1);

			if (eval1(now_time,sched_time,restriction_val)) 
				notify(work_id,work_name,cod_usuario,OVERTIME_SUBJECT);
		}

	  } catch(Exception ex) {

		ex.printStackTrace();
	  }
	}

	/*
	 * avalia o cenario onde a tarefa se aproxima de 75% do tempo limite e ainda nao foi iniciada
	 */
	private static boolean eval0(Timestamp now,Timestamp sched,Time rest) {

		long sched_millis = sched.getTime();

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(sched_millis);

		cal.add(Calendar.HOUR,rest.getHours());
		cal.add(Calendar.MINUTE,rest.getMinutes());

		System.out.println(new Timestamp(cal.getTimeInMillis()).toString());



		// somente um teste...
		if (now.after(new Timestamp(cal.getTimeInMillis())))
			return true;

		double diff_res = new Double(now.getTime()).doubleValue()/new Double(cal.getTimeInMillis()).doubleValue();

		System.out.println(diff_res);

		//System.out.println(new Timestamp(diff_millis).toString());


		return false;
	}

	/*
	 * avalia o cenario onde a tarefa nao foi concluida no tempo limite
	 */
	private static boolean eval1(Timestamp now,Timestamp sched,Time rest) {

		long sched_millis = sched.getTime();
		long rest_millis = rest.getTime();


		Timestamp sched_plus_rest = new Timestamp(sched_millis + rest_millis);

		if (now.after(sched_plus_rest))
			return true;

		return false;
	}


	/*
	 * envia e-mail de notificacao com o subject em questao
	 */
	private static void notify(Integer work_id,String work_name,Integer cod_usuario,String subject) {

		ADGoogleMailer mailer = new ADGoogleMailer();

		//try {

			//mailer.send();
			System.out.println("ZXCC MAILER : " + work_id + " | " + work_name + " | " + cod_usuario);

/*		} catch (MessagingException ex) {

			ex.printStackTrace();
		}*/

	}
}
