<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.net.URL"%>

<%@ page import="javax.xml.namespace.QName"%>
<%@ page import="javax.xml.rpc.encoding.XMLType"%>

<%@ page import="org.apache.axis.client.Call"%>
<%@ page import="org.apache.axis.client.Service"%>

<%
		try {
			//System.out.println("########### " + URL);
			Service service = new Service();
			System.out.println("!!!!!!!!!!");
			Call call = (Call) service.createCall();
					System.out.println("!!!!!!!!!!1");
			
			call.setTargetEndpointAddress(new URL("http://10.102.1.61/oabase/services/oa?wsdl"));
						System.out.println("!!!!!!!!!!2");
			
			call.setOperationName(new QName("http://tempuri.org/","isUser"));
						System.out.println("!!!!!!!!!!3");
			


			call.setReturnType(XMLType.XSD_STRING);
						System.out.println("!!!!!!!!!!4");
			
			call.setUseSOAPAction(true);
						System.out.println("!!!!!!!!!!5");
			
			call.setSOAPActionURI("http://tempuri.org/isUser");
						System.out.println("!!!!!!!!!!6");
			

			String result = (String) call.invoke(new Object[] {});
						System.out.println("!!!!!!!!!!7");
			
			
			out.println("########### " + result);

		} catch (Exception e) {
			System.out.println("+++++++++++取得人事webservice出错");
			e.printStackTrace();
		}

%>