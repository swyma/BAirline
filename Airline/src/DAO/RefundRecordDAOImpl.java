package DAO;
//编辑者：杨海亮
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import HbmFactory.HibernateSessionFactory;
import PO.Refundrecord;

public class RefundRecordDAOImpl implements IRefundRecordDAO{
	//插入记录
	public boolean add(Refundrecord refundrecord){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(refundrecord);
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
	
	//更新记录
	public boolean update(String hql){
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
	
	//删除记录
	public boolean delete(String hql){
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
	//是否有退票
	public boolean refundOrnot(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			Integer a=Integer.parseInt(query.list().get(0).toString());
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
	
	//取出退票表中的相关信息
	public List get_refundInfo(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
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
