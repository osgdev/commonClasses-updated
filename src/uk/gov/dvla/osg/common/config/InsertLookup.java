package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.Insert;

public class InsertLookup {
	
	private static final Logger LOGGER = LogManager.getLogger();
	private HashMap<String, Insert> lookup = new HashMap<>();
	
	public InsertLookup(String filePath) {
		
		LOGGER.trace("Creating Insert Lookup..");
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
		    while ((line = br.readLine()) != null) {
		    	String[] array = line.split(",");
		    	if( !("INSERT REF".equals(array[0].trim())) ) {
		    		lookup.put(array[0].trim(), new Insert( 
		    				Double.parseDouble(array[1].trim()), 
		    				Double.parseDouble(array[2].trim()), 
		    				Integer.parseInt(array[3].trim())));
		    	}
		    }
		}catch (IndexOutOfBoundsException e){
			LOGGER.fatal("Insert lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (FileNotFoundException e) {
			LOGGER.fatal("Insert lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("Insert lookup file error: '{}'",e.getMessage());
			System.exit(1);
		} catch (NullPointerException e){
			LOGGER.fatal("Insert lookup file error: '{}'",e.getMessage());
			System.exit(1);
		}
	}

	public HashMap<String, Insert> getLookup() {
		return lookup;
	}
	
}
