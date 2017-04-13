package artRoom.persistence.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import artRoom.entities.Gallery;

/**
 * Session Bean implementation class GalleryService
 */
@Stateless

public class GalleryService implements GalleryServiceRemote, GalleryServiceLocal {

	@PersistenceContext
	private EntityManager entityManager;
    public GalleryService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addGallery(Gallery gallery) {
		entityManager.persist(gallery);
		
	}

	@Override
	public Gallery findGalleryById(Integer id) {
		// TODO Auto-generated method stub
		return entityManager.find(Gallery.class, id);
	}



	@Override
	public void deleteGalleryById(Integer id) {
		entityManager.remove(findGalleryById(id));
		
	}

	@Override
	public void deleteGallery(Gallery gallery) {
		entityManager.remove(entityManager.merge(gallery));
		
	}

	@Override
	public void updateGallery(Gallery gallery) {
		entityManager.merge(gallery);
		
	}

	@Override
	public List<Gallery> findAllGallery() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("select g from Gallery g").getResultList();
	}

}
