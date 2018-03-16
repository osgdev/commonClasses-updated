package uk.gov.dvla.osg.common.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RpdFileHandler {

	private static final Logger LOGGER = LogManager.getLogger();
	private String inputFile = "";
	private HashMap<String, Integer> results;
	private List<String> headList;
	private PrintWriter pw;

	public RpdFileHandler(String inputFile, String outputFile) {
		
		LOGGER.info("Running RpdFileHandler");
		this.inputFile = inputFile;
		results = new HashMap<String, Integer>();
		headList = new ArrayList<String>();
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, false)));
		} catch (IOException e) {
			LOGGER.fatal("Error when creating output file '{}', error:{}", outputFile, e.getMessage());
			System.exit(1);
		}
		process();
	}

	private void process() {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String head = br.readLine();
			LOGGER.info("Read headers '{}'", head);
			String[] heads = head.split("\\t");
			LOGGER.info("Found {} records in header", heads.length);
			for (int i = 0; i < heads.length; i++) {
				results.put(heads[i], i);
				headList.add(heads[i]);
			}
		} catch (FileNotFoundException e) {
			LOGGER.fatal("File '{}' not found.", inputFile);
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("IO Exception when processing file '{}', error: '{}'", inputFile, e.getMessage());
			System.exit(1);
		}
	}

	public HashMap<String, Integer> getMapping() {
		return results;
	}

	public List<String> getHeaders() {
		return headList;
	}

	public void write(String str) {
		pw.write(str);
	}

	public void write(List<String> arr) {
		String res = "";
		String delim = "" + (char) Integer.parseInt("09", 16);
		int i = 1;
		for (String str : arr) {
			if (arr.size() == i) {
				res = res + str;
			} else {
				res = res + str + delim;
			}
			i++;
		}
		pw.println(res);
	}

	public void closeFile() {
		pw.close();
	}

}
