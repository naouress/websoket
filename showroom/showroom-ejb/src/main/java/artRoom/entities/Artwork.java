package artRoom.entities;

import java.io.Serializable;
import java.lang.Boolean;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Artwork
 *
 */
@Entity

public class Artwork implements Serializable {

	   
	@Id
	private Integer idArtwork;
	private String title;
	private String description;
	private Float price;
	private Boolean disponibility;
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="artwork")
	private List<Auction> auctions;

	
	public Artwork() {
		super();
	}   
	public Integer getIdArtwork() {
		return this.idArtwork;
	}
	public List<Auction> getAuctions() {
		return auctions;
	}
	public void setAuctions(List<Auction> auctions) {
		this.auctions = auctions;
	}

	public void setIdArtwork(Integer idArtwork) {
		this.idArtwork = idArtwork;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}   
	public Boolean getDisponibility() {
		return this.disponibility;
	}

	public void setDisponibility(Boolean disponibility) {
		this.disponibility = disponibility;
	}
   
}
