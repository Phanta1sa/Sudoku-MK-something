import java.util.Arrays;
import java.util.Scanner;

public class GameLoop {
    public static void play(int[][] board, int[][] boardSolved, Scanner input){
            System.out.println("what would you like to place ? write place then follow it in the next line by the row,col and the number \nyou can write --help or --h to get the game rules \ntype hint to get a small hint ");
            switch (Main.input.nextLine()) {
                case "--h", "--help":
                    Board.rules();
                    break;
                case "hint":
                    hints(board);
                    break;
                case "place":
                    int row = input.nextInt() - 1;
                    int col = input.nextInt() - 1;
                    int number = input.nextInt();
                    input.nextLine(); //error fix

                    if ((col >= 0 && col < Board.SIZEOFBOARD) && (row >= 0 && row < Board.SIZEOFBOARD) && (number > 0 && number <= Board.SIZEOFBOARD)) {
                        if (board[row][col] != 0) {
                            System.out.println("can't place here");
                        } else if (isPlayerRight(row, col, number,boardSolved)) {
                            board[row][col] = number;
                            Board.printBoard(board);
                            if (condForVictory(boardSolved, board)) {
                                System.out.println("it is solved!!!!\n would you like to print it in a file ?");
                                if (input.nextLine().equals("yes")) {
                                    System.out.println("what would you like to name it ?");
                                    String name = input.nextLine();
                                    FileManager.writeToFile(name, board);
                                }
                                return;
                            }

                        } else {
                            System.out.println("that is wrong unfortunately");
                        }
                    } else {
                        System.out.println("invalid input");
                    }

                    System.out.println("you can write exit to exit");
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("invalid input");
            }
    }
    private static void hints(int[][] board){
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board.length; col++){
                for (int number = 1; number <=Board.SIZEOFBOARD; number++){
                    if (board[row][col] != 0) continue;
                    if(SolverBrute.isPlaceValid(board, row, col, number)) {
                        System.out.println("Here is your hint: " + number + " at row " + (row+1) + ", column " + (col+1)); // java concatenation is a nightmare
                        Board.printBoard(board);
                        return;
                    }
                }
            }
        }
        System.out.println("no hints");
    }
    private static boolean isPlayerRight(int row, int col, int number , int [][]boardSolved){
        return boardSolved[row][col] == number;
    }
    public static boolean condForVictory(int[][] boardSolved,int[][] board){
        return Arrays.deepEquals(boardSolved, board);
    }
    public static void startPlaying(){
        System.out.println("what game mode would you like ?\n we got zen and rush");
        String mode = Main.input.nextLine().toLowerCase();
        if (mode.equals("rush")) {
            GameMode.Rush();
        } else if (mode.equals("zen")) {
            GameMode.zen();
        } else {
            System.out.println("We don't have that mode.");
        }
    }

}

