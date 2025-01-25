package homeworks.homework10;

import java.util.ArrayList;

public class Sequence {
    public static int[] filter(int[] array, ByCondition condition) {
        ArrayList<Integer> filteredList = new ArrayList<>();

        for (int number : array) {
            if (condition.isOk(number)) {
                filteredList.add(number);
            }
        }

        int[] result = new int[filteredList.size()];
        for (int i = 0; i < filteredList.size(); i++) {
            result[i] = filteredList.get(i);
        }

        return result;
    }
}