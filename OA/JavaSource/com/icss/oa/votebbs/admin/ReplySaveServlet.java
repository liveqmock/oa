/*
 * �������� 2007-3-8
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ReplySaveServlet extends ServletBase {

	/* ���� Javadoc��
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO �Զ����ɷ������
		Connection conn = null;
		try {
			conn = super.getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI);
			String mainId = request.getParameter("mainId")==null?"1":request.getParameter("mainId");
			String replyName = request.getParameter("replytitle")==null?"":request.getParameter("replytitle");
			String replyContent = request.getParameter("replycontent")==null?"":request.getParameter("replycontent");
			String userId = (String)request.getParameter("userId")==null?"0":(String)request.getParameter("userId");
			String morder = (String)request.getParameter("morder")==null?"0":(String)request.getParameter("morder");
			
			BbsVoteHandler handler = new BbsVoteHandler(conn);
			if("1".equals(morder.toString())){//����һ��һƱ��ʽ
//			���ݣɣ�ע���û�,���дˣɣ���ֱ��ȡ�����û���Ϣ�����������û�
				if(!handler.hasIpUser(request.getRemoteAddr(),mainId)){
					BbsVoteuserVO	newuservo=new BbsVoteuserVO();
					newuservo.setVuIp(request.getRemoteAddr());
					newuservo.setVuLoginname(request.getRemoteAddr());
					newuservo.setVuName(request.getRemoteAddr());
					newuservo.setVuPassword("1");
					newuservo.setVuPost(request.getParameter("replywork"));
					newuservo.setVuSex(request.getParameter("replysex"));
					handler.addUser(newuservo);
//				System.out.println("++++++++VoteSaveServlet+++++++++���Ӵˣɣ��û�");
				}
				List userlist=new ArrayList();
				userlist=handler.getIpUserVo(request.getRemoteAddr(),mainId);
				Iterator	useritr=userlist.iterator();
				if(useritr.hasNext()){
					BbsVoteuserVO	uservo=(BbsVoteuserVO) useritr.next();
					userId=uservo.getVuId().toString();
//				System.out.println("++++++++VoteSaveServlet+++++++++ȡ���û��ɣ�userId="+userId);
				}
			
			}else if("2".equals(morder)){//ע���û�ͶƱ
				
				userId = (String)request.getParameter("userId")==null?"0":(String)request.getParameter("userId");
			}else if("3".equals(morder)){//����ͶƱ	
//				��ע���û���Ϣ
				BbsVoteuserVO	newuservo=new BbsVoteuserVO();
				newuservo.setVuIp(request.getRemoteAddr());
				newuservo.setVuLoginname(request.getRemoteAddr());
				newuservo.setVuName(request.getRemoteAddr());
				newuservo.setVuPassword("1");
				newuservo.setVuMainid(mainId);
				newuservo.setVuPost(request.getParameter("work"));
				newuservo.setVuSex(request.getParameter("sex"));
				userId=handler.addUser(newuservo).toString();
			}else if("4".equals(morder)){//OA�û�ͶƱ
				try{
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					String uuid = user.getPersonUuid();
					String	oauserid=user.getUserID();
					String	userenname=user.getEnName();
					String 	usercnname=user.getCnName();
	//				����UUIDע���û�,���дˣɣ���ֱ��ȡ�����û���Ϣ�����������û�
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
	//					System.out.println("++++++++VoteSaveServlet+++++++++���Ӵˣɣ��û�");
					}
					

				}catch(Exception e){
					handleError(e) ;
				}
			
			}
			
			System.out.println("++++++++ReplySaveServlet+++++++++ȡ���û��ɣ�userId="+userId);
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

				vo.setReSendername("����");
			}
			handler.addReply(vo);
			String dist = request.getContextPath()+ "/servlet/ArticleShowServlet?mainId=" + mainId +"&userId="+userId+"&morder="+morder ;
			response.sendRedirect(dist);
			
		} catch (ServiceLocatorException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

}
