package uk.gov.dvla.osg.common.config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.enums.*;

public class ProductionConfiguration {

    static final Logger LOGGER = LogManager.getLogger();

    private final String mailingSite;
    private final Product defaultProduct;

    private final int traySize;
    private final int maxMulti;
    private final int minimumMailsort;
    
    private boolean groupUnderCompliance; // False if MULTIs become singles when changed to SORTED/UNSORTED

    private Map<FullBatchType, Integer> batchMaxMap = new HashMap<>();
    private Map<FullBatchType, Integer> groupMaxMap = new HashMap<>();
    private Map<FullBatchType, String> siteMap = new HashMap<>();
    private Map<EnvelopeType, String> envelopeMap = new HashMap<>();
    

    /**
     * Gets the single instance of ProductionConfiguration.
     *
     * @param filename the filename
     * @return single instance of ProductionConfiguration
     * @throws RuntimeException the runtime exception
     */
    public static ProductionConfiguration getInstance(String filename) throws RuntimeException {
        if (!StringUtils.isBlank(filename)) {
            throw new RuntimeException("Production Configuration has already been initialised");
        }
        
        if (!new File(filename).isFile()) {
            throw new RuntimeException(String.format("Production File %s does not exist on filepath.", filename));
        }
        
        return new ProductionConfiguration(filename);

    }
    
    /**
     * Instantiates a new production configuration.
     *
     * @param filename the filename
     */
    private ProductionConfiguration(String filename) {

        // Loads properties from file
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(new File(filename))) {
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Unable to load Production File [%s] : ", filename, ex.getMessage()));
        }

        this.mailingSite = props.getProperty("site.mailing").toUpperCase();
        this.defaultProduct = Product.valueOf(props.getProperty("mailsort.preference.product").toUpperCase());
        
        this.minimumMailsort = Integer.parseInt(props.getProperty("minimum.mailsort"));
        this.maxMulti = Integer.parseInt(props.getProperty("maxMulti"));
        this.traySize = Integer.parseInt(props.getProperty("traySize"));
        
        // Group Unsorted, default is off
        if (props.containsKey("group.underCompliance")) {
            this.groupUnderCompliance = props.getProperty("group.underCompliance").toUpperCase().equals("Y");
        } else {
            this.groupUnderCompliance = false;
        }
        
        siteMap.put(FullBatchType.FLEETE, props.getProperty("site.english.fleet").toUpperCase());
        siteMap.put(FullBatchType.FLEETW, props.getProperty("site.welsh.fleet").toUpperCase());
        siteMap.put(FullBatchType.MULTIE, props.getProperty("site.english.multi").toUpperCase());
        siteMap.put(FullBatchType.MULTIW, props.getProperty("site.welsh.multi").toUpperCase());
        siteMap.put(FullBatchType.UNSORTEDE, props.getProperty("site.english.unsorted").toUpperCase());
        siteMap.put(FullBatchType.UNSORTEDW, props.getProperty("site.welsh.unsorted").toUpperCase());
        siteMap.put(FullBatchType.SORTEDE, props.getProperty("site.english.sorted").toUpperCase());
        siteMap.put(FullBatchType.SORTEDW, props.getProperty("site.welsh.sorted").toUpperCase());
        siteMap.put(FullBatchType.CLERICALE, props.getProperty("site.english.clerical").toUpperCase());
        siteMap.put(FullBatchType.CLERICALW, props.getProperty("site.welsh.clerical").toUpperCase());
        siteMap.put(FullBatchType.SORTINGE, props.getProperty("site.english.sorting").toUpperCase());
        siteMap.put(FullBatchType.SORTINGW, props.getProperty("site.welsh.sorting").toUpperCase());
        siteMap.put(FullBatchType.REJECTE, props.getProperty("site.english.reject").toUpperCase());
        siteMap.put(FullBatchType.REJECTW, props.getProperty("site.welsh.reject").toUpperCase());
        siteMap.put(FullBatchType.REPRINTE, props.getProperty("site.english.reprint").toUpperCase());
        siteMap.put(FullBatchType.REPRINTW, props.getProperty("site.welsh.reprint").toUpperCase());
        siteMap.put(FullBatchType.UNCODEDE, props.getProperty("site.english.uncoded").toUpperCase());
        siteMap.put(FullBatchType.UNCODEDW, props.getProperty("site.welsh.uncoded").toUpperCase());

