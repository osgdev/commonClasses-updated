package uk.gov.dvla.osg.common.utils;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import uk.gov.dvla.osg.common.classes.Item;

public class Utils {
    
    static final Logger LOGGER = LogManager.getLogger();
    
    /**
     * Prints a summary of the number of items for each batch type by item and by group
     * @param docProps
     */
    public static String summaryPrint(List<Item> items) {
        String customerCount = items.stream()
                                        .collect(Collectors.groupingBy(Item::getFullBatchName, Collectors.counting()))
                                        .toString();

        String groupCount = items.stream()
                                     .filter(item -> item.isEog())
                                     .collect(Collectors.groupingBy(Item::getFullBatchName, Collectors.counting()))
                                     .toString();
        
        Long totalGroups = items.stream()
                                    .filter(item -> item.getGroupId() != null)
                                    .map(item -> item.getGroupId().toString())
                                    .distinct()
                                    .collect(Collectors.counting());
        
        String s = totalGroups == null ? "0" : totalGroups.toString();
        
        
        return String.format("Customer Summary %s, Group Summary %s, Total Groups = %s", customerCount, groupCount, s);
    }
    
    /**
     * Make a copy of a file, with the supplied suffix, only when the logger level is set to TRACE or ALL
     *
     * @param filename the file to copy
     * @param suffix the suffix to append to the filename
     * @param logger the logger
     */
    public static void archiveDpf(String filename, String suffix, Logger logger) {
        String archiveFile = suffix.startsWith(".") ? filename + suffix : filename + "." + suffix;
        Level oldLevel = ((LoggerContext) LogManager.getContext(false)).getConfiguration().getLoggerConfig(logger.getName()).getLevel();
        if (oldLevel.equals(Level.TRACE) || oldLevel.equals(Level.ALL)) {
            try {
                FileUtils.copyFile(new File(filename), new File(archiveFile));
                logger.trace("Copy of file saved as {}", archiveFile);
            } catch (IOException ex) {
                logger.error("Unable to copy file [{}] : {}", filename, ex.getMessage());
            }
        }
    }
}