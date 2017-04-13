package TestComplaint;

import showroom.persistence.service.SendMail;

public class TestMail {
	
	public static void main(String[] args) {
		SendMail test = new SendMail();
		test.envoyer("ben.mahfoudh.naoures@gmail.com","http192.168CISCO","test2","tu a reussi!");
		}

}
