package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.Envelope;

public class EnvelopeLookup {

	private static final Logger LOGGER = LogManager.getLogger();
	private HashMap<String, Envelope> lookup = new HashMap<>();

	public EnvelopeLookup(String filePath) {
		LOGGER.info("Creating Envelope Lookup..");
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] array = line.split(",");
				if (!("REF".equals(array[0].trim()))) {
					lookup.put(array[0].trim(), new Envelope(Double.parseDouble(array[1].trim()),
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
