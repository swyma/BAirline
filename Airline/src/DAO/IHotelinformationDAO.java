package DAO;

//�༭�ߣ�������
import java.util.List;

import PO.Hotelinformation;

public interface IHotelinformationDAO {
	
	public boolean checkName(String hql);
	public String checkCity(String hql);
	
	// ��ѯ���оƵ���Ϣ
	public List QueryAll(int start, int limit);

	// ��ȡ��¼����
	public int QueryCount();

	//�����¼
	public boolean add(Hotelinformation hotelinformation);

	//���¼�¼
	public boolean update(String hql);

	//ɾ����¼
	public boolean delete(String hql);

	//��ѯ��¼
	public List QueryHotelinformation(String hql);

	//��ѯ��Ӧ����
	public int QueryCount(String hql);
	
	
}


