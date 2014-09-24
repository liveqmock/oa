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
import com.icss.oa.multivote.vo.VotePersonVO;



public class MVoteUpdateUserServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("MVoteUpdateUserServlet*********");
    	//ȡ�ô���ѡ��������tableID
    	Integer planid=request.getParameter("planid")==null?new Integer(-1):Integer.valueOf(request.getParameter("planid"));
    	Integer personid=request.getParameter("personid")==null?new Integer(-1):Integer.valueOf(request.getParameter("personid"));
   	
    	Connection conn = null;
 
    	//ȡ����mVoteAddItem.jspҳ������ӵĸ�ѡ�����Ϣ
        String username = request.getParameter("username");
        String userhrid = request.getParameter("userhrid");
        String orgname = request.getParameter("orgname");
        String orgcode = request.getParameter("orgcode");
        
        String deptname = request.getParameter("deptname");
        String deptcode = request.getParameter("deptcode");
        String headship = request.getParameter("headship");
        String cardid = request.getParameter("cardid");
        String parentorg = request.getParameter("parentorg");
      
        System.out.println("MVoteUpdateUserServlet*******planid**"+planid+"personid"+personid);
           
        try {
        	
        	 //��ʼ���õ�������
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteHandler handler=new VoteHandler(conn);
            
            
            
            VotePersonVO vo=new VotePersonVO();//��ʼ��һ��VoteItemVO,���������ѡ����Ϣ
            vo = handler.findByPersonid(personid.toString());
            //ΪVO��ֵ
            vo.setHrid(userhrid)   ;
            vo.setName(username);
            vo.setOrgName(orgname);
            vo.setOrgCode(orgcode);
            vo.setDepName(deptname);
            vo.setDepCode(deptcode);
            vo.setHeadshipName(headship);
            vo.setOrgParentName(parentorg);
            vo.setHeadshipLevelCode(cardid);
            handler.updatePersonVO(vo);
      
            this.forward(request, response, "/servlet/MVoteUserManagerListServlet?planid="+planid+"&username=");
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
