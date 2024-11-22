import java.util.ArrayList;
import java.util.Collections;

public class BucketSort {
    public static void bucketSort(float[] array) {
        int n = array.length;
        if (n <= 0) return;

        ArrayList<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (float num : array) {
            int bucketIndex = (int) (num * n); // Mapeia o número ao índice do balde
            buckets[bucketIndex].add(num);
        }

        for (ArrayList<Float> bucket : buckets) {
            Collections.sort(bucket);
        }

        int index = 0;
        for (ArrayList<Float> bucket : buckets) {
            for (float num : bucket) {
                array[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        float[] array = {0.42f, 0.32f, 0.23f, 0.52f, 0.25f, 0.47f, 0.51f};
        System.out.println("Original Array: ");
        for (float num : array) {
            System.out.print(num + " ");
        }

        bucketSort(array);

        System.out.println("\nSorted Array: ");
        for (float num : array) {
            System.out.print(num + " ");
        }
    }
}