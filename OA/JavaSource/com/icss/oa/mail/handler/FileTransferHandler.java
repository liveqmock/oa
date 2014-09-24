package com.icss.oa.mail.handler;  

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.redflaglinux.ldap.LdapException;
import org.redflaglinux.services.dir.dirmanage;
import org.redflaglinux.user.usermanage;

import test.MailPath.UserPath;

import com.icss.common.log.Log;
import com.icss.common.log.LogFactory;
import com.icss.common.log.filelog.FileLogFactory;
import com.icss.common.thread.IWorkQueue;
import com.icss.common.thread.WorkQueueManager;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.util.FileUtil;
import com.icss.oa.address.handler.AddressHelp;
import com.icss.oa.address.handler.Group;
import com.icss.oa.address.handler.HandlerException;
import com.icss.oa.address.vo.AddressCommongroupVO;
import com.icss.oa.address.vo.AddressGroupinfoVO;
import com.icss.oa.address.vo.SelectOrgpersonVO;
import com.icss.oa.config.FileTransferConfig;
import com.icss.oa.config.IntendWorkConfig;
import com.icss.oa.filetransfer.admin.changeStr;
import com.icss.oa.filetransfer.dao.FiletransferReadrecordDAO;
import com.icss.oa.filetransfer.dao.FiletransferSetDAO;
import com.icss.oa.filetransfer.dao.FiletransferWanttorecordDAO;
import com.icss.oa.filetransfer.util.FiletransferUtil;
import com.icss.oa.filetransfer.vo.FiletransferReadrecordVO;
import com.icss.oa.filetransfer.vo.FiletransferSetVO;
import com.icss.oa.filetransfer.vo.FiletransferWanttorecordVO;
import com.icss.oa.filetransfer.vo.MailAccessoryVO;
import com.icss.oa.intendwork.handler.IntendWork;
import com.icss.oa.util.CommUtil;
import com.icss.oa.util.FileTypeUtil;
import com.icss.oa.util.PropertyManager;
import com.icss.oa.util.UserManagerCache;
import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.EntityManager;

/**
 * @author Administrator
 *
 * 
 */

public class FileTransferHandler { 
	
	/**
	 * Comment for <code>conn</code>
	 */
	private Connection conn;
	
	/**
	 * @param _conn
	 */
	public FileTransferHandler(Connection _conn){
		this.conn = _conn;
	}
	
	/**
	 * 
	 */
	public FileTransferHandler(){
		
	}
	
	private String loghead = "";
	private IWorkQueue workQueue;
	public void setLoghead(String loghead){
		this.loghead = loghead;
	}
	
	/**
	 * 发送带附件文件
	 * @param content
	 * @param msg
	 * @param sendFileBean
	 * @throws MessagingException
	 */
	public void sendAttachFile(
		String content,
		MimeMessage msg,
		SendFileBean sendFileBean)
		throws Exception {
		//创建邮件体
		BodyPart contentBodyPart = new MimeBodyPart();
		//添加内容
		contentBodyPart.setText(content);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(contentBodyPart);

		//添加附件
		for (int index = 0; index < sendFileBean.filenumber(); index++) {
			AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
			//创建附件体
			BodyPart attachBodyPart = new MimeBodyPart();
			DataSource source =
				new FileDataSource(attachFileBean.getUploadUrl());
			attachBodyPart.setDataHandler(new DataHandler(source));
			attachBodyPart.setFileName(attachFileBean.getFileOriginName());
			attachBodyPart.setHeader(
				"filename",
				MimeUtility.encodeText(
					attachFileBean.getFileOriginName(),
					"GBK",
					"B"));
			multipart.addBodyPart(attachBodyPart);
		}

		msg.setContent(multipart);
	}
	
	/**
	 * 得到所有附件文件的文件
	 * @param content
	 * @param msg
	 * @param sendFileBean
	 * @throws MessagingException
	 */
	public String[] getAttachNames(SendFileBean sendFileBean){
			String[] attachFileNames = new String[sendFileBean.filenumber()];
			//添加附件
			for (int index = 0; index < sendFileBean.filenumber(); index++) {
				AttachFileBean attachFileBean = sendFileBean.getAttachFile(index); 
				String downloadPath = attachFileBean.getUploadUrl();
				System.out.println("the downloadPath is........"+downloadPath);
				attachFileNames[index] = downloadPath;
			}
			return attachFileNames;
	}

	/**
	 * 判断一个邮件地址是否合法
	 */
	public boolean isMailUser(String userAddress){
		int index = userAddress.indexOf("@");
		String userid = userAddress.substring(0,index);
		FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
		try{
			String personuuid =  ftsHandler.getUUid(userid);
			return true;
		} catch (Exception ex){
			return false;
		}
		
	}
	/**
	 * 判断多个邮件地址的串中的地址是否合法
	 * @return 存在的地址和不没有邮箱地址的人的userid
	 */
	public List getFromDraftAddress(String userAddress){
		List list = new ArrayList();
		FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		StringTokenizer token = new StringTokenizer(userAddress,",");
		int tokenLength = token.countTokens();
		System.out.println("tokenLength = " +tokenLength);
		String onePerson = "";
		for(int i=0;i<tokenLength;i++){
			System.out.println("i = " +i);
			onePerson = token.nextToken();
			int index = onePerson.indexOf("@");
			String userid = onePerson.substring(0,index);
			try{
				String personuuid =  ftsHandler.getUUid(userid);
				sendto.append(onePerson);
			} catch (Exception ex){
				illegeAddress.append(ftsHandler.getCName(onePerson.substring(0,index)));
			}
		}
		list.add(illegeAddress.toString());
		list.add(sendto.toString());
		return list;
	}
	
	/**
	 * 得到收件人地址（通过SESSION）
	 * @param request
	 * @param domain
	 * @return 存在的地址和不没有邮箱地址的人的userid
	 * @throws Exception
	 */
	public List getSendtoAddressForSendFile(HttpServletRequest request, String domain) throws HandlerException {
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession(true);
		
		//可以发送的用户地址的集合
		List reStringList = new ArrayList();
		
		String reString = "";
		String illegeReString = "";
		
		AddressHelp helper = new AddressHelp(this.conn);
		
		List orgpersonlist = (List) session.getAttribute("sendtoperson");
		if(orgpersonlist == null || orgpersonlist.size() ==0 ){
			throw new HandlerException("地址为空，会话可能超时，启用隐含表单方式进行提取地址！");
		}
		
		//从会话中取得选择的要发送的用户集合，即SelectOrgpersonVO对象的集合
//		System.out.println("得到personList开始！");
		List personList = helper.getperson((List) session.getAttribute("sendtoperson"),request,"sendtoperson");
//		System.out.println("kkkkkkkkkkkkkkkkkkkkk  personList "+personList.size()); 
//		System.out.println("得到personList结束！");
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		
		Iterator personIterator = personList.iterator();
		
		while (personIterator.hasNext()) {
			
			SelectOrgpersonVO selectOrgpersonVO = (SelectOrgpersonVO) personIterator.next();
			
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				//构造一个EMAIL地址，即：userid@domain
				String userid =  ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
				sendto.append(userid).append("@").append(domain).append(",");
				
			}catch (Exception e){
				String CName = selectOrgpersonVO.getName();
				illegeAddress.append(CName);
				illegeAddress.append(",");
			}
			
		}
		//不合法的地址对应的用户姓名
		illegeReString = illegeAddress.toString();
		//可以发送的
		reString = sendto.toString();
		
		//不合法地址对应的用户姓名的集合
		Addresslist.add(0,illegeReString);
		//全部可以发送的地址名称，例如：userid@domain,userid@domain,userid@domain
		Addresslist.add(1,reString);
		
		return Addresslist;
	}	
	
	/**
	 * 根据三个字符串得到list
	 * @return
	 */
	public List getSendtoAddress0830(String s1,String s2,String s3,String domain){
	    
		List legalAddressList = new ArrayList();
		List Addresslist = new ArrayList();
		StringBuffer legalString = new StringBuffer(); 
		StringBuffer illegalString = new StringBuffer();
	    
		//分别得到个人＆个人＆公共分组的字符串
		StringTokenizer addresslist = new StringTokenizer(s1,","); 
		System.out.println("s1    ="+s1);
		StringTokenizer addresslist2 = new StringTokenizer(s2,",");
		System.out.println("s2    ="+s2);
		StringTokenizer addresslist3 = new StringTokenizer(s3,",");
		System.out.println("s3    ="+s3);
	    
		FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);

		//构造取得分组的handler
		Group group = new Group(conn);
	    
		//对取得的个人信息进行解析
		while(addresslist.hasMoreTokens()){
	        
			String uuid = addresslist.nextToken();
			System.out.println("uuid    ="+uuid);
	        
			   String userid = null;
			   
					try{
						userid =  ftsHandler.getUserid(uuid);
					
					
					if(userid!=null){
						legalString.append(userid).append("@").append(domain).append(",");
						String currAddress = userid + "@" + domain;
			        
						if(!legalAddressList.contains(currAddress)){
							 legalAddressList.add(currAddress);
						 }
					}else{
						illegalString.append(userid).append("@").append(domain).append(",");
					}
				
				}catch(Exception e){
									userid ="";
									e.printStackTrace();
								}
		}
	    
	    
		//对取得个人分组信息进行解析
		while(addresslist2.hasMoreTokens()){
			List addressgroupinfolist = group.personInGroup(
					Integer.valueOf(addresslist2.nextToken()),
					"2");
			System.out.println("addressgroupinfolist.size()    ="+addressgroupinfolist.size());
			Iterator addressgroupinfoiterator =
				addressgroupinfolist.iterator();
			System.out.println("addressgroupinfolist.size()1111    ="+addressgroupinfolist.size());
			while(addressgroupinfoiterator.hasNext()){
	        	
				String userid = null;
				
				try{
				AddressGroupinfoVO addressgroupinfovo =
					(AddressGroupinfoVO) addressgroupinfoiterator.next();
	            
				
				String uuid = addressgroupinfovo.getUserid();
				System.out.println("uuid    ="+uuid);
				
				
				
				userid =  ftsHandler.getUserid(uuid);
				
				if(userid!=null){
					legalString.append(userid).append("@").append(domain).append(",");
					String currAddress = userid + "@" + domain;
		        
					if(!legalAddressList.contains(currAddress)){
						 legalAddressList.add(currAddress);
					 }
				}else{
					illegalString.append(userid).append("@").append(domain).append(",");
				}
				
				}catch(Exception e){
						userid ="";
						e.printStackTrace();
					}
		        
		       
			}
		}
	    
		//对取得公共分组的信息进行解析
	    
		while(addresslist3.hasMoreTokens()){
			List grouplist = group.getGroupByParentGroupOrGroup(new Integer(addresslist3.nextToken()));
			Iterator groupiterator = grouplist.iterator();
	        
			while(groupiterator.hasNext()){
				AddressCommongroupVO addresscommongroupvo =
					(AddressCommongroupVO) groupiterator.next();

				List addressgroupinfolist =
					group.personInGroup(
						addresscommongroupvo.getId(),
						"1");
				Iterator addressgroupinfoiterator =
					addressgroupinfolist.iterator();
				
				while(addressgroupinfoiterator.hasNext()){
				    
				    
					String userid = null;
					
					try{
						
						AddressGroupinfoVO addressgroupinfovo =
							(AddressGroupinfoVO) addressgroupinfoiterator.next();
					    
						String uuid = addressgroupinfovo.getUserid();
					    
						
						userid =  ftsHandler.getUserid(uuid);
						
						if(userid!=null){
							legalString.append(userid).append("@").append(domain).append(",");
						}else{
							illegalString.append(userid).append("@").append(domain).append(",");
						}
						
						legalString.append(userid).append("@").append(domain).append(",");
						String currAddress = userid + "@" + domain;
				        
						if(!legalAddressList.contains(currAddress)){
							legalAddressList.add(currAddress);
						}
			        
					}catch(Exception e){
						userid ="";
						e.printStackTrace();
					}
				    
				    
				}
			}
	          
	      
		}
	    
			  //不合法地址对应的用户姓名的集合
			  Addresslist.add(0,"");
			  //全部可以发送的地址名称，例如：userid@domain,userid@domain,userid@domain
			  Addresslist.add(1,legalString);
		
			  //可以发送的地址名称的集合，集合的每个元素是String类型的对象 add by yangyang 20050708
			  //例如：
			  //legalAddressList.get(0) = "user1@domain"
			  //legalAddressList.get(1) = "user2@domain"
			  //legalAddressList.get(2) = "user3@domain"
			  Addresslist.add(2,legalAddressList);
	    
		return Addresslist;
	    
	}
	
	/**
	 * 得到收件人地址（通过SESSION）
	 * @param request
	 * @param domain
	 * @return 存在的地址和不没有邮箱地址的人的userid
	 * @throws Exception
	 */
	public List getSendtoAddress(HttpServletRequest request, String domain) throws HandlerException {

		//可以发送的用户地址的集合 add by yangyang 20050708
		List legalAddressList = new ArrayList();
			
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession(true);
	
		String reString = "";
		String illegeReString = "";
		
		AddressHelp helper = new AddressHelp(this.conn);
		
		List orgpersonlist = (List) session.getAttribute("sendtoperson");
		if(orgpersonlist == null || orgpersonlist.size() ==0 ){
			throw new HandlerException("地址为空，会话可能超时，启用隐含表单方式进行提取地址！");
		}
		
		//从会话中取得选择的要发送的用户集合，即SelectOrgpersonVO对象的集合
//		System.out.println("得到personList开始！");
		List personList = helper.getperson((List) session.getAttribute("sendtoperson"),request,"sendtoperson");
//		System.out.println("kkkkkkkkkkkkkkkkkkkkk  personList "+personList.size()); 
//		System.out.println("得到personList结束！");
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		
		Iterator personIterator = personList.iterator();
		
		while (personIterator.hasNext()) {
			
			SelectOrgpersonVO selectOrgpersonVO = (SelectOrgpersonVO) personIterator.next();
			
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				//构造一个EMAIL地址，即：userid@domain
				String userid =  ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
				sendto.append(userid).append("@").append(domain).append(",");
				
				//add by yangyang 20050708
				String currAddress = userid + "@" + domain;
				legalAddressList.add(currAddress);
				
			}catch (Exception e){
				String CName = selectOrgpersonVO.getName();
				illegeAddress.append(CName);
				illegeAddress.append(",");
			}
			
		}
		//不合法的地址对应的用户姓名
		illegeReString = illegeAddress.toString();
		//可以发送的
		reString = sendto.toString();
		
		//不合法地址对应的用户姓名的集合
		Addresslist.add(0,illegeReString);
		//全部可以发送的地址名称，例如：userid@domain,userid@domain,userid@domain
		Addresslist.add(1,reString);
		
		//可以发送的地址名称的集合，集合的每个元素是String类型的对象 add by yangyang 20050708
		//例如：
		//legalAddressList.get(0) = "user1@domain"
		//legalAddressList.get(1) = "user2@domain"
		//legalAddressList.get(2) = "user3@domain"
		Addresslist.add(2,legalAddressList);

		return Addresslist;
	}
	
	/** 得到收件人地址（通过隐含表单方式）
	 * @param request
	 * @param domain
	 * @return
	 * @throws HandlerException
	 */
	public List getSendtoAddressByHidden(HttpServletRequest request, String domain,String str) throws HandlerException {
		
//		可以发送的用户地址的集合 add by sct 20050713
		List legalAddressList = new ArrayList();
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession(true);
		String reString = "";
		String illegeReString = "";
		
		AddressHelp helper = new AddressHelp(this.conn);
		
		//从会话中取得选择的要发送的用户集合，即SelectOrgpersonVO对象的集合
//		List personList = helper.getperson((List) session.getAttribute("sendtoperson"),request,"sendtoperson");
		List personList = helper.getperson(this.ChangeStringTokenizerToList(str),request,"sendtoperson");
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		
		Iterator personIterator = personList.iterator();
		
		while (personIterator.hasNext()) {
			
			SelectOrgpersonVO selectOrgpersonVO = (SelectOrgpersonVO) personIterator.next();
			
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				//构造一个EMAIL地址，即：userid@domain
				String userid =  ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
				sendto.append(userid).append("@").append(domain).append(",");
				
				//add by sunchuating 20050711
				String currAddress = userid + "@" + domain;
				legalAddressList.add(currAddress);
				
			}catch (Exception e){
				String CName = selectOrgpersonVO.getName();
				illegeAddress.append(CName);
				illegeAddress.append(",");
			}
			
		}
		//不合法的地址对应的用户姓名
		illegeReString = illegeAddress.toString();
		//可以发送的
		reString = sendto.toString();
		
		//不合法地址对应的用户姓名的集合
		Addresslist.add(illegeReString);
		//全部可以发送的地址名称，例如：userid@domain,userid@domain,userid@domain
		Addresslist.add(reString);
		
