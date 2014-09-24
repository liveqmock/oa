/*
 * �������� 2005-8-19
 */
package com.icss.common.cache.intendwork;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.icss.common.cache.CacheManager;
import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.filelog.FileLogFactory;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.intendwork.dao.OfficePendingDAO;
import com.icss.oa.intendwork.vo.OfficePendingVO;


/**
 * ���칤��������
 * 
 * @author YANGYANG
 * 2005-8-19 17:19:04
 */
public class IntendWorkCache {
	
	private static IntendWorkCache intendWorkCacheRef;
	
	public synchronized static IntendWorkCache getInstance(){
		if(intendWorkCacheRef==null){
			intendWorkCacheRef = new IntendWorkCache();
		}
		return intendWorkCacheRef;
	}
	
	
	//�û���½������ȫ�����칤���б��ӳ��
	//key		loginname
	//value		OfficePendingVO����ļ���
	private Map totalListMap;
	//��־���󣬼�¼����ִ�����
	private Log log;
	
	private IntendWorkCache(){
		
		//��־����
		LogFactory factory = new FileLogFactory("NotificationBus");
		//��־����
		log = factory.newInstance(this.getClass());
		
		//��ʼ����������
		totalListMap = new Hashtable();

	}
	
	
	
	/**
	 * ������������
	 */
	public void clearCache(){
		
		synchronized(totalListMap){
			
			totalListMap.clear();
			
			log.debug("[���칤��]�����Ѹ��£�����������ݣ�");
		}
		
	}
	
	
	/**
	 * ��ȡ���������
	 * 
	 * @param loginname
	 * @return
	 */
	public List getTotalList(String loginname){
		
		boolean isCacheEnabled = false;
		try {
			isCacheEnabled = CacheManager.getInstance("IntendWork").isCacheEnabled();
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error("CacheManager��ȡ�쳣��IOException ",e);
			isCacheEnabled = false;
		}
		
		
		List list = new ArrayList();
		
		synchronized(totalListMap){
			
			if( isCacheEnabled && totalListMap.containsKey(loginname)){
				//ֻ��������ʱ�Ŷ�ȡ�������ݣ������ÿ�ζ������ݿ���������
				
				//�����д�������
				list = (List)totalListMap.get(loginname);
				
				log.debug("�û�["+loginname+"]��ȡ[���칤��]�������ݣ�");
				
			}else{
				//���¶�ȡ���ݿ�����
				list = this.reloadTotalList(loginname);
				//�ѵ�½�û����ʹ��칤����Ϣ�ļ��ϱ��浽ӳ���У��Թ�����ʹ��
				totalListMap.put(loginname,list);
				
				log.debug("�û�["+loginname+"]����[���칤��]���ݿ����ݣ�");
			}
			
		}
		return list;
	}
	
	
	
	/**
	 * �����������ݿ�����
	 * 
	 * @param loginname
	 * @return
	 */
	public List reloadTotalList(String loginname){
		
		List list = new ArrayList();
		
		Connection conn = null; 
		try {
			//ȡ��OABASE���ݿ�����ݿ����Ӷ���
			conn = this.getConnection("jdbc/OABASE");
			
			DAOFactory daoFactory = new DAOFactory(conn);
			OfficePendingDAO dao = new OfficePendingDAO();
			dao.setConnection(conn);
			dao.setPersonid(loginname);
			dao.setOpFlag("2");
			dao.addOrderBy("opDate", false);
			daoFactory.setDAO(dao);

			list = daoFactory.find(new OfficePendingVO());
			
			
		} catch (DAOException e) {
			e.printStackTrace();
			log.error("��ȡOABASE���ݿ�����ʧ�ܣ�DAOException ",e);
			
		} catch (NamingException e) {
			e.printStackTrace();
			log.error("��ȡOABASE���ݿ�����ʧ�ܣ�NamingException ",e);
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("��ȡOABASE���ݿ�����ʧ�ܣ�SQLException ",e);
			
		} finally {
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return list;
	}
	






	protected Connection getConnection(String jndiName) throws NamingException, SQLException {
		Connection conn = null;
		Context ctx;
		try {
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup(jndiName);
			conn = ds.getConnection();

		} catch (NamingException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return conn;
	}
	

}
