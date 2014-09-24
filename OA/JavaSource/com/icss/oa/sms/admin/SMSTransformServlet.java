package com.icss.oa.sms.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.sms.handler.SMSHandler;
import com.icss.oa.sms.util.SMSSend;
import com.icss.oa.sms.vo.SMSPersonRoleSearchVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class SMSTransformServlet extends ServletBase {

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("GBK");
		String inputPhones = request.getParameter("phonenums");
		String content = request.getParameter("content");
		String s1 = request.getParameter("addPerson_person");
		String s2 = request.getParameter("addPerson_group");
		String s3 = request.getParameter("addPerson_org");
		String s4 = request.getParameter("addPerson_oagroup");
		String userlist = request.getParameter("sendtoperson");

		Connection conn = null;
		Context ctx = null;

		try {
			conn = getConnection(Globals.DATASOURCEJNDI);

			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String senderuuid = user.getPersonUuid();
			String sendername = user.getCnName();

			SMSHandler handler = new SMSHandler(conn);
			String orgname = handler.getOrgName(senderuuid);
			//内容加尾
			content =content+"["+sendername+"]"+"["+orgname+"]";
			//System.out.println("###########"+content);
			
			List phonelist = handler.getSendtoPhone(s1, s2, s3,s4);
			String sendedNames = phonelist.get(0).toString();
			String seletPhones = phonelist.get(1).toString();
			String unsendNames = phonelist.get(3).toString();
			Integer selectedNum = new Integer(phonelist.get(4).toString());
			
			
			// 判断发送人权限
			List roleList = handler.getPersonSMSRole(senderuuid);
			Integer isout = new Integer(0);
			Integer sendnumber = new Integer(20);
			Iterator rl = roleList.iterator();
			while (rl.hasNext()) {
				SMSPersonRoleSearchVO vo = (SMSPersonRoleSearchVO) rl.next();
				if (vo.getIsout().intValue() >= isout.intValue()) {
					isout = vo.getIsout();
				}
				if (vo.getSendnumber().intValue() >= sendnumber.intValue()) {
					sendnumber = vo.getSendnumber();
				}
			}
			
			//System.out.println("!!!!!!!!!" + userlist);
			
			request.setAttribute("isout", isout);
			request.setAttribute("sendnumber", sendnumber);
			request.setAttribute("selectedNum", selectedNum);
			
			request.setAttribute("sendedNames", sendedNames);
			request.setAttribute("unsendNames", unsendNames);
			request.setAttribute("userlist", userlist);
			if (selectedNum.intValue() > sendnumber.intValue()) {
				this.forward(request, response, "/sms/sendSMS.jsp");
			} else {
				// 接收人的姓名和手工输入的号码
				String receivername = sendedNames + inputPhones;
				//System.out.println("@@@@@@@@"+receivername);
				// 所有接收的手机号
				String phonenums = seletPhones + inputPhones;
				//System.out.println("@@@@@#@@@"+phonenums);
				//短信ID
				String smsid = handler.getSMSIdbyUUID(senderuuid);
				if (receivername != null && receivername.length()>0 && phonenums != null) {
					SMSSend sd = new SMSSend();
					//System.out.println("!!!!!!!"+phonenums+content);
					sd.toSend(phonenums, content,smsid);
					// 存入数据库
					handler.setHistory(senderuuid, sendername, receivername,
							content);
				}
				response.setContentType("text/html; charset=gbk");
				this.forward(request, response, "/sms/sendResult.jsp");

			}
		}catch(RuntimeException e){
			this.handleError(e);
		}catch (Exception e) {
			System.out.println("连接不到FTP服务器");
			this.forward(request, response, "/sms/sendFalse.jsp");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

}
