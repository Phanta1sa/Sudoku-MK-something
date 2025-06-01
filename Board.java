import java.util.Scanner;

import static java.lang.Math.sqrt;

public class Board {
    static  int SIZEOFBOARD =9; //TODO turn it into a instance class that way changing the 9 will change everything else
    static  int SIZEOFSQUARE = (int) sqrt(SIZEOFBOARD);
    static int RULE = (SIZEOFBOARD*(SIZEOFBOARD+1))/2;

    public static void printBoard(int[][] board) {
        for (int i = 0; i < SIZEOFBOARD; i++) {
            for (int j = 0; j < SIZEOFBOARD; j++) {
                System.out.print(board[i][j] + " ");

            }
            System.out.println();
        }
    }
    public static int[][] readingBoard(Scanner input) {
        int[][] board= new int[SIZEOFBOARD][SIZEOFBOARD];
        for (int i = 0; i < SIZEOFBOARD; i++) {
            for (int j = 0; j < SIZEOFBOARD; j++) {
                board[i][j] =input.nextInt();
            }
        }
        return board;
    }
    public static int[][] copy(int[][] board){
        int[][] boardCopy = new int[SIZEOFBOARD][SIZEOFBOARD];
        for(int i = 0; i< SIZEOFBOARD; i++){
            System.arraycopy(board[i], 0, boardCopy[i], 0, SIZEOFBOARD);
        }
        return boardCopy;
    }
    public static void rules(){
        System.out.println("Sudoku Rules:");
        System.out.println("- An nxn grid must be filled with numbers from 1 to n.");
        System.out.println("- No number may be repeated in any row (horizontal line).");
        System.out.println("- No number may be repeated in any column (vertical line).");
        System.out.println("- The grid is divided into 3×3 boxes.");
        System.out.println("- Each sqrt(n)×sqrt(n) box must also contain all numbers from 1 to n with no repeats.");
    }
}
