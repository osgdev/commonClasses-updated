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

import uk.gov.dvla.osg.common.classes.Stationery;

public class StationeryLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	private HashMap<String, Stationery> lookup = new HashMap<String, Stationery>();
	
    /******************************************************************************************
     *              SINGLETON PATTERN
     ******************************************************************************************/
    private static String filename;

    private static class SingletonHelper {
        private static final StationeryLookup INSTANCE = new StationeryLookup();
    }

    public static StationeryLookup getInstance() {
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Stationery Lookup not initialised before use");
        }
        return SingletonHelper.INSTANCE;
    }

    public static void init(String file) throws RuntimeException {
        if (StringUtils.isBlank(filename)) {
            if (new File(file).isFile()) {
                filename = file;
            } else {
                throw new RuntimeException("Stationery Lookup file " + filename + " does not exist on filepath.");
            }
        } else {
            throw new RuntimeException("Production Configuration has already been initialised");
        }
    }

    /*****************************************************************************************/
    
	private StationeryLookup(){
		LOGGER.info("Creating Stationery Lookup..");
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	String[] array = line.split(",");
		    	if( !("REF".equals(array[0].trim())) ){
		    		lookup.put(array[0].trim(), new Stationery(Double.parseDouble(array[1].trim()),
		    								Double.parseDouble(array[2].trim())));
		    	}
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			LOGGER.fatal("Stationery lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("Stationery lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (NullPointerException e){
			LOGGER.fatal("Stationery lookup file error: '{}'",e.getMessage());
			System.exit(1);
		}
	}

	public HashMap<String, Stationery> getLookup() {
		return lookup;
	}
	
}
