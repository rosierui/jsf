package demo;

public class GenericBean {

	private String name = "";
//	private String toolTipContent = "http://docs.jboss.org/richfaces/latest_3_3_X/en/devguide/html/rich_toolTip.html";
	private String toolTipContent = "Your tips here";

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getToolTipContent() {
		return toolTipContent;
	}
	public void setToolTipContent(String toolTipContent) {
		if (toolTipContent != null)
			this.toolTipContent = toolTipContent;
	}
}