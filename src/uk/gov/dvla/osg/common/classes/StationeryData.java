package uk.gov.dvla.osg.common.classes;

/**
 * The Class Stationery.
 */
public class StationeryData {

    private final String type;
	private final double thickness;
	private final double weight;

    /**
     * Gets the single instance of Stationery.
     *
     * @param elements the elements
     * @return single instance of Stationery
     */
    public static StationeryData getInstance(String[] elements) {
        String type = elements[0].trim();
        Double thickness = Double.parseDouble(elements[1].trim());
        Double weight = Double.parseDouble(elements[2].trim());
        return new StationeryData(type, thickness, weight);
    }
    
	/**
	 * Instantiates a new stationery.
	 *
	 * @param type the type
	 * @param thickness the thickness
	 * @param weight the weight
	 */
	private StationeryData(String type, double thickness, double weight) {
	    this.type = type;
		this.thickness = thickness;
		this.weight = weight;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
	    return type;
	}
	
	/**
	 * Gets the thickness.
	 *
	 * @return the thickness
	 */
	public double getThickness() {
		return thickness;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

    @Override
    public String toString() {
        return String.format("Stationery [type=%s, thickness=%s, weight=%s]", type, thickness, weight);
    }

}
