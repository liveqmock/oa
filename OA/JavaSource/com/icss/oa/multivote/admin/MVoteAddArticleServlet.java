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
    	
    	System.out.println("����MVoteAddArticleServlet*********");
    	//ȡ�ô��ӿ�������tableID
    	Integer tableId=request.getParameter("tableId")==null?new Integer(-1):Integer.valueOf(request.getParameter("tableId"));  	
   	
    	Connection conn = null;
 
    	//ȡ����mVoteAddArticle.jspҳ������ӵĸÿ����Ϣ
        String artTitle = request.getParameter("artTitle");
      
        System.out.println("�����Ѿ�ȡ��");
        System.out.println("artTitle="+artTitle);
           
        try {
        	
        	 //��ʼ���õ�������
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            
            
            
            VoteArticleVO artvo=new VoteArticleVO();//��ʼ��һ��VoteArticleVO,��������ÿ���Ϣ
            //ΪVO��ֵ
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
