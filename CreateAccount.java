package NI_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.SystemColor;

import javax.swing.JPasswordField;
import javax.swing.JButton;






import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateAccount {

	private JFrame frmCreateAccount;
	private JTextField UserId;
	private JPasswordField Password;
	Connection conn = null;

	/**
	 * Launch the application.
	 */
	public void account() {
      
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					CreateAccount window = new CreateAccount();
					window.frmCreateAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateAccount() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateAccount = new JFrame();
		frmCreateAccount.getContentPane().setForeground(UIManager.getColor("ComboBox.buttonShadow"));
		frmCreateAccount.getContentPane().setBackground(new Color(255, 255, 255));
		frmCreateAccount.setTitle("Create Account");
		frmCreateAccount.setBounds(100, 100, 331, 381);
		frmCreateAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateAccount.getContentPane().setLayout(null);
		
		JLabel lblCreateYourNew = new JLabel("Create Your New Account");
		lblCreateYourNew.setForeground(new Color(65, 105, 225));
		lblCreateYourNew.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
		lblCreateYourNew.setBounds(89, 53, 162, 14);
		frmCreateAccount.getContentPane().add(lblCreateYourNew);
		
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setFont(new Font("Vrinda", Font.PLAIN, 18));
		lblWelcome.setForeground(new Color(106, 90, 205));
		lblWelcome.setBounds(127, 22, 67, 20);
		frmCreateAccount.getContentPane().add(lblWelcome);
		
		UserId = new JTextField();
	
		UserId.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserId.setText("");
				UserId.setForeground(Color.black);
				
			}
		});
		UserId.setForeground(UIManager.getColor("ComboBox.buttonShadow"));
		UserId.setText("User-ID:");
		UserId.setBounds(79, 106, 172, 43);
		frmCreateAccount.getContentPane().add(UserId);
		UserId.setColumns(10);
		
		Password = new JPasswordField();
		Password.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				char c = 183;
				Password.setEchoChar(c);
				Password.setForeground(Color.black);
				Password.setText("");
			}
		});
		Password.setForeground(UIManager.getColor("ComboBox.buttonShadow"));
		char c = 0;
        Password.setEchoChar(c);
		Password.setText("Password");
		Password.setBounds(79, 163, 172, 43);
		frmCreateAccount.getContentPane().add(Password);
		
		JButton OKButton = new JButton("Create Your Account");
		OKButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String UserName = null, password = null;
				UserName = UserId.getText();
				password = Password.getText();
				
				System.out.printf("\n Your New User name is : '%s' with Password : '%s' \n", UserName, password);
				MySqlLogin update = new MySqlLogin();
//----------------------------------------------------------------------------------------------------------------
				int info = 2;
				try {
					LoginWindow getConnection = new LoginWindow();
					conn = getConnection.connection;
					update.getInfo(conn, UserName, password, info);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
//----------------------------------------------------------------------------------------------------------------
				frmCreateAccount.setVisible(false);
				
			}
		});
		OKButton.setBackground(new Color(211, 211, 211));
		OKButton.setForeground(new Color(0, 0, 0));
		OKButton.setBounds(79, 229, 172, 23);
		frmCreateAccount.getContentPane().add(OKButton);
	}

	public void account1(Connection connection) {
		// TODO Auto-generated method stub
		conn = connection;
	}

	
}
