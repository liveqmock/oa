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
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.fo.vo.FoVotepersonVO;

public class FoInputValueUpdateServlet extends ServletBase{
    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException {
    	Connection conn = null;
        
		try{
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			
			String planid = request.getParameter("planid");
			String invoteid = request.getParameter("invoteid");
			//System.out.println("�޸�FoInputValueUpdateServlet");
		

			//ȡ��ͶƱֵ
			List list=new ArrayList();
			FoHandler handler=new FoHandler(conn);
			list=handler.getSimpleInputValueList(invoteid);
			Iterator itr=list.iterator();
			//ȡ��������ϢMAP
			Map artmap=new HashMap();
			artmap=handler.getSimpleArticleMap(planid);
			//System.out.println("ȡ��������ϢMAP"+artmap.size());
			while(itr.hasNext()){
				FoInputvalueVO vo=(FoInputvalueVO) itr.next();
				int oldnum=vo.getInvalueValue().intValue();
				int newnum=new Integer(request.getParameter(vo.getArtId().toString()+"input")==null?"0":request.getParameter(vo.getArtId().toString()+"input")).intValue();
				System.out.println("��������Ʊ������oldnum="+oldnum+"newnum="+newnum);
				FoArticalVO artvo=(FoArticalVO) artmap.get(vo.getArtId());
				handler.updateArticalVotenum(vo.getArtId().toString(),new Integer(artvo.getArtVotenum().intValue()-oldnum+newnum));
				//����ͶƱ���ݱ�����
				vo.setInvalueValue(new Integer(newnum));
				handler.updateInputValue(vo);
			
			
			}
			
			request.setAttribute("list",list);
			
			String dist = "/servlet/FoInputVoteDetailServlet?planid="+planid+"&invoteid="+invoteid	;
			forward(request,response,dist);
				
			
			
			
			
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