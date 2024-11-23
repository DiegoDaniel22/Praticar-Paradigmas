import org.json.JSONArray;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SelectionSort {

    private static long comparacoes = 0;
    private static long trocas = 0;

    public static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                comparacoes++;
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
                trocas++;
            }
        }
    }

    public static void main(String[] args) {
        try {
            FileReader reader = new FileReader("src/jsons/milOrdenado.json");

            int[] numbers = readJsonArray(reader);

            long startTime = System.nanoTime();

            selectionSort(numbers);

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