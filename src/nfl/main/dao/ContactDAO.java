package nfl.main.dao;

import java.util.List;

import nfl.main.entity.Contact;

public interface ContactDAO {

	public List<Contact> getContacts();

	public void deleteContact(int id);

	public void seenContact(int id);
	
	public Contact getContact(int id);
	
	public void saveContact(Contact contact);

}
