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

public class AddArticalServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	   	
    	Connection conn = null;
        //�����Ϣ����
    	String title = request.getParameter("title");
        String status = request.getParameter("status");
        String type = request.getParameter("type");
        String content = request.getParameter("content");
        String morder = request.getParameter("morder");
        try {
        	
            //��ʼ���õ�������
        	HttpSession usersession = request.getSession();
        	String	uuid=usersession.getAttribute("votebbsuuid")==null?"":usersession.getAttribute("votebbsuuid").toString();
        	String	userid=usersession.getAttribute("votebbsuserid")==null?"":usersession.getAttribute("votebbsuserid").toString();
        	String	username=usersession.getAttribute("votebbsusercnname")==null?"":usersession.getAttribute("votebbsusercnname").toString();
        	
//            CurrentUser user = new CurrentUser();
//            String currentUserId = user.getId();
//            String	currentUserName=user.getName();
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            BbsVoteHandler handler=new BbsVoteHandler(conn);
            
            
            //д����Ϣ��
            Long CurrentTime = new Long(System.currentTimeMillis());
             
            BbsMainarticleVO articalvo=new BbsMainarticleVO();
            articalvo.setTitle(title);
            articalvo.setContext(content);
            articalvo.setStatus(status);
            articalvo.setMOrder(morder);
            articalvo.setType(type);
            articalvo.setPublishtime(CurrentTime.toString());
            articalvo.setUploader(username);
            articalvo.setUploaduuid(uuid);           
            handler.addArtical(articalvo);
               
            
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
