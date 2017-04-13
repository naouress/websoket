package TestComplaint;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import artRoom.entities.*;

import showroom.persistence.service.ComplaintServicesRemote;

public class TestFindComplaint {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		Date t = new Date(2017, 03, 13, 01,44,55);

		//Complaint comp = complaintServicesRemote. findComplaintById(1,2,t);
		//Complaint comp = complaintServicesRemote. findComplaintById(1,2,t);
		
			//System.out.println(comp.getComplaintId().getDate());
			
			
		

	}

}
