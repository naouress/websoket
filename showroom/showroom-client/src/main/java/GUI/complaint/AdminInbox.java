package GUI.complaint;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import artRoom.entities.Complaint;
import artRoom.entities.User;
import showroom.persistence.service.ComplaintServicesRemote;
import webSocket.AdminDiscussionsClient;
import webSocket.discussionMain;

import javax.mail.search.SentDateTerm;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class AdminInbox extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField recherche;
	String State = "inbox";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login l = new Login();
					int id1 = l.idUser;
					AdminInbox frame = new AdminInbox(id1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ********remplissage tableau********************
	DefaultTableModel dtm;
	String en_tetes[] = { "Date", "Sender", "SenderEmail", "Subject", "Message" };

	public void RemplirTableau(ComplaintServicesRemote complaintServicesRemote, JTable table, List<Complaint> comp) {
		dtm = new DefaultTableModel(null, en_tetes);

		for (Complaint c : comp) {

			if (c.getState().equals("ok")) {
				Object[] ligne = new Object[5];

				ligne[0] = c.getComplaintId().getDate();
				ligne[1] = c.getComplainer().getFirstName() + "-" + c.getComplainer().getLastName();
				ligne[2] = c.getComplainer().getEmail();
				ligne[3] = c.getSubject();
				ligne[4] = c.getMessage();

				dtm.addRow(ligne);
			}
		}
		table.setModel(dtm);
	}

	// *****************************************************************
	/**
	 * Create the frame.
	 */
	public AdminInbox(Integer id) throws NamingException {
		setTitle("ContactUs-Complaint Inbox");
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 481);
		table = new JTable();
		table.setBackground(new Color(255, 235, 205));
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Contact Us");
		menuBar.add(mnNewMenu);

		JMenuItem mntmComplaintInbox = new JMenuItem("Complaint Inbox");
		mntmComplaintInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				List<Complaint> comp = complaintServicesRemote.findAllComplaints(id);
				RemplirTableau(complaintServicesRemote, table, comp);
				State = "inbox";
			}
		});

		mnNewMenu.add(mntmComplaintInbox);

		JMenuItem mntmComplaintSent = new JMenuItem("Complaint Sent");
		mntmComplaintSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdminSent sent = new AdminSent(id);
					sent.show();
					setVisible(false);
				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JMenuItem mntmChat = new JMenuItem("Chat");
		mntmChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user1= complaintServicesRemote.findComplainerById(id);
				AdminDiscussionsClient.Mail=user1.getEmail();
				discussionMain.main(null);
			}
		});
		mnNewMenu.add(mntmChat);

		mnNewMenu.add(mntmComplaintSent);

		JMenuItem mntmAllComplaints = new JMenuItem("All Complaints");
		mntmAllComplaints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Complaint> all = complaintServicesRemote.FindAll();
				RemplirTableau(complaintServicesRemote, table, all);
				State = "";
				setTitle("ContactUs-All Complaint");
			}
		});

		mnNewMenu.add(mntmAllComplaints);

		JMenu mnStatistic = new JMenu("Statistic");
		menuBar.add(mnStatistic);

		JMenuItem mntmNewMenuItem = new JMenuItem("Consult Statistic");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdminConsultStat stat = new AdminConsultStat();

				} catch (NamingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnStatistic.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 248, 220));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		List<Complaint> comp1 = complaintServicesRemote.findAllComplaints(id);
		RemplirTableau(complaintServicesRemote, table, comp1);

		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\remove-icon-png-26.png"));
		btnNewButton.setForeground(new Color(205, 92, 92));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				int option = jop.showConfirmDialog(null, "Voulez-vous vraiment supprimer la reclamation ?",
						"suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {

					try {
						int i = table.getSelectedRow();

						String b = (table.getValueAt(i, 0).toString());
						System.out.println(b);
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						Date d = null;
						try {
							d = df.parse(b);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						String c = (table.getValueAt(i, 1).toString());
						int k = 0;
						char ch = c.charAt(k);
						String nom = "", prenom = "";
						while (ch != '-' && k != c.length()) {

							nom = nom + ch;
							k++;
							ch = c.charAt(k);

						}
						prenom = c.substring(k + 1);

						System.out.println(prenom + nom);
						User sender = complaintServicesRemote.findUserByName(nom, prenom);
						int r = sender.getIdUser();

						System.out.println(r);
						System.out.println(State);

						complaintServicesRemote.deleteComplaintById(r, id, d);
						List<Complaint> comp = complaintServicesRemote.findAllComplaints(id);
						RemplirTableau(complaintServicesRemote, table, comp);

						System.out.println("deleted");

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Pick the complaint you want delete");
					}
				}

			}
		});

		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSearch.setForeground(new Color(128, 0, 128));

		recherche = new JTextField();
		recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				List<Complaint> complaints = new ArrayList<Complaint>();
				String value = recherche.getText();
				complaints = complaintServicesRemote.Search(value, id);
				RemplirTableau(complaintServicesRemote, table, complaints);
				if (value.equals("")) {
					List<Complaint> comp = complaintServicesRemote.findAllComplaints(id);
					RemplirTableau(complaintServicesRemote, table, comp);
				}
			}
		});
		recherche.setColumns(10);

		JButton btnResponse = new JButton("Reply");
		btnResponse
				.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\iconos_menu_atencion216x220.png"));
		btnResponse.setForeground(new Color(205, 92, 92));
		btnResponse.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnResponse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = table.getSelectedRow();

					String c = (table.getValueAt(i, 1).toString());
					int k = 0;
					char ch = c.charAt(k);
					String nom = "", prenom = "";
					while (ch != '-' && k != c.length()) {
						nom = nom + ch;
						k++;
						ch = c.charAt(k);
					}
					prenom = c.substring(k + 1);
					String mail = (table.getValueAt(i, 2).toString());
					String subject = (table.getValueAt(i, 3).toString());
					try {
						AdminResponseComplaint ad = new AdminResponseComplaint(id);
						ad.nameComplainer.setText(nom);
						ad.mailComplainer.setText(mail);
						ad.textField_1.setText(subject);
						setVisible(false);
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					System.out.println(prenom + nom);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Select the one to respond");
				}
			}

		});

		JLabel label_1 = new JLabel("L'artisto");
		label_1.setForeground(new Color(219, 112, 147));
		label_1.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\search-icon-53576.png"));

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\images.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(btnResponse, GroupLayout.PREFERRED_SIZE, 112,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnNewButton)
										.addContainerGap())
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblSearch, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(recherche, GroupLayout.PREFERRED_SIZE, 252,
												GroupLayout.PREFERRED_SIZE)
										.addGap(75))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 251, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1).addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(table, GroupLayout.PREFERRED_SIZE, 441,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));
		gl_contentPane
				.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 29,
														GroupLayout.PREFERRED_SIZE)
												.addGap(26))
										.addGroup(gl_contentPane.createSequentialGroup().addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.UNRELATED)))
								.addGroup(
										gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(lblNewLabel)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
														.addComponent(recherche, GroupLayout.PREFERRED_SIZE, 34,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(lblSearch)))
								.addGap(18)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addGap(36)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 47,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnResponse))
								.addContainerGap()));
		contentPane.setLayout(gl_contentPane);
	}
}
