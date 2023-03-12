import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Home extends JPanel implements ActionListener {
	private Header headerP;
	private JTable table;
	// private JTabbedPane homeTabbedPane = new JTabbedPane();
	// private StoreInfo addInfo;
	private JButton addGardener;
	private JButton viewDatabase;
	private String fName;
	//private Database dataB = new Database();
	static DefaultTableModel model = new DefaultTableModel();
	private SQLiteConnection sqliteConn = new SQLiteConnection();
	private Connection conn = sqliteConn.getConnection();
	PreparedStatement pst;
	ResultSet res;
	
	private GdnrInfoF gndrInfoF;

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
				Object[] columnData = new Object[2];
				
				while (res.next()) {
					//columnData[0] = res.getString("ID");
					columnData[0] = res.getString("firstName");
					columnData[1] = res.getString("lastName");
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
	
	public Home() {
		this.setLayout(new BorderLayout());
		//this.setLayout(null);
		this.setBackground(Color.WHITE);
		addGardener = new JButton("Add");

		Panel gndrPanel = new Panel(); // panel on the left
		Panel homePanel = new Panel(); // panel on the right

		gndrPanel.setLayout(null);
		//gndrPanel.setLayout(new GridLayout(10, 1, 5, 5));
		//gndrPanel.setBackground(Color.BLUE);

		homePanel.setLayout(new FlowLayout());
		homePanel.setBackground(Color.decode("#D3D3D3"));
		//gndrPanel.setLayout(new GridLayout(10, 1, 300, 300));

		this.add(gndrPanel, BorderLayout.WEST);
		this.add(homePanel, BorderLayout.EAST);
		gndrPanel.setPreferredSize(new Dimension(700, 300));
		homePanel.setPreferredSize(new Dimension(400, 300));

		//JLabel greeting = new JLabel("Welcome!", SwingConstants.CENTER);
		//greeting.setBorder(new LineBorder(Color.BLACK, 2));

		/*table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Last Name", "First Name", "ID"
				}
			));

		scrollPane.setViewportView(table); */
 
		/*Object col[] = {"First Name", "Last Name"};
		model.setColumnIdentifiers(col);
		table.setModel(model); */
		/*
		 * JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
		 * gndrPanel, homePanel); Dimension minimumSize = new Dimension(300, 700);
		 * gndrPanel.setMinimumSize(minimumSize); homePanel.setMinimumSize(minimumSize);
		 * //splitPanel.setBounds(0,0,300,700); splitPanel.setOneTouchExpandable(true);
		 * splitPanel.setDividerLocation(300); this.add(splitPanel);
		 * //this.getContentPane().add(splitPanel);
		 */

		headerP = new Header();
		this.add(headerP, BorderLayout.NORTH); // adds gardening club header
		
		//String[] colName = { "Gardeners" };
		//Object[][] data = new Object[10][1];
		//DefaultTableModel model = new DefaultTableModel(data, colName);
		//JTable table = new JTable(model);
		//JScrollPane scrollPane = new JScrollPane(table);
		
		addGardener.setBounds(325, 10, 60, 20);
		addGardener.addActionListener(this);
		gndrPanel.add(addGardener);
		
		JPanel tableP = new JPanel();
		tableP.setBounds(40, 40, 600, 500);
		gndrPanel.add(tableP);
		tableP.setLayout(null); 
		
		JScrollPane scrollPane = new JScrollPane();
		gndrPanel.add(new JScrollPane(table));
		scrollPane.setBounds(10, 10, 575, 500);
		tableP.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Last Name", "First Name", "ID"
			}
		)); 
		scrollPane.setViewportView(table);
		//model.addColumn("First Name");
		//model.addColumn("Last Name");
		
		Object col[] = {"First Name", "Last Name"};
		model.setColumnIdentifiers(col);
		table.setModel(model);

		table.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        // Get the row index at the point where the mouse was clicked
		        int rowIndex = table.rowAtPoint(e.getPoint());
		        System.out.println(table.getValueAt(rowIndex, 0).toString());
		        fName = table.getValueAt(rowIndex, 0).toString();
		        //gndrInfoF = new GdnrInfoF(fName);
		        gndrInfoF = new GdnrInfoF();
		    }
		});


		
		//view database button
		viewDatabase = new JButton("Database");
		viewDatabase.addActionListener(this);
		homePanel.add(viewDatabase);
		
		updateTable();
		
	}
	
	public String getFName() {
		return fName;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == addGardener) {
			try {
				//StoreInfo addInfo = new StoreInfo();
				AddGdnr addG = new AddGdnr();
			} catch (Exception ex) {

			}
		}
		if (e.getSource() == viewDatabase) {
			try {
				CardLayouts.cardL.next(CardLayouts.cont);
				//StoreInfo addInfo = new StoreInfo();
				//AddGardener addG = new AddGardener(); // replace with database
			} catch (Exception ex) {

			}
		}
	}
}
