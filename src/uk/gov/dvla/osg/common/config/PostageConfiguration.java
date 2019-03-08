package uk.gov.dvla.osg.common.config;

import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import uk.gov.dvla.osg.common.enums.BatchType;

public class PostageConfiguration {

	static final Logger LOGGER = LogManager.getLogger();

	private String ukmMAcc, ukmFAcc, ukmResourcePath, ukmItemIdLookupFile, ukmMTrayLookupFile, ukmFTrayLookupFile,
			ukmSoapDestination, ukmSoapArchive, ukmConsignorFileDestination, ukmConsignorFileArchive, ukmConsignorDestinationDepartment, 
			unsortedAccountNo, unsortedService, unsortedProduct, unsortedFormat,
			
			ocrProduct, ocrFormat, 
			
			mmScid, mmClass, mmXmlProduct, mmXmlFormat, mmProduct, mmFormat, mmUpuCountryId, mmInfoType, mmVersionId, mmMailType, mmReturnMailFlag, 
			mmReturnMailPc, mmReserved, mmMachineable, mmAppname;

	private int ukmMinimumTrayVolume, ukmMinimumCompliance, maxTrayWeight;

	private List<BatchType> ukmBatchTypes;

    /**
     * Gets the single instance of ProductionConfiguration.
     *
     * @param filename the filename
     * @return single instance of ProductionConfiguration
     * @throws RuntimeException the runtime exception
     */
    public static PostageConfiguration getInstance(String filename) throws RuntimeException {
        if (!StringUtils.isBlank(filename)) {
            throw new RuntimeException("Production Configuration has already been initialised");
        }
        
        if (!new File(filename).isFile()) {
            throw new RuntimeException(String.format("Production File %s does not exist on filepath.", filename));
        }
        
        return new PostageConfiguration(filename);

    }

	private PostageConfiguration(String filename) {
	    
        // Loads properties from file
        Properties props = new Properties();
        try (InputStream input = new FileInputStream(new File(filename))) {
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Unable to load Production File [%s] : ", filename, ex.getMessage()));
        }
        
        this.ukmMAcc = props.getProperty("ukm.m.accNo");
        this.ukmFAcc = props.getProperty("ukm.f.accNo");
        this.unsortedAccountNo = props.getProperty("unsort.accNo");
        this.unsortedService = props.getProperty("unsort.service");
        this.unsortedProduct = props.getProperty("unsort.product");
        this.unsortedFormat =props.getProperty("unsort.format");
        this.ocrProduct = props.getProperty("ocr.product");
        this.ocrFormat = props.getProperty("ocr.format");
        this.mmScid = props.getProperty("mm.scid");
        this.mmClass = props.getProperty("mm.class");
        this.mmXmlProduct = props.getProperty("mm.xmlProduct");
        this.mmXmlFormat = props.getProperty("mm.xmlFormat");
        this.mmProduct = props.getProperty("mm.product");
        this.mmFormat = props.getProperty("mm.format");
        this.mmUpuCountryId = props.getProperty("mm.upuCountryId");
        this.mmInfoType = props.getProperty("mm.infoType");
        this.mmVersionId = props.getProperty("mm.versionId");
        this.mmMailType = props.getProperty("mm.mailType");
        this.mmReturnMailFlag = props.getProperty("mm.returnMailFlag");
        this.mmReturnMailPc = props.getProperty("mm.returnMailPc");
        this.mmReserved = props.getProperty("mm.reserved");
        this.ukmResourcePath = props.getProperty("ukm.resourcePath");
        this.ukmItemIdLookupFile = props.getProperty("ukm.itemIDLookupFilename");
        this.ukmMTrayLookupFile = props.getProperty("ukm.m.trayLookupFilename");
        this.ukmFTrayLookupFile = props.getProperty("ukm.f.trayLookupFilename");
        this.ukmConsignorFileDestination = props.getProperty("ukm.consignorFileDestination");
        this.ukmConsignorFileArchive = props.getProperty("ukm.consignorFileArchivePath");
        this.ukmSoapDestination = props.getProperty("ukm.consignorFileArchivePath");
        this.ukmSoapArchive =props.getProperty("ukm.soapfile.ArchivePath");
        this.ukmConsignorDestinationDepartment = props.getProperty("ukm.consignorDestinationDepartment");
        this.ukmMinimumTrayVolume = Integer.parseInt(props.getProperty("ukm.minimumTrayVolume"));
        this.maxTrayWeight = Integer.parseInt(props.getProperty("ukm.maxTrayWeight"));
        this.ukmMinimumCompliance =Integer.parseInt(props.getProperty("ukm.minimumCompliance"));
        this.ukmBatchTypes = Stream.of(props.getProperty("ukm.batchTypes").split(",")).map(s -> BatchType.valueOf(s.trim())).collect(Collectors.toList());
        this.mmMachineable = props.getProperty("mm.machineable");
        this.mmAppname = props.getProperty("mm.appName");
	}

	/**
	 * Gets the ukm consignor file archive.
	 *
	 * @return the ukm consignor file archive
	 */
	public String getUkmConsignorFileArchive() {
		return ukmConsignorFileArchive;
	}
	
	/**
	 * Gets the ukm M acc.
	 *
	 * @return the ukm M acc
	 */
	public String getUkmMAcc() {
		return ukmMAcc;
	}

	/**
	 * Gets the ukm F acc.
	 *
	 * @return the ukm F acc
	 */
	public String getUkmFAcc() {
		return ukmFAcc;
	}

	/**
	 * Gets the unsorted account no.
	 *
	 * @return the unsorted account no
	 */
	public String getUnsortedAccountNo() {
		return unsortedAccountNo;
	}

