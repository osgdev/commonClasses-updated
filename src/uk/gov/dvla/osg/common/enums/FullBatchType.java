package uk.gov.dvla.osg.common.enums;

/**
 * The Enum FullBatchType.
 */
public enum FullBatchType {
	
	CLERICALE("CLERICAL", "E"), 
	CLERICALW("CLERICAL", "W"), 
	FLEETE("FLEET", "E"), 
	FLEETW("FLEET", "W"), 
	MULTIE("MULTI", "E"), 
	MULTIW("MULTI", "W"), 
	REJECTE("REJECT", "E"),
	REJECTW("REJECT", "W"),
	REPRINTE("REPRINT", "E"),
	REPRINTW("REPRINT", "W"),
	SORTEDE("SORTED", "E"), 
	SORTEDW("SORTED", "W"),
	SORTINGE("SORTING", "E"),
	SORTINGW("SORTING", "W"),
	UNCODEDE("UNCODED", "E"),
	UNCODEDW("UNCODED", "W"),
	UNSORTEDE("UNSORTED", "E"), 
	UNSORTEDW("UNSORTED", "W");

    private final String batchType;
    private final String language;

    /**
     * Gets the.
     *
     * @param batchType the batch type
     * @param lang the lang
     * @return the full batch type
     */
    public static FullBatchType get(BatchType batchType, String lang) {
        return FullBatchType.valueOf(batchType.name()+lang);
    }
    
    /**
     * Instantiates a new full batch type.
     *
     * @param batchType the batch type
     * @param lang the lang
     */
    private FullBatchType(String batchType, String lang) {
        this.batchType = batchType;
        this.language = lang;
    }

}
