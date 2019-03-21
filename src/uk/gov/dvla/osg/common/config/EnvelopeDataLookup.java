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

import uk.gov.dvla.osg.common.classes.EnvelopeData;

/**
 * The Class EnvelopeLookup.
 */
public class EnvelopeDataLookup {

    static final Logger LOGGER = LogManager.getLogger();

    private Map<String, EnvelopeData> lookup;

    /**
     * Gets the single instance of EnvelopeLookup.
     *
     * @param filename the filename
     * @return single instance of EnvelopeLookup
     */
    public static EnvelopeDataLookup getInstance(String filename) {
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Envelope Data Lookup file filename is blank");
        }
        
        if (!new File(filename).isFile()) {
            throw new RuntimeException(String.format("Envelope Data Lookup file %s does not exist on filepath.", filename));
        }
        
        return new EnvelopeDataLookup(filename);
    }

    /**
     * Instantiates a new envelope lookup.
     *
     * @param filename the filename
     */
    private EnvelopeDataLookup(String filename) {

        Path pathToFile = Paths.get(filename);
        
        // create an instance of BufferedReader using try with resource to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // ignore any line starting with #
            this.lookup = br.lines()
                            .filter(line -> !line.startsWith("REF"))
                            .map(line -> EnvelopeData.getInstance(line.split(",")))
                            .collect(Collectors.toMap(EnvelopeData::getType, p -> p));
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Error loading envelope lookup file [%s] : %s", filename, ex.getMessage()));
        }
    }

    /**
     * Gets the.
     *
     * @param id the id
     * @return the envelope data
     */
    public EnvelopeData get(String id) {
        return lookup.get(id);
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