	/**
	 * Gets the unsorted service.
	 *
	 * @return the unsorted service
	 */
	public String getUnsortedService() {
		return unsortedService;
	}

	/**
	 * Gets the unsorted product.
	 *
	 * @return the unsorted product
	 */
	public String getUnsortedProduct() {
		return unsortedProduct;
	}

	/**
	 * Gets the unsorted format.
	 *
	 * @return the unsorted format
	 */
	public String getUnsortedFormat() {
		return unsortedFormat;
	}

	/**
	 * Gets the ocr product.
	 *
	 * @return the ocr product
	 */
	public String getOcrProduct() {
		return ocrProduct;
	}

	/**
	 * Gets the ocr format.
	 *
	 * @return the ocr format
	 */
	public String getOcrFormat() {
		return ocrFormat;
	}

	/**
	 * Gets the mm scid.
	 *
	 * @return the mm scid
	 */
	public String getMmScid() {
		return mmScid;
	}

	/**
	 * Gets the mm class.
	 *
	 * @return the mm class
	 */
	public String getMmClass() {
		return mmClass;
	}

	/**
	 * Gets the mm xml product.
	 *
	 * @return the mm xml product
	 */
	public String getMmXmlProduct() {
		return mmXmlProduct;
	}

	/**
	 * Gets the mm xml format.
	 *
	 * @return the mm xml format
	 */
	public String getMmXmlFormat() {
		return mmXmlFormat;
	}

	/**
	 * Gets the mm product.
	 *
	 * @return the mm product
	 */
	public String getMmProduct() {
		return mmProduct;
	}

	/**
	 * Gets the mm format.
	 *
	 * @return the mm format
	 */
	public String getMmFormat() {
		return mmFormat;
	}

	/**
	 * Gets the mm upu country id.
	 *
	 * @return the mm upu country id
	 */
	public String getMmUpuCountryId() {
		return mmUpuCountryId;
	}

	/**
	 * Gets the mm info type.
	 *
	 * @return the mm info type
	 */
	public String getMmInfoType() {
		return mmInfoType;
	}

	/**
	 * Gets the mm version id.
	 *
	 * @return the mm version id
	 */
	public String getMmVersionId() {
		return mmVersionId;
	}

	/**
	 * Gets the mm mail type.
	 *
	 * @return the mm mail type
	 */
	public String getMmMailType() {
		return mmMailType;
	}

	/**
	 * Gets the mm return mail flag.
	 *
	 * @return the mm return mail flag
	 */
	public String getMmReturnMailFlag() {
		return mmReturnMailFlag;
	}

	/**
	 * Gets the mm return mail pc.
	 *
	 * @return the mm return mail pc
	 */
	public String getMmReturnMailPc() {
		return mmReturnMailPc;
	}

	/**
	 * Gets the mm reserved.
	 *
	 * @return the mm reserved
	 */
	public String getMmReserved() {
		return mmReserved;
	}

	/**
	 * Gets the ukm resource path.
	 *
	 * @return the ukm resource path
	 */
	public String getUkmResourcePath() {
		return ukmResourcePath;
	}

	/**
	 * Gets the ukm item id lookup file.
	 *
	 * @return the ukm item id lookup file
	 */
	public String getUkmItemIdLookupFile() {
		return ukmItemIdLookupFile;
	}

	/**
	 * Gets the ukm M tray lookup file.
	 *
	 * @return the ukm M tray lookup file
	 */
	public String getUkmMTrayLookupFile() {
		return ukmMTrayLookupFile;
	}

	/**
	 * Gets the ukm F tray lookup file.
	 *
	 * @return the ukm F tray lookup file
	 */
	public String getUkmFTrayLookupFile() {
		return ukmFTrayLookupFile;
	}

	/**
	 * Gets the ukm soap destination.
	 *
	 * @return the ukm soap destination
	 */
	public String getUkmSoapDestination() {
		return ukmSoapDestination;
	}

	/**
	 * Gets the ukm soap archive.
	 *
	 * @return the ukm soap archive
	 */
	public String getUkmSoapArchive() {
		return ukmSoapArchive;
	}

	/**
	 * Gets the ukm batch types.
	 *
	 * @return the ukm batch types
	 */
	public List<BatchType> getUkmBatchTypes() {
		return ukmBatchTypes;
	}

	/**
	 * Gets the ukm minimum tray volume.
	 *
	 * @return the ukm minimum tray volume
	 */
	public int getUkmMinimumTrayVolume() {
		return ukmMinimumTrayVolume;
	}

	/**
	 * Gets the ukm minimum compliance.
	 *
	 * @return the ukm minimum compliance
	 */
	public int getUkmMinimumCompliance() {
		return ukmMinimumCompliance;
	}

	/**
	 * Gets the ukm consignor file destination.
	 *
	 * @return the ukm consignor file destination
	 */
	public String getUkmConsignorFileDestination() {
		return ukmConsignorFileDestination;
	}

	/**
	 * Gets the ukm consignor destination department.
	 *
	 * @return the ukm consignor destination department
	 */
	public String getUkmConsignorDestinationDepartment() {
		return ukmConsignorDestinationDepartment;
	}

	/**
	 * Gets the mm machinable.
	 *
	 * @return the mm machinable
	 */
	public String getMmMachinable() {
		return mmMachineable;
	}

	/**
	 * Gets the mm appname.
	 *
	 * @return the mm appname
	 */
	public String getMmAppname() {
		return mmAppname;
	}

	/**
	 * Gets the max tray weight.
	 *
	 * @return the max tray weight
	 */
	public double getMaxTrayWeight() {
		return maxTrayWeight;
	}

}