        // Envelope names per product type
        envelopeMap.put(EnvelopeType.DEFAULTE, props.getProperty("envelope.english.default").toUpperCase());
        envelopeMap.put(EnvelopeType.DEFAULTW, props.getProperty("envelope.welsh.default").toUpperCase());
        envelopeMap.put(EnvelopeType.UNSORTEDE, props.getProperty("envelope.english.unsorted").toUpperCase());
        envelopeMap.put(EnvelopeType.UNSORTEDW, props.getProperty("envelope.welsh.unsorted").toUpperCase());
        envelopeMap.put(EnvelopeType.OCRE, props.getProperty("envelope.english.ocr").toUpperCase());
        envelopeMap.put(EnvelopeType.OCRW, props.getProperty("envelope.welsh.ocr").toUpperCase());
        envelopeMap.put(EnvelopeType.MME, props.getProperty("envelope.welsh.mm").toUpperCase());
        envelopeMap.put(EnvelopeType.MMW, props.getProperty("envelope.welsh.mm").toUpperCase());
        envelopeMap.put(EnvelopeType.UNCODEDE, props.getProperty("envelope.welsh.uncoded").toUpperCase());
        envelopeMap.put(EnvelopeType.UNCODEDW, props.getProperty("envelope.welsh.uncoded").toUpperCase());

        // Batch Max
        batchMaxMap.put(FullBatchType.FLEETE, Integer.parseInt(props.getProperty("batchMax.english.fleet")));
        batchMaxMap.put(FullBatchType.FLEETW, Integer.parseInt(props.getProperty("batchMax.welsh.fleet")));
        batchMaxMap.put(FullBatchType.MULTIE, Integer.parseInt(props.getProperty("batchMax.english.multi")));
        batchMaxMap.put(FullBatchType.MULTIW, Integer.parseInt(props.getProperty("batchMax.welsh.multi")));
        batchMaxMap.put(FullBatchType.UNSORTEDE, Integer.parseInt(props.getProperty("batchMax.english.unsorted")));
        batchMaxMap.put(FullBatchType.UNSORTEDW, Integer.parseInt(props.getProperty("batchMax.welsh.unsorted")));
        batchMaxMap.put(FullBatchType.SORTEDE, Integer.parseInt(props.getProperty("batchMax.english.sorted")));
        batchMaxMap.put(FullBatchType.SORTEDW, Integer.parseInt(props.getProperty("batchMax.welsh.sorted")));
        batchMaxMap.put(FullBatchType.CLERICALE, Integer.parseInt(props.getProperty("batchMax.english.clerical")));
        batchMaxMap.put(FullBatchType.CLERICALW, Integer.parseInt(props.getProperty("batchMax.welsh.clerical")));
        batchMaxMap.put(FullBatchType.SORTINGE, Integer.parseInt(props.getProperty("batchMax.english.sorting")));
        batchMaxMap.put(FullBatchType.SORTINGW, Integer.parseInt(props.getProperty("batchMax.welsh.sorting")));
        batchMaxMap.put(FullBatchType.REJECTE, Integer.parseInt(props.getProperty("batchMax.english.reject")));
        batchMaxMap.put(FullBatchType.REJECTW, Integer.parseInt(props.getProperty("batchMax.welsh.reject")));
        batchMaxMap.put(FullBatchType.REPRINTE, Integer.parseInt(props.getProperty("batchMax.english.reprint")));
        batchMaxMap.put(FullBatchType.REPRINTW, Integer.parseInt(props.getProperty("batchMax.welsh.reprint")));
        batchMaxMap.put(FullBatchType.UNCODEDE, Integer.parseInt(props.getProperty("batchMax.english.uncoded")));
        batchMaxMap.put(FullBatchType.UNCODEDW, Integer.parseInt(props.getProperty("batchMax.welsh.uncoded")));

