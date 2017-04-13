package TestComplaint;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import artRoom.entities.User;
import showroom.persistence.service.ComplaintServicesRemote;

public class TestFindUser {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		//Date t = new Date(2017, 03, 13, 01,44,55);

		User u1 = complaintServicesRemote. findUser("hend", "hend");

		
			System.out.println(u1.getIdUser());
		

	}
}
