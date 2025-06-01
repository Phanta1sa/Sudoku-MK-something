public class SolverBrute {
    /* first solver I made it works on brute force with no optimization */

    private static boolean isRow(int[][] board, int row, int number) {
        for (int i = 0; i < Board.SIZEOFBOARD; i++) {
            if (board[row][i] == number) return false;
        }
        return true;
    }
    private static boolean isCol(int[][] board, int col, int number) {
        for (int i = 0; i < Board.SIZEOFBOARD; i++) {
            if (board[i][col] == number) return false;
        }
        return true;
    }
    private static boolean isSquare(int[][] board, int number, int row, int col) {
        int tempFi = row/(Board.SIZEOFSQUARE)*(Board.SIZEOFSQUARE);
        int tempFj = col/(Board.SIZEOFSQUARE)*(Board.SIZEOFSQUARE);
        for (int i = 0; i <(Board.SIZEOFSQUARE) ; i++) {
            for (int j = 0; j < (Board.SIZEOFSQUARE); j++) {
                if (board[i+tempFi][j+tempFj] == number) return false;

            }
        }
        return true;
    }
    // the Is brothers check for validity
    public static boolean isPlaceValid(int[][] board, int row, int col, int number) { // Used to check if a digit placement is valid according to Sudoku rules

        return isCol(board, col, number) && isRow(board, row, number) && isSquare(board, number, row, col);
    }
    public static boolean solve(int[][] board){
        for (int row = 0; row < Board.SIZEOFBOARD; row++) {
            for (int col = 0; col < Board.SIZEOFBOARD; col++) {
                if(board[row][col] == 0){
                    for(int Number = 1;Number<=Board.SIZEOFBOARD;Number++){
                        if(isPlaceValid(board, row, col, Number)){
                            board[row][col] = Number;
                            if(solve(board)){
                                return true;
                            }else{
                                board[row][col] = 0;
                            }
                        }
                    } return false;
                }
            }
        }
        return true;
    }

}
