/*
 * 创建日期 2007-3-8
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.vo.BbsMainarticleVO;
import com.icss.oa.votebbs.vo.BbsOptionsVO;
import com.icss.oa.votebbs.vo.BbsVoteVO;
import com.icss.oa.votebbs.vo.BbsVoteuserVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author 王苏
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class VoteSaveServlet extends ServletBase {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			String mainId = request.getParameter("mainId") == null ? "0" : request.getParameter("mainId");
			String userId = (String) request.getParameter("userId") == null ? "0" : (String) request.getParameter("userId");
			String morder = request.getParameter("morder") == null ? "0" : request.getParameter("morder");
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			BbsVoteVO vo = new BbsVoteVO();
			if("1".equals(morder.toString())){//内网一机一票方式
				//根据ＩＰ注册用户,若有此ＩＰ则直接取出此用户信息，否则新增用户
				if(!handler.hasIpUser(request.getRemoteAddr(),mainId)){
					BbsVoteuserVO	newuservo=new BbsVoteuserVO();
					newuservo.setVuIp(request.getRemoteAddr());
					newuservo.setVuLoginname(request.getRemoteAddr());
					newuservo.setVuName(request.getRemoteAddr());
					newuservo.setVuPassword("1");
					newuservo.setVuMainid(mainId);
					newuservo.setVuPost(request.getParameter("work"));
					newuservo.setVuSex(request.getParameter("sex"));
					handler.addUser(newuservo);
//					System.out.println("++++++++VoteSaveServlet+++++++++增加此ＩＰ用户");
				}
				//取得用户ID
				List userlist=new ArrayList();
				userlist=handler.getIpUserVo(request.getRemoteAddr(),mainId);
				Iterator	useritr=userlist.iterator();
				String	username="";
				if(useritr.hasNext()){
					BbsVoteuserVO	uservo=(BbsVoteuserVO) useritr.next();
					userId=uservo.getVuId().toString();
					username=uservo.getVuName();
					
				}
				//存储投票信息
				vo.setVtVotername(username);
				vo.setVtVuid(userId);
				
			}else if("2".equals(morder)){//注册用户投票
				//根据注册用户ID取得用户信息
				BbsVoteuserVO uservo = (BbsVoteuserVO)handler.findBbsVoteuserByID(userId);
				vo.setVtVotername(uservo.getVuName());
				vo.setVtVuid(userId);
				
			}else if("3".equals(morder)){//任意投票
				//新注册用户信息
				BbsVoteuserVO	newuservo=new BbsVoteuserVO();
				newuservo.setVuIp(request.getRemoteAddr());
				newuservo.setVuLoginname(request.getRemoteAddr());
				newuservo.setVuName(request.getRemoteAddr());
				newuservo.setVuPassword("1");
				newuservo.setVuMainid(mainId);
				newuservo.setVuPost(request.getParameter("work"));
				newuservo.setVuSex(request.getParameter("sex"));
				userId=handler.addUser(newuservo).toString();
				vo.setVtVotername(request.getRemoteAddr().toString());
				vo.setVtVuid(userId);
			}else if("4".equals(morder)){//OA用户投票
				
				
				try{
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					String uuid = user.getPersonUuid();
					String	oauserid=user.getUserID();
					String	userenname=user.getEnName();
					String 	usercnname=user.getCnName();
//					根据UUID注册用户,若有此ＩＰ则直接取出此用户信息，否则新增用户
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
//						System.out.println("++++++++VoteSaveServlet+++++++++增加此ＩＰ用户");
					}
					
					
					
					
					vo.setVtVotername(usercnname);
					vo.setVtVuid(userId);
					vo.setVtVoteruuid(uuid);
				}catch(Exception e){
					handleError(e) ;
				}
				
				
				
			}
			System.out.println("++++++++VoteSaveServlet+++++++++取得用户ＩＤuserId="+userId);
			//存储投票信息

			vo.setMainid(new Integer(mainId));
			vo.setVtVoterip(request.getRemoteAddr());

			Long CurrentTime = new Long(System.currentTimeMillis());
			vo.setVtVotetime(CurrentTime.toString());
			BbsMainarticleVO mainvo = (BbsMainarticleVO) handler.findBbsMainarticleByID(mainId);

			if ("单选".equals(mainvo.getType())) {
				String vote = (String) request.getParameter("opt") == null ? "0" : (String) request.getParameter("opt");
				vo.setVtSinglenum(vote);
				vo.setVtMultinum("0");
				BbsOptionsVO opvo = handler.getOptionVO(vote);
				opvo.setOpNum(new Integer(new Integer(opvo.getOpNum()).intValue()+1).toString());
				handler.updateOption(opvo);

				vo.setVtOpid(Integer.valueOf(vote));//增加单选投票opid
				handler.addVote(vo);
				System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++成功保存投票信息");
			}
			if ("多选".equals(mainvo.getType())) {
				String multi =  (String) request.getParameter("multi");
				vo.setVtSinglenum("0");
				String[] opt = new String[new Integer(multi).intValue()];
				String optid = "";
				for(int i=0;i<new Integer(multi).intValue();i++){
					optid = request.getParameter("optid_"+new Integer(i).toString()) ;
					BbsOptionsVO opvo = handler.getOptionVO(optid);
					opt[i] = request.getParameter("opt_"+new Integer(i).toString()) ;
					if("good".equals(opt[i])){
						opvo.setOpGood(new Integer(new Integer(opvo.getOpGood()).intValue()+1).toString());
						vo.setVtMultinum("1");
					}
					if("mid".equals(opt[i])){
						opvo.setOpMid(new Integer(new Integer(opvo.getOpMid()).intValue()+1).toString());
						vo.setVtMultinum("2");
					}
					if("bad".equals(opt[i])){
						opvo.setOpBad(new Integer(new Integer(opvo.getOpBad()).intValue()+1).toString());
						vo.setVtMultinum("3");
					}
					handler.updateOption(opvo);
					vo.setVtOpid(new Integer(optid));

					handler.addVote(vo);
				}
				
			}if ("打分".equals(mainvo.getType())) {
				//System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++打分保存投票信息00000000");
				String multi =  (String) request.getParameter("multi");
				String optid = "";
				String	optscore="0";
				for(int i=0;i<new Integer(multi).intValue();i++){
					
					optid = request.getParameter("optid_"+new Integer(i).toString()) ;
					optscore = request.getParameter("opt_"+new Integer(i).toString()) ;
					System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++打分保存投票信息optid="+optid+"optscore="+optscore);
					vo.setVtScore(optscore);
					vo.setVtOpid(new Integer(optid));
					BbsOptionsVO opvo = handler.getOptionVO(optid);
					opvo.setOpScore(String.valueOf(new Integer(opvo.getOpScore()==null?"0":opvo.getOpScore()).intValue()+new Integer(optscore).intValue()));
					System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++打分保存投票信息optscore="+optscore+"optid="+optid);
					handler.updateOption(opvo);
					handler.addVote(vo);
					System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++打分保存投票信息");
				}
				}if ("评议".equals(mainvo.getType())) {
					//System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++打分保存投票信息00000000");
					String multi =  (String) request.getParameter("multi");
					String optid = "";
					String	optword="";
					for(int i=0;i<new Integer(multi).intValue();i++){
						
						optid = request.getParameter("optid_"+new Integer(i).toString()) ;
						optword = request.getParameter("opt_"+new Integer(i).toString()) ;
						
						vo.setVtWord(optword);
						vo.setVtOpid(new Integer(optid));
						handler.addVote(vo);
						System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++评议保存投票信息optid="+optid+"optscore="+optword);
					}
			}
			
			String dist =
				request.getContextPath()
					+ "/servlet/ArticleShowServlet?mainId="
					+ mainId
					+ "&userId="
					+ userId
					+ "&notsave="
					+ 2
					+"&morder="
					+morder;
			response.sendRedirect(dist);
		} catch (ServiceLocatorException e) {

			e.printStackTrace();
		}

	}

}
