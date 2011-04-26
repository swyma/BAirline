package DAO;

//�༭�ߣ��Ž�ƽ
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import PO.Flightcompany;
import PO.Membership;;



public interface ImembershipDAO {
	

	
	// ��ѯ������Ա��Ϣ
	public List QueryAll(int start, int limit);

	// ��ȡ��¼����
	public int QueryCount();

	

	//���¼�¼
	public boolean update(String hql);

	//ɾ���¼
	public boolean delete(String hql);

	//��ѯ��¼
	public List Querymembership(String hql);

	//��ѯ��Ӧ����
	public int QueryCount(String hql);
	
	
}


