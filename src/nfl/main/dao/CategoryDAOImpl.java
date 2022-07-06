package nfl.main.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import nfl.main.entity.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Category> getCategories() {

		Session session = sessionFactory.getCurrentSession();

		Query<Category> query = session.createQuery("from Category", Category.class);

		return query.getResultList();
	}

	@Transactional
	@Override
	public void saveCategory(Category category) {
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(category);

	}

	@Transactional
	@Override
	public Category getCategory(int id) {
		Session session = sessionFactory.getCurrentSession();

		Category category = session.get(Category.class, id);

		return category;
	}

	@Transactional
	@Override
	public void deleteCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Category where id=:id");
		query.setParameter("id", id);
		query.executeUpdate();

	}

	@Transactional
	@Override
	public Category getCategoryByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("from Category c where c.name =:name");
		query.setParameter("name", name);
		return query.getSingleResult();
	}

	@Transactional
	@Override
	public List<Category> getCategoriesPerNumOrder() {
		Session session = sessionFactory.getCurrentSession();

		Query<Category> query = session.createQuery("from Category ORDER BY numOrder ASC", Category.class);

		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Category> getFourCategoriesPerNumOrder() {
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("from Category ORDER BY numOrder").setMaxResults(4);
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Category> getCategoriesWithBlogs() {
		Session session = sessionFactory.getCurrentSession();
		Query<Category> query = session.createQuery("from Category ORDER BY numOrder");

		for (Category category : query.getResultList()) {
			Hibernate.initialize(category.getBlogs());
		}
		return query.getResultList();
		
	}
}
