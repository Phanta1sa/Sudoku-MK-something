
import java.util.InputMismatchException;
import java.util.Random;

import static java.lang.Math.sqrt;

public class UserInterface {
    enum Choice{
        SOLVE,
        PLAY,
        GEN,
        VALIDATE,
        EXIT,
        TESTING
    }

    public static void userInterfaceMain() {
        System.out.println("\tWELCOME TO SUDOKU");
        System.out.println("what would you like to do?");
        System.out.println("1. Solve\n2. Play\n3. Gen\n4. Validate\n5. Exit");
        switch (userInterfaceChoice()){
            case GEN -> userInterfaceGen();
            case PLAY -> GameLoop.startPlaying();
            case SOLVE -> userInterfaceSolve();
            case VALIDATE -> userInterfaceValid();
            case EXIT -> System.exit(0);
            case TESTING -> {
                System.out.println("testing");
                int[][] board =  new int[Board.SIZEOFBOARD][Board.SIZEOFBOARD];
                Solver solver = new Solver();
                TimeControl.timerStart();
                System.out.println("Solving...");
                //System.out.println(SolverBrute.solve(board));
                System.out.println(solver.solveBoard(board));
                //solver.solve(board);
                Board.printBoard(board);
                System.out.println(TimeControl.timeTaken());
            }

        }
    }

    private static void userInterfaceValid() {
        System.out.print("what do you want to do ?\n- (v)alidate board for solvabilty?\n- exit ?");
        String choice =Main.input.nextLine();
        switch (choice) {
            case "v", "V", "validate", "VALIDATE":
                System.out.println("THIS WILL ONLY CHECK FOR SOLVABILITY");
                System.out.println("would you like to read from a file or write it here, it will default here");
                String temp = Main.input.nextLine();
                if (temp.equalsIgnoreCase("file")) {
                    System.out.println("please enter a file name");
                    String filename = Main.input.nextLine();
                    int[][] board =  FileManager.readFromFile(filename);
                    if (Main.solve.solve(board)) {
                        System.out.println("it is solvable");
                    }else System.out.println("it is not solvable");
                }else {
                    System.out.println("please write the board");
                    int[][] board = Board.readingBoard(Main.input);
                    if (Main.solve.solve(board)) {
                        System.out.println("it is solvable");
                    } else System.out.println("it is not solvable");
                }
                break;
            case "q", "exit", "quit":
                break;
        }
    }
    private static Choice userInterfaceChoice() {
        while (true) {
            String choice = Main.input.nextLine().toLowerCase();
            switch (choice) {
                case "1", "solve", "s":  return Choice.SOLVE;
                case "2", "play", "p":   return Choice.PLAY;
                case "3", "gen", "generate", "g": return Choice.GEN;
                case "4", "validate", "v": return Choice.VALIDATE;
                case "5", "exit", "quit", "e", "q": return Choice.EXIT;
                case "testing": return Choice.TESTING;
                default:
                    System.out.println("Invalid choice. Try again:");
            }
        }
    }

    private static void userInterfaceGen() {
        System.out.println("what size board do you want ?");
        switch (Main.input.nextLine().toLowerCase()) {
            case "16" ,"sixteen" , "big"  -> Board.SIZEOFBOARD = 16;
            case "9", "normal", "nine" ->Board.SIZEOFBOARD = 9;
            case "4" ,"four","small" ->Board.SIZEOFBOARD = 4;
            default -> {
                System.out.println("invalid size defaulting to 9.");
                Board.SIZEOFBOARD = 9;
            }

        }
        Board.SIZEOFSQUARE = (int) sqrt(Board.SIZEOFBOARD); // theses shouldn't be static but well
        Board.RULE = (Board.SIZEOFBOARD*(Board.SIZEOFBOARD+1))/2;
        System.out.println("what seed do you want to use?");

        int seed;
        try{//some error handling for flair
            seed = Main.input.nextInt();
            Main.input.nextLine();
        }catch (InputMismatchException e){
            System.out.println("seed can't be empty or letters, defaulting to a random seed");
            seed = new Random().nextInt();
        }
        int[][] board = Gen.generateBoard(seed);
        System.out.println("how many clues do you want to use?");

        int clues =Board.SIZEOFBOARD==9?45:Board.SIZEOFBOARD==4?8:128;
        try {
            clues = Main.input.nextInt();
            Main.input.nextLine();

            while (clues > (Board.SIZEOFBOARD*Board.SIZEOFBOARD-1) && clues < (Board.SIZEOFBOARD==9?27:Board.SIZEOFBOARD==4?14:136)) { // the values here are based on trial and error and are not the true min for what can be generated, and they might cause issues in some seeds
            System.out.println("enter something between " +(Board.SIZEOFBOARD-1) + " and " + (Board.SIZEOFBOARD==9?27:Board.SIZEOFBOARD==4?14:136));
            clues = Main.input.nextInt();
            Main.input.nextLine();
            }
        } catch (InputMismatchException e){
            System.out.println("clues can't be empty or letters, defaulting to " + (Board.SIZEOFBOARD==9?45:Board.SIZEOFBOARD==4?8:128));
        }
        System.out.println("printing board...");
        Gen.deleteCell(board, seed, clues);
        Board.printBoard(board);
        System.out.println("would you like to play this board ? or you can save it into a file?");
        String temp = Main.input.nextLine();
        if (temp.equalsIgnoreCase("play")) {
            int[][] solved = Board.copy(board);
            Solver solver = new Solver();
            solver.solveBoard(solved);
            while (GameLoop.condForVictory(solved,board )) {
                GameLoop.play(board, solved, Main.input);
            }
        }else if (temp.equalsIgnoreCase("save") || (temp.equalsIgnoreCase("file"))) {
            System.out.println("what would you like to name the file?");
            String filename = Main.input.nextLine();
            System.out.println("saving to " + filename);
            FileManager.writeToFile(filename, board);
            System.out.println("done saving");
            return;
        }else {
            System.out.println("invalid input\n sending to main menu");
            UserInterface.userInterfaceMain();
        }
    }
    private static void userInterfaceSolve(){
        while (true) {
            System.out.println("would you like for the puzzle to be read from a file or for it to be manually written");
            if (Main.input.nextLine().equalsIgnoreCase("file")) {
                System.out.println("please select a file");
                String filename = Main.input.nextLine();
                Main.solve.solveBoard(FileManager.readFromFile(filename));
                System.out.println("would you like to save the solution?");
                if (Main.input.nextLine().equalsIgnoreCase("yes")) {
                    FileManager.writeToFile("Solved",Main.solve.solved);
                }

            } else if (Main.input.nextLine().equalsIgnoreCase("manually")) {
                int[][] board = Board.readingBoard(Main.input);
                Main.solve.solve(board);
                Board.printBoard(board);
                System.out.println("would you like to save the solution?");
                if (Main.input.nextLine().equalsIgnoreCase("yes")) {
                    FileManager.writeToFile("Solved",Main.solve.solved);
                }
                return;
            } else {
                System.out.println("invalid input");
                System.out.println("would you like to try again or to exit ?");
                if (Main.input.nextLine().equalsIgnoreCase("exit") || Main.input.nextLine().equalsIgnoreCase("q")) {
                    return;
                }
            }
        }
    }
}

