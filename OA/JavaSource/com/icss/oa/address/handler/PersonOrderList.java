package com.icss.oa.address.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.icss.resourceone.sdk.framework.EntityException;
import com.icss.resourceone.sdk.framework.Person;

/**
 * @author lizb
 * ��ȡ�õ��û���������
 */
public class PersonOrderList {
	/**
	 * ���û�������������(����)
	 * @param personList �û���List
	 * @return ������List
	 */
	public static List personList(List personList) {

		CollatorComparator comparator = new CollatorComparator();
		TreeMap personMap = new TreeMap(comparator);

		for (int i = 0; i < personList.size(); i++) {

			Person person = (Person) personList.get(i);

			try {
				//�õ��û���
				String personName = person.getFullName();
				
				//System.out.println(personName);
				setPerson(personMap, personName, person);

			} catch (EntityException e) {
				e.printStackTrace();
			}
		}

		Object[] keys = personMap.keySet().toArray();
		List reList = new ArrayList();

		for (int i = 0; i < keys.length; i++) {
			reList.add(personMap.get(keys[i]));
		}

		return reList;
	}
	
	private static void setPerson(TreeMap personMap, String personName, Person person) {

		int i=0;
		String newPersonName = new String(personName);

		while(true) {
			if (personMap.get(newPersonName) == null) {
				personMap.put(newPersonName, person);
				break;
			} else {
				newPersonName =personName + i;
				i++;
			}
		}
	}
}
