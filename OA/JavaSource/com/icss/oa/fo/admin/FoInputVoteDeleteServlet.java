/*
 * Created on 2004-5-14
 */
package com.icss.oa.fo.admin;



import java.io.IOException;


import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



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

public class FoInputVoteDeleteServlet extends ServletBase {

    protected void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	Connection conn = null;
       
    	Integer planid=request.getParameter("planid")==null?new Integer(-1):Integer.valueOf(request.getParameter("planid"));
    	Integer invoteid=request.getParameter("invoteid")==null?new Integer(-1):Integer.valueOf(request.getParameter("invoteid"));
    	
    	
    	
        
        
       
        
        try {
        	
        	 //初始化用到的数据
        	
        	conn = this.getConnection(Globals.DATASOURCEJNDI);
            FoHandler handler=new FoHandler(conn);
           
            List list=handler.getVoteArticlelist(planid.toString());
            System.out.println("+++++++++++=文章列表list.size()"+list.size());
            Iterator itr=list.iterator();
            
            Map map=handler.getInputValueMap(invoteid);
            System.out.println("+++++++++++=取得投票数据的Map");
            while(itr.hasNext()){
//            	更改文章中的记录信息
            	
            	FoArticalVO vo=(FoArticalVO) itr.next();
            	System.out.println("+++++++++++=更改文章中的记录信息 artid="+vo.getArtId());
            	int oldvotenum=vo.getArtVotenum().intValue();
            	FoInputvalueVO votevo=(FoInputvalueVO)map.get(vo.getArtId());
            	int temp=0;
            	if(votevo!=null){
            		temp=votevo.getInvalueValue().intValue();
            	}
            	
         
            	int newvotenum=oldvotenum-temp;
            	System.out.println("+++++++++++=更改文章中的记录信息 newvotenum="+newvotenum);
            	handler.updateArticalVotenum(vo.getArtId().toString(),new Integer(newvotenum));

            	
            }
            System.out.println("+++++++++++=删除投票数据表开始");
//          删除投票数据表
            handler.deleteInputValue(invoteid);
            
            System.out.println("+++++++++++=删除投票信息表开始");
            //删除计划表中的投票人数信息
            FoInputvoteVO invotevo=handler.getInputVote(invoteid);
            Integer oldvotepersonnum=new Integer(0);
            if(invotevo.getInvotePersonnum()!=null){
            	oldvotepersonnum=invotevo.getInvotePersonnum();
            }
            FoPlanVO planvo=handler.getPlanVo(planid.toString());
            Integer planvotepersonnum=new Integer(0);
            if(planvo.getPlanPersonnum()!=null){
            	planvotepersonnum=planvo.getPlanPersonnum();
            }
            planvo.setPlanPersonnum(new Integer(planvotepersonnum.intValue()-oldvotepersonnum.intValue()));
            handler.updatePlan(planvo);
            System.out.println("修改计划表中的投票人数信息,变化人数为="+oldvotepersonnum+"原计划人数为"+planvotepersonnum);
            
            //删除投票信息表
            handler.deleteInputVote(invoteid);
          
            
            
            
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
