package GUI.complaint;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import showroom.persistence.service.SendMail;

import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class EnvoyeMail extends JFrame {

	private JPanel contentPane;
	private JTextField address;
	private JTextField subject;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnvoyeMail frame = new EnvoyeMail();
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
	public EnvoyeMail() {
		setTitle("Send Email");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 235, 205));
		
		JLabel lblVotreAdresse = new JLabel("Email Address");
		
		address = new JTextField();
		address.addFocusListener(new FocusAdapter() {
		
			
			@Override
			public void focusLost(FocusEvent e) {
				String mail = address.getText();
				int k = 0;
				char ch ;
				Boolean test = false;
				while (test == false && k != mail.length()) {
				ch = mail.charAt(k);
					if (ch == '@') {
						int j = k + 1;
						while (test == false && j != mail.length()) {
						ch = mail.charAt(j);
							if (ch == '.') {
								test = true;
							}
							j++;
						}
					}
					k++;
				}System.out.println(test);
				if (test== false){
				JOptionPane.showMessageDialog(null, "check your email address");}
				
			}
			}
		);
		address.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Email Password");
		
		JLabel lblSubject = new JLabel("Subject");
		
		subject = new JTextField();
		subject.setColumns(10);
		
		JLabel lblMessage = new JLabel("Message");
		
		JTextArea message = new JTextArea();
		
		JButton btnSendEmail = new JButton("Send Email");
		btnSendEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSendEmail.setForeground(new Color(205, 92, 92));
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username=address.getText();
				String pwd=passwordField.getText();
				String subj=subject.getText();
				String msg=message.getText();
				
				SendMail test = new SendMail();
				test.envoyer(username,pwd,subj,msg);
				JOptionPane.showMessageDialog(null, "We have received your mail, we will repond you as soon as possible");
				
			}
		});
		
		passwordField = new JPasswordField();
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.setForeground(new Color(205, 92, 92));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Login l=new Login();
					l.show();
					setVisible(false);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\Gmail.jpg"));
		
		JLabel lblContactUsWith = new JLabel("Contact Us with Email");
		lblContactUsWith.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblContactUsWith.setForeground(new Color(178, 34, 34));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(49)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVotreAdresse)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addComponent(lblSubject)
							.addComponent(lblMessage))
						.addComponent(lblNewLabel))
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(subject)
						.addComponent(passwordField)
						.addComponent(address, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(message)
							.addGap(4)))
					.addContainerGap(40, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(227, Short.MAX_VALUE)
					.addComponent(btnSendEmail)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(47)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblContactUsWith)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addComponent(lblContactUsWith))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(22)
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)))
					.addGap(46)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVotreAdresse)
						.addComponent(address, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addGap(29)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(subject, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSubject))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMessage)
						.addComponent(message, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSendEmail, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 424, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
