/*
 * �������� 2006-10-17
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.icss.oa.multivote.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ����
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class DEPARTMENT_CODE {
	public static final int DEP_NUM = 10;
	public static final String DEP_1 = "�������";
	public static final String DEP_2 = "ֱ����ҵ";
	public static final String DEP_3 = "ֱ����ҵ";
	public static final String DEP_4 = "��������";
	public static final String DEP_5 = "��������";
	public static final String DEP_6 = "��������";
	public static final String DEP_7 = "���ϵ���";
	public static final String DEP_8 = "���ϵ���";
	public static final String DEP_9 = "��������";
	public static final String DEP_10 = "פ�����";
	public static final String GET_DEP_NAME(int i){
		switch(i){		
			case(0):
			return DEP_1;
			
			case(1):
			return DEP_2;
			
			case(2):
			return DEP_3;
			
			case(3):
			return DEP_4;
			
			case(4):
			return DEP_5;
			
			case(5):
			return DEP_6;
			
			case(6):
			return DEP_7;
			
			case(7):
			return DEP_8;
			
			case(8):
			return DEP_9;
			
			case(9):
				return DEP_10;
			
			default:
			return "ERROR!";
		}
	}
}
