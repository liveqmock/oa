/*
 * Created on 2004-5-29
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test.com.icss;

import org.jdom.*;

import java.io.File;
import org.jdom.input.SAXBuilder;
/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestJDom {

	public static void main(String[] args) {
		String xmlFile = "D:\\ResourceOneHome\\config\\testxml3.xml";
		SAXBuilder builder = new SAXBuilder();
		try{
			Document doc = builder.build(new File(xmlFile));
			
			System.out.println(doc.getRootElement());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
