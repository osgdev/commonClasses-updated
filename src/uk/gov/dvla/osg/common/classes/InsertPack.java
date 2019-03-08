package uk.gov.dvla.osg.common.classes;

/**
 * The Class InsertPack.
 */
public class InsertPack {

    private final String type;
	private final double thickness;
	private final double weight;
	private final int hopperCode;

	/**
	 * Gets the single instance of InsertPack.
	 *
	 * @param attributes the attributes
	 * @return single instance of InsertPack
	 */
	public static InsertPack getInstance(String[] attributes) {
	    String type = attributes[0].trim();
	    Double thickness = Double.parseDouble(attributes[1].trim());
	    Double weight = Double.parseDouble(attributes[2].trim());
	    int code = Integer.parseInt(attributes[3].trim());
	    
	    return new InsertPack(type, thickness, weight, code);
	}
	
	/**
	 * Instantiates a new insert pack.
	 *
	 * @param type the type
	 * @param thickness the thickness
	 * @param weight the weight
	 * @param hopperCode the hopper code
	 */
	public InsertPack(String type, double thickness, double weight, int hopperCode) {
		this.type = type;
	    this.thickness=thickness;
		this.weight=weight;
		this.hopperCode=hopperCode;
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

	/**
	 * Gets the hopper code.
	 *
	 * @return the hopper code
	 */
	public int getHopperCode() {
		return hopperCode;
	}

    @Override
    public String toString() {
        return String.format("InsertPack [type=%s, thickness=%s, weight=%s, hopperCode=%s]", type, thickness, weight, hopperCode);
    }

}
