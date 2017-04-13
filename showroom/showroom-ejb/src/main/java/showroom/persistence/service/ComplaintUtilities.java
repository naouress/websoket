package showroom.persistence.service;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import artRoom.entities.Admin;
import artRoom.entities.User;

/**
 * Session Bean implementation class ComplaintUtilities
 */
@Singleton
@LocalBean
@Startup
public class ComplaintUtilities {

	@PersistenceContext
	private EntityManager entityManager;

	public ComplaintUtilities() {

	}

	@PostConstruct
	public void initDb() {
		User complainer = new User();
		complainer.setIdUser(4);
		complainer.setFirstName("hend");
		complainer.setLastName("hend");
		complainer.setEmail("hend@a.a");
		complainer.setPassword("753");
		complainer.setType("GalleryAssistant");
		
		User receiver = new User();
		receiver.setIdUser(2);
		receiver.setFirstName("y");
		receiver.setLastName("yy");
		receiver.setEmail("y@a.a");
		receiver.setPassword("789");
		receiver.setType("User");
		
		Admin admin = new Admin();
		admin.setIdUser(1);
		admin.setFirstName("administrateur");
		admin.setLastName("admin");
		admin.setEmail("naoures.mahfoudh@gmail.com");
		admin.setPassword("123");
		admin.setType("Admin");
		
		
		User complainer3 = new User();
		complainer3.setIdUser(3);
		complainer3.setFirstName("x");
		complainer3.setLastName("x");
		complainer3.setEmail("x@a.a");
		complainer3.setPassword("159");
		complainer3.setType("Admin");
		
		entityManager.persist(admin);
		entityManager.persist(complainer);
		entityManager.persist(receiver);
		entityManager.persist(complainer3);
	}

}
