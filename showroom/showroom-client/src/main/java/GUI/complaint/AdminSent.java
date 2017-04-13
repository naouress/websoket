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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.Date;
import java.util.List;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class AdminSent extends JFrame {

	private JPanel contentPane;
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login l=new Login();
					int id1=l.idUser;
					AdminSent frame = new AdminSent(id1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ********remplissage tableau********************
	DefaultTableModel dtm;
	String en_tetes[] = { "Date", "Receiver", "ReceiverEmail", "Subject", "Message" };
	private JButton btnNewButton;
	private JLabel label;

	public void RemplirTableau(ComplaintServicesRemote complaintServicesRemote, JTable table) {
		dtm = new DefaultTableModel(null, en_tetes);

		List<Complaint> comp = complaintServicesRemote.findAllYourComplaints(1);

		for (Complaint c : comp) {

			Object[] ligne = new Object[5];

			ligne[0] = c.getComplaintId().getDate();
			ligne[1] = c.getReceiver().getFirstName() + "-" + c.getReceiver().getLastName();
			ligne[2] = c.getReceiver().getEmail();
			ligne[3] = c.getSubject();
			ligne[4] = c.getMessage();

			dtm.addRow(ligne);
		}
		table.setModel(dtm);
	}
	// *****************************************************************

	/**
	 * Create the frame.
	 */
	public AdminSent(Integer id) throws NamingException {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		setTitle("ContactUs-Complaint Sent");
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 431, 422);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 245, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		table2 = new JTable();
		table2.setBackground(new Color(255, 235, 205));
		RemplirTableau(complaintServicesRemote, table2);

		JButton button = new JButton("Delete");
		button.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\remove-icon-png-26.png"));
		button.setFont(new Font("Tahoma", Font.BOLD, 12));
		button.setBackground(SystemColor.menu);
		button.setForeground(new Color(205, 92, 92));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane jop = new JOptionPane();
				int option = jop.showConfirmDialog(null, "Voulez-vous vraiment supprimer la reclamation ?",
						"suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (option == JOptionPane.OK_OPTION) {

					try {
						int i = table2.getSelectedRow();

						String b = (table2.getValueAt(i, 0).toString());
						System.out.println(b);
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

						Date d = null;
						try {
							d = df.parse(b);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						String c = (table2.getValueAt(i, 1).toString());
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
						complaintServicesRemote.deleteComplaintById(1, r, d);
						System.out.println("deleted");
						RemplirTableau(complaintServicesRemote, table2);
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Pick the complaint you want delete");
					}
				}
			}
		});
		
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\images (1).jpg"));
		btnNewButton.setForeground(new Color(205, 92, 92));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton.setBackground(SystemColor.menu);
		btnNewButton.addActionListener(new ActionListener() {
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
		
		label = new JLabel("L'artisto");
		label.setForeground(new Color(219, 112, 147));
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(171, Short.MAX_VALUE)
					.addComponent(button)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
					.addGap(34))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(290, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addComponent(table2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(table2, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 56, Short.MAX_VALUE))
					.addContainerGap(30, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
