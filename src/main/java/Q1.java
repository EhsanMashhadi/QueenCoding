public class Q1 {

    public static void main(String[] args) {

        Q1 q1 = new Q1();

        char[][] grid = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(q1.solve(grid, "ABCCED"));

        char[][] grid1 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(q1.solve(grid1, "SEE"));

        char[][] grid2 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(q1.solve(grid2, "ABCB"));

        char[][] grid3 = {{'A', 'B'}, {'D', 'C'}};
        System.out.println(q1.solve(grid3, "AC"));

        char[][] grid4 = {{'A', 'B', 'C', 'D'}};
        System.out.println(q1.solve(grid4, "DCBA"));

        char[][] grid5 = {{'X', 'Y', 'E', 'Z', 'F', 'M'}, {'A', 'S', 'H', 'B', 'D', 'I'},
                {'N', 'M', 'A', 'C', 'A', 'K'}, {'G', 'H', 'S', 'H', 'H', 'L'}};
        System.out.println(q1.solve(grid5, "EhSANMaSHHADI"));

        char[][] grid6 = {{'X', 'Y', 'E', 'Z', 'F', 'M'}, {'A', 'S', 'H', 'B', 'D', 'I'},
                {'N', 'M', 'A', 'C', 'A', 'K'}, {'G', 'H', 'S', 'H', 'H', 'L'}};
        System.out.println(q1.solve(grid6, "EHSANMASHHADI"));
    }

    /**
     * Iterating over the board elements and executing the DFS algorithm
     *
     * @param grid the board
     * @param word searching word
     * @author Ehsan Mashhadi
     */
    private boolean solve(char[][] grid, String word) {

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (find(i, j, 0, grid, word))
                    return true;
        return false;
    }

    /**
     * Using DFS algorithm in the recursive mode for finding the word
     *
     * @param i    row index
     * @param j    column index
     * @param k    character index
     * @param grid the board
     * @param word searching word
     * @author Ehsan Mashhadi
     */
    private boolean find(int i, int j, int k, char[][] grid, String word) {

        if (i >= grid.length || j >= grid[0].length || i < 0 || j < 0)
            return false;

        else if (Character.toLowerCase(grid[i][j]) == Character.toLowerCase(word.charAt(k))) {
            char tempCharacter = grid[i][j];
            grid[i][j] = '-';
            if (k == word.length() - 1)
                return true;
            else if (find(i + 1, j, k + 1, grid, word) ||
                    find(i - 1, j, k + 1, grid, word) ||
                    find(i, j + 1, k + 1, grid, word) ||
                    find(i, j - 1, k + 1, grid, word))
                return true;

            grid[i][j] = tempCharacter;
        }
        return false;
    }
}
