package DAO;

//�༭�ߣ�������
import java.util.List;

import PO.Flightcompany;

//���չ�˾��Ϣ�ӿ�
public interface IFlightcompanyDAO {
	// ��ѯ���к��չ�˾��Ϣ
	public List QueryAll(int start, int limit);

	// ��ȡ��¼����
	public int QueryCount();
	
	//�����¼
	public boolean add(Flightcompany flightcompany);
	
	//���¼�¼
	public boolean update(String hql);
	
	//ɾ����¼
	public boolean delete(String hql);
}
