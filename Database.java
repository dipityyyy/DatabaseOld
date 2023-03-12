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

public class Database extends JPanel implements ActionListener{

	private JFrame frame;
	private JTable table;
	private JButton homeBtn;
	
	//public Connection conn = SQLiteConnection.ConnectDb();
	//Connection conn = null;
	private SQLiteConnection sqliteConn = new SQLiteConnection();
	private Connection conn = sqliteConn.getConnection();
	PreparedStatement pst;
	ResultSet res;
	static DefaultTableModel model = new DefaultTableModel();
	private JTextField nameTxtField;
	private JTextField nameTxt;
	private boolean hasRan = false;
	private boolean hasName = false;
	private boolean hasAvail = false;
	private boolean hasExp = false;
	private JTextField availTxt;
	private Home homeUp = new Home();

	/**
	 * Launch the application.
	 */
	
	public void updateTable() {
		//conn = SQLiteConnection.ConnectDb();
		while (model.getRowCount()>0) //clears the JTable to refresh
        {
           model.removeRow(0);
        }
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
					res.close();
					pst.close();
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, exc);
				}
			}  */
			//DefaultTableModel model = (DefaultTableModel) table.getModel();
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
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Database window = new Database();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	} */

	/**
	 * Create the application.
	 */
	public Database() {
		initialize();
		//conn = SQLiteConnection.ConnectDb();
		
		Object col[] = {"First Name", "Last Name", "Grade", "Experience Level", "Email", "Volunteer Hours", "Days Available", "Reason for Gardening", "Executive"};
		model.setColumnIdentifiers(col);
		table.setModel(model);
		
		homeBtn = new JButton("Home");
		homeBtn.setBounds(392, 639, 105, 21);
		homeBtn.addActionListener(this);
		this.add(homeBtn);
		homeBtn.setBackground(new Color(255, 255, 255));
		
		/*
		homeBtn = new JButton("Home");
		homeBtn.setBounds(483, 632, 105, 21);
		homeBtn.addActionListener(this);
		this.add(homeBtn); */
		
		JLabel searchLabel = new JLabel("Search for ...");
		searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		searchLabel.setBounds(10, 169, 152, 35);
		this.add(searchLabel);
		
		/*nameTxtField = new JTextField();
		nameTxtField.setBounds(154, 174, 284, 27);
		frame.getContentPane().add(nameTxtField);
		nameTxtField.setColumns(10); */
		/*if (!nameTxtField.getText().isEmpty()) {
			hasName = true;
		} else {
			hasName = false;
		} */
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameLabel.setBounds(154, 159, 45, 13);
		this.add(nameLabel);
		
		JLabel availLabel = new JLabel("Availability");
		availLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		availLabel.setBounds(469, 159, 125, 13);
		this.add(availLabel);
		
		JLabel experienceLabel = new JLabel("Experience");
		experienceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		experienceLabel.setBounds(689, 159, 125, 13);
		this.add(experienceLabel);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(154, 182, 231, 19);
		add(nameTxt);
		nameTxt.setColumns(10);
		if (!nameTxt.getText().isEmpty()) {
			hasName = true;
		} else {
			hasName = false;
		}
		
		JComboBox expCBox = new JComboBox();
		expCBox.setModel(new DefaultComboBoxModel(new String[] {"", "Beginner", "Intermediate", "Advanced"}));
		expCBox.setBounds(689, 181, 178, 21);
		add(expCBox);
		
		availTxt = new JTextField();
		availTxt.setColumns(10);
		availTxt.setBounds(469, 182, 178, 19);
		add(availTxt);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(921, 181, 85, 21);
		this.add(searchBtn);
		searchBtn.setBackground(new Color(255, 255, 255));
		searchBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) { //nested if?
				String sqlSearch = "SELECT * FROM GardenerDB WHERE firstName = " + "'" + nameTxt.getText().toString()  + "'" + " OR level = " + "'" + expCBox.getSelectedItem() + "'" + " OR days = " + "'" + availTxt.getText().toString() + "';";
				System.out.println(sqlSearch);
				try {
					pst = conn.prepareStatement(sqlSearch);
					pst.execute();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1);
				}
				updateTable();

			}
		});
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBounds(559, 639, 105, 21);
		deleteBtn.setBackground(new Color(255, 255, 255));
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				String cell = table.getModel().getValueAt(row,0).toString();
				//System.out.println("cell: " + cell);
				String sql = "DELETE FROM GardenerDB WHERE firstName = '" + cell + "';";
				try {
					pst = conn.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					//updateTable();
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null,ex);
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
				homeUp.updateTable();
			}
		});
		
		add(deleteBtn); 
		
		
		
		
		/*
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//pst = conn.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					updateTable();
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null,ex);
				}
				int row = table.getSelectedRow();
				String cell = table.getModel().getValueAt(row,0).toString();
				String sql = "DELETE FROM GardenerDB WHERE id = " + cell;
				try {
					pst = conn.prepareStatement(sql);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
					//updateTable();
				}catch (Exception ex){
					JOptionPane.showMessageDialog(null,ex);
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
		}); */
	
		//btnDelete.setBackground(new Color(255, 255, 255));
		//btnDelete.setBounds(559, 639, 105, 21);
		//this.add(btnDelete);
		
		updateTable();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame = new JFrame();
		this.setBackground(new Color(230, 230, 250));
		this.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(179, 203, 185));
		panel.setBounds(0, 0, 1086, 149);
		this.add(panel);
		
		JLabel lblNewLabel = new JLabel("Gardening Club");
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 72));
		panel.add(lblNewLabel);
		
		JPanel tableP = new JPanel();
		tableP.setBackground(new Color(230, 230, 250));
		tableP.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		tableP.setBounds(10, 214, 1044, 415);
		this.add(tableP);
		tableP.setLayout(null); 
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1024, 395);
		tableP.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Reason", "Executive", "Days Available", "Volunteer Hours", "Email", "Experience Level", "Grade", "Last Name", "First Name", "ID"
			}
		));
		scrollPane.setViewportView(table);
		//frame.setBounds(0, 0, 1100, 700);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == homeBtn) {
			try {
				System.out.println("Click");
				CardLayouts.cardL.previous(CardLayouts.cont);
				//StoreInfo addInfo = new StoreInfo();
				//AddGardener addG = new AddGardener(); // replace with database
			} catch (Exception ex) {

			}
		}
	}
}
