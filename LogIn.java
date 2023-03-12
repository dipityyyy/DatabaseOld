import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;

public class LogIn extends JPanel implements ActionListener {

	private JLabel username;
	private JTextField enterName;
	private JLabel password;
	private JPasswordField enterPass;
	private JButton goHome;
	private JButton backSignUp;
	private Header headerP;
	private SignUp signUpInfo = new SignUp();

	public LogIn() {
		this.setLayout(new BorderLayout(8, 10));
		this.setBackground(Color.WHITE);

		JPanel centerP = new JPanel();
		centerP.setBackground(Color.WHITE);
		centerP.setLayout(new GridLayout(3, 2, 30, 30));
		this.add(centerP, BorderLayout.CENTER);

		if (signUpInfo.getSignedUp()) {
			CardLayouts.cardL.next(CardLayouts.cont);
			signUpInfo.setSignedUp(false);
		}
		
		goHome = new JButton("Sign In");
		goHome.setBounds(260, 500, 245, 75);
		goHome.addActionListener(this);
		backSignUp = new JButton("Sign Up");
		backSignUp.setBounds(560, 500, 245, 75);
		backSignUp.addActionListener(this);

		Font newButtonFont = new Font("Monospaced", Font.BOLD, 40);
		goHome.setFont(newButtonFont);
		backSignUp.setFont(newButtonFont);

		username = new JLabel("Username: ");
		// username.setBounds(10, 20, 80, 25);
		Font newFontSize = new Font(username.getFont().getName(), username.getFont().getStyle(), 40);
		username.setFont(newFontSize);
		centerP.add(username);
		enterName = new JTextField(20);
		enterName.setFont(newFontSize);
		// enterName.setBounds(485, 240, 380, 70);
		centerP.add(enterName);

		password = new JLabel("Password: ");
		password.setFont(newFontSize);
		// password.setBounds(10, 50, 80, 25);
		centerP.add(password);
		enterPass = new JPasswordField(50);
		enterPass.setFont(newFontSize);
		// enterPass.setBounds(485, 355, 380, 70);
		centerP.add(enterPass);

		centerP.add(goHome);
		centerP.add(backSignUp);

		headerP = new Header();
		this.add(headerP, BorderLayout.NORTH); // adds gardening club header

	}

	public void actionPerformed(ActionEvent e) {
		boolean isMatched = false;
		if (e.getSource() == goHome) {
			String givenName = signUpInfo.getUsername();
			String givenPass = signUpInfo.getPassword();
			try {
				FileReader fr = new FileReader("login.txt");
				BufferedReader br = new BufferedReader(fr);
				String checkLine;
				while ((checkLine = br.readLine()) != null) {
					String name = new String(enterName.getText());
					String password = new String(enterPass.getPassword());
					/*
					System.out.println("Name: " + name);
					System.out.println("Password: " + password);
					System.out.println("Line: " + checkLine);
					System.out.println(name + "\t" + password); */
					if (checkLine.equals(name + "\t" + password)) {
						isMatched = true;
						break;
					}
				}
				fr.close();
			} catch (Exception ex) {

			}
		}
		if (isMatched) {
			CardLayouts.cardL.next(CardLayouts.cont);
		} else if (e.getSource() == backSignUp) {
			CardLayouts.cardL.previous(CardLayouts.cont);
		} else {
			JFrame f = new JFrame();
			JOptionPane.showMessageDialog(f, "Invalid Username or Password");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setFont(new Font("Monospaced", Font.BOLD, 50));
		// g.drawString("Username: ", 190, 290);
		// g.drawString("Password: ", 190, 400);
	}
}
