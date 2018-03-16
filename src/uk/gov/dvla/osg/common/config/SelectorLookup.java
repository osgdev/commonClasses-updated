package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class SelectorLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	
	private String ref, productionConfig, postageConfig, filePath, presentationConfig;
	
	private HashMap<String, SelectorLookup> lookup = new HashMap<String, SelectorLookup>();
	
	public SelectorLookup(String file){
		this.filePath=file;
		if(new File(file).exists()){
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				String line;
				
			    while ((line = br.readLine()) != null) {
			    	String[] array = line.split("\\|");
			    	if( !("SELECTOR".equals(array[0].trim())) ){
			    		SelectorLookup sel = new SelectorLookup(file, array[0].trim(),
			    				array[1].trim(),array[2].trim(),array[3].trim());
			    		
			    		lookup.put(array[0].trim(), sel );
			    	}
			    	
			    }
			    br.close();
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
	
	public SelectorLookup(String file, String ref, String productionConfig, String postageConfig, String presentationConfig){
		this.filePath=file;
		this.ref=ref;
		this.postageConfig=postageConfig;
		this.productionConfig = productionConfig;
		this.presentationConfig=presentationConfig;
		
	}
	
	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getFile() {
		return filePath;
	}

	public String getPresentationConfig() {
		return presentationConfig;
	}

	public void setPresentationConfig(String presentationConfig) {
		this.presentationConfig = presentationConfig;
	}
	
	public String getPostageConfig() {
		return postageConfig;
	}

	public void setPostageConfig(String postageConfig) {
		this.postageConfig = postageConfig;
	}

	public SelectorLookup get(String reference){
		return lookup.get(reference);
	}

	public String getProductionConfig() {
		return productionConfig;
	}

	public void setProductionConfig(String productionConfig) {
		this.productionConfig = productionConfig;
	}
}
