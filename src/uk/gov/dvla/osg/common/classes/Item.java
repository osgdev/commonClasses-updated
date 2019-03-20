package uk.gov.dvla.osg.common.classes;

import org.apache.commons.lang3.StringUtils;

import uk.gov.dvla.osg.common.enums.BatchType;
import uk.gov.dvla.osg.common.enums.FullBatchType;
import uk.gov.dvla.osg.common.enums.Product;

public class Item {

    private static final String ENDMARKER = "X";

    private String docRef, selectorRef, jobTypeAcronym, mailingId, runNo, runDate, subBatch, fleetNo, groupId, lang, 
                   sortField, eog, sot, sob, name1, name2, add1, add2, add3, add4, add5, postcode, dps, msc, 
                   insertRef, envelopeName, customerContent, mmBarcodeContent, 
                   tenDigitJid, sequenceInChild, eightDigitJid, batchSequence, printingSite;

    private StationeryData stationery;
    private Integer noOfPages, totalPagesInGroup, originalIndex, paperSizeMultiplier, trayId, sequenceInTray;

    private BatchType batchType;
    private Product product;

    private Item(Builder builder) {
        this.originalIndex = builder.originalIndex;
        this.docRef = builder.docRef;
        this.selectorRef = builder.selectorRef;
        this.jobTypeAcronym = builder.jobTypeAcronym;
        this.mailingId = builder.mailingId;
        this.runNo = builder.runNo;
        this.runDate = builder.runDate;
        this.batchType = builder.batchType;
        this.subBatch = builder.subBatch;
        this.fleetNo = builder.fleetNo;
        this.groupId = builder.groupId;
        this.lang = builder.lang;
        this.paperSizeMultiplier = builder.paperSizeMultiplier;
        this.sortField = builder.sortField;
        this.name1 = builder.name1;
        this.name2 = builder.name2;
        this.add1 = builder.add1;
        this.add2 = builder.add2;
        this.add3 = builder.add3;
        this.add4 = builder.add4;
        this.add5 = builder.add5;
        this.postcode = builder.postcode;
        this.dps = builder.dps;
        this.msc = builder.msc;
        this.stationery = builder.stationery;
        this.envelopeName = builder.envelopeName;
        this.customerContent = builder.customerContent;
        this.mmBarcodeContent = builder.mmBarcodeContent;
        this.noOfPages = builder.noOfPages;
        this.eog = builder.endOfGroup;
        this.insertRef = builder.insertRef;
    }

    public Integer getOriginalIdx() {
        return originalIndex;
    }
    
    public String getDocRef() {
        return docRef;
    }
    
    public String getSelectorRef() {
        return selectorRef;
    }
    
    public String getJobTypeAcronym() {
        return jobTypeAcronym;
    }
    
    public String getMailingId() {
        return this.mailingId;
    }

    public String getRunNo() {
        return runNo;
    }

    public String getRunDate() {
        return runDate;
    }

    public BatchType getBatchType() {
        return batchType;
    }
    
    public void updateBatchType(BatchType newBatchType) {
        this.batchType = newBatchType;
        if (newBatchType.equals(BatchType.UNSORTED)) {
            this.msc = "";
        }
    }

    public boolean isBatchType(BatchType bt) {
        return this.batchType.equals(bt);
    }

    public String getSubBatchType() {
        return StringUtils.defaultString(subBatch);
    }
    
    public String getFleetNo() {
        return fleetNo;
    }
    
    public String getGroupId() {
        return StringUtils.defaultString(groupId);
    }

    public void clearGroupId() {
        this.groupId = "";
    }
    
    public String getLanguage() {
        return lang;
    }
    
    public int getPaperSizeMultiplier() {
        return paperSizeMultiplier;
    }
    
    public String getSortField() {
        return sortField;
    }

    public String getEog() {
        return eog;
    }

    public void setEog() {
        this.eog = ENDMARKER;
    }
    
    public void clearEog() {
        this.eog = "";
    }

