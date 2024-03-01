import org.junit.Assert;
import org.junit.Test;

public class TestFindUnit {
    private Solver solver = new Solver();

    // True tests
    @Test
    public void test01() {
        int[] clause = { 1, 2, 3, -4 };
        int[] assignment = { 0, -1, -1, 0, 1 };

        int expected = 3;
        int result = solver.findUnit(assignment, clause);

        Assert.assertEquals(expected, result);
    }

//    @Test
//    public void test02() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
//
//    @Test
//    public void test03() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
//
//    @Test
//    public void test04() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
//
//    @Test
//    public void test05() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
//
//    // False tests
//    @Test
//    public void test06() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
//
//    @Test
//    public void test07() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
//
//    @Test
//    public void test08() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
//
//    @Test
//    public void test09() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
//
//    @Test
//    public void test10() {
//        int[] clause = { 1, -2, -3 };
//        int[] assignment = { 0, 1, -1, 1 };
//
//        int expected = ;
//        int result = solver.findUnit(assignment, clause);
//
//        Assert.assertEquals(expected, result);
//    }
}
