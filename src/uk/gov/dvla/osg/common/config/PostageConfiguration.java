package uk.gov.dvla.osg.common.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.classes.BatchType;

public class PostageConfiguration {

	private static final Logger LOGGER = LogManager.getLogger();

	private String ukmMAcc, ukmFAcc, ukmResourcePath, ukmItemIdLookupFile, ukmMTrayLookupFile, ukmFTrayLookupFile,
			ukmSoapDestination, ukmSoapArchive, ukmConsignorFileDestination,
			ukmConsignorFileArchive, ukmConsignorDestinationDepartment;
	private int ukmMinimumTrayVolume, ukmMinimumCompliance, maxTrayWeight;

	private String unsortedAccountNo, unsortedService, unsortedProduct, unsortedFormat;
	private String ocrProduct, ocrFormat;
	private String mmScid, mmClass, mmXmlProduct, mmXmlFormat, mmProduct, mmFormat, mmUpuCountryId, mmInfoType,
			mmVersionId, mmMailType, mmReturnMailFlag, mmReturnMailPc, mmReserved, mmMachineable, mmAppname;

	private List<BatchType> ukmBatchTypes;
	private HashSet<String> requiredFields = new HashSet<String>();

	/******************************************************************************************
	 * SINGLETON PATTERN
	 ******************************************************************************************/
	private static String filename;

	private static class SingletonHelper {
		private static final PostageConfiguration INSTANCE = new PostageConfiguration();
	}

	public static PostageConfiguration getInstance() {
		if (StringUtils.isBlank(filename)) {
			throw new RuntimeException("Postage Configuration not initialised before use");
		}
		return SingletonHelper.INSTANCE;
	}

	public static void init(String file) throws RuntimeException {
		if (StringUtils.isBlank(filename)) {
			if (new File(file).isFile()) {
				filename = file;
			} else {
				throw new RuntimeException("Postage File " + filename + " does not exist on filepath.");
			}
		} else {
			throw new RuntimeException("Postage Configuration has already been initialised");
		}
	}

	/*****************************************************************************************/

	private PostageConfiguration() {

		loadRequiredFields();

		parseConfig(filename);

		if (requiredFields.size() != 0) {
			String missingFields = "";
			for (String str : requiredFields) {
				missingFields = missingFields + str + ",";
			}

			LOGGER.fatal("Missing values from '{}' are '{}'", filename, missingFields);
			System.exit(1);
		}
	}

	private void loadRequiredFields() {
		requiredFields.add("ukm.m.accNo");
		requiredFields.add("ukm.f.accNo");
		requiredFields.add("unsort.accNo");
		requiredFields.add("unsort.service");
		requiredFields.add("unsort.product");
		requiredFields.add("unsort.format");
		requiredFields.add("ocr.product");
		requiredFields.add("ocr.format");
		requiredFields.add("mm.scid");
		requiredFields.add("mm.class");
		requiredFields.add("mm.xmlProduct");
		requiredFields.add("mm.xmlFormat");
		requiredFields.add("mm.product");
		requiredFields.add("mm.format");
		requiredFields.add("mm.upuCountryId");
		requiredFields.add("mm.infoType");
		requiredFields.add("mm.versionId");
		requiredFields.add("mm.mailType");
		requiredFields.add("mm.returnMailFlag");
		requiredFields.add("mm.returnMailPc");
		requiredFields.add("mm.reserved");
		requiredFields.add("ukm.resourcePath");
		requiredFields.add("ukm.itemIDLookupFilename");
		requiredFields.add("ukm.m.trayLookupFilename");
		requiredFields.add("ukm.f.trayLookupFilename");
		requiredFields.add("ukm.consignorFileDestination");
		requiredFields.add("ukm.consignorFileArchivePath");
		requiredFields.add("ukm.soapfile.DestinationPath");
		requiredFields.add("ukm.soapfile.ArchivePath");
		requiredFields.add("ukm.minimumTrayVolume");
		requiredFields.add("ukm.maxTrayWeight");
		requiredFields.add("ukm.minimumCompliance");
		requiredFields.add("ukm.batchTypes");
		requiredFields.add("ukm.consignorDestinationDepartment");
		requiredFields.add("mm.machineable");
		requiredFields.add("mm.appName");
	}

