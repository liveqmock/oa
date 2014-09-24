package com.icss.oa.zbs.yjsduty.handler;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.zbs.yjsduty.dao.TbYjsWorkinfotypeDAO;
import com.icss.oa.zbs.yjsduty.vo.TbYjsWorkinfotypeVO;

public class YjsDutyHanler {

	private Connection conn;

	public YjsDutyHanler(Connection conn) {
		this.conn = conn;
	}

	public HashMap getDutyName() throws DAOException {

		TbYjsWorkinfotypeDAO dao = new TbYjsWorkinfotypeDAO();
		DAOFactory f = new DAOFactory(conn);
		f.setDAO(dao);

		List list = f.find(new TbYjsWorkinfotypeVO());

		HashMap map = new HashMap();
		Iterator it = list.iterator();
		while (it.hasNext()) {
			TbYjsWorkinfotypeVO vo = (TbYjsWorkinfotypeVO) it.next();
			map.put(vo.getWitId(), vo.getWitName());
		}

		return map;

	}

}