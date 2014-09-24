/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.multivote.admin;        

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;


import com.icss.oa.multivote.handler.VoteHandler;


/**
 * @author Administrator
 * 
 * 上传附件
 */
public class MVoteImportVoteUserServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Integer planid=request.getParameter("planid")==null?new Integer(-1):new Integer(request.getParameter("planid"));
        
		Connection conn = null;
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String url = ""; 
			VoteHandler handler=new VoteHandler(conn);
			
			try{
				url = getUploadFileFullName(request, "attfile");
				System.out.println("MVoteImportVoteUserServlet attfile=  "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/multivote/alert.jsp?errormsg=读取文件路径错误");
			}
			// 判断附件总和是否大于10M

			String filename = getUploadOldFileName(request, "attfile");

			System.out.println("++++++++++MVoteImportVoteUserServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// 得到上载的文件名称
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						
					}
					System.out.println("++++++++++MVoteImportVoteUserServlet+++filename===+++++++"+filename);
					// ----------把上载的文件转移到指定的邮件下载目录下----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"multivote"+sep+"upload"+sep));
					
					path.append("user").append(System.currentTimeMillis()).append(File.separator);
					System.out.println("++++++++++MVoteImportVoteUserServlet++++++++++path=======++"+path);
					File uploadpath = new File(path.toString());
					// System.out.println("sunchuanting_path "+path.toString());
					if(!uploadpath.exists()){
						uploadpath.mkdirs();
					}
					String filepath = path.append(filename).toString();
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));
					System.out.println("++++++++++MVoteImportVoteUserServlet++++++++++filepath=======++"+filepath);
					
					
					
					
					if(ins!= null){
						try {
							ins.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
//					 ----------------------读取文件文件---------------------------
					System.out.println("读取文件文件 begin");
					
					//List implist=new ArrayList();
					//List implist2=new ArrayList();
//					取得excel表中的行数据值
					handler.impVoteUserReadExcel(filepath,planid);
					System.out.println("完成数据导入");
					//Iterator impitr=implist.iterator();
					
					
						
					response.sendRedirect(request.getContextPath()
									+ "/servlet/MVoteUserManagerListServlet?planid="+planid);
						
					
					
					System.out.println("读取文件文件 end");
					
					
					
					
				}else{   // file isn't exist
					
					response.sendRedirect(
							request.getContextPath()
								+ "/multivote/alert.jsp?errormsg=文件名为空，请重新上载!");
					
				
				}

		} catch (Exception e) {
			e.printStackTrace();
			handleError(e);
			
		} finally{
			
			if(conn!=null){
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
		}
		
		
	}
	
	
	
}


