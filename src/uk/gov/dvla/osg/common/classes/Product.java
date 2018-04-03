package uk.gov.dvla.osg.common.classes;

public enum Product {
	MM ("MAILMARK"),
	OCR ("OCR"),
	UNSORTED ("UNSORTED"),
	RM ("RM");
	
	private String product;
	
	Product(String product) {
		this.product = product;
	}
	
	public String getValue() {
		return this.product;
	}
}
