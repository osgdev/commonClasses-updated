package uk.gov.dvla.osg.common.classes;

/**
 * The Class EnvelopeData.
 */
public class EnvelopeData {

    private final String type;
	private final double thickness;
	private final int foldMultiplier;
	private final double weight;

    /**
     * Gets the single instance of EnvelopeData.
     *
     * @param attributes the attributes
     * @return single instance of EnvelopeData
     */
    public static EnvelopeData getInstance(String[] attributes) {
        String type = attributes[0].trim();
        double thickness = Double.parseDouble(attributes[1].trim());
        int multiplier = Integer.parseInt(attributes[2].trim());
        double weight = Double.parseDouble(attributes[3].trim());
        
        return new EnvelopeData(type, thickness, weight, multiplier);
    }
    
	/**
	 * Instantiates a new envelope data.
	 *
	 * @param thickness the envelope thickness
	 * @param weight the envelope weight
	 * @param foldMultiplier the fold multiplier
	 */
	private EnvelopeData(String type, double thickness, double weight, int foldMultiplier) {
		this.type = type;
	    this.thickness = thickness;
		this.foldMultiplier = foldMultiplier == 0 ? 1 : foldMultiplier;
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
	 * Gets the envelope thickness.
	 *
	 * @return the thickness
	 */
	public double getThickness() {
		return thickness;
	}

	/**
	 * Gets the fold multiplier.
	 *
	 * @return the fold multiplier
	 */
	public int getFoldMultiplier() {
		return foldMultiplier;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

}
