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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;

import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoInputvalueVO;
import com.icss.oa.fo.vo.FoInputvoteVO;
import com.icss.oa.fo.vo.FoInputvotevalueVO;
import com.icss.oa.fo.vo.FoPlanVO;
import com.icss.oa.log.handler.SendFileBean;
import com.icss.oa.log.handler.AttachFileBean;
import com.icss.oa.util.FileTypeUtil;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

/**
 * @author Administrator
 * 
 * �ϴ�����
 */
public class FoImportVoteUserServlet extends ServletBase {

	protected void performTask(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		Integer planid=request.getParameter("planid")==null?new Integer(-1):new Integer(request.getParameter("planid"));
        
		Connection conn = null;
		try {
			conn = getConnection(com.icss.j2ee.util.Globals.DATASOURCEJNDI); 
			String url = ""; 
			FoHandler handler=new FoHandler(conn);
			
			try{
				url = getUploadFileFullName(request, "attfile");
				System.out.println("FoImportInputVoteServlet attfile=  "+url);
			}catch(Exception e){
				response.sendRedirect(
						request.getContextPath()
							+ "/fo/alert.jsp?errormsg=��ȡ�ļ�·������");
			}
			// �жϸ����ܺ��Ƿ����10M
			File file = new File(url);
			String filename = getUploadOldFileName(request, "attfile");
			
			FileTypeUtil fileutil = FileTypeUtil.getInstance();
			System.out.println("++++++++++FoImportVoteUserServlet+++filename===+++++++"+filename);
			if (filename != null) {
					// �õ����ص��ļ�����
					int index = 0;
					index = filename.lastIndexOf("\\");
					if (index != -1) {  
						filename = filename.substring(index + 1);  
						
					}
					System.out.println("++++++++++FoImportVoteUserServlet+++filename===+++++++"+filename);
					// ----------�����ص��ļ�ת�Ƶ�ָ�����ʼ�����Ŀ¼��----------------
					conn = this.getConnection(Globals.DATASOURCEJNDI);
					ServletContext context = this.getServletContext();
					char sep = File.separatorChar;
					StringBuffer path =
						new StringBuffer(context.getRealPath(sep+"fo"+sep+"upload"+sep));
					
					path.append("user").append(System.currentTimeMillis()).append(File.separator);
					System.out.println("++++++++++FoImportVoteUserServlet++++++++++path=======++"+path);
					File uploadpath = new File(path.toString());
					// System.out.println("sunchuanting_path "+path.toString());
					if(!uploadpath.exists()){
						uploadpath.mkdirs();
					}
					String filepath = path.append(filename).toString();
					InputStream ins = new FileInputStream(url);
					FileUtil.copy(ins,new File(filepath));
					System.out.println("++++++++++FoImportVoteUserServlet++++++++++filepath=======++"+filepath);
					
					
					
					
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
									+ "/servlet/FoUserManagerListServlet?planid="+planid);
						
					
					
					System.out.println("��ȡ�ļ��ļ� end");
					
					
					
					
				}else{   // file isn't exist
					
					response.sendRedirect(
							request.getContextPath()
								+ "/fo/alert.jsp?errormsg=�ļ���Ϊ�գ�����������!");
					
				
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


