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
import com.icss.oa.multivote.vo.VoteItemVO;


public class MVoteAddItemServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("����MVoteAddItemServlet*********");
    	//ȡ�ô���ѡ��������tableID
    	Integer tableId=request.getParameter("tableId")==null?new Integer(-1):Integer.valueOf(request.getParameter("tableId"));  	
   	
    	Connection conn = null;
 
    	//ȡ����mVoteAddItem.jspҳ������ӵĸ�ѡ�����Ϣ
        String itemName = request.getParameter("itemName");
      
        System.out.println("�����Ѿ�ȡ��");
        System.out.println("itemName="+itemName);
           
        try {
        	
        	 //��ʼ���õ�������
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            
            
            
            VoteItemVO itemvo=new VoteItemVO();//��ʼ��һ��VoteItemVO,���������ѡ����Ϣ
            //ΪVO��ֵ
            itemvo.setTableId(tableId);
            itemvo.setItemName(itemName);          
            handler.addItem(itemvo);
      
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
