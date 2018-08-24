package uk.gov.dvla.osg.common.config;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.enums.FullBatchType;
import uk.gov.dvla.osg.common.enums.Product;

public class ProductionConfiguration {

    // Mailing
    private String mailingSite;
    private Product mailsortProduct;
    // Envelopes
    private String envelopeEnglishDefault, envelopeWelshDefault, envelopeEnglishUnsorted, envelopeWelshUnsorted, envelopeEnglishOcr,
            envelopeWelshOcr, envelopeEnglishMm, envelopeWelshMm, envelopeEnglishUncoded, envelopeWelshUncoded;
    
    private Integer traySize, maxMulti, minimumMailsort;
    // Map batch values to enum
    private Map<FullBatchType, Integer> batchMaxMap = new HashMap<>();
    private Map<FullBatchType, Integer> groupMaxMap = new HashMap<>();
    private Map<FullBatchType, String> siteMap = new HashMap<>();

    private static final Logger LOGGER = LogManager.getLogger();

    /******************************************************************************************
     *              SINGLETON PATTERN
     ******************************************************************************************/
    private static String filename;

    private static class SingletonHelper {
        private static final ProductionConfiguration INSTANCE = new ProductionConfiguration();
    }

    public static ProductionConfiguration getInstance() {
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("ProductionConfiguration not initialised before use");
        }
        return SingletonHelper.INSTANCE;
    }
    
    public static void init(String file) throws RuntimeException {
        if (StringUtils.isBlank(filename)) {
            if (new File(file).isFile()) {
                filename = file;
            } else {
                throw new RuntimeException("Production File " + filename + " does not exist on filepath.");
            }
        } else {
            throw new RuntimeException("Production Configuration has already been initialised");
        }
    }

    /*****************************************************************************************/

    private ProductionConfiguration() {

        LOGGER.trace("Loading Production file '{}'", filename);
        // Loads key/value pairs from file
        Properties props = new Properties();
		try (InputStream input = new FileInputStream(new File(filename))) {
			props.load(input);
		} catch (IOException ex) {
			LOGGER.fatal(ExceptionUtils.getStackTrace(ex));
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
        
        this.mailingSite = props.getProperty("site.mailing").toUpperCase();
        this.mailsortProduct = Product.valueOf(props.getProperty("mailsort.preference.product").toUpperCase());
 
        // English And Welsh Default Envelopes, MP - 04/04
        this.envelopeEnglishDefault = props.getProperty("envelope.english.default").toUpperCase();
        this.envelopeWelshDefault = props.getProperty("envelope.welsh.default").toUpperCase();
        
        this.envelopeEnglishUnsorted = props.getProperty("envelope.english.unsorted").toUpperCase();
        this.envelopeWelshUnsorted = props.getProperty("envelope.welsh.unsorted").toUpperCase();
        
        this.envelopeEnglishOcr = props.getProperty("envelope.english.ocr").toUpperCase();
        this.envelopeWelshOcr = props.getProperty("envelope.welsh.ocr").toUpperCase();
        
        this.envelopeEnglishMm = props.getProperty("envelope.english.mm").toUpperCase();
        this.envelopeWelshMm = props.getProperty("envelope.welsh.mm").toUpperCase();
        
        this.envelopeEnglishUncoded = props.getProperty("envelope.english.uncoded").toUpperCase();
        this.envelopeWelshUncoded = props.getProperty("envelope.welsh.uncoded").toUpperCase();
        
        this.minimumMailsort = Integer.parseInt(props.getProperty("minimum.mailsort"));
        this.maxMulti = Integer.parseInt(props.getProperty("maxMulti"));
        this.traySize = Integer.parseInt(props.getProperty("traySize"));
        
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

    public String getMailingSite() {
        return mailingSite;
    }

    public Product getMailsortProduct() {
        return mailsortProduct;
    }

    public String getEnvelopeEnglishUnsorted() {
        return envelopeEnglishUnsorted;
    }

    public String getEnvelopeWelshUnsorted() {
        return envelopeWelshUnsorted;
    }

    public String getEnvelopeEnglishOcr() {
        return envelopeEnglishOcr;
    }

    public String getEnvelopeWelshOcr() {
        return envelopeWelshOcr;
    }

    public String getEnvelopeEnglishMm() {
        return envelopeEnglishMm;
    }

    public String getEnvelopeWelshMm() {
        return envelopeWelshMm;
    }

    public Integer getMinimumMailsort() {
        return minimumMailsort;
    }

    public Integer getTraySize() {
        return traySize;
    }

    public Integer getMaxMulti() {
        return maxMulti;
    }

    public int getBatchMax(FullBatchType fbt) {
        
			return batchMaxMap.get(fbt);
    }

    public int getGroupMax(FullBatchType fbt) {
    	
        if (groupMaxMap.containsKey(fbt)) {
			return groupMaxMap.get(fbt);
		}
        return 0;
    }
    
    public String getSite(FullBatchType fbt) {
        return siteMap.get(fbt);
    }

	public String getEnvelopeEnglishUncoded() {
		return envelopeEnglishUncoded;
	}

	public String getEnvelopeWelshUncoded() {
		return envelopeWelshUncoded;
	}

	public String getEnvelopeWelshDefault() {
		return envelopeWelshDefault;
	}

	public String getEnvelopeEnglishDefault() {
		return envelopeEnglishDefault;
	}
}
