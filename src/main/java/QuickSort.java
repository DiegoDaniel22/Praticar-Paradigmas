import org.json.JSONArray;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class QuickSort {

    private static long comparacoes = 0;
    private static long trocas = 0;

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int mid = low + (high - low) / 2;

        if (array[low] > array[mid]) {
            int temp = array[low];
            array[low] = array[mid];
            array[mid] = temp;
            trocas++;
        }
        if (array[low] > array[high]) {
            int temp = array[low];
            array[low] = array[high];
            array[high] = temp;
            trocas++;
        }
        if (array[mid] > array[high]) {
            int temp = array[mid];
            array[mid] = array[high];
            array[high] = temp;
            trocas++;
        }

        int pivot = array[mid];

        int temp = array[mid];
        array[mid] = array[high];
        array[high] = temp;
        trocas++;

        int i = (low - 1);
        for (int j = low; j < high; j++) {
            comparacoes++;
            if (array[j] <= pivot) {
                i++;
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                trocas++;
            }
        }

        temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        trocas++;

        return i + 1;
    }

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("src/jsons/milOrdenado.json");

            int[] numbers = readJsonArray(reader);

            long startTime = System.nanoTime();

            quickSort(numbers, 0, numbers.length - 1);

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

    private static int[] readJsonArray(FileReader reader) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        int ch;
        while ((ch = reader.read()) != -1) {
            jsonContent.append((char) ch);
        }
        JSONArray jsonArray = new JSONArray(jsonContent.toString());

        int[] numbers = new int[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            numbers[i] = jsonArray.getInt(i);
        }

        return numbers;
    }
}