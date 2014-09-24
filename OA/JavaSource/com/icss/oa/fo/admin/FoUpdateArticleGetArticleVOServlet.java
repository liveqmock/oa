/*
 * Created on 2004-5-14
 */
package com.icss.oa.fo.admin;



import java.io.IOException;


import java.sql.Connection;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoPlanVO;

public class FoUpdateArticleGetArticleVOServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入FoUpdateArticleGetArticleVOServlet");
    	Integer artId = request.getParameter("artId")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("artId"));
    	System.out.println("artId ="+request.getParameter("artId"));
    	
    	Connection conn = null;
    	
        try {
        	
            //初始化用到的数据
        	
        	
//            CurrentUser user = new CurrentUser();
//            String currentUserId = user.getId();
//            String	currentUserName=user.getName();
        	
        	
        	/*HttpSession usersession = request.getSession();

        	String	uuid=usersession.getAttribute("votebbsuuid")==null?"":usersession.getAttribute("votebbsuuid").toString();
        	String	userid=usersession.getAttribute("votebbsuserid")==null?"":usersession.getAttribute("votebbsuserid").toString();
        	String	username=usersession.getAttribute("votebbsusercnname")==null?"":usersession.getAttribute("votebbsusercnname").toString();*/
        
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            FoLiuHandler handler=new FoLiuHandler(conn);
            FoArticalVO foartvo = handler.getArticleVo(artId);          
            System.out.println("artid="+foartvo.getArtId());
            System.out.println("arttitle="+foartvo.getArtTitle());
            request.setAttribute("foartvo",foartvo);
            
            this.forward(request, response, "/fo/FoUpdateArticle.jsp?");
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
