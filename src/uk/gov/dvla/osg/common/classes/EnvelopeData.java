package uk.gov.dvla.osg.common.classes;

public class EnvelopeData {

	private double thickness;
	private int foldMultiplier;
	private double weight;

	public EnvelopeData(double thickness, int foldMultiplier, double weight) {
		this.thickness = thickness;
		this.foldMultiplier = foldMultiplier;
		this.weight = weight;
	}

	public double getThickness() {
		return thickness;
	}

	public int getFoldMultiplier() {
		return foldMultiplier;
	}

	public double getWeight() {
		return weight;
	}

}
