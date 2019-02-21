package uk.gov.dvla.osg.common.enums;

public enum EnvelopeType {
    
    DEFAULTE("DEFAULT", "E"), 
    DEFAULTW("DEFAULT", "W"), 
    UNSORTEDE("UNSORTED", "E"), 
    UNSORTEDW("UNSORTED", "W"), 
    OCRE("OCR", "E"),
    OCRW("OCR", "W"),
    MME("MMM", "E"), 
    MMW("MM", "W"), 
    UNCODEDE("UNCODED","E"),
    UNCODEDW("UNCODED","W");

    private final String product;
    private final String language;

    public static FullBatchType get(Product product, String lang) {
        return FullBatchType.valueOf(product.name()+lang);
    }
    
    public static FullBatchType get(String product, String lang) {
        return FullBatchType.valueOf(product+lang);
    }
    
    private EnvelopeType(String product, String lang) {
        this.product = product;
        this.language = lang;
    }

    public String getBatchType() {
        return this.product;
    }
    
    public String getLanguage() {
        return this.language;
    }
}
