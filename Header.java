import javax.swing.*;
import java.awt.*;

public class Header extends JPanel {
	private JLabel name;
	private JButton goDatabase;

	public Header() {
		this.setBackground(Color.decode("#B3CBB9"));
		this.setLocation(0, 0);
		// this.setSize(1100, 140);
		name = new JLabel("Gardening Club");
		name.setFont(new Font("Monospaced", Font.BOLD, 80));
		name.setHorizontalAlignment(JLabel.CENTER);
		this.add(name);

		goDatabase = new JButton();
		// ImageIcon magnifyingG = new ImageIcon("images/Magnifying_Glass.png"); //
		// creates the image icon
		// goDatabase.setIcon(magnifyingG);
		goDatabase.setOpaque(false); // makes button transparent
		goDatabase.setContentAreaFilled(false); // removes content area
		goDatabase.setBorderPainted(false); // removes border
		this.add(goDatabase);

	}

}
