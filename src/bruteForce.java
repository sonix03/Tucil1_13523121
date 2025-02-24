package src;
import java.util.*;

public class bruteForce {
    static int M, N;
    static char[][] board;
    static int count = 0;
    static List<int[][]> puzzles;
    static char[] labels;

    static boolean bruteForceSolve(int n) {
        if (n == puzzles.size()) {
            return true;
        }
        int[][] puzzle = puzzles.get(n);
        char label = labels[n];
        for (int row = 0; row <= M - puzzle.length; row++) {
            for (int col = 0; col <= N - puzzle[0].length; col++) {
                for (int[][] change : rotation.puzzChange(puzzle)) {
                    count++;
                    if (validPuzz(change, row, col)) {
                        putPuzz(change, row, col, label);
                        if (bruteForceSolve(n + 1)) { return true;
                        }
                        putPuzz(change, row, col, '.');
                    }
                }
            }
        }
        return false;
    }
    
    static boolean validPuzz(int[][] p, int x, int y) {
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                if (p[i][j] == 1) {
                    int nx = x + i, ny = y + j;
                    if (nx >= M || ny >= N || board[nx][ny] != '.') {
                        return false;
                    }     
                }
            }
        }
        return true;
    }

    static void putPuzz(int[][] p, int x, int y, char val) {
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p[i].length; j++) {
                if (p[i][j] == 1) {
                    board[x + i][y + j] = val;
                }
            }
        }
    }

    static void initBoard() {
        board = new char[M][N];
        for (char[] m : board) {
            Arrays.fill(m, '.');
        }

        // Sorting puzzles 
        puzzles = new ArrayList<>(inputPuzzle.puzzles);
        puzzles.sort((a, b) -> Integer.compare(cnt1(b), cnt1(a)));
        
        // Sorting labels
        List<Character> labelLst = new ArrayList<>();
        Map<int[][], Character> puzzleLabel = new HashMap<>();
        
        for (int i = 0; i < inputPuzzle.puzzles.size(); i++) {
            puzzleLabel.put(inputPuzzle.puzzles.get(i), inputPuzzle.labels[i]);
        }

        puzzles.forEach(p 
        -> 
        labelLst.
        add(puzzleLabel.get(p)));
        
        labels = new char[labelLst.size()];

        for (int i = 0; i < labelLst.size(); i++) {
            labels[i] = labelLst.get(i);
        }

    }

    static int cnt1(int[][] p) {
        int count = 0;
        for (int[] m : p) {
            for (int cnt : m) {
                if (cnt == 1) {
                    count++;
                }
                    
            }
        }
        return count;
    }
}