        // Group Max
        groupMaxMap.put(FullBatchType.FLEETE, Integer.parseInt(props.getProperty("groupMax.english.fleet")));
        groupMaxMap.put(FullBatchType.FLEETW, Integer.parseInt(props.getProperty("groupMax.welsh.fleet")));
        groupMaxMap.put(FullBatchType.CLERICALE, Integer.parseInt(props.getProperty("groupMax.english.clerical")));
        groupMaxMap.put(FullBatchType.CLERICALW, Integer.parseInt(props.getProperty("groupMax.welsh.clerical")));
        groupMaxMap.put(FullBatchType.MULTIE, Integer.parseInt(props.getProperty("groupMax.english.multi")));
        groupMaxMap.put(FullBatchType.MULTIW, Integer.parseInt(props.getProperty("groupMax.welsh.multi")));

    }

    /**
     * Gets the site the work will be mailed from
     *
     * @return the mailing site
     */
    public String getMailingSite() {
        return mailingSite;
    }

    /**
     * Gets the preferred mailsort product.
     *
     * @return the mailsort product
     */
    public Product getMailsortProduct() {
        return defaultProduct;
    }

    /**
     * Gets the envelope name, which can then be used to lookup envelope data.
     *
     * @param envelope the envelope type for the product
     * @return the name of the envelope
     */
    public String getEnvelopeName(EnvelopeType envelope) {
        return envelopeMap.get(envelope);
    }

    /**
     * Gets the minimum volume for mailsortable items.
     *
     * @return the minimum mailsort volume
     */
    public Integer getMinimumMailsort() {
        return minimumMailsort;
    }

    /**
     * Gets the size of a standard mailing tray.
     *
     * @return the tray size
     */
    public Integer getTraySize() {
        return traySize;
    }

    /**
     * Gets the maximum volume for MULTI items, after which they become CLERICAL
     *
     * @return the max multi
     */
    public Integer getMaxMulti() {
        return maxMulti;
    }

    /**
     * Gets the full batch max for lookups.
     *
     * @param fbt the fbt
     * @return the batch max
     */
    public int getBatchMax(FullBatchType fbt) {
        return batchMaxMap.get(fbt);
    }

    /**
     * Gets the group max value for a full BatchType.
     *
     * @param fbt the Full BatchType to lookup
     * @return the group max if set for the full BatchType, else 1
     */
    public int getGroupMax(FullBatchType fbt) {

        if (groupMaxMap.containsKey(fbt)) {
            return groupMaxMap.get(fbt);
        }
        return 1;
    }

    /**
     * Gets the group max map for lookups.
     *
     * @return the group max map
     */
    public Map<FullBatchType, Integer> getGroupMaxMap() {
        return this.groupMaxMap;
    }

    /**
     * Gets the site string, which can be a percentage, "X", or "XX"
     *
     * @param fbt the full BatchType
     * @return the site string
     */
    public String getSite(FullBatchType fbt) {
        return siteMap.get(fbt);
    }

    /**
     * Determines if under-compliance items are to be grouped.
     *
     * @return true, if under-compliance items are to be grouped
     */
    public boolean groupUnderCompliance() {
        return groupUnderCompliance;
    }

    /**
     * Checks if the provided batch type is to be used.
     *
     * @param fbt the fbt
     * @return true, if batch type is on
     */
    public boolean isBatchTypeOn(FullBatchType fbt) {
        return !siteMap.get(fbt).contains("X");
    }

    /**
     * Checks if the provided batch type is to be used.
     *
     * @param batchtype the batchtype
     * @param language the language
     * @return true, if batch type is on
     */
    public boolean isBatchTypeOn(BatchType batchtype, String language) {
        return isBatchTypeOn(FullBatchType.get(batchtype, language));
    }

    /**
     * Checks if the provided batch type is to be used.
     *
     * @param batchtype the batchtype
     * @param language the language
     * @return true, if batch type is on
     */
    public boolean isBatchTypeOn(String batchtype, String language) {
        return isBatchTypeOn(FullBatchType.get(batchtype, language));
    }
}
