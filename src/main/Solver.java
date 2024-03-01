import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Solver {
    private int [][] clauseDatabase = null;
    private int numberOfVariables = 0;

    // Worst case complexity : O(v)
    // Best case complexity : O(1)
    public boolean checkClause(int[] assignment, int[] clause) {
        for (int l = 0; l < clause.length; l++) {
            int literal = clause[l];
            int value = assignment[Math.abs(literal)];

            if ((literal > 0 && value == 1) || (literal < 0 && -(value) == 1))
                return true;
        }

        return false;
    }

    // Worst case complexity : O(c * l)
    // Best case complexity : O(c)
    public boolean checkClauseDatabase(int[] assignment, int[][] clauseDatabase) {
        for (int c = 0; c < clauseDatabase.length; c++) {
            boolean satClause = checkClause(assignment, clauseDatabase[c]);

            if (!satClause) return false;
        }

        return true;
    }

    // Worst case complexity : O(v)
    // Best case complexity : O(1)
    public int checkClausePartial(int[] partialAssignment, int[] clause) {
        int unknownCounter = 0;

        for (int v = 0; v < clause.length; v++) {
            int literal = clause[v];
            int value = partialAssignment[Math.abs(literal)];

            if ((literal > 0 && value == 1) || (literal < 0 && -(value) == 1))
                return 1;

            if (value == 0)
                unknownCounter++;
        }

        // everything so far is false, so we check if there is an unknown
        return unknownCounter > 0 ? 0 : -1;
    }

    // Worst case complexity : O(v)
    // Best case complexity : O(1)
    public int findUnit(int[] partialAssignment, int[] clause) {
        int unknownCounter = 0;
        int unknownLiteral = 0;

        for (int v = 0; v < clause.length; v++) {
            int literal = clause[v];
            int value = partialAssignment[Math.abs(literal)];

            if ((literal > 0 && value == 1) || (literal < 0 && -(value) == 1))
                return 0;

            if (value == 0) {
                unknownCounter++;
                unknownLiteral = literal;
            }
        }

        return unknownCounter == 1 ? unknownLiteral : 0;
    }



    // Heuristic method for picking literals based on their score (avoiding numerical ordering)
    private int computeLiteralScore(int variable, int[][] clauseDatabase, int[] state) {
        int score = 0;

        for (int i = 0; i < clauseDatabase.length; i++) {
            // only check unassigned clauses
            if (state[i] == -1) {
                boolean containsVariable = false;
                int[] clause = clauseDatabase[i];

                for (int j = 0; j < clause.length; j++) {

                    // check whether it contains the target variable
                    if (Math.abs(clause[j]) == variable) {
                        containsVariable = true;
                        break;
                    }
                }

                // increment score (unsatisfied clause and it contains the variable)
                if (containsVariable)
                    score++;
            }
        }

        return score;
    }

    private int selectLiteral(int[] assignment, int[][] clauseDatabase, int[] state) {
        int bestVarIndex = -1;
        double bestScore = -1;

        for (int i = 1; i < assignment.length; i++) {
            // only consider unassigned values (prevents stack overflow)
            if (assignment[i] == 0) {
                int score = computeLiteralScore(i, clauseDatabase, state);

                // find the literal with the higest score (appears a lot in unsatisfied clauses)
                if (score > bestScore) {
                    bestVarIndex = i;
                    bestScore = score;
                }
            }
        }

        return bestVarIndex;
    }


    // SAT optimisation techniques to reduce the search space
    public void unitPropagation(int[][] clauseDatabase, int[] assignment, int[] state, HashMap<Integer, ArrayList<Integer>> literalClauses) {
        for (int i = 0; i < clauseDatabase.length; i++) {
            if (state[i] == 1 || state[i] == 0)
                continue; // clause has already been assigned/removed (next iteration)

            // Handle unit clause if it exists
            int unknownLiteral = findUnit(assignment, clauseDatabase[i]);

            if (unknownLiteral == 0)
                continue;

            assignment[Math.abs(unknownLiteral)] = unknownLiteral > 0 ? 1 : -1;

            // assign "removed" state to the clauses that contain the unknown literal
            ArrayList<Integer> clausesPositive = literalClauses.getOrDefault(unknownLiteral, new ArrayList<>());
            for (int j = 0; j < clausesPositive.size(); j++) {
                state[clausesPositive.get(j)] = 0;
            }

            // remove the negations of the unknown literal from all the clauses that contain it
            ArrayList<Integer> clausesNegated = literalClauses.getOrDefault(-unknownLiteral, new ArrayList<>());
            for (int j = 0; j < clausesNegated.size(); j++) {
                clauseDatabase[clausesNegated.get(j)] = Arrays.stream(clauseDatabase[clausesNegated.get(j)]).filter(d -> d != -(unknownLiteral)).toArray();
            }
        }
    }


    void pureLiteralElimination(int[][] clauseDatabase, int[] assignment, int[] state, HashMap<Integer, ArrayList<Integer>> literalClauses) {
        Set<Integer> pure_literals = new HashSet<>();
        Set<Integer> literals = new HashSet<>();

        for (int i = 0; i < clauseDatabase.length; i++) {
            if (state[i] == 0)
                continue;

            int[] clause = clauseDatabase[i];

            for (int literal : clause) {
                literals.add(literal);

                if (literals.contains(-literal)) {
                    pure_literals.remove(literal);
                    pure_literals.remove(-literal);
                    continue;
                }

                pure_literals.add(literal);
            }
        }

        // Handle pure literals if any exist
        if (pure_literals.size() == 0)
            return;

        for (int pure_literal : pure_literals) {
            assignment[Math.abs(pure_literal)] = pure_literal > 0 ? 1 : -1; // assign appropriate value to the pure literal

            // assign "removed" state to the clauses that contain the pure literal
            ArrayList<Integer> pureClause = literalClauses.getOrDefault(pure_literal, new ArrayList<>());
            for (int i = 0; i < pureClause.size(); i++) {
                state[pureClause.get(i)] = 0;
            }
        }
    }


    int[] removeTautologicalClauses(int[][] clauseDatabase, int[] state) {
        for (int i = 0; i < clauseDatabase.length; i++) {
            int[] clause = clauseDatabase[i];
            Set<Integer> clauseSet = new HashSet<>();

            for (int literal : clause) {
                // check whether the clause contains both positive and negative literal of the same variable
                if (clauseSet.contains(-literal)) {
                    state[i] = 0; // no longer needs to be checked (removed tautological clause)
                    break;
                }

                clauseSet.add(literal);
            }
        }

        return state;
    }

    // Helper methods for keeping track of all the clauses
    int[] updateState(int[][] clauseDatabase, int[] assignment, int[] state) {
        // update the states of the clauses based on the new assignment
        for (int i = 0; i < state.length; i++) {
            if (state[i] == 0)
                continue; // "removed" state so no need to assign, so no need to change

            // assign true if clause is satisfied with the current assignment
            if (checkClause(assignment, clauseDatabase[i])) {
                state[i] = 1;
                continue;
            }

            state[i] = -1; // false if not satisfied
        }

        return state;
    }


    boolean checkAssignment(int[] state) {
        for (int clauseState : state) {
            if (clauseState == -1)
                return false;
        }
        return true;
    }


    // SAT Solving Algorithm inspired by DPLL
    // See: https://en.wikipedia.org/wiki/DPLL_algorithm

    int[] checkSat(int[][] clauseDatabase) {
        int[] assignment = new int[numberOfVariables + 1];
        int[] state = new int[clauseDatabase.length]; // 1 = satisfied -1 = not satisfied 0 = treated as removed
        HashMap<Integer, ArrayList<Integer>> literalClauses = new HashMap();

        Arrays.fill(state, -1);

        // Prepare the HashMap: Literal -> list of clauses it belongs to
        for (int i = 0; i < clauseDatabase.length; i++) {
            if (state[i] == 0)
                continue;

            for (int j = 0; j < clauseDatabase[i].length; j++) {
                literalClauses.putIfAbsent(clauseDatabase[i][j], new ArrayList<>());
                literalClauses.get(clauseDatabase[i][j]).add(i);
            }
        }

        // some preprocessing
        pureLiteralElimination(clauseDatabase, assignment, state, literalClauses);
        state = removeTautologicalClauses(clauseDatabase, state);


        return checkSatHelper(clauseDatabase, assignment, state, literalClauses);
    }


    int[] checkSatHelper(int[][] clauseDatabase, int[] assignment, int[] state, HashMap<Integer, ArrayList<Integer>> literalClauses) {
        updateState(clauseDatabase, assignment, state);
        unitPropagation(clauseDatabase, assignment, state, literalClauses);
        pureLiteralElimination(clauseDatabase, assignment, state, literalClauses);

        // choose the next unassigned literal
        int nextLiteral = selectLiteral(assignment, clauseDatabase, state);

        if (nextLiteral == -1) {
            if (checkAssignment(state)) {
                return assignment;
            }

            return null;
        }

        // First decision
        assignment[nextLiteral] = 1;
        int[] result = checkSatHelper(clauseDatabase.clone(), assignment.clone(), state.clone(), literalClauses);

        if (result != null)
            return result;

        // Forced decision (previous has not worked)
        assignment[nextLiteral] = -1;
        result = checkSatHelper(clauseDatabase.clone(), assignment.clone(), state.clone(), literalClauses);

        if (result != null) {
            return result;
        }

        // Undo the assignment before returning
        assignment[nextLiteral] = 0;
        return null;
    }




    /*****************************************************************\
     *** DO NOT CHANGE! DO NOT CHANGE! DO NOT CHANGE! DO NOT CHANGE! ***
     *******************************************************************
     *********** Do not change anything below this comment! ************
     \*****************************************************************/

    public static void main(String[] args) {
        try {
            Solver mySolver = new Solver();

            System.out.println("Enter the file to check");

            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String fileName = br.readLine();

            int returnValue = 0;

            Path file = Paths.get(fileName);
            BufferedReader reader = Files.newBufferedReader(file);
            returnValue = mySolver.runSatSolver(reader);

            return;

        } catch (Exception e) {
            System.err.println("Solver failed :-(");
            e.printStackTrace(System.err);
            return;

        }
    }

    public int runSatSolver(BufferedReader reader) throws Exception, IOException {

        // First load the problem in, this will initialise the clause
        // database and the number of variables.
        loadDimacs(reader);

        // Then we run the part B algorithm
        int [] assignment = checkSat(clauseDatabase);

        // Depending on the output do different checks
        if (assignment == null) {
            // No assignment to check, will have to trust the result
            // is correct...
            System.out.println("s UNSATISFIABLE");
            return 20;

        } else {
            // Cross check using the part A algorithm
            boolean checkResult = checkClauseDatabase(assignment, clauseDatabase);

            if (checkResult == false) {
                throw new Exception("The assignment returned by checkSat is not satisfiable according to checkClauseDatabase?");
            }

            System.out.println("s SATISFIABLE");

            // Check that it is a well structured assignment
            if (assignment.length != numberOfVariables + 1) {
                throw new Exception("Assignment should have one element per variable.");
            }
            if (assignment[0] != 0) {
                throw new Exception("The first element of an assignment must be zero.");
            }
            for (int i = 1; i <= numberOfVariables; ++i) {
                if (assignment[i] == 1 || assignment[i] == -1) {
                    System.out.println("v " + (i * assignment[i]));
                } else {
                    throw new Exception("assignment[" + i + "] should be 1 or -1, is " + assignment[i]);
                }
            }

            return 10;
        }
    }

    // This is a simple parser for DIMACS file format
    void loadDimacs(BufferedReader reader) throws Exception, IOException {
        int numberOfClauses = 0;

        // Find the header line
        do {
            String line = reader.readLine();

            if (line == null) {
                throw new Exception("Found end of file before a header?");
            } else if (line.startsWith("c")) {
                // Comment line, ignore
                continue;
            } else if (line.startsWith("p cnf ")) {
                // Found the header
                String counters = line.substring(6);
                int split = counters.indexOf(" ");
                numberOfVariables = Integer.parseInt(counters.substring(0,split));
                numberOfClauses = Integer.parseInt(counters.substring(split + 1));

                if (numberOfVariables <= 0) {
                    throw new Exception("Variables should be positive?");
                }
                if (numberOfClauses < 0) {
                    throw new Exception("A negative number of clauses?");
                }
                break;
            } else {
                throw new Exception("Unexpected line?");
            }
        } while (true);

        // Set up the clauseDatabase
        clauseDatabase = new int[numberOfClauses][];

        // Parse the clauses
        for (int i = 0; i < numberOfClauses; ++i) {
            String line = reader.readLine();

            if (line == null) {
                throw new Exception("Unexpected end of file before clauses have been parsed");
            } else if (line.startsWith("c")) {
                // Comment; skip
                --i;
                continue;
            } else {
                // Try to parse as a clause
                ArrayList<Integer> tmp = new ArrayList<Integer>();
                String working = line;

                do {
                    int split = working.indexOf(" ");

                    if (split == -1) {
                        // No space found so working should just be
                        // the final "0"
                        if (!working.equals("0")) {
                            throw new Exception("Unexpected end of clause string : \"" + working + "\"");
                        } else {
                            // Clause is correct and complete
                            break;
                        }
                    } else {
                        int var = Integer.parseInt(working.substring(0,split));

                        if (var == 0) {
                            throw new Exception("Unexpected 0 in the middle of a clause");
                        } else {
                            tmp.add(var);
                        }

                        working = working.substring(split + 1);
                    }
                } while (true);

                // Add to the clause database
                clauseDatabase[i] = new int[tmp.size()];
                for (int j = 0; j < tmp.size(); ++j) {
                    clauseDatabase[i][j] = tmp.get(j);
                }
            }
        }

        // All clauses loaded successfully!
        return;
    }

}