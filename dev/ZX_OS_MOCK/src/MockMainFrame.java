import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class MockMainFrame extends JFrame {

	public static String[] WORK_LIST1 = {"W1 LEAF1", "W1 LEAF2", "W1 LEAF3" , "W1 LEAF4" , "W1 LEAF5"};
	public static String[] WORK_LIST2 = {"W2 LEAF1", "W2 LEAF2", "W2 LEAF3"};
	public static String[] WORK_LIST3 = {"W3 LEAF1", "W3 LEAF2"};
		
	
	JTabbedPane tabbed_;
	JPanel topPanel_;
	
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	
	
	public MockMainFrame() {
		
				
		topPanel_ = new JPanel();
		topPanel_.setLayout(new BorderLayout());
		getContentPane().add(topPanel_);
				
		createWork1();
		createWork2();
		createWork3();
		
		tabbed_ = new JTabbedPane();
		tabbed_.addTab("WORK 1", panel1);
		tabbed_.addTab("WORK_2", panel2);
		tabbed_.addTab("WORK 3", panel3);
		tabbed_.setSelectedIndex(0);

		tabbed_.setPreferredSize(getMaximumSize());
		
		topPanel_.add( tabbed_, BorderLayout.CENTER );
		
	}
	
	public void createWork1() {
		
		panel1 = new JPanel();
		panel1.setLayout(null);					

		// WORK ID
		JLabel workIDLabel = new JLabel("Work ID : 1");
		workIDLabel.setBounds(10,15,150,20);
		panel1.add(workIDLabel);			

		// NEXT WORK ID
		JLabel nextWorkIDLabel = new JLabel("Next Work ID : 2");
		nextWorkIDLabel.setBounds(150,15,150,20);
		panel1.add(nextWorkIDLabel);		
	
		// RESP
		JLabel respLabel = new JLabel("ID do Resp. : ");
		respLabel.setBounds(10,75,150,20);
		JComboBox<String> respIDCombo = new JComboBox<String>(new String[] {"Paulo","João","Marcos","Rafael","Rodrigo"});		
		respIDCombo.setBounds(195,75,200,20);
		panel1.add(respLabel);
		panel1.add(respIDCombo);			
		
		// TIME CONSTRAINT
		JLabel timeConstraintLabel = new JLabel("Work TIMEOUT (m) : ");
		timeConstraintLabel.setBounds(10,95,150,20);
		JComboBox<String> timeConstraintCombo = new JComboBox<String>(new String[] {"1","2","3","4","5"});
		timeConstraintCombo.setBounds(175,95,20,20);
		panel1.add(timeConstraintLabel);
		panel1.add(timeConstraintCombo);
		
		// WORK LEAFs STATUS :
		JLabel workLeafsLabel = new JLabel("Work Leafs STATUS : ");
		workLeafsLabel.setBounds(10,125,150,20);
		panel1.add(workLeafsLabel);
		
		for (int i=0;i < WORK_LIST1.length;i++) {

			JCheckBox box = new JCheckBox(WORK_LIST1[i]);
			box.setBounds( 10, 155 + 20*i, 150, 20 );
			panel1.add( box );
			
		}
		
		

	}

	
	public void createWork2() {
		
		panel2 = new JPanel();
		panel2.setLayout(null);					

		// WORK ID
		JLabel workIDLabel = new JLabel("Work ID : 2");
		workIDLabel.setBounds(10,15,150,20);
		panel2.add(workIDLabel);

		// NEXT WORK ID
		JLabel nextWorkIDLabel = new JLabel("Next Work ID : 3");
		nextWorkIDLabel.setBounds(150,15,150,20);
		panel2.add(nextWorkIDLabel);		
		
		// RESP
		JLabel respLabel = new JLabel("ID do Resp. : ");
		respLabel.setBounds(10,75,150,20);
		JComboBox<String> respIDCombo = new JComboBox<String>(new String[] {"Paulo","João","Marcos","Rafael","Rodrigo"});
		respIDCombo.setBounds(195,75,200,20);		
		panel2.add(respLabel);
		panel2.add(respIDCombo);
		
		// TIME CONSTRAINT
		JLabel timeConstraintLabel = new JLabel("Work TIMEOUT (m) : ");
		timeConstraintLabel.setBounds(10,95,150,20);
		JComboBox<String> timeConstraintCombo = new JComboBox<String>(new String[] {"1","2","3","4","5"});
		timeConstraintCombo.setBounds(175,95,20,20);
		panel2.add(timeConstraintLabel);
		panel2.add(timeConstraintCombo);
		
		// WORK LEAFs STATUS :
		JLabel workLeafsLabel = new JLabel("Work Leafs STATUS : ");
		workLeafsLabel.setBounds(10,125,150,20);
		panel2.add(workLeafsLabel);
		
		for (int i=0;i < WORK_LIST2.length;i++) {

			JCheckBox box = new JCheckBox(WORK_LIST2[i]);
			box.setBounds( 10, 155 + 20*i, 150, 20 );
			panel2.add( box );
			
		}
		
		

	}
	
	public void createWork3() {
		
		panel3 = new JPanel();
		panel3.setLayout(null);					

		// WORK ID
		JLabel workIDLabel = new JLabel("Work ID : 3");
		workIDLabel.setBounds(10,15,150,20);
		panel3.add(workIDLabel);

		
		// RESP
		JLabel respLabel = new JLabel("ID do Resp. : ");
		respLabel.setBounds(10,75,150,20);
		JComboBox<String> respIDCombo = new JComboBox<String>(new String[] {"mvera","fabio","rafael"});
		respIDCombo.setBounds(195,75,200,20);		
		panel3.add(respLabel);
		panel3.add(respIDCombo);		
		
		
		// TIME CONSTRAINT
		JLabel timeConstraintLabel = new JLabel("Work TIMEOUT (m) : ");
		timeConstraintLabel.setBounds(10,95,150,20);
		JComboBox<String> timeConstraintCombo = new JComboBox<String>(new String[] {"1","2","3","4","5"});
		timeConstraintCombo.setBounds(175,95,20,20);
		panel3.add(timeConstraintLabel);
		panel3.add(timeConstraintCombo);

		
		// WORK LEAFs STATUS :
		JLabel workLeafsLabel = new JLabel("Work Leafs STATUS : ");
		panel3.add(workLeafsLabel);			
		workLeafsLabel.setBounds(10,125,150,20);
		
		for (int i=0;i < WORK_LIST3.length;i++) {

			JCheckBox box = new JCheckBox(WORK_LIST3[i]);
			box.setBounds( 10, 155 + 20*i, 150, 20 );
			panel3.add( box );
			
		}
		
		

	}
	
	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
									
				MockMainFrame frame = new MockMainFrame();				
								
				frame.setVisible(true);
				frame.pack();
				
			}
		});					
	}

}
