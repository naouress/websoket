package TestComplaint;

import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import artRoom.entities.Complaint;
import showroom.persistence.service.ComplaintServicesRemote;

public class TestSearch {
	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");

		//Date t = new Date(2017, 03, 11);

		String ch="retar";
		Integer id=1;
		
		List<Complaint> comp = complaintServicesRemote.Search(ch, id);

		
		
		for (Complaint c : comp) {
			System.out.println(c.getMessage());
		}

	}
}
