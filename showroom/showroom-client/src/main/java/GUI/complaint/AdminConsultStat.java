package GUI.complaint;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import showroom.persistence.service.ComplaintServicesRemote;

public class AdminConsultStat extends JFrame {

	// private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminConsultStat frame = new AdminConsultStat();
					
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdminConsultStat() throws NamingException {
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		List<Object[]> results = complaintServicesRemote.Statistique();

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for (Object[] result : results) {
			Date x = (Date) result[0];
			Long y = (Long) result[1];
			dataset.setValue(y, "NumberComplaint", x);
		}
		JFreeChart chart = ChartFactory.createLineChart("Complaint Statistic", "Date", "Nomber Complaint", dataset,
				PlotOrientation.VERTICAL, true, false, false);

		//BarRenderer renderer = new BarRenderer();
		//CategoryPlot plot = null;
		ChartFrame frame1 = new ChartFrame("Complaint Statistic", chart);
		frame1.setVisible(true);
		frame1.setSize(400, 650);
	

	
	}

}
