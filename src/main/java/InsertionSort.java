import org.json.JSONArray;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class InsertionSort {

    private static long comparacoes = 0;
    private static long trocas = 0;

    public static void insertionSort(int[] array) {
        int n = array.length;
        System.out.println(n);
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0) {
                comparacoes++;
                if (array[j] > key) {
                    array[j + 1] = array[j];
                    trocas++;
                    j = j - 1;
                } else {
                    break;
                }
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("src/jsons/milOrdenado.json");

            int[] numbers = readJsonArray(reader);

            long startTime = System.nanoTime();

            insertionSort(numbers);

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