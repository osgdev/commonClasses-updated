package uk.gov.dvla.osg.common.classes;

import org.apache.commons.lang3.StringUtils;

import uk.gov.dvla.osg.common.enums.BatchType;
import uk.gov.dvla.osg.common.enums.FullBatchType;
import uk.gov.dvla.osg.common.enums.Product;

public class Customer {

    private static final String ENDMARKER = "X";

    private String docRef, selectorRef, jobTypeAcronym, mailingId, runNo, runDate, subBatch, fleetNo, groupId, lang, paperSize, 
                   sortField, eog, sot, sob = "", name1, name2, add1, add2, add3, add4, add5, postcode, dps, msc, stationery, 
                   insertRef, envelopeName, customerContent, mmBarcodeContent, 
                   tenDigitJid, sequenceInChild, eightDigitJid, batchSequence, printingSite;

    Double weight, size;
    
    private Integer noOfPages, totalPagesInGroup, presentationPriority, originalIndex;

    private BatchType batchType;
    private Product product;

    private Customer(Builder builder) {
        this.originalIndex = builder.originalIndex;
        this.docRef = builder.docRef;
        this.selectorRef = builder.selectorRef;
        this.jobTypeAcronym = builder.jobTypeAcronym;
        this.mailingId = builder.mailingId;
        this.runNo = builder.runNo;
        this.runDate = builder.runDate;
        this.batchType = builder.batchType;
        this.subBatch = builder.subBatch;
        this.presentationPriority = builder.presentationPriority;        
        this.fleetNo = builder.fleetNo;
        this.groupId = builder.groupId;
        this.lang = builder.lang;
        this.paperSize = builder.paperSize;
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
        this.weight = builder.weight;
        this.size = builder.size;
        this.noOfPages = builder.noOfPages;
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
    
    public void updateBatchType(BatchType newBatchType, Integer priority) {
        this.batchType = newBatchType;
        this.presentationPriority = priority;
    }

    public boolean isBatchType(BatchType bt) {
        return this.batchType.equals(bt);
    }

    public String getSubBatch() {
        return subBatch;
    }
    
    public Integer getPresentationPriority() {
        return presentationPriority;
    }

    public void setPresentationPriority(Integer presentationPriority) {
        this.presentationPriority = presentationPriority;
    }
    
    public String getFleetNo() {
        return fleetNo;
    }
    
    public String getGroupId() {
        return groupId;
    }

    public void clearGroupId() {
        this.groupId = "";
    }
    
    public String getLang() {
        return lang;
    }
    
    public String getPaperSize() {
        return paperSize;
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
    
    public String getStationery() {
        return stationery;
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
        return customerContent;
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

    public void setTenDigitJid(Integer tenDigitJid) {
        this.tenDigitJid = tenDigitJid.toString();
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
        return weight;
    }
    
    public Double getSize() {
        return size;
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
        result = prime * result + ((paperSize == null) ? 0 : paperSize.hashCode());
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

    /* (non-Javadoc)
     * 
     * @see java.lang.Object#toString() */
    @Override
    public String toString() {
        // return new ToStringBuilder(this).append("docRef", docRef).toString();
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
                + ", site=" + printingSite
                + ", totalPagesInGroup=" + totalPagesInGroup
                + ", weight=" + weight + ", size=" + size
                + ", stationery=" + stationery
                + ", presentationPriority=" + presentationPriority
                + ", sortField=" + sortField
                + ", paperSize=" + paperSize
                + ", dps=" + dps
                + ", insertRef=" + insertRef
                + ", envelope=" + envelopeName
                + ", mmBarcodeContent=" + mmBarcodeContent
                + ", appName=" + jobTypeAcronym
                + ", mmCustomerContent=" + customerContent
                + ", runNo=" + runNo
                + ", runDate=" + runDate
                + ", mailingId=" + mailingId
                + ", noOfPages=" + noOfPages
                + ", product=" + product
                + ", postcode=" + postcode
                + "]";
    }

    /**
     * Creates builder to build {@link Customer}.
     * @return created builder
     */
    public static Builder builder(Integer originalIdx) {
        return new Builder(originalIdx);
    }

    /**
     * Builder to build {@link Customer}.
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
        private String paperSize;
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
        private String stationery;
        private String envelopeName;
        private String customerContent;
        private String mmBarcodeContent;
        private Double weight;
        private Double size;
        private Integer noOfPages;
        private Integer presentationPriority;
        private Integer originalIndex;
        private BatchType batchType;

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

        public Builder paperSize(String paperSize) {
            this.paperSize = paperSize;
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

        public Builder withStationery(String stationery) {
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

        public Builder weight(Double weight) {
            this.weight = weight;
            return this;
        }

        public Builder size(Double size) {
            this.size = size;
            return this;
        }

        public Builder noOfPages(Integer noOfPages) {
            this.noOfPages = noOfPages;
            return this;
        }

        public Builder withPresentationPriority(Integer presentationPriority) {
            this.presentationPriority = presentationPriority;
            return this;
        }

        public Builder withOriginalIndex(Integer originalIndex) {
            this.originalIndex = originalIndex;
            return this;
        }

        public Builder withBatchType(BatchType batchType) {
            this.batchType = batchType;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
