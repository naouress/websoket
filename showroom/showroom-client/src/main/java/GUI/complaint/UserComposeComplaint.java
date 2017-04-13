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

public class UserComposeComplaint extends JFrame {

	private JPanel contentPane;
	private Complaint complaint;
	private ComplaintServicesRemote complaintServiceRemote;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login l=new Login();
					int id1=l.idUser;
					UserComposeComplaint frame = new UserComposeComplaint(id1);
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
	public UserComposeComplaint(Integer id) throws NamingException {
		setVisible(true);
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		setTitle("ContactUs-ComposeComplaint\r\n");
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 526, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JTextArea textArea = new JTextArea();
		JPanel panel = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(panel,
				Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(panel,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));

		JLabel label_1 = new JLabel("Welcome to our Gallery");
		label_1.setFont(new Font("Segoe Script", Font.BOLD, 17));

		JLabel label = new JLabel("L'artisto");
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 21));

		JLabel label_2 = new JLabel("We would like to hear from you");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel label_5 = new JLabel("Subject");

		JComboBox<String> subjectComplaint = new JComboBox<String>();
		subjectComplaint.addItem("---Choises---");
		subjectComplaint.addItem("Account Problem");
		subjectComplaint.addItem("DeliveryProblem");

		JLabel label_6 = new JLabel("Leave your message hear");

		JButton send = new JButton("Send");
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String subject = (String) subjectComplaint.getSelectedItem();
				String message = textArea.getText();

				User complainer = complaintServicesRemote.findComplainerById(id);
				User receiver = complaintServicesRemote.findReceiverById(1);

				complaintServicesRemote.addComplaint(complainer, receiver, subject, message);
				JOptionPane.showMessageDialog(null, "Your complaint have been sent, Thank for your help!");
				
				try {
					UserInbox in = new UserInbox(id);
					in.show();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setVisible(false);

			}
		});

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel
				.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		
		
		
		JButton btnNewButton = new JButton("Home");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserInbox ui=new UserInbox(id);
					ui.show();
					ui.State="inbox";
					setVisible(false);
					
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(241, Short.MAX_VALUE)
					.addComponent(send, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.addGap(100))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(184)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(141, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGap(34)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_5)
									.addGap(43)
									.addComponent(subjectComplaint, 0, 277, Short.MAX_VALUE))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(108)
											.addComponent(label))
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(48)
											.addComponent(label_1))))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
									.addGap(215))))
						.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
							.addGap(112)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)))
					.addGap(110))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(label_1))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addGap(44)
					.addComponent(label_2)
					.addGap(65)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(subjectComplaint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(55)
					.addComponent(label_6)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(send)
						.addComponent(btnNewButton))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}
}
