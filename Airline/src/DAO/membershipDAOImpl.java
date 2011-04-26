/**
 *留言板--张健平
 */
package DAO;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import HbmFactory.HibernateSessionFactory;
import PO.Membership;;


public class membershipDAOImpl implements ImembershipDAO {
	
	public List QueryAll(int start, int limit) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
		/*try {*/
			session.beginTransaction();
			/*
			 * 	name:'customer_autoid',
						},{
							name : 'customer_id'
						}, {
							name : 'customer_name'
						}, {
							name : 'comment_content'
						}, {
							name : 'comment_time'
						}, {
							name : 'customer_IP'
			 */
			String sql = "SELECT new map(p.commentAutoid as customer_autoid,p.customerId as customer_id,p.customerName as customer_name,"
					+"p.commentContent as comment_content,substring(p.commentTime,1,19) as comment_time,p.commentIp as customer_IP)"
			+"FROM Membership p where p.flag=1";
             
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
			String sql = "select count(p.commentAutoid) from Membership p where p.flag=1";

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
	

	public boolean update(String hql) {
		// TODO Auto-generated method stub
		Session session = HibernateSessionFactory.getSession();
	/*	try {*/
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;

		/*} catch (RuntimeException re) { // 回滚
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
*/
	}

	public boolean delete(String hql) {
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

	public List Querymembership(String hql) {
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
