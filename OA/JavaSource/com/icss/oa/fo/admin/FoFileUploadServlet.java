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
 * ������������Ϣ
 */
public class FoFileUploadServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		//ȡ�øø�������ƻ���ID��
		Integer planPlanid=request.getParameter("planPlanid")==null?new Integer(-1):new Integer(request.getParameter("planPlanid"));
		System.out.println("����FoFileUploadServlet*********planid="+planPlanid.toString());
		
		Connection conn = null;
		FoArticalVO[] foartvoarray=null;
		try {
			
			String url = ""; 
				
			
			try{
				url = getUploadFileFullName(request, "dataimp");//�õ����ص����ļ���ȫ����·��+�ļ�����
				System.out.println("mail_url = "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/fo/FoDataImp.jsp?planPlanid="
							+ planPlanid);
			}
			// �жϸ����ܺ��Ƿ����10M
			File file = new File(url);
			String filename = getUploadOldFileName(request, "dataimp");//�õ����ص����ļ����ļ���
			
			FileTypeUtil fileutil = FileTypeUtil.getInstance();
			System.out.println("++++++++++FoFileUploadServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// �õ����ص��ļ�����
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						// ��ʱ�Ĵ���
						// String extname = fileutil.getFileExtension(filename);
						// int length = 0;
						// if(filename.length()>20+1+extname.length()){
						// filename =
						// filename.substring(0,16).concat("(�ض�)").concat(".").concat(extname);
						// }
					}
					// ----------�����ص��ļ�ת�Ƶ�ָ�����ʼ�����Ŀ¼��----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"fo"+sep+"upload"+sep));//Ŀ��Ŀ¼(����/fo/upload)
					Context ctx = Context.getInstance();
					UserInfo user = ctx.getCurrentLoginInfo();
					String	username=user.getEnName();
					path.append("art").append(username).append(System.currentTimeMillis()).append(File.separator);//(����/fo/upload/art����)
					System.out.println("++++++++++FoFileUploadServlet++++++++++path=======++"+path);
					File uploadpath = new File(path.toString());
					// System.out.println("sunchuanting_path "+path.toString());
					if(!uploadpath.exists()){
						uploadpath.mkdirs();
					}
					String filepath = path.append(filename).toString();
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));//�����ļ�
					System.out.println("++++++++++FoFileUploadServlet++++++++++filepath=======++"+filepath);
					
					
					
					if(ins!= null){
						try {
							ins.close();
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println(e.getMessage());
						}
					}
				
					//�����ļ�
					System.out.println("��ʼ�����ļ�");
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
		            	System.out.println("���ݵ��� begin");
		            	foartvoarray = handler.dataImport2(file_name,planPlanid);//foartvoarray������excel�ļ��еĸ����Ϣ
		            	if(foartvoarray!=null){//ѭ�����ÿһ�������Ϣ
		            		for(int i=0;i<foartvoarray.length;i++){
		            		handler.addArticle(foartvoarray[i]);
		            		}
		            		System.out.println("���ݵ��� end");
		    			
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
		            	System.out.println("����="+ex.toString());
		            	throw new ServletException(ex);
		            }
					
				}else{   // file isn't exist
					
					// ����
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


