# SAT Solver (NP Problem)

The repository showcases a recursive backtracking-based search algorithm, inspired by [Davis–Putnam–Logemann–Loveland (DPLL)](https://en.wikipedia.org/wiki/DPLL_algorithm) algorithm. The algorithm decides the satisfiability of propositional logic formulae by operating on the DIMACS CNF file format, parsing problem definitions, and attempting to find a variable assignment that satisfies all clauses.

## Solution

My solution solves **14/15** tests for each [dataset](https://github.com/hstoklosa/sat-solver/tree/main/src/resources/datasets) in less than 5 seconds, and it was achieved by applying techniques that have assisted with guiding the search towards finding whether the clause is satisfiable or not.

### Heuristic Variable Selection

The selection technique used for this algorithm calculates a score for a given variable based on its presence within the clause database i.e., how many unsatisfied clauses would be affected by assigning a value to this variable. In short, it works by prioritising variables that (if assigned) could potentially satisfy a higher number of unsatisifed clauses.

### Optimisation Techniques

1. **Pure Literal Elimination:** Runs before the algorithm starts (as preprocessing) and during its execution.

    Pure literal is a variable that appears with the same polarity in all clauses. The elimination process involves assigning a truth value to the pure literals that makes all clauses containing them true.

2. **Unit Propagation:** Only runs during the execution of the algorithm.

    A unit clause contains only 1 unassigned literal (due to previous assignments) and the only way to satisfy that clause is to assign the necessary value to the unassigned literal. It significantly reduces the search space by propagating such assignments throughout the formula.

3. **Remove Tautological Clauses:** Only runs before the algorithm starts (as preprocessing).

    A tautological clause is the one that will always be true, regardless of the assignment of its variables. It occurs when a clause contains both a variable and its negation, and removing tautological clauses simplifies the SAT problem without changing its satisfiability.

## Usage

1. Clone the repository

    ```
    git clone git@github.com:hstoklosa/sat-solver.git
    ```

2. Navigate to the directory

    ```
    cd sat-solver
    ```

3. Compile the source code

    ```
    javac Solver.java
    ```

4. Run the algorithm against the clauses

    ```
    java Solver
    ```

## Improvements

The algorithm can be improved by employing certain strategies, which are very advanced methods of reducing the search space and producing better results. I came across these strategies while researching, but they were out of the scope for my algorithm.

### Suggestions

1. **CDCL:** The solver can be enhanced by incorpating the [Conflict-Driven Clause Learning](https://en.wikipedia.org/wiki/Conflict-driven_clause_learning), which is an advanced technique that learns new clauses from conflicts to prevent the solver from revisiting the same conflict in the future.
2. **Watch List DS:** It can be implemented for unit propagation. This will track all clauses and assignments, which will reduce the overhead of scanning through all clauses.
3. **Dynamic Heuristic Adjustmenets:** Integrate dynamic adjustments to the literal selection heuristic based on the current progress (look at [VSIDS](https://en.wikipedia.org/wiki/Boolean_satisfiability_algorithm_heuristics)) to better guide the search.

## Copyrights

© 2024 H. Stoklosa - hubert.stoklosa23@gmail.com

https://github.com/hstoklosa
