package NI_GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UnsuccessfulLogin {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	
	UnsuccessfulLogin window = null;
	public void NoSuccess(final String Name) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new UnsuccessfulLogin(Name);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UnsuccessfulLogin(String Name) {
		initialize(Name);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String Name) {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 250, 250));
		frame.getContentPane().setLayout(null);
		
		JLabel UNameMore = new JLabel("With the given Password Doesn't Exist");
		UNameMore.setFont(new Font("Courier New", Font.BOLD, 12));
		UNameMore.setForeground(new Color(47, 79, 79));
		UNameMore.setBounds(94, 49, 259, 48);
		frame.getContentPane().add(UNameMore);
		
		JLabel UserN = new JLabel("User Name : "+Name);
		UserN.setFont(new Font("Courier New", Font.BOLD, 12));
		UserN.setForeground(new Color(47, 79, 79));
		UserN.setBounds(116, 24, 188, 31);
		frame.getContentPane().add(UserN);
		
		JButton OK = new JButton("OK");
		OK.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frame.setVisible(false);
				
			}
		});
		OK.setBackground(new Color(173, 216, 230));
		OK.setBounds(165, 108, 89, 31);
		frame.getContentPane().add(OK);
		frame.setForeground(new Color(176, 196, 222));
		frame.setBackground(new Color(176, 196, 222));
		frame.setBounds(100, 100, 452, 201);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
