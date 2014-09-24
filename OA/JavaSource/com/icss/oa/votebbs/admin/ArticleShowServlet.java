/*
 * 创建日期 2007-3-6
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.vo.BbsMainarticleVO;
import com.icss.oa.votebbs.vo.BbsVoteuserVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class ArticleShowServlet extends ServletBase {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			String mainId = request.getParameter("mainId")==null?"1":request.getParameter("mainId");
			String action = request.getParameter("action")==null?"2":request.getParameter("action");
			String morder = request.getParameter("morder")==null?"0":request.getParameter("morder");
			System.out.println("++++++++ArticleShowServlet+++++++++morder="+morder);
			BbsVoteuserVO userVO = new BbsVoteuserVO();
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			Integer userId = new Integer(0);
			
			
			//取得主题信息
			BbsMainarticleVO vo = handler.findBbsMainarticleByID(mainId);
			morder=vo.getMOrder();
			if("1".equals(morder)){//内网一机一票
				
				//根据用户ＩＰ取得用户ＩD
				System.out.println("++++++++内网一机一票+++++++++");
				if(handler.hasIpUser(request.getRemoteAddr(),mainId)){//判断是否有此IP用户,若有则直接取得此IP用户ID
					System.out.println("++++++++内网一机一票+++++++++request.getRemoteAddr()="+request.getRemoteAddr());
					List userlist=new ArrayList();
					userlist=handler.getIpUserVo(request.getRemoteAddr(),mainId);
					Iterator	useritr=userlist.iterator();
					if(useritr.hasNext()){
						BbsVoteuserVO	uservo=(BbsVoteuserVO) useritr.next();
						userId=uservo.getVuId();
						System.out.println("++++++++内网一机一票+++++++++取得用户ＩＤuserId="+userId);
					}
				}
				List hasvotelist=handler.getVoteVOList(request.getRemoteAddr().toString(),mainId.toString());
				String	hasvote="0";
				if(hasvotelist.size()>0){
					hasvote="1";//此IP用户已投过票
				}
				System.out.println("++++++++内网一机一票+++++++++hasvote="+hasvote);
				String userId1= userId.toString();
				String notsave= request.getParameter("notsave")==null?"0":request.getParameter("notsave");
				if("1".equals(notsave)){
						String Msginfo = "请不要重复投票！";
						request.setAttribute("Msginfo",Msginfo);
				}
				if("2".equals(notsave)){
						String Msginfo = "您的投票已提交！";
						request.setAttribute("Msginfo",Msginfo);
				}
				request.setAttribute("hasvote",hasvote);
				request.setAttribute("userId",userId1);
	//			request.setAttribute("userId",userId);
				if(!"0".equals(userId1)){

					//根据用户ID取得用户信息
						BbsVoteuserVO uservo = (BbsVoteuserVO)handler.findBbsVoteuserByID(userId1);
						request.setAttribute("uservo",uservo);
				}

			}else if("2".equals(morder)){//用户需注册才可投票
				if("1".equals(action)){
					//注册用户并登陆
					String username = request.getParameter("username")==null?"匿名":request.getParameter("username");
					String sex = request.getParameter("sex")==null?"":request.getParameter("sex");
					String dep = request.getParameter("dep")==null?"":request.getParameter("dep");
					String work = request.getParameter("work")==null?"":request.getParameter("work");
					userVO.setVuName(username);
					userVO.setVuSex(sex);
					userVO.setVuPost(work);
					userVO.setVuDep(dep);
					userVO.setVuIp(request.getRemoteAddr());
					userId = handler.addUser(userVO);
				}else if("3".equals(action)){//根据用户名密码登录
					String loginname = request.getParameter("userid");
					String password = request.getParameter("password");
				    List userlist = handler.getUserListByName(loginname,mainId);//根据输入查看用户表是否有用户名
				    if(userlist==null||userlist.size()==0){//用户名不存在
				    	
						String dist = request.getContextPath()+ "/servlet/BbsLoginServlet?status=3&mainId="+mainId;
						response.sendRedirect(dist);			
						return;		
				    }else{//存在此用户名
				    	BbsVoteuserVO uservo = (BbsVoteuserVO)userlist.get(0);
						if(!uservo.getVuPassword().equals(password)){//验证密码不正确
							String dist = request.getContextPath()+ "/servlet/BbsLoginServlet?status=4&mainId="+mainId ;
							response.sendRedirect(dist);	
							return;
						}
						
						request.setAttribute("uservo",uservo);
						request.setAttribute("userId",uservo.getVuId().toString());
						userId=uservo.getVuId();

				    }
				    
				  
				}else {//根据用户cookie或其它方式（用户ID由页面转入的方式,votesaveservlet）进入投票页面
//										
					String userid123 = request.getParameter("userId")==null?"-1":request.getParameter("userId");
					request.setAttribute("userId",userid123);
					BbsVoteuserVO uservo = handler.findBbsVoteuserByID(userid123);
					request.setAttribute("uservo",uservo);
					userId=new Integer(userid123);
									
				}
				
				//判断是否已投过票
				List hasvotelist=handler.getUserIdVoteVOList(userId,mainId.toString());
				System.out.println("++++++++++++++注册用户投票++++++++userid="+userId+"判断是否已投过票="+hasvotelist.size());
				String	hasvote="0";
				if(hasvotelist.size()>0){
					hasvote="1";//此IP用户已投过票
				}
				request.setAttribute("hasvote",hasvote);
			}else if("3".equals(morder)){//任意投票
			
			//根据用户ＩＰ取得用户ＩD
			System.out.println("++++++++任意投票+++++++++");
			String userId1 = request.getParameter("userId")==null?"-1":request.getParameter("userId");
			String	hasvote="0";
			
			System.out.println("++++++++任意投票+++++++++hasvote="+hasvote);
						
			String notsave= request.getParameter("notsave")==null?"0":request.getParameter("notsave");
			if("1".equals(notsave)){
					String Msginfo = "请不要重复投票！";
					request.setAttribute("Msginfo",Msginfo);
			}
			if("2".equals(notsave)){
					String Msginfo = "您的投票已提交！";
					request.setAttribute("Msginfo",Msginfo);
			}
			request.setAttribute("hasvote",hasvote);
			request.setAttribute("userId",userId1);
//			request.setAttribute("userId",userId);
			if((!"0".equals(userId1))&&(!"-1".equals(userId1))){

				//根据用户ID取得用户信息
					BbsVoteuserVO uservo = (BbsVoteuserVO)handler.findBbsVoteuserByID(userId1);
					request.setAttribute("uservo",uservo);
			}
		}else if("4".equals(morder)){//内网OA用户投票
			try{
				System.out.println("++++++++内网OA用户投票+++++++++");
				Context ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				String uuid = user.getPersonUuid();
				String	userid=user.getUserID();
				//String	userenname=user.getEnName();
				String 	usercnname=user.getCnName();
				//System.out.println("++++++++内网OA用户投票+++++++++uuid="+uuid);
				//取得此UUID用户的投票列表
				List hasvotelist=handler.getUUIDUserVoteList(uuid,mainId);
				String userId1 = request.getParameter("userId")==null?"-1":request.getParameter("userId");
				if(handler.hasUUIDUser(uuid,mainId)){//查看用户表是否有此UUID的用户，若有则取出userId值
					List userlist=handler.getUUIDUserList(uuid,mainId);
					Iterator	useritr=userlist.iterator();
					if(useritr.hasNext()){
						BbsVoteuserVO uservo=(BbsVoteuserVO) useritr.next();
						userId1=uservo.getVuId().toString();
					}
				}
				
				
				String	hasvote="0";
				if(hasvotelist.size()>0){
					hasvote="1";//此IP用户已投过票
				}
				
				System.out.println("++++++++内网OA用户投票+++++++++hasvote="+hasvote+"userId="+userId1+"usercnname="+usercnname);
				request.setAttribute("hasvote",hasvote);
				request.setAttribute("userId",userId1);
				if((!"0".equals(userId1))&&(!"-1".equals(userId1))){

					//根据用户ID取得用户信息
						BbsVoteuserVO uservo = (BbsVoteuserVO)handler.findBbsVoteuserByID(userId1.toString());
						request.setAttribute("uservo",uservo);
				}else{
					BbsVoteuserVO uservo=new BbsVoteuserVO();
					uservo.setVuIp(request.getRemoteAddr());
					uservo.setVuLoginname(userid);
					uservo.setVuName(usercnname);
					request.setAttribute("uservo",uservo);
				}
				
			}catch(Exception e){
				handleError(e) ;
			}
			
			
			
		}
		String dist = "";
//			取得回复列表
		List replylist = new ArrayList();
		String clause = " MAINID="+mainId;
		replylist = handler.getReplyListByClause(clause);

		//取得选项列表
		List optionsList = new ArrayList();
		optionsList = handler.getOptionsListByClause(clause);	
		

		
		request.setAttribute("mainId",mainId);
		request.setAttribute("optionslist",optionsList);
		request.setAttribute("vo",vo);
		request.setAttribute("replylist",replylist);
		dist = "/bbsvote/replyShow.jsp";
		//取得开放的投票主题列表
		List mainlist = handler.getMainListByClause(" STATUS='开放' AND M_ORDER <> '4' ");
		request.setAttribute("morder",morder);
		request.setAttribute("mainlist",mainlist);
		
		forward(request,response,dist);	
			
		} catch (ServiceLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}
