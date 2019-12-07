import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UserInterface extends JFrame {

	private String textToShow;
	private JTextArea tx;
	
	public void setUpMenu() {
		
		
		JMenuBar mbar = new JMenuBar();
		JMenu menuFile = new JMenu("File"); //exit
		JMenu menuHelp = new JMenu("Help"); //about
		
		JMenuItem mbarExit = new JMenuItem("Exit");
		mbarExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JMenuItem mbarAbout = new JMenuItem("About");
		mbarAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Made by Nick Spirakis and Adrian Perez");
			}
		});
		menuFile.add(mbarExit);
		mbar.add(menuFile);
		
		menuHelp.add(mbarAbout);
		mbar.add(menuHelp);
		
		setJMenuBar(mbar);
	}
	public void setupUI() {
		textToShow = "";
		setTitle("Top Websites Visted Monthly");
		setBounds(500, 500, 800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panNorth = new JPanel();
		
		Font f = new Font("Arial",Font.PLAIN,25);
		
		JLabel label = new JLabel("Enter URL");
		label.setFont(f);
		label.setBounds(50, 50, 200, 200);
		JTextField txtToFetch = new JTextField(20);
		txtToFetch.setBounds(50, 50, 200, 200);
		txtToFetch.setFont(f);
		JButton btnTxtToFetch = new JButton("Fetch");
		btnTxtToFetch.setFont(f);
		btnTxtToFetch.setBounds(50, 50, 200, 200);
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
		
		JPanel panSouth = new JPanel();
		
		JButton btnSaveToText = new JButton("Save to text");
		btnSaveToText.setBounds(150, 150, 300, 300);
		btnSaveToText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save to text
				
			}
		});
		
		JButton btnSaveToJson = new JButton("Save to json");
		btnSaveToJson.setBounds(150, 150, 300, 300);	//this should change size but on this one i don't think it does
		btnSaveToText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save to json
				
			}
		});
		panSouth.add(btnSaveToText);
		panSouth.add(btnSaveToJson);
		
		c.add(panSouth, BorderLayout.SOUTH);
		
		setUpMenu();
		
	}
	
	public UserInterface() {
		setupUI();
	}
	
	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.setVisible(true);
}
}