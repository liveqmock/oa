/*
 * Created on 2004-7-20
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test;

import javax.servlet.http.*;

/**
 * @author firecoral
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestWaitServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest request,HttpServletResponse response){
		long start = System.currentTimeMillis();
		try {
			System.out.println("start wait.....");
			this.wait(10000);
			System.out.println("end wait.....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println(end-start);
	}

}
