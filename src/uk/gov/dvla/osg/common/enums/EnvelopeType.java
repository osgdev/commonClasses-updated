package uk.gov.dvla.osg.common.enums;

/**
 * The Enum EnvelopeType.
 */
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

    /**
     * Gets the.
     *
     * @param product the product
     * @param lang the lang
     * @return the full batch type
     */
    public static FullBatchType get(Product product, String lang) {
        return FullBatchType.valueOf(product.name()+lang);
    }
    
    /**
     * Gets the.
     *
     * @param product the product
     * @param lang the lang
     * @return the full batch type
     */
    public static FullBatchType get(String product, String lang) {
        return FullBatchType.valueOf(product+lang);
    }
    
    /**
     * Instantiates a new envelope type.
     *
     * @param product the product
     * @param lang the lang
     */
    private EnvelopeType(String product, String lang) {
        this.product = product;
        this.language = lang;
    }

    /**
     * Gets the batch type.
     *
     * @return the batch type
     */
    public String getBatchType() {
        return this.product;
    }
    
    /**
     * Gets the language.
     *
     * @return the language
     */
    public String getLanguage() {
        return this.language;
    }
}
