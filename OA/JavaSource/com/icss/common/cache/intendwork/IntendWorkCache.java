/*
 * 创建日期 2005-8-19
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
 * 待办工作缓存类
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
	
	
	//用户登陆名称与全部待办工作列表的映射
	//key		loginname
	//value		OfficePendingVO对象的集合
	private Map totalListMap;
	//日志对象，记录程序执行情况
	private Log log;
	
	private IntendWorkCache(){
		
		//日志工厂
		LogFactory factory = new FileLogFactory("NotificationBus");
		//日志对象
		log = factory.newInstance(this.getClass());
		
		//初始化缓存数据
		totalListMap = new Hashtable();

	}
	
	
	
	/**
	 * 清除缓存的数据
	 */
	public void clearCache(){
		
		synchronized(totalListMap){
			
			totalListMap.clear();
			
			log.debug("[待办工作]数据已更新！清除缓存数据！");
		}
		
	}
	
	
	/**
	 * 获取缓存的数据
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
			log.error("CacheManager读取异常！IOException ",e);
			isCacheEnabled = false;
		}
		
		
		List list = new ArrayList();
		
		synchronized(totalListMap){
			
			if( isCacheEnabled && totalListMap.containsKey(loginname)){
				//只有允许缓存时才读取缓存数据，否则就每次都从数据库载入数据
				
				//缓存中存在数据
				list = (List)totalListMap.get(loginname);
				
				log.debug("用户["+loginname+"]获取[待办工作]缓存数据！");
				
			}else{
				//重新读取数据库数据
				list = this.reloadTotalList(loginname);
				//把登陆用户名和待办工作消息的集合保存到映射中，以供缓存使用
				totalListMap.put(loginname,list);
				
				log.debug("用户["+loginname+"]载入[待办工作]数据库数据！");
			}
			
		}
		return list;
	}
	
	
	
	/**
	 * 重新载入数据库数据
	 * 
	 * @param loginname
	 * @return
	 */
	public List reloadTotalList(String loginname){
		
		List list = new ArrayList();
		
		Connection conn = null; 
		try {
			//取得OABASE数据库的数据库连接对象
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
			log.error("获取OABASE数据库连接失败！DAOException ",e);
			
		} catch (NamingException e) {
			e.printStackTrace();
			log.error("获取OABASE数据库连接失败！NamingException ",e);
			
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("获取OABASE数据库连接失败！SQLException ",e);
			
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
