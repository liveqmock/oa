/*
 * Created on 2004-5-14
 */
package com.icss.oa.fo.admin;



import java.io.IOException;


import java.sql.Connection;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoPlanVO;

public class FoUpdatePlanGetPlanVOServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("����FoUpdatePlanGetPlanVOServlet");
    	Integer planPlanid = request.getParameter("planPlanid")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("planPlanid"));
    	System.out.println("planPlanid ="+request.getParameter("planPlanid"));
    	
    	Connection conn = null;
    	
        try {
        	
            //��ʼ���õ�������
        	
        	
//            CurrentUser user = new CurrentUser();
//            String currentUserId = user.getId();
//            String	currentUserName=user.getName();
        	
        	
        	/*HttpSession usersession = request.getSession();

        	String	uuid=usersession.getAttribute("votebbsuuid")==null?"":usersession.getAttribute("votebbsuuid").toString();
        	String	userid=usersession.getAttribute("votebbsuserid")==null?"":usersession.getAttribute("votebbsuserid").toString();
        	String	username=usersession.getAttribute("votebbsusercnname")==null?"":usersession.getAttribute("votebbsusercnname").toString();*/
        
            conn = this.getConnection(Globals.DATASOURCEJNDI);
            FoLiuHandler handler=new FoLiuHandler(conn);
            FoPlanVO planvo = handler.getPlanVo(planPlanid);
                      
            
            request.setAttribute("planvo",planvo);
            
            this.forward(request, response, "/fo/FoUpdatePlan.jsp?");
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
