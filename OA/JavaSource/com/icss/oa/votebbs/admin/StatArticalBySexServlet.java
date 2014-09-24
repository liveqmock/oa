/*
 * Created on 2004-12-4
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.vo.BbsOptionsVO;
import com.icss.oa.votebbs.vo.BbsStatOptionsVO;

/**
 * @author firecoral
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StatArticalBySexServlet extends ServletBase{

	/* (non-Javadoc)
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) 
	    throws ServletException, IOException {
		
		Connection conn = null;
		List list = new ArrayList();
		System.out.println("进入投票进入投票统计");
		String	mainid=request.getParameter("mainid")==null?"-1":request.getParameter("mainid").toString();
		String	statarticletype=request.getParameter("statarticletype")==null?"-1":request.getParameter("statarticletype").toString();
		
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			List optionlist=handler.getarticalOptionList(Integer.valueOf(mainid));
			Iterator	optionitr=optionlist.iterator();
			System.out.println("进入投票统计StatArticalBySexServlet1++++++++++++++++++++mainid="+mainid+"statarticletype"+statarticletype);
				while(optionitr.hasNext()){
					BbsOptionsVO vo=(BbsOptionsVO) optionitr.next();
					
					BbsStatOptionsVO	statvo=new BbsStatOptionsVO();
					statvo.setOpId(vo.getOpId());
					statvo.setOpTitle(vo.getOpTitle());
					statvo.setOpOrder(vo.getOpOrder());
					System.out.println("进入投票统计StatArticalBySexServlet2++++++++++++++++++++");
					if("单选".equals(statarticletype.toString())){
						statvo.setSexstatnum(handler.getSingleStatBySexVO(vo.getOpId()));
					}else{
						statvo.setSexstatnum(handler.getMultiStatBySexVO(vo.getOpId()));
					}
					
					System.out.println("进入投票统计StatArticalBySexServlet3++++++++++++++++++++");
					
					list.add(statvo);
					
				}
				request.setAttribute("list",list);
				request.setAttribute("statarticletype",statarticletype);
				forward(request, response, "/votebbs/statOptionBySex.jsp");
				
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}

}
