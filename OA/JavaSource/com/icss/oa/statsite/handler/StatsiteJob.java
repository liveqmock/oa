/*
 * Created on 2004-4-8
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

package com.icss.oa.statsite.handler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.icss.common.log.ConnLog;
import com.icss.j2ee.services.DBConnectionLocator;
import com.icss.j2ee.services.DBConnectionLocatorException;
import com.icss.j2ee.util.Globals;
import com.icss.oa.statsite.vo.StatSiteDate1VO;
import com.icss.oa.statsite.vo.StatSiteDateVO;

/**
 * @author Administrator
 *
 * 
 */
public class StatsiteJob implements Job {

	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Connection con = null;
		try {

			System.out.println(getClass().toString() + " Job start");
			con = DBConnectionLocator.getInstance().getConnection(Globals.DATASOURCEJNDI);
			ConnLog.open("StatsiteJob");
			StatSiteHandler handler = new StatSiteHandler(con);
			long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
			String time1 = handler.getTimeByLong(new Long(time));
			Long count[] = new Long[25];
			count = handler.getNumber_hour(time1);
			StatSiteDate1VO vo = new StatSiteDate1VO();
			StatSiteDateVO vo1 = new StatSiteDateVO();

			vo.setVisDate(handler.getLongByTime(time1));
			vo1.setVisDate(handler.getLongByTime(time1));

			vo.setH0(count[0]);
			vo.setH1(count[1]);
			vo.setH2(count[2]);
			vo.setH3(count[3]);
			vo.setH4(count[4]);
			vo.setH5(count[5]);
			vo.setH6(count[6]);
			vo.setH7(count[7]);
			vo.setH8(count[8]);
			vo.setH9(count[9]);
			vo.setH10(count[10]);
			vo.setH11(count[11]);
			vo.setH12(count[12]);
			vo.setH13(count[13]);
			vo.setH14(count[14]);
			vo.setH15(count[15]);
			vo.setH16(count[16]);
			vo.setH17(count[17]);
			vo.setH18(count[18]);
			vo.setH19(count[19]);
			vo.setH20(count[20]);
			vo.setH21(count[21]);
			vo.setH22(count[22]);
			vo.setH23(count[23]);

			String string = new String();
			string = String.valueOf(count[24]);
			vo1.setVisNumber(Integer.valueOf(string));

			List dates = handler.getDateByDate();
			Iterator its = null;
			boolean flag = false;

			if (dates != null) {
				its = dates.iterator();
			}

			if (its != null) {
				while (its.hasNext()) {
					StatSiteDateVO vo11 = new StatSiteDateVO();
					vo11 = (StatSiteDateVO) its.next();

					/*System.out.println("DateBase-Date: "+handler.getTimeByLong(vo11.getVisDate()));
					System.out.println("Current-Date: "+handler.getTimeByLong(new Long(time)));*/

					if (handler.getTimeByLong(vo11.getVisDate()).equals(handler.getTimeByLong(new Long(time)))) {
						flag = true;
					}

				}
			}

			//System.out.println("Flag  :"+flag);

			if (flag == false) {
				//   System.out.println("Insert to DateBase Successfully ");
				handler.addHour(vo);
				handler.add(vo1);
				handler.del(time1);
				handler.delAll();
			}

			//-----------------更新并删除一天之前的历史记录－－－－－－－－－－－－－－－－－－－－－－－－－－
			/*List list_half =handler.getList_date(handler.getTimeByLong(new Long(time-24*60*60*1000)));
			Iterator  iterator= null;   
			if(list_half!=null){     
					iterator = list_half.iterator();   
				
			StatSiteVO vo11;
			int i=0;
			while(iterator.hasNext()){
				vo11 = (StatSiteVO)iterator.next();
				handler.updateList_date(vo11);
				i++;
				}
			}*/
			//－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－

			System.out.println(getClass().toString() + "success");

		} catch (DBConnectionLocatorException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			try {
				if (con != null) {
					con.close();
					ConnLog.close("StatsiteJob");
				}
				con = null;
			} catch (SQLException e1) {
				con = null;
				e1.printStackTrace();
			}
		}

	}

}
