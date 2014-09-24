package com.icss.oa.zbs.xtsduty.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.zbs.xtsduty.handler.XtsWorkInfoHandler;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfoVO;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfomainVO;
import com.icss.oa.zbs.xtsduty.vo.TbXtsWorkinfotypeVO;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @version 1.0
 * @author liupei
 */
public class XtsDutyWholeAddServlet extends ServletBase implements Servlet {

	/**
	 * @see com.icss.j2ee.servlet.ServletBase#void
	 *      (javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		try {
			ConnLog.open("YjsDutyWholeAddServlet");
			conn = DBConnectionLocator.getInstance().getConnection(
					Globals.DATASOURCEJNDI);
			// �Ƿ񽻰�
			String isChange = request.getParameter("isChange");

			XtsWorkInfoHandler handler = new XtsWorkInfoHandler(conn);
			List typeList = handler.getMainDutyTypeListByClause(" 1=1");

			Date now = new Date();
			DateFormat d = DateFormat.getDateInstance();
			String daystr = d.format(now);

			String mainId = "";
			String toid = "";
			TbXtsWorkinfomainVO vo = new TbXtsWorkinfomainVO();

			Context ctx = Context.getInstance();
			String userId = ctx.getCurrentPersonUuid();
			// UserMsgHandler userMsghandler = new UserMsgHandler(conn);
			// String userId = userMsghandler.getUserId();
			vo.setWitCreater(userId);
			String leader = request.getParameter("leader");
			if (leader == null) {
				leader = "";
			}
			vo.setWitLeader(leader);

			String secret = request.getParameter("secret");
			if (secret == null) {
				secret = "";
			}
			vo.setWitSecret(secret);

			// vo.setWitDate(dateTime);
			String type = request.getParameter("dutytype");
			if (type == null) {
				type = "";
			}
			vo.setWitClass(type);
			Timestamp dateTime = new Timestamp(now.getTime());
			String addnot = request.getParameter("addnot");
			System.err.println("addnot is " + addnot);
			if ("1".equals(addnot)) {
				//System.out.println("����YjsDutyWholeSAddServlet,addnot=1,ʱ��="+dateTime);
				mainId = request.getParameter("mainId");
				toid = mainId;
				TbXtsWorkinfomainVO vo1 = handler.getMainDutyById(mainId);
				vo1.setWitClass(vo.getWitClass());
				vo1.setWitLeader(vo.getWitLeader());
				vo1.setWitSecret(vo.getWitSecret());
				vo1.setFlag("0");//����ʶ��Ϊ0,��ʾ��������־�༭���
				Integer id = handler.editDutyMainInfo(vo1);//����workinfomainvo��wim_id

				if (id != null) {
					// ��ʼ�������������Ϣ
					String[] content = new String[typeList.size()];
					String[] infoids = new String[typeList.size()];
					for (int i = 0; i < typeList.size(); i++) {
						TbXtsWorkinfotypeVO tvo = (TbXtsWorkinfotypeVO) typeList
								.get(i);
						content[i] = request.getParameter("type_fck_"
								+ tvo.getWitId().toString());

						TbXtsWorkinfoVO infovo = new TbXtsWorkinfoVO();

						infovo.setWimId(id);
						infovo.setWitId(tvo.getWitId());
						infovo.setWitContent(content[i]);
						infovo.setWitCreater(userId);

						// infovo.setWitCreatetime(dateTime);
						infoids[i] = request.getParameter("infoid_"
								+ tvo.getWitId().toString());
						// System.err.println("!!infoids!!="+infoids[i]);
						if ("".equals(infoids[i]) || infoids[i] == null) {
							// System.err.println("!!infoids!!="+infoids[i]);
							infovo.setWitCreater(userId);
							infovo.setWitModifyer(userId);
							infovo.setWitModifytime(dateTime);
							Integer infoid = handler.newDutyContentInfo(infovo);//����һ��workinfo��¼��������workinfo������wi_id
						} else {
							TbXtsWorkinfoVO infovo1 = handler
									.getDutyInfobyId(infoids[i]);
							// System.err.println("infovo1="+infovo1);
							infovo1.setWitContent(infovo.getWitContent());
							infovo1.setWitModifyer(userId);
							infovo1.setWitModifytime(dateTime);

							// infovo.setWiId(new Integer(infoids[i]));
							Integer infoid = handler
									.updateDutyContentInfo(infovo1);
						}
					}
				}
			} else if (isChange != null && isChange.equalsIgnoreCase("y")) {
				System.out.println("+++++++++++++++++++++++����");
				// ȡ�����һ��
				TbXtsWorkinfomainVO mvo = handler.getLastDuty();
				System.out.println("+++++++++++++++++++++++++++"+mvo.getWimId());
				List list = handler.getLastInfo(mvo.getWimId());//һ��workinfomain��Ӧ����workinfo�����졢�Ѱ졢�ر���ʾ��
				vo.setWitDate(dateTime);
				vo.setWitCreatetime(dateTime);
				// ����MainVO
				Integer id = handler.newDutyMainInfo(vo);

				if (id != null) {
					toid = id.toString();
					for (int i = 0; i < list.size(); i++) {

						TbXtsWorkinfoVO tvo = (TbXtsWorkinfoVO) list.get(i);
						// System.out.println("++++++++++++++++"+tvo.getWitContent());
						TbXtsWorkinfoVO infovo = new TbXtsWorkinfoVO();
						infovo.setWimId(id);
						infovo.setWitId(tvo.getWitId());
						if(tvo.getWitId()==2){//�Ѱ�
							infovo.setWitContent(null);
						}else{
							infovo.setWitContent(tvo.getWitContent());
						}
						infovo.setWitCreater(userId);
						infovo.setWitCreatetime(dateTime);

						Integer infoid = handler.newDutyContentInfo(infovo);
						
					}
				}

			} else {
				// System.out.println(isChange);
				// System.out.println("++++++++++++++++comehere");
				vo.setWitDate(dateTime);
				vo.setWitCreatetime(dateTime);
				// ����MainVO
				Integer id = handler.newDutyMainInfo(vo);

				if (id != null) {
					toid = id.toString();
					// ��ʼ�������������Ϣ
					String[] content = new String[typeList.size()];
					for (int i = 0; i < typeList.size(); i++) {
						TbXtsWorkinfotypeVO tvo = (TbXtsWorkinfotypeVO) typeList
								.get(i);
						content[i] = request.getParameter("type_fck_"
								+ tvo.getWitId().toString());
						TbXtsWorkinfoVO infovo = new TbXtsWorkinfoVO();
						infovo.setWimId(id);
						infovo.setWitId(tvo.getWitId());
						infovo.setWitContent(content[i]);
						infovo.setWitCreater(userId);
						infovo.setWitCreatetime(dateTime);

						Integer infoid = handler.newDutyContentInfo(infovo);
					}
				}
			}
			
			/*
			//�༭״̬
			if ("1".equals(addnot)){
				System.out.println("addnot=1,����YjsDutyViewServlet");
			response.sendRedirect(request.getContextPath()
						+ "/servlet/YjsDutyViewServlet?wimid=" + toid);
			}else{
			response.sendRedirect(request.getContextPath()
					+ "/servlet/YjsMainDutyViewServlet?wimid=" + toid);
			}*/
			
			//System.out.println("��YjsDutyWholeAddServlet�У�����ת��YjsMainDutyViewServlet");
			response.sendRedirect(request.getContextPath()
					+ "/servlet/XtsMainDutyViewServlet?wimid=" + toid);
			
		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO �Զ����� catch ��
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					System.out.println("��XtsDutyWholeAddServlet�У�closconn");
					conn.close();
					ConnLog.close("XtsDutyWholeAddServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
