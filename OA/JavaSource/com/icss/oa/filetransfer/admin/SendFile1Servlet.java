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
 * 发送文件
 */
public class SendFile1Servlet extends ServletBase{    
	
	protected void performTask( HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		

//--------------------------------------------------------------------------------------------------------------------------------
// 1.获取传递的参数
//--------------------------------------------------------------------------------------------------------------------------------

		String topic = request.getParameter("topic")==null?"无主题":request.getParameter("topic");			//主题
		String content = request.getParameter("content")==null?"":request.getParameter("content");		//内容
		//String sendcc = request.getParameter("sendcc")==null?"":request.getParameter("sendcc");			//操送
		//String sendbcc = request.getParameter("sendbcc")==null?"":request.getParameter("sendbcc") ;		//密送
		String sun_flag = request.getParameter("sun_flag");		//为了得到缺省的机关党委/或者是hr的地址
		
		//是否保存到发件箱
		String isSent1 = request.getParameter("isSent");

		//是否设置回执
		String isRe = request.getParameter("isRe");
		
		
		//		edit by yangyang 20050701
		  //构造日志的消息头
		  String LOG_HEAD = " [主题："+topic+"] ";
	
		  //日志工厂
		  LogFactory factory = new FileLogFactory("FileTransfor");
		  //日志对象
		  Log log = factory.newInstance(this.getClass(),LOG_HEAD);
			  
		boolean send_flag = false;
		boolean cc_flag = false;
		boolean bcc_flag = false;
		
		
		//分别得到发送人的信息
		String s1 = request.getParameter("addPerson_person");

		String s2 = request.getParameter("addPerson_group");

		String s3 = request.getParameter("addPerson_oagroup");

		String s10 = request.getParameter("addPerson_org");

		send_flag = ((s1!=null&&!"null".equals(s1)&&!"".equals(s1))
				   ||(s2!=null&&!"null".equals(s2)&&!"".equals(s2))
				   ||(s3!=null&&!"null".equals(s3)&&!"".equals(s3))
				   ||(s10!=null&&!"null".equals(s10)&&!"".equals(s10)));
	               
		//分别得到抄送人的信息
		String s4 = request.getParameter("addcc_person");
		String s5 = request.getParameter("addcc_group");
		String s6 = request.getParameter("addcc_oagroup");
		String s11 = request.getParameter("addcc_org");

		

		cc_flag = ((s4!=null&&!"null".equals(s4)&&!"".equals(s4))
				   ||(s5!=null&&!"null".equals(s5)&&!"".equals(s5))
				   ||(s6!=null&&!"null".equals(s6)&&!"".equals(s6))
				   ||(s11!=null&&!"null".equals(s11)&&!"".equals(s11)));
				   
		//分别得到秘送人的信息
		String s7 = request.getParameter("addbcc_person");
		String s8 = request.getParameter("addbcc_group");
		String s12 = request.getParameter("addbcc_org");
		String s9 = request.getParameter("addbcc_oagroup");
		

		
		bcc_flag = ((s7!=null&&!"null".equals(s7)&&!"".equals(s7))
						   ||(s8!=null&&!"null".equals(s8)&&!"".equals(s8))
						   ||(s9!=null&&!"null".equals(s9)&&!"".equals(s9))
						   ||(s12!=null&&!"null".equals(s12)&&!"".equals(s12)));
		
		log.info("是否保存到发件箱 "+isSent1);
		log.info("是否设置回执 "+isRe);
		
		//log.info("sendcc ="+sendcc);
		//log.info("sendbcc"+sendbcc);		
		
		
//为了log4j而进行添加-------------------------------------------------------------		
		
//		String prefix =  getServletContext().getRealPath("/");
////		String file = getInitParameter("log4j");
//		// if the log4j-init-file is not set, then no point in trying
//		System.out.println("................log4j start="+prefix+File.separator+"log4j.properties");
		

//为了log4j而进行添加-------------------------------------------------------------		  
		
		
		//开始时间
		long timeBegin = System.currentTimeMillis();
		//步骤一开始时间	
		long step1 = System.currentTimeMillis();
		
		log.debug("*** *** *** *** *** *** 发送新邮件开始 *** *** *** *** *** *** ");
		log.debug("--- step1 --- [获取传递的参数] 执行开始 ---------------------------------------------------- ");

		//0516为了将使用隐含表单进行隐含提交
		//String addPerson = request.getParameter("addPerson");
		//String addcc = request.getParameter("addcc");
		//String addbcc = request.getParameter("addbcc");

		String ip = PropertyManager.getProperty("archivesip");
		String senddomain = PropertyManager.getProperty("archivesdomain");


		log.debug("邮件主题 topic = " + topic);
		log.debug("邮件内容 content = " + content);
		log.debug("标志位   sun_flag = " + sun_flag);
		log.debug("IP地址   ip = " + ip);
		log.debug("邮件域名 senddomain = " + senddomain);
		log.debug("--- step1 --- [获取传递的参数] 执行完成 ---------------------------------------------------- ");




//--------------------------------------------------------------------------------------------------------------------------------
// 2.声明变量
//--------------------------------------------------------------------------------------------------------------------------------
		
		log.debug("--- step2 --- [声明变量] 执行开始 ---------------------------------------------------- ");

		StringBuffer sendResult = new StringBuffer();		//可发送地址
		StringBuffer overFlowResult = new StringBuffer();	//剩余空间不足
		StringBuffer noPersonResult = new StringBuffer();	//无地址结果
		
		String subject = "";				//主题
		String text = "";					//内容
		StringBuilder to = new StringBuilder();				//主送
		StringBuilder cc = new StringBuilder();				//操送
		StringBuilder bcc = new StringBuilder();				//密送
		String[] attachment = null;		//附件
		int flag = FileTransferConfig.ONLY_SEND;		
		String originUserAddress = "";
		String ccAddress = "";
		String bccAddress = "";

		request.setAttribute("title1","文件发送");
		request.setAttribute("title2","发送结果");

		//红旗邮件处理类对象
		dirmanage mailhandler = null;
		//连接
		Connection conn = null;
		
		log.debug("--- step2 --- [声明变量] 执行完成 ---------------------------------------------------- ");
		
		try {


//--------------------------------------------------------------------------------------------------------------------------------
// 3.初始化相关信息
//--------------------------------------------------------------------------------------------------------------------------------
				
			log.debug("--- step3 --- [初始化相关信息] 执行开始 ---------------------------------------------------- ");		

			//上下文
			Context ctx = Context.getInstance();
			//取得发件人信息
			UserInfo user = ctx.getCurrentLoginInfo();
			//发邮件人姓名
			String chinaName = user.getCnName();	

			//取得连接
			conn = this.getConnection(Globals.DATASOURCEJNDI);
			log.debug("成功获取Connection数据库连接！");

			//文件传输处理器
			FileTransferHandler handler = new FileTransferHandler(conn);
			//设置日志头 edit by yangyang 20050701
			handler.setLoghead(LOG_HEAD);
			
			log.debug("构造FileTransferHandler邮件传输处理对象！");

			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			//根据UUID返回用户的邮箱USERID
			String userid = ftsHandler.getUserid(user.getPersonUuid());
			

			//取得当前系统时间
			long nowtime = System.currentTimeMillis();			
			//发送的标题为标题加上发送的时间串，为删除待办滚动用
			
			
			
			subject = topic.concat(",").concat(String.valueOf(nowtime));
			//subject = topic.concat(",").concat("1210089661000");
			
			//格式化文件内容概要
			text = CommUtil.formathtm(content);
			
			
			log.debug("发件人姓名 chinaName =" + chinaName );
			log.debug("邮箱userid = " + userid);
			log.debug("格式化邮件主题 subject = " + subject);
			log.debug("格式化文件内容 text = " + text);
						
			long step3 = System.currentTimeMillis();
			long step3_1 = step3 - step1;
			
			log.debug("--- step3 --- [初始化相关信息] 执行结束 ---------------------------------------------------- ");
			log.debug("--- step1 to step3 用时"+step3_1+"毫秒！约合"+step3_1/1000+"秒！ ------------------------------------------------- ");




//--------------------------------------------------------------------------------------------------------------------------------
// 4.处理邮件附件
//--------------------------------------------------------------------------------------------------------------------------------
			log.debug("--- step4 --- [处理附件] 执行开始 ---------------------------------------------------- ");			

			//取得新添附件
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
				//关闭流，by lintl
				ins.close();
				attachment[j] = filepath;
			}
			
			//取得已存在附件
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
			
			log.debug("附件大小="+mailmemory);			

//--------------------------------------------------------------------------------------------------------------------------------
// 5.处理发送地址
//--------------------------------------------------------------------------------------------------------------------------------
			
			log.debug("--- step5 --- [处理发送地址] 执行开始 ---------------------------------------------------- ");			
			//得到可发送的用户地址
			StringBuffer noAddressPerson = new StringBuffer();
			
			//为了得到缺省的机关党委的地址
			List sendlist = null;
			
			try {
				if("party".equals(sun_flag)){ 
					//取得机关党委的邮件地址名称的集合
					sendlist = handler.getSendtoAddressForjiguandangwei(request,senddomain);
				} else if("hr".equals(sun_flag)){	
					sendlist = handler.getSendtoAddressForhr(request,senddomain);  
					}else {
					//取得要发送的邮件地址集合
					sendlist = handler.getSendtoAddress0830(s1,s2,s3,s10,senddomain);

				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			//返回的对象为长度为2的集合
			//元素[0]为可以发送的EMAIL地址的集合
			//元素[1]为无地址无法发送的用户姓名的集合			
			//取得不合法地址的用户名称的集合
			if(!(sendlist.get(0).toString().equals(""))){
				//如果第一个选择的用户姓名不为空，则把此名称加入到noAddressPerson字符串中
				noAddressPerson.append(sendlist.get(0).toString());
			}
			//可以发送的用户的EMAIL地址的集合

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
			
			log.debug("--- step5 --- [处理发送地址] 执行结束 ---------------------------------------------------- ");
			
//			储存发送时间 add by lxy
			List alluuidlist = handler.getAlluuidlist();
			FiletransferTimeHandler  ftth = new FiletransferTimeHandler(conn);
			int num = alluuidlist.size();
			for(int i=0;i<num;i++){
				ftth.setRecord(user.getPersonUuid(),(String)alluuidlist.get(i) , (new Date()).getTime());
			}


//--------------------------------------------------------------------------------------------------------------------------------
// 6.取得抄送用户地址
//--------------------------------------------------------------------------------------------------------------------------------		
			log.debug("--- step6 --- [处理操送地址] 执行开始 ---------------------------------------------------- ");	
			//得到抄送用户地址
				
			//操送地址不为空，则处理操送地址
			if(cc_flag){
				//元素[0]为无法操送的无地址的用户姓名集合
				//元素[1]为可以操送的有地址的邮件地址集合
				
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
			log.debug("--- step6 --- [处理操送地址] 执行结束 ---------------------------------------------------- ");
			
			
			
//---------------------------------------------------------------------------------------------------------------------------------
// 7.取得密送用户地址
//---------------------------------------------------------------------------------------------------------------------------------
			log.debug("--- step7 --- [处理密送地址] 执行开始 ---------------------------------------------------- ");			
			//得到密送用户地址
			
			//密送地址不为空，则处理密送地址
			if(bcc_flag){
				
				//取得密送邮件地址的集合
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
			log.debug("--- step7 --- [处理密送地址] 执行结束 ---------------------------------------------------- ");
			
			// send + cc + bcc
			String allsendlist = (to.toString() + cc.toString() + bcc.toString()).trim() ;

			
//---------------------------------------------------------------------------------------------------------------------------------
// 9.发送邮件
//---------------------------------------------------------------------------------------------------------------------------------

			log.debug("--- step9 --- [发送邮件] 执行开始 ---------------------------------------------------- ");
  
			
			if("checked1".equals(isRe)&&"checked2".equals(isSent1)){
			
				flag = FileTransferConfig.TO_SEND_RE;
			
			}else if(!"checked1".equals(isRe)&&"checked2".equals(isSent1)){
				
				flag = FileTransferConfig.TO_SEND;
			
			}else if("checked1".equals(isRe)&&!"checked2".equals(isSent1)){
				
				flag = FileTransferConfig.SEND_RE;
			}
			
			log.debug("发送的邮件是否保存到发件箱-------"+flag);
			log.debug("发送的邮件是否需要回执-------"+isRe);
			
			//			构造红旗的邮件处理类			
			mailhandler = MailhandlerFactory.getHandler(userid);
			log.debug("成功构造dirmanage红旗邮件处理对象！");
			
			//如果有至少一个的接收地址，则对邮件进行发送
			if(allsendlist.length()>0){  
				log.debug("邮件发送地址：  "+allsendlist);  

				//保存到发件箱
				String isSent = request.getParameter("isSent");
				if("checked2".equals(isSent)){
					log.debug("保存到发件箱开始！");
					long save0 = System.currentTimeMillis();
//					String subject0 = topic.concat(Long.toString(System.currentTimeMillis()));
					handler.saveToSent(mailhandler,request.getContextPath(),mailmemory,userid,chinaName,senddomain,subject,text,attachment,flag, allsendlist.toString());
					long save1 = System.currentTimeMillis();
					long save = save1 - save0;
					log.debug("保存到发件箱结束！用时"+save+"毫秒！约合"+save/1000+"秒！");  
				}
				
				
				log.debug("邮件的正题名  "+subject);  
				log.debug("邮件的内容  "+text);
				
				if(attachment!=null){
					for (int i = 0; i < attachment.length; i++) {
						log.debug("附件在服务器上的位置是："+attachment[i]);
					}
				}else{
						log.debug("此邮件无附件");
				}
				
				log.debug("是否要回执?如果是3就是OK！   "+flag);
				
//				发送
				log.debug("调用红旗邮件服务处理对象 发送邮件开始！");
				long send0 = System.currentTimeMillis();
				
				String[] toperson = to.length()>0?to.toString().split(","):null;
				String[] ccperson = cc.length()>0?cc.toString().split(","):null;
				String[] bccperson = bcc.length()>0?bcc.toString().split(","):null;

				
				String[][] failuser = mailhandler.transfermail(subject,text,toperson,ccperson,bccperson,attachment,flag);


//				logger.info("-----------------正题名"+topic+"------end!!!--------------");
//				BasicConfigurator.configure(); 
				
				long send1 = System.currentTimeMillis();
				long sendTake = send1 - send0;
				log.debug("调用红旗邮件服务处理对象 发送邮件结束！用时"+sendTake+"毫秒！约合"+sendTake/1000+"秒！");
				
				
				//发送邮件待办记录
				log.debug("发送待办邮件记录开始！");
				long intend0 = System.currentTimeMillis();

				StringTokenizer yesList = new StringTokenizer(allsendlist.toString(),",");
				while (yesList.hasMoreTokens()) {
					String yesUserID = yesList.nextToken();
					sendResult.append(ftsHandler.getCName(yesUserID.substring(0,yesUserID.indexOf("@"))));
					sendResult.append(",");
					
					int yesindex = yesUserID.indexOf("@");
					yesUserID = yesUserID.substring(0,yesindex);
					
	
					//加入到待办
					String intendtopic = "新邮件，来自"+chinaName+"（"+CommUtil.getTime(System.currentTimeMillis())+"）";
					String tointendId = userid + "," + subject + "," + yesUserID;
					//handler.addToIntend(request.getContextPath(),yesUserID,intendtopic,tointendId);
					Thread t = new Thread(new FindMailRun(userid,subject,nowtime,intendtopic,tointendId,yesUserID,request.getContextPath()));
					ThreadsPool.getInstance().putTask(t);
					
				}
				long intend1 = System.currentTimeMillis();
				long intend = intend1 - intend0;
				log.debug("发送待办邮件记录结束！用时"+intend+"毫秒！约合"+intend/1000+"秒！");
			}
			
			//未发送邮件代办处理 add by lintl
			if(overFlowResult.length()>0){
				String[] no = overFlowResult.toString().split(",");
				for(String nouserid :no){
				String intendtopic = "由于您的邮箱已满，来自  "+chinaName+" 的邮件未能收到！（"+CommUtil.getTime(System.currentTimeMillis())+"）";
				handler.addToIntend(request.getContextPath(),nouserid,intendtopic,"nomail");
				}
			}
			request.getSession().setAttribute("overFlowResult", overFlowResult.toString());

			
			request.getSession().setAttribute("sendResult", sendResult.toString());
			long timeEnd = System.currentTimeMillis();
			long timeTake = timeEnd - timeBegin;
			
			log.debug("发送邮件结束 全部用时"+timeTake+"毫秒！，约合"+timeTake/1000+"秒！");
			//request.getSession().setAttribute("failResult", "发送失败(1cs！0");
			//String m = (String)request.getSession().getAttribute("failResult");
			//System.out.println("failResult = " + m);
			response.sendRedirect("MailRefreshServlet");
			
			//forward(request,response,"/oabase/mail/SendMail_Body.jsp?sun_flag=err");
		}catch (SendFailedException e) {
			//log.debug("SendFailedException = " + e.getMessage());
			log.error("SendFailedException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			//request.setAttribute("failResult","发送失败(1)！");
			request.getSession().setAttribute("failResult", "发送失败(1)！");
			response.sendRedirect("MailRefreshServlet");
		} catch (IOException e) {
			//log.debug("IOException = " + e.getMessage());
			log.error("IOException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "由于您的邮箱已满，发送失败(2)！");
			response.sendRedirect("MailRefreshServlet");
		} catch (EntityException e) {
			//log.debug("EntityException = " + e.getMessage());
			log.error("EntityException = " + e.getMessage(),e);		
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "发送失败(3)！");
			response.sendRedirect("MailRefreshServlet");
		} catch (ServiceLocatorException e) {
			//log.debug("ServiceLocatorException = " + e.getMessage());
			log.error("ServiceLocatorException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "发送失败(4)！");
			response.sendRedirect("MailRefreshServlet");
		} catch (NamingException e) {
			//log.debug("NamingException = " + e.getMessage());
			log.error("NamingException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "发送失败(5)！");
			response.sendRedirect("MailRefreshServlet");
		}  catch (REException e) {
			//log.debug("REException = " + e.getMessage());
			log.error("REException = " + e.getMessage(),e);
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.getSession().setAttribute("failResult", "发送失败(6)！");
			response.sendRedirect("MailRefreshServlet");
		} catch (Exception e) {
			//log.debug("MailException = " + e.getMessage());
			log.error("MailException = " + e.getMessage(),e);
			e.printStackTrace();			
			request.getSession().setAttribute("failResult", "其他错误！");
			response.sendRedirect("MailRefreshServlet");
		} 
		finally {
			
			if (conn != null){
				try {
					//关闭连接
					conn.close();
					log.debug("发送邮件 finally 成功关闭数据库连接！");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if(mailhandler!=null){
				try {
					//关闭邮件连接
					mailhandler.disconnect();
					log.debug("发送邮件 finally 成功关闭红旗邮件服务器连接！");
					
				} catch (LdapException e) {
					e.printStackTrace();
				}
			}
			
			
			//删除服务器上的暂存附件（用于上传的）
			if(attachment!=null&&attachment.length>0){
				
				for(int attachnum=0;attachnum<attachment.length;attachnum++){
					
					int pathindex = attachment[attachnum].lastIndexOf(File.separator);
					String privatedir = attachment[attachnum].substring(0,pathindex);
					//当前一个附件文件
					File attachfile = new File(attachment[attachnum]);
					
					log.debug("发送邮件 finally 删除服务器临时附件 attachfile["+attachnum+"] = " + attachfile);
					
					//删除附件文件
					if(attachfile.exists()){
						attachfile.delete();
					}
					
					//附件文件夹
					File dirfile = new File(privatedir);
					//删除附件文件
					if(dirfile.exists()){
						dirfile.delete();
					}
				}	
			}
		}
	}
	

	
	//	将微秒（Long）的时间转换为字符串型形式
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
