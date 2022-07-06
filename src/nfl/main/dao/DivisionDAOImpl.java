package nfl.main.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import nfl.main.entity.Division;

@Repository
public class DivisionDAOImpl implements DivisionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	@Override
	public List<Division> getDivisions() {
		Session session = sessionFactory.getCurrentSession();
		Query<Division> query = session.createQuery("from Division ORDER BY numOrder ASC", Division.class);
		return query.getResultList();
	}

	@Transactional
	@Override
	public Division getDivision(int id) {
		Session session = sessionFactory.getCurrentSession();
		Division division = session.get(Division.class, id);
		Hibernate.initialize(division.getTeams());
		return division;
	}

	@Transactional
	@Override
	public void saveDivision(Division division) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(division);
	}

	@Transactional
	@Override
	public void deleteDivision(int id) {
		Session session = sessionFactory.getCurrentSession();
		Division division = session.get(Division.class, id);
		session.delete(division);

	}

	@Transactional
	@Override
	public List<Division> getDivisionsPerNumOrder() {
		Session session = sessionFactory.getCurrentSession();
		Query<Division> query = session.createQuery("SELECT d from Division d WHERE d.enabled = 1 ORDER BY numOrder ASC", Division.class);
		return query.getResultList();
	}
	
	@Transactional
	@Override
	public void enableDivision(int id) {
		Session session = sessionFactory.getCurrentSession();
		Division division = session.get(Division.class, id);
		division.setEnabled(!division.getEnabled());
		session.saveOrUpdate(division);
		
	}

}
