/*
 * Created on 2004-6-15
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package test.com.icss;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class TestSysTime {

	public static void main(String[] args) {
		Date date = new Date();
		long ldate = date.getTime();
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		System.out.println(formate.format(date).toString());
	}
}
