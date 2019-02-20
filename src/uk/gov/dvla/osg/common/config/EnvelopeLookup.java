package uk.gov.dvla.osg.common.config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.EnvelopeData;

public class EnvelopeLookup {

    static final Logger LOGGER = LogManager.getLogger();

    private final Map<String, EnvelopeData> lookup = new HashMap<>();

    public static EnvelopeLookup getInstance(String filename) {
        return new EnvelopeLookup(filename);
    }

    private EnvelopeLookup(String filename) {

        if (!new File(filename).isFile()) {
            throw new RuntimeException("Envelope Lookup File " + filename + " does not exist on filepath.");
        }

        LOGGER.info("Loading Envelope Lookup file '{}'", filename);
        Path pathToFile = Paths.get(filename);
        
        // create an instance of BufferedReader using try with resource to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            // read the first line from the text file
            String line = br.readLine();

            // loop until all lines are read
            while (line != null) {
                String[] attributes = line.split(",");
                String envName = attributes[0].trim();

                if (!("REF".equals(envName))) {
                    EnvelopeData envData = EnvelopeData.getInstance(attributes);
                    lookup.put(envName.toUpperCase(), envData);
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

    public EnvelopeData get(String id) {
        return lookup.get(id);
    }
}
