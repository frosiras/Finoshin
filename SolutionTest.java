public class SolutionTest {
    public static void test1(String expression){
        Solution t1 = new Solution(expression);
        System.out.println(testAlerts.TEST1.name + t1.getExpression().equals("x1x2x3&x2x4x5&x1x5&x1x2"));
    }
    enum testAlerts{
            TEST1("Тест 1: ")
        ;
        private final String name;
        testAlerts(String name) {
            this.name = name;
        }
    }
}
