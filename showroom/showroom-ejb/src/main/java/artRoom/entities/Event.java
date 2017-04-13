package artRoom.entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Event
 *
 */
@Entity

public class Event implements Serializable {

	   
	@Id
	private Integer idEvent;
	private Date startDate;
	private Date endDate;
	private String state;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Gallery galleryEvent;
	
	@ManyToOne
	private Artist artist;
	
	@OneToMany
	private List<Artwork> eventArtworks;

	public Event() {
		super();
	}   
	public Integer getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}   
	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}   
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}   
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}
   
}
