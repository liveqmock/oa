/*
 * Created on 2004-5-14
 */
package com.icss.oa.multivote.admin;



import java.io.IOException;


import java.sql.Connection;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.multivote.handler.VoteLiuHandler;
import com.icss.oa.multivote.vo.VoteArticleVO;


public class MVoteAddArticleServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("进入MVoteAddArticleServlet*********");
    	//取得待加库所属的tableID
    	Integer tableId=request.getParameter("tableId")==null?new Integer(-1):Integer.valueOf(request.getParameter("tableId"));  	
   	
    	Connection conn = null;
 
    	//取得在mVoteAddArticle.jsp页面中添加的该库的信息
        String artTitle = request.getParameter("artTitle");
      
        System.out.println("参数已经取完");
        System.out.println("artTitle="+artTitle);
           
        try {
        	
        	 //初始化用到的数据
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            
            
            
            VoteArticleVO artvo=new VoteArticleVO();//初始化一个VoteArticleVO,用来保存该库信息
            //为VO赋值
            artvo.setTableId(tableId);          
            artvo.setArtTitle(artTitle);
            handler.addArticle(artvo);
      
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
