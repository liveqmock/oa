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

public class UpdateArticalOptionServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   	
    	Connection conn = null;
        //获得信息内容


		// 保存发送文件信息状态
    	String mainid = request.getParameter("mainid")==null?"-1":request.getParameter("mainid");
    	String title = request.getParameter("title");
    	String	optionid=request.getParameter("optionid")==null?"-1":request.getParameter("optionid");
        String order = request.getParameter("order");
        String content = request.getParameter("content");
        
        try {
        	
            
        	
        	
//            CurrentUser user = new CurrentUser();
            //取得用户信息及初始化用到的数据
        	HttpSession usersession = request.getSession();
        	String	uuid=usersession.getAttribute("votebbsuuid")==null?"":usersession.getAttribute("votebbsuuid").toString();
        	String	userid=usersession.getAttribute("votebbsuserid")==null?"":usersession.getAttribute("votebbsuserid").toString();

//            Context ctx = Context.getInstance();
//			UserInfo user = ctx.getCurrentLoginInfo();

//            String	userenname=user.getEnName();
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            BbsVoteHandler handler=new BbsVoteHandler(conn);
            
//            System.out.println("+++++++++++++++++UpdateArticalOptionServlet000000000000=");
            //写入信息表先取出
            
            BbsOptionsVO vo=new BbsOptionsVO();
            vo=handler.getOptionVO(optionid);
            
            vo.setOpTitle(title);
            vo.setOpId(Integer.valueOf(optionid));
            vo.setMainid(Integer.valueOf(mainid));
            vo.setOpContext(content);
            
            vo.setOpOrder(order);
//            System.out.println("+++++++++++++++++UpdateArticalOptionServlet11111111title="+title);
            //处理附件信息
            ServletContext context = this.getServletContext();
			char sep = File.separatorChar;
            /*AttachFileBean attachbean=new AttachFileBean();
            attachbean=optionUploads.getAttachFile();
            String	path=attachbean.getUploadUrl();
            String	attachname=attachbean.getFileOriginName();*/
            String	attachurl = getUploadFileFullName(request, "attachfile");
//            System.out.println("+++++++++++++++++UpdateArticalOptionServlet+++++attachurl.indexof="+attachurl.indexOf("null"));
            
			String attachname = getUploadOldFileName(request, "attachfile");
			if (attachname != null&&attachname.indexOf("null")<0) {
				int index = 0;
				index = attachname.lastIndexOf("\\");
				if (index != -1) {  
					attachname = attachname.substring(index + 1);  
					
				}
				
				StringBuffer attachpath =
					new StringBuffer(context.getRealPath(sep+"votebbs"+sep+"uploadfile"+sep));
				
				
	
				
				File uploadattachpath = new File(attachpath.toString());
				 
				if(!uploadattachpath.exists()){
					uploadattachpath.mkdirs();
				}
				String attachfilepath = attachpath.append(userid).append(System.currentTimeMillis()).append(attachname).toString();
//				System.out.println("++++++++++UpdateArticalOptionServlet++++++++++attachfilepath=======++"+attachfilepath);
				StringBuffer	attachnewname=new StringBuffer(userid+System.currentTimeMillis()+attachname);
				InputStream attachins = new FileInputStream(attachurl);
				FileUtil.copy(attachins,new File(attachfilepath));
//				System.out.println("++++++++++UpdateArticalOptionServlet++++++++++attachfilepath=======++"+attachfilepath);
				//设置图片VO
				InputStream picinputstream=new FileInputStream(attachfilepath);
				vo.setOpAccessory(picinputstream);
				vo.setOpAccessoryname(attachnewname.toString());
				
			}
            //处理图片
            String	picurl = getUploadFileFullName(request, "optionpic");
//            System.out.println("+++++++++++++++++UpdateArticalOptionServlet+++++picurl="+picurl);
            
			String filename = getUploadOldFileName(request, "optionpic");
			if (filename != null&&filename.indexOf("null")<0) {
				int index = 0;
				index = filename.lastIndexOf("\\");
				if (index != -1) {  
					filename = filename.substring(index + 1);  
					
				}
				
				StringBuffer picpath =
					new StringBuffer(context.getRealPath(sep+"votebbs"+sep+"uploadpic"+sep));
				
				
	
				
				File uploadpath = new File(picpath.toString());
				if(!uploadpath.exists()){
					uploadpath.mkdirs();
				}
				String picfilepath = picpath.append(userid).append(System.currentTimeMillis()).toString();
//				System.out.println("++++++++++UpdateArticalOptionServlet++++++++++picpath=======++"+picfilepath);
				StringBuffer	picnewname=new StringBuffer(userid+System.currentTimeMillis());
				InputStream ins = new FileInputStream(picurl);
				FileUtil.copy(ins,new File(picfilepath));
//				System.out.println("++++++++++UpdateArticalOptionServlet++++++++++picfilepath=======++"+picfilepath);
				//设置图片VO
				InputStream picinputstream=new FileInputStream(picfilepath);
				vo.setOpPicblob(picinputstream);
				vo.setOpPic(picnewname.toString());
				
			}
			
            handler.updateOption(vo);
//            System.out.println("+++++++++++++++++UpdateArticalOptionServlet55555");    
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
