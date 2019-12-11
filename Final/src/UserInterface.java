import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

/**
 * this class creates the UI and also displays it. The main is in this class as well.
 * setUpMenu function is what displays and creates the jmenus for file and help options.
 * 
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
		/**
		 * adds menu option to menu
		 */
		menuFile.add(mbarExit);
		mbar.add(menuFile);
		
		menuHelp.add(mbarAbout);
		mbar.add(menuHelp);
		/**
		 * adds menu to display
		 */
		
		setJMenuBar(mbar);
	}
	
	/**
	 * setupUI is where components like the text field, button and label are made and placed.
	 * there is also a text area to display the formated data from our website. we had to read it in line by line because this method
	 * only allows for strings, not arrays.
	 */
	public void setupUI() {
		//ArrayList<Model> textToShow;
		setTitle("Top Websites Visted Monthly");
		setBounds(500, 500, 800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		JPanel panNorth = new JPanel();
		
		/**
		 * sets font and size, which i can use in anything if i do  setFont(f)
		 */
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
		/**
		 * this is the code to add a scrollbar because our list is long and we wanted to allow people to see the whole list in 
		 * the UI, not just in files
		 */
		tx = new JTextArea();
		JScrollPane scroll = new JScrollPane(tx, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.getVerticalScrollBar().setValue(0);
		c.add(scroll); //adding scrollbar and text area at same time
		
	/**
	 * this button action listener is to get the url from the text field
	 * here is the url again if needed: https://www.quantcast.com/top-sites/US?userView=Public
	 * we read in every line as a string which is why we need a for loop.
	 */
		btnTxtToFetch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		/**
		 * adds to UI
		 */
		panNorth.add(label);
		panNorth.add(txtToFetch);
		panNorth.add(btnTxtToFetch);
		
		c.add(panNorth, BorderLayout.NORTH);
		
		
		
		JPanel panSouth = new JPanel();
		/**
		 * this is the save to text file button, it uses a function made in writer class and 
		 * also calls open a save dialog box
		 */
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
		
		/**
		 * this is the save to json file button, it uses a function made in writer class and 
		 * also calls open a save dialog box, just like the save to text button does
		 */
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
		/**
		 * adds btns to UI
		 */
		panSouth.add(btnSaveToText);
		panSouth.add(btnSaveToJson);
		
		c.add(panSouth, BorderLayout.SOUTH);
		
		/**
		 * this calls the setUpMenu function to add the file and help Jmenus
		 */
		setUpMenu();
	
		
	}
	
	public UserInterface() {
		setupUI();
	}
	
	
	public static void main(String[] args) {
		/**
		 * this is the main class. 
		 * we create the UI, set its location to be centered in screen, and set it to be visible.
		 */
		UserInterface ui = new UserInterface();
		ui.setLocationRelativeTo(null);
		
		ui.setVisible(true);
}
}