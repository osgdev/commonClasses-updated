package uk.gov.dvla.osg.common.classes;

/**
 * The Class PaperSize.
 */
public class PaperSize {

    private final String type;
	private final Double multiplier;

    /**
     * Gets the single instance of PaperSize.
     *
     * @param elements the elements
     * @return single instance of PaperSize
     */
    public static PaperSize getInstance(String[] elements) {
        String type = elements[0];
        double multiplier = Double.parseDouble(elements[1]);
        return new PaperSize(type, multiplier);
    }
    
    public static PaperSize getInstance(String type, double multiplier) {
        return new PaperSize(type, multiplier);
    }
    
	/**
	 * Instantiates a new paper size.
	 *
	 * @param type the type
	 * @param multiplier the multiplier
	 */
	private PaperSize(String type, double multiplier) {
	    this.type = type;
		this.multiplier = multiplier;
	}

	/**
	 * Gets the multiplier.
	 *
	 * @return the multiplier
	 */
	public int getMultiplier() {
		return multiplier.intValue();
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
	    return this.type;
	}

    @Override
    public String toString() {
        return String.format("PaperSize [type=%s, multiplier=%s]", type, multiplier);
    }
	


}
