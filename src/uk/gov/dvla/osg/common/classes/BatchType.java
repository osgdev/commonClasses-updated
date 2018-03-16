package uk.gov.dvla.osg.common.classes;

public enum BatchType {
	FLEET ("FLEET"),
	CLERICAL ("CLERICAL"),
	MULTI ("MULTI"),
	REJECT ("REJECT"),
	REPRINT ("REPRINT"),
	SORTED ("SORTED"),
	SORTING ("SORTING"),
	UNSORTED ("UNSORTED");
	
	private String batchName;
	
	BatchType(String name) {
		this.batchName = name;
	}
	
	public String batchName() {
		return batchName;
	}
}
