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
import com.icss.oa.multivote.vo.VotePlanVO;

public class MVoteAddPlanServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("����MVoteAddPlanServlet*********");
    	Connection conn = null;
        //���jspҳ����ӵļƻ���Ϣ
  
    	//Integer planNum=("").equals(request.getParameter("planNum"))?new Integer(-1):Integer.valueOf(request.getParameter("planNum"));
  
        //String planScale = request.getParameter("planScale");
        String planName = request.getParameter("planName");
        String planDesc = request.getParameter("planDesc");
        String planOrg = request.getParameter("planOrg");
        String planSeason = request.getParameter("planSeason");
        System.out.println("����ȡ��planScale---planOrg");
        System.out.println("planName="+planName);
        System.out.println("planDesc="+planDesc);
        System.out.println("planOrg="+planOrg);
        System.out.println("planSeason="+planSeason);
        
        
       // Integer planPersonnum=("").equals(request.getParameter("planPersonnum"))?new Integer(-1):Integer.valueOf(request.getParameter("planPersonnum"));
        Integer planNumhigh=("").equals(request.getParameter("planNumhigh"))?new Integer(-1):Integer.valueOf(request.getParameter("planNumhigh"));
       // Integer planNumlow=("").equals(request.getParameter("planNumlow"))?new Integer(-1):Integer.valueOf(request.getParameter("planNumlow"));
        Integer planStatus=("").equals(request.getParameter("planStatus"))?new Integer(-1):Integer.valueOf(request.getParameter("planStatus"));
       
        System.out.println("�����Ѿ�ȫ��ȡ��");
        System.out.println("planStatus="+planStatus.toString());
        
        try {
        	
        	 //��ʼ���õ�������
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            
            
            //д����Ϣ��
            Long CurrentTime = new Long(System.currentTimeMillis());
             
            VotePlanVO planvo=new VotePlanVO();//��ʼ��һ��VotePlanVO,���Ա������ӵļƻ���Ϣ
            //ΪFoPlanVO��ֵ
           // planvo.setPlanNum(planNum);
           // planvo.setPlanScale(planScale);
            planvo.setPlanName(planName);
            planvo.setPlanDesc(planDesc);
            planvo.setPlanOrg(planOrg);
          //  planvo.setPlanPersonnum(planPersonnum);
          //  planvo.setPlanNumlow(planNumlow);
            planvo.setPlanNumhigh(planNumhigh);
            planvo.setPlanSeason(planSeason);
            planvo.setPlanStatus(planStatus);
            handler.addPlan(planvo);//��Ӽƻ�
              
            
            
            this.forward(request, response, "/servlet/MVotePlanManagerListServlet");
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
