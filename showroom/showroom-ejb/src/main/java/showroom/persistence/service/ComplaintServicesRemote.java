package showroom.persistence.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;
import javax.persistence.Query;

import artRoom.entities.User;
import artRoom.entities.Complaint;
import artRoom.entities.ComplaintId;

@Remote
public interface ComplaintServicesRemote {
	void addComplaint(User complainer, User receiver, String Subject, String message);
	
	User findComplainerById(Integer id);
	
	User findReceiverById(Integer id);

	void deleteComplaint(Complaint complaint) ;

	List<Complaint> findAllComplaints(Integer id);

	Complaint findComplaintById(Integer idComplainer, Integer idReceiver,Date date);

	void deleteComplaintById(Integer idComplainer, Integer idReceiver,Date date);

	User findUser(String name, String email);

	User findUserByName(String name, String lastname);

	List<Complaint> findAllYourComplaints(Integer id);

	List<Complaint> Search(String chaine,Integer id);

	List<Object[]> Statistique();

	List<Complaint> FindAll();

	User findLoger(String email, String password);

}
