package NI_GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

public class userGUI extends JFrame implements ActionListener {

	private Database db;
	private DatabaseModifier dmV;
	private DataAccesser daV;
	private Connection con = null;

	// for SQL
	String Community_Area_name = null;
	String Property_type = null;
	String Zip_Code = null;
	String Property_Name = null;
	String Management_Company = null;
	String Unit_range1 = null;
	String Unit_range2 = null;
	String Community_Area_Number = null;
	private int optValue = 0;

	private String endString = "Do you want to quit the Application?";
	private String helpString = "If user want to login again, please click LOGIN button\n"
			+ "1.Type your keyword into wright text box.\n"
			+ "2. Click the SEARCH button.\n"
			+ "3. Click the CLEAR button to start search again\n";

	private String infoString = "CS440 Neighborhood Informant";

	private MenuBar menuBar;
	private Menu helpMenu;
	private MenuItem info;
	private MenuItem how;
	private MenuItem quit;

	private JButton searchButton;
	private JButton clearButton;
	private JButton loginButton;

	private final JTextField areaNamet;
	private final JTextField pTypet;
	private final JTextField zipt;
	private final JTextField pNamet;
	private final JTextField companyt;
	private final JTextField unitRanget;
	private final JTextField unitRanget2;
	private final JTextField areaNumt;

	protected static JTextArea textArea;

	// private InfoDisplay disp;
	// textarea for displaying the info

