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
import com.icss.oa.multivote.vo.VoteItemVO;
import com.icss.oa.multivote.vo.VotePlanVO;

public class MVoteUpdateItemGetItemVOServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入MVoteUpdateItemGetItemVOServlet");
    	Integer itemId = request.getParameter("itemId")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("itemId"));
    	System.out.println("itemId ="+request.getParameter("itemId"));
    	
    	Connection conn = null;
    	
        try {
        	
            //初始化用到的数据
 
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            VoteItemVO voteitemvo = handler.getItemVo(itemId);          
            System.out.println("itemId="+voteitemvo.getItemId());
            System.out.println("itemName="+voteitemvo.getItemName());
            request.setAttribute("voteitemvo",voteitemvo);
            
            this.forward(request, response, "/multivote/mVoteUpdateSingleItem.jsp?");
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
