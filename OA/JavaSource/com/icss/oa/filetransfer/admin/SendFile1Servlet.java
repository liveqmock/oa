/*
 * Created on 2004-4-28
 */
package com.icss.oa.filetransfer.admin;

import gnu.regexp.REException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.StringTokenizer;

import javax.mail.SendFailedException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;

import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.filelog.FileLogFactory;
import com.icss.j2ee.services.ServiceLocatorException;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;
import com.icss.oa.config.FileTransferConfig;
import com.icss.oa.filetransfer.handler.FileTransferHandler;
import com.icss.oa.filetransfer.handler.FiletransferSetHandler;
import com.icss.oa.filetransfer.handler.FiletransferTimeHandler;
import com.icss.oa.filetransfer.handler.SendFileBean;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.filetransfer.util.FindMailRun;
import com.icss.oa.filetransfer.util.MailhandlerFactory;
import com.icss.oa.filetransfer.util.ThreadsPool;
import com.icss.oa.util.CommUtil;
import com.icss.oa.util.PropertyManager;
import com.icss.oa.util.StringUtility;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;


/**
 * �����ļ�
 */
public class SendFile1Servlet extends ServletBase{    
	
	protected void performTask( HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		

//--------------------------------------------------------------------------------------------------------------------------------
// 1.��ȡ���ݵĲ���
//--------------------------------------------------------------------------------------------------------------------------------

		String topic = request.getParameter("topic")==null?"������":request.getParameter("topic");			//����
		String content = request.getParameter("content")==null?"":request.getParameter("content");		//����
		//String sendcc = request.getParameter("sendcc")==null?"":request.getParameter("sendcc");			//����
		//String sendbcc = request.getParameter("sendbcc")==null?"":request.getParameter("sendbcc") ;		//����
		String sun_flag = request.getParameter("sun_flag");		//Ϊ�˵õ�ȱʡ�Ļ��ص�ί/������hr�ĵ�ַ
		
		//�Ƿ񱣴浽������
		String isSent1 = request.getParameter("isSent");

		//�Ƿ����û�ִ
		String isRe = request.getParameter("isRe");
		
		
		//		edit by yangyang 20050701
		  //������־����Ϣͷ
		  String LOG_HEAD = " [���⣺"+topic+"] ";
	
		  //��־����
		  LogFactory factory = new FileLogFactory("FileTransfor");
		  //��־����
		  Log log = factory.newInstance(this.getClass(),LOG_HEAD);
			  
		boolean send_flag = false;
		boolean cc_flag = false;
		boolean bcc_flag = false;
		
		
		//�ֱ�õ������˵���Ϣ
		String s1 = request.getParameter("addPerson_person");

		String s2 = request.getParameter("addPerson_group");

		String s3 = request.getParameter("addPerson_oagroup");

		String s10 = request.getParameter("addPerson_org");

		send_flag = ((s1!=null&&!"null".equals(s1)&&!"".equals(s1))
				   ||(s2!=null&&!"null".equals(s2)&&!"".equals(s2))
				   ||(s3!=null&&!"null".equals(s3)&&!"".equals(s3))
				   ||(s10!=null&&!"null".equals(s10)&&!"".equals(s10)));
	               
		//�ֱ�õ������˵���Ϣ
		String s4 = request.getParameter("addcc_person");
		String s5 = request.getParameter("addcc_group");
		String s6 = request.getParameter("addcc_oagroup");
		String s11 = request.getParameter("addcc_org");

		

		cc_flag = ((s4!=null&&!"null".equals(s4)&&!"".equals(s4))
				   ||(s5!=null&&!"null".equals(s5)&&!"".equals(s5))
				   ||(s6!=null&&!"null".equals(s6)&&!"".equals(s6))
				   ||(s11!=null&&!"null".equals(s11)&&!"".equals(s11)));
				   
		//�ֱ�õ������˵���Ϣ
		String s7 = request.getParameter("addbcc_person");
		String s8 = request.getParameter("addbcc_group");
		String s12 = request.getParameter("addbcc_org");
		String s9 = request.getParameter("addbcc_oagroup");
		

		
		bcc_flag = ((s7!=null&&!"null".equals(s7)&&!"".equals(s7))
						   ||(s8!=null&&!"null".equals(s8)&&!"".equals(s8))
						   ||(s9!=null&&!"null".equals(s9)&&!"".equals(s9))
						   ||(s12!=null&&!"null".equals(s12)&&!"".equals(s12)));
		
		log.info("�Ƿ񱣴浽������ "+isSent1);
		log.info("�Ƿ����û�ִ "+isRe);
		
		//log.info("sendcc ="+sendcc);
		//log.info("sendbcc"+sendbcc);		
		
		
//Ϊ��log4j���������-------------------------------------------------------------		
		
//		String prefix =  getServletContext().getRealPath("/");
////		String file = getInitParameter("log4j");
//		// if the log4j-init-file is not set, then no point in trying
//		System.out.println("................log4j start="+prefix+File.separator+"log4j.properties");
		

//Ϊ��log4j���������-------------------------------------------------------------		  
		
		
		//��ʼʱ��
		long timeBegin = System.currentTimeMillis();
		//����һ��ʼʱ��	
		long step1 = System.currentTimeMillis();
		
		log.debug("*** *** *** *** *** *** �������ʼ���ʼ *** *** *** *** *** *** ");
		log.debug("--- step1 --- [��ȡ���ݵĲ���] ִ�п�ʼ ---------------------------------------------------- ");

		//0516Ϊ�˽�ʹ�����������������ύ
		//String addPerson = request.getParameter("addPerson");
		//String addcc = request.getParameter("addcc");
		//String addbcc = request.getParameter("addbcc");

		String ip = PropertyManager.getProperty("archivesip");
		String senddomain = PropertyManager.getProperty("archivesdomain");


		log.debug("�ʼ����� topic = " + topic);
		log.debug("�ʼ����� content = " + content);
		log.debug("��־λ   sun_flag = " + sun_flag);
		log.debug("IP��ַ   ip = " + ip);
		log.debug("�ʼ����� senddomain = " + senddomain);
		log.debug("--- step1 --- [��ȡ���ݵĲ���] ִ����� ---------------------------------------------------- ");




//--------------------------------------------------------------------------------------------------------------------------------
// 2.��������
//--------------------------------------------------------------------------------------------------------------------------------
		
		log.debug("--- step2 --- [��������] ִ�п�ʼ ---------------------------------------------------- ");

		StringBuffer sendResult = new StringBuffer();		//�ɷ��͵�ַ
		StringBuffer overFlowResult = new StringBuffer();	//ʣ��ռ䲻��
		StringBuffer noPersonResult = new StringBuffer();	//�޵�ַ���
		
		String subject = "";				//����
		String text = "";					//����
		StringBuilder to = new StringBuilder();				//����
		StringBuilder cc = new StringBuilder();				//����
		StringBuilder bcc = new StringBuilder();				//����
		String[] attachment = null;		//����
		int flag = FileTransferConfig.ONLY_SEND;		
		String originUserAddress = "";
		String ccAddress = "";
		String bccAddress = "";

		request.setAttribute("title1","�ļ�����");
		request.setAttribute("title2","���ͽ��");

		//�����ʼ����������
		dirmanage mailhandler = null;
		//����
		Connection conn = null;
		
		log.debug("--- step2 --- [��������] ִ����� ---------------------------------------------------- ");
		
		try {


//--------------------------------------------------------------------------------------------------------------------------------
// 3.��ʼ�������Ϣ
//--------------------------------------------------------------------------------------------------------------------------------
				
			log.debug("--- step3 --- [��ʼ�������Ϣ] ִ�п�ʼ ---------------------------------------------------- ");		

			//������
			Context ctx = Context.getInstance();
			//ȡ�÷�������Ϣ
			UserInfo user = ctx.getCurrentLoginInfo();
			//���ʼ�������
			String chinaName = user.getCnName();	

			//ȡ������
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			log.debug("�ɹ���ȡConnection���ݿ����ӣ�");

			//�ļ����䴦����
			FileTransferHandler handler = new FileTransferHandler(conn);
			//������־ͷ edit by yangyang 20050701
			handler.setLoghead(LOG_HEAD);
			
			log.debug("����FileTransferHandler�ʼ����䴦�����");

			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			//����UUID�����û�������USERID
			String userid = ftsHandler.getUserid(user.getPersonUuid());
			

			//ȡ�õ�ǰϵͳʱ��
			long nowtime = System.currentTimeMillis();			
			//���͵ı���Ϊ������Ϸ��͵�ʱ�䴮��Ϊɾ�����������
			
			
			
			subject = topic.concat(",").concat(String.valueOf(nowtime));
			//subject = topic.concat(",").concat("1210089661000");
			
			//��ʽ���ļ����ݸ�Ҫ
			text = CommUtil.formathtm(content);
			
			
			log.debug("���������� chinaName =" + chinaName );
			log.debug("����userid = " + userid);
			log.debug("��ʽ���ʼ����� subject = " + subject);
			log.debug("��ʽ���ļ����� text = " + text);
						
			long step3 = System.currentTimeMillis();
			long step3_1 = step3 - step1;
			
			log.debug("--- step3 --- [��ʼ�������Ϣ] ִ�н��� ---------------------------------------------------- ");
			log.debug("--- step1 to step3 ��ʱ"+step3_1+"���룡Լ��"+step3_1/1000+"�룡 ------------------------------------------------- ");




//--------------------------------------------------------------------------------------------------------------------------------
// 4.�����ʼ�����
//--------------------------------------------------------------------------------------------------------------------------------
			log.debug("--- step4 --- [������] ִ�п�ʼ ---------------------------------------------------- ");			

			//ȡ��������
			long mailmemory = 0;
			String filenum = request.getParameter("filenum");
			String realnum = request.getParameter("realnum");		
			if(filenum==null || "".equals(filenum)){
				filenum = "0";
			}
			if(realnum==null || "".equals(realnum)){
				realnum = "0";
			}
			StringBuffer path = new StringBuffer(FiletransferUtil.getUploadFilePath());
			path.append(userid).append(System.currentTimeMillis()).append(File.separator);
			File uploadpath = new File(path.toString());  
			if (!uploadpath.exists()) {
				uploadpath.mkdirs();
			}
			if(!"0".equals(realnum)){
				attachment = new String[Integer.parseInt(realnum)];
			}
			int j=-1;
			for(int i=0;i<Integer.parseInt(filenum);i++){
				String filename = "";
				String compname = "file_"+i;
				String fileFillName = getUploadFileFullName(request, compname);
				if("null".equals(fileFillName.substring(fileFillName.lastIndexOf("/")+1))){
					continue;
				}
				j++;
				File tempf = new File(fileFillName);
				mailmemory = mailmemory + tempf.length();
				
				filename = getUploadOldFileName(request, compname);
				if(filename !=null){
					filename = StringUtility.CtoB(filename);
				}
				int index = 0;
				index = filename.lastIndexOf("\\");
				if (index != -1) {
					filename = filename.substring(index + 1);
				}
				String filepath = path.toString()+filename;
				InputStream ins = new FileInputStream(fileFillName);
				FileUtil.copy(ins, new File(filepath));
				//�ر�����by lintl
				ins.close();
				attachment[j] = filepath;
			}
			
			//ȡ���Ѵ��ڸ���
			Enumeration en = request.getParameterNames();
			while(en.hasMoreElements()){
				String temp = (String)en.nextElement();
				if(temp.indexOf("exist_")>=0){
					j++;
					String tf = (String)request.getParameter(temp);
					attachment[j] = tf;
					
					File t = new File(tf);
					mailmemory = mailmemory + t.length();
				}
			}
			
			log.debug("������С="+mailmemory);			

//--------------------------------------------------------------------------------------------------------------------------------
// 5.�����͵�ַ
//--------------------------------------------------------------------------------------------------------------------------------
			
			log.debug("--- step5 --- [�����͵�ַ] ִ�п�ʼ ---------------------------------------------------- ");			
			//�õ��ɷ��͵��û���ַ
			StringBuffer noAddressPerson = new StringBuffer();
			
			//Ϊ�˵õ�ȱʡ�Ļ��ص�ί�ĵ�ַ
			List sendlist = null;
			
			try {
				if("party".equals(sun_flag)){ 
					//ȡ�û��ص�ί���ʼ���ַ���Ƶļ���
					sendlist = handler.getSendtoAddressForjiguandangwei(request,senddomain);
				} else if("hr".equals(sun_flag)){	
					sendlist = handler.getSendtoAddressForhr(request,senddomain);  
					}else {
					//ȡ��Ҫ���͵��ʼ���ַ����
					sendlist = handler.getSendtoAddress0830(s1,s2,s3,s10,senddomain);

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			//���صĶ���Ϊ����Ϊ2�ļ���
			//Ԫ��[0]Ϊ���Է��͵�EMAIL��ַ�ļ���
			//Ԫ��[1]Ϊ�޵�ַ�޷����͵��û������ļ���			
			//ȡ�ò��Ϸ���ַ���û����Ƶļ���
			if(!(sendlist.get(0).toString().equals(""))){
				//�����һ��ѡ����û�������Ϊ�գ���Ѵ����Ƽ��뵽noAddressPerson�ַ�����
				noAddressPerson.append(sendlist.get(0).toString());
			}
			//���Է��͵��û���EMAIL��ַ�ļ���

			originUserAddress = sendlist.get(1).toString();


			StringTokenizer toList = new StringTokenizer(originUserAddress.toString(),",");
			int tokenLength = toList.countTokens();
			//to = new String[tokenLength];
			for(int x=0;x<tokenLength;x++){
				String tot =  toList.nextToken();
				String tuid = tot.substring(0,tot.indexOf("@"));
				if(handler.isEnoughSpace(tuid, mailmemory)){
					to.append(tot);
					to.append(",");
				}else{
					overFlowResult.append(tuid);
					overFlowResult.append(",");
				}
				//to[x] = toList.nextToken();
			}
			
			log.debug("--- step5 --- [�����͵�ַ] ִ�н��� ---------------------------------------------------- ");
			
//			���淢��ʱ�� add by lxy
			List alluuidlist = handler.getAlluuidlist();
			FiletransferTimeHandler  ftth = new FiletransferTimeHandler(conn);
			int num = alluuidlist.size();
			for(int i=0;i<num;i++){
				ftth.setRecord(user.getPersonUuid(),(String)alluuidlist.get(i) , (new Date()).getTime());
			}


//--------------------------------------------------------------------------------------------------------------------------------
// 6.ȡ�ó����û���ַ
//--------------------------------------------------------------------------------------------------------------------------------		
			log.debug("--- step6 --- [������͵�ַ] ִ�п�ʼ ---------------------------------------------------- ");	
			//�õ������û���ַ
				
			//���͵�ַ��Ϊ�գ�������͵�ַ
			if(cc_flag){
				//Ԫ��[0]Ϊ�޷����͵��޵�ַ���û���������
				//Ԫ��[1]Ϊ���Բ��͵��е�ַ���ʼ���ַ����
				
				List sendcclist = null;
				
				try {
					sendcclist = handler.getSendtoAddress0830(s4,s5,s6,s11,senddomain);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
				ccAddress = sendcclist.get(1).toString();

				StringTokenizer ccList = new StringTokenizer(ccAddress.toString(),",");
				int cckenLength = ccList.countTokens();
				//cc = new String[cckenLength];
				for(int y=0;y<cckenLength;y++){
					String cot =  ccList.nextToken();
					String cuid = cot.substring(0,cot.indexOf("@"));
					if(handler.isEnoughSpace(cuid, mailmemory)){
						to.append(cot);
						to.append(",");
					}else{
						overFlowResult.append(cuid);
						overFlowResult.append(",");
					}
					
					//cc[y] = ccList.nextToken();
				}
				
			}
			log.debug("--- step6 --- [������͵�ַ] ִ�н��� ---------------------------------------------------- ");
			
			
			
//---------------------------------------------------------------------------------------------------------------------------------
// 7.ȡ�������û���ַ
//---------------------------------------------------------------------------------------------------------------------------------
			log.debug("--- step7 --- [�������͵�ַ] ִ�п�ʼ ---------------------------------------------------- ");			
			//�õ������û���ַ
			
			//���͵�ַ��Ϊ�գ��������͵�ַ
			if(bcc_flag){
				
				//ȡ�������ʼ���ַ�ļ���
				List sendbcclist = null;
				try {
					sendbcclist = handler.getSendtoAddress0830(s7,s8,s9,s12,senddomain);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				
				bccAddress = sendbcclist.get(1).toString();

				StringTokenizer bccList = new StringTokenizer(bccAddress.toString(),",");
				int bcckenLength = bccList.countTokens();
				//bcc = new String[bcckenLength];
				for(int z=0;z<bcckenLength;z++){
					String bcot =  bccList.nextToken();
					String bcuid = bcot.substring(0,bcot.indexOf("@"));
					if(handler.isEnoughSpace(bcuid, mailmemory)){
						to.append(bcot);
						to.append(",");
					}else{
						overFlowResult.append(bcuid);
						overFlowResult.append(",");
					}
					//bcc[z] = bccList.nextToken();
				}
				
			}
			log.debug("--- step7 --- [�������͵�ַ] ִ�н��� ---------------------------------------------------- ");
			
			// send + cc + bcc
			String allsendlist = (to.toString() + cc.toString() + bcc.toString()).trim() ;

			
//---------------------------------------------------------------------------------------------------------------------------------
// 9.�����ʼ�
//---------------------------------------------------------------------------------------------------------------------------------

			log.debug("--- step9 --- [�����ʼ�] ִ�п�ʼ ---------------------------------------------------- ");
  
			
			if("checked1".equals(isRe)&&"checked2".equals(isSent1)){
			
				flag = FileTransferConfig.TO_SEND_RE;
			
			}else if(!"checked1".equals(isRe)&&"checked2".equals(isSent1)){
				
				flag = FileTransferConfig.TO_SEND;
			
			}else if("checked1".equals(isRe)&&!"checked2".equals(isSent1)){
				
				flag = FileTransferConfig.SEND_RE;
			}
			
			log.debug("���͵��ʼ��Ƿ񱣴浽������-------"+flag);
			log.debug("���͵��ʼ��Ƿ���Ҫ��ִ-------"+isRe);
			
			//			���������ʼ�������			
			mailhandler = MailhandlerFactory.getHandler(userid);
			log.debug("�ɹ�����dirmanage�����ʼ��������");
			
			//���������һ���Ľ��յ�ַ������ʼ����з���
			if(allsendlist.length()>0){  
				log.debug("�ʼ����͵�ַ��  "+allsendlist);  

				//���浽������
				String isSent = request.getParameter("isSent");
				if("checked2".equals(isSent)){
					log.debug("���浽�����俪ʼ��");
					long save0 = System.currentTimeMillis();
//					String subject0 = topic.concat(Long.toString(System.currentTimeMillis()));
					handler.saveToSent(mailhandler,request.getContextPath(),mailmemory,userid,chinaName,senddomain,subject,text,attachment,flag, allsendlist.toString());
					long save1 = System.currentTimeMillis();
					long save = save1 - save0;
					log.debug("���浽�������������ʱ"+save+"���룡Լ��"+save/1000+"�룡");  
				}
				
				
				log.debug("�ʼ���������  "+subject);  
				log.debug("�ʼ�������  "+text);
				
				if(attachment!=null){
					for (int i = 0; i < attachment.length; i++) {
						log.debug("�����ڷ������ϵ�λ���ǣ�"+attachment[i]);
					}
				}else{
						log.debug("���ʼ��޸���");
				}
				
				log.debug("�Ƿ�Ҫ��ִ?�����3����OK��   "+flag);
				
//				����
				log.debug("���ú����ʼ���������� �����ʼ���ʼ��");
				long send0 = System.currentTimeMillis();
				
				String[] toperson = to.length()>0?to.toString().split(","):null;
				String[] ccperson = cc.length()>0?cc.toString().split(","):null;
				String[] bccperson = bcc.length()>0?bcc.toString().split(","):null;

				
				String[][] failuser = mailhandler.transfermail(subject,text,toperson,ccperson,bccperson,attachment,flag);


//				logger.info("-----------------������"+topic+"------end!!!--------------");
//				BasicConfigurator.configure(); 
				
				long send1 = System.currentTimeMillis();
				long sendTake = send1 - send0;
				log.debug("���ú����ʼ���������� �����ʼ���������ʱ"+sendTake+"���룡Լ��"+sendTake/1000+"�룡");
				
				
				//�����ʼ������¼
				log.debug("���ʹ����ʼ���¼��ʼ��");
				long intend0 = System.currentTimeMillis();

				StringTokenizer yesList = new StringTokenizer(allsendlist.toString(),",");
				while (yesList.hasMoreTokens()) {
					String yesUserID = yesList.nextToken();
					sendResult.append(ftsHandler.getCName(yesUserID.substring(0,yesUserID.indexOf("@"))));
					sendResult.append(",");
					
					int yesindex = yesUserID.indexOf("@");
					yesUserID = yesUserID.substring(0,yesindex);
					
	
					//���뵽����
					String intendtopic = "���ʼ�������"+chinaName+"��"+CommUtil.getTime(System.currentTimeMillis())+"��";
					String tointendId = userid + "," + subject + "," + yesUserID;
					//handler.addToIntend(request.getContextPath(),yesUserID,intendtopic,tointendId);
					Thread t = new Thread(new FindMailRun(userid,subject,nowtime,intendtopic,tointendId,yesUserID,request.getContextPath()));
					ThreadsPool.getInstance().putTask(t);
					
				}
				long intend1 = System.currentTimeMillis();
				long intend = intend1 - intend0;
				log.debug("���ʹ����ʼ���¼��������ʱ"+intend+"���룡Լ��"+intend/1000+"�룡");
			}
			
			//δ�����ʼ����촦�� add by lintl
			if(overFlowResult.length()>0){
				String[] no = overFlowResult.toString().split(",");
				for(String nouserid :no){
				String intendtopic = "����������������������  "+chinaName+" ���ʼ�δ���յ�����"+CommUtil.getTime(System.currentTimeMillis())+"��";
				handler.addToIntend(request.getContextPath(),nouserid,intendtopic,"nomail");
				}
			}
			request.getSession().setAttribute("overFlowResult", overFlowResult.toString());

			
			request.getSession().setAttribute("sendResult", sendResult.toString());
			long timeEnd = System.currentTimeMillis();
			long timeTake = timeEnd - timeBegin;
			
			log.debug("�����ʼ����� ȫ����ʱ"+timeTake+"���룡��Լ��"+timeTake/1000+"�룡");
			//request.getSession().setAttribute("failResult", "����ʧ��(1cs��0");
			//String m = (String)request.getSession().getAttribute("failResult");
			//System.out.println("failResult = " + m);
			response.sendRedirect("MailRefreshServlet");
			
			//forward(request,response,"/oabase/mail/SendMail_Body.jsp?sun_flag=err");
		}catch (SendFailedException e) {
			//log.debug("SendFailedException = " + e.getMessage());
			log.error("SendFailedException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			//request.setAttribute("failResult","����ʧ��(1)��");
			request.getSession().setAttribute("failResult", "����ʧ��(1)��");
			response.sendRedirect("MailRefreshServlet");
		} catch (IOException e) {
			//log.debug("IOException = " + e.getMessage());
			log.error("IOException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "����������������������ʧ��(2)��");
			response.sendRedirect("MailRefreshServlet");
		} catch (EntityException e) {
			//log.debug("EntityException = " + e.getMessage());
			log.error("EntityException = " + e.getMessage(),e);		
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "����ʧ��(3)��");
			response.sendRedirect("MailRefreshServlet");
		} catch (ServiceLocatorException e) {
			//log.debug("ServiceLocatorException = " + e.getMessage());
			log.error("ServiceLocatorException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "����ʧ��(4)��");
			response.sendRedirect("MailRefreshServlet");
		} catch (NamingException e) {
			//log.debug("NamingException = " + e.getMessage());
			log.error("NamingException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "����ʧ��(5)��");
			response.sendRedirect("MailRefreshServlet");
		}  catch (REException e) {
			//log.debug("REException = " + e.getMessage());
			log.error("REException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "����ʧ��(6)��");
			response.sendRedirect("MailRefreshServlet");
		} catch (Exception e) {
			//log.debug("MailException = " + e.getMessage());
			log.error("MailException = " + e.getMessage(),e);
			e.printStackTrace();			
			request.getSession().setAttribute("failResult", "��������");
			response.sendRedirect("MailRefreshServlet");
		} 
		finally {
			
			if (conn != null){
				try {
					//�ر�����
					conn.close();
					log.debug("�����ʼ� finally �ɹ��ر����ݿ����ӣ�");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if(mailhandler!=null){
				try {
					//�ر��ʼ�����
					mailhandler.disconnect();
					log.debug("�����ʼ� finally �ɹ��رպ����ʼ����������ӣ�");
					
				} catch (LdapException e) {
					e.printStackTrace();
				}
			}
			
			
			//ɾ���������ϵ��ݴ渽���������ϴ��ģ�
			if(attachment!=null&&attachment.length>0){
				
				for(int attachnum=0;attachnum<attachment.length;attachnum++){
					
					int pathindex = attachment[attachnum].lastIndexOf(File.separator);
					String privatedir = attachment[attachnum].substring(0,pathindex);
					//��ǰһ�������ļ�
					File attachfile = new File(attachment[attachnum]);
					
					log.debug("�����ʼ� finally ɾ����������ʱ���� attachfile["+attachnum+"] = " + attachfile);
					
					//ɾ�������ļ�
					if(attachfile.exists()){
						attachfile.delete();
					}
					
					//�����ļ���
					File dirfile = new File(privatedir);
					//ɾ�������ļ�
					if(dirfile.exists()){
						dirfile.delete();
					}
				}	
			}
		}
	}
	

	
	//	��΢�루Long����ʱ��ת��Ϊ�ַ�������ʽ
	public String getTimeByLong(Long time) {
		try {
			Date date = new Date(time.longValue());
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd:HH mm ss");
			String formatTime = formatter.format(date);
			return formatTime;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
