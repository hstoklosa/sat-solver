import org.junit.Assert;
import org.junit.Test;

public class TestCheckClause {
    private Solver solver = new Solver();

    // True tests
    @Test
    public void test01() {
        int[] clause = { 1, -2, 3 };
        int[] assignment = { 0, 1, -1, 0 };

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertTrue(result);
    }

    @Test
    public void test02() {
        int[] clause = { 1, -2, -3, 4 };
        int[] assignment = { 0, -1, -1, 1, 1 };

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertTrue(result);
    }

    @Test
    public void test03() {
        int[] clause = { 1, -2 };
        int[] assignment = { 0, 1, -1 };

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertTrue(result);
    }

    @Test
    public void test04() {
        int[] clause = { -1, 2, -3, -4 };
        int[] assignment = { 0, 1, 1, -1, 1 };

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertTrue(result);
    }

    @Test
    public void test05() {
        int[] clause = { 1, -1, 2 };
        int[] assignment = { 0, -1, -1 };

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertTrue(result);
    }

    // False tests
    @Test
    public void test06() {
        int[] clause = { 1, 2, -3 };
        int[] assignment = { 0, -1, -1, 1 };

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertFalse(result);
    }

    @Test
    public void test07() {
        int[] clause = { 1, 2 };
        int[] assignment = { 0, -1, -1 };

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertFalse(result);
    }

    @Test
    public void test08() {
        int[] clause = { 1, -2 }; // v1 OR (NOT v2) OR v3
        int[] assignment = { 0, -1, 1 }; // v1 = false, v2 = true, v3 = unknown, v4 = false

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertFalse(result);
    }

    @Test
    public void test09() {
        int[] clause = { -1, -2 };
        int[] assignment = { 0, 1, 1 };

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertFalse(result);
    }

    @Test
    public void test10() {
        int[] clause = { 1, 3, 2, 4, 4}; // v1 OR (NOT v2) OR v3
        int[] assignment = { 0, -1, -1, 0, -1 }; // v1 = false, v2 = true, v3 = unknown, v4 = false

        boolean result = solver.checkClause(assignment, clause);

        Assert.assertFalse(result);
    }
}
