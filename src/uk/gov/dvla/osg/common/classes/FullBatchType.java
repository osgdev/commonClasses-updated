package uk.gov.dvla.osg.common.classes;

public enum FullBatchType {
	FLEETE("FLEET"), 
	FLEETW("FLEET"), 
	CLERICALE("CLERICAL"), 
	CLERICALW("CLERICAL"), 
	MULTIE("MULTI"), 
	MULTIW("MULTI"), 
	REJECTE("REJECT"),
	REJECTW("REJECT"),
	REPRINTE("REPRINT"),
	REPRINTW("REPRINT"),
	SORTEDE("SORTED"), 
	SORTEDW("SORTED"),
	SORTINGE("SORTING"),
	SORTINGW("SORTING"),
	UNSORTEDE("UNSORTED"), 
	UNSORTEDW("UNSORTED");
	
	private final String batchType;

	private FullBatchType(String batchType) {
        this.batchType = batchType;
    }
/*	// OVERLOADED CONSTRUCTOR??
	private FullBatchType(BatchType batchType, String lang) {
        this.batchType = batchType.name()+lang;
    }*/
/*	// OVERLOADED CONSTRUCTOR??
	FullBatchType(BatchType batchType, Language lang) {
        this.batchType = batchType.name()+lang;
    }*/
	public String getValue() {
		return this.batchType;
	}
}
