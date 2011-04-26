package DAO;
//�༭�ߣ����
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import HbmFactory.HibernateSessionFactory;
import PO.Bookinformation;

public class BookinformationDAOImpl implements IBookinformationDAO {
	// ��ѯ���ж�Ʊ��Ϣ
	public List QueryAll(int start, int limit) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			/**********************
			String sql = "SELECT new map(p.booAutoid as booAutoid,p.cusId as cusId,p.comCode as comCode,"
					+ "p.booEveryday as booEveryday,p.booNo as booNo,p.booBaddress as booBaddress,p.booAaddress as booAaddress,"
					+ "p.booBtime as booBtime,p.booAtime as booAtime,p.booBerth as booBerth,"
					+ "p.booNumber as booNumber,p.booTime as booTime,p.booFare as booFare,"
					+ "p.flagPay as flagPay,p.flagPass as flagPass) "
					+ "FROM Bookinformation p where p.flagPass=0 order by p.booTime desc";
            ****************************/
			
			String sql = "SELECT new map(p.booAutoid as booAutoid,p.cusId as cusId,p.comCode as comCode,"
				+ "p.booEveryday as booEveryday,p.booNo as booNo,p.booBaddress as booBaddress,p.booAaddress as booAaddress,"
				+ "SUBSTRING(p.booBtime,12,5) as booBtime,SUBSTRING(p.booAtime,12,5) as booAtime,p.booBerth as booBerth,"
				+ "p.booNumber as booNumber,SUBSTRING(p.booTime,1,19) as booTime,p.booFare as booFare,"
				+ "p.flagPay as flagPay,p.flagPass as flagPass) "
				+ "FROM Bookinformation p where p.flagPass=0 order by p.booTime desc";
			
			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return query.setFirstResult(start).setMaxResults(limit).list();
			}
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return null;
	}

	// ��ȡ��¼����
	public int QueryCount() {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
		//	String sql = "select count(p.booAutoid) from Bookinformation p where p.flagPass=0";
            String sql="select count(p.booAutoid) from Bookinformation p";
			Query query = session.createQuery(sql);
			session.getTransaction().commit();
			if (query != null) {
				return Integer.parseInt(query.list().get(0).toString());
			}
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}

		return 0;
	}

	// �����¼
	public boolean add(Bookinformation bookinformation) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(bookinformation);
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	// ���¼�¼
	public boolean update(String hql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	// ɾ����¼
	public boolean delete(String hql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	// ��ú��չ�˾����
	public List search_comName(String hql) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			if (query != null) {
				return query.list();
			}
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return null;
	}
	
//��ö�Ʊ�ͻ������֤����
	public List search_cusId(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			if (query != null) {
				return query.list();
			}
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return null;
	}
	
	//����ض��ͻ���Ϣ
	public List search_information(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			if (query != null) {
				return query.list();
			}
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return null;
	}
	

	
	//��ö�������λ��������
	public int Booking_FCY_Number(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			int booking_fcy_number=query.executeUpdate();
			session.getTransaction().commit();
			if(booking_fcy_number!=0){
				return booking_fcy_number;
			}
		} catch (RuntimeException re) {
			// �ع�
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return 0;
	}


	
	//������Ϣ�޸�
	public boolean individual_update(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) { // �ع�
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	//�����޸�
	public boolean password_update(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			query.executeUpdate();
			session.getTransaction().commit();
			return true;
		} catch (RuntimeException re) { // �ع�
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}
	
	//ȡ����Ӧ������ۿ�
	public double get_fliDiscount(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			if (query != null) {
				//return Integer.parseInt(query.list().get(0).toString());
				return Double.parseDouble(query.list().get(0).toString());
			}
		} catch (RuntimeException re) {
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return 0;
	}
	
	//ȡ��FCY����λ�ļ۸�
	public double getflifare(String hql){
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(hql);
			session.getTransaction().commit();
			if (query != null) {
			//	return Integer.parseInt(query.list().get(0).toString());
				return Double.parseDouble(query.list().get(0).toString());
			}
	  } catch (RuntimeException re) {
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return 0;
	}
	
}
