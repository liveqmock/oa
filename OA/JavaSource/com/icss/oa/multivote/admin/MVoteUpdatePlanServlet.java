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
import com.icss.oa.multivote.vo.VotePlanVO;

public class MVoteUpdatePlanServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入MVoteUpdatePlanServlet");
    	System.out.println(request.getParameter("planPlanid"));
    	Connection conn = null;
        //获得待更新信息内容
    	Integer planPlanid=request.getParameter("planPlanid").equals("")?new Integer(-1):Integer.valueOf(request.getParameter("planPlanid"));
    	String planName=request.getParameter("planName");
    	//Integer planNum=request.getParameter("planNum").equals("")?new Integer(-1):Integer.valueOf(request.getParameter("planNum"));
    	//String  planScale = request.getParameter("planScale");
    	//Integer planNumlow=request.getParameter("planNumlow").equals("")?new Integer(-1):Integer.valueOf(request.getParameter("planNumlow"));
    	String  planSeason=request.getParameter("planSeason");
    	Integer planNumhigh=request.getParameter("planNumhigh").equals("")?new Integer(-1):Integer.valueOf(request.getParameter("planNumhigh"));
    	Integer planStatus=request.getParameter("planStatus").equals("")?new Integer(-1):Integer.valueOf(request.getParameter("planStatus"));
    	String  planDesc = request.getParameter("planDesc");
    	
    	System.out.println("planPlanid="+planPlanid.toString());
    	System.out.println("planName="+planName);
    	//System.out.println("planNum="+planNum.toString());
    	//System.out.println("planScale="+planScale.toString());
    	//System.out.println("planNumlow="+planNumlow.toString());
    	System.out.println("planNumhigh="+planNumhigh.toString());
    	System.out.println("planStatus="+planStatus.toString());
    	System.out.println("planSeason="+planSeason.toString());
    	System.out.println("planDesc="+planDesc.toString());
        try {
        	
            //初始化用到的数据
        	
        	
//            CurrentUser user = new CurrentUser();
//            String currentUserId = user.getId();
//            String	currentUserName=user.getName();
        	
        	
        	/*HttpSession usersession = request.getSession();

        	String	uuid=usersession.getAttribute("votebbsuuid")==null?"":usersession.getAttribute("votebbsuuid").toString();
        	String	userid=usersession.getAttribute("votebbsuserid")==null?"":usersession.getAttribute("votebbsuserid").toString();
        	String	username=usersession.getAttribute("votebbsusercnname")==null?"":usersession.getAttribute("votebbsusercnname").toString();*/
        
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteLiuHandler handler=new VoteLiuHandler(conn);
            
            
            //写入信息表
           
            VotePlanVO voteplanvo=new VotePlanVO();
            voteplanvo=handler.getPlanVo(planPlanid);
            
            voteplanvo.setPlanPlanid(planPlanid);
            voteplanvo.setPlanName(planName);
            //voteplanvo.setPlanScale(planScale);
           // voteplanvo.setPlanNum(planNum);
            //voteplanvo.setPlanNumlow(planNumlow);
            voteplanvo.setPlanNumhigh(planNumhigh);
            voteplanvo.setPlanSeason(planSeason);
            voteplanvo.setPlanStatus(planStatus);
            voteplanvo.setPlanDesc(planDesc);
            
            handler.updatePlan(voteplanvo);        
           
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
