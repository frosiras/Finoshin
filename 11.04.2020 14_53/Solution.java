import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    //Матрица для нашего решения. ArrayList - это обертка для массива. С памятью тебе работать не придется
    private List matrixOfPartExpr = new ArrayList<ArrayList<String>>();
    private String expression;
    public Solution(String expression){
        this.expression = expression;
        System.out.println("Изначальное выражение: " + expression);//Это чтобы видеть, как изначально выглядит наше выражение. Когда надо будет, то закомментим
        fillMatrixOfPartExpr();
    }
    //Надо заполнить list
    public void fillMatrixOfPartExpr(){ // еще не окончена
        //1 Делим выражение по хор'ам
        //2 Для каждого элемента чекаем размер
        //3 Отправляем элемент в зависимости от размера в конкретный массив

        // Функция split делит строку на мн-во строк. Регулярное выражение здесь "&".
        List<String> dividedExpr = Arrays.asList(this.expression.split("&"));

        System.out.println("Делим по хор'ам: " + dividedExpr);
        System.out.println("--------------------------------");

        for (String string : dividedExpr){
            ArrayList<String> temp = new ArrayList<String>(Arrays.asList(string.split("x")));
            temp.remove(0);
            System.out.println("Вывод номеров переменных: " + temp);
            // Пункт 1 done
            
        }

        System.out.println("--------------------------------");
    }

    public String getExpression(){
        return this.expression;
    }
}
