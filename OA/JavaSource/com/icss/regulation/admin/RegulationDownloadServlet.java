package com.icss.regulation.admin;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.servlet.download.DownloadResponse;
import com.icss.j2ee.util.ConfigurationUtil;
import com.icss.j2ee.util.Globals;
import com.icss.j2ee.util.UUID;
import com.icss.regulation.handler.Html2Text;
import com.icss.regulation.handler.RegulationHandler;
import com.icss.regulation.handler.ZipTools;
import com.icss.regulation.vo.RegulationVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class RegulationDownloadServlet extends ServletBase {

	/**
	 * 
	 */

	protected void performTask(HttpServletRequest request,
			HttpServletResponse response) {
		Connection conn = null;
		try {
			Context ctx = null;
			ctx = Context.getInstance();
			UserInfo user = ctx.getCurrentLoginInfo();
			String uuid = user.getPersonUuid();
			conn = getConnection(Globals.DATASOURCEJNDI);
			RegulationVO vo = new RegulationVO();
			RegulationHandler handler  = new RegulationHandler(conn);

			String ids = request.getParameter("ids")==null?"":request.getParameter("ids");
			String id[]= ids.split(",");
			String str1 = ConfigurationUtil.getInstance().getUploadTempPath();
			System.out.println(str1);
			String str2 = new UUID().toString();
		    String str3 = str1 + File.separator + str2 ;
			
		    String str = "(?s)url= <a href='http://www.csdn.net' target='_blank'>哈哈哈 </a><a href=\"ab.com\" >"; 
		    Pattern p = Pattern.compile("href=['\"]([^'\"]*)['\"]");
		    
		    
			List<String> fileName = new ArrayList<String>();
			for(int i=0;i<id.length;i++){
				vo = handler.getRegulation(new Integer(id[i]));
				String fn= str3+ File.separator + vo.getTitle()+".txt";
				fileName.add(fn);
				
				Matcher matcher = p.matcher(vo.getContent());
		        while(matcher.find()) {
		            String href = matcher.group(1);
		            if(href.indexOf("/UserFiles")>0){
		            	href = href.substring(href.indexOf("/UserFiles")+10);
		            }
		            System.out.println("附件地址="+getServletContext().getRealPath("/UserFiles")+href);
		            fileName.add(getServletContext().getRealPath("/UserFiles")+href);
		        }
				FileUtils.writeStringToFile(new File(fn),Html2Text.Html2Text(vo.getContent()).replaceAll( "&nbsp;&nbsp;","  "), "utf-8"); 
			}

//			ZipBean zb = new ZipBean(new File(str3),new File(str3+"a.zip"));	
//			zb.start();
			
			String zipname = str3+ File.separator+"管理制度.zip";
			ZipTools.MakeZip(fileName, zipname);
			
			DownloadResponse ds = new DownloadResponse(response);
			//ds.downloadInputStreamByTempfile(is, fileName);
			ds.downloadFile(zipname);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}

	}
	
}