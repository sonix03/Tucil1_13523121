package src;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class rotation {
    static List<int[][]> puzzChange(int[][] p) {
        Set<String> visit = new HashSet<>();
        List<int[][]> changes = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            p = rotating(p);
            String hashArr = Arrays.deepToString(p);
            if (visit.add(hashArr)) changes.add(p);
            int[][] mirror = mirroring(p);
            if (visit.add(Arrays.deepToString(mirror))) changes.add(mirror);
        }
        return changes;
    }
    
    static int[][] rotating(int[][] p) {
        int m = p.length; 
        int n = p[0].length;
        int[][] rotated = new int[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                rotated[j][m - 1 - i] = p[i][j];
        return rotated;
    }

    static int[][] mirroring(int[][] p) {
        int m = p.length; 
        int n = p[0].length;
        int[][] mirror = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                mirror[i][n - 1 - j] = p[i][j];
        return mirror;
    }
}
