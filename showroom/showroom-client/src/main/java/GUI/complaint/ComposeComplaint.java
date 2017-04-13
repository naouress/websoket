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

public class ComposeComplaint extends JFrame {

	private JPanel contentPane;
	private Complaint complaint;
	private ComplaintServicesRemote complaintServiceRemote;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login l=new Login();
					int id1=l.idUser;
					ComposeComplaint frame = new ComposeComplaint(id1);
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
	public ComposeComplaint(Integer id) throws NamingException {
		setVisible(true);
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		setTitle("ContactUs-ComposeComplaint\r\n");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 417, 452);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnContactUs = new JMenu("Contact Us");
		menuBar.add(mnContactUs);

		JMenuItem mntmNewMenuItem = new JMenuItem("Complaint Inbox");
		mntmNewMenuItem.setBackground(new Color(255, 228, 196));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					UserInbox all = new UserInbox(id);
					all.show();
					setVisible(false);
					all.State="inbox";
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				setVisible(false);
			}
		});
		mnContactUs.add(mntmNewMenuItem);

		JMenuItem mntmComplaintSent = new JMenuItem("Complaint Sent");
		mntmComplaintSent.setBackground(new Color(255, 228, 196));
		mntmComplaintSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserInbox all = new UserInbox(id);
					all.show();
					setVisible(false);
					all.State="sent";

				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
			}

		});
		mnContactUs.add(mntmComplaintSent);

		JMenuItem mntmComposeANew = new JMenuItem("Compose a new Complaint");
		mntmComposeANew.setBackground(new Color(255, 222, 173));
		mntmComposeANew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ComposeComplaint comp=new ComposeComplaint(id);
					comp.show();
					setVisible(false);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnContactUs.add(mntmComposeANew);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 248, 220));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 391, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		JLabel label_1 = new JLabel("Welcome to our Gallery");
		label_1.setForeground(new Color(255, 140, 0));
		label_1.setBackground(new Color(255, 165, 0));
		label_1.setFont(new Font("Segoe Script", Font.BOLD, 17));

		JLabel label = new JLabel("L'artisto");
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 21));

		JLabel label_2 = new JLabel("We would like to hear from you");
		label_2.setForeground(new Color(255, 165, 0));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_5 = new JLabel("Subject");

		JLabel label_6 = new JLabel("Leave your message hear");
		JTextArea textArea_1 = new JTextArea();
		JButton send = new JButton("Send");
		send.setFont(new Font("Tahoma", Font.BOLD, 12));
		send.setForeground(new Color(255, 165, 0));
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String subject = textField.getText();
				String message = textArea_1.getText();

				User complainer = complaintServicesRemote.findComplainerById(id);
				// subject et receiver à fixer
				User receiver = complaintServicesRemote.findReceiverById(1);

				complaintServicesRemote.addComplaint(complainer, receiver, subject, message);
				JOptionPane.showMessageDialog(null, "Your complaint have been sent, Thank for your help!");
				
				try {
					UserInbox all = new UserInbox(id);
					all.show();
					
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);

			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBackground(new Color(205, 92, 92));
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\images (1).jpg"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					UserInbox all = new UserInbox(id);
					all.show();
					setVisible(false);
					all.State="inbox";
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(255, 165, 0));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\EpqHGIrE.jpeg"));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
									.addGap(8))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_5)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
								.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE))
							.addGap(207))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_1)
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addComponent(lblNewLabel)
							.addGap(51)
							.addComponent(label)
							.addGap(67))))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(199)
					.addComponent(send, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(120, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(64)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(302, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(36)
							.addComponent(label_1))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(26)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(label))))
					.addGap(18)
					.addComponent(label_2)
					.addGap(42)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(label_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(send, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.addGap(22))))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
