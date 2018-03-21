package uk.gov.dvla.osg.common.classes;

public class Selector {

	private String productionConfig;
	private String postageConfig;
	private String presentationConfig;
	private String documentationConfig;
	private String cardDocumentationConfig;

	public Selector(String productionConfig, String postageConfig, String presentationConfig) {
		this.postageConfig=postageConfig;
		this.productionConfig = productionConfig;
		this.presentationConfig=presentationConfig;
	}

	public String getProductionConfig() {
		return productionConfig;
	}

	public String getPostageConfig() {
		return postageConfig;
	}

	public String getPresentationConfig() {
		return presentationConfig;
	}

	public String getDocumentationConfig() {
		return documentationConfig;
	}
	
	public String getCardDocumentationConfig() {
		return cardDocumentationConfig;
	}
}
