package uk.gov.dvla.osg.common.enums;

public enum Product {
	MM ("MAILMARK"),
	OCR ("OCR"),
	UNSORTED ("UNSORTED"),
	UNCODED ("UNCODED");
	
	private String product;
	
	Product(String product) {
		this.product = product;
	}
	
	public String getValue() {
		return this.product;
	}
}
