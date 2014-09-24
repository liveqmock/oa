/*
 * Created on 2004-7-9
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.icss.oa.schedule;

import java.io.IOException;

import org.jdom.Element;
import org.jdom.JDOMException;

/**
 * @author lichg
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public interface ScheduleManagerIfc {
	Element configuation(String configFile) throws JDOMException, IOException;
}