	private void parseConfig(String configFileName) {

		try (BufferedReader br = new BufferedReader(new FileReader(configFileName))) {
			String line;
			while (((line = br.readLine()) != null)) {
				if (!(line.startsWith("#"))) {
					String[] split = line.split("=");
					String attribute = split[0];
					String value = split[1];
					if ("ukm.m.accNo".equalsIgnoreCase(attribute)) {
						this.ukmMAcc = value;
						requiredFields.remove("ukm.m.accNo");
					} else if ("ukm.f.accNo".equalsIgnoreCase(attribute)) {
						this.ukmFAcc = value;
						requiredFields.remove("ukm.f.accNo");
					} else if ("unsort.accNo".equalsIgnoreCase(attribute)) {
						this.unsortedAccountNo = value;
						requiredFields.remove("unsort.accNo");
					} else if ("unsort.service".equalsIgnoreCase(attribute)) {
						this.unsortedService = value;
						requiredFields.remove("unsort.service");
					} else if ("unsort.product".equalsIgnoreCase(attribute)) {
						this.unsortedProduct = value;
						requiredFields.remove("unsort.product");
					} else if ("unsort.format".equalsIgnoreCase(attribute)) {
						this.unsortedFormat = value;
						requiredFields.remove("unsort.format");
					} else if ("ocr.product".equalsIgnoreCase(attribute)) {
						this.ocrProduct = value;
						requiredFields.remove("ocr.product");
					} else if ("ocr.format".equalsIgnoreCase(attribute)) {
						this.ocrFormat = value;
						requiredFields.remove("ocr.format");
					} else if ("mm.scid".equalsIgnoreCase(attribute)) {
						this.mmScid = value;
						requiredFields.remove("mm.scid");
					} else if ("mm.class".equalsIgnoreCase(attribute)) {
						this.mmClass = value;
						requiredFields.remove("mm.class");
					} else if ("mm.xmlProduct".equalsIgnoreCase(attribute)) {
						this.mmXmlProduct = value;
						requiredFields.remove("mm.xmlProduct");
					} else if ("mm.xmlFormat".equalsIgnoreCase(attribute)) {
						this.mmXmlFormat = value;
						requiredFields.remove("mm.xmlFormat");
					} else if ("mm.product".equalsIgnoreCase(attribute)) {
						this.mmProduct = value;
						requiredFields.remove("mm.product");
					} else if ("mm.format".equalsIgnoreCase(attribute)) {
						this.mmFormat = value;
						requiredFields.remove("mm.format");
					} else if ("mm.upuCountryId".equalsIgnoreCase(attribute)) {
						this.mmUpuCountryId = value;
						requiredFields.remove("mm.upuCountryId");
					} else if ("mm.infoType".equalsIgnoreCase(attribute)) {
						this.mmInfoType = value;
						requiredFields.remove("mm.infoType");
					} else if ("mm.versionId".equalsIgnoreCase(attribute)) {
						this.mmVersionId = value;
						requiredFields.remove("mm.versionId");
					} else if ("mm.mailType".equalsIgnoreCase(attribute)) {
						this.mmMailType = value;
						requiredFields.remove("mm.mailType");
					} else if ("mm.returnMailFlag".equalsIgnoreCase(attribute)) {
						this.mmReturnMailFlag = value;
						requiredFields.remove("mm.returnMailFlag");
					} else if ("mm.returnMailPc".equalsIgnoreCase(attribute)) {
						this.mmReturnMailPc = value;
						requiredFields.remove("mm.returnMailPc");
					} else if ("mm.reserved".equalsIgnoreCase(attribute)) {
						this.mmReserved = value;
						requiredFields.remove("mm.reserved");
					} else if ("ukm.resourcePath".equalsIgnoreCase(attribute)) {
						this.ukmResourcePath = value;
						requiredFields.remove("ukm.resourcePath");
					} else if ("ukm.itemIDLookupFilename".equalsIgnoreCase(attribute)) {
						this.ukmItemIdLookupFile = value;
						requiredFields.remove("ukm.itemIDLookupFilename");
					} else if ("ukm.m.trayLookupFilename".equalsIgnoreCase(attribute)) {
						this.ukmMTrayLookupFile = value;
						requiredFields.remove("ukm.m.trayLookupFilename");
					} else if ("ukm.f.trayLookupFilename".equalsIgnoreCase(attribute)) {
						this.ukmFTrayLookupFile = value;
						requiredFields.remove("ukm.f.trayLookupFilename");
					} else if ("ukm.consignorFileDestination".equalsIgnoreCase(attribute)) {
						this.ukmConsignorFileDestination = value;
						requiredFields.remove("ukm.consignorFileDestination");
					} else if ("ukm.consignorFileArchivePath".equalsIgnoreCase(attribute)) {
						this.ukmConsignorFileArchive = value;
						requiredFields.remove("ukm.consignorFileArchivePath");
					} else if ("ukm.soapfile.DestinationPath".equalsIgnoreCase(attribute)) {
						this.ukmSoapDestination = value;
						requiredFields.remove("ukm.soapfile.DestinationPath");
					} else if ("ukm.soapfile.ArchivePath".equalsIgnoreCase(attribute)) {
						this.ukmSoapArchive = value;
						requiredFields.remove("ukm.soapfile.ArchivePath");
					} else if ("ukm.minimumTrayVolume".equalsIgnoreCase(attribute)) {
						this.ukmMinimumTrayVolume = Integer.valueOf(value);
						requiredFields.remove("ukm.minimumTrayVolume");
					} else if ("ukm.maxTrayWeight".equalsIgnoreCase(attribute)) {
						this.maxTrayWeight = Integer.valueOf(value);
						requiredFields.remove("ukm.maxTrayWeight");
					} else if ("ukm.minimumCompliance".equalsIgnoreCase(attribute)) {
						this.ukmMinimumCompliance = Integer.valueOf(value);
						requiredFields.remove("ukm.minimumCompliance");
					} else if ("ukm.batchTypes".equalsIgnoreCase(attribute)) {
						ukmBatchTypes = Arrays.asList(value.split(","))
						                      .stream()
							                  .map(s -> BatchType.valueOf(s))
						                      .collect(Collectors.toList());
						requiredFields.remove("ukm.batchTypes");
					} else if ("ukm.consignorDestinationDepartment".equalsIgnoreCase(attribute)) {
						ukmConsignorDestinationDepartment = value;
						requiredFields.remove("ukm.consignorDestinationDepartment");
					} else if ("mm.machineable".equalsIgnoreCase(attribute)) {
						mmMachineable = value;
						requiredFields.remove("mm.machineable");
					} else if ("mm.appName".equalsIgnoreCase(attribute)) {
						mmAppname = value;
						requiredFields.remove("mm.appName");
					}
				}
			}
		} catch (FileNotFoundException e) {
			LOGGER.fatal("Lookup file error: '{}'", e.getMessage());
			System.exit(1);
		} catch (IOException e) {
			LOGGER.fatal("Lookup file error: '{}'", e.getMessage());
			System.exit(1);
		}
	}

