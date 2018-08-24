package uk.gov.dvla.osg.common.enums;

public enum BatchType {
	CLERICAL ("CLERICAL"),
	FLEET ("FLEET"),
	MULTI ("MULTI"),
	REJECT ("REJECT"),
	REPRINT ("REPRINT"),
	SORTED ("SORTED"),
	SORTING ("SORTING"),
	UNCODED ("UNCODED"),
	UNSORTED ("UNSORTED");
	
	private String batchName;
	
	BatchType(String name) {
		this.batchName = name;
	}
	
	public String getName() {
		return batchName;
	}
}
