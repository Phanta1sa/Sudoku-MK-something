import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    static void writeToFile(String fileName, int[][] board) {
        try {
            FileWriter fw = new FileWriter(fileName);
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < Board.SIZEOFBOARD; j++) {
                    if (j % Board.SIZEOFSQUARE == 0 && j != 0) fw.write("| ");
                    fw.write(board[i][j] + " ");
                }
                fw.write("\n");
                if ((i+1)%Board.SIZEOFSQUARE ==0 ) {
                    for (int j = 0; j < Board.SIZEOFBOARD-2; j++) {
                        fw.write("---");
                    }
                    fw.write("-");
                    fw.write("\n");
                }

            }
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static int[][] readFromFile(String fileName) { // not mine but I can explain it
        int[][] board = new int[Board.SIZEOFBOARD][Board.SIZEOFBOARD];
        try (Scanner scanner = new Scanner(new FileReader(fileName))) {
            int row = 0, col = 0;
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int num = scanner.nextInt();
                    board[row][col++] = num;
                    if (col == Board.SIZEOFBOARD) {
                        col = 0;
                        row++;
                        if (row == Board.SIZEOFBOARD) break;
                    }
                } else {
                    scanner.next();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return board;
    }
}