	public String getUkmConsignorFileArchive() {
		return ukmConsignorFileArchive;
	}
	
	public String getUkmMAcc() {
		return ukmMAcc;
	}

	public String getUkmFAcc() {
		return ukmFAcc;
	}

	public String getFilename() {
		return filename;
	}

	public String getUnsortedAccountNo() {
		return unsortedAccountNo;
	}

	public String getUnsortedService() {
		return unsortedService;
	}

	public String getUnsortedProduct() {
		return unsortedProduct;
	}

	public String getUnsortedFormat() {
		return unsortedFormat;
	}

	public String getOcrProduct() {
		return ocrProduct;
	}

	public String getOcrFormat() {
		return ocrFormat;
	}

	public String getMmScid() {
		return mmScid;
	}

	public String getMmClass() {
		return mmClass;
	}

	public String getMmXmlProduct() {
		return mmXmlProduct;
	}

	public String getMmXmlFormat() {
		return mmXmlFormat;
	}

	public String getMmProduct() {
		return mmProduct;
	}

	public String getMmFormat() {
		return mmFormat;
	}

	public String getMmUpuCountryId() {
		return mmUpuCountryId;
	}

	public String getMmInfoType() {
		return mmInfoType;
	}

	public String getMmVersionId() {
		return mmVersionId;
	}

	public String getMmMailType() {
		return mmMailType;
	}

	public String getMmReturnMailFlag() {
		return mmReturnMailFlag;
	}

	public String getMmReturnMailPc() {
		return mmReturnMailPc;
	}

	public String getMmReserved() {
		return mmReserved;
	}

	public String getUkmResourcePath() {
		return ukmResourcePath;
	}

	public String getUkmItemIdLookupFile() {
		return ukmItemIdLookupFile;
	}

	public String getUkmMTrayLookupFile() {
		return ukmMTrayLookupFile;
	}

	public String getUkmFTrayLookupFile() {
		return ukmFTrayLookupFile;
	}

	public String getUkmSoapDestination() {
		return ukmSoapDestination;
	}

	public String getUkmSoapArchive() {
		return ukmSoapArchive;
	}

	public List<BatchType> getUkmBatchTypes() {
		return ukmBatchTypes;
	}

	public int getUkmMinimumTrayVolume() {
		return ukmMinimumTrayVolume;
	}

	public int getUkmMinimumCompliance() {
		return ukmMinimumCompliance;
	}

	public String getUkmConsignorFileDestination() {
		return ukmConsignorFileDestination;
	}

	public String getUkmConsignorDestinationDepartment() {
		return ukmConsignorDestinationDepartment;
	}

	public String getMmMachineable() {
		return mmMachineable;
	}

	public String getMmAppname() {
		return mmAppname;
	}

	public double getMaxTrayWeight() {
		return maxTrayWeight;
	}

}
