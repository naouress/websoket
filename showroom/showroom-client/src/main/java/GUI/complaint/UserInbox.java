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

public class UserInbox extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField recherche;
	String State;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login l=new Login();
					int id1=l.idUser;
					UserInbox frame = new UserInbox(id1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
//********remplissage tableau********************
	 DefaultTableModel dtm;
	 String en_tetes[]={"Date","Sender","Email","Subject","Message"};
	 public void RemplirTableau(ComplaintServicesRemote complaintServicesRemote,JTable table,List<Complaint> comp){
	        dtm =new DefaultTableModel(null, en_tetes);
	         
	        

			for (Complaint c : comp) {
				 
	            Object[] ligne=new Object[5];
	            
	             ligne[0]=c.getComplaintId().getDate();
	             ligne[1]=c.getComplainer().getFirstName()+"-"+c.getComplainer().getLastName();
	             ligne[2]=c.getComplainer().getEmail();
	             ligne[3]=c.getSubject();
	             ligne[4]=c.getMessage();
	           
	             dtm.addRow(ligne);
	        }
	        table.setModel(dtm);
	    }
	 public void RemplirTableau2(ComplaintServicesRemote complaintServicesRemote,JTable table,List<Complaint> comp){
	        dtm =new DefaultTableModel(null, en_tetes);
	         
	        

			for (Complaint c : comp) {
				 
	            Object[] ligne=new Object[5];
	            
	             ligne[0]=c.getComplaintId().getDate();
	             ligne[1]=c.getReceiver().getFirstName()+"-"+c.getComplainer().getLastName();
	             ligne[2]=c.getReceiver().getEmail();
	             ligne[3]=c.getSubject();
	             ligne[4]=c.getMessage();
	           
	             dtm.addRow(ligne);
	        }
	        table.setModel(dtm);
	    }
//*****************************************************************
	/**
	 * Create the frame.
	 */
	public UserInbox(Integer id) throws NamingException{
		setTitle("ContactUs-Complaint Inbox");
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\ppt\\picture13891884752057.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 431);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Contact Us");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmComplaintInbox = new JMenuItem("Complaint Inbox");
		mntmComplaintInbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("ContactUs-Complaint Inbox");
				List<Complaint> comp = complaintServicesRemote.findAllComplaints(id);
				RemplirTableau(complaintServicesRemote, table, comp);
				State = "inbox";
			}
		});
		mnNewMenu.add(mntmComplaintInbox);
		
		JMenuItem mntmComplaintSent = new JMenuItem("Complaint Sent");
		mntmComplaintSent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setTitle("ContactUs-Complaint Inbox");
				List<Complaint> you = complaintServicesRemote.findAllYourComplaints(id);
				RemplirTableau2(complaintServicesRemote, table, you);
				State = "sent";
			}
		});
		
		JMenuItem mntmChat = new JMenuItem("Chat");
		mntmChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		mnNewMenu.add(mntmChat);
		mnNewMenu.add(mntmComplaintSent);
		
		JMenuItem mntmComposeANew = new JMenuItem("Compose a new Complaint");
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
		mnNewMenu.add(mntmComposeANew);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 224));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("L'artisto");
		label.setForeground(new Color(219, 112, 147));
		label.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		
		table = new JTable();
		table.setBackground(new Color(255, 228, 225));
		List<Complaint> comp = complaintServicesRemote.findAllComplaints(id);
		RemplirTableau(complaintServicesRemote, table,comp);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSearch.setForeground(new Color(139, 0, 139));
		
		recherche = new JTextField();
		recherche.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				List<Complaint> complaints=new ArrayList<Complaint>();
				String value=recherche.getText();
		        complaints=complaintServicesRemote.Search(value, id);
		        RemplirTableau(complaintServicesRemote, table, complaints);
		        if(value.equals("")){
		        	RemplirTableau(complaintServicesRemote, table, comp);
		        }
			}
		});
		recherche.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\search-icon-53576.png"));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Naoures\\Desktop\\esprit\\P-DEV\\complaint.jpg"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(69)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblSearch)
							.addGap(18)
							.addComponent(recherche, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(table, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(19)
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
					.addComponent(lblNewLabel_1)
					.addGap(31))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_1))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(20)
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(recherche, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSearch))))
					.addGap(18)
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
