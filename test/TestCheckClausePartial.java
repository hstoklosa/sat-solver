import org.junit.Assert;
import org.junit.Test;

public class TestCheckClausePartial {
    private Solver solver = new Solver();

    // True tests
    @Test
    public void test01() {
        int[] clause = { 1, -2, -3 };
        int[] assignment = { 0, 1, -1, 1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test02() {
        int[] clause = { -1, 2, -3 };
        int[] assignment = { 0, 1, -1, -1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test03() {
        int[] clause = { -1, -2, 3 };
        int[] assignment = { 0, -1, -1, 1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test04() {
        int[] clause = { -1, 2, -3, -4 };
        int[] assignment = { 0, 1, 1, -1, 1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test05() {
        int[] clause = { 1, -1, 2 };
        int[] assignment = { 0, -1, -1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(1, result);
    }

    // False tests
    @Test
    public void test06() {
        int[] clause = { 1, 2, -3 };
        int[] assignment = { 0, -1, -1, 1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test07() {
        int[] clause = { 1, 2 };
        int[] assignment = { 0, -1, -1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test08() {
        int[] clause = { 1, -2 }; // v1 OR (NOT v2) OR v3
        int[] assignment = { 0, -1, 1 }; // v1 = false, v2 = true, v3 = unknown, v4 = false

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test09() {
        int[] clause = { -1, -2 };
        int[] assignment = { 0, 1, 1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test10() {
        int[] clause = { 1, 3, 2, 4, 4 };
        int[] assignment = { 0, -1, -1, 0, -1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(0, result);
    }

    // Unresolved tests
    @Test
    public void test11() {
        int[] clause = { 1, 2, -3 };
        int[] assignment = { 0, -1, -1, 1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test12() {
        int[] clause = { 1, 2 };
        int[] assignment = { 0, -1, -1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test13() {
        int[] clause = { 1, -2 };
        int[] assignment = { 0, -1, 1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(-1, result);
    }

    @Test
    public void test14() {
        int[] clause = { -1, -2 };
        int[] assignment = { 0, 1, -1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test15() {
        int[] clause = { 1, 3, 2, -4, -4};
        int[] assignment = { 0, -1, -1, 0, 1 };

        int result = solver.checkClausePartial(assignment, clause);

        Assert.assertEquals(0, result);
    }

}
