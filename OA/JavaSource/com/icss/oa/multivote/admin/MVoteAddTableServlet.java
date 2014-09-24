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
    	
    	System.out.println("进入MVoteAddTableServlet*********");
    	String planPlanid=request.getParameter("planPlanid");//取得待加表样所属的计划ID
    	System.out.println("planPlanid="+planPlanid);
    	Integer i_planPlanid=planPlanid==null?new Integer(-1):Integer.valueOf(planPlanid);
    	
    	Connection conn = null;
 
    	//为新增表样的属性赋初值
        String tableTime = null;
        String tableMemo = null;
        String tableTitle = null;
        String tableTail = null;
        String tableDesc = null;
        String tableRowname = null;
        String tableColname = null;
  
        
        try {
        	
        	 //初始化用到的数据
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            
            
            //写入信息表
            Long CurrentTime = new Long(System.currentTimeMillis());
             
            VoteTableVO tablevo=new VoteTableVO();//初始化一个VoteTableVO,用来保存该表样信息
            //为VO赋值
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
