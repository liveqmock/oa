/*
 * Created on 2004-4-27
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.address.helper;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AddressViewHelper {
	public static String getSex(Integer sex) {
		if (sex != null) {
			if (sex.equals("0")) {
				return "±£ÃÜ";
			} else if (sex.equals("1")) {
				return "ÄÐ";
			} else if (sex.equals("2")) {
				return "Å®";
			}

		}

		return "";
	}
}
