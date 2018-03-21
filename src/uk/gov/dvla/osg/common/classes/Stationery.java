package uk.gov.dvla.osg.common.classes;

public class Stationery {

	private double thickness;
	private double weight;

	public Stationery(double thickness, double weight) {
		this.thickness = thickness;
		this.weight = weight;
	}

	public double getThickness() {
		return thickness;
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return "Stationery [thickness=" + thickness + ", weight=" + weight + "]";
	}
}
