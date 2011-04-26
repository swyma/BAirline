package DAO;
//�༭�ߣ����

import java.util.List;

import PO.Bookinformation;

public interface IBookinformationDAO {
	// ��ѯ���ж�Ʊ��Ϣ
	public List QueryAll(int start, int limit);

	// ��ȡ��¼����
	public int QueryCount();
	
	//�����¼
	public boolean add(Bookinformation flightcompany);
	
	//���¼�¼
	public boolean update(String hql);
	
	//ɾ����¼
	public boolean delete(String hql);
	
	//��ú��չ�˾����
	public List search_comName(String hql);
	
	//��ö�Ʊ�ͻ������֤����
	public List search_cusId(String hql); 
	
	// ����ض������Ŀͻ���Ϣ
	public List search_information(String hql);
	

	//��ö�������λ��������
	public int Booking_FCY_Number(String hql);
    
	
	//������Ϣ�޸�
	public boolean individual_update(String hql);
	
	//�����޸�
	public boolean password_update(String hql);
	
	
	//ȡ����Ӧ������ۿ�
	public double get_fliDiscount(String hql);
	
	//ȡ��FCY����λ�ļ۸�
	public double getflifare(String hql);
	
	
}