//		可以发送的地址名称的集合，集合的每个元素是String类型的对象 add by yangyang 20050708
		//例如：
		//legalAddressList.get(0) = "user1@domain"
		//legalAddressList.get(1) = "user2@domain"
		//legalAddressList.get(2) = "user3@domain"
		Addresslist.add(2,legalAddressList);
		
		return Addresslist;
	}
	
	/**
	 * 得到收件人地址(机关党委的邮件地址)（通过给List赋值得到）
	 * @param request
	 * @param domain
	 * @return 存在的地址和不没有邮箱地址的人的userid
	 * @throws Exception
	 */
	public List getSendtoAddressForjiguandangwei(HttpServletRequest request, String domain) {
		
		//可以发送的用户地址的集合 add by yangyang 20050708
		List legalAddressList = new ArrayList();		
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession();
		String reString = "";
		String illegeReString = "";
		AddressHelp helper = new AddressHelp(this.conn);
		List personList = null;
		
		//0308为了是党员先进性的发文特此处理，缺省为机关党委
		SelectOrgpersonVO vo = new SelectOrgpersonVO();
		vo.setUserid("jiguandangwei");
		vo.setUseruuid("5b99f307-10257ea8062-e600d7d60b5229058fb97f42553082d6");
		vo.setName("机关党委");
		vo.setIsperson("1");
		
		/*vo.setUserid("dev");
		vo.setUseruuid("7deef758-fdb75f4693-8581bd2af19e660037d144f55ca4fb1e");
		vo.setName("开发测试账号");
		vo.setIsperson("1");*/
		
		personList = new ArrayList();
		personList.add(vo);
		/////////////////////////////////////////
		
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		Iterator personIterator = personList.iterator();
		while (personIterator.hasNext()) {
			SelectOrgpersonVO selectOrgpersonVO =
				(SelectOrgpersonVO) personIterator.next();
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				String userid =  ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
				sendto.append(userid);
				sendto.append("@");
				sendto.append(domain);
				sendto.append(",");
				
				//把合法用户邮件地址的名称加入到合法地址的集合中 add by yangyang 20050708
				String currAddress = userid + "@" + domain;
				legalAddressList.add(currAddress);
				
			}catch (Exception e){
				String CName = selectOrgpersonVO.getName();
				illegeAddress.append(CName);
				illegeAddress.append(",");
			}
		}
		illegeReString = illegeAddress.toString();
		reString = sendto.toString();

		Addresslist.add(0,illegeReString);
		Addresslist.add(1,reString);
		//可以发送的地址名称的集合，集合的每个元素是String类型的对象 add by yangyang 20050708
		//例如：
		//legalAddressList.get(0) = "user1@domain"
		//legalAddressList.get(1) = "user2@domain"
		//legalAddressList.get(2) = "user3@domain"
		Addresslist.add(2,legalAddressList);		
		
		return Addresslist;
	}
	
	/**
	 * 得到抄送地址（通过SESSION）
	 * @param request
	 * @param domain
	 * @return 存在的地址和不没有邮箱地址的人的userid
	 * @throws Exception
	 */
	public List getSendccAddress(HttpServletRequest request, String domain) throws HandlerException, EntityException  {
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession();
		AddressHelp helper = new AddressHelp(this.conn);
		List personList = null;
			
		String reString = "";
		String illegeReString = "";
		
		List orgpersonlist = (List) session.getAttribute("sendtocc");
		if(orgpersonlist == null || orgpersonlist.size() ==0 ){
			throw new HandlerException("地址为空，会话可能超时，启用隐含表单方式进行提取地址！");
		}
		
		personList = helper.getperson(
				(List) session.getAttribute("sendtocc"),
				request,
				"sendtocc");

		StringBuffer sendtocc = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		if(personList!=null){
			Iterator personIterator = personList.iterator();
			while (personIterator.hasNext()) {
				SelectOrgpersonVO selectOrgpersonVO =
					(SelectOrgpersonVO) personIterator.next();
				//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
				try{
					String userid = ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
					sendtocc.append(userid);
					sendtocc.append("@");
					sendtocc.append(domain);
					sendtocc.append(",");
				}catch(Exception e){
					EntityManager entitymanger = EntityManager.getInstance();
					String CName = entitymanger.findPersonByUuid(selectOrgpersonVO.getUseruuid()).getFullName();
					illegeAddress.append(CName);
					illegeAddress.append(",");
				}
			}
		}
		illegeReString = illegeAddress.toString();
		reString = sendtocc.toString();

		Addresslist.add(illegeReString);
		Addresslist.add(reString);	
		
		return Addresslist;
	}
	
	/**
	 * 得到抄送地址（通过隐含表单）
	 * @param request
	 * @param domain
	 * @param str
	 * @return
	 * @throws HandlerException
	 * @throws EntityException
	 */
	public List getSendccAddressByHidden(HttpServletRequest request, String domain,String str) throws HandlerException, EntityException  {
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession();
		AddressHelp helper = new AddressHelp(this.conn);
		List personList = null;
			
		String reString = "";
		String illegeReString = "";
//		personList = helper.getperson(
//				(List) session.getAttribute("sendtocc"),
//				request,
//				"sendtocc");
		personList = helper.getperson(this.ChangeStringTokenizerToList(str),request,"sendtocc");

		StringBuffer sendtocc = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		if(personList!=null){
			Iterator personIterator = personList.iterator();
			while (personIterator.hasNext()) {
				SelectOrgpersonVO selectOrgpersonVO =
					(SelectOrgpersonVO) personIterator.next();
				//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
				try{
					String userid = ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
					sendtocc.append(userid);
					sendtocc.append("@");
					sendtocc.append(domain);
					sendtocc.append(",");
				}catch(Exception e){
					EntityManager entitymanger = EntityManager.getInstance();
					String CName = entitymanger.findPersonByUuid(selectOrgpersonVO.getUseruuid()).getFullName();
					illegeAddress.append(CName);
					illegeAddress.append(",");
				}
			}
		}
		illegeReString = illegeAddress.toString();
		reString = sendtocc.toString();

		Addresslist.add(illegeReString);
		Addresslist.add(reString);	
		
		return Addresslist;
	}
	
	/**
	 * 得到密送地址（通过SESSION）
	 * @param request
	 * @param domain
	 * @return 存在的地址和不没有邮箱地址的人的中文名
	 * @throws Exception
	 */
	public List getSendbccAddress(HttpServletRequest request, String domain) throws HandlerException, EntityException {
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession();
		AddressHelp helper = new AddressHelp(this.conn);  
		List personList = null;
		
		String reString = "";
		String illegeReString = "";
		
		List orgpersonlist = (List) session.getAttribute("sendtobcc");
		if(orgpersonlist == null || orgpersonlist.size() ==0 ){
			throw new HandlerException("地址为空，会话可能超时，启用隐含表单方式进行提取地址！");
		}
		
		personList = helper.getperson(
				(List) session.getAttribute("sendtobcc"),
				request,
				"sendtobcc");
		StringBuffer sendtobcc = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		if(personList!=null){
			Iterator personIterator = personList.iterator();
			while (personIterator.hasNext()) {
				SelectOrgpersonVO selectOrgpersonVO =
					(SelectOrgpersonVO) personIterator.next();
				//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
				try{
					String userid = ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
					sendtobcc.append(userid);
					sendtobcc.append("@");
					sendtobcc.append(domain);
					sendtobcc.append(",");
				}catch (Exception e){
					EntityManager entitymanger = EntityManager.getInstance();
					String CName = entitymanger.findPersonByUuid(selectOrgpersonVO.getUseruuid()).getFullName();
					illegeAddress.append(CName);
					illegeAddress.append(",");
				}
			}
		}
		
		illegeReString = illegeAddress.toString();
		reString = sendtobcc.toString();

		Addresslist.add(illegeReString);
		Addresslist.add(reString);
		
		return Addresslist;
	}
	
	/**
	 * 得到密送地址（通过隐含表单）
	 * @param request
	 * @param domain
	 * @return 存在的地址和不没有邮箱地址的人的中文名
	 * @throws Exception
	 */
	public List getSendbccAddressByHidden(HttpServletRequest request, String domain,String str) throws HandlerException, EntityException {
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession();
		AddressHelp helper = new AddressHelp(this.conn);
		List personList = null;
		
		String reString = "";
		String illegeReString = "";
//		personList = helper.getperson(
//				(List) session.getAttribute("sendtobcc"),
//				request,
//				"sendtobcc");
		personList = helper.getperson(this.ChangeStringTokenizerToList(str),request,"sendtobcc");
		StringBuffer sendtobcc = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		if(personList!=null){
			Iterator personIterator = personList.iterator();
			while (personIterator.hasNext()) {
				SelectOrgpersonVO selectOrgpersonVO =
					(SelectOrgpersonVO) personIterator.next();
				//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
				FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
				try{
					String userid = ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
					sendtobcc.append(userid);
					sendtobcc.append("@");
					sendtobcc.append(domain);
					sendtobcc.append(",");
				}catch (Exception e){
					EntityManager entitymanger = EntityManager.getInstance();
					String CName = entitymanger.findPersonByUuid(selectOrgpersonVO.getUseruuid()).getFullName();
					illegeAddress.append(CName);
					illegeAddress.append(",");
				}
			}
		}
		
		illegeReString = illegeAddress.toString();
		reString = sendtobcc.toString();

		Addresslist.add(illegeReString);
		Addresslist.add(reString);
		
		return Addresslist;
	}
	/**
	 * 处理附件邮件，返回邮件正文
	 * @param fileMessage
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	public String processAttachPart(
		MimeMessage fileMessage,
		String filepath,
		HttpServletRequest request,
		String directory)
		throws IOException, MessagingException {
		//获得part
		Multipart multipart = (Multipart) fileMessage.getContent();
		String content = "";
		ArrayList attachList = new ArrayList();
		//解析每个部分
		for (int i = 0; i < multipart.getCount(); i++) {
//			System.out.println("begin to get part.......");
			Part part = multipart.getBodyPart(i);
//			String s = part.getDisposition();
			if (!isAttachPart(part)) { //正文部分
				content = (String) part.getContent();
			} else {
//				System.out.println("get attachment part.......");
				AttachFileBean attachFile = saveFile(filepath, part, directory);
				attachList.add(attachFile);
			}
		}
		request.setAttribute("attachList", attachList);
		return content;
	}
	
	/**
	 * 处理附件邮件，返回SendFileBean
	 * @param fileMessage
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 * @throws Exception
	 */
	public SendFileBean getSessionBean(SendFileBean sendFileBean,
										MimeMessage fileMessage,
										ServletContext context,
										String userid,
										HttpServletRequest request) 
		throws Exception{
		
		AttachFileBean filebean = new AttachFileBean();
		StringBuffer privateDir = new StringBuffer(userid);
		privateDir.append(System.currentTimeMillis());
		


		//下载路径修改 2005-12-28
		//原有程序：
		//StringBuffer downloaddir = new StringBuffer(context.getRealPath("/filetransfer/downloadfile/"));
		//修改程序：
		StringBuffer downloaddir = new StringBuffer(FiletransferUtil.getDownloadFilePath());




		String downloadfilepath = this.getfilepath(downloaddir,privateDir.toString());
		String content = "";
		if (!fileMessage.isMimeType("text/plain")) { //存储附件
			//处理附件邮件
			content = this.processAttachPart(
				fileMessage,
				downloadfilepath,
				request,
				privateDir.toString());
		}else{
			content = (String)fileMessage.getContent();
		}
		sendFileBean.setContent(content);

		//取出request里的信息，把附件转移到上载的目录中，并把路径名存入一份到session中，发送时使用
		ArrayList fileList = (ArrayList) request.getAttribute("attachList");
		if(fileList != null)
		for (int i = 0; i < fileList.size(); i++) {  //存储所发送附件的路径
			//用于发送的路径
			

			
			//上传路径修改 2005-12-28
			//原有程序：
			//StringBuffer uploaddir = new StringBuffer(context.getRealPath("/filetransfer/uploadfile/"));
			//修改程序：
			StringBuffer uploaddir = new StringBuffer(FiletransferUtil.getUploadFilePath());


			
			
			File urlFile = new File(uploaddir.toString());
			if(!urlFile.exists()){
				urlFile.mkdirs();
			}
			
			filebean = (AttachFileBean) fileList.get(i);
			
			String downloadpath = downloaddir.toString().concat(filebean.getDownloadUrl());
			InputStream is = new FileInputStream(downloadpath);

			String filename = filebean.getFileOriginName();
			
			uploaddir.append(privateDir.toString());
			uploaddir.append(File.separator);
			uploaddir.append(filename);
			String uploadpath = uploaddir.toString();
			//String uploadpath = uploaddir.append(privateDir.toString()).append(File.separator).append(filename).toString();
			
			File uploadFile = new File(uploadpath);
			FileUtil.copy(is,uploadFile);
			filebean.setUploadUrl(uploadpath);
			sendFileBean.addAttach(filebean);
		}
		return sendFileBean;
	}
	
	/**
	 * only返回邮件正文
	 * @param fileMessage
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	public String getMailContent(
		MimeMessage fileMessage)
		throws IOException, MessagingException {
		//获得part
		Multipart multipart = (Multipart) fileMessage.getContent();
		String content = "";
		ArrayList attachList = new ArrayList();
		//解析每个部分
		for (int i = 0; i < multipart.getCount(); i++) {
			Part part = multipart.getBodyPart(i);
			String s = part.getDisposition();
			if (!isAttachPart(part)) { //正文部分
				content = (String) part.getContent();
			} 
		}
		return content;
	}
	
	/**
	 * only返回邮件Part
	 * @param fileMessage
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	
	public List getAccessoryList(
		MimeMessage fileMessage)
		throws IOException, MessagingException {
		//获得part
		Multipart multipart = (Multipart) fileMessage.getContent();
		String content = "";
		ArrayList attachList = new ArrayList();
		Part part = null;
		List list = new ArrayList();

		//解析每个部分
		for (int i = 0; i < multipart.getCount(); i++) {
			part = multipart.getBodyPart(i);
			String s = part.getDisposition();
			if (!isAttachPart(part)) { 
				content = (String) part.getContent();
			} else {
				MailAccessoryVO vo = new MailAccessoryVO();
				String filename = MimeUtility.decodeText(part.getHeader("filename")[0]);
				InputStream in = part.getInputStream();
				vo.setFilename(filename);
				vo.setIn(in);
				list.add(vo);
			}
		}

		return list;
	}
	/**
	 * 存储附件内容
	 * @param filepath
	 * @param part
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public AttachFileBean saveFile(
		String filepath,
		Part part,
		String directory)
		throws MessagingException, IOException {  
////		String filename = MimeUtility.decodeText(part.getHeader("filename")[0]);  
//		System.out.println("33333333333333333333333333333333333333333333333333333333333333333");
//		System.out.println("the origin part.getFileName() is:........"+part.getFileName());
      
		
		String filename = MimeUtility.decodeText(part.getFileName());  
//		System.out.println("the origin filename is:........" + filename); 
		
//		filename = changeStr.change(filename);
		filename = com.icss.oa.util.StringUtility.CtoB(filename);
//		System.out.println("the origin filename is222222:........" + filename);
//		System.out.println("the decode filename is:........"+filename);   
		AttachFileBean attachFile = new AttachFileBean();  
		attachFile.setFileOriginName(filename);    

		FileTypeUtil util = FileTypeUtil.getInstance();    
		String tmpName =
			System.currentTimeMillis() + "." + util.getFileExtension(filename);
//		System.out.println("the tmpName is:"+tmpName);  
//		System.out.println("begin to copy..........");  
		InputStream is = part.getInputStream();
		FileUtil.copy(is, new File(filepath + filename));  
		attachFile.setDownloadUrl(directory + File.separator+ File.separator+ filename); 
//		System.out.println("the origin filename is:........11"+directory + File.separator + filename);
		//attachFile.setDownloadUrl(directory + File.separator + tmpName);
		File file = new File(filepath + filename); 
		attachFile.setFilesize(file.length());
		if(is!=null){
			is.close();
		}
		return attachFile;
	}
	
	/**
	 * 存储附件流
	 * @param filepath
	 * @param part
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	public InputStream saveFile(
		Part part)
		throws MessagingException, IOException {
		
		InputStream in = null;
		in = part.getInputStream();
		return in;
	}
	
	/**
	 * 判断是否为附件部分
	 * @param part
	 * @return
	 * @throws MessagingException
	 */
	public boolean isAttachPart(Part part) throws MessagingException {
		try {
			return part.getDisposition() != null
				&& (part.getDisposition().equals(Part.ATTACHMENT)
					|| part.getDisposition().equals(Part.INLINE));
		} catch (Exception e) {
			return true;//有附件，但无法解析
		}
	}
	/**
	 * 得到根目录下各个“箱”需要显示的部份：元素为:文件数、未读文件数、文件所占总空间
	 * @return
	 * @throws MessagingException
	 */
	public List getShowContent(String[][] str){
		
		List reList = new ArrayList();
		int a1=0,a2=0; long a3=0;
		int b1=0,b2=0; long b3=0;
		int c1=0,c2=0; long c3=0;
		int d1=0,d2=0; long d3=0;
		
		String firstword = "";
		
		//元素为:文件数、未读文件数、文件所占总空间
		String[] ReceContent = new String[3];  //收件箱
		String[] SentContent = new String[3];  //发件箱
		String[] DraftContent = new String[3]; //草稿箱
		String[] JunkContent = new String[3];  //垃圾箱
				
//		str[0].length为邮件的个数       
		for(int i=0; i < str[0].length; i++){
			firstword = str[2][i].substring(0,1);
			long result = Long.parseLong(str[0][i]);    //得到邮件大小
			if(FileTransferConfig.RECE_FLAG.equals(firstword)){  //收件箱
				a1++;
				String nOro = str[2][i].substring(1,2);
				if(FileTransferConfig.NEW_FLAG.equals(nOro))
					a2++;
				a3 = a3+result;
			}else if(FileTransferConfig.SENT_FLAG.equals(firstword)){  //发件箱
				b1++;
				String nOro = str[2][i].substring(1,2);
				if(FileTransferConfig.NEW_FLAG.equals(nOro))
					b2++;
				b3 = b3+result;
			}else if(FileTransferConfig.DRA_FLAG.equals(firstword)){   //草稿箱
				c1++;
				String nOro = str[2][i].substring(1,2);
				if(FileTransferConfig.NEW_FLAG.equals(nOro))
					c2++;
				c3 = c3+result;
			}else if(FileTransferConfig.JUNK_FLAG.equals(firstword)){  //垃圾箱
				d1++;
				String nOro = str[2][i].substring(1,2);
				if(FileTransferConfig.NEW_FLAG.equals(nOro))
					d2++;
				d3 = d3+result;
			}
		}
		ReceContent[0] = Integer.toString(a1);
		ReceContent[1] = Integer.toString(a2);
		ReceContent[2] = Long.toString(a3);
				
		SentContent[0] = Integer.toString(b1);
		SentContent[1] = Integer.toString(b2);
		SentContent[2] = Long.toString(b3);
				
		DraftContent[0] = Integer.toString(c1);
		DraftContent[1] = Integer.toString(c2);
		DraftContent[2] = Long.toString(c3);
		
		JunkContent[0] = Integer.toString(d1);
		JunkContent[1] = Integer.toString(d2);
		JunkContent[2] = Long.toString(d3);
	

		reList.add(this.getArrayList(ReceContent));
		reList.add(this.getArrayList(SentContent));
		reList.add(this.getArrayList(DraftContent));
		reList.add(this.getArrayList(JunkContent));
        
		return reList;
	}
	
	/**
	 * 得到用户自定义文件夹需要显示的部份：元素为:文件数、未读文件数、文件所占总空间
	 * 每次只返回一个
	 * @return
	 * @throws MessagingException
	 */
	public List getUserFolder(String[][] str){
		List userList = new ArrayList();
		int m1=0,m2=0; long m3=0;
		//元素为:文件数、未读文件数、文件所占总空间
		String[] oneMail = new String[3];  //收件箱
		//str[0].length为邮件的个数 
		m1 = str[0].length;      
		for(int i=0; i < str[0].length; i++){
			String second = str[2][i].substring(1,2);
			if(FileTransferConfig.NEW_FLAG.equals(second)){
				m2++;
			}
			m3 = m3 + Long.parseLong(str[0][i]);
		}
		oneMail[0] = Integer.toString(m1);
		oneMail[1] = Integer.toString(m2);
		oneMail[2] = Long.toString(m3);
		
		return this.getArrayList(oneMail);
	}
	
	/**
	 * 得到邮件大小的字符串表示
	 * @return
	 * @throws MessagingException
	 */
	public String getMailMemory(long number){
		double mailnum = 0;
		String mailMemory = "";  //用于页面显示
		if(number>=1024*1024){
			mailnum = CommUtil.getDivision(number, 1024*1024, 2);
			mailMemory = Double.toString(mailnum)+"MB";
		}else{
			mailnum = CommUtil.getDivision(number, 1024, 2);
			mailMemory = Double.toString(mailnum)+"KB";
		}
		return mailMemory;
	}
	
	/**
	 * 数组转换为List
	 * @return
	 * @throws MessagingException
	 */
	public List getArrayList(String[] str){
		List tempList = new ArrayList();
		for(int i=0;i<str.length;i++){
			tempList.add(str[i]);
		}
		return tempList;
	}
	
	/**
	 * 判断要发的邮件是否大于接收人邮箱的剩余空间
	 * @return  返回可发送的邮件地址  和  不可发送的接收人ID
	 * @throws MessagingException
	 */
	public List confirmOverflow(String path,long memory,String address,String sendUserCName,String userid){
		
		//日志工厂
		LogFactory factory = new FileLogFactory("FileTransfor");
		//日志对象
		Log log = factory.newInstance(this.getClass(),loghead);
		
		//输出参数信息
		log.debug("confirmOverflow("+path+","+memory+","+address+","+sendUserCName+","+userid+")");
		
		//声明变量
		log.debug("confirmOverflow() step1 声明变量 begin");
		List newUser = new ArrayList();
		long sendMemory = memory;
		String sendAddress = address;
		//分割接收地址
		StringTokenizer addresslist = new StringTokenizer(sendAddress,",");  
		StringBuffer canAddress = new StringBuffer();        //可发送的
		StringBuffer cannotAddress = new StringBuffer();        //可发送的
		log.debug("confirmOverflow() step1 声明变量 end");
		
		System.out.println("countTokens  "+addresslist.countTokens());
		
		log.debug("confirmOverflow() step2 while循环 begin");
		int cnt = 0;
		while (addresslist.hasMoreTokens()) {

			List reList = new ArrayList();
			List userFolder = new ArrayList();
			List userFolderName = new ArrayList();
			
			String oneaddress = addresslist.nextToken();
			int index = oneaddress.indexOf("@");
			
			
			//edit by yangyang 20050701
			if(index<0){
				log.debug("while()循环内 第"+cnt+"次循环异常! 变量oneaddress（邮件发送地址）=" + oneaddress + "，邮件接收地址中未包括@符号！");
				continue;
			}
			
			//edit by yangyang 20050701
			String username = oneaddress.substring(0,index).trim();
			String domain = PropertyManager.getProperty("archivesdomain")==null?"未获取":PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip")==null?"未获取":PropertyManager.getProperty("archivesip");
			
			//edit by yangyang 20050701
			log.debug("while()循环内 第"+cnt+"次循环 oneaddress（邮件发送地址） = " + oneaddress + " , index（@符号位置） = " + index + " , username（邮件用户名） = " + username + " , domain（域名称） = " + domain + " , ip（IP地址） = " + ip);
			
			
			dirmanage mailhandler = null;
			try{
				log.debug("new dirmanage() begin");
				mailhandler = new dirmanage(ip, username, domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				log.debug("new dirmanage() end");
				long totalmemo1 = mailhandler.getDirSize("");
				/*log.debug("mailhandler.fileheadList() begin");
				String[][] str = mailhandler.fileheadList("");
				log.debug("mailhandler.fileheadList() end");
				
				//得到在jsp中要显示的内容
				log.debug("this.getShowContent("+str+") begin");
				reList = this.getShowContent(str);
				log.debug("this.getShowContent("+str+") end");
				
				log.debug("reList.get(0)~reList.get(4) begin");
				//元素为:根目录下文件数、未读文件数、文件所占总空间
				List ReceContent = (List)reList.get(0);  //收件箱
				List SentContent = (List)reList.get(1);  //发件箱
				List DraftContent = (List)reList.get(2); //草稿箱
				List JunkContent = (List)reList.get(3);  //垃圾箱
				log.debug("reList.get(0)~reList.get(4) end");
				
				
				//得到用户自定义的文件夹
				log.debug("mailhandler.dirList() begin");
				String[] uFolder = mailhandler.dirList("");
				log.debug("mailhandler.dirList() end");
				
				//得到用户所有自定义文件夹中的邮件列表(一个文件夹是一个LIST，一起放在一个大LIST中)
				log.debug("mailhandler.fileheadList() begin");
				if(uFolder.length>0){
					for(int i=0;i<uFolder.length;i++){
						
						log.debug("mailhandler.fileheadList("+uFolder[i]+") begin");
						String[][] userStr = mailhandler.fileheadList(uFolder[i]);
						log.debug("mailhandler.fileheadList("+uFolder[i]+") end");
						
						userFolder.add(this.getUserFolder(userStr));
					}
				}
				log.debug("mailhandler.fileheadList() end");
				
				
				//得到接收者定义文件夹中所有邮件大小的总和
				long userTotalMemory = 0;
				//得到用户所有自定义文件夹中的邮件列表(一个文件夹是一个LIST，一起放在一个大LIST中)
				for(int m=0;m<userFolder.size();m++){
					List oneList = (List)userFolder.get(m);
					userTotalMemory+=Long.parseLong(oneList.get(2).toString());
				}
				
				long totalmemo = Long.parseLong(ReceContent.get(2).toString())+Long.parseLong(SentContent.get(2).toString())
											+Long.parseLong(DraftContent.get(2).toString())+Long.parseLong(JunkContent.get(2).toString())
											+userTotalMemory;					
				long spareMemory = this.getMailBoxMemory(username)*1024*1024-totalmemo;*/
				long spareMemory = this.getMailBoxMemory(username)*1024*1024-totalmemo1;
				
				
//				log.debug("userTotalMemory = " + userTotalMemory);
				log.debug("totalmemo = " + totalmemo1);
				log.debug("spareMemory = " + spareMemory);
				
				
				if(sendMemory>spareMemory){
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[0]");
					
					//第1部分
					FiletransferSetVO ftsVO = this.getMailUserVO(username);
					
					log.debug("while()循环内 第"+cnt+"次循环，username = " + username);
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[1]");
					if(ftsVO==null){
						log.debug("while()循环内 第"+cnt+"次循环，ftsVO==null");
					}
					
					//第2部分
					//不能发送,加入待办
					if(ftsVO!=null && FileTransferConfig.TOINTEND.equals(ftsVO.getFsRule())){
						
						log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[2]");
						
						//判断是否要加入待办事宜
						String topic= new String();
						long nowtime = System.currentTimeMillis();
						topic = "邮箱已满，"+CommUtil.getTime(nowtime)+" "+sendUserCName+"给您发的邮件被退回！";
						String tointendId = userid + "," + Long.toString(nowtime) + "," + username;
						
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") 加入到待办工作 begin");
						this.addToIntend(path,username,topic,tointendId);
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") 加入到待办工作 end");

						log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[3]");
					}
					
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[4]");
					
					//第3部分
					//cannotAddress.append(username).append("@").append(domain).append(",");
					cannotAddress.append(username);
					cannotAddress.append("@");
					cannotAddress.append(domain);
					cannotAddress.append(",");
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[5]");
					
					
//					//不能发送，滚动删除后再发送
//					if(FileTransferConfig.ROLLDELETE.equals(ftsVO.getFsRule())){
//						//对Inbox中的邮件依时间从旧到新接收时间进行排序
//						List newStr = OrderByTime(str);
//						
//						int newStrLength = newStr.size();
//						for(int x=0;x<newStrLength;x++){
//							String mailpath = ((List)newStr.get(x)).get(2).toString();
//							mailhandler.deletedir(mailpath,1);
//							spareMemory = spareMemory+Long.parseLong(((List)newStr.get(x)).get(0).toString());
//							if(sendMemory<spareMemory){
//								break;
//							}
//						}
//						canAddress.append(username).append("@").append(domain).append(",");
//					}

				}else{
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory<=spareMemory[0]");
					
					canAddress.append(username);
					canAddress.append("@");
					canAddress.append(domain);
					canAddress.append(",");
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory<=spareMemory[1]");
					
				}
				
				
			} catch (DAOException e) {
				
				
								
				e.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				e.printStackTrace();
			    
				//把异常记录到日志中
				log.error(e.getMessage(),e);
				
			}finally{
				if(mailhandler!=null){
					try {
						mailhandler.disconnect();
					} catch (LdapException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						
						//把异常记录到日志中
						log.error(e.getMessage(),e);
						
					}
				}
			    
				//计数器加1
				cnt++;
			}
		    
		} //while
		
		log.debug("confirmOverflow() step2 while循环 end");
		
		
		log.debug("confirmOverflow() step3 构造返回结果 begin");		
		int canLength = canAddress.toString().length();
		String reAddress1 = "";
		if(canLength>0){
			reAddress1 = canAddress.toString().substring(0,canLength-1);
		}
		
		int cannotLength = cannotAddress.toString().length();
		String reAddress2 = "";
		if(cannotLength>0){
			reAddress2 = cannotAddress.toString().substring(0,cannotLength-1);
		}
		
		log.debug("reAddress1 = " + reAddress1);
		log.debug("reAddress2 = " + reAddress2);
		
		newUser.add(reAddress1);
		newUser.add(reAddress2);
		
		log.debug("confirmOverflow() step3 构造返回结果 end");
		
		return newUser;
	}
	
	/**
	 * 发送邮件时判断要发的邮件是否大于接收人邮箱的剩余空间
	 * 
	 * @param path
	 * @param memory
	 * @param addressList		收件人地址集合
	 * @param sendUserCName		发送人的姓名
	 * @param userid
	 * @return	返回可发送的邮件地址  和  不可发送的接收人ID
	 */
	public List confirmOverflowForSendFile1(String path,long memory,List addressList,String sendUserCName,String userid,String isSent){
		
		//日志工厂
		LogFactory factory = new FileLogFactory("FileTransfor");
		//日志对象
		Log log = factory.newInstance(this.getClass(),loghead);
		
		String address = "";
		
		for (int i = 0; i < addressList.size(); i++) {
			String currAddress = (String)addressList.get(i);
			address += currAddress + ",";
		}
		if(address.indexOf(",")>0){
			address = address.substring(0,address.lastIndexOf(","));
		}
		
		//输出参数信息
		log.debug("confirmOverflow("+path+","+memory+","+address+","+sendUserCName+","+userid+")");
		
		//声明变量
		log.debug("confirmOverflow() step1 声明变量 begin");
		List newUser = new ArrayList();
		long sendMemory = memory;
		StringBuffer canAddress = new StringBuffer();        //可发送的
		StringBuffer cannotAddress = new StringBuffer();     //可发送的
//		cannotAddress.
		
		long add_connection_time = 0 ;
		long add_comput_time = 0 ;

		
		log.debug("confirmOverflow() step1 声明变量 end");
		
		log.debug("confirmOverflow() step2 for循环 begin 循环次数="+addressList.size());
		int cnt = 0;
		long step11 = System.currentTimeMillis();
		
		String domain = PropertyManager.getProperty("archivesdomain")==null?"未获取":PropertyManager.getProperty("archivesdomain");
		String ip = PropertyManager.getProperty("archivesip")==null?"未获取":PropertyManager.getProperty("archivesip");

		usermanage user = new usermanage();
		dirmanage dir = null;
		try {
			user.connect(ip,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
			dir=new dirmanage(ip);//调用dirmanage的另外一个构造方法，此方法不需要连接ldap来查找用户目录 。
		} catch (LdapException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		  UserManagerCache cache = UserManagerCache.getInstance();

//		Mailhandler pathhandler = new Mailhandler();
        
		for(int n = 0; n<addressList.size(); n++){
		//while (addresslist.hasMoreTokens()) {

			try{
				List reList = new ArrayList();
				List userFolder = new ArrayList();
				List userFolderName = new ArrayList();
				
				//edit by yangyang 
				String oneaddress = (String)addressList.get(n);
				//String oneaddress = addresslist.nextToken();
				
				int index = oneaddress.indexOf("@");
				
				//edit by yangyang 20050701
				log.debug("for()循环内 第"+cnt+"次循环，index=" + index );        
				if(index<0){
					log.debug("for()循环内 第"+cnt+"次循环异常! 变量oneaddress（邮件发送地址）=" + oneaddress + "，邮件接收地址中未包括@符号！");
					continue;  
				}
				
				//edit by yangyang 20050701
				String username = oneaddress.substring(0,index).trim();
				
				//edit by yangyang 20050701
				log.debug("for()循环内 第"+cnt+"次循环 oneaddress（邮件发送地址） = " + oneaddress + " , index（@符号位置） = " + index + " , username（邮件用户名） = " + username + " , domain（域名称） = " + domain + " , ip（IP地址） = " + ip);

				log.debug("for()循环内 第"+cnt+"次循环，new dirmanage() begin");
				long mailhandler_begin = System.currentTimeMillis();
				
				String path1[] = new String[1];
				
				path1[0] = QueryMailPath1(username).getUser_path();
				
				log.debug(username+" 的d路径是 ："+path1[0]);
				
				if(path1[0]==null||"".equals(path1[0])){
					
					String rootpath=user.getUserRootPath(username,domain);
					path1[0] = rootpath;
					log.debug(username+" 的L路径是 ："+rootpath);
					InsertMailPath1(username,rootpath);
					
				}
//				  path1[0] =  cache.getUserRootPath(username);
				
//				mailhandler = new dirmanage(ip, username, domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				log.debug("使用新的接口");
//				path1[0]=user.getUserRootPath(username,"xinhua.com");     //得到用户的邮箱地址 

		        
				long mailhandler_end = System.currentTimeMillis();
				add_connection_time = add_connection_time +(mailhandler_end-mailhandler_begin);
				log.debug("--- step1 to step2 用时"+(mailhandler_end-mailhandler_begin)+"毫秒！约合"+(mailhandler_end-mailhandler_begin)/1000+"秒！ ------------------------------------------------- ");
				log.debug("for()循环内 第"+cnt+"次循环，new dirmanage() end");  
				
				long totalmemo1_begin = System.currentTimeMillis();
				
				long [] jj = dir.getDirSize(path1); //得到所有用户邮箱的大小
				long totalmemo1 = jj[0]; //得到所有用户邮箱的大小
//				long totalmemo1 = mailhandler.getDirSize("");   
//				long totalmemo1 = dir.getDirSize(path); //得到所有用户邮箱的大小
				long spareMemory = this.getMailBoxMemory(username)*1024*1024-totalmemo1;

				
				long totalmemo1_end = System.currentTimeMillis();
				add_comput_time = add_comput_time +(totalmemo1_end-totalmemo1_begin);
				log.debug("--- step1 to step2 用时"+(totalmemo1_end-totalmemo1_begin)+"毫秒！约合"+(totalmemo1_end-totalmemo1_begin)/1000+"秒！ ------------------------------------------------- ");
				
				if((sendMemory>spareMemory)||((userid.equals(username)&&("checked2".equals(isSent)&&(sendMemory>2*spareMemory))))){
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[0]");
					
					//第1部分
					FiletransferSetVO ftsVO = this.getMailUserVO(username);
					
					log.debug("while()循环内 第"+cnt+"次循环，username = " + username);
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[1]");
					if(ftsVO==null){
						log.debug("while()循环内 第"+cnt+"次循环，ftsVO==null");
					}
					
					//第2部分
					//不能发送,加入待办
					if(ftsVO!=null && FileTransferConfig.TOINTEND.equals(ftsVO.getFsRule())){
						
						log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[2]");
						
						//判断是否要加入待办事宜
						String topic= new String();
						long nowtime = System.currentTimeMillis();
						topic = "邮箱已满，"+CommUtil.getTime(nowtime)+" "+sendUserCName+"给您发的邮件被退回！";
						String tointendId = userid + "," + Long.toString(nowtime) + "," + username;
						
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") 加入到待办工作 begin");
						this.addToIntend(path,username,topic,tointendId);
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") 加入到待办工作 end");

						log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[3]");
					}
					
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[4]");
					
					//第3部分
					//cannotAddress.append(username).append("@").append(domain).append(",");
					cannotAddress.append(username);
					cannotAddress.append("@");
					cannotAddress.append(domain);
					cannotAddress.append(",");
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory>spareMemory[5]");
					
					
//					//不能发送，滚动删除后再发送
//					if(FileTransferConfig.ROLLDELETE.equals(ftsVO.getFsRule())){
//						//对Inbox中的邮件依时间从旧到新接收时间进行排序
//						List newStr = OrderByTime(str);
//						
//						int newStrLength = newStr.size();
//						for(int x=0;x<newStrLength;x++){
//							String mailpath = ((List)newStr.get(x)).get(2).toString();
//							mailhandler.deletedir(mailpath,1);
//							spareMemory = spareMemory+Long.parseLong(((List)newStr.get(x)).get(0).toString());
//							if(sendMemory<spareMemory){
//								break;
//							}
//						}
//						canAddress.append(username).append("@").append(domain).append(",");
//					}

				}else{
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory<=spareMemory[0]");
					
					canAddress.append(username);
					canAddress.append("@");
					canAddress.append(domain);
					canAddress.append(",");
					
					log.debug("while()循环内 第"+cnt+"次循环，sendMemory<=spareMemory[1]");
					
				}
			
			}  catch (Exception e) {
				
				log.debug("for()循环内 第"+cnt+"次循环，Exception");
				log.debug("for()循环内 第"+cnt+"次循环，Exception " + e.getMessage());
				log.error(e.getMessage(),e);
				
				System.err.println(e.getMessage());
				e.printStackTrace();
			    
				
			}finally{
		    	
				log.debug("for()循环内 第"+cnt+"次循环，finally[0]");
		    	
			    
				log.debug("for()循环内 第"+cnt+"次循环，finally[4]");
			    
				//计数器加1
				cnt++;
			}
		    
		} //结束for循环

		 
		 for (int i = 0; i < addressList.size(); i++) {
//			 System.out.println("map"+to[i]+list_result.get(to[i].substring(0,to[i].lastIndexOf("@"))));
	   }
		
		log.debug("confirmOverflow() step2 for循环 end");

		
		log.debug("confirmOverflow() step3 构造返回结果 begin");		
		
		log.debug("confirmOverflow() step3 构造返回结果 end");
		long step12 = System.currentTimeMillis();
		log.debug("--- step1 to step2 用时"+(step12-step11)+"毫秒！约合"+(step12-step11)/1000+"秒！ ------------------------------------------------- ");
		log.debug("--- 链接总用时 "+add_connection_time+"毫秒！约合"+add_connection_time/1000+"秒！ ------- ");
		log.debug("--- 判断总用时 "+add_comput_time+"毫秒！约合"+add_comput_time/1000+"秒！ ------- "); 
		
		int canLength = canAddress.toString().length();
		String reAddress1 = "";
		if(canLength>0){
			reAddress1 = canAddress.toString().substring(0,canLength-1);
		}
		
		int cannotLength = cannotAddress.toString().length();
		String reAddress2 = "";
		if(cannotLength>0){
			reAddress2 = cannotAddress.toString().substring(0,cannotLength-1);
		}
		
		log.debug("reAddress1 = " + reAddress1);
		log.debug("reAddress2 = " + reAddress2);
		
		newUser.add(reAddress1);
		newUser.add(reAddress2);
		return newUser;
	}	
	

	/**
	 * 发送邮件时判断要发的邮件是否大于接收人邮箱的剩余空间
	 * 
	 * @param path
	 * @param memory
	 * @param addressList		收件人地址集合
	 * @param sendUserCName		发送人的姓名
	 * @param userid
	 * @return	返回可发送的邮件地址  和  不可发送的接收人ID
	 */
	public List confirmOverflowForSendFile(String path,long memory,List addressList,String sendUserCName,String userid){
		
		//日志工厂
		LogFactory factory = new FileLogFactory("FileTransfor");
		//日志对象
		Log log = factory.newInstance(this.getClass(),loghead);
		
		String address = "";
		for (int i = 0; i < addressList.size(); i++) {
			String currAddress = (String)addressList.get(i);
			address += currAddress + ",";
		}
		if(address.indexOf(",")>0){
			address = address.substring(0,address.lastIndexOf(","));
		}
		
		//输出参数信息
		log.debug("confirmOverflow("+path+","+memory+","+address+","+sendUserCName+","+userid+")");
		
		//声明变量
		log.debug("confirmOverflow() step1 声明变量 begin");
		List newUser = new ArrayList();
		long sendMemory = memory;
		StringBuffer canAddress = new StringBuffer();        //可发送的
		StringBuffer cannotAddress = new StringBuffer();     //可发送的
//		cannotAddress.
		
		long add_connection_time = 0 ;
		long add_comput_time = 0 ;
		
		//分割接收地址
		//String sendAddress = address;		
		//StringTokenizer addresslist = new StringTokenizer(sendAddress,",");
		//System.out.println("countTokens  "+addresslist.countTokens());
		
		log.debug("confirmOverflow() step1 声明变量 end");
		
		log.debug("confirmOverflow() step2 for循环 begin 循环次数="+addressList.size());
		int cnt = 0;
		long step11 = System.currentTimeMillis();
		
		String domain = PropertyManager.getProperty("archivesdomain")==null?"未获取":PropertyManager.getProperty("archivesdomain");
		String ip = PropertyManager.getProperty("archivesip")==null?"未获取":PropertyManager.getProperty("archivesip");
		
		String path1[]=new  String[1];
		
		dirmanage mailhandler = null;
		
		usermanage user=new usermanage();
		dirmanage dir = null;//调用dirmanage的另外一个构造方法，此方法不需要连接ldap来查找用户目录 。
		try {
			dir = new dirmanage(ip);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			user.connect(ip,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
		} catch (LdapException e1) {
			e1.printStackTrace();
		}
		
		for(int n = 0; n<addressList.size(); n++){
		//while (addresslist.hasMoreTokens()) {

			try{
				List reList = new ArrayList();
				List userFolder = new ArrayList();
				List userFolderName = new ArrayList();
				
				//edit by yangyang 
				String oneaddress = (String)addressList.get(n);
				//String oneaddress = addresslist.nextToken();
				
				int index = oneaddress.indexOf("@");
				
				//edit by yangyang 20050701
				log.debug("for()循环内 第"+cnt+"次循环，index=" + index );        
				if(index<0){
					log.debug("for()循环内 第"+cnt+"次循环异常! 变量oneaddress（邮件发送地址）=" + oneaddress + "，邮件接收地址中未包括@符号！");
					continue;  
				}
				
				//edit by yangyang 20050701
				String username = oneaddress.substring(0,index).trim();
				
				//edit by yangyang 20050701
				log.debug("for()循环内 第"+cnt+"次循环 oneaddress（邮件发送地址） = " + oneaddress + " , index（@符号位置） = " + index + " , username（邮件用户名） = " + username + " , domain（域名称） = " + domain + " , ip（IP地址） = " + ip);

				log.debug("for()循环内 第"+cnt+"次循环，new dirmanage() begin");
				long mailhandler_begin = System.currentTimeMillis();
//				mailhandler = new dirmanage(ip, username, domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				log.debug("使用新的接口");
				path1[0]=user.getUserRootPath(username,"xinhua.com");     //得到用户的邮箱地址 
				long mailhandler_end = System.currentTimeMillis();
				add_connection_time = add_connection_time +(mailhandler_end-mailhandler_begin);
				log.debug("--- step1 to step2 用时"+(mailhandler_end-mailhandler_begin)+"毫秒！约合"+(mailhandler_end-mailhandler_begin)/1000+"秒！ ------------------------------------------------- ");
				log.debug("for()循环内 第"+cnt+"次循环，new dirmanage() end");  
				
				long totalmemo1_begin = System.currentTimeMillis();
//				long totalmemo1 = mailhandler.getDirSize("");   
				long totalmemo1 = dir.getDirSize(path); //得到所有用户邮箱的大小
				
//				log.debug("for()循环内 第"+cnt+"次循环，mailhandler.fileheadList() begin");
//				String[][] str = mailhandler.fileheadList("");
//				log.debug("for()循环内 第"+cnt+"次循环，mailhandler.fileheadList() end");
//				
//				//得到在jsp中要显示的内容
//				log.debug("for()循环内 第"+cnt+"次循环，this.getShowContent("+str+") begin");
//				reList = this.getShowContent(str);
//				log.debug("for()循环内 第"+cnt+"次循环，this.getShowContent("+str+") end");
//				
//				log.debug("for()循环内 第"+cnt+"次循环，reList.get(0)~reList.get(3) begin");
//				//元素为:根目录下文件数、未读文件数、文件所占总空间
//				List ReceContent = (List)reList.get(0);  //收件箱
//				List SentContent = (List)reList.get(1);  //发件箱
//				List DraftContent = (List)reList.get(2); //草稿箱
//				List JunkContent = (List)reList.get(3);  //垃圾箱
//				log.debug("for()循环内 第"+cnt+"次循环，reList.get(0)~reList.get(3) end");
//				
//				
//				//得到用户自定义的文件夹
//				log.debug("for()循环内 第"+cnt+"次循环，mailhandler.dirList() begin");
//				String[] uFolder = mailhandler.dirList("");
//				log.debug("for()循环内 第"+cnt+"次循环，mailhandler.dirList() end");
//				
//				//得到用户所有自定义文件夹中的邮件列表(一个文件夹是一个LIST，一起放在一个大LIST中)
//				log.debug("for()循环内 第"+cnt+"次循环，mailhandler.fileheadList() begin");
//				if(uFolder.length>0){
//					for(int i=0;i<uFolder.length;i++){
//						
//						log.debug("mailhandler.fileheadList("+uFolder[i]+") begin");
//						String[][] userStr = mailhandler.fileheadList(uFolder[i]);
//						log.debug("mailhandler.fileheadList("+uFolder[i]+") end");
//						
//						userFolder.add(this.getUserFolder(userStr));
//					}
//				}
//				log.debug("for()循环内 第"+cnt+"次循环，mailhandler.fileheadList() end");
//				
//				
//				//得到接收者定义文件夹中所有邮件大小的总和
//				long userTotalMemory = 0;
//				//得到用户所有自定义文件夹中的邮件列表(一个文件夹是一个LIST，一起放在一个大LIST中)
//				for(int m=0;m<userFolder.size();m++){
//					List oneList = (List)userFolder.get(m);
//					userTotalMemory+=Long.parseLong(oneList.get(2).toString());
//				} 
//				
//				long totalmemo = Long.parseLong(ReceContent.get(2).toString())+Long.parseLong(SentContent.get(2).toString())
//											+Long.parseLong(DraftContent.get(2).toString())+Long.parseLong(JunkContent.get(2).toString())
//											+userTotalMemory;					
//				long spareMemory = this.getMailBoxMemory(username)*1024*1024-totalmemo;
				long spareMemory = this.getMailBoxMemory(username)*1024*1024-totalmemo1;
				
				
//				log.debug("for()循环内 第"+cnt+"次循环，userTotalMemory = " + userTotalMemory);
//				log.debug("for()循环内 第"+cnt+"次循环，totalmemo = " + totalmemo1);
//				log.debug("for()循环内 第"+cnt+"次循环，sendMemory = " + sendMemory);
//				log.debug("for()循环内 第"+cnt+"次循环，spareMemory = " + spareMemory);
				
				long totalmemo1_end = System.currentTimeMillis();
				add_comput_time = add_comput_time +(totalmemo1_end-totalmemo1_begin);
				log.debug("--- step1 to step2 用时"+(totalmemo1_end-totalmemo1_begin)+"毫秒！约合"+(totalmemo1_end-totalmemo1_begin)/1000+"秒！ ------------------------------------------------- ");
				
				
				if(sendMemory>spareMemory){
					
					log.debug("for()循环内 第"+cnt+"次循环，sendMemory>spareMemory[0]");
					
					//第1部分
					FiletransferSetVO ftsVO = this.getMailUserVO(username);
					
					log.debug("for()循环内 第"+cnt+"次循环，username = " + username);
					
					log.debug("for()循环内 第"+cnt+"次循环，sendMemory>spareMemory[1]");
					if(ftsVO==null){
						log.debug("for()循环内 第"+cnt+"次循环，ftsVO==null");
					}
					
					//第2部分
					//不能发送,加入待办
					if(ftsVO!=null && FileTransferConfig.TOINTEND.equals(ftsVO.getFsRule())){
						
						log.debug("for()循环内 第"+cnt+"次循环，sendMemory>spareMemory[2]");
						
						//判断是否要加入待办事宜
						String topic= new String();
						long nowtime = System.currentTimeMillis();
						topic = "邮箱已满，"+CommUtil.getTime(nowtime)+" "+sendUserCName+"给您发的邮件被退回！";
						String tointendId = userid + "," + Long.toString(nowtime) + "," + username;
						
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") 加入到待办工作 begin");
						this.addToIntend(path,username,topic,tointendId);
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") 加入到待办工作 end");

						log.debug("for()循环内 第"+cnt+"次循环，sendMemory>spareMemory[3]");
					}
					
					
					log.debug("for()循环内 第"+cnt+"次循环，sendMemory>spareMemory[4]");
					
					//第3部分
					//cannotAddress.append(username).append("@").append(domain).append(",");
					cannotAddress.append(username);
					cannotAddress.append("@");
					cannotAddress.append(domain);
					cannotAddress.append(",");
					
					log.debug("for()循环内 第"+cnt+"次循环，sendMemory>spareMemory[5]");
					
					
//					//不能发送，滚动删除后再发送
//					if(FileTransferConfig.ROLLDELETE.equals(ftsVO.getFsRule())){
//						//对Inbox中的邮件依时间从旧到新接收时间进行排序
//						List newStr = OrderByTime(str);
//						
//						int newStrLength = newStr.size();
//						for(int x=0;x<newStrLength;x++){
//							String mailpath = ((List)newStr.get(x)).get(2).toString();
//							mailhandler.deletedir(mailpath,1);
//							spareMemory = spareMemory+Long.parseLong(((List)newStr.get(x)).get(0).toString());
//							if(sendMemory<spareMemory){
//								break;
//							}
//						}
//						canAddress.append(username).append("@").append(domain).append(",");
//					}

				}else{
					
					log.debug("for()循环内 第"+cnt+"次循环，sendMemory<=spareMemory[0]");
					
					canAddress.append(username);
					canAddress.append("@");
					canAddress.append(domain);
					canAddress.append(",");
					
					log.debug("for()循环内 第"+cnt+"次循环，sendMemory<=spareMemory[1]");
					
				}
				
				
			} catch (DAOException e) {
				
				log.debug("for()循环内 第"+cnt+"次循环，DAOException");
				log.debug("for()循环内 第"+cnt+"次循环，DAOException " + e.getMessage());
				log.error(e.getMessage(),e);
				
				System.err.println(e.getMessage());
				e.printStackTrace();

			} catch (Exception e) {
				
				log.debug("for()循环内 第"+cnt+"次循环，Exception");
				log.debug("for()循环内 第"+cnt+"次循环，Exception " + e.getMessage());
				log.error(e.getMessage(),e);
				
				System.err.println(e.getMessage());
				e.printStackTrace();
			    
				
			}finally{
		    	
				log.debug("for()循环内 第"+cnt+"次循环，finally[0]");
		    	
				if(mailhandler!=null){
					try {
						
						log.debug("for()循环内 第"+cnt+"次循环，finally[1]");
						
						mailhandler.disconnect();
						
						log.debug("for()循环内 第"+cnt+"次循环，finally[2]");
						
					} catch (LdapException e) {
						
						log.debug("for()循环内 第"+cnt+"次循环，finally[3]");
						log.debug("for()循环内 第"+cnt+"次循环，LdapException" + e.getMessage());
						log.error(e.getMessage(),e);
						
						System.err.println(e.getMessage());
						e.printStackTrace();

					}
				}
			    
				log.debug("for()循环内 第"+cnt+"次循环，finally[4]");
			    
				//计数器加1
				cnt++;
			}
		    
		} //结束for循环
		
		log.debug("confirmOverflow() step2 for循环 end");

		
		log.debug("confirmOverflow() step3 构造返回结果 begin");		
		
		log.debug("confirmOverflow() step3 构造返回结果 end");
		long step12 = System.currentTimeMillis();
		log.debug("--- step1 to step2 用时"+(step12-step11)+"毫秒！约合"+(step12-step11)/1000+"秒！ ------------------------------------------------- ");
		log.debug("--- 链接总用时 "+add_connection_time+"毫秒！约合"+add_connection_time/1000+"秒！ ------- ");
		log.debug("--- 判断总用时 "+add_comput_time+"毫秒！约合"+add_comput_time/1000+"秒！ ------- "); 
		
		int canLength = canAddress.toString().length();
		String reAddress1 = "";
		if(canLength>0){
			reAddress1 = canAddress.toString().substring(0,canLength-1);
		}
		
		int cannotLength = cannotAddress.toString().length();
		String reAddress2 = "";
		if(cannotLength>0){
			reAddress2 = cannotAddress.toString().substring(0,cannotLength-1);
		}
		
		log.debug("reAddress1 = " + reAddress1);
		log.debug("reAddress2 = " + reAddress2);
		
		newUser.add(reAddress1);
		newUser.add(reAddress2);
		return newUser;
	}	
	
	
	
	
	/**
	 * 对邮件（包括邮件目录）依时间从新到旧的接收时间进行排序
	 * @return
	 * @throws 
	 */
	public List OrderByTime(String[][] str) throws MessagingException{
		
		List newlist = new ArrayList();
		
		for(int i=0;i<str[0].length;i++){
			
			String mailHead = str[2][i];
			MessageHandler mHandler = new MessageHandler();
			Date reDate = mHandler.getReceiveDate(mailHead);
			long retime = 0;
			if(reDate!=null)
				retime = reDate.getTime();
			
			if(i==0){
				List tempList = new ArrayList();
				for(int j=0;j<4;j++){
					tempList.add(str[j][0]);
				}
				newlist.add(tempList);
			}else{
				//一次循环，插入一个邮件记录
				int listLength = newlist.size(); 
				for(int k=0;k<listLength;k++){
					//为找到一条时间小于要插入的这条记录的时间
					List temp = (ArrayList)newlist.get(k);
					String compHead = temp.get(2).toString();
					long compTime = 0;
					if(mHandler.getReceiveDate(compHead)!=null)
						compTime = mHandler.getReceiveDate(compHead).getTime();
					
					if(retime>compTime){
						//把新记录写入一个LIST
						List tempList1 = new ArrayList();
						List tempList2 = new ArrayList();
						for(int j=0;j<4;j++){
							tempList1.add(str[j][i]);
						}
						//整体往后移
						for(int m=k;m<listLength;m++){
							//exchange to set
							tempList2 = (List) newlist.get(m);
							newlist.set(m,tempList1);
							tempList1 = tempList2;
						}
						newlist.add(tempList1);
						break;
					}else{
						if(k==listLength-1){
							//小于最后一个的时间，直接加在末尾
							List tempList = new ArrayList();
							for(int j=0;j<4;j++){
								tempList.add(str[j][i]);
							}
							newlist.add(tempList);
						}
					}
				}//for
			}
			 
		}//for
		return newlist;
	}
	
	
	/**
	 * 对邮件（包括邮件目录）依时间从旧到新（升序）的接收时间进行排序
	 * @return
	 * @throws 
	 */
	public List OrderByTimeAsc(List olderlist) throws MessagingException{
		
		List newlist = new ArrayList();
		Iterator oItr = olderlist.iterator();
		
		int i = 0;
		while(oItr.hasNext()){
			
			List onemail = (ArrayList)oItr.next();
			String mailHead = (String)onemail.get(2);
			MessageHandler mHandler = new MessageHandler();
			Date reDate = mHandler.getReceiveDate(mailHead);
			long retime = 0;
			if(reDate!=null)
				retime = reDate.getTime();
			
			if(i==0){
				List tempList = new ArrayList();
				for(int j=0;j<4;j++){
					tempList.add(onemail.get(j));
				}
				newlist.add(tempList);
				i++;
			}else{
				//一次循环，插入一个邮件记录
				int listLength = newlist.size(); 
				for(int k=0;k<listLength;k++){
					//为找到一条时间大于要插入的这条记录的时间
					List temp = (ArrayList)newlist.get(k);
					String compHead = temp.get(2).toString();
					long compTime = 0;
					if(mHandler.getReceiveDate(compHead)!=null)
						compTime = mHandler.getReceiveDate(compHead).getTime();
					
					if(retime<compTime){
						//把新记录写入一个LIST
						List tempList1 = new ArrayList();
						List tempList2 = new ArrayList();
						for(int j=0;j<4;j++){
							tempList1.add(onemail.get(j));
						}
						//整体往后移
						for(int m=k;m<listLength;m++){
							//exchange to set
							tempList2 = (List) newlist.get(m);
							newlist.set(m,tempList1);
							tempList1 = tempList2;
						}
						newlist.add(tempList1);
						break;
					}else{
						if(k==listLength-1){
							//大于最后一个的时间，直接加在末尾
							List tempList = new ArrayList();
							for(int j=0;j<4;j++){
								tempList.add(onemail.get(j));
							}
							newlist.add(tempList);
						}
					}
				}//for
			}//if
			 
		}//while
		return newlist;
	}
	
	/**
	 * 对邮件（包括邮件目录）依时间从新到旧（降序）的接收时间进行排序
	 * @return
	 * @throws 
	 */
	public List OrderByTimeDes(List olderlist) throws MessagingException{
		
		List newlist = new ArrayList();
		Iterator oItr = olderlist.iterator();
		
		int i = 0;
		while(oItr.hasNext()){
			
			List onemail = (ArrayList)oItr.next();
			String mailHead = (String)onemail.get(2);
			MessageHandler mHandler = new MessageHandler();
			Date reDate = mHandler.getReceiveDate(mailHead);
			long retime = 0;
			if(reDate!=null)
				retime = reDate.getTime();
			
			if(i==0){
				List tempList = new ArrayList();
				for(int j=0;j<4;j++){
					tempList.add(onemail.get(j));
				}
				newlist.add(tempList);
				i++;
			}else{
				//一次循环，插入一个邮件记录
				int listLength = newlist.size(); 
				for(int k=0;k<listLength;k++){
					//为找到一条时间小于要插入的这条记录的时间
					List temp = (ArrayList)newlist.get(k);
					String compHead = temp.get(2).toString();
					long compTime = 0;
					if(mHandler.getReceiveDate(compHead)!=null)
						compTime = mHandler.getReceiveDate(compHead).getTime();
					
					if(retime>compTime){
						//把新记录写入一个LIST
						List tempList1 = new ArrayList();
						List tempList2 = new ArrayList();
						for(int j=0;j<4;j++){
							tempList1.add(onemail.get(j));
						}
						//整体往后移
						for(int m=k;m<listLength;m++){
							//exchange to set
							tempList2 = (List) newlist.get(m);
							newlist.set(m,tempList1);
							tempList1 = tempList2;
						}
						newlist.add(tempList1);
						break;
					}else{
						if(k==listLength-1){
							//小于最后一个的时间，直接加在末尾
							List tempList = new ArrayList();
							for(int j=0;j<4;j++){
								tempList.add(onemail.get(j));
							}
							newlist.add(tempList);
						}
					}
				}//for
			}//if
			 
		}//while
		return newlist;
	}
	
	/**
	 * 对邮件（只对其中一个文件夹）依时间从新到旧（降序）的接收时间进行排序
	 * @return
	 * @throws 
	 */
	public List OrderSingleFolderByTimeDes(List olderlist) throws MessagingException{
		
		List newlist = new ArrayList();
		Iterator oItr = olderlist.iterator();
		
		int i = 0;
		while(oItr.hasNext()){
			
			List onemail = (ArrayList)oItr.next();
			String mailHead = (String)onemail.get(1);
			MessageHandler mHandler = new MessageHandler();
			Date reDate = mHandler.getReceiveDate(mailHead);
			long retime = 0;
			if(reDate!=null)
				retime = reDate.getTime();
			
			if(i==0){
				List tempList = new ArrayList();
				for(int j=0;j<3;j++){
					tempList.add(onemail.get(j));
				}
				newlist.add(tempList);
				i++;
			}else{
				//一次循环，插入一个邮件记录
				int listLength = newlist.size(); 
				for(int k=0;k<listLength;k++){
					//为找到一条时间小于要插入的这条记录的时间
					List temp = (ArrayList)newlist.get(k);
					String compHead = temp.get(1).toString();
					long compTime = 0;
					if(mHandler.getReceiveDate(compHead)!=null)
						compTime = mHandler.getReceiveDate(compHead).getTime();
					
					if(retime>compTime){
						//把新记录写入一个LIST
						List tempList1 = new ArrayList();
						List tempList2 = new ArrayList();
						for(int j=0;j<3;j++){
							tempList1.add(onemail.get(j));
						}
						//整体往后移
						for(int m=k;m<listLength;m++){
							//exchange to set
							tempList2 = (List) newlist.get(m);
							newlist.set(m,tempList1);
							tempList1 = tempList2;
						}
						newlist.add(tempList1);
						break;
					}else{
						if(k==listLength-1){
							//小于最后一个的时间，直接加在末尾
							List tempList = new ArrayList();
							for(int j=0;j<3;j++){
								tempList.add(onemail.get(j));
							}
							newlist.add(tempList);
						}
					}
				}//for
			}//if
			 
		}//while
		return newlist;
	}
	
	/**
	 * 得到一个用户的邮箱设置VO
	 * @return
	 * @throws RjHandlerException
	 */
	public FiletransferSetVO getMailUserVO(String userId) throws DAOException {

		DAOFactory factory = new DAOFactory(this.conn);
		
		FiletransferSetDAO fDao = new FiletransferSetDAO();
		fDao.setFsUid(userId);
		factory.setDAO(fDao);
		
		FiletransferSetVO ftsVO = null;

		List mailUserList = factory.find(new FiletransferSetVO());
		Iterator muIter = mailUserList.iterator();
		while(muIter.hasNext()){
			ftsVO = (FiletransferSetVO)muIter.next();
			break;
		}

		return ftsVO;
	}

	/**
	 * 得到一个用户的邮箱容量的大小
	 * @return
	 * @throws 
	 */	
	public long getMailBoxMemory(String userId){
		long number = 0;
		FiletransferSetVO ftsVO;
		try {
			ftsVO = this.getMailUserVO(userId);
			if(ftsVO!=null){
				number = ((Integer)ftsVO.getFsSize()).longValue();	
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return number; 
	}
	
	/**
	 * 得到一个用户的邮箱报警的百分数
	 * @return
	 * @throws 
	 */	
	public long getMailBoxPercent(String userId){
		long number = 0;
		FiletransferSetVO ftsVO;
		try {
			ftsVO = this.getMailUserVO(userId);
			
			if(ftsVO!=null){
				number = ((Integer)ftsVO.getFsPer()).longValue();	
			}
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return number; 
	}
	
	/**
		 * 将邮件保存到发件箱
		 * @param mailhandler
		 * @param path
		 * @param mailmemory
		 * @param username
		 * @param chinaName
		 * @param senddomain
		 * @param subject
		 * @param text
		 * @param attachment
		 * @param flag
	 */	
	public void saveToSent(dirmanage mailhandler,String path,long mailmemory,String username,String chinaName,String domain,
						   String subject,String text,String[] attachment,int flag,String receperson) 
			throws NamingException{

//		String SendAddress = username+"@"+domain;
//		String[] to = {SendAddress};
//		
//		  //		日志工厂
//		  LogFactory factory = new FileLogFactory("FileTransfor");
//		  //日志对象
//		  Log log = factory.newInstance(this.getClass(),loghead);
//	  
//	  
//		try {
//			List sendAddress = confirmOverflow(path,mailmemory,SendAddress,chinaName,username);
//			String canSendAddress = (String)sendAddress.get(0);
//			
//			log.debug("得到保存到发件箱的人员列表 "+canSendAddress);
//			
//			boolean issuccess = false;   //判断是否保存成功
//			if(!("".equals(canSendAddress))){
//				
//				log.debug("保存到发件箱的邮件的正题名  "+subject);
//				log.debug("保存到发件箱的邮件的内容  "+text);
//				//
//				if(attachment!=null){
//					for (int i = 0; i < attachment.length; i++) {
//						log.debug("附件在服务器上的位置是："+attachment[i]);
//					}
//				}else{
//						log.debug("此邮件无附件");
//				}
//							
//				log.debug("发送给自己 start  ");
//				  mailhandler.transfermail(subject,text,to,null,null,attachment,flag,null);   
//                
//				log.debug("发送给自己 end  ");
//				//等待，用于发送给自己的邮件为已经被收到
//				//050702修改
//				if(mailmemory<1000000){
//					Thread.sleep(2000);
//				}else if(mailmemory<30000){
//					Thread.sleep(6000);
//				}else if(mailmemory<6000){
//					Thread.sleep(8000);
//				}else{
//					Thread.sleep(10000);
//				}
//				
//
//				//发送给自己后马上把接收到的邮件转移到发件箱
//				MessageHandler handler = new MessageHandler();
//				String[][] str = mailhandler.fileheadList("");
//				for(int i=0;i<str[0].length;i++){
//					String mailhead = str[1][i];
//					String reSubject = handler.getIntendSubject(mailhead);
////					String recePerosn = handler.getRecipients(mailhead);
//					if(subject.equals(reSubject)){  //用时间来判断
//						String mark = FileTransferConfig.SENT_FLAG;
//						String mailName = str[2][i];
//						String newmailName = mark+"o"+mailName.substring(2,mailName.length());
//						issuccess = mailhandler.dirRename(mailName,newmailName);
//					}
//				}
//			}
			
			//写阅读记录表(用于查看有多少人阅读了发出的文件）
			//if(issuccess){
				this.setRecord(username,subject,receperson);
			//}

//		} catch (MessagingException e) {
//			e.printStackTrace();
//			log.debug("保存到发件箱时发生错误  "+e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.debug("保存到发件箱时发生错误  "+e.getMessage());
//		} 
	}
	
	/**
	 * 写入数据库，标识为需要阅读记录
	 * @param 邮箱用户USERID和邮件标题（包括时间）
	 * @return
	 * @throws 
	 */
	public void setRecord(String userid,String subject,String receperson){
		FiletransferWanttorecordDAO fdao = new FiletransferWanttorecordDAO();
		fdao.setRecordid(userid.concat(",").concat(subject));
		fdao.setConnection(conn);
		Integer mailid;
		try {
			fdao.create();
			//写另一个表
			FiletransferSetHandler ftshandler = new FiletransferSetHandler(conn);
			mailid = fdao.getMailid();
			StringTokenizer reAddress = new StringTokenizer(receperson.toString(),",");
			String oneReAddress = "";
			String reUserid = "";
			while (reAddress.hasMoreTokens()) {
				oneReAddress = reAddress.nextToken().toString();
				reUserid = oneReAddress.substring(0,oneReAddress.indexOf("@"));
				String personuuid = ftshandler.getAllUUid(reUserid);
				FiletransferReadrecordDAO frecorddao = new FiletransferReadrecordDAO();
				frecorddao.setMailid(mailid);
				frecorddao.setReadpersonuuid(personuuid);
				frecorddao.setIsread(FileTransferConfig.NOREAD);
				frecorddao.setConnection(conn);
				try {
					frecorddao.create();
				} catch (DAOException e) {		
					e.printStackTrace();
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 写入数据库，标识为需要阅读记录(若从发件箱转发，则需要把接收人加到阅读记录表中)
	 * @param 邮箱用户USERID和邮件标题（包括时间）及接收人的所有邮件地址
	 * @return
	 * @throws 
	 */
	public void setOnlyRecord(String userid,String subject,String receperson){
		//写另一个表
		FiletransferSetHandler ftshandler = new FiletransferSetHandler(conn);
		String code = userid.concat(",").concat(subject.trim().substring(3));
		Integer mailid = this.getRecordMailid(code);
		StringTokenizer reAddress = new StringTokenizer(receperson.toString(),",");
		String oneReAddress = "";
		String reUserid = "";
		while (reAddress.hasMoreTokens()) {
			oneReAddress = reAddress.nextToken().toString();
			reUserid = oneReAddress.substring(0,oneReAddress.indexOf("@"));
			String personuuid = ftshandler.getUUid(reUserid);
			FiletransferReadrecordDAO frecorddao = new FiletransferReadrecordDAO();
			frecorddao.setMailid(mailid);
			frecorddao.setReadpersonuuid(personuuid);
			frecorddao.setIsread(FileTransferConfig.NOREAD);
			DAOFactory factory = new DAOFactory(conn);
			factory.setDAO(frecorddao);
			List list;
			try {
				list = factory.find(new FiletransferReadrecordVO());
				if(list==null||list.size()==0){
					frecorddao.setConnection(conn);
					frecorddao.create();
				}
			} catch (DAOException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 删除一条阅读记录
	 * @param 邮箱用户USERID和邮件标题（包括时间）
	 * @return
	 * @throws 
	 */
	public void deleteRecord(String userid,String subject){
		FiletransferWanttorecordDAO fdao = new FiletransferWanttorecordDAO();
		String recordid = userid.concat(",").concat(subject);
		Integer mailid = this.getRecordMailid(recordid);
		if(mailid!=null){
			fdao.setMailid(mailid);
			fdao.setConnection(conn);
			try {
				fdao.delete();
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 查看一个邮件是否需要有阅读记录，若需要，则返回MAILID，否则返回NULL
	 * @return
	 * @throws 
	 */
	public Integer getRecordMailid(String subject){
		Integer recordMailid = null;
		List wantlist = new ArrayList();
		FiletransferWanttorecordDAO fdao = new FiletransferWanttorecordDAO();
		fdao.setRecordid(subject);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(fdao);
		try {
			wantlist = factory.find(new FiletransferWanttorecordVO());
			if(wantlist!=null&&wantlist.size()>0){   //为一个需要记录阅读记录的邮件
				FiletransferWanttorecordVO fvo = (FiletransferWanttorecordVO)wantlist.get(0);
				recordMailid = fvo.getMailid();
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return recordMailid;
	}
	
	/**
	 * 写入数据库，若为已读，则不重新写入，若没有，就写入
	 * @return
	 * @throws 
	 */
	public void writeReadPersonuuid(Integer mailId,String personuuid){
		FiletransferReadrecordDAO fdao = new FiletransferReadrecordDAO();
		fdao.setMailid(mailId);
		fdao.setReadpersonuuid(personuuid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(fdao);
		try {
			List list = factory.find(new FiletransferReadrecordVO());
			if(list!=null||list.size()>0){  //此用户第一次读这个需要写阅读记录的邮件
				FiletransferReadrecordVO fvo = (FiletransferReadrecordVO)list.get(0);
				String isread = fvo.getIsread();
				if(FileTransferConfig.NOREAD.equals(isread)){
					Statement stmt = conn.createStatement();
					String sql = "update FILETRANSFER_READRECORD set isread='"+FileTransferConfig.READ+"'"
						+ " where mailid="+mailId+" AND readpersonuuid='"+personuuid+"'";
					stmt.executeUpdate(sql);
					stmt.close();
				}
			}
		} catch (DAOException e) {		
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 写入数据库，加一条阅读记录，但标识为未读
	 * @param 邮箱用户USERID和邮件标题（包括时间）
	 * @return
	 * @throws 
	 */
	public void setisRead(String userid,String subject){
		FiletransferWanttorecordDAO fdao = new FiletransferWanttorecordDAO();
		fdao.setRecordid(userid.concat(",").concat(subject));
		fdao.setConnection(conn);
		try {
			fdao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 依RECORDMAILID查出所有阅读过此邮件的人的中文名称
	 * @return
	 * @throws 
	 */
	public List getReadPersonName(Integer recordMailid){
		List Personuuidlist = new ArrayList();
		List personList = new ArrayList();
		FiletransferReadrecordDAO fdao = new FiletransferReadrecordDAO();
		fdao.setMailid(recordMailid);
		fdao.setIsread(FileTransferConfig.READ);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(fdao);
		try {
			Personuuidlist = factory.find(new FiletransferReadrecordVO());
			if(Personuuidlist!=null&&Personuuidlist.size()>0){   //为一个需要记录阅读记录的邮件
				Iterator Itr = Personuuidlist.iterator();
				while(Itr.hasNext()){
					FiletransferReadrecordVO fvo = (FiletransferReadrecordVO)Itr.next();
					EntityManager entitymanger = EntityManager.getInstance();
					personList.add(entitymanger.findPersonByUuid(fvo.getReadpersonuuid()).getFullName());
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		return personList;
	}
	
	/**
	 * 依RECORDMAILID查出所有阅读过此邮件的人的中文名称
	 * @return
	 * @throws 
	 */
	public String getReadPersonName1(Integer recordMailid){
		List Personuuidlist = new ArrayList();
//		List personList = new ArrayList();
		StringBuffer namebuf = new StringBuffer();
		FiletransferReadrecordDAO fdao = new FiletransferReadrecordDAO();
		fdao.setMailid(recordMailid);
		fdao.setIsread(FileTransferConfig.READ);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(fdao);
		try {
			Personuuidlist = factory.find(new FiletransferReadrecordVO());
			if(Personuuidlist!=null&&Personuuidlist.size()>0){   //为一个需要记录阅读记录的邮件
				Iterator Itr = Personuuidlist.iterator();
				int i = 0 ;
				while(Itr.hasNext()){
					FiletransferReadrecordVO fvo = (FiletransferReadrecordVO)Itr.next();
					EntityManager entitymanger = EntityManager.getInstance();
					String CName = entitymanger.findPersonByUuid(fvo.getReadpersonuuid()).getFullName();
//					personList.add(entitymanger.findPersonByUuid(fvo.getReadpersonuuid()).getFullName());
					if(i!=0) namebuf.append(",");
					namebuf.append(CName);
					i++;
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		return namebuf.toString();
	}
	
	/**
	 * 依RECORDMAILID查出被保存的文件的接收人（中文名）
	 * @return
	 * @throws 
	 */
	public String getRecePerson(Integer recordMailid){
		StringBuffer namebuf = new StringBuffer();
		FiletransferReadrecordDAO fdao = new FiletransferReadrecordDAO();
		fdao.setMailid(recordMailid);
		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(fdao);
		try {
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(conn);
			List recordlist = factory.find(new FiletransferReadrecordVO());
			int i = 0 ;
			if(recordlist!=null&&recordlist.size()>0){   //为一个需要记录阅读记录的邮件
				Iterator Itr = recordlist.iterator();
				while(Itr.hasNext()){
					FiletransferReadrecordVO fvo = (FiletransferReadrecordVO)Itr.next();
					EntityManager entitymanger;
					String CName = null;
					try {
						entitymanger = EntityManager.getInstance();
						CName = entitymanger.findPersonByUuid(fvo.getReadpersonuuid()).getFullName();
						if(i!=0) namebuf.append(",");
						namebuf.append(CName);
						i++;
					} catch (EntityException e1) {
						e1.printStackTrace();
					}
					
				    
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return namebuf.toString();
	}
	
	/**
	 * 加入到待办
	 * @return
	 * @throws 
	 */	
	public void addToIntend(String path,String username,String topic,String tointendId){
		//判断是否要加入待办事宜
		IntendWork addIntendHandler = new IntendWork(this.conn);
		String source= new String();
		String url= new String();
		String navigate= new String();
		String personid = new String();
		source = "文件传递";
//		url = path+"/servlet/MessageListServlet?type=system&folder=Inbox&search=no";
		url = path+"/servlet/ShowNoReadServlet";
		navigate = path+"/servlet/LeftListServlet";
		//得到通用的userid
		FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
		String commUserid = ftsHandler.getUid(username);
		personid = commUserid;
		String type = IntendWorkConfig.IMPORT_LOCAL;
	
		if(personid!=null&&!personid.equals("")){
			addIntendHandler.addWork(topic,source,url,type,navigate,personid,IntendWork.getCodeValue("oa_filetrans"),tointendId);
		}
		
	}
	
	/**
	 * 删除待办
	 * @return
	 * @throws 
	 */	
	public void delIntend(String code,String mailhead,String userid){
		MessageHandler mhandler = new MessageHandler();
		IntendWork intendHandler = new IntendWork(this.conn);
		String mailfrom = "";
		String subject = "";
		try {
			mailfrom = mhandler.getFrom(mailhead);
			int findex1 = mailfrom.indexOf("<");
			int findex2 = mailfrom.indexOf("@");
			mailfrom = mailfrom.substring(findex1+1,findex2);
			subject = mhandler.getIntendSubject(mailhead);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		String intendId = mailfrom + "," + subject + "," + userid;
		try {
			intendHandler.deleteWork(code,intendId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/**
		 *转换成ISO-8859-1编码格式
		 * @param  
		 * @exception 
		 */	
		 public String GBtoISO(String selectValue) {
			 String changeValue = "";
			 if (selectValue != null) {
				 try {
					  changeValue = new String(selectValue.getBytes("GB2312"), "ISO-8859-1");
				 } catch (UnsupportedEncodingException ex) {
					  ex.printStackTrace();
				 }
			 }else {
				 changeValue = "";
			 }
			 return  changeValue;
		  }
	      
	/** 把ISO-8859-1码转换成GB2312 
	*/ 
		 public static String ISOtoGB(String iso){ 
			 String gb; 
			 try{ 
				 if(iso.equals("") || iso == null){ 
					 return ""; 
				 }else{ 
					 iso = iso.trim(); 
					 gb = new String(iso.getBytes("ISO-8859-1"),"GB2312"); 
					 return gb; 
				 } 
			 }catch(Exception e){ 
				 System.err.print("编码转换错误："+e.getMessage()); 
				 return ""; 
			 } 
		 } 
		 
		   public void delFile(String str){
			File f =null;
			f=new File(str);
			if(f.exists()){
				  f.delete();	
				}
			}
		 
		  public void zipFile(String textName,List list,String filepath) throws Exception{

			File file=new File(filepath+textName+".zip");
			 if(!file.exists()){
				  file.createNewFile();
			   }
			 
			FileOutputStream f = new FileOutputStream(file) ; 
			CheckedOutputStream csum = new CheckedOutputStream(f , new Adler32());
			//CZipOutputStream zos = new CZipOutputStream(csum,"GB2312");
			ZipOutputStream zos = new ZipOutputStream(csum);
			
			DataInputStream in  = new DataInputStream(new FileInputStream(filepath+textName+".txt"));
			if(in!=null){
				zos.putNextEntry(new ZipEntry(textName+".txt"));
				int c ;
				while((c=in.read())!=-1)
				zos.write(c) ;
				in.close() ;}
			this.delFile(filepath+textName+".txt");
			
			MailAccessoryVO vo = null;
			String name1= null;
			
			Iterator it = list.iterator();
			if(it!=null){
				while(it.hasNext()){
					InputStream in1 = null; 
					vo = (MailAccessoryVO)it.next();
					in1 = (InputStream)vo.getIn();
					name1 = vo.getFilename();
					
					if(in1!=null){
						zos.putNextEntry(new ZipEntry(name1));
						int c3;
						while((c3=in1.read())!=-1)
							zos.write(c3) ;
							in1.close();}
				 }
				}
			zos.close();
		  }
		  
		  public void zipAllFile(Map map,String path) throws IOException{
		  	
			File file=new File(path+"download.zip");
			 if(!file.exists()){
				  file.createNewFile();
			   }
			 
			Map map1 = new HashMap();   
			map1 = new TreeMap();
			
			map1 = (TreeMap)map;
			 
			FileOutputStream f = new FileOutputStream(file) ; 
			CheckedOutputStream csum = new CheckedOutputStream(f , new Adler32());
			//CZipOutputStream zos = new CZipOutputStream(csum,"GB2312");
			ZipOutputStream zos = new ZipOutputStream(csum);
			
			Iterator it = map1.values().iterator();
			
			while (it.hasNext()) {
				// Get value
				String  value = (String)it.next();
				DataInputStream in  = new DataInputStream(new FileInputStream(path+value+".zip"));
		        
				if(in!=null){
					zos.putNextEntry(new ZipEntry(value+".zip"));
					int c ;
					while((c=in.read())!=-1)
					zos.write(c) ;
					in.close() ;}
		        
				//this.delFile(filepath+textName+".txt");
			}

			zos.close();
			}
		  	
	/**
	 * 当前用户附件存储路径
	 * @param path
	 * @param privateDir
	 * @return
	 */
	public String getfilepath(StringBuffer path,String privateDir) {
		String temppath = path.toString();
		temppath = temppath.concat(privateDir).concat(System.getProperty("file.separator"));
		File file = new File(temppath);
		file.mkdirs();
		return temppath;
	}

	/**
	 * @param request
	 * @param senddomain
	 * @return
	 */
	public List getSendtoAddressForhr(HttpServletRequest request, String domain) {
		
		//可以发送的用户地址的集合 add by yangyang 20050708
		List legalAddressList = new ArrayList();		
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession();
		String reString = "";
		String illegeReString = "";
		AddressHelp helper = new AddressHelp(this.conn);
		List personList = null;
		
		//0308为了是党员先进性的发文特此处理，缺省为机关党委
		SelectOrgpersonVO vo = new SelectOrgpersonVO();
		vo.setUserid("suchengfang");
		vo.setUseruuid("3f40a06e-10241e61553-9849ddd9cda567c722b51e4369c3577a");
		vo.setName("苏成芳");
		vo.setIsperson("1");
		
		/*vo.setUserid("dev");
		vo.setUseruuid("7deef758-fdb75f4693-8581bd2af19e660037d144f55ca4fb1e");
		vo.setName("开发测试账号");
		vo.setIsperson("1");*/
		
		personList = new ArrayList();
		personList.add(vo);
		/////////////////////////////////////////
		
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		Iterator personIterator = personList.iterator();
		while (personIterator.hasNext()) {
			SelectOrgpersonVO selectOrgpersonVO =
				(SelectOrgpersonVO) personIterator.next();
			//依邮箱设置表，得到准确的邮箱用户名，用来构造邮箱地址
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				String userid =  ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
				sendto.append(userid);
				sendto.append("@");
				sendto.append(domain);
				sendto.append(",");
				
				//add by yangyang 20050708
				String currAddress = userid + "@" + domain;
				legalAddressList.add(currAddress);				
				
			}catch (Exception e){
				String CName = selectOrgpersonVO.getName();
				illegeAddress.append(CName);
				illegeAddress.append(",");
			}
		}
		illegeReString = illegeAddress.toString();
		reString = sendto.toString();

		Addresslist.add(0,illegeReString);
		Addresslist.add(1,reString);
		//可以发送的地址名称的集合，集合的每个元素是String类型的对象 add by yangyang 20050708
		//例如：
		//legalAddressList.get(0) = "user1@domain"
		//legalAddressList.get(1) = "user2@domain"
		//legalAddressList.get(2) = "user3@domain"
		Addresslist.add(2,legalAddressList);
		
		return Addresslist;
	}
  
	/**将StringTonkenzier转换成为List类型得数据
	 * @param str
	 * @return
	 */
	public List ChangeStringTokenizerToList(String str) {
		List list = new ArrayList();
		
		StringTokenizer toList = new StringTokenizer(
				str.toString().concat(","), ",");
		int tokenLength = toList.countTokens();
		for (int x = 0; x < tokenLength; x++) {
			SelectOrgpersonVO vo = new SelectOrgpersonVO();
			vo.setUseruuid(toList.nextToken());
			vo.setIsperson("1");
			list.add(vo);
		}
		return list;
	}
	
	
	/**
	 * 	查询用户的路径信息
	 */
	public UserPath QueryMailPath1(String userid) {
	
		Statement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM USER_PATH WHERE USERID = '" + userid+"'";

		UserPath userpathVO = new UserPath();

		try {
		
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
	
			if (rs != null) {
		
			   if (rs.next()) {
				 userpathVO.setUserid(rs.getString("USERID"));
				 userpathVO.setUser_path(rs.getString("PATH"));
			   }
		   }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		

		}

		return userpathVO;

	}
	
	/**插入用户的路径信息
	 * @param userid
	 * @param path
	 */
	public void InsertMailPath1(String userid, String path) {
	
		Statement stmt = null;	

		String sql =
			"INSERT INTO USER_PATH VALUES ('" + userid + "','" + path + "')";

		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}
	}
	
	
}
