package nfl.main.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nfl.main.entity.Blog;

@Repository
public class BlogDAOImpl implements BlogDAO {
	
	
	
	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Blog> getBlogs() {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> query = session.createQuery("from Blog", Blog.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Blog> getBlogsWithComments() {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> query = session.createQuery("from Blog", Blog.class);
		for (Blog blog : query.getResultList()) {
			Hibernate.initialize(blog.getComments());
		}
		return query.getResultList();
	}

	@Transactional
	@Override
	public void saveBlog(Blog blog) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(blog);

	}

	@Transactional
	@Override
	public Blog getBlog(int id) {
		Session session = sessionFactory.getCurrentSession();

		Blog blog = session.get(Blog.class, id);

		return blog;
	}

	@Transactional
	@Override
	public Blog getBlogWithTeam(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, id);
		Hibernate.initialize(blog.getTeams());
		return blog;
	}

	@Transactional
	@Override
	public void deleteBlog(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, id);
		session.delete(blog);

	}

	@Transactional
	@Override
	public Blog getBlogWithImages(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, id);
		Hibernate.initialize(blog.getImages());
		return blog;
	}

	@Transactional
	@Override
	public Blog getBlogWithInitialize(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, id);
		Hibernate.initialize(blog.getTeams());
		Hibernate.initialize(blog.getImages());
		return blog;
	}

	@Transactional
	@Override
	public List<Blog> getLastTwelveBlogs() {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> blogs = session.createQuery("SELECT b FROM Blog b WHERE b.enabled = 1 ORDER BY b.id DESC")
				.setMaxResults(12);
		for (Blog blog : blogs.getResultList()) {
			Hibernate.initialize(blog.getComments());
		}
		return blogs.getResultList();
	}

	@Transactional
	@Override
	public List<Blog> getLastThreeBlogs() {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> blogs = session.createQuery("SELECT b FROM Blog b ORDER BY b.id DESC").setMaxResults(3);
		for (Blog blog : blogs.getResultList()) {
			Hibernate.initialize(blog.getComments());
		}
		return blogs.getResultList();
	}

	@Transactional
	@Override
	public void enableBlog(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, id);
		blog.setEnabled(!blog.getEnabled());
		session.saveOrUpdate(blog);

	}

	@Transactional
	@Override
	public void importantBlog(int id) {
		Session session = sessionFactory.getCurrentSession();
		Blog blog = session.get(Blog.class, id);
		blog.setIsImportant(!blog.getIsImportant());
		session.saveOrUpdate(blog);

	}

	@Transactional
	@Override
	public List<Blog> getThreeImportantBlogs() {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> blogs = session
				.createQuery("SELECT b FROM Blog b WHERE b.isImportant = 1 AND b.enabled = 1 ORDER BY b.id DESC")
				.setMaxResults(3);
		for (Blog blog : blogs.getResultList()) {
			Hibernate.initialize(blog.getComments());
		}
		return blogs.getResultList();
	}

	@Transactional
	@Override
	public List<Blog> getThreeBlogsViews() {

		Session session = sessionFactory.getCurrentSession();
		Query<Blog> blogs = session.createQuery("SELECT b FROM Blog b ORDER BY b.numberViews DESC").setMaxResults(3);
		List<Blog> list = blogs.getResultList();

		for (Blog blog : list) {
			Hibernate.initialize(blog.getComments());
		}
		return list;
	}

	@Transactional
	@Override
	public long getBlogsSize() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Blog b WHERE b.enabled = 1");
		return (long) query.uniqueResult();
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerPage(int page) {

		Session session = sessionFactory.getCurrentSession();

		int offset = (12 * page) - 12;

		Query<Blog> query = session.createNativeQuery(
				"SELECT * FROM blog b WHERE b.enabled = 1 ORDER BY b.id DESC LIMIT 12 OFFSET :offset", Blog.class);
		query.setParameter("offset", offset);
		List<Blog> list = query.getResultList();

		for (Blog blog : list) {
			Hibernate.initialize(blog.getComments());
		}
		return list;
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerWord(String word, int page) {
		Session session = sessionFactory.getCurrentSession();

		long offset = (12 * page) - 12;

		Query query = session.createNativeQuery(
				"SELECT * FROM blog b WHERE b.enabled = 1 AND b.title LIKE :word OR b.description LIKE :word ORDER BY b.id DESC LIMIT 12 OFFSET :offset",
				Blog.class);
		query.setParameter("word", "%" + word + "%");
		query.setParameter("offset", offset);
		List<Blog> list = query.getResultList();

		for (Blog blog : list) {
			Hibernate.initialize(blog.getComments());
		}

		return list;
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerCategory(int idCategory, long page) {
		Session session = sessionFactory.getCurrentSession();

		long offset = (12 * page) - 12;

		Query query = session.createNativeQuery(
				"SELECT * FROM blog b WHERE b.enabled = 1 AND b.idCategory = :idCategory ORDER BY b.id DESC LIMIT 12 OFFSET :offset",
				Blog.class);
		query.setParameter("offset", offset);
		query.setParameter("idCategory", idCategory);
		List<Blog> list = query.getResultList();

		for (Blog blog : list) {
			Hibernate.initialize(blog.getComments());
		}
		return list;
	}

	@Transactional
	@Override
	public int getBlogsSizePerCategory(int idCategory) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("SELECT count(*) FROM blog b WHERE b.enabled = 1 AND b.idCategory = :idCategory");
		query.setParameter("idCategory", idCategory);
		return query.uniqueResult().hashCode();
	}

	@Transactional
	@Override
	public int getBlogsSizePerTerm(String term) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(
				"SELECT count(*) FROM blog b WHERE b.enabled = 1 AND b.title LIKE :word OR b.description LIKE :word");
		query.setParameter("word", "%" + term + "%");
		return query.uniqueResult().hashCode();
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerTeam(int idTeam, long page) {
		Session session = sessionFactory.getCurrentSession();

		long offset = (12 * page) - 12;

		Query query2 = session.createNativeQuery(
				"SELECT blog.*\r\n" + "FROM blog_team\r\n" + "right join team on team.id = blog_team.idTeam\r\n"
						+ "right join blog on blog.id = blog_team.idBlog\r\n"
						+ "WHERE blog.enabled = 1 AND team.id = :idTeam ORDER BY blog.id DESC LIMIT 12 OFFSET :offset",
				Blog.class);
		query2.setParameter("offset", offset);
		query2.setParameter("idTeam", idTeam);
		List<Blog> list = query2.getResultList();

		for (Blog blog : list) {
			Hibernate.initialize(blog.getComments());
		}
		return list;
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerAuthor(int idAuthor, long page) {
		Session session = sessionFactory.getCurrentSession();

		long offset = (12 * page) - 12;

		Query query = session.createNativeQuery(
				"SELECT * FROM blog b WHERE b.enabled = 1 AND b.idAuthor = :idAuthor ORDER BY b.id DESC LIMIT 12 OFFSET :offset",
				Blog.class);
		query.setParameter("offset", offset);
		query.setParameter("idAuthor", idAuthor);
		List<Blog> list = query.getResultList();

		for (Blog blog : list) {
			Hibernate.initialize(blog.getComments());
		}
		return list;
	}

	@Transactional
	@Override
	public int getBlogsSizePerAuthor(int idAuthor) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createNativeQuery("SELECT count(*) FROM blog b WHERE b.enabled = 1 AND b.idAuthor = :idAuthor");
		query.setParameter("idAuthor", idAuthor);
		return query.uniqueResult().hashCode();
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerTitle(String title) {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> query = session.createQuery("from Blog b WHERE b.titleURL LIKE :title");
		query.setParameter("title", "%" + title + "%");
		Blog blog = query.getSingleResult();

		Query<Blog> query2 = session.createQuery(
				"from Blog b WHERE b.id = (select min(b.id) FROM Blog b WHERE b.id > :id AND b.author.id = :ida)");
		query2.setParameter("id", blog.getId());
		query2.setParameter("ida", blog.getAuthor().getId());

		Query<Blog> query3 = session.createQuery(
				"from Blog b WHERE b.id = (select max(b.id) FROM Blog b WHERE b.id < :id AND b.author.id = :ida)");
		query3.setParameter("id", blog.getId());
		query3.setParameter("ida", blog.getAuthor().getId());

		List<Blog> list = new ArrayList<>();
		try {
			list.add(query2.getSingleResult());
		} catch (NoResultException e) {
			list.add(query.getSingleResult());
		}

		list.add(query.getSingleResult());

		try {
			list.add(query3.getSingleResult());
		} catch (NoResultException e) {
			list.add(query.getSingleResult());
		}

		Hibernate.initialize(query.getSingleResult().getComments());
		Hibernate.initialize(query.getSingleResult().getImages());
		Hibernate.initialize(query.getSingleResult().getTeams());
		query.getSingleResult().getImages().remove(query.getSingleResult().getImage());
		return list;
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerStatus(boolean enabled) {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> query = session.createQuery("from Blog b WHERE b.enabled =:enabled");
		query.setParameter("enabled", enabled);

		List<Blog> blogs = query.getResultList();

		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getComments());
		}
		return blogs;
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerSearchTitle(String title) {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> query = session.createQuery("from Blog b WHERE b.title LIKE CONCAT('%',:title,'%')");
		query.setParameter("title", title);

		List<Blog> blogs = query.getResultList();

		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getComments());
		}
		return blogs;
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerSearchAuthor(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> query = session.createQuery("from Blog b WHERE b.author.id =:id");
		query.setParameter("id", id);

		List<Blog> blogs = query.getResultList();

		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getComments());
		}
		return blogs;
	}

	@Transactional
	@Override
	public List<Blog> getBlogsPerSearchCategory(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query<Blog> query = session.createQuery("from Blog b WHERE b.category.id =:id");
		query.setParameter("id", id);

		List<Blog> blogs = query.getResultList();

		for (Blog blog : blogs) {
			Hibernate.initialize(blog.getComments());
		}
		return blogs;
	}

}
