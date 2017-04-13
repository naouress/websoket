package artRoom.entities;

import java.io.Serializable;
import java.util.Date;

public class AdminMessage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String date;
	private String mail;
	private String text;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return text;
	}
	public AdminMessage() {
		// TODO Auto-generated constructor stub
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
