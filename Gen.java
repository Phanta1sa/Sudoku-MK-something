import java.util.Arrays;
import java.util.Random;

public class Gen {
    static Solver solver = new Solver();
    public static int randGen(long seed) {
        Random generator = new Random(seed);
        return generator.nextInt();
    }

    public static int[][] generateBoard(int seed) {
        System.out.println("Generating board...");
        int counter = 0;
        int[][] board = new int[Board.SIZEOFBOARD][Board.SIZEOFBOARD];
        for (int i = 0; i <= 1000 && counter < (Board.SIZEOFBOARD==9?16:Board.SIZEOFBOARD==4?3:120); i++) {// a template bigger than 16 will lead to cases where the board generated will be unsolvable. confirmed by the smallest known puzzle 17(9X9) and by trial and error
            int row = Math.floorMod(randGen(seed), Board.SIZEOFBOARD);
            int col = Math.floorMod(randGen(seed + 1), Board.SIZEOFBOARD);
            int number = 1 + Math.floorMod(randGen(seed + 2), Board.SIZEOFBOARD);
            if (board[row][col] == 0 && SolverBrute.isPlaceValid(board, row, col, number)) {
                board[row][col] = number;
                counter++;
            }
            seed++;
        }
        int[][] solved = Board.copy(board);
        if(!solver.solveBoard(solved)) System.err.println("Solver failed please try again later");
        else System.out.println("check finished");
        //Board.printBoard(solved);
        return solved;
    }

    public static void deleteCell(int[][] board, int seed, int clues) {
        int count = 0;
        int attempts =0;

        while (count < ((Board.SIZEOFBOARD * Board.SIZEOFBOARD) - clues)) {
            int row = Math.floorMod(randGen(seed), Board.SIZEOFBOARD);
            int col = Math.floorMod(randGen(seed + count), Board.SIZEOFBOARD);
            if (board[row][col] != 0 && uniq(board, row, col, board[row][col])) {
                board[row][col] = 0;
                count++;
                attempts++;
            } else {
                attempts++;
                seed++;
            }
            if (attempts > 10000000) {
                System.out.println("couldn't delete cell enough, the clue count might be bigger to assure uniqueness ");
                break;
            }
        }
    }

    /*public static boolean uniq(int[][] board, int row, int col, int number) { //still has some bugs
        Solver solver = new Solver();
        int[][] boardCopy = Board.copy(board);
        for (int num = 1; num <= Board.SIZEOFBOARD; num++) {
            if (num == number) continue;
            if (Main.solve.isPlaceValid(boardCopy, row, col, num)) {
                boardCopy[row][col] = num;
                if (solver.solve(boardCopy)) {
                    return false;
                }
                boardCopy[row][col] = 0; // reset for next try
            }
        }
        boardCopy[row][col] = 0;
        return true;
    }*/
    public static boolean uniq(int[][] board, int row, int col, int number) {
        for (int num = 1; num <= Board.SIZEOFBOARD; num++) {
            if (num == number) continue;
            if (SolverBrute.isPlaceValid(board, row, col, num)) {
                int[][] tryCopy = Board.copy(board);
                tryCopy[row][col] = num;
                Solver solver = new Solver(); // fresh solver each time
                if (solver.solve(tryCopy)) return false;
            }
        }
        return true;
    }
}