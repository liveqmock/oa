/*
 * Created on 2004-5-14
 */
package com.icss.oa.multivote.admin;



import java.io.IOException;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;


import com.icss.oa.multivote.handler.VoteHandler;
import com.icss.oa.multivote.vo.VoteArticleVO;
import com.icss.oa.multivote.vo.VotePersonVO;
import com.icss.oa.multivote.vo.VotePlanVO;
import com.icss.oa.multivote.vo.VoteTableVO;



public class MVoteDeleteUserServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	//ȡ�ô���ѡ��������tableID
    	Integer planid=request.getParameter("planid")==null?new Integer(-1):Integer.valueOf(request.getParameter("planid"));  	
    	Integer personId=request.getParameter("personId")==null?new Integer(-1):Integer.valueOf(request.getParameter("personId"));
    	Connection conn = null;
 
    	
           
        try {
        	
        	 //��ʼ���õ�������
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            VoteHandler handler=new VoteHandler(conn);
            //����ͶƱ����
            VotePlanVO planvo=new VotePlanVO();
            planvo=handler.getPlanVo(planid.toString());
            //ȡ��ͶƱ�˵�ͶƱ��Ϣ
            VotePersonVO vo = new VotePersonVO();
			vo = handler.findByPersonid(personId.toString());
			String	userhasvote=vo.getFlag();
			if("1".equals(userhasvote)){//��Ͷ��Ʊ
				Integer votepersonnum=new Integer(0);
				if(planvo.getPlanPersonnum()!=null){
					votepersonnum=planvo.getPlanPersonnum();
				}
				planvo.setPlanPersonnum(new Integer(votepersonnum.intValue()-1));
				handler.updatePlan(planvo);
			}
            //ɾ��ͶƱ
			
			
					handler.deleteUserVoteValue(planid,personId,planvo.getPlanSeason());
				
				
			
            //ɾ����Ա��Ϣ
            handler.deleteUser(planid,personId);
            
            
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
