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
//    	 System.out.println("++++++++++++����FoUsrVoteServlet+personid");
		String personid = request.getParameter("personid");
		String personname = request.getParameter("personname");

		String planid = request.getParameter("planid");
		String[] voteartids =request.getParameterValues("voteform1ids") == null? new String[] { "-1" }: request.getParameterValues("voteform1ids");
//        System.out.println("++++++++++++����FoUsrVoteServlet+personid"+personid+"length="+voteartids.length);
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			FoHandler handler=new FoHandler(conn);
			//System.out.println("+++++++++++==FoUsrVoteServlet");
			FoVotepersonVO vo = new FoVotepersonVO();
			vo = handler.findByPersonid(personid);
			String	userhasvote=vo.getFlag();
			//System.out.println("+++++++++++==FoUsrVoteServletuserhasvote= "+userhasvote);
			if(!"1".equals(userhasvote)){//δͶ��Ʊ
				if (!new String[] { "-1" }.equals(voteartids)) {
					//System.out.println("+++++++++++==FoUsrVoteServlet000");
					for (int i = 0; i < voteartids.length; i++) {
					//System.out.println("+++++++++++==FoUsrVoteServlet111");
					//��дͶƱ��¼
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
					//System.out.println("+++++++++++==FoUsrVoteServlet��дͶƱ��¼���");
					//��������ͶƱƱ��
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
					//				����ͶƱ����
					FoPlanVO planvo=handler.getPlanVo(planid);
					Integer votepersonnum=new Integer(0);
					if(planvo.getPlanPersonnum()!=null){
						votepersonnum=planvo.getPlanPersonnum();
					}
					planvo.setPlanPersonnum(new Integer(votepersonnum.intValue()+1));
					handler.updatePlan(planvo);
					//System.out.println("+++++++++++==FoUsrVoteServlet��������ͶƱƱ��");
					//				�����û�ͶƱ��ʶ
					handler.updatePersonFlag(personid,new Integer(1));
					//System.out.println("+++++++++++==FoUsrVoteServlet�����û�ͶƱ��ʶ");
					//���¶�����ͶƱҳ�淵���û��б�
					request.setAttribute("planid",planid);
					request.setAttribute("personid",personid);
					request.setAttribute("personname",personname);
					request.setAttribute("errormsg","�������ͶƱ��лл���Ĳ���");
					
					String dist = "/fo/votecomplete.jsp"				;
					forward(request,response,dist);
				}else{
					//�����û�ͶƱ��ʶ
					handler.updatePersonFlag(personid,new Integer(1));
					System.out.println("+++++++++++==FoUsrVoteServlet 2�����û�ͶƱ��ʶ");
					//���¶�����ͶƱҳ�淵���û��б�
					request.setAttribute("errormsg","����ͶƱ��Ͷ����лл���Ĳ���");
					
					String dist = "/fo/votecomplete.jsp"				;
					forward(request,response,dist);
					
				}
			}else{//��Ͷ��Ʊ
				
				
				//���¶�����ͶƱҳ�淵���û��б�
				String dist = "/fo/votecomplete.jsp?personid="+personid
				+"&personname="+personname
				+"&planid="+planid
				
				+"&errormsg=����Ͷ��ѡƱ�������ظ�ͶƱ"
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