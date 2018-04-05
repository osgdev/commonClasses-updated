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

import uk.gov.dvla.osg.common.classes.Envelope;

public class EnvelopeLookup {

	private static final Logger LOGGER = LogManager.getLogger();
	private HashMap<String, Envelope> lookup = new HashMap<>();

  /******************************************************************************************
  *              SINGLETON PATTERN
  ******************************************************************************************/
 private static String filename;

 private static class SingletonHelper {
     private static final EnvelopeLookup INSTANCE = new EnvelopeLookup();
 }

 public static EnvelopeLookup getInstance() {
     if (StringUtils.isBlank(filename)) {
         throw new RuntimeException("Envelope Lookup not initialised before use");
     }
     return SingletonHelper.INSTANCE;
 }

 public static void init(String file) throws RuntimeException {
     if (StringUtils.isBlank(filename)) {
         if (new File(file).isFile()) {
             filename = file;
         } else {
             throw new RuntimeException("Envelope Lookup File " + filename + " does not exist on filepath.");
         }
     } else {
         throw new RuntimeException("Envelope Lookup has already been initialised");
     }
 }
 /*****************************************************************************************/
 
	private EnvelopeLookup() {
		LOGGER.info("Creating Envelope Lookup..");
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] array = line.split(",");
				if (!("REF".equals(array[0].trim()))) {
					lookup.put(array[0].trim().toUpperCase(), new Envelope(Double.parseDouble(array[1].trim()),
							Integer.parseInt(array[2].trim()), Double.parseDouble(array[3].trim())));
				}
			}
		} catch (FileNotFoundException e) {
			LOGGER.fatal("Envelope lookup file error: '{}'", e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("Envelope lookup file error: '{}'", e.getMessage());
			System.exit(1);
		} catch (NullPointerException e) {
			LOGGER.fatal("Envelope lookup file error: '{}'", e.getMessage());
			System.exit(1);
		}
	}

	public HashMap<String, Envelope> getLookup() {
		return lookup;
	}

}
