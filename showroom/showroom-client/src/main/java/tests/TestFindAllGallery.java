package tests;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import artRoom.entities.Gallery;
import artRoom.persistence.service.GalleryServiceRemote;

public class TestFindAllGallery {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		GalleryServiceRemote galleryServicesRemote = (GalleryServiceRemote) context
				.lookup("showroom-ear/showroom-ejb/GalleryService!artRoom.persistence.service.GalleryServiceRemote");

		List<Gallery> gallery = galleryServicesRemote.findAllGallery();
		for (Gallery g : gallery) {

			System.out.println(g.getAddress());
		}
	}

}
