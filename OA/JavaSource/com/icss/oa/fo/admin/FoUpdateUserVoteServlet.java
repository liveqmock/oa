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
import com.icss.oa.fo.vo.FoVotepersonVO;


public class FoUpdateUserVoteServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 
    	System.out.println("进入FoUpdateUserVoteServlet");
    	Integer personId = request.getParameter("personId")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("personId"));
    	Integer planid = request.getParameter("planid")==null?(new Integer(-1)):Integer.valueOf(request.getParameter("planid"));
    	String flag = request.getParameter("flag");
    	System.out.println("personId ="+request.getParameter("personId"));
    	System.out.println("planid ="+planid.toString());
    	System.out.println("flag="+flag);
    	Connection conn = null;
    	
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
            FoLiuHandler handler=new FoLiuHandler(conn);
            //FoVotepersonVO fopersonvo = handler.getVotepersonVo(personId);          
            //System.out.println("personId="+fopersonvo.getPersonId());
            //System.out.println("personname="+fopersonvo.getName());
            //request.setAttribute("fopersonvo",fopersonvo);
            //fopersonvo.setFlag("1");
            //handler.updatePerson(fopersonvo);
            handler.updateUserVote(personId,planid,flag);
            response.sendRedirect(request.getContextPath()+"/servlet/FoUserManagerListServlet?planid="+planid.toString());
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
