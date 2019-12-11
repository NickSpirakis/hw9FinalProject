import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

/**
 * setUpMenu function is 
 * */


public class UserInterface extends JFrame {

	
	
	//private String textToShow;
	private JTextArea tx;
	ArrayList<Model> textToShow;
	
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
		//ArrayList<Model> textToShow;
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
		
		tx = new JTextArea();
		JScrollPane scroll = new JScrollPane(tx, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.getVerticalScrollBar().setValue(0);
		c.add(scroll); //adding scrollbar and text area at same time
		
		//c.add(tx, BorderLayout.CENTER); this was old code without scroll
	
		btnTxtToFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//tx = new JTextArea();
				String text = txtToFetch.getText();
				ScannerWeb.scraper(text);
				String str ="";
				textToShow = ScannerWeb.getList();
				for (int i = 0; i < textToShow.size(); i++) {
					
					str = str + String.format("%s%25s %s", textToShow.get(i).getRank(), textToShow.get(i).getSite(), textToShow.get(i).getPeople() );
					str = str + "\n";
					tx.setText(str);
				}
				
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
				try {
					JFileChooser saveText = new JFileChooser("c:\\temp\\");
					if(saveText.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						
						if (Writer.toTextFile(saveText.getSelectedFile(), ScannerWeb.getList())) {
							JOptionPane.showMessageDialog(null, "File Svaed");
						} else {
							JOptionPane.showMessageDialog(null, "File not saved");
						}
					}
				}catch(Exception ex) {
					System.out.println("Could not save the file");
				}
			}
		});
		
		JButton btnSaveToJson = new JButton("Save to json");
		btnSaveToJson.setBounds(150, 150, 300, 300);	//this should change size but on this one i don't think it does
		btnSaveToJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save to json
				try {
					JFileChooser saveJson = new JFileChooser(new File("c:\\temp\\"));
					if(saveJson.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
						if (Writer.writeMembersToJSON(saveJson.getSelectedFile(), ScannerWeb.getList())) {
							JOptionPane.showMessageDialog(null, "File Svaed");
						} else {
							JOptionPane.showMessageDialog(null, "File not saved");
						}
					}
				}catch(Exception ex) {
					System.out.println("Could not save the file");
				}
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
		ui.setLocationRelativeTo(null);
		
		ui.setVisible(true);
}
}