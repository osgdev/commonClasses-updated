package uk.gov.dvla.osg.common.enums;

public enum FullBatchType {
	
	CLERICALE("CLERICAL"), 
	CLERICALW("CLERICAL"), 
	FLEETE("FLEET"), 
	FLEETW("FLEET"), 
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
	UNCODEDE("UNCODED"),
	UNCODEDW("UNCODED"),
	UNSORTEDE("UNSORTED"), 
	UNSORTEDW("UNSORTED");
	
	private final String batchType;

	private FullBatchType(String batchType) {
        this.batchType = batchType;
    }

	public String getValue() {
		return this.batchType;
	}
}
