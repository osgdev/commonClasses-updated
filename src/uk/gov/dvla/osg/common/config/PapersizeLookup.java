package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.PaperSize;

public class PapersizeLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	private HashMap<String, PaperSize> lookup = new HashMap<>();
	
    /******************************************************************************************
     *              SINGLETON PATTERN
     ******************************************************************************************/
    private static String filename;

    private static class SingletonHelper {
        private static final PapersizeLookup INSTANCE = new PapersizeLookup();
    }

    public static PapersizeLookup getInstance() {
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Papersize Lookup not initialised before use");
        }
        return SingletonHelper.INSTANCE;
    }

    public static void init(String file) throws RuntimeException {
        if (StringUtils.isBlank(filename)) {
            if (new File(file).isFile()) {
                filename = file;
            } else {
                throw new RuntimeException("Papersize Lookup File " + filename + " does not exist on filepath.");
            }
        } else {
            throw new RuntimeException("Papersize Lookup has already been initialised");
        }
    }
    /*****************************************************************************************/
    
	private PapersizeLookup() {
		LOGGER.info("Creating Papersize Lookup..");
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	if( !(line.startsWith("#")) ){
		    		String[] array = line.split(",",-1);
		    		lookup.put(array[0].trim(), new PaperSize(Double.parseDouble(array[1].trim())));
		    	}
		    }
		} catch (FileNotFoundException e) {
			LOGGER.fatal("PapersizeLookup lookup file error: '{}'", e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("PapersizeLookup lookup file error: '{}'", e.getMessage());
			System.exit(1);
		} catch (NullPointerException e){
			LOGGER.fatal("PapersizeLookup lookup file error: '{}'", e.getMessage());
			System.exit(1);
		}
	}
	
	public HashMap<String, PaperSize> getLookup() {
		return lookup;
	}

}
