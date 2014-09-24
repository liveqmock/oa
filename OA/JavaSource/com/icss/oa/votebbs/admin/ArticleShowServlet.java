/*
 * �������� 2007-3-6
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
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
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class ArticleShowServlet extends ServletBase {

	/* ���� Javadoc��
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
			
			
			//ȡ��������Ϣ
			BbsMainarticleVO vo = handler.findBbsMainarticleByID(mainId);
			morder=vo.getMOrder();
			if("1".equals(morder)){//����һ��һƱ
				
				//�����û��ɣ�ȡ���û���D
				System.out.println("++++++++����һ��һƱ+++++++++");
				if(handler.hasIpUser(request.getRemoteAddr(),mainId)){//�ж��Ƿ��д�IP�û�,������ֱ��ȡ�ô�IP�û�ID
					System.out.println("++++++++����һ��һƱ+++++++++request.getRemoteAddr()="+request.getRemoteAddr());
					List userlist=new ArrayList();
					userlist=handler.getIpUserVo(request.getRemoteAddr(),mainId);
					Iterator	useritr=userlist.iterator();
					if(useritr.hasNext()){
						BbsVoteuserVO	uservo=(BbsVoteuserVO) useritr.next();
						userId=uservo.getVuId();
						System.out.println("++++++++����һ��һƱ+++++++++ȡ���û��ɣ�userId="+userId);
					}
				}
				List hasvotelist=handler.getVoteVOList(request.getRemoteAddr().toString(),mainId.toString());
				String	hasvote="0";
				if(hasvotelist.size()>0){
					hasvote="1";//��IP�û���Ͷ��Ʊ
				}
				System.out.println("++++++++����һ��һƱ+++++++++hasvote="+hasvote);
				String userId1= userId.toString();
				String notsave= request.getParameter("notsave")==null?"0":request.getParameter("notsave");
				if("1".equals(notsave)){
						String Msginfo = "�벻Ҫ�ظ�ͶƱ��";
						request.setAttribute("Msginfo",Msginfo);
				}
				if("2".equals(notsave)){
						String Msginfo = "����ͶƱ���ύ��";
						request.setAttribute("Msginfo",Msginfo);
				}
				request.setAttribute("hasvote",hasvote);
				request.setAttribute("userId",userId1);
	//			request.setAttribute("userId",userId);
				if(!"0".equals(userId1)){

					//�����û�IDȡ���û���Ϣ
						BbsVoteuserVO uservo = (BbsVoteuserVO)handler.findBbsVoteuserByID(userId1);
						request.setAttribute("uservo",uservo);
				}

			}else if("2".equals(morder)){//�û���ע��ſ�ͶƱ
				if("1".equals(action)){
					//ע���û�����½
					String username = request.getParameter("username")==null?"����":request.getParameter("username");
					String sex = request.getParameter("sex")==null?"":request.getParameter("sex");
					String dep = request.getParameter("dep")==null?"":request.getParameter("dep");
					String work = request.getParameter("work")==null?"":request.getParameter("work");
					userVO.setVuName(username);
					userVO.setVuSex(sex);
					userVO.setVuPost(work);
					userVO.setVuDep(dep);
					userVO.setVuIp(request.getRemoteAddr());
					userId = handler.addUser(userVO);
				}else if("3".equals(action)){//�����û��������¼
					String loginname = request.getParameter("userid");
					String password = request.getParameter("password");
				    List userlist = handler.getUserListByName(loginname,mainId);//��������鿴�û����Ƿ����û���
				    if(userlist==null||userlist.size()==0){//�û���������
				    	
						String dist = request.getContextPath()+ "/servlet/BbsLoginServlet?status=3&mainId="+mainId;
						response.sendRedirect(dist);			
						return;		
				    }else{//���ڴ��û���
				    	BbsVoteuserVO uservo = (BbsVoteuserVO)userlist.get(0);
						if(!uservo.getVuPassword().equals(password)){//��֤���벻��ȷ
							String dist = request.getContextPath()+ "/servlet/BbsLoginServlet?status=4&mainId="+mainId ;
							response.sendRedirect(dist);	
							return;
						}
						
						request.setAttribute("uservo",uservo);
						request.setAttribute("userId",uservo.getVuId().toString());
						userId=uservo.getVuId();

				    }
				    
				  
				}else {//�����û�cookie��������ʽ���û�ID��ҳ��ת��ķ�ʽ,votesaveservlet������ͶƱҳ��
//										
					String userid123 = request.getParameter("userId")==null?"-1":request.getParameter("userId");
					request.setAttribute("userId",userid123);
					BbsVoteuserVO uservo = handler.findBbsVoteuserByID(userid123);
					request.setAttribute("uservo",uservo);
					userId=new Integer(userid123);
									
				}
				
				//�ж��Ƿ���Ͷ��Ʊ
				List hasvotelist=handler.getUserIdVoteVOList(userId,mainId.toString());
				System.out.println("++++++++++++++ע���û�ͶƱ++++++++userid="+userId+"�ж��Ƿ���Ͷ��Ʊ="+hasvotelist.size());
				String	hasvote="0";
				if(hasvotelist.size()>0){
					hasvote="1";//��IP�û���Ͷ��Ʊ
				}
				request.setAttribute("hasvote",hasvote);
			}else if("3".equals(morder)){//����ͶƱ
			
			//�����û��ɣ�ȡ���û���D
			System.out.println("++++++++����ͶƱ+++++++++");
			String userId1 = request.getParameter("userId")==null?"-1":request.getParameter("userId");
			String	hasvote="0";
			
			System.out.println("++++++++����ͶƱ+++++++++hasvote="+hasvote);
						
			String notsave= request.getParameter("notsave")==null?"0":request.getParameter("notsave");
			if("1".equals(notsave)){
					String Msginfo = "�벻Ҫ�ظ�ͶƱ��";
					request.setAttribute("Msginfo",Msginfo);
			}
			if("2".equals(notsave)){
					String Msginfo = "����ͶƱ���ύ��";
					request.setAttribute("Msginfo",Msginfo);
			}
			request.setAttribute("hasvote",hasvote);
			request.setAttribute("userId",userId1);
//			request.setAttribute("userId",userId);
			if((!"0".equals(userId1))&&(!"-1".equals(userId1))){

				//�����û�IDȡ���û���Ϣ
					BbsVoteuserVO uservo = (BbsVoteuserVO)handler.findBbsVoteuserByID(userId1);
					request.setAttribute("uservo",uservo);
			}
		}else if("4".equals(morder)){//����OA�û�ͶƱ
			try{
				System.out.println("++++++++����OA�û�ͶƱ+++++++++");
				Context ctx = Context.getInstance();
				UserInfo user = ctx.getCurrentLoginInfo();
				String uuid = user.getPersonUuid();
				String	userid=user.getUserID();
				//String	userenname=user.getEnName();
				String 	usercnname=user.getCnName();
				//System.out.println("++++++++����OA�û�ͶƱ+++++++++uuid="+uuid);
				//ȡ�ô�UUID�û���ͶƱ�б�
				List hasvotelist=handler.getUUIDUserVoteList(uuid,mainId);
				String userId1 = request.getParameter("userId")==null?"-1":request.getParameter("userId");
				if(handler.hasUUIDUser(uuid,mainId)){//�鿴�û����Ƿ��д�UUID���û���������ȡ��userIdֵ
					List userlist=handler.getUUIDUserList(uuid,mainId);
					Iterator	useritr=userlist.iterator();
					if(useritr.hasNext()){
						BbsVoteuserVO uservo=(BbsVoteuserVO) useritr.next();
						userId1=uservo.getVuId().toString();
					}
				}
				
				
				String	hasvote="0";
				if(hasvotelist.size()>0){
					hasvote="1";//��IP�û���Ͷ��Ʊ
				}
				
				System.out.println("++++++++����OA�û�ͶƱ+++++++++hasvote="+hasvote+"userId="+userId1+"usercnname="+usercnname);
				request.setAttribute("hasvote",hasvote);
				request.setAttribute("userId",userId1);
				if((!"0".equals(userId1))&&(!"-1".equals(userId1))){

					//�����û�IDȡ���û���Ϣ
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
//			ȡ�ûظ��б�
		List replylist = new ArrayList();
		String clause = " MAINID="+mainId;
		replylist = handler.getReplyListByClause(clause);

		//ȡ��ѡ���б�
		List optionsList = new ArrayList();
		optionsList = handler.getOptionsListByClause(clause);	
		

		
		request.setAttribute("mainId",mainId);
		request.setAttribute("optionslist",optionsList);
		request.setAttribute("vo",vo);
		request.setAttribute("replylist",replylist);
		dist = "/bbsvote/replyShow.jsp";
		//ȡ�ÿ��ŵ�ͶƱ�����б�
		List mainlist = handler.getMainListByClause(" STATUS='����' AND M_ORDER <> '4' ");
		request.setAttribute("morder",morder);
		request.setAttribute("mainlist",mainlist);
		
		forward(request,response,dist);	
			
		} catch (ServiceLocatorException e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		}
	}

}
