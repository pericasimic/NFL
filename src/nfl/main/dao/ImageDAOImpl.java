package nfl.main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nfl.main.entity.Image;

@Repository
public class ImageDAOImpl implements ImageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public void saveImage(Image image) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(image);

	}

	@Transactional
	@Override
	public List<Image> getImagesPerBlogId(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Image> query = session.createQuery("from Image i where i.blog.id = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

}
