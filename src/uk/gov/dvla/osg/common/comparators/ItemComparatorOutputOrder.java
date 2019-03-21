package uk.gov.dvla.osg.common.comparators;

import java.util.Comparator;

import uk.gov.dvla.osg.common.classes.Item;
import uk.gov.dvla.osg.common.config.PresentationPriorityLookup;

/*
 * SORT ORDER IS: 
 * LOCATION -> LANGUAGE -> STATIONERY -> PRESENTATION_ORDER -> SUB_BATCH -> 
 * SORT_FIELD -> FLEET_NO -> MSC -> GROUP_ID -> TRAY_ID -> SEQUENCE_IN_TRAY
 */
public class ItemComparatorOutputOrder implements Comparator<Item> {

    private PresentationPriorityLookup lookup;

    public ItemComparatorOutputOrder(PresentationPriorityLookup lookup) {
        this.lookup = lookup;

    }

    @Override
    public int compare(Item o1, Item o2) {

        // LOCATION
        int locationResult = o1.getPrintingSite().compareTo(o2.getPrintingSite());
        if (locationResult != 0) {
            return locationResult;
        }

        // LANGUAGE
        int langResult = o1.getLanguage().compareTo(o2.getLanguage());
        if (langResult != 0) {
            return langResult;
        }

        // STATIONERY
        int statResult = o1.getStationeryType().compareTo(o2.getStationeryType());
        if (statResult != 0) {
            return statResult;
        }

        // PRESENTATION_ORDER
        int i = lookup.lookupRunOrder(o1.getBatchType(), o1.getSubBatchType());
        int j = lookup.lookupRunOrder(o2.getBatchType(), o2.getSubBatchType());
        int presResult = i < j ? -1 : (i == j ? 0 : 1);
        if (presResult != 0) {
            return presResult;
        }

        // SUB_BATCH
        int subBatchResult = o1.getSubBatchType().compareTo(o2.getSubBatchType());
        if (subBatchResult != 0) {
            return subBatchResult;
        }

        // SORT_FIELD
        int sortFieldResult = o1.getSortField().compareTo(o2.getSortField());
        if (sortFieldResult != 0) {
            return sortFieldResult;
        }

        // FLEET_NO
        int fleetResult = o1.getFleetNo().compareTo(o2.getFleetNo());
        if (fleetResult != 0) {
            return fleetResult;
        }

        // MAILSORT CODE
        int mscResult = o1.getMailsortCode().compareTo(o2.getMailsortCode());
        if (mscResult != 0) {
            return mscResult;
        }

        // GROUP_ID
        int groupIdResult = o1.getGroupId().compareTo(o2.getGroupId());
        if (groupIdResult != 0) {
            return groupIdResult;
        }

        // TRAY_ID - unbox to int to overcome strange limitations with Integer
        i = o1.getTrayId() != null ? o1.getTrayId() : 0;
        j = o2.getTrayId() != null ? o2.getTrayId() : 0;
        int trayIdResult = i < j ? -1 : (i == j ? 0 : 1);
        if (trayIdResult != 0) {
            return trayIdResult;
        }

        // SEQUENCE_IN_TRAY - unbox to int to overcome strange limitations with Integer
        i = o1.getSequenceInTray() != null ? o1.getSequenceInTray() : 0;
        j = o2.getSequenceInTray() != null ? o2.getSequenceInTray() : 0;
        return i < j ? -1 : (i == j ? 0 : 1);
    }
}
