import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HeapSort {

    private static long comparacoes = 0;
    private static long trocas = 0;

    public static void heapSort(int[] array) {
        int n = array.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            trocas++;

            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n) {
            comparacoes++;
            if (array[left] > array[largest]) {
                largest = left;
            }
        }

        if (right < n) {
            comparacoes++;
            if (array[right] > array[largest]) {
                largest = right;
            }
        }

        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            trocas++;

            heapify(array, n, largest);
        }
    }

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/jsons/milDecrescente.json"));

            int[] numbers = readJsonArray(reader);

            long startTime = System.nanoTime();

            heapSort(numbers);

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