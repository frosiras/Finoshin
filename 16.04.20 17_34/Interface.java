import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

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
                try {
                    checkAnswer(answer);
                } catch (Exception e) {
                    System.out.println(alert.REWRITE.name);
                    answer = "";
                }
                if (!answer.equals("0")) {
                    if (expression.equals(""))
                        expression = answer;
                    else
                        expression = expression + "&" + answer;
                }
            } while (!answer.equals("0") && !answer.equals("exit"));
            if (answer.equals("0")) {
                // я не знал, какое имя дать переменной, поэтому назвал johny
                Solution johny = new Solution(expression);
            }
        } while (!answer.equals("exit"));
    }

    //Если есть символы не x или не число, то попросить ввести заново
    private static void checkAnswer(String answer) throws Exception {
        char[] jojo = answer.toCharArray();
        for (int i = 0; i < jojo.length; i++)
            if ((jojo[i] < '0' || jojo[i] > '9') && jojo[i] != 'x')
                throw new Exception();
    }

    private static void getTests() {
        SolutionTest.test1("x1x2x3x4&x2x5x6&x3x4x5&x1x6&x2x3x5&x6");
        SolutionTest.test2("x1x2x3&x1x2x4&x1x3&x2x3&x5x6&x1&x3&x6");
        SolutionTest.test3("x1x3&x2x5&x4x5&x1x3x6&x2x3x5&x1x4x5");
    }

    //Это твои енумы
    enum alert {
        INTRODUCTION("Вводите выражение до следующего xor'a(Пример: x1x2x3) или же нажмите 0 для окончания записи. Для выхода из программы введите - exit"),
        REWRITE("Вы ввели некорректный символ, перепишите, пожалуйста"),
        ;
        private final String name;

        alert(String name) {
            this.name = name;
        }
    }
}
