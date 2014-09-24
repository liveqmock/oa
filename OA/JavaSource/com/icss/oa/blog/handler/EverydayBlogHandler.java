/*
 * Created on 2004-12-7
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.blog.handler;
/*
 * Created on 2003-12-26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
//import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Iterator;
import java.util.*;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import com.icss.oa.blog.dao.*;
import com.icss.oa.blog.vo.*;
import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class EverydayBlogHandler {
	private Connection conn;
	public EverydayBlogHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * get EverydayBlog list
	 * @author Administrator
	 *
	 */
	public  List getEverydayBlogList() {
		
		EverydayBlogDAO dao = new EverydayBlogDAO();
		DAOFactory 		factory = new DAOFactory(conn);
		List 			list = null;
		dao.addOrderBy("id",true);
		factory.setDAO(dao);		
		try {
			list = factory.find(new EverydayBlogVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	public Integer getRandomBlogID(int max){		
		Integer		RandomBlogID = null;
		int 		RandomNum;
		int 		Len=0;//

		//EverydayBlogVO vo = new EverydayBlogVO();
		if (max<0){//如果表里没有数据，则返回null
			RandomBlogID = new Integer(0);
		}
		else{//如果表里有数据
			Random random1 = new Random();
			RandomNum = Math.abs(random1.nextInt())%max+1;
			RandomBlogID = new Integer(RandomNum);//取一个0到List.lenght之间的随机数
			//vo = (EverydayBlogVO)list.get(RandomBlogID);//将随机数所在位置的EverydayBlogVO取出
		}
		return RandomBlogID;
	}
    
	public EverydayBlogVO getNextBlogVO(List list,int station){		
		
		int Len=0;//
		EverydayBlogVO vo = new EverydayBlogVO();
		if (list.isEmpty()){//如果List里没有数据，则返回null
			return null;
		}
		else{//如果List里有数据
			Len = list.size();	//取得List的总记录数		
			System.out.println("Len = "+Len);
			if (station==Len){
				station = 0;
			}
			vo = (EverydayBlogVO)list.get(station+1);//将station所在位置的下一个EverydayBlogVO取出
		}
		return vo;
	}
	
	
	public void addEverydayBlog(EverydayBlogVO vo) {
		EverydayBlogDAO dao = new EverydayBlogDAO();
		dao.setId(vo.getId());
		dao.setBlogname(vo.getBlogname());
		dao.setBlogcontent(vo.getBlogcontent());
		dao.setBlogdata(vo.getBlogdata());
		dao.setConnection(conn);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	
	public void addEverydayBlog(Integer id, String name, String Blogcontent ) {
		EverydayBlogDAO dao = new EverydayBlogDAO(conn);
		dao.setId(id);
		dao.setBlogname(name);
		dao.setBlogcontent(Blogcontent);
		try {
			dao.create();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public String getUserId() {
		Context ctx;
		UserInfo user = null;
		try {
			ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		if (user != null)
			return user.getPersonUuid();
		else
			return null;
	}
	
	public String getUserCnName() {
		Context ctx;
		UserInfo user = null;
		try {
			ctx = Context.getInstance();
			user = ctx.getCurrentLoginInfo();
		} catch (EntityException e) {
			e.printStackTrace();
		}
		if (user != null)
			return user.getCnName();
		else
			return null;
	}
	
	/**
	 * 通过贴子id得到贴子（包含大字段）
	 * @return
	 * @throws RjHandlerException
	 */
	
	public EverydayBlogVO getById(Integer id) {
		DAOFactory factory = new DAOFactory(conn);
		EverydayBlogDAO dao= new EverydayBlogDAO();
		//fileDao.setConnection(conn);
		dao.setId(id);
		factory.setDAO(dao);
		EverydayBlogVO vo=null;
		try {
			vo=(EverydayBlogVO)factory.findByPrimaryKey(new EverydayBlogVO());
			System.out.println("vovovovovovo= "+vo.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}  
	/**
	 * 将字符串转换为long
	 * @param time
	 * @return long
	 */
	public static long getLongByTime(String time) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = formatter.parse(time);
		if (date == null)
			throw new Exception("the date/time string can not parse");
		return date.getTime();
	}
	
	/**
	 * 根据指定的vo进行更新，其中新vo中的id由是当前行日记的id
    */
	public void UpdateEverydayBlogVO(EverydayBlogVO newVO)
		throws EverydayBlogException {

		EverydayBlogDAO Dao = new EverydayBlogDAO();
		Dao.setConnection(conn);
		Dao.setValueObject(newVO);
		try {
			Dao.update(true);
		} catch (Exception e) {
			System.err.println("==================================="+e.getMessage());
			throw new EverydayBlogException(e);
		}
	}

	/**返回查询结果集
	 */
	public List getSearchList(String queryname) {
		EverydayBlogDAO dao = new EverydayBlogDAO();
		if(queryname!=null&&!queryname.equals("")){
			dao.setWhereClause("BLOGNAME LIKE '%"+queryname+"%' ");
		}
		DAOFactory factory = new DAOFactory(conn);
		
		factory.setDAO(dao);
		List list = null;
		try {
			list = factory.find(new EverydayBlogVO());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @param blogid
	 */
	public void DelEverydayBlog(Integer blogid) throws EverydayBlogException {
		// TODO Auto-generated method stub
		EverydayBlogDAO dao = new EverydayBlogDAO();
		dao.setConnection(conn);
		dao.setId(blogid);

		try {
			dao.delete();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw new EverydayBlogException(e);
		}

	}

	public int getCounts(){		
		String strCount;
		int intCount = 0;
		Statement stm = null;
		ResultSet rs = null;
		try {
			stm = conn.createStatement();
			String sql = "select count(*) as counts from RO_EVERYDAYBLOG";
			rs = stm.executeQuery(sql);
			rs.next();
			strCount = rs.getString("counts");
			
			intCount = Integer.parseInt(strCount);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(rs!=null){
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if(stm!=null){
				try {
					stm.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return intCount;
	}
	
	
	public void setIsShow(boolean isshow,String userid,String username){
		Statement stm = null;
		try {
			stm = conn.createStatement();
			String updatesql1 = "update ro_setblog set isshow='1' where userid='"+userid+"'";
			String updatesql2 = "update ro_setblog set isshow='0' where userid='"+userid+"'";
			String Addsql1 = "INSERT INTO Ro_Setblog (userid, username,isshow) VALUES ('"+userid+"','"+username+"','1')";
			String Addsql2 = "INSERT INTO Ro_Setblog (userid, username,isshow) VALUES ('"+userid+"','"+username+"','0')";

			if (isshow){
				if ((stm.executeUpdate(updatesql1))<=0){
					int updateint = stm.executeUpdate(Addsql1);
				}
			}
			else{
				if (stm.executeUpdate(updatesql2)<=0){
					stm.executeUpdate(Addsql2);
				}
				
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if(stm!=null){
				try {
					stm.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}

