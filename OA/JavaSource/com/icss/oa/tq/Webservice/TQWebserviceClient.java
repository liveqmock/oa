package com.icss.oa.tq.Webservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.apache.soap.Constants;
import org.apache.soap.Fault;
import org.apache.soap.SOAPException;
import org.apache.soap.encoding.SOAPMappingRegistry;
import org.apache.soap.encoding.soapenc.StringDeserializer;
import org.apache.soap.rpc.Call;
import org.apache.soap.rpc.Parameter;
import org.apache.soap.rpc.Response;
import org.apache.soap.transport.http.SOAPHTTPConnection;
import org.apache.soap.util.xml.QName;

public class TQWebserviceClient {

	//private static final String LURL = "http://10.102.1.36:8080/Servers/services/ServerAll?wsdl";
	
	private String LURL = this.readValue("TQWebservice");

	
	private static final String RESULT_APP_ERROR = "ERROR";

	Logger logger = Logger.getLogger(TQWebserviceClient.class);
	
    public String register(String admstrid,String agentid,String agentpw,
    	String username,String userpassword,String sex,String nickname,String email,
    	String city,String departmentId,String spreadparam) throws Exception 
    { 	
		//�ӿڵ�ַ����
        URL url= new URL (LURL);//���ز��Ե�ַ 
        //��ʽ��ַ
        //URL url= new URL (SURL);
        //�ӿڷ��񷽷�
        String method = "newRegister";
        //�ӿڲ���
        Vector params = new Vector();
        params.addElement(new Parameter("admstrid", String.class, admstrid, null));
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("username", String.class, username, null));
        params.addElement(new Parameter("userpassword", String.class, userpassword, null));
        params.addElement(new Parameter("sex", String.class, sex, null));
        params.addElement(new Parameter("nickname", String.class, nickname, null));
        params.addElement(new Parameter("email", String.class, email, null));
        params.addElement(new Parameter("city", String.class, city, null));
        params.addElement(new Parameter("departmentId", String.class, departmentId, null));
        params.addElement(new Parameter("spreadparam", String.class, spreadparam, null));       
        String TQ = callAndSend(url,method,params);	
        logger.debug("register-->"+"�û�����"+username+";���룺"+userpassword+"; TQ���룺"+TQ);
        return TQ;
    }
    
