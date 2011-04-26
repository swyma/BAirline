package DAO;

//编辑者：周永丰
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import HbmFactory.HibernateSessionFactory;
import PO.Flightcompany;

//航空公司信息实现类
public class FlightcompanyDAOImpl implements IFlightcompanyDAO {

	public List QueryAll(int start, int limit) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "SELECT new map(p.comAutoid as comAutoid,p.comCode as comCode,p.comName as comName,"
					+ "p.comAddress as comAddress,p.comRegister as comRegister,"
					+ "p.comInformation as comInformation) "
					+ "FROM Flightcompany p WHERE p.flag=1 order by p.comRegister desc";

			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return query.setFirstResult(start).setMaxResults(limit).list();
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

	public int QueryCount() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "select count(p.comAutoid) from Flightcompany p where p.flag=1";

			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return Integer.parseInt(query.list().get(0).toString());
			}
		} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}

		return 0;
	}

	public boolean add(Flightcompany flightcompany) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(flightcompany);
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean update(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);  
			query.executeUpdate(); 
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	public boolean delete(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);  
			query.executeUpdate(); 
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

}
