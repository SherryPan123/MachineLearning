package cn.edu.fudan.frequentItem.Apriori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sherry on 18-4-20.
 */
class ItemsList {

    List<Items> itemsList;

    ItemsList() {
        itemsList = new ArrayList<>();
    }

    void addElement(Items items) {
        itemsList.add(items);
    }

    ItemsList combineList() {
        ItemsList combineItemsList = new ItemsList();
        for (int i = 0; i < itemsList.size(); i++) {
            for (int j = i + 1; j < itemsList.size(); j++) {
                Items items1 = itemsList.get(i);
                Items items2 = itemsList.get(j);
                Items combineItems = items1.combine(items2);
                if (combineItems != null) {
                    combineItemsList.addElement(combineItems);
                }
            }
        }
        return combineItemsList;
    }

    ItemsList countAndFilter(List<Items> dataset, int minSupport) {
        ItemsList newItemsList = new ItemsList();
        Map<Items, Integer> countMap = new HashMap<>();
        for (Items items : itemsList) {
            for (Items data : dataset) {
                if (data.contains(items)) {
                    if (countMap.containsKey(items)) {
                        int cnt = countMap.get(items) + 1;
                        countMap.put(items, cnt);
                    } else {
                        countMap.put(items, 1);
                    }
                }
            }
        }
        for (Map.Entry<Items, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= minSupport) {
                newItemsList.addElement(entry.getKey());
            }
        }
        return newItemsList;
    }

    int getSize() {
        return itemsList.size();
    }

    public String toString() {
        return itemsList.toString();
    }
}
