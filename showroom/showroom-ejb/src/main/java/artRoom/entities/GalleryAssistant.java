package artRoom.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GalleryAssistant
 *
 */
@Entity

public class GalleryAssistant extends User implements Serializable {

	
	private String position;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	private Gallery gallery;

	public GalleryAssistant() {
		super();
	}   
	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
   
}
