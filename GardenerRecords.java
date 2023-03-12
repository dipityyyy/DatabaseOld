import java.awt.EventQueue;


import javax.swing.JFrame;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class GardenerRecords {

	private JFrame frame;
	private JTextField jtxtfFirstName;
	private JTextField jtxtfLastName;
	private JTextField jtxtfEmail;
	private JTextField jtxtfHours;
	private JTable table;
	private JComboBox cboxGrade;
	private JComboBox cboxLevel;
	private JTextArea jtxtareaReason;
	private JCheckBox chckbxExec;
	private JList jListDays;
	
	//public Connection conn = SQLiteConnection.ConnectDb();
	//Connection conn = null;
	private SQLiteConnection sqliteConn = new SQLiteConnection();
	private Connection conn = sqliteConn.getConnection();
	PreparedStatement pst;
	ResultSet res;
	static DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	
	public void updateTable() {
		//conn = SQLiteConnection.ConnectDb();
		if(conn != null) {
			String sql = "SELECT * FROM GardenerDB";
			try {
				pst = conn.prepareStatement(sql);
				res = pst.executeQuery();
				Object[] columnData = new Object[9];
				
				while (res.next()) {
					//columnData[0] = res.getString("ID");
					columnData[0] = res.getString("firstName");
					columnData[1] = res.getString("lastName");
					columnData[2] = res.getInt("grade");
					columnData[3] = res.getString("level");
					columnData[4] = res.getString("email");
					columnData[5] = res.getDouble("hours");
					columnData[6] = res.getString("days");
 					columnData[7] = res.getString("reason");
					columnData[8] = res.getBoolean("executive");
					model.addRow(columnData);
				}
				/* res.close();
				pst.close(); */
				//conn.close();
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null, e);
				e.printStackTrace();
			}
			/*finally {
				try {
					//res.close();
					pst.close();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc);
				}
			} */
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] {
					//54:01
			});
			if (table.getSelectedRow() == -1) {
				if (table.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Update Confirmed", "Gardening Database", JOptionPane.OK_OPTION);
				}
			}
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GardenerRecords window = new GardenerRecords();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GardenerRecords() {
		initialize();
		//conn = SQLiteConnection.ConnectDb();
		
		Object col[] = {"First Name", "Last Name", "Grade", "Experience Level", "Email", "Volunteer Hours", "Days Available", "Reason for Gardening", "Executive"};
		model.setColumnIdentifiers(col);
		table.setModel(model);
		
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(230, 230, 250));
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(179, 203, 185));
		panel.setBounds(0, 0, 1086, 149);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("Gardening Club");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 72));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_1.setBounds(10, 159, 608, 245);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel jtxtFirst = new JLabel("First Name");
		jtxtFirst.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtFirst.setBounds(10, 10, 169, 13);
		panel_1.add(jtxtFirst);
		
		JLabel jtxtGrade = new JLabel("Grade");
		jtxtGrade.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtGrade.setBounds(10, 53, 169, 13);
		panel_1.add(jtxtGrade);
		
		JLabel jtxtEmail = new JLabel("Email");
		jtxtEmail.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtEmail.setBounds(10, 97, 70, 13);
		panel_1.add(jtxtEmail);
		
		JLabel jtxtLevel = new JLabel("Experience Level");
		jtxtLevel.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtLevel.setBounds(189, 53, 192, 13);
		panel_1.add(jtxtLevel);
		
		JLabel jtxtReason = new JLabel("Reasoning");
		jtxtReason.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtReason.setBounds(475, 29, 108, 13);
		panel_1.add(jtxtReason);
		
		JLabel jtxtDays = new JLabel("Days Available");
		jtxtDays.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtDays.setBounds(10, 206, 169, 13);
		panel_1.add(jtxtDays);
		
		JLabel jtxtHours = new JLabel("Volunteer Hours");
		jtxtHours.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtHours.setBounds(10, 133, 192, 13);
		panel_1.add(jtxtHours);
		
		JLabel jtxtExecutive = new JLabel("Executive");
		jtxtExecutive.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtExecutive.setBounds(10, 168, 117, 13);
		panel_1.add(jtxtExecutive);
		
		JLabel jtxtLast = new JLabel("Last Name");
		jtxtLast.setFont(new Font("Monospaced", Font.BOLD, 20));
		jtxtLast.setBounds(178, 10, 169, 13);
		panel_1.add(jtxtLast);
		
		jtxtfFirstName = new JTextField();
		jtxtfFirstName.setBounds(10, 33, 131, 19);
		panel_1.add(jtxtfFirstName);
		jtxtfFirstName.setColumns(10);
		
		jtxtfLastName = new JTextField();
		jtxtfLastName.setColumns(10);
		jtxtfLastName.setBounds(172, 33, 131, 19);
		panel_1.add(jtxtfLastName);
		
		cboxLevel = new JComboBox();
		cboxLevel.setModel(new DefaultComboBoxModel(new String[] {"", "Beginner", "Intermediate", "Advanced"}));
		cboxLevel.setBounds(226, 73, 155, 21);
		panel_1.add(cboxLevel);
		
		cboxGrade = new JComboBox();
		cboxGrade.setModel(new DefaultComboBoxModel(new String[] {"", "9", "10", "11", "12"}));
		cboxGrade.setBounds(10, 76, 128, 21);
		panel_1.add(cboxGrade);
		
		jtxtfEmail = new JTextField();
		jtxtfEmail.setBounds(83, 98, 179, 19);
		panel_1.add(jtxtfEmail);
		jtxtfEmail.setColumns(10);
		
		jtxtfHours = new JTextField();
		jtxtfHours.setBounds(207, 134, 96, 19);
		panel_1.add(jtxtfHours);
		jtxtfHours.setColumns(10);
		
		chckbxExec = new JCheckBox("");
		chckbxExec.setBounds(142, 168, 93, 21);
		panel_1.add(chckbxExec);
		
		jtxtareaReason = new JTextArea();
		jtxtareaReason.setBounds(467, 53, 131, 174);
		panel_1.add(jtxtareaReason);
		
		jListDays = new JList();
		jListDays.setValueIsAdjusting(true);
		jListDays.setVisibleRowCount(1);
		jListDays.setModel(new AbstractListModel() {
			String[] values = new String[] {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		jListDays.setBounds(226, 163, 149, 82);
		panel_1.add(jListDays);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setBackground(new Color(230, 230, 250));
		panel_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		panel_1_2.setBounds(10, 414, 1044, 215);
		frame.getContentPane().add(panel_1_2);
		panel_1_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1024, 195);
		panel_1_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Reason", "Executive", "Days Available", "Volunteer Hours", "Email", "Experience Level", "Grade", "Last Name", "First Name", "ID"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.setBackground(new Color(128, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sqlInsert = "INSERT INTO GardenerDB(firstName, lastName, grade, level, email, hours, days, reason, executive) VALUES(?,?,?,?,?,?,?,?,?)";
				//String sqlInsert = "INSERT INTO GardenerDB(ID, firstName, lastName, grade, level, email, hours, days, reason, executive) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				try {
					pst = conn.prepareStatement(sqlInsert);
					pst.setString(1, jtxtfFirstName.getText());
					pst.setString(2, jtxtfLastName.getText());
					pst.setString(3, (String)cboxGrade.getSelectedItem());
					pst.setString(4, (String) cboxLevel.getSelectedItem());
					pst.setString(5, jtxtfEmail.getText());
					pst.setString(6, jtxtfHours.getText());
					pst.setString(7, jListDays.getSelectedValue().toString());
					pst.setString(8, jtxtareaReason.getText());
					if (chckbxExec.isSelected()) {
					pst.setString(9, "1");
					} else { 
						pst.setString(9, "0");} 
					pst.executeUpdate();
					System.out.println("HERE");
					updateTable();
					//model = (DefaultTableModel) table.getModel();
					/*
					res.close();
					pst.close(); */
					//conn.close();
				}
				catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
					System.out.println(ex);
					ex.printStackTrace();
				}
					/*finally {
						try {
							res.close();
							pst.close();
						} catch (Exception exc) {
							JOptionPane.showMessageDialog(null, e);
						}
					} */
				}
				/*catch (Exception ex) {
					//JOptionPane.showMessageDialog(null, e);
					System.out.println(ex);
					ex.printStackTrace();
				}  */
			}
		//});
		);
		btnNewButton.setBounds(20, 632, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String cell = table.getModel().getValueAt(row,0).toString();
				System.out.println("cell: " + cell);
				String sql = "DELETE FROM GardenerDB WHERE firstName = " + cell;
				try {
					pst = conn.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					//updateTable();
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null,ex);
					System.out.println(ex);
				}
				model = (DefaultTableModel) table.getModel();
				if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "No data to delete ", "Gardening Database", JOptionPane.OK_OPTION);
					} else {
						JOptionPane.showMessageDialog(null, "Select a row to delete ", "Gardening Database", JOptionPane.OK_OPTION);
					}
				} else {
					model.removeRow(table.getSelectedRow());
				}
			}
		});
		btnDelete.setBackground(new Color(128, 0, 0));
		btnDelete.setBounds(137, 632, 85, 21);
		frame.getContentPane().add(btnDelete);
		frame.setBounds(0, 0, 1100, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
