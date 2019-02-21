package uk.gov.dvla.osg.common.enums;

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

    public static FullBatchType get(BatchType batchType, String lang) {
        return FullBatchType.valueOf(batchType.name()+lang);
    }
    
    public static FullBatchType get(String batchType, String lang) {
        return FullBatchType.valueOf(batchType+lang);
    }
    
    private FullBatchType(String batchType, String lang) {
        this.batchType = batchType;
        this.language = lang;
    }

    public String getBatchType() {
        return this.batchType;
    }
    
    public String getLanguage() {
        return this.language;
    }
}
