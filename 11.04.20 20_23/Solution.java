import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    //Матрица для нашего решения. ArrayList - это обертка для массива. С памятью тебе работать не придется
    private ArrayList<ArrayList<String>> matrixOfPartExpr = new ArrayList<>();
    private int maxIndex = 0;
    private String expression;

    public Solution(String expression){
        this.expression = expression;
        System.out.println("Изначальное выражение: " + expression); //Это чтобы видеть, как изначально выглядит наше выражение. Когда надо будет, то закомментим
        fillMatrixOfPartExpr();
        changeIntoNumberOfRepetitions();
    }

    //Переводим матрицу из кучи индексов в матрицу из повторений
    private void changeIntoNumberOfRepetitions() {
        // 1 Берем внутренний массив матрицы
        // 2 Сортируем его
        // 3 Считаем число одинаковых чисел и добавляем в массив с подсчетом индексов
        for (int j = 0; j < matrixOfPartExpr.size(); j++) { // 1 done
            ArrayList<String> list = matrixOfPartExpr.get(j);
            Collections.sort(list); // 2 done
            System.out.println(list);
            // 3.1 Создать новый массив
            // 3.2 Посчитать повторяющиеся числа
            // 3.3 Добавить в массив это число по индексу
            int numberOfRepetitions = 1;
            ArrayList<String> tempArr = new ArrayList<>(); // 3.1 done
            for (int i = 0; i <= maxIndex; i++)
                tempArr.add("0");
            for (int i = 1; i < list.size(); i++){
                if (list.get(i-1).equals(list.get(i)))
                    numberOfRepetitions++;
                else {
                    int currentIndex = Integer.parseInt(list.get(i-1));// значение этого индекса
                    tempArr.set(currentIndex, numberOfRepetitions+"");
                    numberOfRepetitions = 1;
                    if (i+1 == list.size())
                        tempArr.set(Integer.parseInt(list.get(i)), "1");
                }
            }
            matrixOfPartExpr.set(j,tempArr);
        }
        matrixOfPartExpr.remove(0);
        for (int i = 0; i < matrixOfPartExpr.size(); i++)
            matrixOfPartExpr.get(i).remove(0);
        System.out.println(matrixOfPartExpr);
    }

    //Заполняем матрицу попавшимися элементами. Вводятся их номера.
    private void fillMatrixOfPartExpr(){ // еще не окончена
        //1 Делим выражение по хор'ам
        //2 Для каждого вектора чекаем размер
        //3 Отправляем вектор в зависимости от размера в конкретный массив

        // Функция split делит строку на мн-во строк. Регулярное выражение здесь "&".
        List<String> dividedExpr = Arrays.asList(this.expression.split("&"));

        System.out.println("Делим по хор'ам: " + dividedExpr);
        System.out.println("--------------------------------");

        for (String string : dividedExpr){
            ArrayList<String> temp = new ArrayList<String>(Arrays.asList(string.split("x")));
            temp.remove(0);
            System.out.println("Вывод номеров переменных: " + temp);
            // Пункт 1 done

            //2.1 Получить размер вектора
            int numberOfParameters = temp.size();

            //3.1 Посмотреть, если ли для него место в массиве
            //3.2.1 Есть - добавить его туда
            //3.2.2 Нет - создавать массивы, пока не дойдешь до массива, которая хранит вектор неоходимого размера

            //3.2.2
            if (numberOfParameters>=matrixOfPartExpr.size()){ //3.2.2
                while (numberOfParameters>=matrixOfPartExpr.size()){
                    matrixOfPartExpr.add(matrixOfPartExpr.size(), new ArrayList<String>());
                }
            }

            //3.2.1
            for (String number : temp){
                int index = Integer.parseInt(number);
                if (index > maxIndex){
                    maxIndex = index;
                }
                matrixOfPartExpr.get(temp.size()).add(number); // Добавляем эти наборы во внутрении массивы
            }
            System.out.println(matrixOfPartExpr);
            //Пункт 2 и 3 done
        }

        System.out.println("--------------------------------");
    }



    public String getExpression(){
        return this.expression;
    }
}
