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
 * �ϴ�����
 */
public class FoArtContentUploadServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		//ȡ�ø�������ƻ�ID,���ظ�����ݵĸ��ID
		Integer planPlanid=request.getParameter("planPlanid")==null?new Integer(-1):new Integer(request.getParameter("planPlanid"));
		Integer artId=request.getParameter("artId")==null?new Integer(-1):new Integer(request.getParameter("artId"));
		System.out.println("����FoArtContentUploadServlet*********planid="+planPlanid.toString()+"*******artid="+artId.toString());
		
		Connection conn = null;
		
		try {
			
			String url = ""; 
				
			
			try{
				url = getUploadFileFullName(request, "uploadartcontent");//ȡ�ñ��������ļ���ȫ��(·��+�ļ���)
				System.out.println("mail_url = "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/fo/FoUploadArticleContent.jsp?planPlanid="
							+ planPlanid.toString()+"&artId="+artId.toString());
			}
			// �жϸ����ܺ��Ƿ����10M
			File file = new File(url);
			String filename = getUploadOldFileName(request, "uploadartcontent");//ȡ�ñ��������ļ����ļ���
			String filetype = null;
			
			FileTypeUtil fileutil = FileTypeUtil.getInstance();
			System.out.println("++++++++++FoArtContentUploadServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// �õ����ص��ļ�����
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
						
						// ��ʱ�Ĵ���
						// String extname = fileutil.getFileExtension(filename);
						// int length = 0;
						// if(filename.length()>20+1+extname.length()){
						// filename =
						// filename.substring(0,16).concat("(�ض�)").concat(".").concat(extname);
						// }
					}
					System.out.println("filenameold="+filename);
					System.out.println("filetype="+filetype);
					// ----------�����ص��ļ�ת�Ƶ�ָ�����ʼ�����Ŀ¼��----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"fo"+sep+"article"+sep));//����Ŀ¼(����/fo/article)
					Context ctx = Context.getInstance();
					
					//��art+ʱ����Ϊ�������ϵ��ļ���
					String	filenamenew= "art"+System.currentTimeMillis()+filetype;
					
					System.out.println("++++++++++FoArtContentUploadServlet++++++++++path=======++"+path);
					File uploadpath = new File(path.toString());
					// System.out.println("sunchuanting_path "+path.toString());
					if(!uploadpath.exists()){
						uploadpath.mkdirs();//�������Ŀ¼�����ڣ��򴴽���Ŀ¼
					}
					
					
					filename = filenamenew;//�µ��ļ���	
					System.out.println("filenamenew="+filename);
					String filepath = path.append(filename).toString();		
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));
					System.out.println("++++++++++FoArtContentUploadServlet++++++++++filepath=======++"+filepath);
					
					//�����ݿ�art_memo�ֶ������Ӧ�ĸ�����ݵ��ļ���
					try {
					
						conn = this.getConnection(Globals.DATASOURCEJNDI);
			            FoLiuHandler handler=new FoLiuHandler(conn);
			            FoArticalVO foartvo = handler.getArticleVo(artId);
			            
			            foartvo.setArtMemo(filename);
			            
			            //System.out.println("artid="+foartvo.getArtId());
			            //System.out.println("arttitle="+foartvo.getArtTitle());
			            handler.updateArticle(foartvo);//���¸ø��
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
					
					// ����
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


