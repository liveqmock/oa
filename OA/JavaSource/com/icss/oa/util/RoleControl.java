/*
 * Created on 2004-6-24
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.icss.oa.util;

import java.util.ArrayList;
import java.util.List;

import com.icss.resourceone.sdk.framework.Person;
import com.icss.resourceone.sdk.right.AppRole;
import com.icss.resourceone.sdk.right.RightException;
import com.icss.resourceone.sdk.right.RightManager;
import com.icss.resourceone.sdk.right.Role;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class RoleControl {
	public static final String ROLE_info_admin1="info.admin1";
	public static final String ROLE_info_admin2="info.admin2";
	public static final String ROLE_oa_message="oa.sendshortmessage";
	public static void dispatchRole(String personuuid,String roleCode) throws RightException{
		Role role=new Role();
		role.setRolecode(roleCode);
		role=role.getInstanceByKey();
		Person p=new Person();
		try{
			p.setUuid(personuuid);
		}
		catch(Exception e){}
		List personlist=new ArrayList();
		personlist.add(p);
		role.addPersons(personlist);
	}
	public static void deleteRolePerson(String personuuid,String roleCode){
		try{
			Role role=new Role();
			role.setRolecode(roleCode);
			role=role.getInstanceByKey();
			Person p=new Person();
			p.setUuid(personuuid);
			List personlist=new ArrayList();
			personlist.add(p);
			role.removePersons(personlist);
		}
		catch(Exception e){}
	}
	
	public static boolean hasRole(String personuuid,String roleCode){
		try{
			boolean flag = false;
			Person p=new Person();
			p.setUuid(personuuid);
			RightManager r=RightManager.getInstance();
			List roleList=r.findAppRolesByPerson(p);
			for(int i=0;i<roleList.size();i++){
				AppRole role = null;
				role =(AppRole)roleList.get(i);
				if(roleCode.equals(role.getRolecode())){
					
					flag =true;
					}
				}
			
			return flag;
		}
		catch(Exception e){
			return false;
		}
	}
}
