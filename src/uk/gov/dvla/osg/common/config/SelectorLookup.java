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

import uk.gov.dvla.osg.common.classes.Selector;

public class SelectorLookup {
    
	static final Logger LOGGER = LogManager.getLogger();
	private final Map<String, Selector> lookup;

    public static SelectorLookup getInstance(String filename) {
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Selector Lookup file filename is blank");
        }
        
        if (!new File(filename).isFile()) {
            throw new RuntimeException(String.format("Selector Lookup file %s does not exist on filepath.", filename));
        }
        
        return new SelectorLookup(filename);
    }
    
	private SelectorLookup(String filename) {
	    
        Path pathToFile = Paths.get(filename);
        
        // create an instance of BufferedReader using try with resource to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // ignore the first line (header row)from the text file
            lookup = br.lines()
              .filter(line -> !line.startsWith("SELECTOR"))
              .map(line -> line.split("\\|"))
              .map(elements -> Selector.getInstance(elements))
              .collect(Collectors.toMap(Selector::getType, p -> p));
		} catch (IndexOutOfBoundsException | IOException | NullPointerException e) {
		    throw new RuntimeException(String.format("Lookup file error : %s", e.getMessage()));
		}
	}

	public boolean isPresent(String selector) {
	    return lookup.containsKey(selector);
	}	
	
	public Selector getSelector(String selector) {
		return lookup.get(selector);
	}
}
