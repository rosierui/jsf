package org.richfaces.demo.ajaxlist;


/**
 * 
 * Renamed Song to AvReportMaster
 *
 */
@SuppressWarnings("serial")
public class AvReportMaster  implements java.io.Serializable {

	private long id;
	
	String 		progname; // Required Input *
	String		clpname;
	String 		archcdesc;	// Required Input *
	
	private String title;
	private String genre;
	private int trackNumber;
	private String artistName;

	
	public AvReportMaster(long id,String progname, String clpname, String desc) {
		this.id = id;
		this.progname = progname;
		this.clpname = clpname;
		this.archcdesc = desc;
	}	

	public AvReportMaster(long id,String title,int num) {
		this.id=id;
		this.title=title;
		this.trackNumber=num;
	}	
	
	public AvReportMaster(long id) {
		this.id = id;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(int trackNumber) {
		this.trackNumber = trackNumber;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getType() {
		return "song";
	}	

	public String getArtistName() {
		return artistName;
	}

	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
	public String getProgname() {
		return progname;
	}

	public void setProgname(String progname) {
		this.progname = progname;
	}

	public String getClpname() {
		return clpname;
	}

	public void setClpname(String clpname) {
		this.clpname = clpname;
	}

	public String getArchcdesc() {
		return archcdesc;
	}

	public void setArchcdesc(String archcdesc) {
		this.archcdesc = archcdesc;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + trackNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AvReportMaster other = (AvReportMaster) obj;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (trackNumber != other.trackNumber)
			return false;
		return true;
	}
	
}
