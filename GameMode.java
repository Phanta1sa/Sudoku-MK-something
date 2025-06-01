import java.util.Random;

public class GameMode {
    static String input;
    public static void Rush() {
        System.out.println("WELCOME TO RUSH!!!!");
        System.out.println("this game mode is a race against time you have to solve with 45 clues before the time is done");
        System.out.println("Enter a seed (or leave blank for random):");
        input = Main.input.nextLine();
        int seed = input.isEmpty() ? new Random().nextInt() : Integer.parseInt(input); //last prog lecture (:
        System.out.println("Type (y)es to start the game or (n)o to cancel:");
        String startChoice = Main.input.nextLine().toLowerCase();

        if (!startChoice.equals("y")) {
            System.out.println("Exiting Rush mode.");
            return;
        }

        int[][] board = Gen.generateBoard(seed);
        int[][] boardSolved = Board.copy(board);
        Solver solver = new Solver();
        solver.solve(boardSolved);
        Gen.deleteCell(board, seed, 45);

        TimeControl.timerForRush(5); // Start 5-minute timer

        while (TimeControl.waiting && !GameLoop.condForVictory(boardSolved, board)) {
            Board.printBoard(board);
            GameLoop.play(board, boardSolved, Main.input);
        }

        if (GameLoop.condForVictory(boardSolved, board)) {
            System.out.println("You finished in time! ");
        } else {
            System.out.println("Time's up! Better luck next time. here is your seed if you want to try again with the same puzzle, seed:" + seed);
        }
    }

    public static void zen(){
        System.out.println("hello and all welcome to zen mode");
        System.out.println("everything here is chill no timer, no hint limit, no nothing all you have to do here is play");
        System.out.println("Enter a seed (or leave blank for random):");
        input = Main.input.nextLine();
        int seed = input.isEmpty() ? new Random().nextInt() : Integer.parseInt(input);
        int[][] board = Gen.generateBoard(seed);
        int[][] boardSolved = Board.copy(board);
        Solver solver = new Solver();
        solver.solve(boardSolved);
        Gen.deleteCell(board, seed, 45);

        System.out.println("Here is your puzzle:");
        Board.printBoard(board);
        while (!GameLoop.condForVictory(boardSolved, board)) {
            GameLoop.play(board, boardSolved, Main.input);
        }

    }
}
