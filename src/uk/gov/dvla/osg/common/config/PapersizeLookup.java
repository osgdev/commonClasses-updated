package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.PaperSize;

public class PapersizeLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	private HashMap<String, PaperSize> lookup = new HashMap<>();
	
	public PapersizeLookup(String filePath){
		LOGGER.info("Creating Papersize Lookup..");
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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
