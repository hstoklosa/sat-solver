import org.junit.Test;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestCheckSet {
    // 210007889
    // 210007889
    @Test
    public void test01() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-01.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-02.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-03.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test04() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-04.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test05() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-05.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test06() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-06.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test07() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-07.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test08() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-08.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test09() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-09.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test10() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-10.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test11() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-11.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test12() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-12.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test13() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-13.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test14() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-14.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test15() {
        Solver solver = new Solver();

        try {
            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-15.cnf"));
            solver.runSatSolver(r);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
//    public void testAsif() {
//        Solver solver = new Solver();
//
//        try {
//            BufferedReader r = Files.newBufferedReader(Paths.get("data/210007889-clause-database-14.cnf"));
//            solver.runSatSolver(r);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
//    }
}
