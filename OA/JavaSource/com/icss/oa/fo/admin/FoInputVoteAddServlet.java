/*
 * Created on 2004-5-14
 */
package com.icss.oa.fo.admin;



import java.io.IOException;


import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.icss.j2ee.servlet.ServletBase;
import com.icss.j2ee.util.Globals;

import com.icss.oa.util.CurrentUser;
import com.icss.oa.fo.handler.FoHandler;
import com.icss.oa.fo.handler.FoLiuHandler;
import com.icss.oa.fo.vo.FoArticalVO;
import com.icss.oa.fo.vo.FoInputvalueVO;
import com.icss.oa.fo.vo.FoInputvoteVO;
import com.icss.oa.fo.vo.FoPlanVO;

public class FoInputVoteAddServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	Connection conn = null;
       
    	Integer planid=request.getParameter("planid")==null?new Integer(-1):Integer.valueOf(request.getParameter("planid"));
    	String invoteperson = request.getParameter("invoteperson");
        Integer invotepersonnum = request.getParameter("invotepersonnum")==null?new Integer(0):new Integer(request.getParameter("invotepersonnum"));
        String invotedesc = request.getParameter("invotedesc");

     
        
       
        
        try {
        	
        	 //初始化用到的数据
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            FoHandler handler=new FoHandler(conn);
            CurrentUser user=new CurrentUser();
            
            
            //写入投票信息表
            Date date=new Date();
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String formatTime = formatter.format(date);
             
            FoInputvoteVO vo=new FoInputvoteVO();
            vo.setInvotePerson(invoteperson);
            vo.setInvotePersonnum(invotepersonnum);
            vo.setInvoteDesc(invotedesc);
            vo.setInvoteRecorder(user.getName());
            vo.setInvoteDate(formatTime);
            vo.setPlanPlanid(planid);
            Integer invoteid=handler.addInputvote(vo);
            System.out.println(" 增加一条输入投票记录表 invoteid="+invoteid);
            //更新计划表中的投票人数
            FoPlanVO planvo=handler.getPlanVo(planid.toString());
            Integer votepersonnum=new Integer(0);
            if(planvo.getPlanPersonnum()!=null){
            	votepersonnum=planvo.getPlanPersonnum();
            	
            }
            planvo.setPlanPersonnum(new Integer(votepersonnum.intValue()+invotepersonnum.intValue()));
            handler.updatePlan(planvo);
            //更新投票数据表中记录
            List list=new ArrayList();
            list=handler.getVoteArticlelist(planid.toString());
            Iterator itr=list.iterator();
            while(itr.hasNext()){
            	FoArticalVO artvo=(FoArticalVO) itr.next();
            	FoInputvalueVO invaluevo=new FoInputvalueVO();
            	invaluevo.setArtId(artvo.getArtId());
            	invaluevo.setInvoteId(invoteid);
            	invaluevo.setInvalueValue(new Integer(0));
            	handler.addInputVoteValue(invaluevo);
            	System.out.println(" 增加投票数据记录表 artid="+artvo.getArtId());
            }
            
      
            
            
            this.forward(request, response, "/servlet/FoInputVoteManagerServlet?planid="+planid);
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
