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


public class MVoteUpdateItemServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入MVoteUpdateItemServlet");
    	System.out.println("itemId="+request.getParameter("itemId"));
       	Connection conn = null;
        //获得待更新信息内容
    	Integer itemId=request.getParameter("itemId")==null?new Integer(-1):Integer.valueOf(request.getParameter("itemId"));
    	String itemName=request.getParameter("itemName");

    	System.out.println("itemId="+itemId.toString()); 	
    	System.out.println("itemName="+itemName);

        try {
        	
            //初始化用到的数据
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
           
            //写入信息表
           
            VoteItemVO voteitemvo=new VoteItemVO();
            voteitemvo = handler.getItemVo(itemId);
            
            String tableId = voteitemvo.getTableId().toString();
            voteitemvo.setItemId(itemId);
            voteitemvo.setItemName(itemName);
            
            handler.updateItem(voteitemvo);
         
            this.forward(request, response, "/servlet/MVoteUpdateItemGetItemListServlet?tableId="+tableId);
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
