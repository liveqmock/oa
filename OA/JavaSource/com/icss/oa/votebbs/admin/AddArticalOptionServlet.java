/*
 * Created on 2004-5-14
 */
package com.icss.oa.votebbs.admin;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


import java.sql.Connection;



import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.FileUtil;
import com.icss.j2ee.util.Globals;


import com.icss.oa.votebbs.handler.AttachFileBean;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.handler.OptionUploadBean;
import com.icss.oa.votebbs.vo.BbsMainarticleVO;
import com.icss.oa.votebbs.vo.BbsOptionsVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;

public class AddArticalOptionServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   	
    	Connection conn = null;
        //获得信息内容
    	HttpSession session = request.getSession();

		// 保存发送文件信息状态
		OptionUploadBean optionUploads = OptionUploadBean.getInstanceFromSession(session);
    	String mainid = request.getParameter("mainid")==null?"-1":request.getParameter("mainid");
    	String title = request.getParameter("title");
       
        String order = request.getParameter("order");
        String content = request.getParameter("content");
        try {
        	
            
        	
        	HttpSession usersession = request.getSession();
        	String	uuid=usersession.getAttribute("votebbsuuid")==null?"":usersession.getAttribute("votebbsuuid").toString();
        	String	userid=usersession.getAttribute("votebbsuserid")==null?"":usersession.getAttribute("votebbsuserid").toString();

//            CurrentUser user = new CurrentUser();
            //取得用户信息及初始化用到的数据
//            Context ctx = Context.getInstance();
//			UserInfo user = ctx.getCurrentLoginInfo();

//            String	userenname=user.getEnName();
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            BbsVoteHandler handler=new BbsVoteHandler(conn);
            
//            System.out.println("+++++++++++++++++AddArticalOptionServlet000000000000=");
            //写入信息表
             
            BbsOptionsVO vo=new BbsOptionsVO();
            vo.setOpTitle(title);
            vo.setMainid(Integer.valueOf(mainid));
            vo.setOpContext(content);
            
            vo.setOpOrder(order);
            vo.setOpNum("0");
            vo.setOpGood("0");
            vo.setOpMid("0");
            vo.setOpBad("0");
//            System.out.println("+++++++++++++++++AddArticalOptionServlet11111111title="+title);
            //处理附件信息
            ServletContext context = this.getServletContext();
			char sep = File.separatorChar;
            /*AttachFileBean attachbean=new AttachFileBean();
            attachbean=optionUploads.getAttachFile();
            String	path=attachbean.getUploadUrl();
            String	attachname=attachbean.getFileOriginName();*/
            String	attachurl = getUploadFileFullName(request, "attachfile");
//            System.out.println("+++++++++++++++++AddArticalOptionServlet+++++attachurl="+attachurl);
            
			String attachname = getUploadOldFileName(request, "attachfile");
			if (attachname != null) {
				int index = 0;
				index = attachname.lastIndexOf("\\");
				if (index != -1) {  
					attachname = attachname.substring(index + 1);  
					
				}
				
				StringBuffer attachpath =
					new StringBuffer(context.getRealPath(sep+"votebbs"+sep+"uploadfile"+sep));
				
				
	
				
				File uploadattachpath = new File(attachpath.toString());
				// System.out.println("sunchuanting_path "+path.toString());
				if(!uploadattachpath.exists()){
					uploadattachpath.mkdirs();
				}
				String attachfilepath = attachpath.append(userid).append(System.currentTimeMillis()).append(attachname).toString();
//				System.out.println("++++++++++AddArticalOptionServlet++++++++++attachfilepath=======++"+attachfilepath);
				StringBuffer	attachnewname=new StringBuffer(userid+System.currentTimeMillis()+attachname);
				InputStream attachins = new FileInputStream(attachurl);
				FileUtil.copy(attachins,new File(attachfilepath));
//				System.out.println("++++++++++AddArticalOptionServlet++++++++++attachfilepath=======++"+attachfilepath);
				//设置图片VO
				InputStream picinputstream=new FileInputStream(attachfilepath);
				vo.setOpAccessory(picinputstream);
				vo.setOpAccessoryname(attachnewname.toString());
				
			}
            //处理图片
            String	picurl = getUploadFileFullName(request, "optionpic");
//            System.out.println("+++++++++++++++++AddArticalOptionServlet+++++picurl="+picurl);
            
			String filename = getUploadOldFileName(request, "optionpic");
			if (filename != null) {
				int index = 0;
				index = filename.lastIndexOf("\\");
				if (index != -1) {  
					filename = filename.substring(index + 1);  
					
				}
				
				StringBuffer picpath =
					new StringBuffer(context.getRealPath(sep+"votebbs"+sep+"uploadpic"+sep));
				
				
	
				
				File uploadpath = new File(picpath.toString());
				// System.out.println("sunchuanting_path "+path.toString());
				if(!uploadpath.exists()){
					uploadpath.mkdirs();
				}
				String picfilepath = picpath.append(userid).append(System.currentTimeMillis()).toString();
//				System.out.println("++++++++++AddArticalOptionServlet++++++++++picpath=======++"+picfilepath);
				StringBuffer	picnewname=new StringBuffer(userid+System.currentTimeMillis());
				InputStream ins = new FileInputStream(picurl);
				FileUtil.copy(ins,new File(picfilepath));
//				System.out.println("++++++++++AddArticalOptionServlet++++++++++picfilepath=======++"+picfilepath);
				//设置图片VO
				InputStream picinputstream=new FileInputStream(picfilepath);
				vo.setOpPicblob(picinputstream);
				vo.setOpPic(picnewname.toString());
				
			}
			
            handler.addOption(vo);
//            System.out.println("+++++++++++++++++AddArticalOptionServlet555555555");    
            response.sendRedirect("ArticleOptionListServlet?mainid="+mainid);

//            this.forward(request, response, "/servlet/ArticleOptionListServlet?mainid="+mainid);
        } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }

    }
}
