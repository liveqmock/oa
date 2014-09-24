package com.icss.oa.filetransfer.handler;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.address.dao.SysPersonDAO;
import com.icss.oa.address.vo.SysPersonVO;
import com.icss.oa.filetransfer.dao.FiletransferTimeDAO;
import com.icss.oa.filetransfer.vo.FiletransferTimeVo;

/**
 * 2008-3-25
 * @author lxy
 * @version 1.0
 *
 */
public class FiletransferTimeHandler {
	private Connection conn;
	
	public FiletransferTimeHandler(Connection conn) {
		this.conn = conn;
	}
	
	/*
	 * 返回发送邮件列表
	 */
	public List sendRecord(String senderId, String receiverId){
		DAOFactory factory = new DAOFactory(conn);
		List result = new ArrayList();
		FiletransferTimeDAO ftdao = new FiletransferTimeDAO(conn);
		factory.setDAO(ftdao);
		ftdao.setWhereClause("SENDUSERID = '" + senderId + "' AND RECEIVEUSERID = '" + receiverId+"'");
		try {
			result = factory.find(new FiletransferTimeVo());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/*
	 * 如果时间小于数据库中时间，更新数据库，否则不变
	 */
	public void updateTime(String senderId, String receiverId, long time){
		/*FiletransferTimeDAO ftdao = new FiletransferTimeDAO(conn);
		FiletransferTimeVo fvo =new FiletransferTimeVo();
		fvo.setSenduserid(senderId);
		fvo.setReceiveuserid(receiverId);
		fvo.setTime(time);
		ftdao.setValueObject(fvo);
		ftdao.setWhereClause("SENDUSERID = '" + senderId + "' AND RECEIVEUSERID = '" + receiverId+"'");
		try {
			ftdao.update(false);
		} catch (DAOException e) {
			e.printStackTrace();
		}	*/
		String sql = "update FILETRANSFER_TIME set time = " + time + " where SENDUSERID = '"+ senderId + "' and RECEIVEUSERID = '" + receiverId + "'";
		System.out.println("sql:"+sql);
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.executeUpdate(sql);
			//stat.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(stat!=null){
				try {
					stat.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 插入一条记录
	 */
	public void insertRecord(String senderId, String receiverId, long time){
		DAOFactory factory = new DAOFactory(conn);
		FiletransferTimeDAO ftdao = new FiletransferTimeDAO(conn);
		factory.setDAO(ftdao);
		ftdao.setSenduserid(senderId);
		ftdao.setReceiveuserid(receiverId);
		ftdao.setTime(time);
		try {
			ftdao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}			
	}
	
	/*
	 * 如果已经有这个记录，更新时间，如果没有插入新记录
	 */
	public void setRecord(String senderId, String receiverId, long time){
		List result = this.sendRecord(senderId, receiverId);
		if(result.size()==0){
			this.insertRecord(senderId, receiverId, time);
		}else{
			this.updateTime(senderId, receiverId, time);
		}	
	}
	
	/*
	 * 查询最近联系人
	 */
	public List selectByTime(String senduserid,int num){
		List present = new ArrayList();
		List presentNum = new ArrayList();
		DAOFactory factory = new DAOFactory(conn);
		FiletransferTimeDAO ftdao = new FiletransferTimeDAO(conn);
		factory.setDAO(ftdao);
		ftdao.setWhereClause("SENDUSERID = '"+ senduserid +"'");
		ftdao.addOrderBy("time",false);
		System.out.print("where:"+ftdao.getWhereClause());
		try {
			List temp = factory.find(new FiletransferTimeVo());
			System.out.print("tempsize:"+temp.size());
			for(int i=0;i<temp.size();i++){
				SysPersonDAO sdao = new SysPersonDAO();
				sdao.setConnection(conn);
				factory.setDAO(sdao);
				sdao.setPersonuuid(((FiletransferTimeVo)temp.get(i)).getReceiveuserid());
				List userlist = factory.find(new SysPersonVO());
				if(userlist.size()!=0){
					SysPersonVO syspvo = (SysPersonVO)userlist.get(0);
					//syspvo.setCnname(new String(syspvo.getCnname().getBytes("GBK"),"GB2312"));
					present.add(syspvo);
				}
			}		
		} catch (DAOException e) {
			e.printStackTrace();
		} 
		System.out.print("truesize:"+present.size());
		//取得最近的num位，联系人
		if(present.size()<=num){
			return present;
		}else{
			for(int j=0;j<num;j++){
				presentNum.add(present.get(j));
			}
			return presentNum;
		}
		
		
	}
}
