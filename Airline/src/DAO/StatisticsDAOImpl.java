package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import HbmFactory.HibernateSessionFactory;

public class StatisticsDAOImpl implements IStatisticsDAO {

	public List QueryMonth() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "select new map(day(booTime)as name,count(flagPass) as visits) "
					+ "from Bookinformation " + "where flagPass=0 and month(now())=month(booTime) "
					+ "group by day(booTime)";

			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return query.list();
			}
		} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return null;
	}

	public List QueryYear() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "select new map(month(booTime)as name,count(flagPass) as visits) "
					+ "from Bookinformation " + "where flagPass=0 and year(now())=year(booTime)"
					+ "group by month(booTime)";

			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return query.list();
			}
		} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return null;
	}

}
