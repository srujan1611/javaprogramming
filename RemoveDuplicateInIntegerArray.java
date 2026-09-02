import java.util.Arrays;
import java.util.Scanner;
public class RemoveDuplicateInIntegerArray {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the size of the array: ");
            int size = scanner.nextInt();
            int[] array = new int[size];

            System.out.println("Enter the elements of the array:");
            for (int i = 0; i < size; i++) {
                array[i] = scanner.nextInt();
            }

            int[] uniqueArray = removeDuplicates(array);
            System.out.println("Array after removing duplicates:");
            for (int num : uniqueArray) {
                System.out.print(num + " ");
            }
        }
    }

    public static int[] removeDuplicates(int[] array) {
        int n = array.length;
        if (n == 0) return new int[0];

        // Sort the array
        Arrays.sort(array);

        // Create a temporary array to hold unique elements
        int[] temp = new int[n];
        int j = 0;

        // Traverse through the sorted array and store unique elements
        for (int i = 0; i < n - 1; i++) {
            if (array[i] != array[i + 1]) {
                temp[j++] = array[i];
            }
        }
        // Add the last element
        temp[j++] = array[n - 1];

        // Create a new array with the size of unique elements
        return Arrays.copyOf(temp, j);
    }
}