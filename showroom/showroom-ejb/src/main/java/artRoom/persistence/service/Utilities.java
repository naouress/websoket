package artRoom.persistence.service;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import artRoom.entities.Gallery;

@Singleton
@LocalBean
@Startup
public class Utilities {
	@PersistenceContext
	private EntityManager entityManager;
	@PostConstruct
	public void initDb() {
		Gallery gallery = new Gallery();
	
		gallery.setAddress("Nabeul");
		entityManager.persist(gallery);
		
		Gallery gallery2 = new Gallery();
		
		gallery2.setAddress("Paris");
		entityManager.persist(gallery2);
		
        Gallery gallery3 = new Gallery();
		gallery3.setAddress("Touleuse");
		entityManager.persist(gallery3);
		
	}
}
