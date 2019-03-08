package uk.gov.dvla.osg.common.enums;

/**
 * The Enum BatchType.
 */
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
	
	/**
	 * Instantiates a new batch type.
	 *
	 * @param name the name
	 */
	private BatchType(String name) {
		this.batchName = name;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return batchName;
	}
}
