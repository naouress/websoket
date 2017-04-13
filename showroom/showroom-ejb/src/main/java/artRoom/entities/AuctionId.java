package artRoom.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class AuctionId implements Serializable {

	private Integer idOwner;
	private Integer idArtwork;
	private Date dateAuction;
	private static final long serialVersionUID = 1L;

	public AuctionId() {
		// TODO Auto-generated constructor stub
	}

	public AuctionId(Integer idOwner, Integer idArtwork) {
		super();
		this.idOwner = idOwner;
		this.idArtwork = idArtwork;
		this.dateAuction = new Date();
	}

	
	public Integer getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(Integer idOwner) {
		this.idOwner = idOwner;
	}

	public Integer getidArtwork() {
		return idArtwork;
	}

	public void setidArtwork(Integer idArtwork) {
		this.idArtwork = idArtwork;
	}

	public Date getDateAuction() {
		return dateAuction;
	}

	public void setDateAuction(Date dateAuction) {
		this.dateAuction = dateAuction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateAuction == null) ? 0 : dateAuction.hashCode());
		result = prime * result + ((idArtwork == null) ? 0 : idArtwork.hashCode());
		result = prime * result + ((idOwner == null) ? 0 : idOwner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuctionId other = (AuctionId) obj;
		if (dateAuction == null) {
			if (other.dateAuction != null)
				return false;
		} else if (!dateAuction.equals(other.dateAuction))
			return false;
		if (idArtwork == null) {
			if (other.idArtwork!= null)
				return false;
		} else if (!idArtwork.equals(other.idArtwork))
			return false;
		if (idOwner == null) {
			if (other.idOwner != null)
				return false;
		} else if (!idOwner.equals(other.idOwner))
			return false;
		return true;
	}

}
