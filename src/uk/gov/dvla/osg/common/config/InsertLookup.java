package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.Insert;

public class InsertLookup {

	private static final Logger LOGGER = LogManager.getLogger();
	private HashMap<String, Insert> lookup = new HashMap<>();

	/******************************************************************************************
	 *                              SINGLETON PATTERN
	 ******************************************************************************************/
	private static String filename;

	private static class SingletonHelper {
		private static final InsertLookup INSTANCE = new InsertLookup();
	}

	public static InsertLookup getInstance() {
		if (StringUtils.isBlank(filename)) {
			throw new RuntimeException("Insert Lookup not initialised before use");
		}
		return SingletonHelper.INSTANCE;
	}
	
	public static void init(String file) throws RuntimeException {
		if (StringUtils.isBlank(filename)) {
			if (new File(file).isFile()) {
				filename = file;
			} else {
				throw new RuntimeException("Insert Lookup File " + filename + " does not exist on filepath.");
			}
		} else {
			throw new RuntimeException("Insert Lookup has already been initialised");
		}
	}

	/*****************************************************************************************/

	private InsertLookup() {

	    LOGGER.trace("Loading Insert Lookup file '{}'", filename);
	    
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] array = line.split(",");
				if (!("INSERT REF".equals(array[0].trim()))) {
					lookup.put(array[0].trim(), new Insert(Double.parseDouble(array[1].trim()),
							Double.parseDouble(array[2].trim()), Integer.parseInt(array[3].trim())));
				}
			}
		} catch (IndexOutOfBoundsException | IOException | NullPointerException e) {
			LOGGER.fatal("Insert lookup file error: {}", e.getMessage());
			System.exit(1);
		}
	}

	public HashMap<String, Insert> getLookup() {
		return lookup;
	}

	public Insert get(String id) {
		return lookup.get(id);
	}
}
