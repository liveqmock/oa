/*
 * Created on 2004-5-14
 */
package com.icss.oa.votebbs.admin;



import java.io.IOException;


import java.sql.Connection;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;

import com.icss.oa.util.CurrentUser;
import com.icss.oa.votebbs.handler.BbsVoteHandler;
import com.icss.oa.votebbs.vo.BbsMainarticleVO;

public class UpdateMainArticalServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   	
    	Connection conn = null;
        //获得信息内容
    	Integer mainid=request.getParameter("mainid")==null?new Integer(-1):Integer.valueOf(request.getParameter("mainid"));
    	String title = request.getParameter("title");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String content = request.getParameter("content");
        String morder = request.getParameter("morder");
        try {
        	
            //初始化用到的数据
        	
        	
//            CurrentUser user = new CurrentUser();
//            String currentUserId = user.getId();
//            String	currentUserName=user.getName();
        	
        	
        	HttpSession usersession = request.getSession();

        	String	uuid=usersession.getAttribute("votebbsuuid")==null?"":usersession.getAttribute("votebbsuuid").toString();
        	String	userid=usersession.getAttribute("votebbsuserid")==null?"":usersession.getAttribute("votebbsuserid").toString();
        	String	username=usersession.getAttribute("votebbsusercnname")==null?"":usersession.getAttribute("votebbsusercnname").toString();
        
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            BbsVoteHandler handler=new BbsVoteHandler(conn);
            
            
            //写入信息表
            Long CurrentTime = new Long(System.currentTimeMillis());
            System.out.println("+++++++++++++++username1+++++++++"+username);   
            
            BbsMainarticleVO articalvo=new BbsMainarticleVO();
            articalvo.setMainid(mainid);
            articalvo.setTitle(title);
            articalvo.setContext(content);
            articalvo.setStatus(status);
            articalvo.setMOrder(morder);
            articalvo.setType(type);
            articalvo.setModifytime(CurrentTime.toString());
            articalvo.setUploader(username);
            articalvo.setUploaduuid(uuid);           
            handler.updateArtical(articalvo);
            System.out.println("+++++++++++++++username+++++++++"+username);   
            
            this.forward(request, response, "/servlet/ArticleManagerListServlet");
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
