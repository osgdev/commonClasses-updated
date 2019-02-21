package uk.gov.dvla.osg.common.classes;

import static uk.gov.dvla.osg.common.enums.BatchType.*;

import org.apache.commons.lang3.StringUtils;

import uk.gov.dvla.osg.common.enums.*;

public class Customer {

	private static final String ENDMARKER = "X";

	private String docRef, selectorRef, stationery, subBatch, fleetNo, paperSize, msc, sortField, eog, dps, name1,
			name2, add1, add2, add3, add4, add5, postcode, insertRef, envelope, mmBarcodeContent, sot, appName,
			mmCustomerContent, runNo, runDate, mailingId, sob = "";

	private Integer sequenceInChild, batchSequence, noOfPages, totalPagesInGroup, tenDigitJid, eightDigitJid;

	Double weight, size;

	private Integer presentationPriority, originalIndex, groupId;

	private BatchType batchType;
	private Language lang;
	private Product product;
	private Site site;
	private int transactionID;

	public void setMailingId(String id) {
		this.mailingId = id;
	}
	
	public String getMailingId() {
		return this.mailingId;
	}
	
	public void setTransactionID(int id) {
		this.transactionID = id;
	}

	public int getTransactionID() {
		return this.transactionID;
	}

	public Customer(Integer originalIdx) {
		this.originalIndex = originalIdx;
	}

	public void setSob() {
		this.sob = ENDMARKER;
	}

	public String getSob() {
		return sob;
	}

	public boolean isSob() {
		return StringUtils.isNotBlank(sob);
	}

	public void clearSob() {
		this.sob = "";
	}

	public Integer getTenDigitJid() {
		return tenDigitJid;
	}

	public void setTenDigitJid(int tenDigitJid) {
		this.tenDigitJid = tenDigitJid;
	}

	public String getCustomerContent() {
		return mmCustomerContent;
	}

	public void setCustomerContent(String mmCustomerContent) {
		this.mmCustomerContent = mmCustomerContent;
	}

	public Integer getTotalPagesInGroup() {
		return totalPagesInGroup;
	}

