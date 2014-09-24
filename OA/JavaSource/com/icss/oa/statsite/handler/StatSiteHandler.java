package com.icss.oa.statsite.handler;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.*;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.j2ee.dao.DAOException; 
import com.icss.oa.meeting.handler.HandlerException;
import com.icss.oa.statsite.vo.*;
import com.icss.oa.statsite.dao.*;

public class StatSiteHandler 
{

	private Connection conn;

	public StatSiteHandler(Connection conn)
	{
		this.conn = conn;
	}
	
	public List getInfoList(String str) throws HandlerException {
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDAO dao = new StatSiteDAO();
		factory.setDAO(dao);
		dao.setWhereClause(str);
		try {
			List list = factory.find(new StatSiteVO());
			System.out.println("KKKKKKKKKKKKKKKKKKKKKK");
			return list;
		}
		catch (DAOException e) {
			throw new HandlerException(e);
		}
	}

	/**
	 * 创建新test
	 * @param testVO 
	 * @throws HandlerException
	 */
	public void  add(StatSiteVO statsiteVO) throws HandlerException
	{
		StatSiteDAO statsitedao = new StatSiteDAO();
		statsitedao.setValueObject(statsiteVO);
		statsitedao.setConnection(conn);

		try
		{
			statsitedao.create();
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	
	public void addHour(StatSiteDate1VO vo) throws HandlerException
	{
		try
		{ 
		    StatSiteDate1DAO statsitehourdao = new StatSiteDate1DAO();
		    statsitehourdao.setValueObject(vo);
		    statsitehourdao.setConnection(conn);
		    statsitehourdao.create();
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
		  
	}
	
	public void add(StatSiteDateVO vo) throws HandlerException
	{
		try
		{ 
		    StatSiteDateDAO dao = new StatSiteDateDAO();
		    dao.setValueObject(vo);
		    dao.setConnection(conn);
		    dao.create();
		}
		catch (DAOException e)     
		{
			throw new HandlerException(e);
		}
	}
	
	public List getDateByDate() throws HandlerException
	{
		try
		{ 
		    StatSiteDateDAO dao = new StatSiteDateDAO();
		    DAOFactory factory = new DAOFactory(conn);
		    factory.setDAO(dao);
		    List list = factory.find(new StatSiteDateVO());
		    
			return list;
		}
		catch (DAOException e)     
		{
			throw new HandlerException(e);
		}
	}
	
	public void del(String time) {
		StatSiteDAO dao = new StatSiteDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setWhereClause("TIME >="+this.getLongByTime(time).longValue()+" AND  TIME <="+(this.getLongByTime(time).longValue()+24*60*60*1000-1));
		factory.setDAO(dao);
		dao.setConnection(conn);
		try {
			factory.batchDelete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delAll() {
		StatSiteDAO dao = new StatSiteDAO();
		DAOFactory factory = new DAOFactory(conn);
		dao.setWhereClause("TIME >= 0 AND  TIME <= 9091600823959");
		factory.setDAO(dao);
		dao.setConnection(conn);
		try {
			factory.batchDelete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List get() throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDAO dao = new StatSiteDAO();
		factory.setDAO(dao);
		dao.addOrderBy("time",false);

		try
		{
			List list = factory.find(new StatSiteVO());
			return list;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	
	
	public List getList_date(String str) throws HandlerException
	{
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDAO dao = new StatSiteDAO();
		factory.setDAO(dao);
		dao.setWhereClause("TIME <="+(this.getLongByTime(str).longValue()-1));
		
		//dao.addOrderBy("time",false);

		try
		{
			List list = factory.find(new StatSiteVO());
			return list;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	
	public void updateList_date(StatSiteVO vo) throws DAOException, HandlerException{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO dao = new StatSiteDateDAO();
		factory.setDAO(dao);
	    List list;
		list = factory.find(new StatSiteDateVO());
		Iterator  iterator= null;   
		if(list!=null){     
	 		iterator = list.iterator();   
	 	}
		StatSiteDateVO vo1 = new StatSiteDateVO();
		StatSiteDateVO vo2 = new StatSiteDateVO();
		
		boolean flag = false;
		while(iterator.hasNext()){
			vo1 = (StatSiteDateVO)iterator.next();
			if(this.getTimeByLong(vo1.getVisDate()).equals(this.getTimeByLong(vo.getTime())))
			{
				
				flag = true;
				vo2.setVisDate(this.getLongByTime(this.getTimeByLong(vo.getTime())));
				vo2.setVisNumber(new Integer(vo1.getVisNumber().intValue()));
				vo2.setId(vo1.getId());
				this.updateDate(vo2);
				}
		}
		if(flag==false){
		   	
				vo2.setVisDate(this.getLongByTime(this.getTimeByLong(vo.getTime())));
				vo2.setVisNumber(new Integer(1));
				this.add(vo2);
				}
		this.delete(vo.getId());
	}
	
	public void updateDate(StatSiteDateVO vo) throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO dao = new StatSiteDateDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setId(vo.getId());
		try {
			dao = (StatSiteDateDAO) factory.findByPrimaryKey();
			int num=vo.getVisNumber().intValue();
			dao.setVisNumber(Integer.valueOf(num+1));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	
	public List getHourList(Long time) throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDate1DAO dao = new StatSiteDate1DAO();
		dao.setVisDate(time);
		factory.setDAO(dao);

		try
		{
			List list = factory.find(new StatSiteDate1VO());
			return list;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	
	public List getMoldList() throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteMoldDAO dao = new StatSiteMoldDAO();
		factory.setDAO(dao);

		try
		{
			List list = factory.find(new StatSiteMoldVO());
			return list;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}

	public List getIPAddressList() throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpnumberDAO dao = new StatSiteIpnumberDAO();
		factory.setDAO(dao);

		try
		{
			List list = factory.find(new StatSiteIpnumberVO());
			return list;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	/**
	 * 修改test信息
	 * @param testVO 
	 */
	public void alter(StatSiteVO statsiteVO) throws HandlerException
	{
		//DAOFactory factory = new DAOFactory(conn);
		try
		{
			StatSiteDAO statsitedao = new StatSiteDAO();
			statsitedao.setValueObject(statsiteVO);
			statsitedao.setConnection(conn);
			statsitedao.update(true);

		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
	}
	/**
		   * 删除test
		   * @param  id
		   * @exception 
		   */
	public void delete(Integer id) throws HandlerException
	{
		try
		{
			DAOFactory factory = new DAOFactory(conn);
			StatSiteDAO statsitedao = new StatSiteDAO();
			factory.setDAO(statsitedao);
			statsitedao.setConnection(conn);
			statsitedao.setId(id);
			statsitedao.delete();

		}
		catch (Exception e)
		{
			throw new HandlerException();
		}
	}
	
	//新建一个模块访问数的记录
	public void add(String mold_name) throws HandlerException
	{
		StatSiteMoldDAO statsitedao = new StatSiteMoldDAO();
		statsitedao.setConnection(conn);
		statsitedao.setVisMold(mold_name);
		statsitedao.setVisNumber(Integer.valueOf(1));
		try
		{
			statsitedao.create();
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	
    //新建一个IP地址访问数的记录
	public void addIP(String ip_name) throws HandlerException
	{
		StatSiteIpnumberDAO dao = new StatSiteIpnumberDAO();
		dao.setConnection(conn);
		dao.setAddress(ip_name);
		dao.setCounter(Long.valueOf(1));
		try
		{
			dao.create();
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}

	/**
	 * 更新新的模块查询记录+1
	 * @param mVO //StatSiteMoldeVO
	 * @throws HandlerException
	 */
	
	public void updateMoldNumber(String mold_name) throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteMoldDAO dao = new StatSiteMoldDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setId(this.getMoldID(mold_name));
		try {
			dao = (StatSiteMoldDAO) factory.findByPrimaryKey();
			int num=(this.getMoldNumber(mold_name)).intValue();
			dao.setVisNumber(Integer.valueOf(num+1));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新新的模块查询记录+1
	 * @param mVO //StatSiteMoldeVO
	 * @throws HandlerException
	 */
	
	public void updateIpNumber(String ip_name) throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpnumberDAO dao = new StatSiteIpnumberDAO();
		factory.setDAO(dao);
		dao.setConnection(conn);
		dao.setId(this.getIPID(ip_name));
		try {
			dao = (StatSiteIpnumberDAO) factory.findByPrimaryKey();
			int num=(this.getIpNumber(ip_name)).intValue();
			dao.setCounter(Long.valueOf(num+1));
			dao.update(true);
		} catch (DAOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 根据输入的模块名字查询相应的游览次数
	 * @param mold_name //模块名字
	 * @throws HandlerException
	 */
	public Integer getMoldNumber(String mold_name) throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteMoldDAO mdao = new StatSiteMoldDAO();
		StatSiteMoldVO mvo = new StatSiteMoldVO();
		Integer num1=null;
		mdao.setVisMold(mold_name);
		factory.setDAO(mdao);
		
		try
		{
			List list = factory.find(new StatSiteMoldVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteMoldVO)it.next();
					num1=mvo.getVisNumber();
					}
			return num1;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	public Long getIpNumber(String Ip_name) throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpnumberDAO mdao = new StatSiteIpnumberDAO();
		StatSiteIpnumberVO mvo = new StatSiteIpnumberVO();
		Long num1=null;
		mdao.setAddress(Ip_name);
		factory.setDAO(mdao);
		
		try
		{
			List list = factory.find(new StatSiteIpnumberVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteIpnumberVO)it.next();
					num1=mvo.getCounter();
					}
			return num1;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	
    //得到某月的访问列表
	public List getNumber_monthList(String time) throws HandlerException{
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		//StatSiteDateVO mvo = new StatSiteDateVO();
		String cunMonth=String.valueOf(this.getLongByTime(this.getCurruntMonth(time)).longValue());
		String nextMonth=String.valueOf(this.getLongByTime(this.getNextMonth(time)).longValue()-1);
			
		mdao.setWhereClause("VIS_DATE>="+cunMonth+" and VIS_DATE<="+nextMonth);
		mdao.addOrderBy("visDate",true);
		factory.setDAO(mdao);
		//int num2=0;
		//long num1=0;
		
		try
		{
			List list = factory.find(new StatSiteDateVO());
			return list;
			
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
	}
	
//	得到某星期的访问次数
	public Long [] getNumber_week(String time) throws HandlerException{
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		StatSiteDateVO mvo = new StatSiteDateVO();
		int week1 = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		
		long cun = this.getLongByTime(time).longValue();
		Long week [] = new Long[8] ;
		long count=0;
		//System.out.println("k  k  k  "+week1);
		
		try
		{
			for(int i=0;i<7;i++){
			//	System.out.println("-----------sunchuanting  "+i);
				mdao.setWhereClause("VIS_DATE>="+(cun-(week1-2-i)*24*60*60*1000)+" and VIS_DATE<="+(cun-(week1-3-i)*24*60*60*1000-1));
				factory.setDAO(mdao);
				List list = factory.find(new StatSiteDateVO());
				Iterator it = list.iterator();
				while(it.hasNext()){
						mvo=(StatSiteDateVO)it.next();
						week[i]=Long.valueOf(mvo.getVisNumber().intValue());
						count+=mvo.getVisNumber().intValue();
					//	System.out.println("k  k  k  "+i+week[i]);
						
						}
				}
			week[7] =new Long(count);
			
			for(int i=0;i<8;i++)
			{
				if(week[i]==null)
				      {	week[i]=new Long(0);}
			}
			
			return (week);
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
	}
	
//	得到某月的访问次数
	public Long getNumber_month(String time) throws HandlerException{
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		StatSiteDateVO mvo = new StatSiteDateVO();
		String cunMonth=String.valueOf(this.getLongByTime(this.getCurruntMonth(time)).longValue());
		String nextMonth=String.valueOf(this.getLongByTime(this.getNextMonth(time)).longValue()-1);
		//System.out.println("99wwwwwww**aa "+time);
		//System.out.println("99wwwwwww**aa time "+"  "+time);
		
		mdao.setWhereClause("VIS_DATE>="+cunMonth+" and VIS_DATE<="+nextMonth);
		factory.setDAO(mdao);
		int num2=0;
		long num1=0;
		
		try
		{
			List list = factory.find(new StatSiteDateVO());
			//System.out.println("99wwwwwww**aa timewwwwwwwwwwww "+"  "+list.size());
			Iterator it = list.iterator();
			
			while(it.hasNext()){
					mvo=(StatSiteDateVO)it.next();
					num2=mvo.getVisNumber().intValue();
				//	System.out.println("99wwwwwww**a num2 "+"  "+num2);
					num1+=num2;		
					
				//	System.out.println("99wwwwwww**aawq num1 "+"  "+num1);
					
					}
			
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
		return (new Long(num1));
	}
	    //得到某年的访问次数
	public Long getNumber_Year(String time) throws HandlerException{
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		StatSiteDateVO mvo = new StatSiteDateVO();
		String cunYear=String.valueOf(this.getLongByTime(this.getCurruntyear(time)).longValue());
		String nextYear=String.valueOf(this.getLongByTime(this.getNextyear(time)).longValue()-1);
		
		mdao.setWhereClause("VIS_DATE>="+cunYear+" and VIS_DATE<="+nextYear);
		factory.setDAO(mdao);
		int num2=0;
		long num1=0;
		
		try
		{
			List list = factory.find(new StatSiteDateVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteDateVO)it.next();
					num2=mvo.getVisNumber().intValue();
					num1+=num2;
					}
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}

		return (new Long(num1));
	}
	
    //得到某日的访问次数
	public Integer getNumber_Date(String time) throws HandlerException{
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		StatSiteDateVO mvo = new StatSiteDateVO();
		mdao.setVisDate(this.getLongByTime(time));
		factory.setDAO(mdao);
		Integer num1=null;
		
		try
		{
			List list = factory.find(new StatSiteDateVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteDateVO)it.next();
					num1=mvo.getVisNumber();
					}
			return num1;
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
	}
	
    //得到一天之中按小时的的访问次数数组
	public Long [] getNumber_hour(String time) throws HandlerException{
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDAO mdao = new StatSiteDAO();
		StatSiteVO mvo = new StatSiteVO();
		long cun=this.getLongByTime(time).longValue();
		Long pp[] = new Long[25];
		for (int i=0;i<24;i++){pp[i] = new Long(0);}
		long count1=0;
		ResultSet rs = null;
		try {  
			for(int i=0;i<24;i++){
				
				String sql =
					" SELECT COUNT(ID) AS ISSHARE FROM STAT_SITE WHERE TIME >="+(cun+60*60*1000*i)+" and TIME <="+(cun+60*60*1000*(i+1)-1);
				int share = 0;
				
					rs = conn.createStatement().executeQuery(sql);
					if (rs != null) {
						if (rs.next()) {
							share = rs.getInt("ISSHARE");
							pp[i]=new Long(share);
							count1+=share;
						}
					}
			pp[24] = new Long(count1);
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try{
				if(rs!=null){
					rs.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return (pp);
	}
	
	//根据提供的时间字符串得到相应的月份第一天的字符串
	
	public String getCurruntMonth(String time){
		
		return (time.substring(0,8)+1);
		}
	
	//	根据提供的时间字符串得到相应的年第一天的字符串
	public String getCurruntyear(String time){
		
		int year=Integer.valueOf(time.substring(0,4)).intValue();
		
		return (year+"-01-01");
		}
		
	//	根据提供的时间字符串得到before的年第一天的字符串
	public String getBfoyear(String time){
		
		int year=Integer.valueOf(time.substring(0,4)).intValue();
		
		return ((year-1)+"-01-01");
		}
		
	//	根据提供的时间字符串得到after的年第一天的字符串
	public String getNextyear(String time){
		
		int year=Integer.valueOf(time.substring(0,4)).intValue();
		
		return ((year+1)+"-01-01");
		}
		
	//根据提供的时间字符串得到下个月分第一天的字符串
	public String getNextMonth(String time){
		
		String returnstr=null;
		int month=Integer.valueOf(time.substring(5,7)).intValue();
		
		if(month!=12){
			if((month+1)>9) 	{	returnstr=(time.substring(0,5)+(month+1)+"-01");}
			  else{	returnstr=(time.substring(0,5)+"0"+(month+1)+"-01");}
			}
		if(month==12){
			
			int year=Integer.valueOf(time.substring(0,4)).intValue()+1;
			String month1=time.substring(5,7);
			returnstr=(String.valueOf(year)+"-01-01");
			}	
		return (returnstr);
		}
		
	
	//根据提供的时间字符串得到下个月分第一天的字符串
	public String getBfoMonth(String time){
		
		String returnstr=null;
		int month=Integer.valueOf(time.substring(5,7)).intValue();
		
		if(month!=1){
			returnstr=(time.substring(0,5)+(month-1)+"-01");
			}
		if(month==1){
			
			int year=Integer.valueOf(time.substring(0,4)).intValue()-1;
			//String month1=time.substring(5,7);
			returnstr=(String.valueOf(year)+"-12-01");
			}	
		return (returnstr);
		}
	
	
	/**
	 * 根据输入的模块名字得到相应主键
	 * @param mold_name //模块名字
	 * @throws HandlerException
	 */
	public Integer getMoldID(String mold_name) throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteMoldDAO mdao = new StatSiteMoldDAO();
		StatSiteMoldVO mvo = new StatSiteMoldVO();
		Integer num1=null;
		mdao.setVisMold(mold_name);
		factory.setDAO(mdao);
		
		try
		{
			List list = factory.find(new StatSiteMoldVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteMoldVO)it.next();
					num1=mvo.getId();
					}
			if(num1==null){
				this.add(mold_name);
				this.getMoldID(mold_name);
				}
			return num1;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	
	/**
	 * 根据输入的IP地址的得到相应主键
	 * @param ip_name //ip地址的名字
	 * @throws HandlerException
	 */
	public Integer getIPID(String ip_name) throws HandlerException
	{
		DAOFactory factory = new DAOFactory(conn);
		StatSiteIpnumberDAO mdao = new StatSiteIpnumberDAO();
		StatSiteIpnumberVO mvo = new StatSiteIpnumberVO();
		Integer num1=null;
		mdao.setAddress(ip_name);
		factory.setDAO(mdao);
		
		try
		{
			List list = factory.find(new StatSiteIpnumberVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteIpnumberVO)it.next();
					num1=mvo.getId();
					}
			if(num1==null){
				this.addIP(ip_name);
				this.getIPID(ip_name);
				}
			return num1;
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}
	}
	public void addDateNumber(StatSiteDateVO statsiteDatevo ) throws HandlerException
	{
		StatSiteDateDAO statsitedao = new StatSiteDateDAO();
		statsitedao.setValueObject(statsiteDatevo);
		statsitedao.setConnection(conn);

		try
		{
			statsitedao.create();
		}
		catch (DAOException e)
		{
			throw new HandlerException(e);
		}

	}
	
	//将字符串型的时间转换为微秒（Long）形式
	public  Long getLongByTime(String time){
		try{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			ParsePosition pos = new ParsePosition(0);
			Date date = formatter.parse(time, pos);
			if (date == null) return null;
			return new Long(date.getTime());
		}
		catch(Exception e){
			return null;
		}
	}
	
	//	将微秒（Long）的时间转换为字符串型形式
	public String getTimeByLong(Long time){
		try{
			  Date date=new Date(time.longValue());
			  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			  String formatTime = formatter.format(date);
			  return formatTime;
		}
		catch(Exception e){
			return null;
		}
	}
	
//	得到最先的访问时间
	public Long getMin_time() throws HandlerException{
		
		//DAOFactory factory = new DAOFactory(conn);
		//StatSiteDateDAO mdao = new StatSiteDateDAO();
		//StatSiteDateVO mvo = new StatSiteDateVO();
		long share = 0;
		ResultSet rs = null;
		Statement st = null;
		try {
				String sql =
					" SELECT MIN(VIS_DATE) AS ISSHARE FROM STAT_SITE_DATE ";
					st=conn.createStatement();
					rs = st.executeQuery(sql);
					if (rs != null) {
						if (rs.next()) {
							share = rs.getLong("ISSHARE");
							
						}
					}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(st!=null){
				try{
					st.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		//System.out.print("fdsfdsfds1"+share);
		return new Long(share);
	}
	
//	得到最后的访问时间
	public Long getMax_time() throws HandlerException{
		
		//DAOFactory factory = new DAOFactory(conn);
		//StatSiteDateDAO mdao = new StatSiteDateDAO();
		//StatSiteDateVO mvo = new StatSiteDateVO();
		long share = 0;
		ResultSet rs = null;
		Statement st = null;
		try {
				String sql =
					" SELECT MAX(VIS_DATE) AS ISSHARE FROM STAT_SITE_DATE ";
				st= conn.createStatement();
					rs = st.executeQuery(sql);
					if (rs != null) {
						if (rs.next()) {
							share = rs.getLong("ISSHARE");
							
						}
					}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(st!=null){
				try{
					st.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		//System.out.print("fdsfdsfds1"+share);
		return new Long(share);
	}
	
//	得到总共访问的次数
	public Long getCount() throws HandlerException{
		
		//DAOFactory factory = new DAOFactory(conn);
		//StatSiteDateDAO mdao = new StatSiteDateDAO();
		//StatSiteDateVO mvo = new StatSiteDateVO();
		long share = 0;
		ResultSet rs = null;
		Statement st = null;

		try {
				String sql =
					" SELECT COUNT(ID) AS ISSHARE FROM STAT_SITE ";
				st=conn.createStatement();
					rs = st.executeQuery(sql);
					if (rs != null) {
						if (rs.next()) {
							share = rs.getLong("ISSHARE");
							
						}
					}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(st!=null){
				try{
					st.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		//System.out.print("fdsfdsfds1"+share);
		return new Long(share);
	}
	
	
	//	得到最大的月的访问次数和月份
	public monthVO getMaxNumber_month() throws HandlerException{
		
		String time =this.getTimeByLong(getMin_time());
		monthVO month = new monthVO();
		month.setmonth("");
		month.setcount(new Long(0));
		
		//while(this.getLongByTime(this.getCurruntMonth(time)).longValue()<=System.currentTimeMillis()){
		int i=0;
		while(this.getLongByTime(this.getCurruntMonth(time)).longValue()<=System.currentTimeMillis()){
			
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		StatSiteDateVO mvo = new StatSiteDateVO();
		String cunMonth=String.valueOf(this.getLongByTime(this.getCurruntMonth(time)).longValue());
		//System.out.println("99wwwwwww**aaw "+cunMonth);
		String nextMonth=String.valueOf(this.getLongByTime(this.getNextMonth(time)).longValue()-1);
		
		mdao.setWhereClause("VIS_DATE>="+cunMonth+" and VIS_DATE<="+nextMonth);
		factory.setDAO(mdao);
		int num2=0;
		long num1=0;
		try 
		{
			List list = factory.find(new StatSiteDateVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteDateVO)it.next();
					num2=mvo.getVisNumber().intValue();
					num1+=num2;		
					}	
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
		if(num1>=month.getcount().longValue())
		   {	
			    month.setcount(new Long(num1));
		        month.setmonth(time);
		   }
		time = this.getNextMonth(time);
		//System.out.println("99wwwwwww**aaw "+time);
		//System.out.println("99wwwwwww**aaw "+cunMonth);
		}
		return (month);
	}
	
	//	得到最大的日访问次数和日期
	public dateVO getMaxNumber_date() throws HandlerException{
		
		String time =this.getTimeByLong(getMin_time());
		dateVO date = new dateVO();
		date.setdate("");
		date.setcount(new Long(0));
		Long timekk =this.getLongByTime(time);
		
		//while(this.getLongByTime(this.getCurruntMonth(time)).longValue()<=System.currentTimeMillis()){
		int i=0;
		while(timekk.longValue()<=System.currentTimeMillis()){
			
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		StatSiteDateVO mvo = new StatSiteDateVO();
		String cun=String.valueOf(timekk.longValue());
		//System.out.println("99wwwwwww**aaw "+cunMonth);
		String next=String.valueOf(timekk.longValue()+24*60*60*1000-1);
		
		mdao.setWhereClause("VIS_DATE>="+cun+" and VIS_DATE<="+next);
		factory.setDAO(mdao);
		int num2=0;
		try 
		{
			List list = factory.find(new StatSiteDateVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteDateVO)it.next();
					num2=mvo.getVisNumber().intValue();
					}	
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
		if(num2>=date.getcount().longValue())
		   {	
			date.setcount(new Long(num2));
			date.setdate(this.getTimeByLong(timekk));
		   }
		timekk = new Long(timekk.longValue()+24*60*60*1000);
		}
		return (date);
	}
	
//	得到最大的时访问次数和日期
	public HourVO getMaxNumber_hour() throws HandlerException{
		
		HourVO hour = new HourVO();
		hour.setdate("");
		hour.setcount(new Long(0));
		hour.sethour(new Long(0));
			
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDate1DAO mdao = new StatSiteDate1DAO();
		StatSiteDate1VO mvo = new StatSiteDate1VO();
		factory.setDAO(mdao);
		
		try 
		{
			List list = factory.find(new StatSiteDate1VO());
			Iterator it = list.iterator();
			Long [] hour1 = new Long[25];  
			
			while(it.hasNext()){
					for(int i=0;i<25;i++){	hour1[i]=new Long(0);}
				        mvo=(StatSiteDate1VO)it.next();
                        hour1[0]=mvo.getH0();hour1[1]=mvo.getH1();hour1[2]=mvo.getH2();hour1[3]=mvo.getH3();hour1[4]=mvo.getH4();hour1[5]=mvo.getH5();
                        hour1[6]=mvo.getH6();hour1[7]=mvo.getH7();hour1[8]=mvo.getH8();hour1[9]=mvo.getH9();hour1[10]=mvo.getH10();hour1[11]=mvo.getH11();
                        hour1[12]=mvo.getH12();hour1[13]=mvo.getH13();hour1[14]=mvo.getH14();hour1[15]=mvo.getH15();hour1[16]=mvo.getH16();hour1[17]=mvo.getH17();
                        hour1[18]=mvo.getH18();hour1[19]=mvo.getH19();hour1[20]=mvo.getH20();hour1[21]=mvo.getH21();hour1[22]=mvo.getH22();hour1[23]=mvo.getH23();
				        hour1[24]=mvo.getVisDate();
				    
				    for(int j=0;j<24;j++)
						   {	
							if(hour1[j].longValue()>=hour.getcount().longValue()){
				    	      hour.setcount(hour1[j]);
				    	      hour.setdate(this.getTimeByLong(hour1[24]));
				    	      hour.sethour(new Long(j));
				    	      }
						   }
					}	
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
		
		return (hour);
	}
	
	//得到总访问次数
	public Long getcount() throws HandlerException{
				
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		StatSiteDateVO mvo = new StatSiteDateVO();
		
		mdao.setWhereClause("VIS_DATE>="+this.getMin_time()+" and VIS_DATE<="+this.getMax_time());
		factory.setDAO(mdao);
		
		int num2=0;
		long num1=0;
		
		try 
		{
			List list = factory.find(new StatSiteDateVO());
			Iterator it = list.iterator();
			while(it.hasNext()){
					mvo=(StatSiteDateVO)it.next();
					num2=mvo.getVisNumber().intValue();
					num1+=num2;
					}	
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
		
		DAOFactory factory1 = new DAOFactory(conn);
		StatSiteDAO mdao1 = new StatSiteDAO();
		StatSiteVO mvo1 = new StatSiteVO();
		long share = 0;
		ResultSet rs = null;
		Statement st = null;
		try {
				String sql =
					" SELECT COUNT(ID) AS ISSHARE FROM STAT_SITE";
					st = conn.createStatement();
					rs = st.executeQuery(sql);
					if (rs != null) {
						if (rs.next()) {
							share = rs.getLong("ISSHARE");
							
						}
					}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(st!=null){
				try{
					st.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return (new Long(num1+share));
	}
	
//	得到全月分析的列表
	public Long[] getAll_monthList() throws HandlerException{
		
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDateDAO mdao = new StatSiteDateDAO();
		StatSiteDateVO mvo = new StatSiteDateVO();
		String starttime = this.getTimeByLong(this.getLongByTime(this.getCurruntyear(this.getTimeByLong(this.getMin_time()))));
		//System.out.println("fffffffffffffff"+starttime);
		Long [] AllMonth =new Long[12];
		for(int j=0;j<12;j++){AllMonth [j] =new Long(0);}
		int i=0;
		
		while(this.getLongByTime(starttime).longValue()<=System.currentTimeMillis())
		{
			if(i==12){i=0;}
			String cunMonth=String.valueOf(this.getLongByTime(this.getCurruntMonth(starttime)).longValue());
			String nextMonth=String.valueOf(this.getLongByTime(this.getNextMonth(starttime)).longValue()-1);
			//System.out.println("fffffffffffffff"+this.getCurruntMonth(starttime));
			mdao.setWhereClause("VIS_DATE>="+cunMonth+" and VIS_DATE<="+nextMonth);
			factory.setDAO(mdao);
			int num2=0;
			long num1=0;
		
		try
		{
			List list = factory.find(new StatSiteDateVO());
			Iterator it = list.iterator();
			System.out.println("fffffffff  size     "+list.size());
			
			while(it.hasNext()){
					mvo=(StatSiteDateVO)it.next();
					num2=mvo.getVisNumber().intValue();
					//System.out.println("fffffffff  num2     "+num2);
					num1=num1+num2;		
					//System.out.println("fffffffff  num1     "+num1);
					}
			AllMonth[i]=new Long(AllMonth[i].longValue()+num1); 
			//System.out.println("fffffffff    allmonth   "+i+"    "+AllMonth[i]);
			starttime = this.getNextMonth(starttime);
			i++;
		}
			
	
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
		
	}return (AllMonth);
	}
	
//	得到全日分析
	public Long [] getAllNumber_hour() throws HandlerException{
			
		DAOFactory factory = new DAOFactory(conn);
		StatSiteDate1DAO mdao = new StatSiteDate1DAO();
		StatSiteDate1VO mvo = new StatSiteDate1VO();
		factory.setDAO(mdao);
		Long [] hour1 = new Long[25]; 
		
		try 
		{
			List list = factory.find(new StatSiteDate1VO());
			Iterator it = list.iterator();
			 
			
			while(it.hasNext()){
					for(int i=0;i<25;i++){	hour1[i]=new Long(0);}
				        mvo=(StatSiteDate1VO)it.next();
                        hour1[0]=new Long(hour1[0].longValue()+mvo.getH0().longValue()); 		hour1[1]=new Long(hour1[1].longValue()+mvo.getH1().longValue());
                        hour1[2]=new Long(hour1[2].longValue()+mvo.getH2().longValue()); 		hour1[3]=new Long(hour1[3].longValue()+mvo.getH3().longValue());
                        hour1[4]=new Long(hour1[4].longValue()+mvo.getH4().longValue()); 		hour1[5]=new Long(hour1[5].longValue()+mvo.getH5().longValue());
                        hour1[6]=new Long(hour1[6].longValue()+mvo.getH6().longValue()); 		hour1[7]=new Long(hour1[7].longValue()+mvo.getH7().longValue());
                        hour1[8]=new Long(hour1[8].longValue()+mvo.getH8().longValue());		hour1[9]=new Long(hour1[9].longValue()+mvo.getH9().longValue());
                        hour1[10]=new Long(hour1[10].longValue()+mvo.getH10().longValue()); 	hour1[11]=new Long(hour1[11].longValue()+mvo.getH11().longValue());
                        hour1[12]=new Long(hour1[12].longValue()+mvo.getH12().longValue()); 	hour1[13]=new Long(hour1[13].longValue()+mvo.getH13().longValue());
                        hour1[14]=new Long(hour1[14].longValue()+mvo.getH14().longValue()); 	hour1[15]=new Long(hour1[15].longValue()+mvo.getH15().longValue());
                        hour1[16]=new Long(hour1[16].longValue()+mvo.getH16().longValue()); 	hour1[17]=new Long(hour1[17].longValue()+mvo.getH17().longValue());
                        hour1[18]=new Long(hour1[18].longValue()+mvo.getH18().longValue()); 	hour1[19]=new Long(hour1[19].longValue()+mvo.getH19().longValue());
                        hour1[20]=new Long(hour1[20].longValue()+mvo.getH20().longValue()); 	hour1[21]=new Long(hour1[21].longValue()+mvo.getH21().longValue());
                        hour1[22]=new Long(hour1[22].longValue()+mvo.getH22().longValue()); 	hour1[23]=new Long(hour1[23].longValue()+mvo.getH23().longValue());
				    
					}	
		}
		catch (Exception e)
		{
			throw new HandlerException(e);
		}
		for(int j=0;j<24;j++)
		   {	
			 hour1[24]=new Long(hour1[24].longValue()+hour1[j].longValue());
		   }
		
		return (hour1);
	}
	
 public void working (){
 	try{
 		long time = System.currentTimeMillis();//-(2)*24*60*60*1000;
		String time1 = getTimeByLong(new Long(time));    
		Long count[] = new Long[25];
		count = getNumber_hour(time1);
		System.out.println("time1"+time1);
		for(int i=0;i<25;i++){System.out.println("count  "+i+"    "+count[i]);}
		
		StatSiteDate1VO vo = new  StatSiteDate1VO();
		StatSiteDateVO vo1 = new  StatSiteDateVO();
		
		vo.setVisDate(getLongByTime(time1));
		vo1.setVisDate(getLongByTime(time1));
		
		vo.setH0(count[0]);vo.setH1(count[1]);vo.setH2(count[2]);vo.setH3(count[3]);
		vo.setH4(count[4]);vo.setH5(count[5]);vo.setH6(count[6]);vo.setH7(count[7]);
		vo.setH8(count[8]);vo.setH9(count[9]);vo.setH10(count[10]);vo.setH11(count[11]);
		vo.setH12(count[12]);vo.setH13(count[13]);vo.setH14(count[14]);vo.setH15(count[15]);
		vo.setH16(count[16]);vo.setH17(count[17]);vo.setH18(count[18]);vo.setH19(count[19]);
		vo.setH20(count[20]);vo.setH21(count[21]);vo.setH22(count[22]);vo.setH23(count[23]);
		
		String string = new String();
		string =String.valueOf(count[24]);
		vo1.setVisNumber(Integer.valueOf(string));
		
	 	addHour(vo);
		add(vo1);
		
		del(time1);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
 	}
 
    //	得到最多的访问地址
	public String getMax_address() throws HandlerException{
		
		String address = "sss";
		long count=0;
		ResultSet rs = null;
		try {
					rs = conn.createStatement().executeQuery(" SELECT MAX(COUNTER) AS KK FROM STAT_SITE_IPNUMBER");
					if (rs != null) {
						if (rs.next()) {
							count =rs.getLong("KK");  						
						}
					}	
					
					rs=null;
					rs = conn.createStatement().executeQuery(" SELECT ADDRESS  FROM STAT_SITE_IPNUMBER WHERE COUNTER="+count);
					if (rs != null) {
						if (rs.next()) {
							address =rs.getString("ADDRESS");				
						}
					}	 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return address;
	}
}

