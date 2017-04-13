package tests;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import artRoom.entities.Gallery;
import artRoom.persistence.service.GalleryServiceRemote;



public class TestAddGallery {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		GalleryServiceRemote galleryServicesRemote = (GalleryServiceRemote) context.lookup(
				"showroom-ear/showroom-ejb/GalleryService!artRoom.persistence.service.GalleryServiceRemote");

		Gallery gallery= new Gallery();
		galleryServicesRemote.addGallery(gallery);
		System.out.println("aaaaaaaaaa");
	}

}
