/*
 * Created on 2004-2-18
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.bbs.user.control;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;
import com.icss.oa.bbs.handler.BBSHandler;
import com.icss.oa.util.CommUtil;
/**
 * @author Administrator
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EditArticleServlet extends ServletBase {
	protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		Integer articleId = new Integer(request.getParameter("articleId"));
		String content = request.getParameter("content");
		String articleName = request.getParameter("articleName");
		String boardId = request.getParameter("boardId");
		String topicId = request.getParameter("topicId");
		String face = request.getParameter("face");
		String tointend = request.getParameter("checkbox3");
		if ("checked".equals(tointend))
			tointend = "1";
		else
			tointend = "0";
		String accType = "";
		String oldAccType = request.getParameter("accType");
		//		取得上传文件名
		String oldFileName = getUploadOldFileName(request, "acc");
		String fileType = "";
		if (oldFileName != null && oldFileName != "") {
			int index = 0;
			index = oldFileName.lastIndexOf("\\");
			if (index != -1)
				oldFileName = oldFileName.substring(index + 1);
			//取得文件后缀名
			index = oldFileName.lastIndexOf(".");
			if (index != -1)
				fileType = oldFileName.substring(index + 1);
		}
		//设置文件类型
		if (oldFileName == null || oldFileName == "")
			accType = "0";
		else if (fileType.equals("jpg") || fileType.equals("bmp") || fileType.equals("gif"))
			accType = "1";
		else
			accType = "2";

		if (!oldAccType.equals("0")) {
			accType = oldAccType;
		}

		try {
			conn = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("EditArticleServlet");
			BBSHandler handler = new BBSHandler(conn);
			if (oldFileName != null) {
				//获得文件路径名
				String fileFillName = getUploadFileFullName(request, "acc");
				InputStream inputStream = new FileInputStream(fileFillName);
				handler.addAccessory(articleId, oldFileName, inputStream);
				//关闭输入流
				inputStream.close();
			}
			if (content != null) {
				content = CommUtil.formathtm(content);
			}
			handler.editArticle(articleId, articleName, content, tointend, accType, face);
			response.sendRedirect("ShowArticleServlet?topicId=" + topicId + "&boardId=" + boardId + "&hitFlag=0");
		} catch (Exception e) {
			handleError(e);
		} finally {
			try {
				if (conn != null){
					conn.close();
					ConnLog.close("EditArticleServlet");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
