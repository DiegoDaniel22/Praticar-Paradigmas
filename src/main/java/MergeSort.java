import org.json.JSONArray;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MergeSort {

    private static long comparacoes = 0;
    private static long trocas = 0;

    public static void mergeSort(int[] array) {
        if (array.length < 2) {
            return;
        }

        int mid = array.length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, array.length);

        mergeSort(left);
        mergeSort(right);

        merge(array, left, right);
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            comparacoes++;
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
                trocas++;
            } else {
                array[k++] = right[j++];
                trocas++;
            }
        }

        while (i < left.length) {
            array[k++] = left[i++];
            trocas++;
        }

        while (j < right.length) {
            array[k++] = right[j++];
            trocas++;
        }
    }

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("src/jsons/milOrdenado.json");

            int[] numbers = readJsonArray(reader);

            long startTime = System.nanoTime();

            mergeSort(numbers);

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