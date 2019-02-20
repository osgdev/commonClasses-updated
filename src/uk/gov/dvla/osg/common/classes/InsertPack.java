package uk.gov.dvla.osg.common.classes;

public class InsertPack {

	private double thickness;
	private double weight;
	private int hopperCode;

	public static InsertPack getInstance(String[] attributes) {
	    Double thickness = Double.parseDouble(attributes[1].trim());
	    Double weight = Double.parseDouble(attributes[2].trim());
	    int code = Integer.parseInt(attributes[3].trim());
	    
	    return new InsertPack(thickness, weight, code);
	}
	
	public InsertPack(double thickness, double weight, int hopperCode) {
		this.thickness=thickness;
		this.weight=weight;
		this.hopperCode=hopperCode;
	}

	public double getThickness() {
		return thickness;
	}

	public double getWeight() {
		return weight;
	}

	public int getHopperCode() {
		return hopperCode;
	}

}
