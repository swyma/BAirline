/*==============*/
/* 编辑者：叶茂安 */
/*============*/

package DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import HbmFactory.HibernateSessionFactory;
import PO.Flightinformation;

public class FlightinformationDAOImpl implements IFlightinformationDAO {

	// 查询全部
	public List QueryAll(int start, int limit) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "select new map(" + "p.fliAutoid as fliAutoid,"
					+ "p.comCode as comCode," + "p.airCode as airCode,"
					+ "p.fliEveryday as fliEveryday," + "p.fliNo as fliNo,"
					+ "p.fliDiscount as fliDiscount,"
					+ "p.fliBaddress as fliBaddress,"
					+ "p.fliAaddress as fliAaddress,"
					+ "SUBSTRING(p.fliBtime,12,5) as fliBtime,"
					+ "SUBSTRING(p.fliAtime,12,5) as fliAtime,"
					+ "p.fliFnumber as fliFnumber,"
					+ "p.fliCnumber as fliCnumber,"
					+ "p.fliYnumber as fliYnumber," + "p.fliFfare as fliFfare,"
					+ "p.fliCfare as fliCfare," + "p.fliYfare as fliYfare,"
					+ "p.fliRefundtime as fliRefundtime,"
					+ "p.fliRefund as fliRefund) "
					+ "from Flightinformation p " + "where flag=1";
			Query query = session.createQuery(sql);

			session.getTransaction().commit();
			if (query != null) {
				List list = query.setFirstResult(start).setMaxResults(limit)
						.list();
				return list;
			}
		} catch (RuntimeException re) {
			session.beginTransaction().rollback();
			throw re;
		} finally {
			session.close();
		}
		return null;
	}

	// 取得总数
	public int QueryCount() {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String sql = "select count(p.fliAutoid) from Flightinformation p where p.flag=1";
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

	// 添加信息
	public boolean add(Flightinformation flightinfomation) {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			session.save(flightinfomation);
			session.getTransaction().commit();
			return true;

		} catch (RuntimeException re) {
			session.beginTransaction().rollback();
			return false;
		} finally {
			session.close();
		}

	}

	// 更新信息
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

	// 删除信息
	public boolean delete(String hql) {
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

	// 判断是否可以插入与更新
	public boolean addornot(String hql) {
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

	public List QueryInf(String sql) {
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

	// 获取相应的记录总数（分页）
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

	// 查询所有的航空代码
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

	// 取得所有的机型信息
	public List GetairType() {
		Session session = HibernateSessionFactory.getSession();
		try {
			session.beginTransaction();
			String hql = "select distinct(p.airCode) as airCode  from Airtype p where p.flag=1";
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

	public int GetNumber(String sql) {
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

	/* ----杨海亮  修改   2010 4 10   */
	//取得航班信息在客户订票信息中显示
	public  List fliInformationShow(String sql){
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
}
