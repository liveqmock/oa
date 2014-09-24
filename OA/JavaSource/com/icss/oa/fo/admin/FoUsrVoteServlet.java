/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.sql.Connection;
import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.fo.vo.FoVoteVO;
import com.icss.oa.fo.vo.FoVotepersonVO;


public class FoUsrVoteServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
//    	 System.out.println("++++++++++++进入FoUsrVoteServlet+personid");
		String personid = request.getParameter("personid");
		String personname = request.getParameter("personname");

		String planid = request.getParameter("planid");
		String[] voteartids =request.getParameterValues("voteform1ids") == null? new String[] { "-1" }: request.getParameterValues("voteform1ids");
//        System.out.println("++++++++++++进入FoUsrVoteServlet+personid"+personid+"length="+voteartids.length);
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			FoHandler handler=new FoHandler(conn);
			//System.out.println("+++++++++++==FoUsrVoteServlet");
			FoVotepersonVO vo = new FoVotepersonVO();
			vo = handler.findByPersonid(personid);
			String	userhasvote=vo.getFlag();
			//System.out.println("+++++++++++==FoUsrVoteServletuserhasvote= "+userhasvote);
			if(!"1".equals(userhasvote)){//未投过票
				if (!new String[] { "-1" }.equals(voteartids)) {
					//System.out.println("+++++++++++==FoUsrVoteServlet000");
					for (int i = 0; i < voteartids.length; i++) {
					//System.out.println("+++++++++++==FoUsrVoteServlet111");
					//填写投票记录
					FoVoteVO votevo=new FoVoteVO();
					votevo.setArtId(new Integer(voteartids[i]));
					votevo.setPersonId(new Integer(personid));
					votevo.setVoteValue(new Integer(1));
					//System.out.println("+++++++++++==FoUsrVoteServlet222");
					Date date=new Date();
					
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String formatTime = formatter.format(date);
					//System.out.println("+++++++++++==FoUsrVoteServlet333"+formatTime);
					votevo.setVoteDate(formatTime);
					//System.out.println("+++++++++++==FoUsrVoteServlet444"+formatTime);
					handler.addVoteValue(votevo);
					//System.out.println("+++++++++++==FoUsrVoteServlet填写投票记录完成");
					//更改文章投票票数
					FoArticalVO artvo=new FoArticalVO();
					artvo=handler.getSimpleArtVO(voteartids[i]);
					Integer nowvotenum=new Integer(0);
					//System.out.println("++++++++++artvo="+artvo.getArtVotenum());
					if(artvo.getArtVotenum()!=null&&!"".equals(artvo.getArtVotenum().toString())){
						nowvotenum=artvo.getArtVotenum();
					}
					artvo.setArtVotenum(new Integer(nowvotenum.intValue()+1));
					handler.updateArticalVotenum(voteartids[i],artvo.getArtVotenum());
								
					}
					//				更改投票人数
					FoPlanVO planvo=handler.getPlanVo(planid);
					Integer votepersonnum=new Integer(0);
					if(planvo.getPlanPersonnum()!=null){
						votepersonnum=planvo.getPlanPersonnum();
					}
					planvo.setPlanPersonnum(new Integer(votepersonnum.intValue()+1));
					handler.updatePlan(planvo);
					//System.out.println("+++++++++++==FoUsrVoteServlet更改文章投票票数");
					//				更改用户投票标识
					handler.updatePersonFlag(personid,new Integer(1));
					//System.out.println("+++++++++++==FoUsrVoteServlet更改用户投票标识");
					//重新定向到已投票页面返回用户列表
					request.setAttribute("planid",planid);
					request.setAttribute("personid",personid);
					request.setAttribute("personname",personname);
					request.setAttribute("errormsg","您已完成投票，谢谢您的参与");
					
					String dist = "/fo/votecomplete.jsp"				;
					forward(request,response,dist);
				}else{
					//更改用户投票标识
					handler.updatePersonFlag(personid,new Integer(1));
					System.out.println("+++++++++++==FoUsrVoteServlet 2更改用户投票标识");
					//重新定向到已投票页面返回用户列表
					request.setAttribute("errormsg","您的投票已投交，谢谢您的参与");
					
					String dist = "/fo/votecomplete.jsp"				;
					forward(request,response,dist);
					
				}
			}else{//已投过票
				
				
				//重新定向到已投票页面返回用户列表
				String dist = "/fo/votecomplete.jsp?personid="+personid
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