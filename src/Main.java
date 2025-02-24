package src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scannerA = new Scanner(System.in);
        
        System.out.print("Masukkan nama file: ");
        String dir = "../test/input/";
        String file = scannerA.nextLine();
        
        Scanner scannerB = new Scanner(new File(dir + file));

        bruteForce.M = scannerB.nextInt();
        bruteForce.N = scannerB.nextInt();
        int P = scannerB.nextInt();

        scannerB.nextLine(); 

        inputPuzzle.readInput(dir + file);

        
        bruteForce.initBoard(); // DEFAULT

        long start = System.currentTimeMillis();

        if (P > inputPuzzle.puzzles.size()) {
            System.out.println("Nilai P lebih besar dari jumlah puzzle yang tersedia!");
            System.out.println("Jumlah puzzle yang tersedia: " + inputPuzzle.puzzles.size());
            scannerA.close();
            scannerB.close();
            return;
        }

        if (bruteForce.bruteForceSolve(0)) {
            long end = System.currentTimeMillis();
            outputPuzzle.printBoardColor(bruteForce.board);
            System.err.println();

            System.out.println("Waktu Pencarian: " + (end - start) + " ms");
            System.out.println();
            System.out.println("Banyak kasus yang ditinjau: " + bruteForce.count);
            System.out.println();

            System.out.println("Apakah anda ingin menyimpan solusi? (ya/tidak)");
            String choice = scannerA.next();
            scannerA.nextLine();

            if (choice.equals("ya")) {
                System.out.print("Masukkan nama file (tanpa format txt atau png): ");
                String name = scannerA.nextLine();

                outputPuzzle.saveBoardText(bruteForce.board, name + ".txt");
                outputPuzzle.saveBoardImage(bruteForce.board, name + ".png");
                System.out.println("File berhasil disimpan");
            } 
            else {
                System.out.println("File tidak tersimpan");
            }

        } 
        else {
            System.out.println("Tidak ada solusi");
        }

        scannerA.close();
        scannerB.close();
    }
}
