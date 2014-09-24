/*
 * Created on 2004-4-12
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */

package com.icss.oa.useraddress.admin;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.UserMsgHandler;
import com.icss.oa.useraddress.handler.UserAddressHandler;
import com.icss.oa.useraddress.vo.OaaddListVO;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Generation - Code and Comments
 */
public class UseraddressImportServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		String tempfile_name=request.getParameter("file_name");
		byte temp[]=null;
		String file_name=null;
		try {
			temp=tempfile_name.getBytes("iso-8859-1");
			
		}catch(UnsupportedEncodingException e){
			
			System.out.println(e.toString());
		}
		 file_name=new String(temp);
		
		List UseraddressList = null;

		int j;

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
//			ConnLog.open("UseraddressPrintServlet");			
			UserAddressHandler handler = new UserAddressHandler(conn);

			System.out.println("数据导入 begin");
			handler.dataImport(file_name);
			System.out.println("数据导入 end");
			response.sendRedirect("OaaddListServlet");

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);

		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("UseraddressPrintServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

}
