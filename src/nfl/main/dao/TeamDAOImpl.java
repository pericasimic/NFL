package nfl.main.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import nfl.main.entity.Blog;
import nfl.main.entity.Team;

@Repository
public class TeamDAOImpl implements TeamDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Team> getTeams() {
		Session session = sessionFactory.getCurrentSession();
		Query<Team> query = session.createQuery("from Team", Team.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public Team getTeam(int id) {
		Session session = sessionFactory.getCurrentSession();
		Team team = session.get(Team.class, id);
		return team;
	}

	@Transactional
	@Override
	public void saveTeam(Team team) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(team);
	}

	@Transactional
	@Override
	public void deleteTeam(int id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Team WHERE id=:id");
		query.setParameter("id", id);
		query.executeUpdate();
	}

	@Transactional
	@Override
	public List<Team> getTeamsById(List<Integer> ids) {
		Session session = sessionFactory.getCurrentSession();
		Query<Team> query = session.createQuery("select t from Team t where t.id IN (:ids)");
		query.setParameter("ids", ids);
		return query.getResultList();
	}

	@Transactional
	@Override
	public List<Team> getTeamsOrderByBlog() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(
				"SELECT team.*\r\n"
				+ "FROM blog_team\r\n"
				+ "left join Team on team.id = blog_team.idTeam\r\n"
				+ "group by team.name\r\n"
				+ "order by count(team.id) desc\r\n"
				+ "", Team.class);

		return query.getResultList();
	}

	@Transactional
	@Override
	public Team getTeamByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query<Team> query = session.createQuery("from Team t where t.name =:name");
		query.setParameter("name", name);
		Team team = query.getSingleResult();
		Hibernate.initialize(team.getBlogs());
        for(Blog b: team.getBlogs()) {
        	Hibernate.initialize(b.getComments());
        }
		return team;
	}

	@Transactional
	@Override
	public Team getTeamWithBlogs(int id) {
		Session session = sessionFactory.getCurrentSession();
		Team team = session.get(Team.class, id);
		Hibernate.initialize(team.getBlogs());
		for(Blog b: team.getBlogs()) {
        	Hibernate.initialize(b.getComments());
        }
		return team;
	}

}
