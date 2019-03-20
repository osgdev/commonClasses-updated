package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.StationeryData;

/**
 * Used to lookup Stationery data from the Stationery Lookup file by the type of stationery.
 * Stationery types are preset for the item in the input DPF.
 */
public class StationeryDataLookup {
	
    static final Logger LOGGER = LogManager.getLogger();
	
	private Map<String, StationeryData> lookup;
	
    
    /**
     * Gets the single instance of StationeryLookup.
     *
     * @param filename the name and path of the stationery lookup file
     * @return single instance of StationeryLookup
     */
    public static StationeryDataLookup getInstance(String filename) {
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Stationery Lookup file filename is blank");
        }
        
        if (!new File(filename).isFile()) {
            throw new RuntimeException(String.format("Stationery Lookup file %s does not exist on filepath.", filename));
        }
        
        return new StationeryDataLookup(filename);
    }
    
	/**
	 * Instantiates a new stationery lookup.
	 *
	 * @param filename the filename
	 */
	private StationeryDataLookup(String filename) {
	    
        Path pathToFile = Paths.get(filename);
        
        // create an instance of BufferedReader using try with resource to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // ignore any line starting with REF
            lookup = br.lines()
                    .filter(line -> !line.startsWith("REF"))
                    .map(line -> line.split(","))
                    .map(elements -> StationeryData.getInstance(elements))
                    .collect(Collectors.toMap(StationeryData::getType, p -> p));
            
		} catch (IndexOutOfBoundsException | IOException | NullPointerException ex) {
		    throw new RuntimeException(String.format("Stationery lookup file error : %s", ex.getMessage()));
		}
	}

    /**
     * Gets the stationery data for a type of stationery.
     *
     * @param stationeryType the stationery type
     * @return the stationery data
     */
    public StationeryData getStationery(String stationeryType) {
        return lookup.get(stationeryType);
    }
    
    /**
     * Contains key.
     *
     * @param key the key
     * @return true, if successful
     */
    public boolean containsKey(String key) {
        return lookup.containsKey(key);
    }
}
