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
 * �ϴ�����
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
							+ "/multivote/alert.jsp?errormsg=��ȡ�ļ�·������");
			}
			// �жϸ����ܺ��Ƿ����10M

			String filename = getUploadOldFileName(request, "attfile");

			System.out.println("++++++++++MVoteImportVoteUserServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// �õ����ص��ļ�����
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						
					}
					System.out.println("++++++++++MVoteImportVoteUserServlet+++filename===+++++++"+filename);
					// ----------�����ص��ļ�ת�Ƶ�ָ�����ʼ�����Ŀ¼��----------------
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
//					 ----------------------��ȡ�ļ��ļ�---------------------------
					System.out.println("��ȡ�ļ��ļ� begin");
					
					//List implist=new ArrayList();
					//List implist2=new ArrayList();
//					ȡ��excel���е�������ֵ
					handler.impVoteUserReadExcel(filepath,planid);
					System.out.println("������ݵ���");
					//Iterator impitr=implist.iterator();
					
					
						
					response.sendRedirect(request.getContextPath()
									+ "/servlet/MVoteUserManagerListServlet?planid="+planid);
						
					
					
					System.out.println("��ȡ�ļ��ļ� end");
					
					
					
					
				}else{   // file isn't exist
					
					response.sendRedirect(
							request.getContextPath()
								+ "/multivote/alert.jsp?errormsg=�ļ���Ϊ�գ�����������!");
					
				
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


