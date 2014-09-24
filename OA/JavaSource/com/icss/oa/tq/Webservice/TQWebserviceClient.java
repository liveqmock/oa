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
		//接口地址参数
        URL url= new URL (LURL);//本地测试地址 
        //正式地址
        //URL url= new URL (SURL);
        //接口服务方法
        String method = "newRegister";
        //接口参数
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
        logger.debug("register-->"+"用户名："+username+";密码："+userpassword+"; TQ号码："+TQ);
        return TQ;
    }
    
//    public String refresh(String admstrid,String agentid,String agentpw,String uin,
//    		String username,String userpassword,String nickname,String departmentId,String spreadparam) throws MalformedURLException {
//		//接口地址参数
//        URL url= new URL (LURL);//本地测试地址 
//        //接口服务方法
//        String method = "newRefresh";
//        //接口参数
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
//        logger.debug("refresh-->"+" TQ号码："+uin+"用户名："+username+";密码："+userpassword);
//        return callAndSend(url,method,params);
//    }
    
    public String refresh(String agentid,String agentpw,String uin,
    		String username,String userpassword,String nickname,String departmentId,String spreadparam) throws MalformedURLException {
		//接口地址参数
        URL url= new URL (LURL);//本地测试地址 
        //接口服务方法
        String method = "newRefresh";
        //接口参数
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
        logger.debug("refresh-->"+" TQ号码："+uin+"用户名："+username+";密码："+userpassword);
        return callAndSend(url,method,params);
    }
     
    
    public String delete(String agentid,String agentpw,String uin,String username) throws MalformedURLException {
    	
		//接口地址参数
        URL url= new URL (LURL);//本地测试地址 
        //接口服务方法
        String method = "newDelete";
        //接口参数
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("uin", String.class, uin, null));
        params.addElement(new Parameter("username", String.class, username, null));
        logger.debug("delete-->"+"用户名："+username+" TQ号码： "+uin);
        return callAndSend(url,method,params);
    }
    
   
    
 
 
    
  
  
    
   
    
    //系统消息创建
    public String createSysMsg(String agentid, String agentpw, String title, String digest, String body, String life_time, String refresh_time, 
			String smess_type, String create_auther, String smess_uin, String smess_start_uin, String smess_end_uin, String show_type, String smess_url) throws MalformedURLException {
    	
        //接口地址参数
        URL url= new URL (LURL);//本地测试地址 
        //接口服务方法
        String method = "createSysMsg";
        //接口参数
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
    
    //系统消息更新
    public String refreshSysMsg(String agentid, String agentpw, String id,
    	String title, String digest, String body, String life_time, 
		String refresh_time, String smess_type, String create_auther, 
		String smess_uin, String smess_start_uin, String smess_end_uin, 
		String show_type, String smess_url) throws MalformedURLException {
        //接口地址参数
        URL url= new URL (LURL);//本地测试地址 
        //接口服务方法
        String method = "refreshSysMsg";
        //接口参数
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
    
    //系统消息的删除
    public String deleteSysMsg(String agentid, String agentpw, String id) throws MalformedURLException {
        //接口地址参数
        URL url= new URL (LURL);//本地测试地址 
        //接口服务方法
        String method = "deleteSysMsg";
        //接口参数
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("id", String.class, id, null));
    	return callAndSend(url,method,params);
    }
    
    public String getSysMsgIdByTime(String agentid, String agentpw, String startTime, String endTime) throws MalformedURLException {
        //接口地址参数
        URL url= new URL (LURL);//本地测试地址 
        //接口服务方法
        String method = "getSysMsgIdByTime";
        //接口参数
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("startTime", String.class, startTime, null));
        params.addElement(new Parameter("endTime", String.class, endTime, null));
    	return callAndSend(url,method,params);
    }
    
    public String getSysMsgInfoById(String agentid, String agentpw, String id) throws MalformedURLException {
        //接口地址参数
        URL url= new URL (LURL);//本地测试地址 
        //接口服务方法
        String method = "getSysMsgInfoById";
        //接口参数
        Vector params = new Vector();
        params.addElement(new Parameter("agentid", String.class, agentid, null));
        params.addElement(new Parameter("agentpw", String.class, agentpw, null));
        params.addElement(new Parameter("id", String.class, id, null));
    	return callAndSend(url,method,params);
    }
    
    //创建部门
  

    
    
    
    
    
    
    
       
    
    
    
	/**
	 * 发送并接收结果
	 *
	 * @param 接口的url
	 * @param 接口的方法名
	 * @see "接口的方法名上面已给出，或在把接口连接敲入ie地址栏打开查看"
	 * @param 参数堆栈
	 * @return int 生成的用户号码 或错误信息 
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
			
		SOAPMappingRegistry smr = new SOAPMappingRegistry ();//它用来注册一个以前未知的类型,比如定制的JavaBean
        StringDeserializer sd = new StringDeserializer ();
        smr.mapTypes (Constants.NS_URI_SOAP_ENC, new QName ("", "Result"), null, null, sd);
        SOAPHTTPConnection st = new SOAPHTTPConnection();
        Call call = new Call ();
        call.setSOAPTransport(st);
        call.setSOAPMappingRegistry (smr);
        call.setTargetObjectURI ("http://tempuri.org/message/");
        
          //设置调用的方法名    ***需要修改
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

        // 检查返回值
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
			System.out.println("############## 取得 WebServices.properties 出错");
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
