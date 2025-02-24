package src;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class outputPuzzle {
    private static final String[] ANSI = {
        "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", 
        "\u001B[35m", "\u001B[36m", "\u001B[37m", "\u001B[91m", 
        "\u001B[95m", "\u001B[90m"
    };
    private static final String RESET_ANSI = "\u001B[0m";

    private static final Color[] COLORS = {
        Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, 
        Color.MAGENTA, Color.CYAN, Color.WHITE, Color.ORANGE, 
        Color.PINK, Color.GRAY
    };

    private static String getAnsiColor(char c) {
        if (c == '.') return "\u001B[30m";
        int i = (c - 'A') % ANSI.length;
        return ANSI[i];
    }

    private static Color getColor(char c) {
        if (c == '.') return Color.BLACK;
        int index = (c - 'A') % COLORS.length;
        return COLORS[index];
    }

    public static void printBoardColor(char[][] c) {
        if (c == null || c.length == 0) {
            System.out.println("Board kosong");
            return;
        }
    
        for (char[] row : c) {
            for (char n : row) {
                System.out.print(getAnsiColor(n) + n + " " + RESET_ANSI);
            }
            System.out.println();
        }
    }
    

    public static void saveBoardImage(char[][] m, String name) {
        int size = 50;
        int width = m[0].length * size;
        int height = m.length * size;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                g.setColor(getColor(m[i][j]));
                g.fillRect(j * size, i * size, size, size);
                
                g.setColor(Color.BLACK);
                g.drawRect(j * size, i * size, size, size);
            }
        }
        g.dispose();

        File dir = new File("../test/output");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File file = new File(dir, name);
        try {
            ImageIO.write(image, "png", file);
            System.out.println("Output berhasil tersimpan di " + file.getAbsolutePath());
        } 
        catch (Exception e) {
            System.out.println("Gagal menyimpan gambar " + e.getMessage());
        }
    }

    public static void saveBoardText(char[][] m, String name) {
        File dir = new File("../test/output");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        
        File outputFile = new File(dir, name);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (char[] row : m) {
                writer.write(new String(row));
                writer.newLine();
            }
            System.out.println("Output berhasil tersimpan di " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Gagal menyimpan output: " + e.getMessage());
        }
    }
}