//    public String refresh(String admstrid,String agentid,String agentpw,String uin,
//    		String username,String userpassword,String nickname,String departmentId,String spreadparam) throws MalformedURLException {
//		//�ӿڵ�ַ����
//        URL url= new URL (LURL);//���ز��Ե�ַ 
//        //�ӿڷ��񷽷�
//        String method = "newRefresh";
//        //�ӿڲ���
//        Vector params = new Vector();
//        params.addElement(new Parameter("admstrid", String.class, admstrid, null));
//        params.addElement(new Parameter("agentid", String.class, agentid, null));
//        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
//        params.addElement(new Parameter("uin", String.class, uin, null));
//        params.addElement(new Parameter("username", String.class, username, null));
//        params.addElement(new Parameter("userpassword", String.class, userpassword, null));
//        params.addElement(new Parameter("nickname", String.class, nickname, null));
//        params.addElement(new Parameter("departmentId", String.class, departmentId, null));
//        params.addElement(new Parameter("spreadparam", String.class, spreadparam, null));       
//        logger.debug("refresh-->"+" TQ���룺"+uin+"�û�����"+username+";���룺"+userpassword);
//        return callAndSend(url,method,params);
//    }
    
    public String refresh(String agentid,String agentpw,String uin,
    		String username,String userpassword,String nickname,String departmentId,String spreadparam) throws MalformedURLException {
		//�ӿڵ�ַ����
        URL url= new URL (LURL);//���ز��Ե�ַ 
        //�ӿڷ��񷽷�
        String method = "newRefresh";
        //�ӿڲ���
        Vector params = new Vector();
       // params.addElement(new Parameter("admstrid", String.class, admstrid, null));
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("uin", String.class, uin, null));
        params.addElement(new Parameter("username", String.class, username, null));
        params.addElement(new Parameter("userpassword", String.class, userpassword, null));
        params.addElement(new Parameter("nickname", String.class, nickname, null));
        params.addElement(new Parameter("departmentId", String.class, departmentId, null));
        params.addElement(new Parameter("spreadparam", String.class, spreadparam, null));       
        logger.debug("refresh-->"+" TQ���룺"+uin+"�û�����"+username+";���룺"+userpassword);
        return callAndSend(url,method,params);
    }
     
    
    public String delete(String agentid,String agentpw,String uin,String username) throws MalformedURLException {
    	
		//�ӿڵ�ַ����
        URL url= new URL (LURL);//���ز��Ե�ַ 
        //�ӿڷ��񷽷�
        String method = "newDelete";
        //�ӿڲ���
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("uin", String.class, uin, null));
        params.addElement(new Parameter("username", String.class, username, null));
        logger.debug("delete-->"+"�û�����"+username+" TQ���룺 "+uin);
        return callAndSend(url,method,params);
    }
    
   
    
 
 
    
  
  
    
   
    
    //ϵͳ��Ϣ����
    public String createSysMsg(String agentid, String agentpw, String title, String digest, String body, String life_time, String refresh_time, 
			String smess_type, String create_auther, String smess_uin, String smess_start_uin, String smess_end_uin, String show_type, String smess_url) throws MalformedURLException {
    	
        //�ӿڵ�ַ����
        URL url= new URL (LURL);//���ز��Ե�ַ 
        //�ӿڷ��񷽷�
        String method = "createSysMsg";
        //�ӿڲ���
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("title", String.class, title, null));
        params.addElement(new Parameter("digest", String.class, digest, null));
        params.addElement(new Parameter("body", String.class, body, null));
        params.addElement(new Parameter("life_time", String.class, life_time, null));
        params.addElement(new Parameter("refresh_time", String.class, refresh_time, null));
        params.addElement(new Parameter("smess_type", String.class, smess_type, null));
        params.addElement(new Parameter("create_auther", String.class, create_auther, null));
        params.addElement(new Parameter("smess_uin", String.class, smess_uin, null));
        params.addElement(new Parameter("smess_start_uin", String.class, smess_start_uin, null));
        params.addElement(new Parameter("smess_end_uin", String.class, smess_end_uin, null));
        params.addElement(new Parameter("show_type", String.class, show_type, null));
        params.addElement(new Parameter("smess_url", String.class, smess_url, null));
    	return callAndSend(url,method,params);
    }
    
    //ϵͳ��Ϣ����
    public String refreshSysMsg(String agentid, String agentpw, String id,
    	String title, String digest, String body, String life_time, 
		String refresh_time, String smess_type, String create_auther, 
		String smess_uin, String smess_start_uin, String smess_end_uin, 
		String show_type, String smess_url) throws MalformedURLException {
        //�ӿڵ�ַ����
        URL url= new URL (LURL);//���ز��Ե�ַ 
        //�ӿڷ��񷽷�
        String method = "refreshSysMsg";
        //�ӿڲ���
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("id", String.class, id, null));
        params.addElement(new Parameter("title", String.class, title, null));
        params.addElement(new Parameter("digest", String.class, digest, null));
        params.addElement(new Parameter("body", String.class, body, null));
        params.addElement(new Parameter("life_time", String.class, life_time, null));
        params.addElement(new Parameter("refresh_time", String.class, create_auther, null));
        params.addElement(new Parameter("smess_type", String.class, smess_start_uin, null));
        params.addElement(new Parameter("smess_uin", String.class, agentpw, null));
        params.addElement(new Parameter("smess_end_uin", String.class, smess_end_uin, null));
        params.addElement(new Parameter("show_type", String.class, show_type, null));
        params.addElement(new Parameter("smess_url", String.class, smess_url, null));
    	return callAndSend(url,method,params);
    }
    
    //ϵͳ��Ϣ��ɾ��
    public String deleteSysMsg(String agentid, String agentpw, String id) throws MalformedURLException {
        //�ӿڵ�ַ����
        URL url= new URL (LURL);//���ز��Ե�ַ 
        //�ӿڷ��񷽷�
        String method = "deleteSysMsg";
        //�ӿڲ���
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("id", String.class, id, null));
    	return callAndSend(url,method,params);
    }
    
    public String getSysMsgIdByTime(String agentid, String agentpw, String startTime, String endTime) throws MalformedURLException {
        //�ӿڵ�ַ����
        URL url= new URL (LURL);//���ز��Ե�ַ 
        //�ӿڷ��񷽷�
        String method = "getSysMsgIdByTime";
        //�ӿڲ���
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("startTime", String.class, startTime, null));
        params.addElement(new Parameter("endTime", String.class, endTime, null));
    	return callAndSend(url,method,params);
    }
    
    public String getSysMsgInfoById(String agentid, String agentpw, String id) throws MalformedURLException {
        //�ӿڵ�ַ����
        URL url= new URL (LURL);//���ز��Ե�ַ 
        //�ӿڷ��񷽷�
        String method = "getSysMsgInfoById";
        //�ӿڲ���
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("id", String.class, id, null));
    	return callAndSend(url,method,params);
    }
    
    //��������
  

    
    
    
    
    
    
    
       
    
    
    
	/**
	 * ���Ͳ����ս��
	 *
	 * @param �ӿڵ�url
	 * @param �ӿڵķ�����
	 * @see "�ӿڵķ����������Ѹ��������ڰѽӿ���������ie��ַ���򿪲鿴"
	 * @param ������ջ
	 * @return int ���ɵ��û����� �������Ϣ 
	 * 
	 */
	private String  callAndSend(URL url,String method,Vector vParams)
	{
		
		String result=RESULT_APP_ERROR;
		
		if(vParams == null||url==null)
		{
			System.out.println (" vparam error in CTQWebService" );
			return result;
		}
		if(method == null||method.equals(""))
		{
			System.out.println (" method error in CTQWebService" );
			return result;
		}
			
		SOAPMappingRegistry smr = new SOAPMappingRegistry ();//������ע��һ����ǰδ֪������,���綨�Ƶ�JavaBean
        StringDeserializer sd = new StringDeserializer ();
        smr.mapTypes (Constants.NS_URI_SOAP_ENC, new QName ("", "Result"), null, null, sd);
        SOAPHTTPConnection st = new SOAPHTTPConnection();
        Call call = new Call ();
        call.setSOAPTransport(st);
        call.setSOAPMappingRegistry (smr);
        call.setTargetObjectURI ("http://tempuri.org/message/");
        
          //���õ��õķ�����    ***��Ҫ�޸�
        call.setMethodName(method);
        call.setEncodingStyleURI ("http://schemas.xmlsoap.org/soap/encoding/");
        call.setParams(vParams);
        
        Response resp = null;

        try 
        {
          	resp = call.invoke (url, "");
        }
        catch (SOAPException e) 
        {
        	System.err.println("Caught SOAPException (" + e.getFaultCode () + "): " + e.getMessage ());
        	return result;
        }

        // ��鷵��ֵ
        if (resp != null && !resp.generatedFault()) 
        {
	        Parameter ret = resp.getReturnValue();
	        Object value = ret.getValue();
			result=(String)value;
	        System.out.println ("Answer--> " + value);
	        return result;
        }
        else 
        {
            Fault fault = resp.getFault ();
            System.err.println ("Generated fault: ");
            System.out.println (" Fault Code = " + fault.getFaultCode());
            System.out.println (" Fault String = " + fault.getFaultString());
            return result;
        }
	}
    
	
	private String readValue(String key) {
		
		Properties props = new Properties();
		InputStream in = null;
		try {
			in = this.getClass().getResourceAsStream("/WebServices.properties");
			props.load(in);

			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			System.out.println("############## ȡ�� WebServices.properties ����");
			e.printStackTrace();
			return null;
		}finally{
			
				try {
					if(in!=null)
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
