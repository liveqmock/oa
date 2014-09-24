/*
 * Created on 2004-5-14
 */
package com.icss.oa.multivote.admin;



import java.io.IOException;


import java.sql.Connection;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.multivote.handler.VoteLiuHandler;
import com.icss.oa.multivote.vo.VoteArticleVO;
import com.icss.oa.multivote.vo.VotePlanVO;

public class MVoteUpdateArticleGetArticleVOServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入MVoteUpdateArticleGetArticleVOServlet");
    	Integer artId = request.getParameter("artId")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("artId"));
    	System.out.println("artId ="+request.getParameter("artId"));
    	
    	Connection conn = null;
    	
        try {
        	
            //初始化用到的数据
 
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            VoteArticleVO voteartvo = handler.getArticleVo(artId);          
            System.out.println("artid="+voteartvo.getArtId());
            System.out.println("arttitle="+voteartvo.getArtTitle());
            request.setAttribute("voteartvo",voteartvo);
            
            this.forward(request, response, "/multivote/mVoteUpdateSingleArticle.jsp?");
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
