package cn.edu.fudan.ml;

import java.io.*;
import java.util.*;

/**
 * Created by sherry on 17-10-10.
 */
public class KMeans {

    private final static int NumberOfType = 3;

    public static void main(String[] args) {
        // parameter k, d-dimension
        int k = 3;
        int d = 2;
        // read data
        String filename = "data/train.txt";
        List<DataUnit> training = readFromFile(filename);

        // input uncategorized data
        List<Double> detection = new ArrayList<Double>();
        System.out.println("input " + d + " dimension data");
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextDouble()){
            detection.add(sc.nextDouble());
        }
        // iteration: find nearest k neighbors
        int type = -1;
        while (type < 0 || k > detection.size()) {
            type = kmeans(training, detection, k);
            k += 2;
        }
        System.out.println("The detection is probably type " + type);
    }

    private static int kmeans(List<DataUnit> training, List<Double> detection, int k) {
        int type = -1;
        List<IdAndDis> queue = new ArrayList<IdAndDis>();
        for (int i = 0; i < training.size(); i++) {
            double dis = training.get(i).getDistance(detection);
            queue.add(new IdAndDis(i, dis));
        }
        Collections.sort(queue, (o1, o2) -> o1.distance - o2.distance > 0 ? 1 : -1);
        int[] voted = new int[NumberOfType + 1];
        int pt = 0;
        while (pt < k) {
            voted[queue.get(pt).id]++;
            pt++;
        }
        int mx = 0, mxId = 0;
        for (int i = 1; i <= NumberOfType; i++) {
            if (voted[i] > mx) {
                mxId = i;
                mx = voted[i];
            }
        }
        for (int i = 1; i <= NumberOfType; i++) {
            if (voted[i] == mx && mxId != i) {
                type = -1;
                break;
            }
        }
        return type;
    }

    private static class IdAndDis {
        int id;
        double distance;
        IdAndDis(int id, double distance) {
            this.id = id;
            this.distance = distance;
        }
    };

    private static List<DataUnit> readFromFile(String filename) {
        List<DataUnit> dataUnits = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] unit = line.split(" ");
                int categoryId = Integer.valueOf(unit[0]);
                List<Double> data = new ArrayList<>();
                for (int i = 1; i < unit.length; i++) {
                    data.add(Double.valueOf(unit[i]));
                }
                dataUnits.add(new DataUnit(categoryId, data));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataUnits;
    }

}
