/*
 * Created on 2004-4-15
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.intendwork.helper;

import com.icss.oa.config.IntendWorkConfig;
import com.icss.oa.util.CommUtil;
/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ShowWorkHelper {
	public static String showWorkFlag(String flag) {
		if (flag.equals(IntendWorkConfig.WORKFLAG_HASDONE)) {
			return "已处理";
		} else if (flag.equals(IntendWorkConfig.WORKFLAG_NOTDO)) {
			return "未处理";
		}
		return "";
	}

	public static String showWorkModify(String flag, Long modify) {
		if (modify == null) {
			return "";
		} else if (flag.equals(IntendWorkConfig.WORKFLAG_NOTDO)) {
			return "";
		} else {
			return CommUtil.getTime(modify.longValue());
		}

	}
}
