/*
 * �������� 2007-3-8
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
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class VoteSaveServlet extends ServletBase {

	/* ���� Javadoc��
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
			if("1".equals(morder.toString())){//����һ��һƱ��ʽ
				//���ݣɣ�ע���û�,���дˣɣ���ֱ��ȡ�����û���Ϣ�����������û�
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
//					System.out.println("++++++++VoteSaveServlet+++++++++���Ӵˣɣ��û�");
				}
				//ȡ���û�ID
				List userlist=new ArrayList();
				userlist=handler.getIpUserVo(request.getRemoteAddr(),mainId);
				Iterator	useritr=userlist.iterator();
				String	username="";
				if(useritr.hasNext()){
					BbsVoteuserVO	uservo=(BbsVoteuserVO) useritr.next();
					userId=uservo.getVuId().toString();
					username=uservo.getVuName();
					
				}
				//�洢ͶƱ��Ϣ
				vo.setVtVotername(username);
				vo.setVtVuid(userId);
				
			}else if("2".equals(morder)){//ע���û�ͶƱ
				//����ע���û�IDȡ���û���Ϣ
				BbsVoteuserVO uservo = (BbsVoteuserVO)handler.findBbsVoteuserByID(userId);
				vo.setVtVotername(uservo.getVuName());
				vo.setVtVuid(userId);
				
			}else if("3".equals(morder)){//����ͶƱ
				//��ע���û���Ϣ
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
			}else if("4".equals(morder)){//OA�û�ͶƱ
				
				
				try{
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					String uuid = user.getPersonUuid();
					String	oauserid=user.getUserID();
					String	userenname=user.getEnName();
					String 	usercnname=user.getCnName();
//					����UUIDע���û�,���дˣɣ���ֱ��ȡ�����û���Ϣ�����������û�
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
//						System.out.println("++++++++VoteSaveServlet+++++++++���Ӵˣɣ��û�");
					}
					
					
					
					
					vo.setVtVotername(usercnname);
					vo.setVtVuid(userId);
					vo.setVtVoteruuid(uuid);
				}catch(Exception e){
					handleError(e) ;
				}
				
				
				
			}
			System.out.println("++++++++VoteSaveServlet+++++++++ȡ���û��ɣ�userId="+userId);
			//�洢ͶƱ��Ϣ

			vo.setMainid(new Integer(mainId));
			vo.setVtVoterip(request.getRemoteAddr());

			Long CurrentTime = new Long(System.currentTimeMillis());
			vo.setVtVotetime(CurrentTime.toString());
			BbsMainarticleVO mainvo = (BbsMainarticleVO) handler.findBbsMainarticleByID(mainId);

			if ("��ѡ".equals(mainvo.getType())) {
				String vote = (String) request.getParameter("opt") == null ? "0" : (String) request.getParameter("opt");
				vo.setVtSinglenum(vote);
				vo.setVtMultinum("0");
				BbsOptionsVO opvo = handler.getOptionVO(vote);
				opvo.setOpNum(new Integer(new Integer(opvo.getOpNum()).intValue()+1).toString());
				handler.updateOption(opvo);

				vo.setVtOpid(Integer.valueOf(vote));//���ӵ�ѡͶƱopid
				handler.addVote(vo);
				System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++�ɹ�����ͶƱ��Ϣ");
			}
			if ("��ѡ".equals(mainvo.getType())) {
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
				
			}if ("���".equals(mainvo.getType())) {
				//System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++��ֱ���ͶƱ��Ϣ00000000");
				String multi =  (String) request.getParameter("multi");
				String optid = "";
				String	optscore="0";
				for(int i=0;i<new Integer(multi).intValue();i++){
					
					optid = request.getParameter("optid_"+new Integer(i).toString()) ;
					optscore = request.getParameter("opt_"+new Integer(i).toString()) ;
					System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++��ֱ���ͶƱ��Ϣoptid="+optid+"optscore="+optscore);
					vo.setVtScore(optscore);
					vo.setVtOpid(new Integer(optid));
					BbsOptionsVO opvo = handler.getOptionVO(optid);
					opvo.setOpScore(String.valueOf(new Integer(opvo.getOpScore()==null?"0":opvo.getOpScore()).intValue()+new Integer(optscore).intValue()));
					System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++��ֱ���ͶƱ��Ϣoptscore="+optscore+"optid="+optid);
					handler.updateOption(opvo);
					handler.addVote(vo);
					System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++��ֱ���ͶƱ��Ϣ");
				}
				}if ("����".equals(mainvo.getType())) {
					//System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++��ֱ���ͶƱ��Ϣ00000000");
					String multi =  (String) request.getParameter("multi");
					String optid = "";
					String	optword="";
					for(int i=0;i<new Integer(multi).intValue();i++){
						
						optid = request.getParameter("optid_"+new Integer(i).toString()) ;
						optword = request.getParameter("opt_"+new Integer(i).toString()) ;
						
						vo.setVtWord(optword);
						vo.setVtOpid(new Integer(optid));
						handler.addVote(vo);
						System.out.println("+++++++++++++++++VoteSaveServlet++++++++++++���鱣��ͶƱ��Ϣoptid="+optid+"optscore="+optword);
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
