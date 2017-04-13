package artRoom.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Artist
 *
 */
@Entity

public class Artist extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="artist")
	private List<Event> events;

	public Artist() {
		super();
	}
   
}
