/*
 * Created on 2004-5-10
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.filetransfer.handler;

import com.icss.oa.util.CommUtil;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class AttachHelper {
	public static String getFileSize(long size) {
		StringBuffer filesize = new StringBuffer();
		if (size == 0) {
			filesize.append("0.0 MB");
		} else if (size < 10240) {
			filesize.append("< 0.01 MB");
		} else {
			filesize.append(CommUtil.getDivision(size, 1024 * 1024, 2));
			filesize.append(" MB");
		}
		return filesize.toString();
	}
}
