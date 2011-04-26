package DAO;

//�༭�ߣ�¬ΰ��
import java.util.List;

import PO.Airtype;

//�ɻ�������Ϣ�ӿ�
public interface IAirtypeDAO {
	// ��ѯ���зɻ�������Ϣ
	public List QueryAll(int start, int limit);

	//��ѯ��Ӧ�ķɻ�������Ϣ
	public List QueryInf(String sql);
	
	// ��ȡ��¼����
	public int QueryCount();
	
	//��ȡ��Ӧ�ļ�¼��������ҳ��
	public int QueryCounts(String sql);
	
	//�����¼
	public boolean add(Airtype airtype);
	
	//���¼�¼
	public boolean update(String hql);
	
	//ɾ����¼
	public boolean delete(String hql);
	
}
