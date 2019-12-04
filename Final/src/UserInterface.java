import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserInterface extends JFrame {

	private String textToShow;
	private JTextArea tx;
	
	public void setupUI() {
		textToShow = "";
		setTitle("Top Websites Visted Monthly");
		setBounds(400, 400, 1500, 1500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panNorth = new JPanel();
		
		JLabel label = new JLabel("Enter URL");
		JTextField txtToFetch = new JTextField(50);
		JButton btnTxtToFetch = new JButton("Fetch");
		btnTxtToFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = txtToFetch.getText();
				textToShow = textToShow + "\n" + text;
				tx.setText(textToShow);
			}
		});
		
		panNorth.add(label);
		panNorth.add(txtToFetch);
		panNorth.add(btnTxtToFetch);
		
		c.add(panNorth, BorderLayout.NORTH);
		
	}
	
	public UserInterface() {
		setupUI();
	}
	
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.setVisible(true);
}
}