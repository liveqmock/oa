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
import com.icss.oa.multivote.vo.VoteTableVO;


public class MVoteUpdateTailServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("����MVoteUpdateTailServlet");
    	System.out.println("tableId="+request.getParameter("tableId"));
       	Connection conn = null;
        //��ô�������Ϣ����
    	Integer tableId=request.getParameter("tableId")==null?new Integer(-1):Integer.valueOf(request.getParameter("tableId"));
    	String tableTail=request.getParameter("tableTail");
    	String tableTitle=request.getParameter("tableTitle");
    	System.out.println("tableId="+tableId.toString()); 	
    	System.out.println("tableTail="+tableTail+"tableTitle="+tableTitle);

        try {
        	
            //��ʼ���õ�������
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
           
            //д����Ϣ��
           
            VoteTableVO votetablevo=new VoteTableVO();
            votetablevo=handler.getTableVo(tableId);
            
            votetablevo.setTableId(tableId);
            votetablevo.setTableTail(tableTail);
            votetablevo.setTableTitle(tableTitle);
            
            handler.updateTable(votetablevo);
         
            this.forward(request, response, "/servlet/MVotePlanArticleListServlet?tableId="+tableId);
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
