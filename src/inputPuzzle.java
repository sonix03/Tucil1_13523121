package src;
import java.io.*;
import java.util.*;

public class inputPuzzle {
    static List<int[][]> puzzles = new ArrayList<>();
    public static char[] labels;
    private static Set<Character> labelSet = new LinkedHashSet<>();
    public static String d = "";

    public static void readInput(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));

        if (!scanner.hasNextInt()) {
            System.out.println("Masukan Tidak Valid");
            scanner.close();
            return;
        }

        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int P = scanner.nextInt();
        scanner.nextLine();

        if (scanner.hasNextLine()) {
            d = scanner.nextLine().trim();
        }

        List<String> lines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.trim().isEmpty()) {
                lines.add(line);
            }
        }
        scanner.close();
        labelSet.clear();
        for (String a : lines) {
            for (char chr : a.toCharArray()) {
                if (chr != ' ') { 
                    labelSet.add(chr);
                }
            }
        }

        labels = new char[labelSet.size()];
        int i = 0;
        for (char chr : labelSet) {
            labels[i++] = chr;
        }

        for (char chr : labels) {
            processPuzzle(lines, chr);
        }
    }
    
    private static void processPuzzle(List<String> lines, char label) {
        int m = lines.size();
        int n = lines.
        stream().mapToInt(String::length)
        .max().orElse(0);
    
        char[][] allGrid = new char[m][n];
        for (int r = 0; r < m; r++) {
            String line = lines.get(r);
            for (int c = 0; c < n; c++) {
                if (c < line.length()) {
                    allGrid[r][c] = line.charAt(c);
                }
                else {
                    allGrid[r][c] = ' ';
                }
            }
        }
    
        boolean[] validRow = new boolean[m];
        boolean[] validCol = new boolean[n];
    
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (allGrid[r][c] == label) {
                    validRow[r] = true;
                    validCol[c] = true;
                }
            }
        }
    
        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        while (top <= bottom && !validRow[top]) {
            top++;
        } 
        while (bottom >= top && !validRow[bottom]) {
            bottom--;
        }
        while (left <= right && !validCol[left]) {
            left++;
        } 
        while (right >= left && !validCol[right]) {
            right--;
        } 
    
        int nRow = bottom - top + 1;
        int nCol = right - left + 1;
        int[][] trim = new int[nRow][nCol];
    
        for (int r = 0; r < nRow; r++) {
            for (int c = 0; c < nCol; c++) {
                if (allGrid[top + r][left + c] == label) {
                    trim[r][c] = 1;
                }
                else {
                    trim[r][c] = 0;
                }
            }
        }
    
        puzzles.add(trim);
    }
}