	public userGUI() {

		// set Frame
		super("Neighborhood Informant");
		setBounds(400, 200, 1200, 900);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				int confirm = JOptionPane.showOptionDialog(null,
						"Are You Sure to Close Application?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					delectConnect();
					System.exit(0);
				}
			}
		});
		// setVisible(true);

		// MenuBar
		menuBar = new MenuBar();
		helpMenu = new Menu("HELP");
		info = new MenuItem("INFO");
		how = new MenuItem("HowToUse");
		quit = new MenuItem("Quit");

		helpMenu.add(info);
		helpMenu.add(how);
		helpMenu.add(quit);

		menuBar.add(helpMenu);
		setMenuBar(menuBar);

		Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
		Container mainPanel = this.getContentPane();
		JPanel searchPanel = new JPanel();
		JPanel buttonPanel = new JPanel();

		JLabel areaName = new JLabel("Commnuity Area Name (ex.Albany Park)",
				SwingConstants.LEFT);// Community Area Name
		areaNamet = new JTextField(20);
		areaNamet.addActionListener(this);

		JLabel pType = new JLabel("Property Type (ex.Senior)",
				SwingConstants.LEFT); // Property Type
		pTypet = new JTextField(20);
		pTypet.addActionListener(this);
		
		JLabel zipCode = new JLabel("Zip Code (ex.60608)", SwingConstants.LEFT); // Zipcode
		zipt = new JTextField(20);
		zipt.addActionListener(this);

		JLabel pName = new JLabel("Property Name (ex.Mayfair Commons)",
				SwingConstants.LEFT); // Property name
		pNamet = new JTextField(20);
		pNamet.addActionListener(this);
		
		JLabel mCompany = new JLabel("Management Company (ex. The Thresholds)",
				SwingConstants.LEFT); // management company
		companyt = new JTextField(20);
		companyt.addActionListener(this);
		// UnitRange 
		JLabel unitRange = new JLabel("First Unit Range", SwingConstants.LEFT); // UnitRange
		unitRanget = new JTextField(20);
		unitRanget.addActionListener(this);
		
		JLabel unitRange2 = new JLabel("Second Unit Range", SwingConstants.LEFT); // UnitRange
		unitRanget2 = new JTextField(20);
		unitRanget2.addActionListener(this);
		
		//area number
		JLabel areaNum = new JLabel("Community Area Number (ex. 71)",
				SwingConstants.LEFT);// commuity area number
		areaNumt = new JTextField(20);
		areaNumt.addActionListener(this);

		searchPanel.setLayout(new GridLayout(9, 1));
		// searchPanel.setLayout(new FlowLayout());
		searchPanel.add(areaName);
		searchPanel.add(areaNamet);
		searchPanel.add(pType);
		searchPanel.add(pTypet);
		searchPanel.add(zipCode);
		searchPanel.add(zipt);
		searchPanel.add(pName);
		searchPanel.add(pNamet);
		searchPanel.add(mCompany);
		searchPanel.add(companyt);
		searchPanel.add(unitRange);
		searchPanel.add(unitRanget);
		searchPanel.add(unitRange2);
		searchPanel.add(unitRanget2);
		searchPanel.add(areaNum);
		searchPanel.add(areaNumt);

		mainPanel.add(searchPanel);

		searchButton = new JButton("SEARCH");
		searchButton.addActionListener(this);
		clearButton = new JButton("CLEAR");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
			}
		});
		loginButton = new JButton("LOG-IN");
		loginButton.addActionListener(this);

		buttonPanel.add(searchButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(loginButton);

		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(buttonPanel);

		// TextArea
		textArea = new JTextArea(25, 90);
		JScrollPane scroll = new JScrollPane(textArea);

		Border textBorder = BorderFactory.createTitledBorder(border,
				"Information Board");
		scroll.setBorder(textBorder);
		textArea.setCaretPosition(textArea.getText().length());
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		mainPanel.add(scroll);

		DownloadCSV dCSV = new DownloadCSV();
		dCSV.DownloadPages(
				"https://data.cityofchicago.org/api/views/s6ha-ppgi/rows.csv?accessType=DOWNLOAD",
				"1.csv");
		// Build Database connection
		DatabaseModifier dbM = new DatabaseModifier();
		con = dbM.connectDB();
		dbM.creatTable(con);
		// Load the CSV file to the mySQL database
		LoadCSV ldCSV = new LoadCSV();
		db = new Database("/Users/zeli/Documents/workspace/440/1.csv");
		ldCSV.loadCSV(con,"/Users/zeli/Documents/workspace/440/1.csv");
		DataAccesser dbA = new DataAccesser(textArea);
		dmV = dbM;
		daV = dbA;
		// textArea.append("Here is the value.");

		info.addActionListener(this);
		how.addActionListener(this);
		quit.addActionListener(this);
		setVisible(true);

	}

	public JTextArea getDispArea() {
		return textArea;
	}

	public void delectConnect() {
		dmV.deleteTable(con);
	}

	public void setJTextVisable(boolean b) {
		areaNamet.setEnabled(b);
		pTypet.setEnabled(b);
		zipt.setEnabled(b);
		pNamet.setEnabled(b);
		companyt.setEnabled(b);
		unitRanget.setEnabled(b);
		unitRanget2.setEnabled(b);
		areaNumt.setEnabled(b);
	}

	public void clearSearchContent() {
		areaNamet.setText("");
		pTypet.setText("");
		zipt.setText("");
		pNamet.setText("");
		companyt.setText("");
		unitRanget.setText("");
		unitRanget2.setText("");
		areaNumt.setText("");
	}

	public void getInfoFromUser() throws IOException {
		Connection con = null;
		con = dmV.connectDB();
		switch (optValue) {
		case 1:
			daV.infoFromCmmName(db, Community_Area_name);
			break;
		case 2:
			this.setJTextVisable(false);
			pTypet.setEditable(true);
			daV.infoFromPtype(db, Property_type);
			break;
		case 3:
			this.setJTextVisable(false);
			zipt.setEnabled(true);
			daV.infoFromZip(db, Zip_Code);
			break;
		case 4:
			this.setJTextVisable(false);
			pNamet.setEnabled(true);
			daV.infoFromPname(db, Property_Name);
			break;
		case 5:
			this.setJTextVisable(false);
			companyt.setEnabled(true);
			daV.ManagementCompany(db, Management_Company);
			break;
		case 6:
			this.setJTextVisable(false);
			unitRanget.setEnabled(true);
			unitRanget2.setEnabled(true);
			int low,
			high;
			low = Integer.valueOf(Unit_range1);
			high = Integer.valueOf(Unit_range2);
			if (low > high) {
				int temp = low;
				low = high;
				high = temp;
			}
			daV.Units(db, low, high);
			optValue = 0;
			Unit_range1 = null;
			Unit_range2 = null;
			break;
		case 7:
			this.setJTextVisable(false);
			areaNumt.setEnabled(true);
			daV.CommunityAreaNumber(db, Integer.valueOf(Community_Area_Number));
			break;
		default:
			textArea.append("Please Type in the info and click Search.\n");
			break;

		}
	}

	public void actionPerformed(ActionEvent e) {
		int val;

		if (e.getSource() == info) {

			JOptionPane.showMessageDialog(this, infoString, "Information",
					JOptionPane.INFORMATION_MESSAGE);

		} else if (e.getSource() == how) {
			JOptionPane.showMessageDialog(this, helpString, "HOW TO USE",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (e.getSource() == quit) {

			val = JOptionPane.showConfirmDialog(this, endString,
					"Close Application", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (val == JOptionPane.YES_OPTION)
				delectConnect();
			System.exit(0);

		} else if (e.getSource() == loginButton) {
			val = JOptionPane.showConfirmDialog(this,
					"Log in another account? Current window will close.",
					"Information", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE);
			if (val == JOptionPane.YES_OPTION) {
				LoginWindow lw = new LoginWindow();
				this.delectConnect();
				this.setVisible(false);
				lw.getLoginWindow().setVisible(true);
			}
		} else if (e.getSource() == searchButton) {
			try {
				getInfoFromUser();
				this.setJTextVisable(true);
				this.clearSearchContent();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource() == areaNamet){
			Community_Area_name = areaNamet.getText();
			optValue = 1;
			setJTextVisable(false);
			areaNamet.setEnabled(true);
		}else if(e.getSource() == pTypet){
			Property_type = pTypet.getText();
			optValue = 2;
			setJTextVisable(false);
			pTypet.setEnabled(true);
		}else if(e.getSource() == zipt){
			Zip_Code = zipt.getText();
			optValue = 3;
			setJTextVisable(false);
			zipt.setEnabled(true);
		}else if(e.getSource() == pNamet){
			Property_Name = pNamet.getText();
			optValue = 4;
			setJTextVisable(false);
			pNamet.setEnabled(true);
		}else if(e.getSource() == companyt){
			Management_Company = companyt.getText();
			optValue = 5;
			setJTextVisable(false);
			companyt.setEnabled(true);
		}else if(e.getSource() == unitRanget){
			Unit_range1 = unitRanget.getText();
			textArea.append(" First Unit Range is : " + Unit_range1 + "\n");
			if (Unit_range2 == null) {
				setJTextVisable(false);
				unitRanget.setEnabled(true);
				unitRanget2.setEnabled(true);
				textArea.append("Please type in Second Unit Range!\n");
			} else if (Unit_range1 == null) {
				setJTextVisable(false);
				unitRanget.setEnabled(true);
				unitRanget2.setEnabled(true);
				textArea.append("Please type in First Unit Range!\n");
			} else {
				optValue = 6;
				setJTextVisable(false);
				unitRanget.setEnabled(true);
				unitRanget2.setEnabled(true);
			}
		}else if(e.getSource() == unitRanget2){
			Unit_range2 = unitRanget2.getText();
			// optValue = 6;
			textArea.append(" Second Unit Range is : " + Unit_range2 + "\n");
			if (Unit_range1 == null) {
				setJTextVisable(false);
				unitRanget.setEnabled(true);
				unitRanget2.setEnabled(true);
				textArea.append("Please type in First Unit Range!\n");
			} else if (Unit_range2 == null) {
				setJTextVisable(false);
				unitRanget.setEnabled(true);
				unitRanget2.setEnabled(true);
				textArea.append("Please type in Second Unit Range!\n");
			} else {
				optValue = 6;
				setJTextVisable(false);
				unitRanget.setEnabled(true);
				unitRanget2.setEnabled(true);
			}
		}else if(e.getSource() == areaNumt){
			Community_Area_Number = areaNumt.getText();
			optValue = 7;
			setJTextVisable(false);
			areaNumt.setEnabled(true);
		}
	}

	/*
	 * public static void main(String[] args){ userGUI gui = new userGUI(); }
	 */

}
