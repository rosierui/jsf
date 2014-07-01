package org.richfaces.demo.ajaxlist;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.FacesException;
import javax.faces.model.SelectItem;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.xmlrules.DigesterLoader;
import org.xml.sax.SAXException;

/**
 * Almost OK
 *
 * @author jonathan
 *
 */

public class AvMasterMaintenanceHandler1 {

	List<AvReportMaster> 	sameClpnameList; // same CL Name report list
	Set<AvReportMaster> 	selectedSameClpnameSet = new HashSet<AvReportMaster>(); // selected same CL Name report set
	List<AvReportMaster> 	selectedSameClpnameList = new ArrayList<AvReportMaster>(); // selected same CL Name report list

	public AvMasterMaintenanceHandler1() {
		sameClpnameList = new ArrayList<AvReportMaster>();
		initData();
		initBean();
	}

	private long nextId = 0;
	private long getNextId() {
		return nextId++;
	}

	private void initData() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("org/richfaces/demo/tree/data.txt");
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] rb = new byte[1024];
		int read;
		try {
			do {
				read = is.read(rb);
				if (read>0) {
					os.write(rb, 0, read);
				}
			} while (read>0);
			String buf = os.toString();
			StringTokenizer toc1 = new StringTokenizer(buf,"\n");
			while (toc1.hasMoreTokens()) {
				String str = toc1.nextToken();
				StringTokenizer toc2 = new StringTokenizer(str, "\t");
				String songTitle = toc2.nextToken();
				String artistName = toc2.nextToken();
				toc2.nextToken();
				toc2.nextToken();
				AvReportMaster song = new AvReportMaster(getNextId());
				song.setTitle(songTitle);
				song.setArtistName(artistName);
sameClpnameList.add(song);				
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// ============================================ new methods for ECM 1302920
	public List<AvReportMaster> getSameClpnameList() {
		return sameClpnameList;
	}
	public void setSameClpnameList(List<AvReportMaster> reportList) {
		this.sameClpnameList = reportList;
	}

	public void takeSelection() {
		selectedSameClpnameList.clear();
		selectedSameClpnameList.addAll(selectedSameClpnameSet);
		Collections.sort(selectedSameClpnameList, new ReportMasterComparator());
	}
	
	public Set<AvReportMaster> getSelectedSameClpnameSet() {
		return selectedSameClpnameSet;
	}
	
	public void setSelectedSameClpnameSet(Set<AvReportMaster> selectedReportSet) {
		this.selectedSameClpnameSet = selectedReportSet;
	}
	
	public List<AvReportMaster> getSelectedSameClpnameList() {
		return selectedSameClpnameList;
	}
	
	public void setSelectedSameClpnameList(List<AvReportMaster> selectedReportList) {
		this.selectedSameClpnameList = selectedReportList;
	}

	// ============================ new methods picklist, from PickListBean.java
	private List<String> result;


	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}

	public Integer getItems() {
		if (result == null){
			return 0;
		}
		return result.size();
	}
	// =========================== new methods picklist, from CaplitalsBean.java
	private ArrayList<Capital> capitals = new ArrayList<Capital>();
	private ArrayList<String> capitalsNames = new ArrayList<String>();
	private List<SelectItem> capitalsOptions = new ArrayList<SelectItem>();
    private String capital = ""; 
	
    private String currentStateFilterValue;
    private String currentNameFilterValue;
    
	public List<Capital> autocomplete(Object suggest) {
        String pref = (String)suggest;
        ArrayList<Capital> result = new ArrayList<Capital>();

        Iterator<Capital> iterator = getCapitals().iterator();
        while (iterator.hasNext()) {
            Capital elem = ((Capital) iterator.next());
            if ((elem.getName() != null && elem.getName().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
            {
                result.add(elem);
            }
        }
        return result;
    }
    
	public void initBean() {
		URL rulesUrl = getClass().getResource("capitals-rules.xml");
		Digester digester =	DigesterLoader.createDigester(rulesUrl);
		digester.push(this);
		try {
			digester.parse(getClass().getResourceAsStream("capitals.xml"));
		} catch (IOException e) {
			throw new FacesException(e);
		} catch (SAXException e) {
			throw new FacesException(e);
		}
		capitalsNames.clear();
		for (Capital cap : capitals) {
			capitalsNames.add(cap.getName());
		}
		capitalsOptions.clear();
		for (Capital cap : capitals) {
			capitalsOptions.add(new SelectItem(cap.getName(),cap.getState())); // capital is the key, state is the list option
		}
	}
	
//	public void resetFilter() {
//		setCurrentNameFilterValue("");
//		setCurrentStateFilterValue("");
//	}
	
	public String addCapital(Capital capital) {
		capitals.add(capital);
		return null;
	}
	
	public ArrayList<Capital> getCapitals() {
		return capitals;
	}

//	public String getCapital() {
//		return capital;
//	}

//	public void setCapital(String capital) {
//		this.capital = capital;
//	}

	public List<SelectItem> getCapitalsOptions() {
		return capitalsOptions;
	}

//	public ArrayList<String> getCapitalsNames() {
//		return capitalsNames;
//	}

//	public String getCurrentStateFilterValue() {
//		return currentStateFilterValue;
//	}
//
//	public void setCurrentStateFilterValue(String currentStateFilterValue) {
//		this.currentStateFilterValue = currentStateFilterValue;
//	}

//	public String getCurrentNameFilterValue() {
//		return currentNameFilterValue;
//	}
//
//	public void setCurrentNameFilterValue(String currentNameFilterValue) {
//		this.currentNameFilterValue = currentNameFilterValue;
//	}
	
}
