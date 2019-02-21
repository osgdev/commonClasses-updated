package uk.gov.dvla.osg.common.classes;

public class Stationery {

    private final String type;
	private final double thickness;
	private final double weight;

    public static Stationery getInstance(String[] elements) {
        String type = elements[0].trim();
        Double thickness = Double.parseDouble(elements[1].trim());
        Double weight = Double.parseDouble(elements[2].trim());
        return new Stationery(type, thickness, weight);
    }
    
	private Stationery(String type, double thickness, double weight) {
	    this.type = type;
		this.thickness = thickness;
		this.weight = weight;
	}

	public String getType() {
	    return type;
	}
	public double getThickness() {
		return thickness;
	}

	public double getWeight() {
		return weight;
	}

    @Override
    public String toString() {
        return String.format("Stationery [type=%s, thickness=%s, weight=%s]", type, thickness, weight);
    }

}
