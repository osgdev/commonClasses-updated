package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.InsertPack;

public class InsertLookup {

    static final Logger LOGGER = LogManager.getLogger();
    
    private HashMap<String, InsertPack> lookup = new HashMap<>();

    public static InsertLookup getInstance(String filename) {
        
        if (StringUtils.isBlank(filename)) {
            throw new RuntimeException("Insert Lookup file filename is blank");
        }
        
        if (!new File(filename).isFile()) {
            throw new RuntimeException(String.format("Insert Lookup File '%s'does not exist on filepath", filename));
        }
        
        return new InsertLookup(filename);
    }

    private InsertLookup(String filename) {

        Path pathToFile = Paths.get(filename);

        // create an instance of BufferedReader using try with resource to close
        // resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {
                String[] attributes = line.split(",");
                if (!("INSERT REF".equals(attributes[0].trim()))) {
                    InsertPack pack = InsertPack.getInstance(attributes);
                    lookup.put(attributes[0].trim(), pack);
                }
            }
        } catch (IndexOutOfBoundsException | IOException | NullPointerException e) {
            LOGGER.fatal("Insert lookup file error: {}", e.getMessage());
            System.exit(1);
        }
    }

    public InsertPack get(String id) {
        return lookup.get(id);
    }
}
