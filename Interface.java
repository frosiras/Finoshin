import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interface {
    //Вот это сложное выражение на самом деле обозначает, что у тебя есть переменная reader, которая работает с вводом и читает из него.
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    //static - это значит, что такая переменная одна для всех объектов
    public static String answer = null;

    public static void main(String[] args) throws IOException {
        getTests();
        do {
            String expression = "";
            System.out.println(alert.INTRODUCTION.name);
            //Внутренний цикл работает, пока не введут 0 для окончания ввода или exit для выхода из программы.
            //Внешний цикл выходит из программы, если ввели exit
            do {
                answer = reader.readLine();
                if (!answer.equals("0")) {
                    if (expression.equals(""))
                        expression = answer;
                    else
                        expression = expression + "&" + answer;
                }
            } while (!answer.equals("0")&&!answer.equals("exit"));
            if (answer.equals("0")){
                // я не знал, какое имя дать переменной, поэтому назвал johny
                Solution johny = new Solution(expression);
            }
        } while (!answer.equals("exit"));
    }

    private static void getTests(){
        SolutionTest.test1("x1x2x3&x2x4x5&x1x5&x1x2");
    }

    //Это твои енумы
    enum alert{
        INTRODUCTION("Вводите выражение до следующего xor'a(Пример: x1x2x3) или же нажмите 0 для окончания записи. Для выхода из программы введите - exit");
        private final String name;
        alert(String name){
            this.name = name;
        }
    }
}
