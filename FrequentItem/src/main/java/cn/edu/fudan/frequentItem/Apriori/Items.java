package cn.edu.fudan.frequentItem.Apriori;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sherry on 18-4-20.
 */
public class Items {

    private List<String> stringList;

    public Items(List<String> stringList) {
        this.stringList = stringList;
    }

    List<String> getStringList() {
        return stringList;
    }

    private void sort() {
        stringList.sort(String::compareTo);
    }

    Items combine(Items items2) {
        List<String> list2 = items2.getStringList();
        if (stringList.size() != list2.size()) return null;
        this.sort();
        items2.sort();

        List<String> combineList = new ArrayList<>();
        for (int i = 0; i < stringList.size() - 1; i++) {
            if (!stringList.get(i).equals(list2.get(i))) {
                return null;
            }
            combineList.add(stringList.get(i));
        }
        combineList.add(stringList.get(stringList.size() - 1));
        combineList.add(list2.get(list2.size() - 1));
        return new Items(combineList);
    }

    boolean contains(Items items) {
        if (items.getSize() > this.getSize()) return false;
        this.sort();
        items.sort();
        List<String> stringList2 = items.getStringList();
        int j = 0;
        for (String str : stringList) {
            if (str.equals(stringList2.get(j))) {
                j++;
                if (j == stringList2.size()) return true;
            }
        }
        return false;
    }

    public boolean equals(Object o) {
        return (o instanceof Items) && (isSame(stringList, ((Items) o).getStringList()));
    }

    private boolean isSame(List<String> strList1, List<String> strList2) {
        if (strList1.size() != strList2.size()) return false;
        for (int i = 0; i < strList1.size(); i++) {
            if (!strList1.get(i).equals(strList2.get(i))) return false;
        }
        return true;
    }

    private int getSize() {
        return stringList.size();
    }

    public String toString() {
        String s = "{";
        for (String str : stringList) {
            s += str + " ";
        }
        s += "}";
        return s;
    }

}
