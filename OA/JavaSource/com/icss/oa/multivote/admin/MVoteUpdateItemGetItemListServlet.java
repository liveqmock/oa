/*
 * Created on 2004-5-14
 */
package com.icss.oa.multivote.admin;



import java.io.IOException;


import java.sql.Connection;
import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.multivote.handler.VoteLiuHandler;
import com.icss.oa.multivote.vo.VoteItemVO;
import com.icss.oa.multivote.vo.VotePlanVO;

public class MVoteUpdateItemGetItemListServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入MVoteUpdateItemGetItemListServlet");
    	Integer tableId = request.getParameter("tableId")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("tableId"));
    	System.out.println("tableId ="+tableId);
    	
    	List list_item = null;
    	Connection conn = null;
    	
        try {
        	
            //初始化用到的数据
 
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            list_item = handler.getvoteItemList(tableId);
                      
 
            request.setAttribute("list_item",list_item);
            System.out.println("+++++++++++MVoteUpdateItemGetItemListServlet++++++list_item++"+list_item.size());
            
            this.forward(request, response, "/multivote/mVoteUpdateItem.jsp?tableId="+tableId);
            
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
