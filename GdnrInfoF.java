import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class GdnrInfoF extends JFrame {

	private JPanel gdnrPane;
	private JTextField fNameTxt;
	private JTextField lNameTxt;
	private JTextField textField_2;
	private JTextField textField_3;
	private Home homeData;
	private String name;
	private String lastName;
	private int grade;
	private String level;
	private String email;
	private Double hours;
	private String days;
	private String reason;
	private boolean exec;
	
	static DefaultTableModel model = new DefaultTableModel();
	private SQLiteConnection sqliteConn = new SQLiteConnection();
	private Connection conn = sqliteConn.getConnection();
	PreparedStatement pst;
	ResultSet resset;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GdnrInfoF frame = new GdnrInfoF(); //create new class for just info; call that to the frame
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//public GdnrInfoF(String firstName) {
	public GdnrInfoF() {
		homeData = new Home();
		name = homeData.getFName();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0,0,800,560);
		gdnrPane = new JPanel();
		gdnrPane.setBackground(new Color(225, 255, 225));
		gdnrPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(gdnrPane);
		gdnrPane.setLayout(null);
		
		JLabel fNameLabel = new JLabel("First Name");
		fNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		fNameLabel.setBounds(22, 23, 124, 34);
		gdnrPane.add(fNameLabel);
		
		JLabel lNameLabel = new JLabel("Last Name");
		lNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lNameLabel.setBounds(236, 23, 117, 34);
		gdnrPane.add(lNameLabel);
		
		fNameTxt = new JTextField();
		fNameTxt.setColumns(10);
		fNameTxt.setBounds(22, 60, 170, 19);
		gdnrPane.add(fNameTxt);
		//String sqlFName = "SELECT * FROM GardenerDB WHERE firstName = '" + name + "';";
		String sqlFName = "SELECT * FROM GardenerDB WHERE firstName = '" + name + "';";
		try {
			pst = conn.prepareStatement(sqlFName);
			resset = pst.executeQuery();
			System.out.println("Name found: " + name);
			lastName = resset.getString("lastName");
			grade = resset.getInt("grade");
			level = resset.getString("level");
			email = resset.getString("email");
			hours = resset.getDouble("hours");
			days = resset.getString("days");
			reason = resset.getString("reason");
			exec = resset.getBoolean("executive");
			
			System.out.println(lastName);
			System.out.println(grade);
			System.out.println(level);
			System.out.println(email);
			System.out.println(hours);
			System.out.println(days);
			System.out.println(reason);
			System.out.println(exec);

			//updateTable();
		}catch (Exception ex){
			JOptionPane.showMessageDialog(null,ex);
			System.out.println(ex);
		}
		fNameTxt.setText(name);
		
		lNameTxt = new JTextField();
		lNameTxt.setColumns(10);
		lNameTxt.setBounds(236, 60, 170, 19);
		gdnrPane.add(lNameTxt);
		
		JLabel daysLabel = new JLabel("Days Available");
		daysLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		daysLabel.setBounds(22, 130, 146, 34);
		gdnrPane.add(daysLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		emailLabel.setBounds(22, 430, 146, 34);
		gdnrPane.add(emailLabel);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(22, 464, 179, 19);
		gdnrPane.add(textField_2);
		
		JLabel expLabel = new JLabel("Experience Level");
		expLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		expLabel.setBounds(307, 141, 179, 34);
		gdnrPane.add(expLabel);
		
		JComboBox cboxLevel = new JComboBox();
		cboxLevel.setModel(new DefaultComboBoxModel(new String[] {"", "Beginner", "Intemediate", "Advanced"}));
		cboxLevel.setBounds(307, 178, 155, 21);
		gdnrPane.add(cboxLevel);
		
		JLabel execLabel = new JLabel("Executive?");
		execLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		execLabel.setBounds(307, 269, 103, 34);
		gdnrPane.add(execLabel);
		
		JCheckBox execChkBx = new JCheckBox("");
		execChkBx.setBounds(416, 279, 27, 21);
		gdnrPane.add(execChkBx);
		
		JLabel gradeLabel = new JLabel("Grade");
		gradeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		gradeLabel.setBounds(307, 344, 146, 34);
		gdnrPane.add(gradeLabel);
		
		JComboBox cboxGrade = new JComboBox();
		cboxGrade.setModel(new DefaultComboBoxModel(new String[] {"", "9", "10", "11", "12"}));
		cboxGrade.setBounds(307, 376, 128, 21);
		gdnrPane.add(cboxGrade);
		
		JLabel hoursLabel = new JLabel("Volunteer Hours");
		hoursLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hoursLabel.setBounds(307, 430, 146, 34);
		gdnrPane.add(hoursLabel);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(307, 464, 133, 19);
		gdnrPane.add(textField_3);
		
		JLabel reasonLabel = new JLabel("Reasoning");
		reasonLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reasonLabel.setBounds(534, 23, 103, 34);
		gdnrPane.add(reasonLabel);
		
		JTextArea reasonTxtArea = new JTextArea();
		reasonTxtArea.setBounds(534, 76, 215, 276);
		gdnrPane.add(reasonTxtArea);
		
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false); //sets the frame invisible
				dispose(); //destroy the JFrame object
			}
		});
		closeBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		closeBtn.setBounds(534, 482, 103, 31);
		gdnrPane.add(closeBtn);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		updateBtn.setBounds(647, 482, 103, 31);
		gdnrPane.add(updateBtn);
	}

}
