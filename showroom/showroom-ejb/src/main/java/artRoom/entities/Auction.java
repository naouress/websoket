package artRoom.entities;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Auction
 *
 */
@Entity

public class Auction implements Serializable {

	@EmbeddedId
	private AuctionId auctionId;
	private Integer duration;
	private Float price;
	private String type;
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "idOwner", referencedColumnName = "idUser", updatable = false, insertable = false)
	private User owner;

	@ManyToOne
	@JoinColumn(name = "idArtwork", referencedColumnName = "idArtwork", updatable = false, insertable = false)
	private Artwork artwork;

	
	public Auction( Integer duration, Float price, String type, User owner, Artwork artwork) {
		super();
		this.auctionId = new AuctionId(owner.getIdUser(),artwork.getIdArtwork());
		this.duration = duration;
		this.price = price;
		this.type = type;
		this.owner = owner;
		this.artwork = artwork;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Artwork getArtwork() {
		return artwork;
	}

	public void setArtwork(Artwork artwork) {
		this.artwork = artwork;
	}

	public Auction() {
		super();
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
