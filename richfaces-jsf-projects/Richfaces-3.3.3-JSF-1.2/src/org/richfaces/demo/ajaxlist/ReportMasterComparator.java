package org.richfaces.demo.ajaxlist;


import java.util.Comparator;

public class ReportMasterComparator implements Comparator<AvReportMaster>{
	
	public int compare(AvReportMaster obj1, AvReportMaster obj2) {
		return obj1.getTitle().compareTo(obj2.getTitle());
	}	

}
