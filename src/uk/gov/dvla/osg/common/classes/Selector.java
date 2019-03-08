package uk.gov.dvla.osg.common.classes;

/**
 * The Class Selector.
 */
public class Selector {

	private String productionConfig = "";
	private String postageConfig = "";
	private String presentationConfig = "";
    private String type;
    private String documentationConfig;
    private String cardDocumentationConfig;

    /**
     * Gets the single instance of Selector.
     *
     * @param elements the elements
     * @return single instance of Selector
     */
    public static Selector getInstance(String[] elements) {
        String type = elements[0].trim();
        String productionConfig = elements[1].trim();
        String postageConfig = elements[2].trim();
        String presentationConfig = elements[3].trim();
        String documentationConfig = elements[4].trim();
        String cardDocumentationConfig = elements[5].trim();
        return new Selector(type, productionConfig, postageConfig, presentationConfig, documentationConfig, cardDocumentationConfig);
    }
    
	/**
	 * Instantiates a new selector.
	 *
	 * @param type the type
	 * @param productionConfig the production config
	 * @param postageConfig the postage config
	 * @param presentationConfig the presentation config
	 * @param documentationConfig the documentation config
	 * @param cardDocumentationConfig the card documentation config
	 */
	private Selector(String type, String productionConfig, String postageConfig, String presentationConfig, String documentationConfig, String cardDocumentationConfig) {
		this.type = type;
        this.postageConfig=postageConfig;
		this.productionConfig = productionConfig;
		this.presentationConfig=presentationConfig;
        this.documentationConfig = documentationConfig;
        this.cardDocumentationConfig = cardDocumentationConfig;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
	    return this.type;
	}
	
	/**
	 * Gets the production config.
	 *
	 * @return the production config
	 */
	public String getProductionConfig() {
		return productionConfig;
	}

	/**
	 * Gets the postage config.
	 *
	 * @return the postage config
	 */
	public String getPostageConfig() {
		return postageConfig;
	}

	/**
	 * Gets the presentation config.
	 *
	 * @return the presentation config
	 */
	public String getPresentationConfig() {
		return presentationConfig;
	}

	/**
	 * Gets the documentation config.
	 *
	 * @return the documentation config
	 */
	public String getDocumentationConfig() {
		return documentationConfig;
	}
	
	/**
	 * Gets the card documentation config.
	 *
	 * @return the card documentation config
	 */
	public String getCardDocumentationConfig() {
		return cardDocumentationConfig;
	}

    @Override
    public String toString() {
        return String.format("Selector [productionConfig=%s, postageConfig=%s, presentationConfig=%s, type=%s, documentationConfig=%s, cardDocumentationConfig=%s]", productionConfig, postageConfig, presentationConfig, type, documentationConfig, cardDocumentationConfig);
    }


}