	public void setTotalPagesInGroup(int totalPagesInGroup) {
		this.totalPagesInGroup = totalPagesInGroup;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(String product) {
		if (StringUtils.isNotBlank(product)) {
			this.product = Product.valueOf(product);
		}
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getOriginalIdx() {
		return originalIndex;
	}

	public String getEnvelope() {
		return envelope;
	}

	public void setEnvelope(String envelope) {
		this.envelope = envelope;
	}

	public String getInsertRef() {
		return insertRef;
	}

	public void setInsertRef(String insertRef) {
		this.insertRef = insertRef;
	}

	public Integer getPresentationPriority() {
		return presentationPriority;
	}

	public void setPresentationPriority(Integer presentationPriority) {
		this.presentationPriority = presentationPriority;
	}

	public String getMsc() {
		return msc;
	}

	public void setMsc(String msc) {
		this.msc = msc;
	}

	public int getBatchSequence() {
		return batchSequence;
	}

	public void setBatchSequence(Integer batchSequence) {
		this.batchSequence = batchSequence;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getFleetNo() {
		return fleetNo;
	}

	public void setFleetNo(String ref) {
		this.fleetNo = ref;
	}

	public String getSelectorRef() {
		return selectorRef;
	}

	public void setSelectorRef(String ref) {
		this.selectorRef = ref;
	}

	public String getDocRef() {
		return docRef;
	}

	public void setDocRef(String ref) {
		this.docRef = ref;
	}

	public Language getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = Language.valueOf(lang);
	}

	public void setLang(Language lang) {
		this.lang = lang;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = Site.valueOf(site.toUpperCase());
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getStationery() {
		return stationery;
	}

	public void setStationery(String stationery) {
		this.stationery = stationery;
	}

	public String getBatchName() {
		return this.batchType.name();
	}

	public BatchType getBatchType() {
		return batchType;
	}

	public void setBatchType(String batchType) {
		if (StringUtils.isNotBlank(batchType)) {
			this.batchType = BatchType.valueOf(batchType);
		}
	}

	public void setBatchType(BatchType batchType) {
		this.batchType = batchType;
	}

	public boolean isBatchType(BatchType bt) {
		return this.batchType.equals(bt);
	}

	public String getSubBatch() {
		return subBatch;
	}

	public void setSubBatch(String subBatch) {
		this.subBatch = subBatch;
	}

	public Integer getEightDigitJid() {
		return eightDigitJid;
	}

	public void setEightDigitJid(String jid) {
		this.eightDigitJid = Integer.parseInt(jid);
	}

	public void setEightDigitJid(int jid) {
		this.eightDigitJid = jid;
	}

	public Integer getSequenceInChild() {
		return sequenceInChild;
	}

	public void setSequenceInChild(int sequence) {
		this.sequenceInChild = sequence;
	}

	public String getPaperSize() {
		return paperSize;
	}

	public void setPaperSize(String paperSize) {
		this.paperSize = paperSize;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getEog() {
		return eog;
	}

	public void setEog() {
		this.eog = ENDMARKER;
	}

	public void setEog(String eog) {
		this.eog = eog;
	}

	public void clearEog() {
		this.eog = "";
	}

	public boolean isEog() {
		return StringUtils.isNotBlank(eog);
	}

	public int getNoOfPages() {
		return noOfPages;
	}

	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getAdd1() {
		return add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getAdd3() {
		return add3;
	}

	public void setAdd3(String add3) {
		this.add3 = add3;
	}

	public String getAdd4() {
		return add4;
	}

	public void setAdd4(String add4) {
		this.add4 = add4;
	}

	public String getAdd5() {
		return add5;
	}

	public void setAdd5(String add5) {
		this.add5 = add5;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getDps() {
		return dps;
	}

	public void setDps(String dps) {
		this.dps = dps;
	}

	public String[] print() {
		String[] str = { this.docRef, this.site.name(), this.eightDigitJid.toString() };
		return str;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public String getMmBarcodeContent() {
		return mmBarcodeContent;
	}

	public void setMmBarcodeContent(String mmBarcodeContent) {
		this.mmBarcodeContent = mmBarcodeContent;
	}

	public String getSot() {
		return sot == null ? "" : sot;
	}

	public void setSot(String sot) {
		this.sot = sot.toUpperCase();
	}

	public void setSot() {
		this.sot = ENDMARKER;
	}

	public void clearSot() {
		this.sot = "";
	}

	public boolean isSot() {
		return StringUtils.isNotBlank(sot);
	}

	public void updateBatchType(BatchType newBatchType, Integer priority) {
		this.batchType = newBatchType;
		this.presentationPriority = priority;
		//this.presentationPriority = priority;
		if (this.batchType.equals(UNSORTED)) {
			this.msc = "";
		}
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((batchType == null) ? 0 : batchType.hashCode()); //
		result = prime * result + ((lang == null) ? 0 : lang.hashCode()); //
		result = prime * result + ((paperSize == null) ? 0 : paperSize.hashCode());
		result = prime * result + ((site == null) ? 0 : site.hashCode()); //
		result = prime * result + ((stationery == null) ? 0 : stationery.hashCode()); //
		result = prime * result + ((subBatch == null) ? 0 : subBatch.hashCode()); //
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Customer other = (Customer) obj;
		if (batchType == null) {
			if (other.batchType != null) return false;
		} else if (!batchType.equals(other.batchType)) return false;
		if (lang == null) {
			if (other.lang != null) return false;
		} else if (!lang.equals(other.lang)) return false;
		if (paperSize == null) {
			if (other.paperSize != null) return false;
		} else if (!paperSize.equals(other.paperSize)) return false;
		if (site == null) {
			if (other.site != null) return false;
		} else if (!site.equals(other.site)) return false;
		if (stationery == null) {
			if (other.stationery != null) return false;
		} else if (!stationery.equals(other.stationery)) return false;
		if (subBatch == null) {
			if (other.subBatch != null) return false;
		} else if (!subBatch.equals(other.subBatch)) return false;
		return true;
	}

	public FullBatchType getFullBatchType() {
		return FullBatchType.valueOf(batchType.name() + lang.name());
	}

   public String getFullBatchName() {
        return batchType.name() + lang.name();
    }
	
   public String getRunNo() {
		return runNo;
	}

	public void setRunNo(String runNo) {
		this.runNo = runNo;
	}

	public String getRpdJid() {
		return eightDigitJid + "." + batchSequence;
	}

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        //return new ToStringBuilder(this).append("docRef", docRef).toString();
        return "Customer [docRef=" + docRef + ", "
                + "selectorRef=" + selectorRef 
                + ", batchType=" + batchType 
                + ", subBatch=" + subBatch 
                + ", lang=" + lang 
                + ", msc=" + msc
                + ", fleetNo=" + fleetNo 
                + ", groupId=" + groupId 
                + ", sob=" + sob 
                + ", sot=" + sot 
                + ", eog=" + eog 
                + ", sequenceInChild=" + sequenceInChild
                + ", batchSequence=" + batchSequence 
                + ", tenDigitJid=" + tenDigitJid 
                + ", eightDigitJid=" + eightDigitJid 
                + ", site=" + site 
                + ", totalPagesInGroup=" + totalPagesInGroup 
                + ", weight="+ weight + ", size=" + size 
                + ", stationery=" + stationery
                + ", presentationPriority=" + presentationPriority 
                + ", sortField=" + sortField 
                + ", paperSize=" + paperSize 
                + ", dps=" + dps 
                + ", insertRef=" + insertRef 
                + ", envelope=" + envelope
                + ", mmBarcodeContent=" + mmBarcodeContent 
                + ", appName=" + appName
                + ", mmCustomerContent=" + mmCustomerContent 
                + ", runNo=" + runNo 
                + ", runDate=" + runDate
                + ", mailingId=" + mailingId 
                + ", noOfPages=" + noOfPages 
                + ", product=" + product
                + ", postcode=" + postcode
                + ", transactionID=" + transactionID + "]";
    }

}
