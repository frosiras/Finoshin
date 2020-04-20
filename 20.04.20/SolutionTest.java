public class SolutionTest {
    public static void test1(String expression) {
        System.out.println(testAlerts.TEST1.name);
        Solution t1 = new Solution(expression);
        System.out.println(testAlerts.TEST1.name + (t1.getRatingOfVector() == 1));
    }

    public static void test2(String expression) {
        System.out.println(testAlerts.TEST2.name);
        Solution t2 = new Solution(expression);
        System.out.println(testAlerts.TEST2.name + (t2.getRatingOfVector() == 1));
    }

    public static void test3(String expression) {
        System.out.println(testAlerts.TEST3.name);
        Solution t3 = new Solution(expression);
        System.out.println(testAlerts.TEST3.name + (t3.getRatingOfVector() == 2));
    }
    public static void test4(String expression) {
        System.out.println(testAlerts.TEST4.name);
        Solution t4 = new Solution(expression);
        System.out.println(testAlerts.TEST4.name + (t4.getRatingOfVector() == 16));
    }
    public static void test5(String expression) {
        System.out.println(testAlerts.TEST5.name);
        Solution t = new Solution(expression);
        System.out.println(testAlerts.TEST5.name + (t.getRatingOfVector() == 2));
    }

    public static void test6(String expression) {
        System.out.println(testAlerts.TEST6.name);
        Solution t = new Solution(expression);
        System.out.println(testAlerts.TEST6.name + (t.getRatingOfVector() == 6));
    }
}

enum testAlerts {
    TEST1("Тест 1: "),
    TEST2("Тест 2: "),
    TEST3("Тест 3: "),
    TEST4("Тест 4: "),
    TEST5("Тест 5: "),
    TEST6("Тест 6: "),

    ;
    public final String name;

    testAlerts(String name) {
        this.name = name;
    }
}
