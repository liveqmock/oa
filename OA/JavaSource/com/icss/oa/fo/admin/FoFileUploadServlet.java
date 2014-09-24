/*
 * Created on 2004-5-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.fo.admin;        

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponseWrapper;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;



import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.util.FileTypeUtil;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import java.io.UnsupportedEncodingException;

/**
 * @author Administrator
 * 
 * 批量导入稿件信息
 */
public class FoFileUploadServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		//取得该稿件所属计划的ID号
		Integer planPlanid=request.getParameter("planPlanid")==null?new Integer(-1):new Integer(request.getParameter("planPlanid"));
		System.out.println("进入FoFileUploadServlet*********planid="+planPlanid.toString());
		
		Connection conn = null;
		FoArticalVO[] foartvoarray=null;
		try {
			
			String url = ""; 
				
			
			try{
				url = getUploadFileFullName(request, "dataimp");//得到本地导入文件的全名（路径+文件名）
				System.out.println("mail_url = "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/fo/FoDataImp.jsp?planPlanid="
							+ planPlanid);
			}
			// 判断附件总和是否大于10M
			File file = new File(url);
			String filename = getUploadOldFileName(request, "dataimp");//得到本地导入文件的文件名
			
			FileTypeUtil fileutil = FileTypeUtil.getInstance();
			System.out.println("++++++++++FoFileUploadServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// 得到上载的文件名称
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						// 暂时的处理
						// String extname = fileutil.getFileExtension(filename);
						// int length = 0;
						// if(filename.length()>20+1+extname.length()){
						// filename =
						// filename.substring(0,16).concat("(截断)").concat(".").concat(extname);
						// }
					}
					// ----------把上载的文件转移到指定的邮件下载目录下----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"fo"+sep+"upload"+sep));//目标目录(……/fo/upload)
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					String	username=user.getEnName();
					path.append("art").append(username).append(System.currentTimeMillis()).append(File.separator);//(……/fo/upload/art……)
					System.out.println("++++++++++FoFileUploadServlet++++++++++path=======++"+path);
					File uploadpath = new File(path.toString());
					// System.out.println("sunchuanting_path "+path.toString());
					if(!uploadpath.exists()){
						uploadpath.mkdirs();
					}
					String filepath = path.append(filename).toString();
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));//拷贝文件
					System.out.println("++++++++++FoFileUploadServlet++++++++++filepath=======++"+filepath);
					
					
					
					if(ins!= null){
						try {
							ins.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
				
					//解析文件
					System.out.println("开始解析文件");
					byte temp[]=null;
					try {
						temp=filepath.getBytes("iso-8859-1");
						
					}catch(UnsupportedEncodingException e){
						
						System.out.println(e.toString());
					}	
					String file_name=new String(temp);
					System.out.println("file_name"+file_name);
					
					conn = this.getConnection(Globals.DATASOURCEJNDI);
		            FoLiuHandler handler=new FoLiuHandler(conn);
		            try{
		            	System.out.println("数据导入 begin");
		            	foartvoarray = handler.dataImport2(file_name,planPlanid);//foartvoarray数组存放excel文件中的稿件信息
		            	if(foartvoarray!=null){//循环添加每一个稿件信息
		            		for(int i=0;i<foartvoarray.length;i++){
		            		handler.addArticle(foartvoarray[i]);
		            		}
		            		System.out.println("数据导入 end");
		    			
		    			//request.setAttribute("planPlanid",planPlanid.toString());
		            		System.out.println(getServletContext());
		            		response.sendRedirect(request.getContextPath()+"/servlet/FoPlanArticalListServlet?planPlanid="+planPlanid);
		            		
		            		//forward(request, response, "/fo/FoImportError.jsp");
		            	}else{
		            		//forward(request, response, "/servlet/FoPlanArticalListServlet?planPlanid"+planPlanid);
		            		response.sendRedirect(request.getContextPath()+"/fo/FoImportError.jsp");
		            	}
		    			//getServletContext().getRequestDispatcher("/FoPlanManagerListServlet").forward(request,response);
		            }catch(Exception ex){
		            	System.out.println("出错="+ex.toString());
		            	throw new ServletException(ex);
		            }
					
				}else{   // file isn't exist
					
					// 返回
						response.sendRedirect(
							request.getContextPath() + "/fo/FoDataImp.jsp?planPlanid="+planPlanid);
					
				}
				
			// else isn't bigger than 10M

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


