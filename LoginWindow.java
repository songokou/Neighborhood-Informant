package NI_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class LoginWindow extends JFrame {
	
	static Connection connection = null;
	
	static LoginWindow frame = new LoginWindow();
	
	public LoginWindow getLoginWindow(){
		return frame;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Login = "Non";
	String Password = "Non";
	private JPanel contentPane;
	private JTextField LoginField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 * @throws SQLException 
	 */
	static
	MySqlLogin database = new MySqlLogin();
	
	public static void main(String[] args) throws SQLException {
		
		
//--------------------------------------------------------------------------------------------------------
		try {
			  Class.forName("com.mysql.jdbc.Driver");
			 } catch (ClassNotFoundException e) {
			  System.out.println(" Failed ");
			 
			 }
			 
			 System.out.println("MySQL JDBC Driver Registered!");
			 connection = null;
			 
			 try {
			  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cs440_login_ids","root", "root");
			 
			 } catch (SQLException e) {
			  System.out.println("Connection Failed! Check output console");
			  e.printStackTrace();
			 }
			 
			 if (connection != null) {
			  System.out.println("You are now connected to the database. ");
			 } else {
			  System.out.println("Failed to make connection!");
			 }
//--------------------------------------------------------------------------------------------------------
		
		
		
			 make();

		/*
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
	}
	
	public Connection send(){
		return connection;
		
	}
	
	public static void make(){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setAutoRequestFocus(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 488);
		contentPane = new JPanel();
		contentPane.setForeground(UIManager.getColor("ScrollBar.trackHighlightForeground"));
		contentPane.setBackground(UIManager.getColor("ScrollBar.track"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JTextPane WelcomeMessage = new JTextPane();
		WelcomeMessage.setEditable(false);
		WelcomeMessage.setBackground(UIManager.getColor("Separator.highlight"));
		WelcomeMessage.setForeground(Color.DARK_GRAY);
		WelcomeMessage.setFont(new Font("Verdana", Font.BOLD, 11));
		WelcomeMessage.setText("\tWelcome to Neighborhood Information Center");
		
		JPanel panel = new JPanel();
		panel.setForeground(UIManager.getColor("TextPane.caretForeground"));
		panel.setBorder(new LineBorder(UIManager.getColor("TextField.background")));
		panel.setBackground(UIManager.getColor("TextField.disabledBackground"));
		
		JTextPane LoginMessage = new JTextPane();
		LoginMessage.setEditable(false);
		LoginMessage.setText("Please Login to Continue");
		LoginMessage.setForeground(SystemColor.controlText);
		LoginMessage.setFont(new Font("Verdana", Font.BOLD, 11));
		LoginMessage.setBackground(UIManager.getColor("TextField.disabledBackground"));
		
		LoginField = new JTextField();
		LoginField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login = LoginField.getText();
				System.out.println("\n Login-ID is : "+Login);
			}
		});
		LoginField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LoginField.setText("");
				LoginField.setForeground(Color.BLACK);
			}
		});
		LoginField.setBackground(UIManager.getColor("TextField.background"));
		LoginField.setForeground(SystemColor.scrollbar);
		LoginField.setText("Login ID");
		LoginField.setColumns(10);
		
		JButton LoginButton = new JButton("Login");
		LoginButton.addActionListener(new ActionListener() {
			@SuppressWarnings({ })
			public void actionPerformed(ActionEvent arg0) {
				
				Login = LoginField.getText();
				System.out.println("\n Login-ID is : "+Login);
				
				Password = passwordField.getText();
				System.out.println("\n LOGIN BUTTON - Password is : "+Password); 
				
				//******************************************************************************
				// Set LoginField Back to Normal
				LoginField.setText("");
				LoginField.setBackground(UIManager.getColor("TextField.background"));
				LoginField.setForeground(SystemColor.scrollbar);
				LoginField.setText("Login ID");
				
				// Set PasswordField Back to Normal
				passwordField.setText("");
				passwordField.setBackground(UIManager.getColor("TextField.background"));
				passwordField.setForeground(SystemColor.scrollbar);
				char c = 0;
		        passwordField.setEchoChar(c);
		        passwordField.setText("Password");
		        //*******************************************************************************
		        
		        int Valid = -99;

		        try {
		        	int info = 1;
					Valid = database.getInfo(connection, Login, Password, info);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.printf("\n Failed to retrieve \n");
					
				}
		        
				if(Valid > 0){
					System.out.printf(" Successful in Login App. \n");
					frame.setVisible(false);
					userGUI program = new userGUI();
					//System.exit(0);
				}else{
					UnsuccessfulLogin fail = new UnsuccessfulLogin(Login);
					fail.NoSuccess(Login);
				}
			}
		});
		LoginButton.setBackground(new Color(102, 153, 255));
		
		JTextPane txtpnCreateAnAccount = new JTextPane();
		txtpnCreateAnAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CreateAccount newAcct = new CreateAccount();
				newAcct.account();
			}
		});
		txtpnCreateAnAccount.setEditable(false);
		txtpnCreateAnAccount.setText("Create an account");
		txtpnCreateAnAccount.setForeground(new Color(25, 25, 112));
		txtpnCreateAnAccount.setFont(new Font("Verdana", Font.BOLD, 11));
		txtpnCreateAnAccount.setBackground(SystemColor.menu);
		
		passwordField = new JPasswordField();
		
		// Setting Password as text
		passwordField.setBackground(UIManager.getColor("TextField.background"));
		passwordField.setForeground(SystemColor.scrollbar);
		passwordField.setText("Password");
		char c = 0;
        passwordField.setEchoChar(c);
        passwordField.setText("Password");
		
		// Done setting text
		
		
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				char c = 183;
				passwordField.setForeground(Color.black);
		        passwordField.setEchoChar(c);
				passwordField.setText("");
			}
		});
		passwordField.setBackground(UIManager.getColor("TextField.background"));
		passwordField.setForeground(SystemColor.scrollbar);
		passwordField.setText("Password");
		passwordField.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				Password = passwordField.getText();


	            System.out.println("\n Password is : "+Password); 
			
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(60, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(txtpnCreateAnAccount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(63))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(LoginMessage, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(LoginButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(LoginField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
								.addComponent(passwordField))
							.addGap(42))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(30)
					.addComponent(LoginMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(LoginField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(LoginButton)
					.addPreferredGap(ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
					.addComponent(txtpnCreateAnAccount, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(WelcomeMessage, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
							.addGap(0))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
							.addGap(66))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(WelcomeMessage, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 342, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(48, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
