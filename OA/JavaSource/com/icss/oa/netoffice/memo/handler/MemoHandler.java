/*
 * Created on 2004-4-10
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.netoffice.memo.handler;

import java.sql.Connection;
import java.sql.Date;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.netoffice.memo.dao.OfficeMemoDAO;
import com.icss.oa.netoffice.memo.vo.OfficeMemoVO;
import com.icss.resourceone.common.logininfo.UserInfo;
import com.icss.resourceone.sdk.framework.Context;
import com.icss.resourceone.sdk.framework.EntityException;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class MemoHandler {

	private Connection conn;

	public MemoHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * �õ����еı���¼�б����������ֶΣ�
	 * @return
	 * @throws RjHandlerException
	 */
	public List getMemoList(String personuuid) throws MemoHandlerException {
		List list = new ArrayList();
		//int k=0;
		DAOFactory factory = new DAOFactory(conn);
		OfficeMemoDAO memoDao = new OfficeMemoDAO();
		memoDao.setMemoOwnerid(personuuid);
		memoDao.addOrderBy("memoTime", true);
		factory.setDAO(memoDao);
		try {
			list = factory.find(new OfficeMemoVO());
		} catch (Exception e) {
			throw new MemoHandlerException(e);
		}
		return list;
	}

	/**
	 * ͨ������¼id�õ�����¼���������ֶΣ�
	 * @return
	 * @throws RjHandlerException
	 */

	public OfficeMemoVO getById(Integer id) {
		DAOFactory factory = new DAOFactory(conn);
		OfficeMemoDAO mDao = new OfficeMemoDAO();
		//fileDao.setConnection(conn);
		mDao.setMemoId(id);
		factory.setDAO(mDao);
		OfficeMemoVO mVO = null;
		try {
			mVO = (OfficeMemoVO) factory.findByPrimaryKey(new OfficeMemoVO());
			System.out.println(mVO.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mVO;
	}

	/**
	 * 
	 * @param time
	 * @return
	 */

	public List getListInTimeSegment(Long time1, Long time2, String personid)
		throws MemoHandlerException {
		List mlist = new ArrayList();
		OfficeMemoDAO mdao = new OfficeMemoDAO();

		mdao.setWhereClause(
			"MEMO_TIME>="
				+ time1
				+ " and MEMO_TIME<"
				+ time2
				+ "  and MEMO_OWNERID='"
				+ personid
				+ "'");

		DAOFactory factory = new DAOFactory(conn);
		factory.setDAO(mdao);
		try {
			mlist = factory.find(new OfficeMemoVO());
		} catch (Exception e) {
			System.err.println(e.getMessage());
			throw new MemoHandlerException(e);
		}
		return mlist;

	}

	/**
	 * ��long��ʱ��ת��Ϊ"yyyy.MM.dd ' 'HH:mm  "����ʽ
	 * @param time
	 * @return
	 */
	public static String getTime(long time) {
		SimpleDateFormat formatter =
			new SimpleDateFormat("yyyy.MM.dd ' 'HH:mm  ");
		return formatter.format(new Date(time));
	}

	/**
	 * ����µı���¼
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window - Preferences - Java - Code Generation - Code and Comments
	 */

	public void addMemo(OfficeMemoVO mVO) throws MemoHandlerException {

		OfficeMemoDAO mDao = new OfficeMemoDAO();
		mDao.setConnection(conn);
		mDao.setValueObject(mVO);
		try {
			mDao.create();
		} catch (Exception e) {
			throw new MemoHandlerException(e);
		}
	}

	/**
	 * ����ָ����vo���и��£�������vo�е�id���ǵ�ǰ��memo��id
	*/
	public void updateMemo(OfficeMemoVO newMVO) throws MemoHandlerException {

		OfficeMemoDAO mDao = new OfficeMemoDAO();
		mDao.setConnection(conn);
		mDao.setValueObject(newMVO);
		try {
			mDao.update(true);
		} catch (Exception e) {
			System.err.println(
				"===================================" + e.getMessage());
			throw new MemoHandlerException(e);
		}
	}

	/**
	* ɾ��ָ�����ռ�
	* @param tuuid
	* @throws ServiceTypeMaintenanceHandlerException
	*/
	public void deleteMemo(Integer muuid) throws MemoHandlerException {

		OfficeMemoDAO memoDao = new OfficeMemoDAO();
		memoDao.setConnection(conn);
		memoDao.setMemoId(muuid);

		try {
			memoDao.delete();
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw new MemoHandlerException(e);
		}
	}
	/**
	 * ���ַ���ת��Ϊlong
	 * @param time
	 * @return long
	 */
	public static long getLongByTime(String time) throws Exception {
		SimpleDateFormat formatter =
			new SimpleDateFormat("yyyy.MM.dd ' 'HH:mm  ");
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = formatter.parse(time, pos);
		if (date == null)
			throw new Exception("the date/time string can not parse");
		return date.getTime();
	}
	public static long getLongByTime2(String time) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		java.util.Date date = formatter.parse(time, pos);
		if (date == null)
			throw new Exception("the date/time string can not parse");
		return date.getTime();
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
	/**
	 * get user name
	 * @author Administrator
	 *
	 * To change the template for this generated type comment go to
	 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
	 */
	public String getUserName() {
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

}
