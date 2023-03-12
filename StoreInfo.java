import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StoreInfo extends JFrame {

	private JFrame gAdd;
	private String name;
	private JList gradeList;
	private static int[] grade = { 9, 10, 11, 12 };
	private JList levelList;
	private static String[] level = { "Beginner", "Intermediate", "Advanced" };
	private String email;
	private int volHours;
	private JList daysList;
	private JList testList;
	private static String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };
	private String reason;
	private JButton inputG; // flag for executive or not?

	public StoreInfo() {
		gAdd = new JFrame("Gardener Information");
		gAdd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gAdd.setVisible(true);
		gAdd.setSize(500, 400);
		gAdd.setLayout(new FlowLayout());
		gAdd.setResizable(true);

		daysList = new JList(days);
		daysList.setVisibleRowCount(1);
		daysList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(daysList));

		inputG = new JButton("Add");
		gAdd.add(inputG);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == inputG) {
			try {
				testList.setListData(daysList.getSelectedValues());
			} catch (Exception ex) {
			}
		}
	}
}
