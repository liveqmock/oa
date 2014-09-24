package com.icss.oa.address.handler;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Comparator;

public class CollatorComparator implements Comparator{

	Collator collator = Collator.getInstance();
	public int compare(Object arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		CollationKey key1 = collator.getCollationKey(arg0.toString());
		CollationKey key2 = collator.getCollationKey(arg1.toString());
		
		return key1.compareTo(key2);
	}
	
}