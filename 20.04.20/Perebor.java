import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Perebor {
    private static ArrayList<String> setOfNumbers = new ArrayList<>();
    public static ArrayList<String> getSetOfNumbers(){
        return setOfNumbers;
    }
    public static void findNumbers(int n) {
        setOfNumbers.clear();
        ArrayList<String> setOfFigures = new ArrayList<>();
        if (n == 0) {
            setOfNumbers.clear();
        }
        else if (n == 1)
            setOfNumbers.add("1");
        else if (n == 2) {
            setOfNumbers.add("1");
            setOfNumbers.add("2");
            setOfNumbers.add("12");
        }
        else {
            for (int i = 1; i < n + 1; i++)
                setOfFigures.add(i + "");
            // Вернихний цикл отвечает за число разрядов в числе
            for (int numberOfRank = 1; numberOfRank <= setOfFigures.size(); numberOfRank++) {
                // Цикл отвечает за расстояние между выбранными цифрами
                for (int distanceBetween = 1; distanceBetween < setOfFigures.size(); distanceBetween++) { // было setOfFigures.size()
                    // Цикл отвечает за проход, проверку, сортировку и вставку
                    for (int step = 0; step < setOfFigures.size(); step++) {
                        String tempNumber = "";
                        int nextIndex = step - 1;
                        //Получил число, отсортировал, проверил на входимость, вставил
                        for (int j = 0; j < numberOfRank; j++) {
                            if (j == 1)
                                nextIndex = (nextIndex + distanceBetween) % setOfFigures.size();
                            else nextIndex = (nextIndex + 1) % setOfFigures.size();
                            tempNumber += setOfFigures.get(nextIndex);
                        }
                        tempNumber = sortNumber(tempNumber);
                        if (!setOfNumbers.contains(tempNumber) && !hasTheSameFigure(tempNumber))
                            setOfNumbers.add(tempNumber);
                    }
                }
            }
        }
    }

    public static boolean hasTheSameFigure(String tempNumber){
        char[] arr = tempNumber.toCharArray();
        Arrays.sort(arr);
        for(int i = 0; i < arr.length-1; i++)
            if (arr[i] == arr[i+1])
                return true;
        return false;
    }

    public static String sortNumber(String number){
        String sortedNumber = "";
        char[] arr = number.toCharArray();
        Arrays.sort(arr);
        for (char c : arr)
            sortedNumber+=c;
        return sortedNumber;
    }

}
