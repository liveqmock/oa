/*
 * 创建日期 2004-4-1
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.icss.oa.commsite.admin;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.commsite.handler.CommsiteHandler;
import com.icss.oa.commsite.vo.CommsiteVO;

/**
 * @author Administrator
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class UpdateWebPageServlet extends ServletBase {

	/* （非 Javadoc）
	 * @see com.icss.j2ee.servlet.ServletBase#performTask(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

			Connection conn=null;
		
			Integer ocsId = new Integer(request.getParameter("ocsId"));		
			String ocsName = request.getParameter("ocsName");
			Integer ocsIndex = new Integer(request.getParameter("ocsIndex"));
			String ocsLink0 = request.getParameter("ocsLink");
			String ocsDes = request.getParameter("ocsDes");
			String ocsLogo = request.getParameter("ocsLogo");
			String check[] = request.getParameterValues("check");
			boolean usedefaultlogo=false;
			try{
				if(check.length>0) usedefaultlogo=true;
			}
			catch(Exception e){
				e.printStackTrace();
				usedefaultlogo=false;
			}
			
			try {
				conn = this.getConnection(Globals.DATASOURCEJNDI);
				ConnLog.open("UpdateWebPageServlet");
				CommsiteHandler handler = new CommsiteHandler(conn);
				InputStream inputStream = null;
				
				CommsiteVO vo = new CommsiteVO();
				vo.setOcsId(ocsId);
				vo.setOcsName(ocsName);
				vo.setOcsIndex(ocsIndex);
				String ocsLink =handler.CheckLink(ocsLink0);
				vo.setOcsLink(ocsLink);
				vo.setOcsDes(ocsDes);
				if(!usedefaultlogo){
					String filename = this.getUploadFileFullName(request,"ocsLogo");
					try{
						inputStream = new FileInputStream(filename);
					}
					catch(FileNotFoundException e){
						inputStream = null;
					}
					if(inputStream!=null){
						vo.setOcsLogo(inputStream);
					}
				}
				
				handler.update(vo,usedefaultlogo);
				
				if(inputStream!=null){
					inputStream.close();
				}
				response.sendRedirect("ListWebPageServlet?_page_num="+request.getParameter("_page_num"));
				
			} catch (Exception e) {
				handleError(e);
			} finally {
				try {
					if (conn != null) {
						conn.close();
						ConnLog.close("UpdateWebPageServlet");
					}
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				}
			}

	}

}
