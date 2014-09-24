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
 * 上传附件
 */
public class FoArtContentUploadServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		//取得稿件所属计划ID,上载稿件内容的稿件ID
		Integer planPlanid=request.getParameter("planPlanid")==null?new Integer(-1):new Integer(request.getParameter("planPlanid"));
		Integer artId=request.getParameter("artId")==null?new Integer(-1):new Integer(request.getParameter("artId"));
		System.out.println("进入FoArtContentUploadServlet*********planid="+planPlanid.toString()+"*******artid="+artId.toString());
		
		Connection conn = null;
		
		try {
			
			String url = ""; 
				
			
			try{
				url = getUploadFileFullName(request, "uploadartcontent");//取得本地上载文件的全名(路径+文件名)
				System.out.println("mail_url = "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/fo/FoUploadArticleContent.jsp?planPlanid="
							+ planPlanid.toString()+"&artId="+artId.toString());
			}
			// 判断附件总和是否大于10M
			File file = new File(url);
			String filename = getUploadOldFileName(request, "uploadartcontent");//取得本地上载文件的文件名
			String filetype = null;
			
			FileTypeUtil fileutil = FileTypeUtil.getInstance();
			System.out.println("++++++++++FoArtContentUploadServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// 得到上载的文件名称
					int index = 0;
					int indexdot = 0;
					index = filename.lastIndexOf("\\");
					
					indexdot = filename.lastIndexOf(".");
					System.out.println("indexdot="+indexdot);
					filetype = filename.substring(indexdot,filename.length());
					System.out.println("filetype="+filetype);
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						System.out.println("filenameold="+filename);
						
						// 暂时的处理
						// String extname = fileutil.getFileExtension(filename);
						// int length = 0;
						// if(filename.length()>20+1+extname.length()){
						// filename =
						// filename.substring(0,16).concat("(截断)").concat(".").concat(extname);
						// }
					}
					System.out.println("filenameold="+filename);
					System.out.println("filetype="+filetype);
					// ----------把上载的文件转移到指定的邮件下载目录下----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"fo"+sep+"article"+sep));//上载目录(……/fo/article)
					Context ctx = Context.getInstance();
					
					//以art+时间作为服务器上的文件名
					String	filenamenew= "art"+System.currentTimeMillis()+filetype;
					
					System.out.println("++++++++++FoArtContentUploadServlet++++++++++path=======++"+path);
					File uploadpath = new File(path.toString());
					// System.out.println("sunchuanting_path "+path.toString());
					if(!uploadpath.exists()){
						uploadpath.mkdirs();//如果上载目录不存在，则创建新目录
					}
					
					
					filename = filenamenew;//新的文件名	
					System.out.println("filenamenew="+filename);
					String filepath = path.append(filename).toString();		
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));
					System.out.println("++++++++++FoArtContentUploadServlet++++++++++filepath=======++"+filepath);
					
					//向数据库art_memo字段添加相应的稿件内容的文件名
					try {
					
						conn = this.getConnection(Globals.DATASOURCEJNDI);
			            FoLiuHandler handler=new FoLiuHandler(conn);
			            FoArticalVO foartvo = handler.getArticleVo(artId);
			            
			            foartvo.setArtMemo(filename);
			            
			            //System.out.println("artid="+foartvo.getArtId());
			            //System.out.println("arttitle="+foartvo.getArtTitle());
			            handler.updateArticle(foartvo);//更新该稿件
			            response.sendRedirect(request.getContextPath()+"/servlet/FoPlanArticalListServlet?planPlanid="+planPlanid.toString());
			            //this.forward(request, response, "/servlet/FoPlanArticalListServlet?planPlanid="+planPlanid);
			            
					}catch(Exception e){
						e.printStackTrace();
					}
					
					if(ins!= null){
						try {
							ins.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
				
					
					
				}else{   // file isn't exist
					
					// 返回
						response.sendRedirect(
							request.getContextPath() + "/fo/FoUploadArticleContent.jsp?planPlanid="+planPlanid+"&artId="+artId.toString());
					
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


