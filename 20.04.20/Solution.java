import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    private static final boolean ISTEST = false;
    private static final boolean PRINTNEWVECTORS = false;
    //Матрица для нашего решения. ArrayList - это обертка для массива. С памятью тебе работать не придется
    private ArrayList<ArrayList<String>> matrixOfPartExpr = new ArrayList<>();
    private int maxIndex = 0;
    private String expression;
    private ArrayList<String> pairs = new ArrayList<>();
    private int ratingOfVector = 1;

    public Solution(String expression) {
        this.expression = expression;
        if (ISTEST)
            System.out.println("Изначальное выражение: " + expression); //Это чтобы видеть, как изначально выглядит наше выражение. Когда надо будет, то закомментим
        fillMatrixOfPartExpr();
        if (ISTEST)
            printMatrix();
        changeIntoNumberOfRepetitions();
        deleteZeroVectors();
        findTheSameColumns();
        System.out.println("Мощность равна: " + findRating(1));
    }

    //Переводим матрицу из кучи индексов в матрицу из повторений
    private void changeIntoNumberOfRepetitions() {
        if (ISTEST)
            System.out.println("Переводим матрицу индексов в матрицу повторений");
        // 1 Берем внутренний массив матрицы
        // 2 Сортируем его
        // 3 Считаем число одинаковых чисел и добавляем в массив с подсчетом индексов
        for (int j = 0; j < matrixOfPartExpr.size(); j++) { // 1 done
            ArrayList<String> list = matrixOfPartExpr.get(j);
            Collections.sort(list); // 2 done
            if (ISTEST)
                System.out.println(list);
            // 3.1 Создать новый массив
            // 3.2 Посчитать повторяющиеся числа
            // 3.3 Добавить в массив это число по индексу
            ArrayList<String> tempArr = new ArrayList<>(); // 3.1 done
            for (int i = 0; i <= maxIndex; i++)
                tempArr.add("0");
            int counter = 0;
            for (int currentVal = 0; currentVal < tempArr.size(); currentVal++) {
                for (int t = 0; t < list.size(); t++) {
                    if (currentVal == Integer.parseInt(list.get(t)))
                        counter++;
                }
                tempArr.set(currentVal, counter + "");
                counter = 0;

            }
            matrixOfPartExpr.set(j, tempArr);
        }
        matrixOfPartExpr.remove(0);
        for (int i = 0; i < matrixOfPartExpr.size(); i++)
            matrixOfPartExpr.get(i).remove(0);
    }
    //Ищем мощность
    private int findRating(int q) {
        Perebor.findNumbers(pairs.size());
        ArrayList<String> listOfNumbers = Perebor.getSetOfNumbers();
        deleteBigNumbers(listOfNumbers);
        if (PRINTNEWVECTORS)
            System.out.println(listOfNumbers);
        if (!listOfNumbers.isEmpty())
            // Все возможные индексы перебрали. Теперь пробуем их применить
            for (String s : listOfNumbers) {
                if (ISTEST)
                    System.out.println("Индексы " + s);
                isTheSameVectorTest(s);
            }
        return ratingOfVector;
    }

    //Удаляет числа, если их максимальный разряд больше половины максимального индекса
    private void deleteBigNumbers(ArrayList<String> listOfNumbers) {
        for (int i = 0; i < listOfNumbers.size(); i++){
            if (listOfNumbers.get(i).length()>(maxIndex/2)){
                listOfNumbers.remove(i);
            }
        }
    }

    private void isTheSameVectorTest(String s) {
        if (haveDoubleIndex(s)) {
            ArrayList<Character> tempExpression
                    = new ArrayList<>(
                    expression.chars()
                            .mapToObj(e -> (char) e)
                            .collect(
                                    Collectors.toList()
                            )
            );
            for (int j = 0; j < s.length(); j++) {
                char index = s.charAt(j);
                if (ISTEST)
                    System.out.println("Номер пары " + index);
                String string1 = pairs.get(index-49);
                char first = string1.charAt(0);
                first++;
                char second = string1.charAt(2);
                second++;
                if (ISTEST)
                    System.out.println("Меняем индексы местами: " + first + " " + second);
                //Заменяем индексы в выражении
                for (int i = 0; i < tempExpression.size(); i++) {
                    if (tempExpression.get(i).equals(first)&&tempExpression.get(i-1).equals('x'))
                        tempExpression.set(i, second);
                    else if (tempExpression.get(i).equals(second)&&tempExpression.get(i-1).equals('x'))
                        tempExpression.set(i, first);
                }
            }
            String comparedExpression = "";
            for (char t : tempExpression)
                comparedExpression += t;
            if (PRINTNEWVECTORS) {
                System.out.println(expression);
                System.out.println(comparedExpression);
            }
            if (isEqual(comparedExpression)) {
                ratingOfVector++;
            }
        }

    }

    private boolean haveDoubleIndex(String indexes) {
        // Строка с индексами indexes
        // берем первый индекс и еще какой-то
        // берем пары из массива pairs по этим индексам
        // Если у пар есть одинаковые индексы, то выбрасывает false
        for (int firstIndex = 0; firstIndex < indexes.length() - 1; firstIndex++) {
            for (int secondIndex = firstIndex + 1; secondIndex < indexes.length(); secondIndex++) {
                char first = indexes.charAt(firstIndex);
                char second = indexes.charAt(secondIndex);
                if (ISTEST)
                    System.out.println("Сравниваем пары " + first + " и " + second);
                String firstPair = pairs.get(first - 49);
                String secondPair = pairs.get(second - 49);
                if (ISTEST)
                    System.out.println("Тестим firstPair и SecondPair " + firstPair + " " + secondPair);
                char index1 = firstPair.charAt(0);
                char index2 = firstPair.charAt(2);
                if (secondPair.contains(("" + index1)) || secondPair.contains("" + index2)) {
                    if (ISTEST)
                        System.out.println(false);
                    return false;
                }
            }
        }
        if (ISTEST)
            System.out.println(true);
        return true;
    }

    //Проверка на то, что это один и тот же вектор
    private void isTheSameVector(String string) {
        // 1 делит строку на переменные
        // 2 выражение переходит в массив чаров и по нему идет цикл с заменой цифр
        if (ISTEST)
            System.out.println("Сравнивает полученное выражение с изначальным выражением для одной пары");
        char first = string.charAt(0);
        first++;
        char second = string.charAt(2);
        second++;
        if (PRINTNEWVECTORS)
            System.out.println("" + first + " и " + second);
        ArrayList<Character> tempExpression
                = new ArrayList<>(
                expression.chars()
                        .mapToObj(e -> (char) e)
                        .collect(
                                Collectors.toList()
                        )
        ); // Перевели кусок выражения в массив чаров

        //Заменяем индексы в выражении
        for (int i = 1; i < tempExpression.size(); i++) {
            if (tempExpression.get(i).equals(first) && tempExpression.get(i - 1).equals('x' )) {
                tempExpression.set(i, second);
            } else if (tempExpression.get(i).equals(second) && tempExpression.get(i - 1).equals('x' ))
                tempExpression.set(i, first);
        }
        String comparedExpression = "";
        for (char t : tempExpression)
            comparedExpression += t;
        if (PRINTNEWVECTORS) {
            System.out.println(tempExpression);
            System.out.println(comparedExpression);
        }
        //Проверяем, что измененное выражение соответсвует изначальному
        if (isEqual(comparedExpression)) {
            ratingOfVector++;
            System.out.println(ratingOfVector);
        }
    }

    // Проверка на равенство двух строк вне зависимости от того, как стоят элементы,
    // разделенные &
    private boolean isEqual(String string) {
        if (ISTEST)
            System.out.println("Проверка на равенство двух строк вне зависимости от того, как стоят элементы, разделенные &");
        // Отсортировали по индексам
        string = sortIndex(string);

        ArrayList<String> expressionList = new ArrayList<>(Arrays.asList(expression.split("&")));
        ArrayList<String> comparedExpressionList = new ArrayList<>(Arrays.asList(string.split("&")));
        if (PRINTNEWVECTORS)
            System.out.println(comparedExpressionList);
        // Сравниваем
        for (String temp : expressionList)
            if (!comparedExpressionList.contains(temp))
                return false;
        return true;
    }

    //Сортируем каждую строку по индексам
    private String sortIndex(String string) {
        // получил строку
        // разбил на кучу подстрок по &
        // каждую сортируешь пузырьком
        if (ISTEST)
            System.out.println("Сортируем по индексам");
        String newString = "";
        ArrayList<String> list = new ArrayList<>(Arrays.asList(string.split("&")));
        for (int i = 0; i < list.size(); i++) {
            ArrayList<Character> tempExpression
                    = new ArrayList<>(
                    list.get(i).chars()
                            .mapToObj(e -> (char) e)
                            .collect(
                                    Collectors.toList()
                            )
            ); // Разбили на подстроки и каждую перевели в массив чаров

            // Тупо пузырек
            for (int k = 1; k < tempExpression.size(); k += 2) {
                for (int j = 1; j < tempExpression.size() - 2; j += 2) {
                    if (tempExpression.get(j) > tempExpression.get(j + 2)) {
                        char temp = tempExpression.get(j);
                        tempExpression.set(j, tempExpression.get(j + 2));
                        tempExpression.set(j + 2, temp);
                    }
                }

            } // Отсортировали

            // Забиваем это все в новую строку
            for (char c : tempExpression)
                newString = newString + c;
            newString += "&";
        }
        if (PRINTNEWVECTORS)
            System.out.println(newString);
        return newString;
    }

    //Удаляем нулевые вектора
    public void deleteZeroVectors() {
        if (ISTEST)
            System.out.println("Удаляем нулевые вектора");
        int counter = 0;
        for (int currentArrNumber = 0; currentArrNumber < matrixOfPartExpr.size(); currentArrNumber++) {
            ArrayList<String> vector = matrixOfPartExpr.get(currentArrNumber);
            for (int index = 0; index < vector.size(); index++) {
                if (vector.get(index).equals("0"))
                    counter++;
            }
            if (counter == maxIndex)
                matrixOfPartExpr.remove(currentArrNumber);
            counter = 0;
        }
        if (ISTEST)
            printMatrix();
    }

    //Заполняем матрицу попавшимися элементами. Вводятся их номера.
    private void fillMatrixOfPartExpr() { // еще не окончена
        if (ISTEST)
            System.out.println("Заполняем матрицу");
        //1 Делим выражение по хор'ам
        //2 Для каждого вектора чекаем размер
        //3 Отправляем вектор в зависимости от размера в конкретный массив

        // Функция split делит строку на мн-во строк. Регулярное выражение здесь "&".
        List<String> dividedExpr = Arrays.asList(this.expression.split("&"));
        //System.out.println("Делим по хор'ам: " + dividedExpr);

        for (String string : dividedExpr) {
            ArrayList<String> temp = new ArrayList<String>(Arrays.asList(string.split("x")));
            temp.remove(0);

            //2.1 Получить размер вектора
            int numberOfParameters = temp.size();

            //3.1 Посмотреть, если ли для него место в массиве
            //3.2.1 Есть - добавить его туда
            //3.2.2 Нет - создавать массивы, пока не дойдешь до массива, которая хранит вектор неоходимого размера

            //3.2.2
            if (numberOfParameters >= matrixOfPartExpr.size()) { //3.2.2
                while (numberOfParameters >= matrixOfPartExpr.size()) {
                    matrixOfPartExpr.add(matrixOfPartExpr.size(), new ArrayList<String>());
                }
            }

            //3.2.1
            for (String number : temp) {
                int index = Integer.parseInt(number);
                if (index > maxIndex) {
                    maxIndex = index;
                }
                matrixOfPartExpr.get(temp.size()).add(number); // Добавляем эти наборы во внутрении массивы
            }
        }

    }

    public void printMatrix() {
        System.out.println("Выводим матрицу: ");
        for (ArrayList<String> list : matrixOfPartExpr) {
            System.out.println(list);
        }
    }

    public void printMatrix(ArrayList<ArrayList<String>> matrix) {
        System.out.println("Выводим матрицу: ");
        for (ArrayList<String> list : matrix) {
            System.out.println(list);
        }
    }

    public int getRatingOfVector() {
        return ratingOfVector;
    }

    private void findTheSameColumns() {
        if (ISTEST)
            System.out.println("Ищем одинаковые столбцы");
        // 1 Транспонируем матрицу
        // 2 Ищем одинаковые строки
        ArrayList<ArrayList<String>> transposedMatrix = transposeMatrix();
        String sameColumn;
        for (int currentArray = 0; currentArray < transposedMatrix.size(); currentArray++) {
            for (int comparedArray = currentArray + 1; comparedArray < transposedMatrix.size(); comparedArray++) {
                sameColumn = currentArray + "&";
                if (transposedMatrix.get(currentArray).equals(transposedMatrix.get(comparedArray))) {
                    sameColumn = sameColumn + comparedArray;
                    pairs.add(sameColumn);
                }
            }
        }
        System.out.println("Одинаковые столбцы " + pairs);
    }

    // Транспонируем матрицу
    private ArrayList<ArrayList<String>> transposeMatrix() {
        if (ISTEST)
            System.out.println("Транспонируем матрицу");
        ArrayList<ArrayList<String>> transposedMatrix = new ArrayList<>();
        ArrayList<String> tempArray = new ArrayList<>();
        for (int i = 0; i < maxIndex; i++) {
            for (int j = 0; j < matrixOfPartExpr.size(); j++) {
                tempArray.add(matrixOfPartExpr.get(j).get(i));
            }
            transposedMatrix.add(tempArray);
            tempArray = new ArrayList<>();
        }
        if (ISTEST)
            printMatrix(transposedMatrix);
        return transposedMatrix;
    }


    public String getExpression() {
        return this.expression;
    }
}
