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

import uk.gov.dvla.osg.common.classes.PaperSize;

/**
 * The Class PapersizeLookup.
 */
public class PapersizeLookup {
	
    static final Logger LOGGER = LogManager.getLogger();
	
	private Map<String, PaperSize> lookup;
	
    
    /**
     * Gets the single instance of PapersizeLookup.
     *
     * @param filename the filename
     * @return single instance of PapersizeLookup
     */
    public static PapersizeLookup getInstance(String filename) {
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Papersize Lookup file filename is blank");
        }
        
        if (!new File(filename).isFile()) {
            throw new RuntimeException(String.format("Papersize Lookup file %s does not exist on filepath.", filename));
        }
        
        return new PapersizeLookup(filename);
    }
    
	/**
	 * Instantiates a new papersize lookup.
	 *
	 * @param filename the filename
	 */
	private PapersizeLookup(String filename) {
        Path pathToFile = Paths.get(filename);
        
        // create an instance of BufferedReader using try with resource to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // ignore any line starting with REF
            lookup = br.lines()
                    .filter(line -> !line.startsWith("#"))
                    .map(line -> PaperSize.getInstance(line.split(",")))
                    .collect(Collectors.toMap(PaperSize::getType, p -> p));
        } catch (IOException ex) {
            throw new RuntimeException(String.format("PaperSize lookup file error : %s", ex.getMessage()));
        }
	}
	
	/**
	 * Gets the lookup.
	 *
	 * @return the lookup
	 */
	public Map<String, PaperSize> getLookup() {
		return lookup;
	}
	
	public PaperSize getPapersize(String id) {
        return lookup.containsKey(id) ? lookup.get(id) : PaperSize.getInstance("null", 1);
	}
	
}
