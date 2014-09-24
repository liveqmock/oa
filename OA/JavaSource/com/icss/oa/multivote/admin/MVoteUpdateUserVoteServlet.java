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
import com.icss.oa.multivote.handler.VoteHandler;




public class MVoteUpdateUserVoteServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	//System.out.println("Ω¯»ÎFoUpdateUserVoteServlet");
    	Integer personId = request.getParameter("personId")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("personId"));
    	Integer planid = request.getParameter("planid")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("planid"));
    	String flag = request.getParameter("flag");

    	Connection conn = null;
    	
        try {
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteHandler handler=new VoteHandler(conn);
           
            handler.updateUserVote(personId,planid,flag);
            response.sendRedirect(request.getContextPath()+"/servlet/MVoteUserManagerListServlet?planid="+planid.toString());
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
