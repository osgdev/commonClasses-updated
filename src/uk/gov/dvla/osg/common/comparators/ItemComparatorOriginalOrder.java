package uk.gov.dvla.osg.common.comparators;

import java.util.Comparator;

import uk.gov.dvla.osg.common.classes.Item;

public class ItemComparatorOriginalOrder implements Comparator<Item> {


    @Override
    public int compare(Item o1, Item o2) {
        
        int result = o1.getOriginalIdx().compareTo(o2.getOriginalIdx());
        if (result != 0)
        {
            return result;
        }

        return o1.getOriginalIdx().compareTo(o2.getOriginalIdx());

    }


}
