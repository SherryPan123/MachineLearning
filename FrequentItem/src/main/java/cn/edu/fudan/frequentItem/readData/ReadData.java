package cn.edu.fudan.frequentItem.readData;

import cn.edu.fudan.frequentItem.Apriori.Items;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sherry on 18-4-20.
 */
public class ReadData {

    public static List<Items> readData(String filename) {
        List<Items> dataset = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] element = line.split(",|\\s+");
                List<String> itemsList = new ArrayList<>();
                for (int i = 0; i < element.length; i++) {
                    itemsList.add(element[i]);
                }
                Items items = new Items(itemsList);
                dataset.add(items);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataset;
    }
}
