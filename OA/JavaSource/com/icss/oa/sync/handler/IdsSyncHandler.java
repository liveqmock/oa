package com.icss.oa.sync.handler;

import java.io.UnsupportedEncodingException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.icss.core.base.BaseException;
import com.icss.core.base.ErrorCode;
import com.icss.core.base.HandlerBase;
import com.icss.core.db.DBConnectionProvider;
import com.icss.core.db.DataBaseExecutor;
import com.icss.core.db.PagingInfo;
import com.icss.core.db.Record;
import com.icss.core.db.RecordSet;
import com.icss.core.util.DateUtility;
import com.icss.core.util.UUIDGenerator;
import com.icss.core.web.RequestKit;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.sync.dao.SysOrgDAO;
import com.icss.oa.sync.vo.SysOrgVO;

/** 
 * 
 * @ClassName: IdsPersonSyncServlet
 * @Description: 人IDS同步人员或组织信息
 * @author: caojl caojl@chinasofti.com
 * @date: 2012-12-10
 * 
 */
public class IdsSyncHandler extends HandlerBase
{
    
	
    /**
     * 同步人员页面
     * @param requestKit
     * @param pagingInfo
     * @return
     */
    public RecordSet syncPersonList(RequestKit kit, PagingInfo pagingInfo)
    {
        Connection conn = null;
        RecordSet recordSet = null;
        String JNDI="jdbc/ROEEE";
        try
        {
            conn = DBConnectionProvider.getConnection(JNDI);
            DataBaseExecutor m_executor = DataBaseExecutor.getExecutor(conn);
            
             
            StringBuffer sb = new StringBuffer(); 
    		sb.append(" SELECT distinct PERSON_SYNC.id,PERSON_SYNC.operatetype,PERSON_SYNC.xinhuaid,PERSON_SYNC.hrid,PERSON_SYNC.username,PERSON_SYNC.userpwd,PERSON_SYNC.groupid,PERSON_SYNC.gender,PERSON_SYNC.mobilephone,PERSON_SYNC.renyuanxh,PERSON_SYNC.truename,PERSON_SYNC.position,PERSON_SYNC.version,PERSON_SYNC.opratetime,PERSON_SYNC.jsonstring,PERSON_SYNC.approved  ");
    		sb.append(",PERSON_SYNC.OLD_TRUENAME,PERSON_SYNC.OLD_MOBILEPHONE,PERSON_SYNC.OLD_GENDER,PERSON_SYNC.OLD_GROUPID,PERSON_SYNC.OLD_RENYUANXH,PERSON_SYNC.OLD_POSITION,PERSON_SYNC.OLD_EMAIL ");
    		sb.append(" ,SYS_PERSON.MOBILE,SYS_PERSON.CNNAME,SYS_PERSON.SEX,SYS_PERSON.ORGCODE ");
    		sb.append(" FROM PERSON_SYNC LEFT JOIN (");
    		sb.append(" SELECT S.XINHUAID,S.MOBILE,S.CNNAME,S.SEX,U.ORGCODE FROM SYS_PERSON S,SYS_ORGPERSON T,RO_ORG U ");
    		sb.append(" WHERE S.DELTAG = '0' and t.ISBELONG='1' and  u.deltag='0'  AND S.PERSONUUID = T.PERSONUUID(+) AND T.ORGUUID = U.ORGUUID(+) ");
    		sb.append(" ) SYS_PERSON ON PERSON_SYNC.XINHUAID = SYS_PERSON.XINHUAID");
    		sb.append(" WHERE 1 = 1 "); 
    		
    		Record seaRecord = kit.getRecordWithPrefix("SEA_");
    		String name = seaRecord.getString("NAME", "");
    		String usercode = seaRecord.getString("USERCODE", "");
    		String operatetype = seaRecord.getString("OPERATETYPE", "");
    		String audittype = seaRecord.getString("AUDITTYPE", "0");
    		String person_sync_ids = seaRecord.getString("PERSON_SYNC_IDS", "");
    		
    		if(!name.equals("")) {
    			sb.append(" AND PERSON_SYNC.TRUENAME LIKE '%" + name + "%' ");
    		}
    		if(!usercode.equals("")) {
    			sb.append(" AND PERSON_SYNC.USERNAME LIKE '%" + usercode + "%' ");
    		}
    		if(!operatetype.equals("")) {
    			sb.append(" AND PERSON_SYNC.OPERATETYPE ='" + operatetype + "' ");
    		}
    		if(!audittype.equals("")&&!audittype.equals("all")) {
    			sb.append(" AND PERSON_SYNC.APPROVED='" + audittype + "'");
    		}
    		
    		if(!person_sync_ids.equals("")){
    			person_sync_ids = person_sync_ids.substring(0, person_sync_ids.length()-1);
    			String person_sync_idsArr[] =  person_sync_ids.split(",");
    			if(person_sync_idsArr!=null&&person_sync_idsArr.length>0){
    				sb.append(" AND PERSON_SYNC.xinhuaid IN("); 
    				for(int i=0;i<person_sync_idsArr.length;i++){
    					sb.append("'").append(person_sync_idsArr[i]).append("'");
    					if(i<person_sync_idsArr.length-1){
    						sb.append(",");
    					}
    				}
    				sb.append(")");
    			}
    		}
    		
    		
    		sb.append(" order by PERSON_SYNC.OPRATETIME ");
            System.out.println("xxxxxxxxxxxxxxxxxx:"+sb.toString());
            recordSet = m_executor.find(sb.toString(),pagingInfo);
            return recordSet;
        }
        catch (SQLException e)
        {
            throw new BaseException(ErrorCode.DB_EXCEPTION, e);
        }finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    throw new BaseException(ErrorCode.CON_NOT_CLOSE, e);
                }
            }
        }
    }
    
    
    
    
    
    /**
     * 同步组织页面
     * @param requestKit
     * @param pagingInfo
     * @return
     */
    public RecordSet syncOrgList(RequestKit kit, PagingInfo pagingInfo)
    {
        Connection conn = null;
        RecordSet recordSet = null;
        String JNDI="jdbc/ROEEE";
        try
        {
            conn = DBConnectionProvider.getConnection(JNDI);
            DataBaseExecutor m_executor = DataBaseExecutor.getExecutor(conn);
            
            
            StringBuffer sb = new StringBuffer();
    		sb.append("SELECT ORG_SYNC.ID,ORG_SYNC.OPERATETYPE,ORG_SYNC.GROUPCODE,");
    		sb.append("ORG_SYNC.PARENTGROUPCODE,ORG_SYNC.GROUPNAME,ORG_SYNC.CONTACT,ORG_SYNC.SERIALINDEX,");
    		sb.append("ORG_SYNC.MEMO,ORG_SYNC.OLD_GROUPCODE,ORG_SYNC.OLD_PARENTGROUPCODE,ORG_SYNC.OLD_GROUPNAME,");
    		sb.append("ORG_SYNC.OLD_CONTACT,ORG_SYNC.OLD_SERIALINDEX,ORG_SYNC.OLD_MEMO,ORG_SYNC.JSONSTRING,ORG_SYNC.OPRATETIME,ORG_SYNC.APPROVED, ");
    		sb.append("SYS_ORG.ORGCODE2,SYS_ORG.CNNAME2,SYS_ORG.CONTACT2,SYS_ORG.SERIALINDEX2,SYS_ORG.MEMO2 ");
    		
    		
    		sb.append("FROM ORG_SYNC LEFT JOIN (SELECT S.ORGCODE ORGCODE2,S.CNNAME CNNAME2,S.CONTACT CONTACT2,S.SERIALINDEX SERIALINDEX2,S.MEMO MEMO2 FROM SYS_ORG S WHERE S.DELTAG='0') SYS_ORG ON ORG_SYNC.GROUPCODE = SYS_ORG.ORGCODE2 ");
    		sb.append(" WHERE 1 = 1 "); 
    		
    		Record seaRecord = kit.getRecordWithPrefix("SEA_");
    		String orgname = seaRecord.getString("ORGNAME", "");
    		String orgcode = seaRecord.getString("ORGCODE", "");
    		String Otype = seaRecord.getString("OPERATETYPE", "");
    		String Atype = seaRecord.getString("AUDITTYPE", "0");
    		String orgcodes = seaRecord.getString("ORG_SYNC_ORGCODES", "");
    		
    		
    		if(orgname!=null&&!orgname.equals("")) {
    			sb.append(" AND ORG_SYNC.GROUPNAME LIKE '%" + orgname + "%' ");
    		}
    		if(orgcode!=null&&!orgcode.equals("")) {
    			sb.append(" AND ORG_SYNC.GROUPCODE LIKE '%" + orgcode + "%' ");
    		}
    		if(Otype!=null&&!Otype.equals("")) {
    			sb.append(" AND ORG_SYNC.OPERATETYPE ='" + Otype + "' ");
    		}
    		if(Atype != null&&!Atype.equals("")&&!Atype.equals("all")) {
    			sb.append(" AND ORG_SYNC.APPROVED='" + Atype + "'");
    		}
    		
    		
    		if(!orgcodes.equals("")){
    			orgcodes = orgcodes.substring(0, orgcodes.length()-1);
    			String orgcodesArr[] =  orgcodes.split(",");
    			if(orgcodesArr!=null&&orgcodesArr.length>0){
    				sb.append(" AND ORG_SYNC.GROUPCODE IN("); 
    				for(int i=0;i<orgcodesArr.length;i++){
    					sb.append("'").append(orgcodesArr[i]).append("'");
    					if(i<orgcodesArr.length-1){
    						sb.append(",");
    					}
    				}
    				sb.append(")");
    			}
    		}
    		
    		sb.append(" ORDER BY ORG_SYNC.OPRATETIME  ");
    		
            recordSet = m_executor.find(sb.toString(),pagingInfo);
            return recordSet;
        }
        catch (SQLException e)
        {
            throw new BaseException(ErrorCode.DB_EXCEPTION, e);
        }finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    throw new BaseException(ErrorCode.CON_NOT_CLOSE, e);
                }
            }
        }
    }
    
    
    
    
    
    
    /**
	 * @author 周义
	 * @param orgcode
	 * @return 得到组织的全路径
	 */
	public String getFullOrgName(String orgCode) throws HandlerException {
		String fullName = "";
		if(orgCode!=null&&!orgCode.equals("")){
			Record record = getOrgByOrgCode(orgCode);
			if(record!=null){
				String p_id = record.getString("PARENTORGUUID","");
				fullName = record.getString("CNNAME","");
				if(!p_id.equals("")){
					while(!p_id.equals("0000000000000000000000000000000000000000000000000000000000000000")){
						record = getOrgByOrgUuid(p_id);
						p_id = record.getString("PARENTORGUUID","");
						fullName = record.getString("CNNAME","")+"->"+fullName;
					}
				}
			}
		}
		
		return fullName;
	}
	
	/**
	 * @author 周义
	 * @param orgcode
	 * @return 根据组织code获得组织名称
	 */
	public String getOrgName(String orgCode) throws HandlerException {
		Connection conn = null;
		String JNDI="jdbc/ROEEE";
		String returnStr = "";
		try {
			conn = DBConnectionProvider.getConnection(JNDI);
			DataBaseExecutor m_executor = DataBaseExecutor.getExecutor(conn);
			String sqlStr = "SELECT CNNAME FROM SYS_ORG WHERE ORGCODE='"+orgCode+"'";
			RecordSet rset = m_executor.find(sqlStr);
			if(rset!=null&&rset.size()>0){ 
				returnStr = rset.get(0).getString("CNNAME","");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally 
        { 
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    throw new BaseException(ErrorCode.CON_NOT_CLOSE, e);
                }
            }
        }
		return returnStr;
	}
	
	
	
	
	 /**
	 * @author 周义
	 * @param orgcode
	 * @return 根据ORGCODE得到组织
	 */
	public Record getOrgByOrgCode(String orgcode) throws HandlerException {
		Connection conn = null;
		String JNDI="jdbc/ROEEE";
		Record returnRecord = null;
		try {
			conn = DBConnectionProvider.getConnection(JNDI);
			DataBaseExecutor m_executor = DataBaseExecutor.getExecutor(conn);
			String sqlStr = "SELECT * FROM SYS_ORG WHERE ORGCODE='"+orgcode+"'";
			RecordSet rset = m_executor.find(sqlStr);
			if(rset!=null&&rset.size()>0){
				returnRecord = rset.get(0);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    throw new BaseException(ErrorCode.CON_NOT_CLOSE, e);
                }
            }
        }
		return returnRecord;
	}
    
	
	/**
	 * @author 周义
	 * @param orgcode
	 * @return 根据ORGUUID得到组织
	 */
	public Record getOrgByOrgUuid(String orgUuid) throws HandlerException {
		Connection conn = null;
		String JNDI="jdbc/ROEEE";
		Record returnRecord = null;
		try {
			conn = DBConnectionProvider.getConnection(JNDI);
			DataBaseExecutor m_executor = DataBaseExecutor.getExecutor(conn);
			String sqlStr = "SELECT * FROM SYS_ORG WHERE ORGUUID='"+orgUuid+"'";
			RecordSet rset = m_executor.find(sqlStr);
			if(rset!=null&&rset.size()>0){
				returnRecord = rset.get(0);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    throw new BaseException(ErrorCode.CON_NOT_CLOSE, e);
                }
            }
        }
		return returnRecord;
	}
	
	
	
	/**
	 * @author 周义
	 * @param orgcode
	 * @return 根据userid得到组织全称
	 */
	public String getOrgCodeByUserid(String userid) throws HandlerException {
		Connection conn = null;
		String JNDI="jdbc/ROEEE";
		String orgCode = "";
		try{
			conn = DBConnectionProvider.getConnection(JNDI);
			DataBaseExecutor m_executor = DataBaseExecutor.getExecutor(conn);
			StringBuffer sb = new StringBuffer(""); 
			sb.append(" SELECT U.ORGCODE FROM SYS_PERSON T, SYS_ORGPERSON S, SYS_ORG U ");
			sb.append(" WHERE T.PERSONUUID = S.PERSONUUID ");
			sb.append(" AND S.ISBELONG = '1' ");
			sb.append(" AND S.ORGUUID = U.ORGUUID AND T.USERID='").append(userid).append("'");
			RecordSet rset = m_executor.find(sb.toString());
			if(rset!=null&&rset.size()>0){
				Record rcd = rset.get(0);
				orgCode = rcd.getString("ORGCODE", "");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally
        {
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (SQLException e)
                {
                    throw new BaseException(ErrorCode.CON_NOT_CLOSE, e);
                }
            }
        }
		return orgCode;
	}
    
    
}
