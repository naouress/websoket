package TestComplaint;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import artRoom.entities.*;

import showroom.persistence.service.ComplaintServicesRemote;

public class FindAllComplaints {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		//Date t = new Date(2017, 03, 11);

		List<Complaint> comp = complaintServicesRemote.findAllYourComplaints(1);

		for (Complaint c : comp) {
			System.out.println(c.getComplaintId().getIdReceiver());
		}
		
		//List<Complaint> c=complaintServicesRemote.FindAll();
		/*for (Object c1 : c) {
		System.out.println(c1.);
	}*/
		

	}

}
