package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.Selector;

public class SelectorLookup {
	private static final Logger LOGGER = LogManager.getLogger();
	private HashMap<String, Selector> lookup = new HashMap<>();

	/******************************************************************************************
	 * SINGLETON PATTERN
	 ******************************************************************************************/
	private static String filename;

	private static class SingletonHelper {
		private static final SelectorLookup INSTANCE = new SelectorLookup();
	}

	public static SelectorLookup getInstance() {
		if (StringUtils.isBlank(filename)) {
			throw new RuntimeException("Selector Lookup not initialised before use");
		}
		return SingletonHelper.INSTANCE;
	}
	
	public static void init(String file) throws RuntimeException {
		if (StringUtils.isBlank(filename)) {
			if (new File(file).isFile()) {
				filename = file;
			} else {
				throw new RuntimeException("Selector Lookup file " + filename + " does not exist on filepath.");
			}
		} else {
			throw new RuntimeException("Selector has already been initialised");
		}
	}

	/*****************************************************************************************/

	public SelectorLookup() {

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] array = line.split("\\|");
				if (!("SELECTOR".equals(array[0].trim()))) {
					lookup.put(array[0].trim(), new Selector(array[1].trim(), array[2].trim(), array[3].trim()));
				}
			}
		} catch (IndexOutOfBoundsException | IOException | NullPointerException e) {
			LOGGER.fatal("Lookup file error: {}", e.getMessage());
			System.exit(1);
		}
	}

	public HashMap<String, Selector> getLookup() {
		return this.lookup;
	}

	public Selector get(String selector) {
		return lookup.get(selector);
	}
}
