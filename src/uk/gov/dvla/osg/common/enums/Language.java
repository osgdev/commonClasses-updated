package uk.gov.dvla.osg.common.enums;

public enum Language {
 E ("English"),
 W ("Welsh");
	
	private String language;
	
	Language(String language) {
		this.language = language;
	}
	
	public String getValue() {
		return this.language;
	}
}
