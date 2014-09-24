/*
 * Created on 2004-5-14
 */
package com.icss.oa.fo.admin;



import java.io.IOException;


import java.sql.Connection;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;

import com.icss.oa.util.CurrentUser;
import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.fo.vo.FoPlanVO;

public class FoAddPlanServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("进入FoAddPlanServlet*********");
    	Connection conn = null;
        //获得jsp页面添加的计划信息
  
    	Integer planNum=("").equals(request.getParameter("planNum"))?new Integer(-1):Integer.valueOf(request.getParameter("planNum"));
  
        String planScale = request.getParameter("planScale");
        String planName = request.getParameter("planName");
        String planDesc = request.getParameter("planDesc");
        String planOrg = request.getParameter("planOrg");
        System.out.println("参数取完planScale---planOrg");
        System.out.println("planName="+planName);
        System.out.println("planDesc="+planDesc);
        System.out.println("planOrg="+planOrg);
        
        Integer planPersonnum=("").equals(request.getParameter("planPersonnum"))?new Integer(-1):Integer.valueOf(request.getParameter("planPersonnum"));
        Integer planNumhigh=("").equals(request.getParameter("planNumhigh"))?new Integer(-1):Integer.valueOf(request.getParameter("planNumhigh"));
        Integer planNumlow=("").equals(request.getParameter("planNumlow"))?new Integer(-1):Integer.valueOf(request.getParameter("planNumlow"));
        Integer planStatus=("").equals(request.getParameter("planStatus"))?new Integer(-1):Integer.valueOf(request.getParameter("planStatus"));
       
        System.out.println("参数已经全部取完");
        System.out.println("planStatus="+planStatus.toString());
        
        try {
        	
        	 //初始化用到的数据
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            FoLiuHandler handler=new FoLiuHandler(conn);
            
            
            //写入信息表
            Long CurrentTime = new Long(System.currentTimeMillis());
             
            FoPlanVO planvo=new FoPlanVO();//初始化一个FoPlanVO,用以保存待添加的计划信息
            //为FoPlanVO赋值
            planvo.setPlanNum(planNum);
            planvo.setPlanScale(planScale);
            planvo.setPlanName(planName);
            planvo.setPlanDesc(planDesc);
            planvo.setPlanOrg(planOrg);
            planvo.setPlanPersonnum(planPersonnum);
            planvo.setPlanNumlow(planNumlow);
            planvo.setPlanNumhigh(planNumhigh);
            planvo.setPlanStatus(planStatus);
            handler.addPlan(planvo);//添加计划
               
            
            this.forward(request, response, "/servlet/FoPlanManagerListServlet");
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
