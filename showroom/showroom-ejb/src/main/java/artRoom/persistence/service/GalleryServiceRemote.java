package artRoom.persistence.service;

import java.util.List;

import javax.ejb.Remote;

import artRoom.entities.Gallery;

@Remote
public interface GalleryServiceRemote {
	

	void addGallery(Gallery gallery);

	Gallery findGalleryById(Integer id);


	void deleteGalleryById(Integer id);

	void deleteGallery(Gallery gallery);

	void updateGallery(Gallery gallery);

	List<Gallery> findAllGallery();
}
