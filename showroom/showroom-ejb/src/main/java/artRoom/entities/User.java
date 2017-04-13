package artRoom.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity

public class User implements Serializable {

	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Integer idUser;
	private String password;
	private String address;
	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNumber;
	private String type;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="owner")
	private List<Auction> auctions;
	
	@OneToMany(mappedBy="complainer")
	private List<Complaint> complaints;
	
	@OneToMany(mappedBy="receiver")
	private List<Complaint> responses;

	public User() {
		super();
	}   
	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}   
	public String getPassword() {
		return this.password;
	}

	public List<Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
	public List<Complaint> getResponses() {
		return responses;
	}
	public void setResponses(List<Complaint> responses) {
		this.responses = responses;
	}
	public void setPassword(String password) {
		this.password = password;
	}   
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}   
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}   
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public Long getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
   
}
