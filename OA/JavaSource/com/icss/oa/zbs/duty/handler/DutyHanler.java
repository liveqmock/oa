package com.icss.oa.zbs.duty.handler;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.zbs.duty.dao.TbZbsWorkinfotypeDAO;
import com.icss.oa.zbs.duty.vo.TbZbsWorkinfotypeVO;

public class DutyHanler {

	private Connection conn;

	public DutyHanler(Connection conn) {
		this.conn = conn;
	}

	public HashMap getDutyName() throws DAOException {

		TbZbsWorkinfotypeDAO dao = new TbZbsWorkinfotypeDAO();
		DAOFactory f = new DAOFactory(conn);
		f.setDAO(dao);

		List list = f.find(new TbZbsWorkinfotypeVO());

		HashMap map = new HashMap();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			TbZbsWorkinfotypeVO vo = (TbZbsWorkinfotypeVO) it.next();
			map.put(vo.getWitId(), vo.getWitName());
		}

		return map;

	}

}