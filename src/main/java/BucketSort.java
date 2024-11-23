import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BucketSort {

    private static long comparacoes = 0;
    private static long trocas = 0;

    public static void bucketSort(int[] array) {
        int n = array.length;
        if (n <= 0) return;

        int maxValue = Arrays.stream(array).max().orElse(Integer.MAX_VALUE);
        int minValue = Arrays.stream(array).min().orElse(Integer.MIN_VALUE);

        int bucketCount = (maxValue - minValue) / n + 1;
        ArrayList<Integer>[] buckets = new ArrayList[bucketCount];

        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        for (int num : array) {
            int bucketIndex = (num - minValue) * (bucketCount - 1) / (maxValue - minValue);
            comparacoes++;
            buckets[bucketIndex].add(num);
        }

        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            bucket.sort(Integer::compareTo);
            for (int num : bucket) {
                comparacoes++;
                if (array[index] != num) {
                    trocas++;
                }
                array[index++] = num;
            }
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/jsons/milOrdenado.json"));

            int[] numbers = readJsonArray(reader);

            long startTime = System.nanoTime();

            bucketSort(numbers);

            long endTime = System.nanoTime();
            long duration = endTime - startTime;

            System.out.println("Sorted Array: " + Arrays.toString(numbers));
            System.out.println("Total de comparações: " + comparacoes);
            System.out.println("Total de trocas: " + trocas);
            System.out.println("Tempo de execução: " + duration + " nanosegundos");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readJsonArray(BufferedReader reader) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }

        JSONArray jsonArray = new JSONArray(jsonContent.toString());

        int[] numbers = new int[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            numbers[i] = jsonArray.getInt(i);
        }

        return numbers;
    }
}