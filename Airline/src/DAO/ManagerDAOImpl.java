/**
 *管理员表-- 李招意
 */
package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;



import HbmFactory.HibernateSessionFactory;
import PO.Manager;

public class ManagerDAOImpl implements IManagerDAO {
	// 查询用户名是否存在
	public boolean checkName(String hql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			Integer a = Integer.parseInt(query.list().get(0).toString());
			System.out.println(a);
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

	// 根据用户名查询密码是否匹配
	public String checkPwd(String hql) {
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
		try {
			session.beginTransaction();
			String sql = "SELECT new map(p.manAutoid as manAutoid,p.manAccount as manAccount,p.manPwd as manPwd,"
					+ "p.manId as manId,p.manSex as manSex,p.manTelnumber as manTelnumber,"
					+ "p.manEmail as manEmail,p.manRegister as manRegister)"
					+ "FROM Manager p WHERE p.flag=1 order by p.manRegister desc";

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
			String sql = "select count(p.manAutoid) from Manager p where p.flag=1";

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

	public boolean add(Manager manager) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		/*try {*/
			session.beginTransaction();
			session.save(manager);
			session.getTransaction().commit();
			return true;
		/*} catch (RuntimeException re) {
			// 回滚
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}*/
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

	public List QueryManager(String hql) {
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
