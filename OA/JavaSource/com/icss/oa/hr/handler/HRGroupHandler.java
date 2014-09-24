package com.icss.oa.hr.handler;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.icss.j2ee.dao.DAOException;
import com.icss.j2ee.dao.DAOFactory;
import com.icss.oa.hr.admin.HRGroupWebservice;
import com.icss.oa.hr.dao.HRSysPersonDAO;
import com.icss.oa.hr.vo.HRGroupVO;
import com.icss.oa.hr.vo.HRSysPersonVO;

public class HRGroupHandler {

	private Connection conn;

	public HRGroupHandler() {
	}

	public HRGroupHandler(Connection conn) {
		this.conn = conn;
	}

	/**
	 * 根据groupid取得所有用户
	 * @param groupid
	 * @return
	 */
	public List getAllUuidByGroupid(String groupid) {

		HRGroupWebservice hw = new HRGroupWebservice();
		String hr = hw.GetAllPerson(groupid);


		List personlist = new ArrayList();

		try {
			Document document = DocumentHelper.parseText(hr);
			Element rootElement = document.getRootElement();

			for (Iterator i_pe = rootElement.elementIterator(); i_pe.hasNext();) {
				Element e_pe = (Element) i_pe.next();
				String hrid = e_pe.element("HRID").getText();
				
				System.out.println("HRID= "+hrid);
				HRSysPersonVO vo = getPersonByHrid(hrid);
				if(vo!=null){
				personlist.add(getPersonByHrid(hrid));
				}

			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return personlist;
	}

	/**
	 * 根据hrid 取得用户
	 * @param hrid
	 * @return
	 * @throws DAOException
	 */
	public HRSysPersonVO getPersonByHrid(String hrid) throws DAOException {

		List list = null;
		
		DAOFactory factory = new DAOFactory(conn);
		HRSysPersonDAO dao = new HRSysPersonDAO();
		dao.setHrid(hrid);
		factory.setDAO(dao);

		list = factory.find(new HRSysPersonVO());

		if (list != null) {
			if (list.size() > 0) {
				HRSysPersonVO vo = (HRSysPersonVO) list.get(0);
				return vo;
			}
		}

		return null;
	}
	
	/**
	 * 根据parentid取得下面的分组
	 * @param parentid
	 * @return
	 */
	public List getGroupByParentid(String parentid){
		List list = new ArrayList();
		HRGroupWebservice hw = new HRGroupWebservice();
		String group = hw.GetPersonGroup(); 
	
		try {
			Document document = DocumentHelper.parseText(group);
			Element rootElement = document.getRootElement();

			for (Iterator i_pe = rootElement.elementIterator(); i_pe.hasNext();) {
				Element e_pe = (Element) i_pe.next();
				String id = e_pe.element("PARENTID").getText();
				if(e_pe.element("PARENTID").getText().equalsIgnoreCase(parentid)){
					HRGroupVO vo = new HRGroupVO();
					vo.setGroupid(e_pe.element("GROUPID").getText());
					vo.setOrgname(e_pe.element("ORGNAME").getText());
					vo.setDescription(e_pe.element("DESCRIPTION").getText());
					vo.setParentid(e_pe.element("PARENTID").getText());
					
					list.add(vo);
				}
				

			}

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}