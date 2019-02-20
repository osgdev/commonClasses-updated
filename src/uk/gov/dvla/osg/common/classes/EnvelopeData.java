package uk.gov.dvla.osg.common.classes;

public class EnvelopeData {

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
        Double thickness = Double.parseDouble(attributes[1].trim());
        Double weight = Double.parseDouble(attributes[3].trim());
        int multiplier = Integer.parseInt(attributes[2].trim());
        
        return new EnvelopeData(thickness, weight, multiplier);
    }
    
	/**
	 * Instantiates a new envelope data.
	 *
	 * @param thickness the envelope thickness
	 * @param weight the envelope weight
	 * @param foldMultiplier the fold multiplier
	 */
	private EnvelopeData(double thickness, double weight, int foldMultiplier) {
		this.thickness = thickness;
		this.foldMultiplier = foldMultiplier;
		this.weight = weight;
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
