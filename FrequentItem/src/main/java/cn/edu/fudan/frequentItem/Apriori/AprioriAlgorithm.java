package cn.edu.fudan.frequentItem.Apriori;

import java.util.*;

/**
 * Created by sherry on 18-4-20.
 */
class AprioriAlgorithm {

    private List<Items> dataset;
    private int minSupport;

    private List<ItemsList> frequentItemsLists;

    AprioriAlgorithm(List<Items> dataset, int minSupport) {
        this.dataset = dataset;
        this.minSupport = minSupport;
    }

    void getFrequentItems() {
        frequentItemsLists = new ArrayList<>();
        frequentItemsLists.add(new ItemsList()); // 0 no meanings
        // First scan the dataset, calculate times of each word
        Map<String, Integer> countMap = new HashMap<>();
        for (Items data : dataset) {
            List<String> items = data.getStringList();
            for (String str : items) {
                if (countMap.containsKey(str)) {
                    int cnt = countMap.get(str) + 1;
                    countMap.put(str, cnt);
                    continue;
                }
                countMap.put(str, 1);
            }
        }

        // Filter the map
        ItemsList frequentItemsList = new ItemsList();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() >= minSupport) {
                String str = entry.getKey();
                List<String> stringList = new ArrayList<>();
                stringList.add(str);
                Items items = new Items(stringList);
                frequentItemsList.addElement(items);
            }
        }
        frequentItemsLists.add(frequentItemsList);
        // Combine items of last frequent items list
        ItemsList itemsList = frequentItemsList.combineList();
        int k = 0;
        while (true) {
            frequentItemsList = itemsList.countAndFilter(dataset, minSupport);
            if (frequentItemsList.getSize() < 1) break;
            frequentItemsLists.add(frequentItemsList);
            itemsList = frequentItemsList.combineList();
            if (k++ > 5) break;
        }
    }

    void output() {
        for (int i = 1; i < frequentItemsLists.size(); i++) {
            System.out.println(frequentItemsLists.get(i).toString());
        }
    }

}
