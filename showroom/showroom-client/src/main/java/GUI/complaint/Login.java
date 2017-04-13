package GUI.complaint;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import artRoom.entities.User;
import showroom.persistence.service.ComplaintServicesRemote;
import webSocket.AdminDiscussionsClient;
import webSocket.discussionMain;

import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField Email;
	private JPasswordField passwordField;
	private JButton btnLogin;
	public Integer idUser;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmSendAnEmail;
	private JLabel lblEmail;
	private JLabel lblPassword;
	private JLabel label;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	
	
	public Login() throws NamingException {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		setTitle("Login");
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");
		
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 337);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("ContactUs");
		mnNewMenu.setBackground(new Color(255, 239, 213));
		menuBar.add(mnNewMenu);
		
		mntmSendAnEmail = new JMenuItem("Send An Email");
		mntmSendAnEmail.setBackground(new Color(255, 228, 225));
		mntmSendAnEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EnvoyeMail env=new EnvoyeMail();
				env.show();
				setVisible(false);
			}
		});
		mnNewMenu.add(mntmSendAnEmail);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 240, 245));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		Email = new JTextField();
		Email.setColumns(10);

		passwordField = new JPasswordField();

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnLogin.setForeground(new Color(205, 92, 92));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = Email.getText();
				String pwd = passwordField.getText();

				User user = complaintServicesRemote.findLoger(email, pwd);

				idUser = user.getIdUser();
				String type = user.getType();

				if (type.equals("Admin")) {
					try {
						AdminInbox in = new AdminInbox(idUser);
						in.show();
						setVisible(false);
						
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						UserInbox uin = new UserInbox(idUser);
						uin.show();
						setVisible(false);
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		
		lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(205, 92, 92));
		
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(205, 92, 92));
		
		label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\welcome_zps1769b996.png"));
		
		 

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(316, Short.MAX_VALUE)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addGap(24))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblEmail)
						.addComponent(lblPassword))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(Email, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
						.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
					.addGap(93))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(181, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 88, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(Email, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(18)
					.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}
