package artRoom.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Complaint
 *
 */
@Entity

public class Complaint implements Serializable {

	@EmbeddedId
	private ComplaintId complaintId;
	private String subject;
	private String message;
	private String state;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="idComplainer",referencedColumnName="idUser",updatable=false,insertable=false)
	private User complainer;
	@ManyToOne
	@JoinColumn(name="idReceiver",referencedColumnName="idUser",updatable=false,insertable=false)
	private User receiver;
	
	
	public Complaint() {
		super();
	}


	public Complaint(String subject, String message, User complainer, User receiver) {
		super();
		this.complaintId=new ComplaintId(complainer.getIdUser(),receiver.getIdUser());
		this.subject = subject;
		this.message = message;
		this.complainer = complainer;
		this.receiver = receiver;
	}
	
	


	public Complaint(String subject, User complainer, User receiver) {
		super();
		this.subject = subject;
		this.complainer = complainer;
		this.receiver = receiver;
	}


	public Complaint(Date t, User complainer, User receiver) {
		super();
		this.complaintId=new ComplaintId(complainer.getIdUser(),receiver.getIdUser(),t);
		this.complainer = complainer;
		this.receiver = receiver;
	}


	public ComplaintId getComplaintId() {
		return complaintId;
	}


	public void setComplaintId(ComplaintId complaintId) {
		this.complaintId = complaintId;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public User getComplainer() {
		return complainer;
	}


	public void setComplainer(User complainer) {
		this.complainer = complainer;
	}


	public User getReceiver() {
		return receiver;
	}


	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}
	
	
	


	

   
}
