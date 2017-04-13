package TestComplaint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import showroom.persistence.service.*;
import artRoom.entities.*;

public class TestDeleteComplaint {
	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		//Complaint complaint = complaintServicesRemote. findComplaintById(1,2);

		//Date t = new Date(2017, 03, 13,16,32,14);
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        
	        Date d=null;
			try {
				d = df.parse("2017-03-13 17:18:30.0");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		complaintServicesRemote.deleteComplaintById(1,2,d);
	}
}
