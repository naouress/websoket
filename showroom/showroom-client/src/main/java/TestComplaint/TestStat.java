package TestComplaint;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import showroom.persistence.service.ComplaintServicesRemote;

public class TestStat {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		ComplaintServicesRemote complaintServicesRemote = (ComplaintServicesRemote) context.lookup(
				"showroom-ear/showroom-ejb/ComplaintServices!showroom.persistence.service.ComplaintServicesRemote");
		
		List<Object[]> results = complaintServicesRemote.Statistique();
		
		for (Object[] result : results) {
		      System.out.println("Date: " + result[0] + ", redendance: " + result[1]);
		  }

	}

}
