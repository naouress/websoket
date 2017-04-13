package GUI.complaint;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import artRoom.entities.Complaint;
import artRoom.entities.User;
import showroom.persistence.service.ComplaintServices;
import showroom.persistence.service.ComplaintServicesRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class AdminResponseComplaint extends JFrame {

	public JPanel contentPane;
	public JTextField nameComplainer;
	public JTextField mailComplainer;
	private Complaint complaint;
	private ComplaintServicesRemote complaintServiceRemote;
	public JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login l=new Login();
					int id1=l.idUser;
					AdminResponseComplaint frame = new AdminResponseComplaint(id1);
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
	public AdminResponseComplaint(Integer id) throws NamingException {
		setVisible(true);
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		setTitle("ContactUs-ComposeComplaint\r\n");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 547);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnContactUs = new JMenu("Contact");
		menuBar.add(mnContactUs);

		JMenuItem mntmNewMenuItem = new JMenuItem("Complaint Inbox");
		mntmNewMenuItem.setBackground(new Color(255, 228, 225));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdminInbox all;
				try {
					all = new AdminInbox(id);
					all.show();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				setVisible(false);
			}
		});
		mnContactUs.add(mntmNewMenuItem);

		JMenuItem mntmComplaintSent = new JMenuItem("Complaint Sent");
		mntmComplaintSent.setBackground(new Color(255, 228, 225));
		mntmComplaintSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdminSent sent = new AdminSent(id);
					sent.show();

				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}

		});
		mnContactUs.add(mntmComplaintSent);

		JMenuItem mntmComposeANew = new JMenuItem("Compose a new Complaint");
		mntmComposeANew.setBackground(new Color(255, 228, 225));
		mntmComposeANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new AdminResponseComplaint(id);
					setVisible(false);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnContactUs.add(mntmComposeANew);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 220));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
					.addContainerGap())
		);

		JLabel lblLastname = new JLabel("Name");
		lblLastname.setForeground(new Color(199, 21, 133));

		nameComplainer = new JTextField();
		nameComplainer.setColumns(10);

		mailComplainer = new JTextField();
		mailComplainer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				String mail = mailComplainer.getText();
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
		});
		
		
	
		
		mailComplainer.setColumns(10);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setForeground(new Color(199, 21, 133));

		JLabel label_5 = new JLabel("Subject");
		label_5.setForeground(new Color(199, 21, 133));

		JLabel lblResponse = new JLabel("Response");
		lblResponse.setForeground(new Color(199, 21, 133));
		JTextArea textArea = new JTextArea();
		JButton send = new JButton("Send");
		send.setFont(new Font("Tahoma", Font.BOLD, 12));
		send.setForeground(new Color(205, 92, 92));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = nameComplainer.getText();
				String email = mailComplainer.getText();
				String subject = textField_1.getText();
				String message = textArea.getText();

				User receiver = complaintServicesRemote.findUser(name, email);
				// subject et receiver à fixer
				User complainer = complaintServicesRemote.findComplainerById(id);

				complaintServicesRemote.addComplaint(complainer, receiver, subject, message);
				JOptionPane.showMessageDialog(null, "sent!");
				
				try {
					AdminInbox all = new AdminInbox(id);
					all.show();
					all.State="inbox";
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);

			}
		});
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(205, 92, 92));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminInbox ad;
				try {
					ad = new AdminInbox(id);
					ad.show();
					setVisible(false);
					ad.State="inbox";
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\reply-xxl.png"));
		
		JLabel lblRespondToComplaint = new JLabel("Reply Complaint");
		lblRespondToComplaint.setFont(new Font("Segoe Script", Font.BOLD, 15));
		lblRespondToComplaint.setForeground(new Color(199, 21, 133));
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(84)
							.addComponent(lblResponse, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(232)
							.addComponent(send, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(30, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(82)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createSequentialGroup()
										.addComponent(lblLastname, GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
										.addGap(7))
									.addGroup(gl_panel.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(label_5)
										.addGap(28))))
							.addGroup(gl_panel.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
								.addGap(18)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))
							.addGap(47)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(nameComplainer, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(textField_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(mailComplainer, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
							.addGap(77))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblRespondToComplaint)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(25)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(53)
							.addComponent(lblRespondToComplaint)))
					.addGap(69)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameComplainer, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLastname))
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mailComplainer, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_5))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(28)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(send, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
								.addComponent(btnBack, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(51)
							.addComponent(lblResponse)))
					.addGap(15))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
