/*
 * 创建日期 2007-3-8
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.votebbs.admin;

import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.vo.BbsReplyVO;
import com.icss.oa.votebbs.vo.BbsVoteuserVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class ReplySaveServlet extends ServletBase {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成方法存根
		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			String mainId = request.getParameter("mainId")==null?"1":request.getParameter("mainId");
			String replyName = request.getParameter("replytitle")==null?"":request.getParameter("replytitle");
			String replyContent = request.getParameter("replycontent")==null?"":request.getParameter("replycontent");
			String userId = (String)request.getParameter("userId")==null?"0":(String)request.getParameter("userId");
			String morder = (String)request.getParameter("morder")==null?"0":(String)request.getParameter("morder");
			
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			if("1".equals(morder.toString())){//内网一机一票方式
//			根据ＩＰ注册用户,若有此ＩＰ则直接取出此用户信息，否则新增用户
				if(!handler.hasIpUser(request.getRemoteAddr(),mainId)){
					BbsVoteuserVO	newuservo=new BbsVoteuserVO();
					newuservo.setVuIp(request.getRemoteAddr());
					newuservo.setVuLoginname(request.getRemoteAddr());
					newuservo.setVuName(request.getRemoteAddr());
					newuservo.setVuPassword("1");
					newuservo.setVuPost(request.getParameter("replywork"));
					newuservo.setVuSex(request.getParameter("replysex"));
					handler.addUser(newuservo);
//				System.out.println("++++++++VoteSaveServlet+++++++++增加此ＩＰ用户");
				}
				List userlist=new ArrayList();
				userlist=handler.getIpUserVo(request.getRemoteAddr(),mainId);
				Iterator	useritr=userlist.iterator();
				if(useritr.hasNext()){
					BbsVoteuserVO	uservo=(BbsVoteuserVO) useritr.next();
					userId=uservo.getVuId().toString();
//				System.out.println("++++++++VoteSaveServlet+++++++++取得用户ＩＤuserId="+userId);
				}
			
			}else if("2".equals(morder)){//注册用户投票
				
				userId = (String)request.getParameter("userId")==null?"0":(String)request.getParameter("userId");
			}else if("3".equals(morder)){//任意投票	
//				新注册用户信息
				BbsVoteuserVO	newuservo=new BbsVoteuserVO();
				newuservo.setVuIp(request.getRemoteAddr());
				newuservo.setVuLoginname(request.getRemoteAddr());
				newuservo.setVuName(request.getRemoteAddr());
				newuservo.setVuPassword("1");
				newuservo.setVuMainid(mainId);
				newuservo.setVuPost(request.getParameter("work"));
				newuservo.setVuSex(request.getParameter("sex"));
				userId=handler.addUser(newuservo).toString();
			}else if("4".equals(morder)){//OA用户投票
				try{
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					String uuid = user.getPersonUuid();
					String	oauserid=user.getUserID();
					String	userenname=user.getEnName();
					String 	usercnname=user.getCnName();
	//				根据UUID注册用户,若有此ＩＰ则直接取出此用户信息，否则新增用户
					if(!handler.hasUUIDUser(uuid,mainId)){
						BbsVoteuserVO	newuservo=new BbsVoteuserVO();
						newuservo.setVuIp(request.getRemoteAddr());
						newuservo.setVuLoginname(oauserid);
						newuservo.setVuName(usercnname);
						newuservo.setVuPassword("1");
						newuservo.setVuMainid(mainId);
						newuservo.setVuUuid(uuid);
						newuservo.setVuPost(request.getParameter("work"));
						newuservo.setVuSex(request.getParameter("sex"));
						userId=handler.addUser(newuservo).toString();
	//					System.out.println("++++++++VoteSaveServlet+++++++++增加此ＩＰ用户");
					}
					

				}catch(Exception e){
					handleError(e) ;
				}
			
			}
			
			System.out.println("++++++++ReplySaveServlet+++++++++取得用户ＩＤuserId="+userId);
			BbsReplyVO vo = new BbsReplyVO();
			vo.setMainid(new Integer(mainId));
			vo.setReSenderip(request.getRemoteAddr());
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datestr = df.format(date);
			vo.setReTime(datestr);			
			vo.setReTitle(replyName);
			vo.setReContext(replyContent);
			vo.setReVuid(userId);
			if(!"0".equals(userId)&&!"-1".equals(userId)){
				BbsVoteuserVO uvo = handler.findBbsVoteuserByID(userId);
				vo.setReSendername(uvo.getVuName());
				vo.setReSenderuuid(uvo.getVuUuid());
			}else{

				vo.setReSendername("匿名");
			}
			handler.addReply(vo);
			String dist = request.getContextPath()+ "/servlet/ArticleShowServlet?mainId=" + mainId +"&userId="+userId+"&morder="+morder ;
			response.sendRedirect(dist);
			
		} catch (ServiceLocatorException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

}
