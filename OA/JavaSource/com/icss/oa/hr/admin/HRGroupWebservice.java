package com.icss.oa.hr.admin;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class HRGroupWebservice {

//	private String ENDPOINT = HRGroupWebservice.readValue("HRWebservice");
//	private String URL = HRGroupWebservice.readValue("HRUserWebservice");
//
//	private String SyncURL = HRGroupWebservice.readValue("HRSyncWebservice");

	// private static final String ENDPOINT =
	// "http://172.16.143.73/CommonDapter/PersonGroup.asmx";

	/**
	 * 取得仅这个ID分组的人员
	 * 
	 * @param groupID
	 * @return
	 */
	public String GetPerson(String groupID) {
//		String result = "";
//		try {
//			//System.out.println("###"+groupID);
//			Service service = new Service();
//			Call call = (Call) service.createCall();
//			call.setTargetEndpointAddress(new URL(ENDPOINT));
//
//			call
//					.setOperationName(new QName("http://www.philisense.com",
//							"GetPerson"));
//
//			call.addParameter(new QName("http://www.philisense.com", "groupID"),
//					XMLType.XSD_STRING, ParameterMode.IN);
//
//			call.setReturnType(XMLType.XSD_STRING);
//			call.setUseSOAPAction(true);
//			call.setSOAPActionURI("http://www.philisense.com/GetPerson");
//
//			result = (String) call.invoke(new Object[] { groupID });
//			//System.out.println("++人事 " + result);
//		} catch (Exception e) {
//			System.out.println("+++++++++++取得人事webservice出错");
//			e.printStackTrace();
//		}

		return "";
	}

	/**
	 * 取得ID下的所有人员
	 * 
	 * @param groupID
	 * @return
	 */
	public String GetAllPerson(String groupID) {
//		String result = "";
//		// System.out.println("@@@@@@@@@"+groupID);
//		try {
//			Service service = new Service();
//			Call call = (Call) service.createCall();
//			call.setTargetEndpointAddress(new URL(ENDPOINT));
//
//			call.setOperationName(new QName("http://www.philisense.com",
//					"GetAllPerson"));
//
//			call.addParameter(new QName("http://www.philisense.com", "groupID"),
//					XMLType.XSD_STRING, ParameterMode.IN);
//
//			call.setReturnType(XMLType.XSD_STRING);
//			call.setUseSOAPAction(true);
//			call.setSOAPActionURI("http://www.philisense.com/GetAllPerson");
//
//			result = (String) call.invoke(new Object[] { groupID });
//			//System.out.println("++人事1 " + result);
//
//		} catch (Exception e) {
//			System.out.println("+++++++++++取得人事webservice出错");
//			e.printStackTrace();
//		}

		return "";
	}

	/**
	 * 取得所有分组
	 * 
	 * @return
	 */
	public String GetPersonGroup() {
//		String result = "";
//		try {
//			//System.out.println("########### " + ENDPOINT);
//			Service service = new Service();
//			Call call = (Call) service.createCall();
//			call.setTargetEndpointAddress(new URL(ENDPOINT));
//			call.setOperationName(new QName("http://www.philisense.com",
//					"GetPersonGroup"));
//
//			call.setReturnType(XMLType.XSD_STRING);
//			call.setUseSOAPAction(true);
//			call.setSOAPActionURI("http://www.philisense.com/GetPersonGroup");
//
//			result = (String) call.invoke(new Object[] {});
//
//			//System.out.println("########### " + result);
//
//		} catch (Exception e) {
//			System.out.println("+++++++++++取得人事webservice出错");
//			e.printStackTrace();
//
//			result = "<PERSONGROUPS><GROUP><GROUPID></GROUPID><ORGNAME></ORGNAME><DESCRIPTION></DESCRIPTION><PARENTID></PARENTID><GROUPLEVEL></GROUPLEVEL></GROUP></PERSONGROUPS>";
//
//		}

		return "";
	}

	/**
	 * 返回人事
	 * 
	 * @return
	 */
	public String BackToHR(String xml) {
//		String result = "";
//		try {
//			System.out.println("########### " + URL);
//			Service service = new Service();
//			Call call = (Call) service.createCall();
//			call.setTargetEndpointAddress(new URL(URL));
//			call.setOperationName(new QName("http://www.philisense.com",
//					"GetSynchroUserName"));
//
//			call.addParameter(new QName("http://www.philisense.com", "xml"),
//					XMLType.XSD_STRING, ParameterMode.IN);
//			// call.addParameter("xml", XMLType.XSD_STRING, ParameterMode.IN);
//
//			call.setReturnType(XMLType.XSD_STRING);
//			call.setUseSOAPAction(true);
//			call.setSOAPActionURI("http://www.philisense.com/GetSynchroUserName");
//
//			result = (String) call.invoke(new Object[] { xml });
//
//			System.out.println("########### " + result);
//
//		} catch (Exception e) {
//			System.out.println("+++++++++++取得人事webservice出错");
//			e.printStackTrace();
//		}

		return "";
	}

	public String GetSyncOrgInfo() {
//		String result = "";
//		try {
//			System.out.println("########### " + SyncURL);
//
//			Service service = new Service();
//			Call call = (Call) service.createCall();
//			call.setTargetEndpointAddress(new URL(SyncURL));
//			call.setOperationName(new QName("http://www.philisense.com",
//					"GetSyncOrgInfo"));
//
//			call.setReturnType(XMLType.XSD_STRING);
//			call.setUseSOAPAction(true);
//			call.setSOAPActionURI("http://www.philisense.com/GetSyncOrgInfo");
//
//			result = (String) call.invoke(new Object[] {});
//
//			// System.out.println("########### " + result);
//
//		} catch (Exception e) {
//			System.out.println("+++++++++++取得人事webservice出错");
//			e.printStackTrace();
//		}

		return "";
	}

	public String GetSyncPersonInfo() {
//		String result = "";
//		try {
//			System.out.println("########### " + SyncURL);
//			Service service = new Service();
//			Call call = (Call) service.createCall();
//			call.setTargetEndpointAddress(new URL(SyncURL));
//			call.setOperationName(new QName("http://www.philisense.com",
//					"GetSyncPersonInfo"));
//					
//			call.setReturnType(XMLType.XSD_STRING);
//			call.setUseSOAPAction(true);
//			call.setSOAPActionURI("http://www.philisense.com/GetSyncPersonInfo");
//
//			result = (String) call.invoke(new Object[] {});
//
//			System.out.println("@@@@@@" + result);
//
//		} catch (Exception e) {
//			System.out.println("+++++++++++取得人事webservice出错");
//			e.printStackTrace();
//		}

		return "";
	}

	public static String readValue(String key) {

		Properties props = new Properties();
		try {
			Class theClass = HRGroupWebservice.class;

			InputStream in = theClass
					.getResourceAsStream("/WebServices.properties");
			props.load(in);

			String value = props.getProperty(key);
			return value;
		} catch (Exception e) {
			System.out.println("############## 取得 WebServices.properties 出错");
			e.printStackTrace();
			return null;
		}
	}

}
