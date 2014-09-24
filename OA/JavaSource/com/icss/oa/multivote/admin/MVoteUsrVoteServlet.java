/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.multivote.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.sql.Connection;
import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.VotePersonVO;
import com.icss.oa.multivote.vo.VotePlanVO;
import com.icss.oa.multivote.vo.VoteValueVO;



public class MVoteUsrVoteServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
//    	 System.out.println("++++++++++++进入FoUsrVoteServlet+personid");
		String personid = request.getParameter("personid");
		String personname = request.getParameter("personname");

		String planid = request.getParameter("planid");
		String planseason = request.getParameter("planseason")==null?"-1":request.getParameter("planseason");
		//String[] voteartids =request.getParameterValues("voteform1ids") == null? new String[] { "-1" }: request.getParameterValues("voteform1ids");
//        System.out.println("++++++++++++进入MVoteUsrVoteServlet+personid"+personid+"planseason="+planseason);
		Enumeration inputnames=request.getParameterNames();
		List valuelist=new ArrayList();
		while (inputnames.hasMoreElements()){
			String inputname=inputnames.nextElement().toString();
//			System.out.println("++++++++++++++++++++++++++++++++inputname="+inputname);
			if(inputname.indexOf("_input")>0){
				String artid=inputname.substring(0,inputname.indexOf("+"));
				String itemid=inputname.substring(inputname.indexOf("+")+1,inputname.indexOf("_input"));
				String	inputvalue=request.getParameter(inputname);
				String[] value={artid,itemid,inputvalue};
				
				valuelist.add(value);
//				System.out.println("++++++MVoteUsrVoteServlet+++++++++++++++++++++++artid="+artid+"itemid="+itemid+"inputvalue"+inputvalue);
			}
		}
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			VoteHandler handler=new VoteHandler(conn);
			//System.out.println("+++++++++++==FoUsrVoteServlet");
			VotePersonVO vo = new VotePersonVO();
			vo = handler.findByPersonid(personid);
			String	userhasvote=vo.getFlag();
//			System.out.println("+++++++++++==FoUsrVoteServletuserhasvote= "+userhasvote);
			if(!"1".equals(userhasvote)){//未投过票
				if (valuelist.size()>0) {
//					System.out.println("+++++++++++==FoUsrVoteServlet000");
					for (int i = 0; i < valuelist.size(); i++) {
					//System.out.println("+++++++++++==FoUsrVoteServlet111");
					String[] valuestring=(String[]) valuelist.get(i);
					//填写投票记录
					VoteValueVO votevo=new VoteValueVO();
					votevo.setArtId(new Integer(valuestring[0]));
					votevo.setItemId(new Integer(valuestring[1]));
					votevo.setVoteValue(new Integer(valuestring[2]));
					votevo.setPersonId(new Integer(personid));
					
//					System.out.println("+++++++++++==FoUsrVoteServlet222");
					Date date=new Date();
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String formatTime = formatter.format(date);
					//System.out.println("+++++++++++==FoUsrVoteServlet333"+formatTime);
					votevo.setVoteDate(formatTime);
					votevo.setVoteSeason(planseason);
//					System.out.println("+++++++++++==FoUsrVoteServlet444"+planseason);
					handler.addVoteValue(votevo);
//					System.out.println("+++++++++++==FoUsrVoteServlet填写投票记录完成");
					}
					//				更改投票人数
					VotePlanVO planvo=handler.getPlanVo(planid);
					Integer votepersonnum=new Integer(0);
					if(planvo.getPlanPersonnum()!=null){
						votepersonnum=planvo.getPlanPersonnum();
					}
					planvo.setPlanPersonnum(new Integer(votepersonnum.intValue()+1));
					handler.updatePlan(planvo);
					//System.out.println("+++++++++++==FoUsrVoteServlet更改文章投票票数");
					//				更改用户投票标识
					handler.updatePersonFlag(personid,new Integer(1));
//					System.out.println("+++++++++++==FoUsrVoteServlet更改用户投票标识");
					//重新定向到已投票页面返回用户列表
					request.setAttribute("planid",planid);
					request.setAttribute("personid",personid);
					request.setAttribute("personname",personname);
					request.setAttribute("errormsg","您已完成投票，谢谢您的参与");
					
					String dist = "/multivote/mVotecomplete.jsp"				;
					forward(request,response,dist);
				}else{
					//更改用户投票标识
					handler.updatePersonFlag(personid,new Integer(1));
//					System.out.println("+++++++++++==FoUsrVoteServlet 2更改用户投票标识");
					//重新定向到已投票页面返回用户列表
					request.setAttribute("errormsg","您的投票已投交，谢谢您的参与");
					
					String dist = "/multivote/mVotecomplete.jsp"		;
					forward(request,response,dist);
					
				}
			}else{//已投过票
				
				
				//重新定向到已投票页面返回用户列表
				String dist = "/multivote/mVotecomplete.jsp?personid="+personid
				+"&personname="+personname
				+"&planid="+planid
				
				+"&errormsg=您已投过选票，不能重复投票"
				;
				forward(request,response,dist);
				
			}				
		}catch(Exception e){
			handleError(e) ;
		}finally{
			try {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			} catch (Exception e) {
				handleError(e);
			}
        }
    }
}