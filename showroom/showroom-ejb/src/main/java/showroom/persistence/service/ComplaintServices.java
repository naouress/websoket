package showroom.persistence.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import artRoom.entities.User;
import artRoom.entities.Complaint;
import artRoom.entities.ComplaintId;

/**
 * Session Bean implementation class complaintServices
 */
@Stateless
public class ComplaintServices implements ComplaintServicesRemote, ComplaintServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ComplaintServices() {
	}

	@Override
	public void addComplaint(User complainer, User receiver, String Subject, String message) {
		Complaint complaint = new Complaint(Subject, message, complainer, receiver);

		entityManager.persist(complaint);
	}

	@Override
	public User findComplainerById(Integer id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	public User findLoger(String email,String password) {
		return (User) entityManager.createQuery("SELECT e FROM User e WHERE e.email = ?1 AND e.password= ?2")
				.setParameter(1, email).setParameter(2, password).getSingleResult();
	}
	

	@Override
	public User findReceiverById(Integer id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User findUser(String name, String email) {
		return (User) entityManager.createQuery("SELECT e FROM User e WHERE e.firstName = ?1 AND e.email = ?2")
				.setParameter(1, name).setParameter(2, email).getSingleResult();
	}

	@Override
	public User findUserByName(String name, String lastname) {
		return (User) entityManager.createQuery("SELECT e FROM User e WHERE e.firstName = ?1 AND e.lastName = ?2")
				.setParameter(1, name).setParameter(2, lastname).getSingleResult();
	}

	@Override
	public Complaint findComplaintById(Integer idComplainer, Integer idReceiver, Date date) {
		return (Complaint) entityManager
				.createQuery(
						"SELECT e FROM Complaint e WHERE e.complaintId.idComplainer = ?1 AND e.complaintId.idReceiver = ?2  AND e.complaintId.date = ?3")
				.setParameter(1, idComplainer).setParameter(2, idReceiver).setParameter(3, date).getSingleResult();
	}

	@Override
	public void deleteComplaintById(Integer idComplainer, Integer idReceiver, Date date) {
		entityManager.remove(findComplaintById(idComplainer, idReceiver, date));
	}

	@Override
	public void deleteComplaint(Complaint complaint) {
		entityManager.remove(entityManager.merge(complaint));
	}

	@Override
	public List<Complaint> findAllComplaints(Integer id) {
		return entityManager
				.createQuery(
						"select t from Complaint t  WHERE t.complaintId.idReceiver = ?1 ORDER BY t.complaintId.date desc")
				.setParameter(1, id).getResultList();
	}

	@Override
	public List<Complaint> findAllYourComplaints(Integer id) {
		return entityManager
				.createQuery(
						"select t from Complaint t  WHERE t.complaintId.idComplainer = ?1 ORDER BY t.complaintId.date desc")
				.setParameter(1, id).getResultList();
	}

	@Override
	public List<Complaint> Search(String chaine, Integer id) {
		return entityManager
				.createQuery(
						"select t from Complaint t  WHERE (t.complainer.email LIKE ?3 OR t.complainer.firstName LIKE ?3 OR t.complainer.lastName LIKE ?3 OR t.subject LIKE ?3 OR t.message LIKE ?4) AND(t.complaintId.idComplainer = ?1 OR t.complaintId.idReceiver = ?2) ORDER BY t.complaintId.date desc")
				.setParameter(1, id).setParameter(2, id).setParameter(3, "%" + chaine + "%")
				.setParameter(4, "%" + chaine + "%").getResultList();
	}

	@Override
	public List<Complaint> FindAll() {
		return entityManager.createQuery("select t from Complaint t  ").getResultList();
	}

	@Override
	public List<Object[]> Statistique() {
		return entityManager.createQuery(
				"select t.complaintId.date ,count(t.complaintId.idComplainer) from Complaint t GROUP BY t.complaintId.date",
				Object[].class).getResultList();
	}
}
