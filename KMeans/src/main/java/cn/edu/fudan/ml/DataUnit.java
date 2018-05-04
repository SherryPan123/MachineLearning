package cn.edu.fudan.ml;

import java.util.List;

/**
 * Created by sherry on 17-10-10.
 */
public class DataUnit {

    private int categoryId;
    private List<Double> value;

    DataUnit(int categoryId, List<Double> value) {
        this.categoryId = categoryId;
        this.value = value;
    }

    public double getDistance(List<Double> detection) {
        // Euclidean distance
        double euc = 0d;
        for (int i = 0; i < detection.size(); i++) {
            euc += (detection.get(i) - value.get(i)) * (detection.get(i) - value.get(i));
        }
        return Math.sqrt(euc);
    }
}
