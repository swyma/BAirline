package DAO;

//�༭�ߣ�������
import java.util.List;

import PO.Flightcompany;
import PO.Manager;



public interface IManagerDAO {
	
	public boolean checkName(String hql);
	public String checkPwd(String hql);
	//public boolean checkCode(String hql);
	
	// ��ѯ������Ա��Ϣ
	public List QueryAll(int start, int limit);

	// ��ȡ��¼����
	public int QueryCount();

	//�����¼
	public boolean add(Manager manager);

	//���¼�¼
	public boolean update(String hql);

	//ɾ����¼
	public boolean delete(String hql);

	//��ѯ��¼
	public List QueryManager(String hql);

	//��ѯ��Ӧ����
	public int QueryCount(String hql);
	
	
}


