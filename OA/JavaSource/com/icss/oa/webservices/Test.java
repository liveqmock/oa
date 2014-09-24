package com.icss.oa.webservices;

import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;

public class Test {
	public static void main(String args[]) {
		Service srvcModel = new ObjectServiceFactory().create(IOAService.class);
		XFireProxyFactory factory = new XFireProxyFactory(XFireFactory
				.newInstance().getXFire());

		String URL = "http://10.102.1.38/oabase/services/oa";
		try {
			IOAService srvc = (IOAService) factory.create(srvcModel, URL);

			// SAXReader reader = new SAXReader();
			// Document document = reader.read(new File("c:\\intendWork.xml"));
			// Element root=document.getRootElement();
			// String docXmlText=document.asXML();
			// String rootXmlText=root.asXML();
			// System.out.println(docXmlText);
			// System.out.println(rootXmlText);
			// Element memberElm=root.element("member");
			// String memberXmlText=memberElm.asXML();

			// String xml="";
			System.out.println(srvc.isUser());
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}
}