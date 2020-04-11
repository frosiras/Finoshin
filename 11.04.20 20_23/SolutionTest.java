public class SolutionTest {
    public static void test1(String expression){
        System.out.println(testAlerts.TEST1.name);
        Solution t1 = new Solution(expression);
        System.out.println(testAlerts.TEST1.name + t1.getExpression().equals("x1&x2&x1x2x3&x2x4x5&x1x5&x1x2"));
    }
    public static void test2(String expression){
        System.out.println(testAlerts.TEST2.name);
        Solution t2 = new Solution(expression);
        System.out.println(testAlerts.TEST2.name + t2.getExpression().equals("x1x2x3&x1x2x4&x1x3&x2x3&x5x6&x1&x3&x6"));
    }
    enum testAlerts{
            TEST1("Тест 1: "),
            TEST2("Тест 2: "),
        ;
        private final String name;
        testAlerts(String name) {
            this.name = name;
        }
    }
}
