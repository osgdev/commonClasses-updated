package uk.gov.dvla.osg.common.enums;

public enum Site {
	
	F("FFORESTFACH"),
	M("MORRISTON"),
	x("NO SITE"),
	xx("NO GROUP");
	
	private final String site;

	Site(String site) {
        this.site = site;
    }

	public String getInitial() {
		return this.site;
	}
}
