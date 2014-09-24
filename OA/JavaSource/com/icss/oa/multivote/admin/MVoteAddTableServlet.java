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

import com.icss.oa.util.CurrentUser;
import com.icss.oa.multivote.handler.VoteLiuHandler;
import com.icss.oa.multivote.vo.VoteTableVO;


public class MVoteAddTableServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("����MVoteAddTableServlet*********");
    	String planPlanid=request.getParameter("planPlanid");//ȡ�ô��ӱ��������ļƻ�ID
    	System.out.println("planPlanid="+planPlanid);
    	Integer i_planPlanid=planPlanid==null?new Integer(-1):Integer.valueOf(planPlanid);
    	
    	Connection conn = null;
 
    	//Ϊ�������������Ը���ֵ
        String tableTime = null;
        String tableMemo = null;
        String tableTitle = null;
        String tableTail = null;
        String tableDesc = null;
        String tableRowname = null;
        String tableColname = null;
  
        
        try {
        	
        	 //��ʼ���õ�������
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            
            
            //д����Ϣ��
            Long CurrentTime = new Long(System.currentTimeMillis());
             
            VoteTableVO tablevo=new VoteTableVO();//��ʼ��һ��VoteTableVO,��������ñ�����Ϣ
            //ΪVO��ֵ
            tablevo.setPlanPlanid(i_planPlanid);
            tablevo.setTableTime(tableTime);
            tablevo.setTableMemo(tableMemo);
            tablevo.setTableTitle(tableTitle);
            tablevo.setTableTail(tableTail);
            tablevo.setTableDesc(tableDesc);
            tablevo.setTableRowname(tableRowname);
            tablevo.setTableColname(tableColname);
            
            handler.addTable(tablevo);
              
            this.forward(request, response, "/servlet/MVoteTableServlet");
            
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
