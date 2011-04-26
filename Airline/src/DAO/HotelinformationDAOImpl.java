/**
 *管理员表-- 李招意
 */
package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;



import HbmFactory.HibernateSessionFactory;
import PO.Hotelinformation;

public class HotelinformationDAOImpl implements IHotelinformationDAO {
	// 查询酒店名是否存在
	public boolean checkName(String hql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			Integer a = Integer.parseInt(query.list().get(0).toString());
			if (a == 0) {
				return true;
			}
		} catch (RuntimeException re) {
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return false;
	}

	// 根据酒店所在城市查询是否存在
	public String checkCity(String hql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			if (query != null) {
				return query.list().get(0).toString();
			}
		} catch (RuntimeException re) {
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}

		return null;
	}
	
	public List QueryAll(int start, int limit) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
	/*	try {*/

			session.beginTransaction();
			String sql = "SELECT new map(p.hotelAutoid as hotelAutoid,p.hotelName as hotelName,p.hotelLevel as hotelLevel,"
					+ "p.hotelTel as hotelTel,p.hotelCity as hotelCity,p.hotelAddress as hotelAddress,"
					+ "p.hotelPage as hotelPage,p.hotelPicture as hotelPicture)"
					+ " FROM Hotelinformation p WHERE p.flag=1";

			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return query.setFirstResult(start).setMaxResults(limit).list();
			}
		/*} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}*/
		return null;
	}

	public int QueryCount() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "select count(p.hotelAutoid) from Hotelinformation p where p.flag=1";

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

	public boolean add(Hotelinformation hotelinformation) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(hotelinformation);
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

		} catch (RuntimeException re) { // 回滚
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

		} catch (RuntimeException re) { // 回滚
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}

	}

	public List QueryHotelinformation(String hql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();

			Query query = session.createQuery(hql);
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

	public int QueryCount(String hql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return 0;
	}

}
