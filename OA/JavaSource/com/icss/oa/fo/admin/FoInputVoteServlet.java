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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.user.User;
import com.icss.j2ee.log.Log;
import com.icss.j2ee.log.LogFactory;
import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoInputvalueVO;
import com.icss.oa.fo.vo.FoInputvoteVO;
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.fo.vo.FoVotepersonVO;
import com.icss.oa.util.CurrentUser;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.Organization;

public class FoInputVoteServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
       
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			
			String planid = request.getParameter("planid");
			
			
			FoHandler handler = new FoHandler(conn);
			//ȡ���û���Ϣ
			Context ctx=Context.getInstance();
			UserInfo person=ctx.getCurrentLoginInfo();
			
			String	username=person.getCnName();
			String 	orgname=handler.getUserOrgUuid();
			
			
			System.out.println("ȡ���û���="+username+"��֯="+orgname+"ͶƱplanid="+planid);
			
			if(!"".equals(username)&&username!=null){
				//�����û����ж��Ƿ��������ͶƱ
				
				List invotelist=new ArrayList();
				invotelist=handler.getInputVoteList(planid,username);
				Integer invoteid=new Integer(-1);
				FoInputvoteVO invotevo=new FoInputvoteVO();
				System.out.println("ȡ�ô��û����µ�����ͶƱ��¼����="+invotelist.size());
				if(invotelist.size()==0||invotelist==null){
//					д��ͶƱ��Ϣ��
		            Date date=new Date();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String formatTime = formatter.format(date);
					
					invotevo.setInvoteDate(formatTime);
					invotevo.setInvoteRecorder(username);
					invotevo.setPlanPlanid(new Integer(planid));
					invotevo.setInvotePersonnum(new Integer(0));
					invotevo.setInvotePerson(orgname);
					invoteid=handler.addInputvote(invotevo);
					invotevo.setInvoteId(invoteid);
					System.out.println("�������ͶƱ��Ϣ���д�� invoteid="+invoteid);
					
					//					����ͶƱ���ݱ��м�¼
		            List list=new ArrayList();
		            list=handler.getVoteArticlelist(planid.toString());
		            Iterator itr=list.iterator();
		            while(itr.hasNext()){
		            	FoArticalVO artvo=(FoArticalVO) itr.next();
		            	FoInputvalueVO invaluevo=new FoInputvalueVO();
		            	invaluevo.setArtId(artvo.getArtId());
		            	invaluevo.setInvoteId(invoteid);
		            	invaluevo.setInvalueValue(new Integer(0));
		            	handler.addInputVoteValue(invaluevo);
		            	System.out.println(" ����ͶƱ���ݼ�¼�� artid="+artvo.getArtId());
		            }
					
					
					
					
				}else{
					invotevo=(FoInputvoteVO)invotelist.get(0);
					invoteid=invotevo.getInvoteId();
					System.out.println("ȡ������ͶƱ�� invoteid="+invoteid);
				}
				
				
//				ȡ�������б���Ϣ
				List list=new ArrayList();
				
				//list=handler.getInputValueList(invoteid);
				list=handler.getVoteArticlelist(planid);
				System.out.println("ȡ��ͶƱ�����б� "+list.size());
				
				
				
				Map map=new HashMap();
				map=handler.getInputValueMap(invoteid);
				
				//ȡ�üƻ���Ϣ
				FoPlanVO planvo=new FoPlanVO();
				planvo=handler.getPlanVo(planid);
				request.setAttribute("planvo",planvo);
				request.setAttribute("map",map);
				System.out.println("ȡ��ͶƱֵMap "+map.size());
				request.setAttribute("list",list);
				request.setAttribute("invotevo",invotevo);
				String dist = "/fo/Foinputvote_vote.jsp?planid="+planid+"&invoteid="+invoteid	;
				
				forward(request,response,dist);
			}else{
				String dist = "/fo/alert.jsp?errormsg=δȡ�������û���Ϣ,�����µ�¼ϵͳ!"	;
				
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