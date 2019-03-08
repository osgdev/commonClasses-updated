package uk.gov.dvla.osg.common.classes;

/**
 * The Class PaperSize.
 */
public class PaperSizeDivisor {

    private final String type;
	private final Double divisor;

    /**
     * Gets the single instance of PaperSize.
     *
     * @param elements the elements
     * @return single instance of PaperSize
     */
    public static PaperSizeDivisor getInstance(String[] elements) {
        String type = elements[0];
        double multiplier = Double.parseDouble(elements[1]);
        return new PaperSizeDivisor(type, multiplier);
    }
    
    public static PaperSizeDivisor getInstance(String type, double multiplier) {
        return new PaperSizeDivisor(type, multiplier);
    }
    
	/**
	 * Instantiates a new paper size.
	 *
	 * @param type the type
	 * @param multiplier the multiplier
	 */
	private PaperSizeDivisor(String type, double multiplier) {
	    this.type = type;
		this.divisor = multiplier;
	}

	/**
	 * Gets the multiplier.
	 *
	 * @return the multiplier
	 */
	public int getDivisor() {
		return divisor.intValue();
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
        return String.format("PaperSize [type=%s, multiplier=%s]", type, divisor);
    }
	


}
