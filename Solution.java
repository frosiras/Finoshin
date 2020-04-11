import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    //Матрица для нашего решения. ArrayList - это обертка для массива. С памятью тебе работать не придется
    private List list = new ArrayList<ArrayList<String>>();
    private String expression;
    public Solution(String expression){
        this.expression = expression;
        System.out.println(expression);//Это чтобы видеть, как изначально выглядит наше выражение. Когда надо будет, то закомментим
        fillMatrix();
    }
    public void fillMatrix(){
        // Функция split делит строку на мн-во строк. Регулярное выражение здесь "&".
        List tempList = Arrays.asList(this.expression.split("&"));
        System.out.println(tempList);
    }
}
