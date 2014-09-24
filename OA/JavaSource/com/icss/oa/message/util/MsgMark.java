/*
 * Created on 2004-9-17
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.message.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MsgMark {
	
	private static int currentNum;
	private static int max_num;
	private static int num_length;
	
	private static boolean bInit=false;
	
	public static void Init(Connection conn,int _num_length){
		
		Statement state=null;
		ResultSet res=null;
		num_length=_num_length;
		
		try{
			state=conn.createStatement();
			
			StringBuffer sql=new StringBuffer();
			
			sql.append("select max(MS_DATE) as MAXDATE from MSG_SEND ");
			
			res=state.executeQuery(sql.toString());
			
			if(res.next()){
				long maxdate=res.getLong("MAXDATE");
				res.close();
				sql=new StringBuffer();
				sql.append("select MS_ID from MSG_SEND where MS_DATE="+maxdate);
				res=state.executeQuery(sql.toString());
				if(res.next()){
					currentNum=res.getInt("MS_ID");
				}
				else{
					throw new Exception("无法找到最大短信ID");
				}
				
			} else{
				throw new Exception("无法找到最大短信发送日期");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			currentNum=1;
			
		} finally {
			if(res!=null){
				try{
					res.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
			if(state!=null){
				try{
					state.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		
		StringBuffer s=new StringBuffer();
		s.append("1");
		for(int i=0;i<num_length;++i){
			s.append("0");
		}
		try{
			max_num=new Integer(s.toString()).intValue();
		}
		catch(Exception e){
			e.printStackTrace();
			max_num=1000;
		}
		bInit=true;
	}
	
	
	public static int getNum(){
		if(!bInit){
			return -1;
		}
		++currentNum;
		if(currentNum>=max_num){
			currentNum=1;
		}
		return currentNum;
	}
	
	
	public static String numToString(int num){
		int i=num;
		String s=""+i;
		int l=s.length();
		StringBuffer sb=new StringBuffer();
		for(int j=0;j<num_length-l;++j){
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}
	
	
}


