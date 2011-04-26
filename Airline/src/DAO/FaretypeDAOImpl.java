package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import HbmFactory.HibernateSessionFactory;
import PO.Faretype;

public class FaretypeDAOImpl implements IFaretypeDAO {

	public List queryall() {
		// TODO Auto-generated method stub
        Session session = HibernateSessionFactory.getSession();
        session.beginTransaction();
        String sql = "select new map(p.farComcode as farComcode,p.farGoldscore as farGoldscore,p.farGolddiscount as farGolddiscount,p.farSilscore as farSilscore,p.farSildiscount as farSildiscount,p.farBroscore as farBroscore,p.farBrodiscount as farBrodiscount) from Faretype p  ";
        Query query = session.createQuery(sql);
        session.getTransaction().commit();
        if (query != null) {
            return query.list();
        }
		return null;
	}

	public boolean update(String hql) {
		// TODO Auto-generated method stub
        Session session = HibernateSessionFactory.getSession();
        try
        {
        session.beginTransaction();
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.getTransaction().commit();
        return true;
        }catch(Exception e)
        {
        	session.beginTransaction().rollback();
        	return false;
        }
        finally{
        	session.close();
        }
	}
	
	public boolean add(Faretype fare) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
       // try
      //  {
        session.beginTransaction();
        session.save(fare);
        session.getTransaction().commit();
        return true;
       /* }catch(RuntimeException e)
        {
        	session.beginTransaction().rollback();
        	return false;
        }
        finally{
        	session.close();
        }*/
	}
	public boolean del(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		/*try {*/
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
/*
		} catch (RuntimeException re) { // 回滚
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}*/

	}
	public List QueryFaretype(String hql) {
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
	public int QueryCount() {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "select count(p.farComcode) from Faretype p ";

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

	public List GetcomCode() {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String hql = "select distinct(p.comCode) from Flightcompany p where p.flag=1";
			Query query = session.createQuery(hql);
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

}


