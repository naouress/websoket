package tests;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import artRoom.entities.Gallery;
import artRoom.persistence.service.GalleryServiceRemote;



public class TestFindGallery {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		GalleryServiceRemote galleryServicesRemote = (GalleryServiceRemote) context.lookup(
				"showroom-ear/showroom-ejb/GalleryService!artRoom.persistence.service.GalleryServiceRemote");

		Gallery galler=galleryServicesRemote.findGalleryById(1);
	System.out.println(galler.getAddress());
	}

}
