package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.BatchType;

public class PresentationConfiguration {

	private static final Logger LOGGER = LogManager.getLogger();
	private static Map<String, Integer> presLookup = new HashMap<>();

    /******************************************************************************************
     *              SINGLETON PATTERN
     ******************************************************************************************/
    private static String filename;

    private static class SingletonHelper {
        private static final PresentationConfiguration INSTANCE = new PresentationConfiguration();
    }

    public static PresentationConfiguration getInstance() {
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Presentation Configuration not initialised before use");
        }
        return SingletonHelper.INSTANCE;
    }

    public static void init(String file) throws RuntimeException {
        if (StringUtils.isBlank(filename)) {
            if (new File(file).isFile()) {
                filename = file;
            } else {
                throw new RuntimeException("Presentation File " + filename + " does not exist on filepath.");
            }
        } else {
            throw new RuntimeException("Presentation Configuration has already been initialised");
        }
    }
    /*****************************************************************************************/

	public PresentationConfiguration() {
	    
	    LOGGER.trace("Loading Presentation Configuration file '{}'", filename);
		
	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line = null;
			Integer k = 0;
			while ((line = br.readLine()) != null) {
				presLookup.put(line.trim(), k++);
			}
		} catch (IOException ex) {
			LOGGER.fatal("Unable to read Presentation Configuration file {} {}", filename, ex.getMessage());
			System.exit(1);
		}
	}
	public int lookupRunOrder(String batchComparator) {
		return presLookup.containsKey(batchComparator) ? presLookup.get(batchComparator) : 999;
	}
	
	public int lookupRunOrder(BatchType batchComparator) {
		return presLookup.containsKey(batchComparator.name()) ? presLookup.get(batchComparator.name()) : 999;
	}
}
