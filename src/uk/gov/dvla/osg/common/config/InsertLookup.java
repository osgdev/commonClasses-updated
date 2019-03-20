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

import uk.gov.dvla.osg.common.classes.InsertPack;

/**
 * The Class InsertLookup.
 */
public class InsertLookup {

    static final Logger LOGGER = LogManager.getLogger();

    private static final String[] NULL_INSERT = {"NULL","0","0","0"};
    
    private Map<String, InsertPack> lookup;

    /**
     * Gets the single instance of InsertLookup.
     *
     * @param filename the filename
     * @return single instance of InsertLookup
     */
    public static InsertLookup getInstance(String filename) {
        
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Insert Lookup file filename is blank");
        }
        
        if (!new File(filename).isFile()) {
            throw new RuntimeException(String.format("Insert Lookup File '%s'does not exist on filepath", filename));
        }
        
        return new InsertLookup(filename);
    }

    /**
     * Instantiates a new insert lookup.
     *
     * @param filename the filename
     */
    private InsertLookup(String filename) {

        Path pathToFile = Paths.get(filename);

        // create an instance of BufferedReader using try with resource to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // ignore any line starting with INSERT REF
            lookup = br.lines()
                    .filter(line -> !line.startsWith("INSERT REF"))
                    .map(line -> InsertPack.getInstance(line.split(",")))
                    .collect(Collectors.toMap(InsertPack::getType, p -> p));
            
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Insert lookup file error [%s]: %s", filename, ex.getMessage()));
        }
    }

    /**
     * Gets the.
     *
     * @param id the id
     * @return the insert pack
     */
    public InsertPack get(String id) {
        return lookup.containsKey(id) ? lookup.get(id) : InsertPack.getInstance(NULL_INSERT);
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
