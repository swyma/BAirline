package DAO;

//编辑者：卢伟灵
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import HbmFactory.HibernateSessionFactory;
import PO.Airtype;

//飞机机型信息实现类
public class AirtypeDAOImpl implements IAirtypeDAO {

	public List QueryAll(int start, int limit) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "SELECT new map(p.airAutoid as airAutoid,p.airCode as airCode,"
					+ "p.airF as airF,p.airFname as airFname,p.airFnumber as airFnumber,"
					+ "p.airC as airC,p.airCname as airCname,p.airCnumber as airCnumber,"
					+ "p.airY as airY,p.airYname as airYname,p.airYnumber as airYnumber,"
					+ "p.airTotalnumber as airTotalnumber)"
					+ "FROM Airtype p WHERE p.flag=1";

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
    
	//取得总数
	public int QueryCount() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "select count(p.airAutoid) from Airtype p where p.flag=1";

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
	
	//查询相应的飞机机型信息
	public List QueryInf(String sql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return query.list();
			}
		} catch (RuntimeException re) {
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return null;
	}
	
	//获取相应的记录总数（分页）
	public int QueryCounts(String sql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return Integer.parseInt(query.list().get(0).toString());
			}
		} catch (RuntimeException re) {
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return 0;
	}
	

	public boolean add(Airtype airtype) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(airtype);
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
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
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
