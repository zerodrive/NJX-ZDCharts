/*
 * CHARTHandler.java
 * Version: 0.8
 * Copyright 2015 Thomas Schwade
 * http://www.zerodrive.net
 * Licensed under the EUPL V.1.1
 * https://github.com/zerodrive/NJX-ZDCharts/blob/master/LICENSE.pdf
 */

package net.zerodrive.njx.controls.charts;

import org.xml.sax.AttributeList;

import com.softwareag.cis.gui.generate.ITagHandler;
import com.softwareag.cis.gui.generate.IXSDGenerationHandler;
import com.softwareag.cis.gui.protocol.Message;
import com.softwareag.cis.gui.protocol.ProtocolItem;
import com.softwareag.cis.gui.util.ControlSelection;

public class CHARTHandler implements ITagHandler {
	String m_charttype;
	String m_valueprop;
	String m_selectedprop;
	String m_width;
	String m_height;
	String m_clickmethod;
	String m_chartstyle;
	boolean m_responsive;

	@Override
	public void generateHTMLForStartTag(int id, String tagName,
			AttributeList attrlist, ITagHandler[] handlersAbove,
			StringBuffer sb, ProtocolItem protocolItem) {
		readAttributes(attrlist);
		checkAttributes(protocolItem);
		addDataBinding(protocolItem);
		generateLayoutHTML(id, protocolItem, sb);
	}

	@Override
	public void generateHTMLForEndTag(String tagName, StringBuffer sb) {
		sb.append("\n<!-- CHART end -->\n");
	}

	@Override
	public void generateJavaScriptForInit(int id, String tagName,
			StringBuffer sb) {
		sb.append("csciframe.registerListener(romu" + id + ");\n");
	}

	// =========================================================================
	// private methods
	// =========================================================================

	private void readAttributes(AttributeList attr) {
		m_charttype = null;
		m_valueprop = null;
		m_selectedprop = null;
		m_width = null;
		m_height = null;
		m_clickmethod = null;
		m_chartstyle = "";
		m_responsive = false;

		for (int i = 0; i < attr.getLength(); i++) {
			if (attr.getName(i).equals("charttype"))
				m_charttype = attr.getValue(i);
			if (attr.getName(i).equals("valueprop"))
				m_valueprop = attr.getValue(i);
			if (attr.getName(i).equals("selectedprop"))
				m_selectedprop = attr.getValue(i);
			if (attr.getName(i).equals("width"))
				m_width = attr.getValue(i);
			if (attr.getName(i).equals("height"))
				m_height = attr.getValue(i);
			if (attr.getName(i).equals("clickmethod"))
				m_clickmethod = attr.getValue(i);
			if (attr.getName(i).equals("responsive")) {
				if (attr.getValue(i).equals("true")) {
					m_responsive = true;
				}
			}
		}
	}

	private void checkAttributes(ProtocolItem pi) {
		if (m_valueprop == null)
			pi.addMessage(new Message(Message.TYPE_ERROR,
					"Attribute valueprop is not set"));
		if (m_charttype == null)
			pi.addMessage(new Message(Message.TYPE_ERROR,
					"Attribute charttype is not set"));
		if (m_width == null)
			pi.addMessage(new Message(Message.TYPE_ERROR,
					"Attribute width is not set"));
		if (m_height == null)
			pi.addMessage(new Message(Message.TYPE_ERROR,
					"Attribute height is not set"));
	}

	private void addDataBinding(ProtocolItem pi) {
		IXSDGenerationHandler xgh = pi.findXSDGenerationHandler();
		if (xgh == null) {
			pi.addMessage(new Message(Message.TYPE_ERROR,
					"XSDGenerationHandler not found"));
		} else {
			xgh.addAdapterData(pi, m_valueprop, "String");
			xgh.addAdapterData(pi, m_selectedprop, "String");
			xgh.addAdapterEvent(pi, m_clickmethod);
		}

		// Load Javascript libraries
		pi.addJsLib("../ZDCHARTS_UI/Chart.js/Chart.min.js");
		pi.addJsLib("../ZDCHARTS_UI/js/CHARTControls.js");
		
		// Property usage
		pi.addProperty(m_valueprop);
		pi.addProperty(m_selectedprop);
		pi.addMethod(m_clickmethod);
	}

	private void generateLayoutHTML(int id, ProtocolItem protocolItem,
			StringBuffer html) {
		html.append("\n<!-- CHART begin -->\n");
		if (! m_responsive)
			html.append("<td class='TDAroundControl' width=0 rowspan=1 colspan=1 align='center' valign='middle'>\n");
		html.append("<canvas" + " id='IO_" + id + "'" + " width='" + m_width + "'"
				+ " height='" + m_height + "'" + ">");
		html.append("</canvas>\n");
		if (! m_responsive)
			html.append("</td>\n");
		if (m_responsive) {
			html.append("<style type='text/css'>\n");
			html.append("canvas {height: auto !important;width: 100% !important;}\n");
			html.append("</style>\n");
		}
		html.append("<script>\n");
		html.append("function romu" + id + "(model)\n");
        html.append("{\n");
        html.append("var cc = document.getElementById('IO_" + id + "');\n");
        html.append("C.romuCHCHART2(cc" + "," + "'" + m_charttype + "'" + "," + "'"	+ m_valueprop + "'"
        		+ "," + "'" + m_selectedprop + "'" + "," + "'" + m_clickmethod + "'" + ")\n");
        html.append("}\n");
		html.append("</script>\n");
	}
}
