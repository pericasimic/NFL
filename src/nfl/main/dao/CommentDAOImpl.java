package nfl.main.dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import nfl.main.entity.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Comment> getComments() {
		Session session = sessionFactory.getCurrentSession();

		Query<Comment> query = session.createQuery("SELECT c FROM Comment c ORDER BY c.id DESC", Comment.class);

		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Comment> getCommentsPerBlogID(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Comment> query = session
				.createNativeQuery("SELECT * FROM Comment c WHERE c.idBlog = :id ORDER BY c.id DESC", Comment.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Transactional
	@Override
	public void enableComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = session.get(Comment.class, id);
		comment.setEnabled(!comment.getEnabled());
		session.saveOrUpdate(comment);

	}

	@Transactional
	@Override
	public List<Comment> getCommentsPerBlogIDEnabled(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Comment> query = session.createNativeQuery(
				"SELECT * FROM Comment c WHERE c.enabled = 1 AND c.idBlog = :id ORDER BY c.id DESC", Comment.class);
		query.setParameter("id", id);
		return query.getResultList();
	}

	@Transactional
	@Override
	public Comment getComment(int id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment = session.get(Comment.class, id);
		return comment;
	}

	@Transactional
	@Override
	public void saveComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		comment.setDate(dtf.format(now));
		
		session.saveOrUpdate(comment);

	}

}