    public boolean isEog() {
        return StringUtils.isNotBlank(eog);
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

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public String getAdd1() {
        return add1;
    }
    
    public String getAdd2() {
        return add2;
    }

    public String getAdd3() {
        return add3;
    }

    public String getAdd4() {
        return add4;
    }

    public String getAdd5() {
        return add5;
    }

    public String getPostcode() {
        return postcode;
    }
    
    public String getDps() {
        return dps;
    }

    public void setDps(String dps) {
        this.dps = dps;
    }
    
    public String getMsc() {
        return msc;
    }

    public void setMsc(String msc) {
        this.msc = msc;
    }
    
    public StationeryData getStationery() {
        return stationery;
    }
    
    public String getStationeryType() {
        return stationery.getType();
    }
    
    public String getInsertRef() {
        return insertRef;
    }

    public void setInsertRef(String insertRef) {
        this.insertRef = insertRef;
    }
    
    public int getNoOfPages() {
        return noOfPages;
    }
    
    public Integer getTotalPagesInGroup() {
        return totalPagesInGroup;
    }

    public void setTotalPagesInGroup(int totalPagesInGroup) {
        this.totalPagesInGroup = totalPagesInGroup;
    } 
    
    public String getEnvelopeName() {
        return envelopeName;
    }

    public void setEnvelopeName(String envelope) {
        this.envelopeName = envelope;
    }
    
    public String getCustomerContent() {
        return StringUtils.defaultString(customerContent);
    }

    public void setCustomerContent(String mmCustomerContent) {
        this.customerContent = mmCustomerContent;
    }
    
    public String getMmBarcodeContent() {
        return mmBarcodeContent;
    }

    public void setMmBarcodeContent(String mmBarcodeContent) {
        this.mmBarcodeContent = mmBarcodeContent;
    }
    
    public String getTenDigitJid() {
        return tenDigitJid;
    }

    public void setTenDigitJid(long tenDigitJid) {
        this.tenDigitJid = Long.toString(tenDigitJid);
    }

    public String getSequenceInChild() {
        return sequenceInChild;
    }

    public void setSequenceInChild(Integer sequence) {
        this.sequenceInChild = sequence.toString();
    }

    public String getEightDigitJid() {
        return eightDigitJid;
    }

    public void setEightDigitJid(Integer jid) {
        this.eightDigitJid = jid.toString();
    }
    
    public String getBatchSequence() {
        return batchSequence;
    }

    public void setBatchSequence(Integer batchSequence) {
        this.batchSequence = batchSequence.toString();
    }
    
    public String getRpdJid() {
        return String.format("%s.%s", eightDigitJid, batchSequence);
    }
    
    public String getPrintingSite() {
        return printingSite;
    }

    public void setPrintingSite(String site) {
        this.printingSite = site;
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

    public Double getWeight() {
        return stationery.getWeight() * noOfPages;
    }
    
    
    public Double getThickness() {
        return (stationery.getThickness() / paperSizeMultiplier) * noOfPages;
    }
    
    
    public FullBatchType getFullBatchType() {
        return FullBatchType.valueOf(batchType.name() + lang);
    }

    public String getFullBatchName() {
        return batchType.name() + lang;
    }
    
    public String[] print() {
        String[] str = { this.docRef, this.printingSite, this.eightDigitJid.toString() };
        return str;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((batchType == null) ? 0 : batchType.hashCode());
        result = prime * result + ((lang == null) ? 0 : lang.hashCode());
        result = prime * result + ((paperSizeMultiplier == null) ? 0 : paperSizeMultiplier.hashCode());
        result = prime * result + ((printingSite == null) ? 0 : printingSite.hashCode());
        result = prime * result + ((stationery == null) ? 0 : stationery.hashCode());
        result = prime * result + ((subBatch == null) ? 0 : subBatch.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        if (batchType == null) {
            if (other.batchType != null) return false;
        } else if (!batchType.equals(other.batchType)) return false;
        if (lang == null) {
            if (other.lang != null) return false;
        } else if (!lang.equals(other.lang)) return false;
        if (paperSizeMultiplier == null) {
            if (other.paperSizeMultiplier != null) return false;
        } else if (!paperSizeMultiplier.equals(other.paperSizeMultiplier)) return false;
        if (printingSite == null) {
            if (other.printingSite != null) return false;
        } else if (!printingSite.equals(other.printingSite)) return false;
        if (stationery == null) {
            if (other.stationery != null) return false;
        } else if (!stationery.equals(other.stationery)) return false;
        if (subBatch == null) {
            if (other.subBatch != null) return false;
        } else if (!subBatch.equals(other.subBatch)) return false;
        return true;
    }


    /**
     * Creates builder to build {@link Item}.
     * @return created builder
     */
    public static Builder builder(Integer originalIdx) {
        return new Builder(originalIdx);
    }

    /**
     * Builder to build {@link Item}.
     */
    @SuppressWarnings("hiding")
    public static final class Builder {
        private String docRef;
        private String selectorRef;
        private String jobTypeAcronym;
        private String mailingId;
        private String runNo;
        private String runDate;
        private String subBatch;
        private String fleetNo;
        private String groupId;
        private String lang;
        private int paperSizeMultiplier;
        private String sortField;
        private String name1;
        private String name2;
        private String add1;
        private String add2;
        private String add3;
        private String add4;
        private String add5;
        private String postcode;
        private String dps;
        private String msc;
        private StationeryData stationery;
        private String envelopeName;
        private String customerContent;
        private String mmBarcodeContent;
        private Integer noOfPages;
        private Integer originalIndex;
        private BatchType batchType;
        private String endOfGroup;
        private String insertRef;

        private Builder(Integer originalIdx) {
            this.originalIndex = originalIdx;
        }

        public Builder docRef(String docRef) {
            this.docRef = docRef;
            return this;
        }

        public Builder selectorRef(String selectorRef) {
            this.selectorRef = selectorRef;
            return this;
        }

        public Builder jobTypeAcronym(String jobTypeAcronym) {
            this.jobTypeAcronym = jobTypeAcronym;
            return this;
        }

        public Builder mailingId(String mailingId) {
            this.mailingId = mailingId;
            return this;
        }

        public Builder runNo(String runNo) {
            this.runNo = runNo;
            return this;
        }

        public Builder runDate(String runDate) {
            this.runDate = runDate;
            return this;
        }

        public Builder subBatch(String subBatch) {
            this.subBatch = subBatch;
            return this;
        }

        public Builder fleetNo(String fleetNo) {
            this.fleetNo = fleetNo;
            return this;
        }

        public Builder groupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder lang(String lang) {
            this.lang = lang;
            return this;
        }

        public Builder paperSizeMultiplier(int paperSizeMultiplier) {
            this.paperSizeMultiplier = paperSizeMultiplier;
            return this;
        }

        public Builder sortField(String sortField) {
            this.sortField = sortField;
            return this;
        }

        public Builder name1(String name1) {
            this.name1 = name1;
            return this;
        }

        public Builder name2(String name2) {
            this.name2 = name2;
            return this;
        }

        public Builder add1(String add1) {
            this.add1 = add1;
            return this;
        }

        public Builder add2(String add2) {
            this.add2 = add2;
            return this;
        }

        public Builder add3(String add3) {
            this.add3 = add3;
            return this;
        }

        public Builder add4(String add4) {
            this.add4 = add4;
            return this;
        }

        public Builder add5(String add5) {
            this.add5 = add5;
            return this;
        }

        public Builder postcode(String postcode) {
            this.postcode = postcode;
            return this;
        }

        public Builder dps(String dps) {
            this.dps = dps;
            return this;
        }

        public Builder msc(String msc) {
            this.msc = msc;
            return this;
        }

        public Builder stationery(StationeryData stationery) {
            this.stationery = stationery;
            return this;
        }

        public Builder envelopeName(String envelopeName) {
            this.envelopeName = envelopeName;
            return this;
        }

        public Builder customerContent(String customerContent) {
            this.customerContent = customerContent;
            return this;
        }

        public Builder mmBarcodeContent(String mmBarcodeContent) {
            this.mmBarcodeContent = mmBarcodeContent;
            return this;
        }

        public Builder noOfPages(Integer noOfPages) {
            this.noOfPages = noOfPages;
            return this;
        }

        public Builder originalIndex(Integer originalIndex) {
            this.originalIndex = originalIndex;
            return this;
        }

        public Builder batchType(String batchType) {
            this.batchType = BatchType.valueOf(batchType);
            return this;
        }

        public Builder endOfGroup(String endOfGroup) {
            this.endOfGroup = endOfGroup;
            return this;
        }
        
        public Builder insertRef(String insertRef) {
            this.insertRef = insertRef;
            return this;
        }
        
        public Item build() {
            return new Item(this);
        }


    }

    public void setSequenceInTray(int index) {
        this.sequenceInTray = index;
    }

    public void setTrayId(int id) {
        this.trayId = id;
    }

    /**
     * Gets the tray id.
     *
     * @return the tray id if assigned, else -1
     */
    public Integer getTrayId() {
        return trayId;
    }

    /**
     * Gets the sequence in tray.
     *
     * @return the sequence in tray if assigned else -1
     */
    public Integer getSequenceInTray() {
        return sequenceInTray;
    }
}
