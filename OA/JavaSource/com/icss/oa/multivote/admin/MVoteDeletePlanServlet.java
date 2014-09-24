/*
 * Created on 2007-6-22
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.multivote.admin;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.addressbook.handler.AddressbookHandler;
import com.icss.oa.addressbook.vo.AddressbookFolderVO;
import com.icss.oa.multivote.handler.VoteLiuHandler;
import com.icss.oa.log.handler.LogHandler;
import java.util.List;
import java.util.*;

/**
 *删除计划
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MVoteDeletePlanServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Connection conn = null;
		List artidlist=null;		
		List personidlist=null;
		List valueidlist=null;
		int count_art=0;
		int count_value=0;
		//Integer planPlanid = Integer.valueOf(request.getParameter("planPlanid"));
		Integer planPlanid = request.getParameter("planPlanid")==null?(new Integer(0)):(Integer.valueOf(request.getParameter("planPlanid")));
		
		System.out.println("进入++++++DeletePlanServlet++++++++++planPlanid="+planPlanid.toString());
		try {
			conn =
				DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			
			VoteLiuHandler handler = new VoteLiuHandler(conn);
			
			//判断是否拥有投票表样信息	
			if(handler.hasVoteTable(planPlanid)){
				System.out.println("1111111");
				Integer tid=handler.getVoteTableId(planPlanid);
				System.out.println("tid="+tid);
				if(tid!=null)
				{
					System.out.println("222222");
					System.out.println("此表下拥有table记录");
					//判断是否拥有文章记录
					count_art=handler.hasVoteArticle(tid);
					if(count_art!=0){
						artidlist = handler.getVoteArtIdlist(tid,count_art);
						System.out.println("已得到artidlist,size()="+artidlist.size());
						Iterator itr_artid = artidlist.iterator();
						System.out.println("111111");									
						int ca=artidlist.size();
						int k=0;
						
						while(k<ca){
					//	while(itr_artid.hasNext()){
							Integer artid=Integer.valueOf(itr_artid.next().toString());
							//Integer artid = (Integer)itr_artid.next();
							System.out.println("artid"+k+"="+artid);
							k++;
						//判断是否拥有投票值记录
							System.out.println("22222");
							count_value=handler.hasVoteValueByArtid(artid);
							System.out.println("count_value="+count_value);
							if(count_value!=0){
								
								/*valueidlist = handler.getVoteValueIdlist(artid,count_value);
								System.out.println("已得到valueidlist,size()="+valueidlist.size());
								Iterator itr_valueid = valueidlist.iterator();
								System.out.println("333333");
								int cv =  valueidlist.size();
								int j=0;
								while(j<cv){
									System.out.println("444444");
									Integer valueid = Integer.valueOf(itr_valueid.next().toString());
									System.out.println("valueid"+"j="+valueid);
									j++;
								}*/
								//调用删除投票值纪录的函数
								handler.deleteValueByArtid(artid);
								}
						}
						handler.deleteArtical(tid);
					}
					//判断是否拥有选项记录
					if(handler.hasVoteItem(tid)){
						handler.deleteItem(tid);
					}
					
				}
				handler.deleteVotetable(planPlanid);
				//handler.deleteArtical(planPlanid);
			}
			
			if(handler.hasVotePerson(planPlanid)){
				System.out.println("有person记录");
				/*personidlist=handler.getVotePersonIdlist(planPlanid);
				Iterator itr_personid=personidlist.iterator();
				while(itr_personid.hasNext()){
					Integer personid = (Integer)itr_personid.next();
					if(handler.hasVoteValueByPersonid(personid)){
						handler.deleteValueByPersonid(personid);
					}
				}*/
				handler.deleteVoteperson(planPlanid);
			}
			
			//删除计划记录
			handler.deletePlan(planPlanid);
			 this.forward(request, response, "/servlet/MVotePlanManagerListServlet");
			//response.sendRedirect("ArticalOptionListServlet?mainid=" + mainid);

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
