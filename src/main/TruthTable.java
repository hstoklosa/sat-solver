public class TruthTable {

    public static void generateValues(int n) {
        int rows = (int)Math.pow(2, n);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((i / (int)Math.pow(2, j)) % 2 == 0 ? "F " : "T ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        generateValues(3);
    }
}
