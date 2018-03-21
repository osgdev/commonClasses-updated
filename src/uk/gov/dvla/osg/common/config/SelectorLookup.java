package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.Selector;


public class SelectorLookup {
	private static final Logger LOGGER = LogManager.getLogger();	
	private HashMap<String, Selector> lookup = new HashMap<>();
	
	
	public SelectorLookup(String file){
		if(new File(file).exists()){
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				
			    while ((line = br.readLine()) != null) {
			    	String[] array = line.split("\\|");
			    	if( !("SELECTOR".equals(array[0].trim())) ){
			    		lookup.put(array[0].trim(), new Selector(array[1].trim(),
			                    							     array[2].trim(),
			                    							     array[3].trim()) );
			    	}
			    }
			} catch (FileNotFoundException e) {
				LOGGER.fatal("Lookup file error: '{}'",e.getMessage());
				System.exit(1);
			} catch (IOException e) {
				LOGGER.fatal("Lookup file error: '{}'",e.getMessage());
				System.exit(1);
			} catch (NullPointerException e){
				LOGGER.fatal("Lookup file error: '{}'",e.getMessage());
				System.exit(1);
			}
		}else{
			LOGGER.fatal("Lookup file: '{}' doesn't exist",file);
			System.exit(1);
		}
	}

	public HashMap<String, Selector> getLookup() {
		return this.lookup;
	}
}
