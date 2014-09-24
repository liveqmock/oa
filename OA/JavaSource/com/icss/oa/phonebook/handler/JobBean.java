/*
 * Created on 2004-12-23
 *
 */
package com.icss.oa.phonebook.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.util.Globals;
import com.icss.oa.phonebook.dao.PhoneJobDAO;
import com.icss.oa.phonebook.vo.PhoneJobVO;

/**
 * @author firecoral
 *
 * 
 */
public class JobBean {

	/**构造两个HASHTABLE以便以后取
	 * TABLE由一个InitPhoneJobServlet来调用初始化
	 */

	protected Hashtable jobLevelHashTable;
	protected Hashtable jobNameHashTable;
	public static JobBean jobBeanRef;
	public synchronized static JobBean getInstance() {
		if (jobBeanRef == null) {
			jobBeanRef = new JobBean();
		}
		return jobBeanRef;
	}
	private JobBean() {
		jobLevelHashTable = new Hashtable();
		jobNameHashTable = new Hashtable();
	}

	public void initHashTable(Connection conn) {
		Integer jobLevel = null;
		String jobName = "";
		Integer jobId = null;
		PhoneJobDAO jdao = new PhoneJobDAO();
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(jdao);
		try {
			List list = factory.find(new PhoneJobVO());
			if (list.size() > 0) {
				Iterator jItr = list.iterator();
				while (jItr.hasNext()) {
					PhoneJobVO jVO = (PhoneJobVO) jItr.next();
					jobId = jVO.getJobId();
					jobLevel = jVO.getJobLevel();
					System.out.println("the jobId is:" + jobId);
					System.out.println("the jobLevel is:" + jobLevel);
					jobLevelHashTable.put(jobId, jobLevel);
					jobName = jVO.getJobName();
					jobNameHashTable.put(jobId, jobName);

				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	//取LEVEL
	public Integer getJobLevel(Integer jobId) {
		Integer jobLevel = null;
		try{
		    jobLevel = (Integer) jobLevelHashTable.get(jobId);
		}catch(Exception e){
		    e.printStackTrace();
		    jobLevelHashTable.clear();
		    jobNameHashTable.clear();
		    initTwoHashtable_bak();
		    jobLevel = (Integer) jobLevelHashTable.get(jobId);
		}
		return jobLevel;
	}

	//取Name
	public String getJobName(Integer jobId) {
		String jobName = null;
		try{
		    jobName = jobNameHashTable.get(jobId).toString();
		} catch (Exception e) {
		    e.printStackTrace();
		    jobLevelHashTable.clear();
		    jobNameHashTable.clear();
		    initTwoHashtable_bak();
		    jobName = jobNameHashTable.get(jobId).toString();
        }
		return jobName;
	}
	
	public void initTwoHashtable_bak(){
	    
	    Connection conn = null;
	    try {
            conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
            Integer jobLevel = null;
    		String jobName = "";
    		Integer jobId = null;
    		PhoneJobDAO jdao = new PhoneJobDAO();
    		DAOFactory factory = new DAOFactory(conn);
    		factory.setDAO(jdao);
    		try {
    			List list = factory.find(new PhoneJobVO());
    			if (list.size() > 0) {
    				Iterator jItr = list.iterator();
    				while (jItr.hasNext()) {
    					PhoneJobVO jVO = (PhoneJobVO) jItr.next();
    					jobId = jVO.getJobId();
    					jobLevel = jVO.getJobLevel();
    					jobLevelHashTable.put(jobId, jobLevel);
    					jobName = jVO.getJobName();
    					jobNameHashTable.put(jobId, jobName);

    				}
    			}
    		} catch (DAOException e) {
    			e.printStackTrace();
    		}
        } catch (DBConnectionLocatorException e) {
            e.printStackTrace();
        } finally{
            if(conn!=null){
                try {
                    conn.close();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        
        
	}
}
