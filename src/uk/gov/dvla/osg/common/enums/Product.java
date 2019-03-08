package uk.gov.dvla.osg.common.enums;

/**
 * The Enum Product.
 */
public enum Product {
    
	MM("MAILMARK"),
	OCR("OCR"),
	UNSORTED("UNSORTED"),
	UNCODED("UNCODED"),
	DEFAULT("DEFAULT");
	
	private String product;
	
	private Product(String product) {
		this.product = product;
	}
	
	public String getValue() {
		return this.product;
	}
}
