/*******************************************************************************
* Copyright (c) ICSS Corporation and others.
* All rights reserved.
* Created on 2006-10-10 16:21:47.
*
* @author wangsu
*******************************************************************************/

package com.icss.oa.fo.admin;

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

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoInputvalueVO;
import com.icss.oa.fo.vo.FoInputvoteVO;
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.fo.vo.FoVotepersonVO;

public class FoInputValueVoteServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
        
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			
			String planid = request.getParameter("planid");
			String invoteperson = request.getParameter("invoteperson")==null?"":request.getParameter("invoteperson");
			String invotepersonnum = request.getParameter("invotepersonnum")==null?"0": request.getParameter("invotepersonnum");
			String invoteid = request.getParameter("invoteid");
			//System.out.println("修改FoInputValueUpdateServlet");
			
			

			//取得投票值
			List list=new ArrayList();
			FoHandler handler=new FoHandler(conn);
			
//			取得投票信息
			FoInputvoteVO inputvo=handler.getInputVote(new Integer(invoteid));
			System.out.println("取得投票信息=");
			//更改输入投票信息中的人数
			int oldinputpersonnum=inputvo.getInvotePersonnum().intValue();
			inputvo.setInvotePersonnum(new Integer(invotepersonnum));
			handler.updateInputVote(inputvo);
			System.out.println("更改输入投票信息中的人数");
			//更改计划中的投票人数
			FoPlanVO planvo=handler.getPlanVo(planid);
			int oldvotenum=planvo.getPlanPersonnum().intValue();
			int newvotenum=oldvotenum-oldinputpersonnum+new Integer(invotepersonnum).intValue();
			planvo.setPlanPersonnum(new Integer(newvotenum));
			handler.updatePlan(planvo);
			
			
			
			
			list=handler.getSimpleInputValueList(invoteid);
			Iterator itr=list.iterator();
			//取得文章信息MAP
			Map artmap=new HashMap();
			artmap=handler.getSimpleArticleMap(planid);
			//System.out.println("取得文章信息MAP"+artmap.size());
			while(itr.hasNext()){
				FoInputvalueVO vo=(FoInputvalueVO) itr.next();
				int oldnum=vo.getInvalueValue().intValue();
				int newnum=new Integer(request.getParameter(vo.getArtId().toString()+"input")==null?"0":request.getParameter(vo.getArtId().toString()+"input")).intValue();
				System.out.println("进入文章票数更新oldnum="+oldnum+"newnum="+newnum);
				FoArticalVO artvo=(FoArticalVO) artmap.get(vo.getArtId());
				handler.updateArticalVotenum(vo.getArtId().toString(),new Integer(artvo.getArtVotenum().intValue()-oldnum+newnum));
				//更新投票数据表数据
				vo.setInvalueValue(new Integer(newnum));
				handler.updateInputValue(vo);
			
			
			}
			
			request.setAttribute("list",list);
			
			String dist = "/servlet/FoInputVoteServlet?planid="+planid	;
			//forward(request,response,dist);
				
			response.sendRedirect(request.getContextPath()+"/servlet/FoInputVoteServlet?planid="+planid	);
			
			
			
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