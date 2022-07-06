package nfl.main.dao;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.AbstractUriTemplateHandler;

import nfl.main.entity.Author;
import nfl.main.entity.Role;

@Repository
public class AuthorDAOImpl implements AuthorDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Author> getAuthors() {
		Session session = sessionFactory.getCurrentSession();
		Query<Author> query = session.createQuery("from users", Author.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public Author getAuthor(int id) {
		Session session = sessionFactory.getCurrentSession();
		Author author = session.get(Author.class, id);
		return author;
	}

	@Transactional
	@Override
	public void saveAuthor(Author author) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(author);
	}

	@Transactional
	@Override
	public void deleteAuthor(int id) {
		Session session = sessionFactory.getCurrentSession();
		Author author = session.get(Author.class, id);
		session.delete(author);

	}

	@Transactional
	@Override
	public void enableAuthor(int id) {
		Session session = sessionFactory.getCurrentSession();
		Author author = session.get(Author.class, id);
		author.setEnabled(!author.getEnabled());
		session.saveOrUpdate(author);

	}

	@Transactional
	@Override
	public Author getAuthorPerNameAndLastname(String name, String lastname) {
		Session session = sessionFactory.getCurrentSession();
		Query<Author> query = session.createQuery("from users u WHERE u.name =:name AND u.lastname =:lastname");
		query.setParameter("name", name);
		query.setParameter("lastname", lastname);

		return query.getSingleResult();

	}

	@Transactional
	@Override
	public Author getAuthorPerUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<Author> query = session.createQuery("from users u where u.username =:username");
		query.setParameter("username", username);
		try {
			return query.getSingleResult();
		} catch (NoResultException no) {
			no.printStackTrace();
		}
			return null;
		}
		
	

	@Transactional
	@Override
	public List<Role> getRoles() {
		Session session = sessionFactory.getCurrentSession();
		Query<Role> query = session.createQuery("from roles", Role.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void importUsername(Author author) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery("UPDATE authorities a SET username = :username WHERE idAuthor = :id");
		query.setParameter("username", author.getUsername());
		query.setParameter("id", author.getId());
		query.executeUpdate();

	}

	@Transactional
	@Override
	public void editRoles(Author author) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery("DELETE FROM `nflblog`.`authorities` WHERE `idAuthor`=:id");
		query.setParameter("id", author.getId());

		query.executeUpdate();

		for (Role r : author.getAuthorities()) {
			if (!r.getAuthority().isEmpty()) {
				query = session.createNativeQuery(
						"INSERT INTO `nflblog`.`authorities` (`username`, `authority`, `idAuthor`) VALUES (:username, :role, :id)");
				query.setParameter("username", author.getUsername());
				query.setParameter("role", r.getAuthority());
				query.setParameter("id", author.getId());
				query.executeUpdate();
			}
		}

	}

}
