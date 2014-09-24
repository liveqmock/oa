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


public class MVoteUpdateArticleServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入MVoteUpdateArticleServlet");
    	System.out.println("artId="+request.getParameter("artId"));
       	Connection conn = null;
        //获得待更新信息内容
    	Integer artId=request.getParameter("artId")==null?new Integer(-1):Integer.valueOf(request.getParameter("artId"));
    	String artTitle=request.getParameter("artTitle");

    	System.out.println("artId="+artId.toString()); 	
    	System.out.println("artTitle="+artTitle);

        try {
        	
            //初始化用到的数据
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
           
            //写入信息表
           
            VoteArticleVO votearticlevo=new VoteArticleVO();
            votearticlevo=handler.getArticleVo(artId);
            String tableId = votearticlevo.getTableId().toString();
            votearticlevo.setArtId(artId);
            votearticlevo.setArtTitle(artTitle);
            
            handler.updateArticle(votearticlevo);
         
            this.forward(request, response, "/servlet/MVoteUpdateArticleGetArticleListServlet?tableId="+tableId);
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
