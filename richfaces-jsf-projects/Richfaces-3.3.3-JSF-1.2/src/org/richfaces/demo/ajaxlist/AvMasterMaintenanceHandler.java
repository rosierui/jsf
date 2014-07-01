package org.richfaces.demo.ajaxlist;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.faces.model.SelectItem;


public class AvMasterMaintenanceHandler {

	List<AvReportMaster> 	sameClpnameList; // same CL Name report list
	Set<AvReportMaster> 	selectedSameClpnameSet = new HashSet<AvReportMaster>(); // selected same CL Name report set
	List<AvReportMaster> 	selectedSameClpnameList = new ArrayList<AvReportMaster>(); // selected same CL Name report list

	public AvMasterMaintenanceHandler() {
		sameClpnameList = new ArrayList<AvReportMaster>();
		initData();
		initBean();
		initReportBean();
	}

	private long nextId = 0;
	private long getNextId() {
		return nextId++;
	}

	private void initData() {
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("org/richfaces/demo/ajaxlist/data.txt");
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

    public String cancel() {
        return null;
    }

    public String save() {
    	List<String> data = this.getResult();
    	this.result = null;
        return null;
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
	private List<SelectItem> capitalsOptions = new ArrayList<SelectItem>();
	
        
	public void initBean() {
		this.capitals = new ArrayList<Capital>();
		capitals.add(new Capital("Phoenix", "Arizona"));
		capitals.add(new Capital("Atlanta", "Georgia"));
		capitals.add(new Capital("Honolulu", "Hawaii"));
		capitals.add(new Capital("Boise", "Idaho"));
		
		capitalsOptions.clear();
		for (Capital cap : capitals) {
			capitalsOptions.add(new SelectItem(cap.getName(), cap.getState())); // capital is the key, state is the list option
		}
		
	}

	public List<SelectItem> getCapitalsOptions() {
		return capitalsOptions;
	}

	// =========================== new methods for AvReportMaster
	private ArrayList<AvReportMaster> reportList = new ArrayList<AvReportMaster>();
	private List<SelectItem> reportOptions = new ArrayList<SelectItem>();
	
        
	public void initReportBean() {
		this.reportList = new ArrayList<AvReportMaster>();
		reportList.add(new AvReportMaster(12, "APP030", "APC032", "A/P Paynee"));
		reportList.add(new AvReportMaster(28, "APP032", "APC032", "A/P Checking"));
		reportList.add(new AvReportMaster(37, "APP033", "APC032", "A/P Saving"));
		reportList.add(new AvReportMaster(93, "APP034", "APC032", "A/P Autopay"));
		
		reportOptions.clear();
		for (AvReportMaster cap : reportList) {
			reportOptions.add(new SelectItem(cap.getId(), cap.getProgname() + " " + cap.getArchcdesc()));
		}
		
	}

	public List<SelectItem> getReportOptions() {
		return reportOptions;
	}
	
}
