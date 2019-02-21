package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.enums.BatchType;

public class PresentationConfiguration {

    static final Logger LOGGER = LogManager.getLogger();
    private final List<String> priorityList;

    public static PresentationConfiguration getInstance(String filename) throws RuntimeException {
        if (!StringUtils.isBlank(filename)) {
            throw new RuntimeException("Presentation Configuration has already been initialised");
        }
        if (!new File(filename).isFile()) {
            throw new RuntimeException("Presentation File " + filename + " does not exist on filepath.");
        }

        return new PresentationConfiguration(filename);
    }

    private PresentationConfiguration(String filename) {

        Path pathToFile = Paths.get(filename);
        
        // create an instance of BufferedReader using try with resource to close resources
        try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {
            priorityList = br.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Unable to read Presentation Configuration file %s : %s", filename, ex.getMessage()));
        }
    }

    public int lookupRunOrder(String batchComparator) {
        return priorityList.contains(batchComparator) ? priorityList.indexOf(batchComparator) : 999;
    }

    public int lookupRunOrder(BatchType batchType) {
        return priorityList.contains(batchType.name()) ? priorityList.indexOf(batchType.name()) : 999;
    }
}
