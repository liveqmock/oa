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
//    	 System.out.println("++++++++++++����FoUsrVoteServlet+personid");
		String personid = request.getParameter("personid");
		String personname = request.getParameter("personname");

		String planid = request.getParameter("planid");
		String planseason = request.getParameter("planseason")==null?"-1":request.getParameter("planseason");
		//String[] voteartids =request.getParameterValues("voteform1ids") == null? new String[] { "-1" }: request.getParameterValues("voteform1ids");
//        System.out.println("++++++++++++����MVoteUsrVoteServlet+personid"+personid+"planseason="+planseason);
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
			if(!"1".equals(userhasvote)){//δͶ��Ʊ
				if (valuelist.size()>0) {
//					System.out.println("+++++++++++==FoUsrVoteServlet000");
					for (int i = 0; i < valuelist.size(); i++) {
					//System.out.println("+++++++++++==FoUsrVoteServlet111");
					String[] valuestring=(String[]) valuelist.get(i);
					//��дͶƱ��¼
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
//					System.out.println("+++++++++++==FoUsrVoteServlet��дͶƱ��¼���");
					}
					//				����ͶƱ����
					VotePlanVO planvo=handler.getPlanVo(planid);
					Integer votepersonnum=new Integer(0);
					if(planvo.getPlanPersonnum()!=null){
						votepersonnum=planvo.getPlanPersonnum();
					}
					planvo.setPlanPersonnum(new Integer(votepersonnum.intValue()+1));
					handler.updatePlan(planvo);
					//System.out.println("+++++++++++==FoUsrVoteServlet��������ͶƱƱ��");
					//				�����û�ͶƱ��ʶ
					handler.updatePersonFlag(personid,new Integer(1));
//					System.out.println("+++++++++++==FoUsrVoteServlet�����û�ͶƱ��ʶ");
					//���¶�����ͶƱҳ�淵���û��б�
					request.setAttribute("planid",planid);
					request.setAttribute("personid",personid);
					request.setAttribute("personname",personname);
					request.setAttribute("errormsg","�������ͶƱ��лл���Ĳ���");
					
					String dist = "/multivote/mVotecomplete.jsp"				;
					forward(request,response,dist);
				}else{
					//�����û�ͶƱ��ʶ
					handler.updatePersonFlag(personid,new Integer(1));
//					System.out.println("+++++++++++==FoUsrVoteServlet 2�����û�ͶƱ��ʶ");
					//���¶�����ͶƱҳ�淵���û��б�
					request.setAttribute("errormsg","����ͶƱ��Ͷ����лл���Ĳ���");
					
					String dist = "/multivote/mVotecomplete.jsp"		;
					forward(request,response,dist);
					
				}
			}else{//��Ͷ��Ʊ
				
				
				//���¶�����ͶƱҳ�淵���û��б�
				String dist = "/multivote/mVotecomplete.jsp?personid="+personid
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