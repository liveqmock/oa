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
	 * ���ʹ������ļ�
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
		//�����ʼ���
		BodyPart contentBodyPart = new MimeBodyPart();
		//�������
		contentBodyPart.setText(content);

		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(contentBodyPart);

		//��Ӹ���
		for (int index = 0; index < sendFileBean.filenumber(); index++) {
			AttachFileBean attachFileBean = sendFileBean.getAttachFile(index);
			//����������
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
	 * �õ����и����ļ����ļ�
	 * @param content
	 * @param msg
	 * @param sendFileBean
	 * @throws MessagingException
	 */
	public String[] getAttachNames(SendFileBean sendFileBean){
			String[] attachFileNames = new String[sendFileBean.filenumber()];
			//��Ӹ���
			for (int index = 0; index < sendFileBean.filenumber(); index++) {
				AttachFileBean attachFileBean = sendFileBean.getAttachFile(index); 
				String downloadPath = attachFileBean.getUploadUrl();
				System.out.println("the downloadPath is........"+downloadPath);
				attachFileNames[index] = downloadPath;
			}
			return attachFileNames;
	}

	/**
	 * �ж�һ���ʼ���ַ�Ƿ�Ϸ�
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
	 * �ж϶���ʼ���ַ�Ĵ��еĵ�ַ�Ƿ�Ϸ�
	 * @return ���ڵĵ�ַ�Ͳ�û�������ַ���˵�userid
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
	 * �õ��ռ��˵�ַ��ͨ��SESSION��
	 * @param request
	 * @param domain
	 * @return ���ڵĵ�ַ�Ͳ�û�������ַ���˵�userid
	 * @throws Exception
	 */
	public List getSendtoAddressForSendFile(HttpServletRequest request, String domain) throws HandlerException {
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession(true);
		
		//���Է��͵��û���ַ�ļ���
		List reStringList = new ArrayList();
		
		String reString = "";
		String illegeReString = "";
		
		AddressHelp helper = new AddressHelp(this.conn);
		
		List orgpersonlist = (List) session.getAttribute("sendtoperson");
		if(orgpersonlist == null || orgpersonlist.size() ==0 ){
			throw new HandlerException("��ַΪ�գ��Ự���ܳ�ʱ��������������ʽ������ȡ��ַ��");
		}
		
		//�ӻỰ��ȡ��ѡ���Ҫ���͵��û����ϣ���SelectOrgpersonVO����ļ���
//		System.out.println("�õ�personList��ʼ��");
		List personList = helper.getperson((List) session.getAttribute("sendtoperson"),request,"sendtoperson");
//		System.out.println("kkkkkkkkkkkkkkkkkkkkk  personList "+personList.size()); 
//		System.out.println("�õ�personList������");
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		
		Iterator personIterator = personList.iterator();
		
		while (personIterator.hasNext()) {
			
			SelectOrgpersonVO selectOrgpersonVO = (SelectOrgpersonVO) personIterator.next();
			
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				//����һ��EMAIL��ַ������userid@domain
				String userid =  ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
				sendto.append(userid).append("@").append(domain).append(",");
				
			}catch (Exception e){
				String CName = selectOrgpersonVO.getName();
				illegeAddress.append(CName);
				illegeAddress.append(",");
			}
			
		}
		//���Ϸ��ĵ�ַ��Ӧ���û�����
		illegeReString = illegeAddress.toString();
		//���Է��͵�
		reString = sendto.toString();
		
		//���Ϸ���ַ��Ӧ���û������ļ���
		Addresslist.add(0,illegeReString);
		//ȫ�����Է��͵ĵ�ַ���ƣ����磺userid@domain,userid@domain,userid@domain
		Addresslist.add(1,reString);
		
		return Addresslist;
	}	
	
	/**
	 * ���������ַ����õ�list
	 * @return
	 */
	public List getSendtoAddress0830(String s1,String s2,String s3,String domain){
	    
		List legalAddressList = new ArrayList();
		List Addresslist = new ArrayList();
		StringBuffer legalString = new StringBuffer(); 
		StringBuffer illegalString = new StringBuffer();
	    
		//�ֱ�õ����ˣ����ˣ�����������ַ���
		StringTokenizer addresslist = new StringTokenizer(s1,","); 
		System.out.println("s1    ="+s1);
		StringTokenizer addresslist2 = new StringTokenizer(s2,",");
		System.out.println("s2    ="+s2);
		StringTokenizer addresslist3 = new StringTokenizer(s3,",");
		System.out.println("s3    ="+s3);
	    
		FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);

		//����ȡ�÷����handler
		Group group = new Group(conn);
	    
		//��ȡ�õĸ�����Ϣ���н���
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
	    
	    
		//��ȡ�ø��˷�����Ϣ���н���
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
	    
		//��ȡ�ù����������Ϣ���н���
	    
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
	    
			  //���Ϸ���ַ��Ӧ���û������ļ���
			  Addresslist.add(0,"");
			  //ȫ�����Է��͵ĵ�ַ���ƣ����磺userid@domain,userid@domain,userid@domain
			  Addresslist.add(1,legalString);
		
			  //���Է��͵ĵ�ַ���Ƶļ��ϣ����ϵ�ÿ��Ԫ����String���͵Ķ��� add by yangyang 20050708
			  //���磺
			  //legalAddressList.get(0) = "user1@domain"
			  //legalAddressList.get(1) = "user2@domain"
			  //legalAddressList.get(2) = "user3@domain"
			  Addresslist.add(2,legalAddressList);
	    
		return Addresslist;
	    
	}
	
	/**
	 * �õ��ռ��˵�ַ��ͨ��SESSION��
	 * @param request
	 * @param domain
	 * @return ���ڵĵ�ַ�Ͳ�û�������ַ���˵�userid
	 * @throws Exception
	 */
	public List getSendtoAddress(HttpServletRequest request, String domain) throws HandlerException {

		//���Է��͵��û���ַ�ļ��� add by yangyang 20050708
		List legalAddressList = new ArrayList();
			
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession(true);
	
		String reString = "";
		String illegeReString = "";
		
		AddressHelp helper = new AddressHelp(this.conn);
		
		List orgpersonlist = (List) session.getAttribute("sendtoperson");
		if(orgpersonlist == null || orgpersonlist.size() ==0 ){
			throw new HandlerException("��ַΪ�գ��Ự���ܳ�ʱ��������������ʽ������ȡ��ַ��");
		}
		
		//�ӻỰ��ȡ��ѡ���Ҫ���͵��û����ϣ���SelectOrgpersonVO����ļ���
//		System.out.println("�õ�personList��ʼ��");
		List personList = helper.getperson((List) session.getAttribute("sendtoperson"),request,"sendtoperson");
//		System.out.println("kkkkkkkkkkkkkkkkkkkkk  personList "+personList.size()); 
//		System.out.println("�õ�personList������");
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		
		Iterator personIterator = personList.iterator();
		
		while (personIterator.hasNext()) {
			
			SelectOrgpersonVO selectOrgpersonVO = (SelectOrgpersonVO) personIterator.next();
			
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				//����һ��EMAIL��ַ������userid@domain
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
		//���Ϸ��ĵ�ַ��Ӧ���û�����
		illegeReString = illegeAddress.toString();
		//���Է��͵�
		reString = sendto.toString();
		
		//���Ϸ���ַ��Ӧ���û������ļ���
		Addresslist.add(0,illegeReString);
		//ȫ�����Է��͵ĵ�ַ���ƣ����磺userid@domain,userid@domain,userid@domain
		Addresslist.add(1,reString);
		
		//���Է��͵ĵ�ַ���Ƶļ��ϣ����ϵ�ÿ��Ԫ����String���͵Ķ��� add by yangyang 20050708
		//���磺
		//legalAddressList.get(0) = "user1@domain"
		//legalAddressList.get(1) = "user2@domain"
		//legalAddressList.get(2) = "user3@domain"
		Addresslist.add(2,legalAddressList);

		return Addresslist;
	}
	
	/** �õ��ռ��˵�ַ��ͨ����������ʽ��
	 * @param request
	 * @param domain
	 * @return
	 * @throws HandlerException
	 */
	public List getSendtoAddressByHidden(HttpServletRequest request, String domain,String str) throws HandlerException {
		
//		���Է��͵��û���ַ�ļ��� add by sct 20050713
		List legalAddressList = new ArrayList();
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession(true);
		String reString = "";
		String illegeReString = "";
		
		AddressHelp helper = new AddressHelp(this.conn);
		
		//�ӻỰ��ȡ��ѡ���Ҫ���͵��û����ϣ���SelectOrgpersonVO����ļ���
//		List personList = helper.getperson((List) session.getAttribute("sendtoperson"),request,"sendtoperson");
		List personList = helper.getperson(this.ChangeStringTokenizerToList(str),request,"sendtoperson");
		StringBuffer sendto = new StringBuffer();
		StringBuffer illegeAddress = new StringBuffer();
		
		Iterator personIterator = personList.iterator();
		
		while (personIterator.hasNext()) {
			
			SelectOrgpersonVO selectOrgpersonVO = (SelectOrgpersonVO) personIterator.next();
			
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				//����һ��EMAIL��ַ������userid@domain
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
		//���Ϸ��ĵ�ַ��Ӧ���û�����
		illegeReString = illegeAddress.toString();
		//���Է��͵�
		reString = sendto.toString();
		
		//���Ϸ���ַ��Ӧ���û������ļ���
		Addresslist.add(illegeReString);
		//ȫ�����Է��͵ĵ�ַ���ƣ����磺userid@domain,userid@domain,userid@domain
		Addresslist.add(reString);
		
//		���Է��͵ĵ�ַ���Ƶļ��ϣ����ϵ�ÿ��Ԫ����String���͵Ķ��� add by yangyang 20050708
		//���磺
		//legalAddressList.get(0) = "user1@domain"
		//legalAddressList.get(1) = "user2@domain"
		//legalAddressList.get(2) = "user3@domain"
		Addresslist.add(2,legalAddressList);
		
		return Addresslist;
	}
	
	/**
	 * �õ��ռ��˵�ַ(���ص�ί���ʼ���ַ)��ͨ����List��ֵ�õ���
	 * @param request
	 * @param domain
	 * @return ���ڵĵ�ַ�Ͳ�û�������ַ���˵�userid
	 * @throws Exception
	 */
	public List getSendtoAddressForjiguandangwei(HttpServletRequest request, String domain) {
		
		//���Է��͵��û���ַ�ļ��� add by yangyang 20050708
		List legalAddressList = new ArrayList();		
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession();
		String reString = "";
		String illegeReString = "";
		AddressHelp helper = new AddressHelp(this.conn);
		List personList = null;
		
		//0308Ϊ���ǵ�Ա�Ƚ��Եķ����ش˴���ȱʡΪ���ص�ί
		SelectOrgpersonVO vo = new SelectOrgpersonVO();
		vo.setUserid("jiguandangwei");
		vo.setUseruuid("5b99f307-10257ea8062-e600d7d60b5229058fb97f42553082d6");
		vo.setName("���ص�ί");
		vo.setIsperson("1");
		
		/*vo.setUserid("dev");
		vo.setUseruuid("7deef758-fdb75f4693-8581bd2af19e660037d144f55ca4fb1e");
		vo.setName("���������˺�");
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
			//���������ñ��õ�׼ȷ�������û������������������ַ
			FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
			try{
				String userid =  ftsHandler.getUserid(selectOrgpersonVO.getUseruuid());
				sendto.append(userid);
				sendto.append("@");
				sendto.append(domain);
				sendto.append(",");
				
				//�ѺϷ��û��ʼ���ַ�����Ƽ��뵽�Ϸ���ַ�ļ����� add by yangyang 20050708
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
		//���Է��͵ĵ�ַ���Ƶļ��ϣ����ϵ�ÿ��Ԫ����String���͵Ķ��� add by yangyang 20050708
		//���磺
		//legalAddressList.get(0) = "user1@domain"
		//legalAddressList.get(1) = "user2@domain"
		//legalAddressList.get(2) = "user3@domain"
		Addresslist.add(2,legalAddressList);		
		
		return Addresslist;
	}
	
	/**
	 * �õ����͵�ַ��ͨ��SESSION��
	 * @param request
	 * @param domain
	 * @return ���ڵĵ�ַ�Ͳ�û�������ַ���˵�userid
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
			throw new HandlerException("��ַΪ�գ��Ự���ܳ�ʱ��������������ʽ������ȡ��ַ��");
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
				//���������ñ��õ�׼ȷ�������û������������������ַ
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
	 * �õ����͵�ַ��ͨ����������
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
				//���������ñ��õ�׼ȷ�������û������������������ַ
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
	 * �õ����͵�ַ��ͨ��SESSION��
	 * @param request
	 * @param domain
	 * @return ���ڵĵ�ַ�Ͳ�û�������ַ���˵�������
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
			throw new HandlerException("��ַΪ�գ��Ự���ܳ�ʱ��������������ʽ������ȡ��ַ��");
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
				//���������ñ��õ�׼ȷ�������û������������������ַ
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
	 * �õ����͵�ַ��ͨ����������
	 * @param request
	 * @param domain
	 * @return ���ڵĵ�ַ�Ͳ�û�������ַ���˵�������
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
				//���������ñ��õ�׼ȷ�������û������������������ַ
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
	 * �������ʼ��������ʼ�����
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
		//���part
		Multipart multipart = (Multipart) fileMessage.getContent();
		String content = "";
		ArrayList attachList = new ArrayList();
		//����ÿ������
		for (int i = 0; i < multipart.getCount(); i++) {
//			System.out.println("begin to get part.......");
			Part part = multipart.getBodyPart(i);
//			String s = part.getDisposition();
			if (!isAttachPart(part)) { //���Ĳ���
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
	 * �������ʼ�������SendFileBean
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
		


		//����·���޸� 2005-12-28
		//ԭ�г���
		//StringBuffer downloaddir = new StringBuffer(context.getRealPath("/filetransfer/downloadfile/"));
		//�޸ĳ���
		StringBuffer downloaddir = new StringBuffer(FiletransferUtil.getDownloadFilePath());




		String downloadfilepath = this.getfilepath(downloaddir,privateDir.toString());
		String content = "";
		if (!fileMessage.isMimeType("text/plain")) { //�洢����
			//�������ʼ�
			content = this.processAttachPart(
				fileMessage,
				downloadfilepath,
				request,
				privateDir.toString());
		}else{
			content = (String)fileMessage.getContent();
		}
		sendFileBean.setContent(content);

		//ȡ��request�����Ϣ���Ѹ���ת�Ƶ����ص�Ŀ¼�У�����·��������һ�ݵ�session�У�����ʱʹ��
		ArrayList fileList = (ArrayList) request.getAttribute("attachList");
		if(fileList != null)
		for (int i = 0; i < fileList.size(); i++) {  //�洢�����͸�����·��
			//���ڷ��͵�·��
			

			
			//�ϴ�·���޸� 2005-12-28
			//ԭ�г���
			//StringBuffer uploaddir = new StringBuffer(context.getRealPath("/filetransfer/uploadfile/"));
			//�޸ĳ���
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
	 * only�����ʼ�����
	 * @param fileMessage
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	public String getMailContent(
		MimeMessage fileMessage)
		throws IOException, MessagingException {
		//���part
		Multipart multipart = (Multipart) fileMessage.getContent();
		String content = "";
		ArrayList attachList = new ArrayList();
		//����ÿ������
		for (int i = 0; i < multipart.getCount(); i++) {
			Part part = multipart.getBodyPart(i);
			String s = part.getDisposition();
			if (!isAttachPart(part)) { //���Ĳ���
				content = (String) part.getContent();
			} 
		}
		return content;
	}
	
	/**
	 * only�����ʼ�Part
	 * @param fileMessage
	 * @param filepath
	 * @return
	 * @throws IOException
	 * @throws MessagingException
	 */
	
	public List getAccessoryList(
		MimeMessage fileMessage)
		throws IOException, MessagingException {
		//���part
		Multipart multipart = (Multipart) fileMessage.getContent();
		String content = "";
		ArrayList attachList = new ArrayList();
		Part part = null;
		List list = new ArrayList();

		//����ÿ������
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
	 * �洢��������
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
	 * �洢������
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
	 * �ж��Ƿ�Ϊ��������
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
			return true;//�и��������޷�����
		}
	}
	/**
	 * �õ���Ŀ¼�¸������䡱��Ҫ��ʾ�Ĳ��ݣ�Ԫ��Ϊ:�ļ�����δ���ļ������ļ���ռ�ܿռ�
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
		
		//Ԫ��Ϊ:�ļ�����δ���ļ������ļ���ռ�ܿռ�
		String[] ReceContent = new String[3];  //�ռ���
		String[] SentContent = new String[3];  //������
		String[] DraftContent = new String[3]; //�ݸ���
		String[] JunkContent = new String[3];  //������
				
//		str[0].lengthΪ�ʼ��ĸ���       
		for(int i=0; i < str[0].length; i++){
			firstword = str[2][i].substring(0,1);
			long result = Long.parseLong(str[0][i]);    //�õ��ʼ���С
			if(FileTransferConfig.RECE_FLAG.equals(firstword)){  //�ռ���
				a1++;
				String nOro = str[2][i].substring(1,2);
				if(FileTransferConfig.NEW_FLAG.equals(nOro))
					a2++;
				a3 = a3+result;
			}else if(FileTransferConfig.SENT_FLAG.equals(firstword)){  //������
				b1++;
				String nOro = str[2][i].substring(1,2);
				if(FileTransferConfig.NEW_FLAG.equals(nOro))
					b2++;
				b3 = b3+result;
			}else if(FileTransferConfig.DRA_FLAG.equals(firstword)){   //�ݸ���
				c1++;
				String nOro = str[2][i].substring(1,2);
				if(FileTransferConfig.NEW_FLAG.equals(nOro))
					c2++;
				c3 = c3+result;
			}else if(FileTransferConfig.JUNK_FLAG.equals(firstword)){  //������
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
	 * �õ��û��Զ����ļ�����Ҫ��ʾ�Ĳ��ݣ�Ԫ��Ϊ:�ļ�����δ���ļ������ļ���ռ�ܿռ�
	 * ÿ��ֻ����һ��
	 * @return
	 * @throws MessagingException
	 */
	public List getUserFolder(String[][] str){
		List userList = new ArrayList();
		int m1=0,m2=0; long m3=0;
		//Ԫ��Ϊ:�ļ�����δ���ļ������ļ���ռ�ܿռ�
		String[] oneMail = new String[3];  //�ռ���
		//str[0].lengthΪ�ʼ��ĸ��� 
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
	 * �õ��ʼ���С���ַ�����ʾ
	 * @return
	 * @throws MessagingException
	 */
	public String getMailMemory(long number){
		double mailnum = 0;
		String mailMemory = "";  //����ҳ����ʾ
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
	 * ����ת��ΪList
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
	 * �ж�Ҫ�����ʼ��Ƿ���ڽ����������ʣ��ռ�
	 * @return  ���ؿɷ��͵��ʼ���ַ  ��  ���ɷ��͵Ľ�����ID
	 * @throws MessagingException
	 */
	public List confirmOverflow(String path,long memory,String address,String sendUserCName,String userid){
		
		//��־����
		LogFactory factory = new FileLogFactory("FileTransfor");
		//��־����
		Log log = factory.newInstance(this.getClass(),loghead);
		
		//���������Ϣ
		log.debug("confirmOverflow("+path+","+memory+","+address+","+sendUserCName+","+userid+")");
		
		//��������
		log.debug("confirmOverflow() step1 �������� begin");
		List newUser = new ArrayList();
		long sendMemory = memory;
		String sendAddress = address;
		//�ָ���յ�ַ
		StringTokenizer addresslist = new StringTokenizer(sendAddress,",");  
		StringBuffer canAddress = new StringBuffer();        //�ɷ��͵�
		StringBuffer cannotAddress = new StringBuffer();        //�ɷ��͵�
		log.debug("confirmOverflow() step1 �������� end");
		
		System.out.println("countTokens  "+addresslist.countTokens());
		
		log.debug("confirmOverflow() step2 whileѭ�� begin");
		int cnt = 0;
		while (addresslist.hasMoreTokens()) {

			List reList = new ArrayList();
			List userFolder = new ArrayList();
			List userFolderName = new ArrayList();
			
			String oneaddress = addresslist.nextToken();
			int index = oneaddress.indexOf("@");
			
			
			//edit by yangyang 20050701
			if(index<0){
				log.debug("while()ѭ���� ��"+cnt+"��ѭ���쳣! ����oneaddress���ʼ����͵�ַ��=" + oneaddress + "���ʼ����յ�ַ��δ����@���ţ�");
				continue;
			}
			
			//edit by yangyang 20050701
			String username = oneaddress.substring(0,index).trim();
			String domain = PropertyManager.getProperty("archivesdomain")==null?"δ��ȡ":PropertyManager.getProperty("archivesdomain");
			String ip = PropertyManager.getProperty("archivesip")==null?"δ��ȡ":PropertyManager.getProperty("archivesip");
			
			//edit by yangyang 20050701
			log.debug("while()ѭ���� ��"+cnt+"��ѭ�� oneaddress���ʼ����͵�ַ�� = " + oneaddress + " , index��@����λ�ã� = " + index + " , username���ʼ��û����� = " + username + " , domain�������ƣ� = " + domain + " , ip��IP��ַ�� = " + ip);
			
			
			dirmanage mailhandler = null;
			try{
				log.debug("new dirmanage() begin");
				mailhandler = new dirmanage(ip, username, domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				log.debug("new dirmanage() end");
				long totalmemo1 = mailhandler.getDirSize("");
				/*log.debug("mailhandler.fileheadList() begin");
				String[][] str = mailhandler.fileheadList("");
				log.debug("mailhandler.fileheadList() end");
				
				//�õ���jsp��Ҫ��ʾ������
				log.debug("this.getShowContent("+str+") begin");
				reList = this.getShowContent(str);
				log.debug("this.getShowContent("+str+") end");
				
				log.debug("reList.get(0)~reList.get(4) begin");
				//Ԫ��Ϊ:��Ŀ¼���ļ�����δ���ļ������ļ���ռ�ܿռ�
				List ReceContent = (List)reList.get(0);  //�ռ���
				List SentContent = (List)reList.get(1);  //������
				List DraftContent = (List)reList.get(2); //�ݸ���
				List JunkContent = (List)reList.get(3);  //������
				log.debug("reList.get(0)~reList.get(4) end");
				
				
				//�õ��û��Զ�����ļ���
				log.debug("mailhandler.dirList() begin");
				String[] uFolder = mailhandler.dirList("");
				log.debug("mailhandler.dirList() end");
				
				//�õ��û������Զ����ļ����е��ʼ��б�(һ���ļ�����һ��LIST��һ�����һ����LIST��)
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
				
				
				//�õ������߶����ļ����������ʼ���С���ܺ�
				long userTotalMemory = 0;
				//�õ��û������Զ����ļ����е��ʼ��б�(һ���ļ�����һ��LIST��һ�����һ����LIST��)
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
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[0]");
					
					//��1����
					FiletransferSetVO ftsVO = this.getMailUserVO(username);
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����username = " + username);
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[1]");
					if(ftsVO==null){
						log.debug("while()ѭ���� ��"+cnt+"��ѭ����ftsVO==null");
					}
					
					//��2����
					//���ܷ���,�������
					if(ftsVO!=null && FileTransferConfig.TOINTEND.equals(ftsVO.getFsRule())){
						
						log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[2]");
						
						//�ж��Ƿ�Ҫ�����������
						String topic= new String();
						long nowtime = System.currentTimeMillis();
						topic = "����������"+CommUtil.getTime(nowtime)+" "+sendUserCName+"���������ʼ����˻أ�";
						String tointendId = userid + "," + Long.toString(nowtime) + "," + username;
						
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") ���뵽���칤�� begin");
						this.addToIntend(path,username,topic,tointendId);
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") ���뵽���칤�� end");

						log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[3]");
					}
					
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[4]");
					
					//��3����
					//cannotAddress.append(username).append("@").append(domain).append(",");
					cannotAddress.append(username);
					cannotAddress.append("@");
					cannotAddress.append(domain);
					cannotAddress.append(",");
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[5]");
					
					
//					//���ܷ��ͣ�����ɾ�����ٷ���
//					if(FileTransferConfig.ROLLDELETE.equals(ftsVO.getFsRule())){
//						//��Inbox�е��ʼ���ʱ��Ӿɵ��½���ʱ���������
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
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory<=spareMemory[0]");
					
					canAddress.append(username);
					canAddress.append("@");
					canAddress.append(domain);
					canAddress.append(",");
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory<=spareMemory[1]");
					
				}
				
				
			} catch (DAOException e) {
				
				
								
				e.printStackTrace();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				e.printStackTrace();
			    
				//���쳣��¼����־��
				log.error(e.getMessage(),e);
				
			}finally{
				if(mailhandler!=null){
					try {
						mailhandler.disconnect();
					} catch (LdapException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						
						//���쳣��¼����־��
						log.error(e.getMessage(),e);
						
					}
				}
			    
				//��������1
				cnt++;
			}
		    
		} //while
		
		log.debug("confirmOverflow() step2 whileѭ�� end");
		
		
		log.debug("confirmOverflow() step3 ���췵�ؽ�� begin");		
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
		
		log.debug("confirmOverflow() step3 ���췵�ؽ�� end");
		
		return newUser;
	}
	
	/**
	 * �����ʼ�ʱ�ж�Ҫ�����ʼ��Ƿ���ڽ����������ʣ��ռ�
	 * 
	 * @param path
	 * @param memory
	 * @param addressList		�ռ��˵�ַ����
	 * @param sendUserCName		�����˵�����
	 * @param userid
	 * @return	���ؿɷ��͵��ʼ���ַ  ��  ���ɷ��͵Ľ�����ID
	 */
	public List confirmOverflowForSendFile1(String path,long memory,List addressList,String sendUserCName,String userid,String isSent){
		
		//��־����
		LogFactory factory = new FileLogFactory("FileTransfor");
		//��־����
		Log log = factory.newInstance(this.getClass(),loghead);
		
		String address = "";
		
		for (int i = 0; i < addressList.size(); i++) {
			String currAddress = (String)addressList.get(i);
			address += currAddress + ",";
		}
		if(address.indexOf(",")>0){
			address = address.substring(0,address.lastIndexOf(","));
		}
		
		//���������Ϣ
		log.debug("confirmOverflow("+path+","+memory+","+address+","+sendUserCName+","+userid+")");
		
		//��������
		log.debug("confirmOverflow() step1 �������� begin");
		List newUser = new ArrayList();
		long sendMemory = memory;
		StringBuffer canAddress = new StringBuffer();        //�ɷ��͵�
		StringBuffer cannotAddress = new StringBuffer();     //�ɷ��͵�
//		cannotAddress.
		
		long add_connection_time = 0 ;
		long add_comput_time = 0 ;

		
		log.debug("confirmOverflow() step1 �������� end");
		
		log.debug("confirmOverflow() step2 forѭ�� begin ѭ������="+addressList.size());
		int cnt = 0;
		long step11 = System.currentTimeMillis();
		
		String domain = PropertyManager.getProperty("archivesdomain")==null?"δ��ȡ":PropertyManager.getProperty("archivesdomain");
		String ip = PropertyManager.getProperty("archivesip")==null?"δ��ȡ":PropertyManager.getProperty("archivesip");

		usermanage user = new usermanage();
		dirmanage dir = null;
		try {
			user.connect(ip,389,"cn=root,o=redflag.com,c=CH","simple","redflag.com");
			dir=new dirmanage(ip);//����dirmanage������һ�����췽�����˷�������Ҫ����ldap�������û�Ŀ¼ ��
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
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����index=" + index );        
				if(index<0){
					log.debug("for()ѭ���� ��"+cnt+"��ѭ���쳣! ����oneaddress���ʼ����͵�ַ��=" + oneaddress + "���ʼ����յ�ַ��δ����@���ţ�");
					continue;  
				}
				
				//edit by yangyang 20050701
				String username = oneaddress.substring(0,index).trim();
				
				//edit by yangyang 20050701
				log.debug("for()ѭ���� ��"+cnt+"��ѭ�� oneaddress���ʼ����͵�ַ�� = " + oneaddress + " , index��@����λ�ã� = " + index + " , username���ʼ��û����� = " + username + " , domain�������ƣ� = " + domain + " , ip��IP��ַ�� = " + ip);

				log.debug("for()ѭ���� ��"+cnt+"��ѭ����new dirmanage() begin");
				long mailhandler_begin = System.currentTimeMillis();
				
				String path1[] = new String[1];
				
				path1[0] = QueryMailPath1(username).getUser_path();
				
				log.debug(username+" ��d·���� ��"+path1[0]);
				
				if(path1[0]==null||"".equals(path1[0])){
					
					String rootpath=user.getUserRootPath(username,domain);
					path1[0] = rootpath;
					log.debug(username+" ��L·���� ��"+rootpath);
					InsertMailPath1(username,rootpath);
					
				}
//				  path1[0] =  cache.getUserRootPath(username);
				
//				mailhandler = new dirmanage(ip, username, domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				log.debug("ʹ���µĽӿ�");
//				path1[0]=user.getUserRootPath(username,"xinhua.com");     //�õ��û��������ַ 

		        
				long mailhandler_end = System.currentTimeMillis();
				add_connection_time = add_connection_time +(mailhandler_end-mailhandler_begin);
				log.debug("--- step1 to step2 ��ʱ"+(mailhandler_end-mailhandler_begin)+"���룡Լ��"+(mailhandler_end-mailhandler_begin)/1000+"�룡 ------------------------------------------------- ");
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����new dirmanage() end");  
				
				long totalmemo1_begin = System.currentTimeMillis();
				
				long [] jj = dir.getDirSize(path1); //�õ������û�����Ĵ�С
				long totalmemo1 = jj[0]; //�õ������û�����Ĵ�С
//				long totalmemo1 = mailhandler.getDirSize("");   
//				long totalmemo1 = dir.getDirSize(path); //�õ������û�����Ĵ�С
				long spareMemory = this.getMailBoxMemory(username)*1024*1024-totalmemo1;

				
				long totalmemo1_end = System.currentTimeMillis();
				add_comput_time = add_comput_time +(totalmemo1_end-totalmemo1_begin);
				log.debug("--- step1 to step2 ��ʱ"+(totalmemo1_end-totalmemo1_begin)+"���룡Լ��"+(totalmemo1_end-totalmemo1_begin)/1000+"�룡 ------------------------------------------------- ");
				
				if((sendMemory>spareMemory)||((userid.equals(username)&&("checked2".equals(isSent)&&(sendMemory>2*spareMemory))))){
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[0]");
					
					//��1����
					FiletransferSetVO ftsVO = this.getMailUserVO(username);
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����username = " + username);
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[1]");
					if(ftsVO==null){
						log.debug("while()ѭ���� ��"+cnt+"��ѭ����ftsVO==null");
					}
					
					//��2����
					//���ܷ���,�������
					if(ftsVO!=null && FileTransferConfig.TOINTEND.equals(ftsVO.getFsRule())){
						
						log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[2]");
						
						//�ж��Ƿ�Ҫ�����������
						String topic= new String();
						long nowtime = System.currentTimeMillis();
						topic = "����������"+CommUtil.getTime(nowtime)+" "+sendUserCName+"���������ʼ����˻أ�";
						String tointendId = userid + "," + Long.toString(nowtime) + "," + username;
						
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") ���뵽���칤�� begin");
						this.addToIntend(path,username,topic,tointendId);
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") ���뵽���칤�� end");

						log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[3]");
					}
					
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[4]");
					
					//��3����
					//cannotAddress.append(username).append("@").append(domain).append(",");
					cannotAddress.append(username);
					cannotAddress.append("@");
					cannotAddress.append(domain);
					cannotAddress.append(",");
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[5]");
					
					
//					//���ܷ��ͣ�����ɾ�����ٷ���
//					if(FileTransferConfig.ROLLDELETE.equals(ftsVO.getFsRule())){
//						//��Inbox�е��ʼ���ʱ��Ӿɵ��½���ʱ���������
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
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory<=spareMemory[0]");
					
					canAddress.append(username);
					canAddress.append("@");
					canAddress.append(domain);
					canAddress.append(",");
					
					log.debug("while()ѭ���� ��"+cnt+"��ѭ����sendMemory<=spareMemory[1]");
					
				}
			
			}  catch (Exception e) {
				
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����Exception");
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����Exception " + e.getMessage());
				log.error(e.getMessage(),e);
				
				System.err.println(e.getMessage());
				e.printStackTrace();
			    
				
			}finally{
		    	
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����finally[0]");
		    	
			    
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����finally[4]");
			    
				//��������1
				cnt++;
			}
		    
		} //����forѭ��

		 
		 for (int i = 0; i < addressList.size(); i++) {
//			 System.out.println("map"+to[i]+list_result.get(to[i].substring(0,to[i].lastIndexOf("@"))));
	   }
		
		log.debug("confirmOverflow() step2 forѭ�� end");

		
		log.debug("confirmOverflow() step3 ���췵�ؽ�� begin");		
		
		log.debug("confirmOverflow() step3 ���췵�ؽ�� end");
		long step12 = System.currentTimeMillis();
		log.debug("--- step1 to step2 ��ʱ"+(step12-step11)+"���룡Լ��"+(step12-step11)/1000+"�룡 ------------------------------------------------- ");
		log.debug("--- ��������ʱ "+add_connection_time+"���룡Լ��"+add_connection_time/1000+"�룡 ------- ");
		log.debug("--- �ж�����ʱ "+add_comput_time+"���룡Լ��"+add_comput_time/1000+"�룡 ------- "); 
		
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
	 * �����ʼ�ʱ�ж�Ҫ�����ʼ��Ƿ���ڽ����������ʣ��ռ�
	 * 
	 * @param path
	 * @param memory
	 * @param addressList		�ռ��˵�ַ����
	 * @param sendUserCName		�����˵�����
	 * @param userid
	 * @return	���ؿɷ��͵��ʼ���ַ  ��  ���ɷ��͵Ľ�����ID
	 */
	public List confirmOverflowForSendFile(String path,long memory,List addressList,String sendUserCName,String userid){
		
		//��־����
		LogFactory factory = new FileLogFactory("FileTransfor");
		//��־����
		Log log = factory.newInstance(this.getClass(),loghead);
		
		String address = "";
		for (int i = 0; i < addressList.size(); i++) {
			String currAddress = (String)addressList.get(i);
			address += currAddress + ",";
		}
		if(address.indexOf(",")>0){
			address = address.substring(0,address.lastIndexOf(","));
		}
		
		//���������Ϣ
		log.debug("confirmOverflow("+path+","+memory+","+address+","+sendUserCName+","+userid+")");
		
		//��������
		log.debug("confirmOverflow() step1 �������� begin");
		List newUser = new ArrayList();
		long sendMemory = memory;
		StringBuffer canAddress = new StringBuffer();        //�ɷ��͵�
		StringBuffer cannotAddress = new StringBuffer();     //�ɷ��͵�
//		cannotAddress.
		
		long add_connection_time = 0 ;
		long add_comput_time = 0 ;
		
		//�ָ���յ�ַ
		//String sendAddress = address;		
		//StringTokenizer addresslist = new StringTokenizer(sendAddress,",");
		//System.out.println("countTokens  "+addresslist.countTokens());
		
		log.debug("confirmOverflow() step1 �������� end");
		
		log.debug("confirmOverflow() step2 forѭ�� begin ѭ������="+addressList.size());
		int cnt = 0;
		long step11 = System.currentTimeMillis();
		
		String domain = PropertyManager.getProperty("archivesdomain")==null?"δ��ȡ":PropertyManager.getProperty("archivesdomain");
		String ip = PropertyManager.getProperty("archivesip")==null?"δ��ȡ":PropertyManager.getProperty("archivesip");
		
		String path1[]=new  String[1];
		
		dirmanage mailhandler = null;
		
		usermanage user=new usermanage();
		dirmanage dir = null;//����dirmanage������һ�����췽�����˷�������Ҫ����ldap�������û�Ŀ¼ ��
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
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����index=" + index );        
				if(index<0){
					log.debug("for()ѭ���� ��"+cnt+"��ѭ���쳣! ����oneaddress���ʼ����͵�ַ��=" + oneaddress + "���ʼ����յ�ַ��δ����@���ţ�");
					continue;  
				}
				
				//edit by yangyang 20050701
				String username = oneaddress.substring(0,index).trim();
				
				//edit by yangyang 20050701
				log.debug("for()ѭ���� ��"+cnt+"��ѭ�� oneaddress���ʼ����͵�ַ�� = " + oneaddress + " , index��@����λ�ã� = " + index + " , username���ʼ��û����� = " + username + " , domain�������ƣ� = " + domain + " , ip��IP��ַ�� = " + ip);

				log.debug("for()ѭ���� ��"+cnt+"��ѭ����new dirmanage() begin");
				long mailhandler_begin = System.currentTimeMillis();
//				mailhandler = new dirmanage(ip, username, domain, ip, 389, "cn=root,o=redflag.com,c=ch", "simple", "redflag.com");
				log.debug("ʹ���µĽӿ�");
				path1[0]=user.getUserRootPath(username,"xinhua.com");     //�õ��û��������ַ 
				long mailhandler_end = System.currentTimeMillis();
				add_connection_time = add_connection_time +(mailhandler_end-mailhandler_begin);
				log.debug("--- step1 to step2 ��ʱ"+(mailhandler_end-mailhandler_begin)+"���룡Լ��"+(mailhandler_end-mailhandler_begin)/1000+"�룡 ------------------------------------------------- ");
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����new dirmanage() end");  
				
				long totalmemo1_begin = System.currentTimeMillis();
//				long totalmemo1 = mailhandler.getDirSize("");   
				long totalmemo1 = dir.getDirSize(path); //�õ������û�����Ĵ�С
				
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����mailhandler.fileheadList() begin");
//				String[][] str = mailhandler.fileheadList("");
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����mailhandler.fileheadList() end");
//				
//				//�õ���jsp��Ҫ��ʾ������
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����this.getShowContent("+str+") begin");
//				reList = this.getShowContent(str);
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����this.getShowContent("+str+") end");
//				
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����reList.get(0)~reList.get(3) begin");
//				//Ԫ��Ϊ:��Ŀ¼���ļ�����δ���ļ������ļ���ռ�ܿռ�
//				List ReceContent = (List)reList.get(0);  //�ռ���
//				List SentContent = (List)reList.get(1);  //������
//				List DraftContent = (List)reList.get(2); //�ݸ���
//				List JunkContent = (List)reList.get(3);  //������
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����reList.get(0)~reList.get(3) end");
//				
//				
//				//�õ��û��Զ�����ļ���
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����mailhandler.dirList() begin");
//				String[] uFolder = mailhandler.dirList("");
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����mailhandler.dirList() end");
//				
//				//�õ��û������Զ����ļ����е��ʼ��б�(һ���ļ�����һ��LIST��һ�����һ����LIST��)
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����mailhandler.fileheadList() begin");
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
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����mailhandler.fileheadList() end");
//				
//				
//				//�õ������߶����ļ����������ʼ���С���ܺ�
//				long userTotalMemory = 0;
//				//�õ��û������Զ����ļ����е��ʼ��б�(һ���ļ�����һ��LIST��һ�����һ����LIST��)
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
				
				
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����userTotalMemory = " + userTotalMemory);
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����totalmemo = " + totalmemo1);
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory = " + sendMemory);
//				log.debug("for()ѭ���� ��"+cnt+"��ѭ����spareMemory = " + spareMemory);
				
				long totalmemo1_end = System.currentTimeMillis();
				add_comput_time = add_comput_time +(totalmemo1_end-totalmemo1_begin);
				log.debug("--- step1 to step2 ��ʱ"+(totalmemo1_end-totalmemo1_begin)+"���룡Լ��"+(totalmemo1_end-totalmemo1_begin)/1000+"�룡 ------------------------------------------------- ");
				
				
				if(sendMemory>spareMemory){
					
					log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[0]");
					
					//��1����
					FiletransferSetVO ftsVO = this.getMailUserVO(username);
					
					log.debug("for()ѭ���� ��"+cnt+"��ѭ����username = " + username);
					
					log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[1]");
					if(ftsVO==null){
						log.debug("for()ѭ���� ��"+cnt+"��ѭ����ftsVO==null");
					}
					
					//��2����
					//���ܷ���,�������
					if(ftsVO!=null && FileTransferConfig.TOINTEND.equals(ftsVO.getFsRule())){
						
						log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[2]");
						
						//�ж��Ƿ�Ҫ�����������
						String topic= new String();
						long nowtime = System.currentTimeMillis();
						topic = "����������"+CommUtil.getTime(nowtime)+" "+sendUserCName+"���������ʼ����˻أ�";
						String tointendId = userid + "," + Long.toString(nowtime) + "," + username;
						
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") ���뵽���칤�� begin");
						this.addToIntend(path,username,topic,tointendId);
						log.debug("this.addToIntend("+path+","+username+","+topic+","+tointendId+") ���뵽���칤�� end");

						log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[3]");
					}
					
					
					log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[4]");
					
					//��3����
					//cannotAddress.append(username).append("@").append(domain).append(",");
					cannotAddress.append(username);
					cannotAddress.append("@");
					cannotAddress.append(domain);
					cannotAddress.append(",");
					
					log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory>spareMemory[5]");
					
					
//					//���ܷ��ͣ�����ɾ�����ٷ���
//					if(FileTransferConfig.ROLLDELETE.equals(ftsVO.getFsRule())){
//						//��Inbox�е��ʼ���ʱ��Ӿɵ��½���ʱ���������
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
					
					log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory<=spareMemory[0]");
					
					canAddress.append(username);
					canAddress.append("@");
					canAddress.append(domain);
					canAddress.append(",");
					
					log.debug("for()ѭ���� ��"+cnt+"��ѭ����sendMemory<=spareMemory[1]");
					
				}
				
				
			} catch (DAOException e) {
				
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����DAOException");
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����DAOException " + e.getMessage());
				log.error(e.getMessage(),e);
				
				System.err.println(e.getMessage());
				e.printStackTrace();

			} catch (Exception e) {
				
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����Exception");
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����Exception " + e.getMessage());
				log.error(e.getMessage(),e);
				
				System.err.println(e.getMessage());
				e.printStackTrace();
			    
				
			}finally{
		    	
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����finally[0]");
		    	
				if(mailhandler!=null){
					try {
						
						log.debug("for()ѭ���� ��"+cnt+"��ѭ����finally[1]");
						
						mailhandler.disconnect();
						
						log.debug("for()ѭ���� ��"+cnt+"��ѭ����finally[2]");
						
					} catch (LdapException e) {
						
						log.debug("for()ѭ���� ��"+cnt+"��ѭ����finally[3]");
						log.debug("for()ѭ���� ��"+cnt+"��ѭ����LdapException" + e.getMessage());
						log.error(e.getMessage(),e);
						
						System.err.println(e.getMessage());
						e.printStackTrace();

					}
				}
			    
				log.debug("for()ѭ���� ��"+cnt+"��ѭ����finally[4]");
			    
				//��������1
				cnt++;
			}
		    
		} //����forѭ��
		
		log.debug("confirmOverflow() step2 forѭ�� end");

		
		log.debug("confirmOverflow() step3 ���췵�ؽ�� begin");		
		
		log.debug("confirmOverflow() step3 ���췵�ؽ�� end");
		long step12 = System.currentTimeMillis();
		log.debug("--- step1 to step2 ��ʱ"+(step12-step11)+"���룡Լ��"+(step12-step11)/1000+"�룡 ------------------------------------------------- ");
		log.debug("--- ��������ʱ "+add_connection_time+"���룡Լ��"+add_connection_time/1000+"�룡 ------- ");
		log.debug("--- �ж�����ʱ "+add_comput_time+"���룡Լ��"+add_comput_time/1000+"�룡 ------- "); 
		
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
	 * ���ʼ��������ʼ�Ŀ¼����ʱ����µ��ɵĽ���ʱ���������
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
				//һ��ѭ��������һ���ʼ���¼
				int listLength = newlist.size(); 
				for(int k=0;k<listLength;k++){
					//Ϊ�ҵ�һ��ʱ��С��Ҫ�����������¼��ʱ��
					List temp = (ArrayList)newlist.get(k);
					String compHead = temp.get(2).toString();
					long compTime = 0;
					if(mHandler.getReceiveDate(compHead)!=null)
						compTime = mHandler.getReceiveDate(compHead).getTime();
					
					if(retime>compTime){
						//���¼�¼д��һ��LIST
						List tempList1 = new ArrayList();
						List tempList2 = new ArrayList();
						for(int j=0;j<4;j++){
							tempList1.add(str[j][i]);
						}
						//����������
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
							//С�����һ����ʱ�䣬ֱ�Ӽ���ĩβ
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
	 * ���ʼ��������ʼ�Ŀ¼����ʱ��Ӿɵ��£����򣩵Ľ���ʱ���������
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
				//һ��ѭ��������һ���ʼ���¼
				int listLength = newlist.size(); 
				for(int k=0;k<listLength;k++){
					//Ϊ�ҵ�һ��ʱ�����Ҫ�����������¼��ʱ��
					List temp = (ArrayList)newlist.get(k);
					String compHead = temp.get(2).toString();
					long compTime = 0;
					if(mHandler.getReceiveDate(compHead)!=null)
						compTime = mHandler.getReceiveDate(compHead).getTime();
					
					if(retime<compTime){
						//���¼�¼д��һ��LIST
						List tempList1 = new ArrayList();
						List tempList2 = new ArrayList();
						for(int j=0;j<4;j++){
							tempList1.add(onemail.get(j));
						}
						//����������
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
							//�������һ����ʱ�䣬ֱ�Ӽ���ĩβ
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
	 * ���ʼ��������ʼ�Ŀ¼����ʱ����µ��ɣ����򣩵Ľ���ʱ���������
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
				//һ��ѭ��������һ���ʼ���¼
				int listLength = newlist.size(); 
				for(int k=0;k<listLength;k++){
					//Ϊ�ҵ�һ��ʱ��С��Ҫ�����������¼��ʱ��
					List temp = (ArrayList)newlist.get(k);
					String compHead = temp.get(2).toString();
					long compTime = 0;
					if(mHandler.getReceiveDate(compHead)!=null)
						compTime = mHandler.getReceiveDate(compHead).getTime();
					
					if(retime>compTime){
						//���¼�¼д��һ��LIST
						List tempList1 = new ArrayList();
						List tempList2 = new ArrayList();
						for(int j=0;j<4;j++){
							tempList1.add(onemail.get(j));
						}
						//����������
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
							//С�����һ����ʱ�䣬ֱ�Ӽ���ĩβ
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
	 * ���ʼ���ֻ������һ���ļ��У���ʱ����µ��ɣ����򣩵Ľ���ʱ���������
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
				//һ��ѭ��������һ���ʼ���¼
				int listLength = newlist.size(); 
				for(int k=0;k<listLength;k++){
					//Ϊ�ҵ�һ��ʱ��С��Ҫ�����������¼��ʱ��
					List temp = (ArrayList)newlist.get(k);
					String compHead = temp.get(1).toString();
					long compTime = 0;
					if(mHandler.getReceiveDate(compHead)!=null)
						compTime = mHandler.getReceiveDate(compHead).getTime();
					
					if(retime>compTime){
						//���¼�¼д��һ��LIST
						List tempList1 = new ArrayList();
						List tempList2 = new ArrayList();
						for(int j=0;j<3;j++){
							tempList1.add(onemail.get(j));
						}
						//����������
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
							//С�����һ����ʱ�䣬ֱ�Ӽ���ĩβ
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
	 * �õ�һ���û�����������VO
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
	 * �õ�һ���û������������Ĵ�С
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
	 * �õ�һ���û������䱨���İٷ���
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
		 * ���ʼ����浽������
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
//		  //		��־����
//		  LogFactory factory = new FileLogFactory("FileTransfor");
//		  //��־����
//		  Log log = factory.newInstance(this.getClass(),loghead);
//	  
//	  
//		try {
//			List sendAddress = confirmOverflow(path,mailmemory,SendAddress,chinaName,username);
//			String canSendAddress = (String)sendAddress.get(0);
//			
//			log.debug("�õ����浽���������Ա�б� "+canSendAddress);
//			
//			boolean issuccess = false;   //�ж��Ƿ񱣴�ɹ�
//			if(!("".equals(canSendAddress))){
//				
//				log.debug("���浽��������ʼ���������  "+subject);
//				log.debug("���浽��������ʼ�������  "+text);
//				//
//				if(attachment!=null){
//					for (int i = 0; i < attachment.length; i++) {
//						log.debug("�����ڷ������ϵ�λ���ǣ�"+attachment[i]);
//					}
//				}else{
//						log.debug("���ʼ��޸���");
//				}
//							
//				log.debug("���͸��Լ� start  ");
//				  mailhandler.transfermail(subject,text,to,null,null,attachment,flag,null);   
//                
//				log.debug("���͸��Լ� end  ");
//				//�ȴ������ڷ��͸��Լ����ʼ�Ϊ�Ѿ����յ�
//				//050702�޸�
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
//				//���͸��Լ������ϰѽ��յ����ʼ�ת�Ƶ�������
//				MessageHandler handler = new MessageHandler();
//				String[][] str = mailhandler.fileheadList("");
//				for(int i=0;i<str[0].length;i++){
//					String mailhead = str[1][i];
//					String reSubject = handler.getIntendSubject(mailhead);
////					String recePerosn = handler.getRecipients(mailhead);
//					if(subject.equals(reSubject)){  //��ʱ�����ж�
//						String mark = FileTransferConfig.SENT_FLAG;
//						String mailName = str[2][i];
//						String newmailName = mark+"o"+mailName.substring(2,mailName.length());
//						issuccess = mailhandler.dirRename(mailName,newmailName);
//					}
//				}
//			}
			
			//д�Ķ���¼��(���ڲ鿴�ж������Ķ��˷������ļ���
			//if(issuccess){
				this.setRecord(username,subject,receperson);
			//}

//		} catch (MessagingException e) {
//			e.printStackTrace();
//			log.debug("���浽������ʱ��������  "+e.getMessage());
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.debug("���浽������ʱ��������  "+e.getMessage());
//		} 
	}
	
	/**
	 * д�����ݿ⣬��ʶΪ��Ҫ�Ķ���¼
	 * @param �����û�USERID���ʼ����⣨����ʱ�䣩
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
			//д��һ����
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
	 * д�����ݿ⣬��ʶΪ��Ҫ�Ķ���¼(���ӷ�����ת��������Ҫ�ѽ����˼ӵ��Ķ���¼����)
	 * @param �����û�USERID���ʼ����⣨����ʱ�䣩�������˵������ʼ���ַ
	 * @return
	 * @throws 
	 */
	public void setOnlyRecord(String userid,String subject,String receperson){
		//д��һ����
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
	 * ɾ��һ���Ķ���¼
	 * @param �����û�USERID���ʼ����⣨����ʱ�䣩
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
	 * �鿴һ���ʼ��Ƿ���Ҫ���Ķ���¼������Ҫ���򷵻�MAILID�����򷵻�NULL
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
			if(wantlist!=null&&wantlist.size()>0){   //Ϊһ����Ҫ��¼�Ķ���¼���ʼ�
				FiletransferWanttorecordVO fvo = (FiletransferWanttorecordVO)wantlist.get(0);
				recordMailid = fvo.getMailid();
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return recordMailid;
	}
	
	/**
	 * д�����ݿ⣬��Ϊ�Ѷ���������д�룬��û�У���д��
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
			if(list!=null||list.size()>0){  //���û���һ�ζ������Ҫд�Ķ���¼���ʼ�
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
	 * д�����ݿ⣬��һ���Ķ���¼������ʶΪδ��
	 * @param �����û�USERID���ʼ����⣨����ʱ�䣩
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
	 * ��RECORDMAILID��������Ķ������ʼ����˵���������
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
			if(Personuuidlist!=null&&Personuuidlist.size()>0){   //Ϊһ����Ҫ��¼�Ķ���¼���ʼ�
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
	 * ��RECORDMAILID��������Ķ������ʼ����˵���������
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
			if(Personuuidlist!=null&&Personuuidlist.size()>0){   //Ϊһ����Ҫ��¼�Ķ���¼���ʼ�
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
	 * ��RECORDMAILID�����������ļ��Ľ����ˣ���������
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
			if(recordlist!=null&&recordlist.size()>0){   //Ϊһ����Ҫ��¼�Ķ���¼���ʼ�
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
	 * ���뵽����
	 * @return
	 * @throws 
	 */	
	public void addToIntend(String path,String username,String topic,String tointendId){
		//�ж��Ƿ�Ҫ�����������
		IntendWork addIntendHandler = new IntendWork(this.conn);
		String source= new String();
		String url= new String();
		String navigate= new String();
		String personid = new String();
		source = "�ļ�����";
//		url = path+"/servlet/MessageListServlet?type=system&folder=Inbox&search=no";
		url = path+"/servlet/ShowNoReadServlet";
		navigate = path+"/servlet/LeftListServlet";
		//�õ�ͨ�õ�userid
		FiletransferSetHandler ftsHandler = new FiletransferSetHandler(this.conn);
		String commUserid = ftsHandler.getUid(username);
		personid = commUserid;
		String type = IntendWorkConfig.IMPORT_LOCAL;
	
		if(personid!=null&&!personid.equals("")){
			addIntendHandler.addWork(topic,source,url,type,navigate,personid,IntendWork.getCodeValue("oa_filetrans"),tointendId);
		}
		
	}
	
	/**
	 * ɾ������
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
		 *ת����ISO-8859-1�����ʽ
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
	      
	/** ��ISO-8859-1��ת����GB2312 
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
				 System.err.print("����ת������"+e.getMessage()); 
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
	 * ��ǰ�û������洢·��
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
		
		//���Է��͵��û���ַ�ļ��� add by yangyang 20050708
		List legalAddressList = new ArrayList();		
		
		List Addresslist = new ArrayList();

		HttpSession session = request.getSession();
		String reString = "";
		String illegeReString = "";
		AddressHelp helper = new AddressHelp(this.conn);
		List personList = null;
		
		//0308Ϊ���ǵ�Ա�Ƚ��Եķ����ش˴���ȱʡΪ���ص�ί
		SelectOrgpersonVO vo = new SelectOrgpersonVO();
		vo.setUserid("suchengfang");
		vo.setUseruuid("3f40a06e-10241e61553-9849ddd9cda567c722b51e4369c3577a");
		vo.setName("�ճɷ�");
		vo.setIsperson("1");
		
		/*vo.setUserid("dev");
		vo.setUseruuid("7deef758-fdb75f4693-8581bd2af19e660037d144f55ca4fb1e");
		vo.setName("���������˺�");
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
			//���������ñ��õ�׼ȷ�������û������������������ַ
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
		//���Է��͵ĵ�ַ���Ƶļ��ϣ����ϵ�ÿ��Ԫ����String���͵Ķ��� add by yangyang 20050708
		//���磺
		//legalAddressList.get(0) = "user1@domain"
		//legalAddressList.get(1) = "user2@domain"
		//legalAddressList.get(2) = "user3@domain"
		Addresslist.add(2,legalAddressList);
		
		return Addresslist;
	}
  
	/**��StringTonkenzierת����ΪList���͵�����
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
	 * 	��ѯ�û���·����Ϣ
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
	
	/**�����û���·����Ϣ
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
