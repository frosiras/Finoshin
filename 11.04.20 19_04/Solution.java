import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    //Матрица для нашего решения. ArrayList - это обертка для массива. С памятью тебе работать не придется
    private ArrayList<ArrayList<String >> matrixOfPartExpr = new ArrayList<>();
    private String expression;

    public Solution(String expression){
        this.expression = expression;
        System.out.println("Изначальное выражение: " + expression);//Это чтобы видеть, как изначально выглядит наше выражение. Когда надо будет, то закомментим
        fillMatrixOfPartExpr();
        changeIntoNumberOfRepetitions();
    }

    //Переводим матрицу из кучи индексов в матрицу из повторений
    private void changeIntoNumberOfRepetitions() {
        // 1 Берем внутренний массив матрицы
        // 2 Сортируем его
        // 3 Считаем число одинаковых чисел и добавляем в массив с подсчетом индексов
        for (ArrayList<String> list : matrixOfPartExpr){ // 1 done
            Collections.sort(list); // 2 done

            // 3.1 Создать новый массив
            // 3.2 Посчитать повторяющиеся числа
            // 3.3 Добавить в массив это число
            int numberOfRepetitions = 0;
            ArrayList<Integer> tempArr = new ArrayList<>(); // 3.1 done
            for (String number : list){

            }
        }
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
