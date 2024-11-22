import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class BubbleSort {

    public static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            // Lê o arquivo JSON e extrai o array de números
            FileReader reader = new FileReader("numeros.json");
            int[] numbers = readJsonArray(reader);

            // Exibe o array original
            System.out.println("Original Array: " + Arrays.toString(numbers));

            // Ordena o array usando Bubble Sort
            bubbleSort(numbers);

            // Exibe o array ordenado
            System.out.println("Sorted Array: " + Arrays.toString(numbers));

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

        // Converte o JSONArray em um array de inteiros
        int[] numbers = new int[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            numbers[i] = jsonArray.getInt(i);
        }

        return numbers;
    }
}
