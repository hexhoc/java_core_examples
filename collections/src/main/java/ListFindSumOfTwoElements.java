import java.util.HashMap;
import java.util.HashSet;

public class ListFindSumOfTwoElements {
    public static void main(String[] args) {
        int[] arr = {3,2,1,4};
        checkByTraversingArrayInArray(arr, 6);
        checkByHashMap(arr, 6);
    }

    public static int[] checkByTraversingArrayInArray(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == target) {
                    return new int[] {arr[i], arr[j]};
                }
            }
        }

        throw new IllegalArgumentException("No solution");
    }

    public static int[] checkByHashMap(int[] arr, int target) {
        HashSet<Integer> map = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            int diff = target - arr[i];
            if (map.contains(diff)) {
                return new int[] {diff, arr[i]};
            }
            map.add(arr[i]);
        }

        throw new IllegalArgumentException("No solution");
    }
}
