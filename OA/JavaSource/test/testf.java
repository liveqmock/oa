/*
 * Created on 2004-9-20
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package test;

import com.icss.oa.message.handler.MsgHandler;  

/**
 * @author xhsh
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class testf {
	public static void main(String arg[]){
		String a="°¢ËÕdadadada\r\n\n\r\r\ra~ÐÂ»ªÉç~";
		System.out.println(a);
		String s=MsgHandler.replaceContent(a,false);
		System.out.println(s);
		String s1=MsgHandler.replaceBackContent(s);
		System.out.println(s1);
		System.out.println(MsgHandler.getContent(s1));
		System.out.println(MsgHandler.getContentOrg(s1));
	}
}
