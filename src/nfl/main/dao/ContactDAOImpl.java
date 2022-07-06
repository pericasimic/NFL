package nfl.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import nfl.main.entity.Contact;

@Repository
public class ContactDAOImpl implements ContactDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Contact> getContacts() {
		Session session = sessionFactory.getCurrentSession();
		Query<Contact> query = session.createQuery("from Contact", Contact.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void deleteContact(int id) {
		Session session = sessionFactory.getCurrentSession();
		Contact contact = session.get(Contact.class, id);
		session.delete(contact);
		
	}

	@Transactional
	@Override
	public void seenContact(int id) {
		Session session = sessionFactory.getCurrentSession();
		Contact contact = session.get(Contact.class, id);
		contact.setSeen(true);
		session.saveOrUpdate(contact);
		
	}

	@Transactional
	@Override
	public Contact getContact(int id) {
		Session session = sessionFactory.getCurrentSession();
		Contact contact = session.get(Contact.class, id);
		return contact;
	}

	@Transactional
	@Override
	public void saveContact(Contact contact) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(contact);
		
	}

}
