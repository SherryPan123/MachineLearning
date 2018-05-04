package cn.edu.fudan.frequentItem.Apriori;

import cn.edu.fudan.frequentItem.readData.ReadData;

import java.util.List;

/**
 * Created by sherry on 18-4-20.
 */
public class AprioriTest {

    public static void main(String[] args) {
        List<Items> dataset = ReadData.readData("dataset/TD.txt");
        double minSup = 0.01;
        int minSupport = (int) (minSup * dataset.size());
        //int minSupport = 50;
        AprioriAlgorithm aprioriAlgorithm = new AprioriAlgorithm(dataset, minSupport);
        aprioriAlgorithm.getFrequentItems();
        aprioriAlgorithm.output();
    }

}
