public class Solver {
    int[] freq = new int[Board.SIZEOFBOARD]; // how many times does each element appear in the board
    int[][] solved = new int[Board.SIZEOFBOARD][Board.SIZEOFBOARD]; // saving for the puzzle for file system
    int[] bitMaskCol = new int[Board.SIZEOFBOARD];
    int[] bitMaskRow = new int[Board.SIZEOFBOARD];
    int[] bitMaskSq = new int[Board.SIZEOFBOARD];
    int[] usedInRow = new int[Board.SIZEOFBOARD];
    int[] usedInCol = new int[Board.SIZEOFBOARD];
    int[] usedInSq = new int[Board.SIZEOFBOARD];
    public boolean isPlaceValid(int row, int col, int number) {
        return (bitMaskRow[row] & (1<<(number-1))) == 0 && (bitMaskCol[col] & (1<<(number-1))) ==0 && (bitMaskSq[squareIndex(row,col)] & (1<<(number-1))) == 0; // checks if the bits are used
    }
    public boolean solve(int[][] board){
        for (int row = 0; row < Board.SIZEOFBOARD; row++) {
            for (int col = 0; col < Board.SIZEOFBOARD; col++) {
                if(board[row][col] == 0){
                    for(int number =1 ; number<=Board.SIZEOFBOARD;number++){
                        if (freq[number-1] == Board.SIZEOFBOARD) continue;
                        if(isPlaceValid(row, col, number)) {
                            if(usedInCol[col] == 0 ) return false;
                            if(usedInRow[row] == 0 ) return false;
                            if(usedInSq[squareIndex(row,col)] == 0 ) return false;
                            board[row][col] = number;
                            addStep(row, col, number);
                            if (solve(board)) {
                                return true;
                            } else {
                                removeStep(row, col, number);// redo the changes you did and try again
                                board[row][col] = 0;

                            }
                        }
                    }return false;
                }
            }
        }return true;
    }
    private int squareIndex(int row, int col) {
        return (row / Board.SIZEOFSQUARE) * Board.SIZEOFSQUARE + (col / Board.SIZEOFSQUARE);
    }
    private  void addStep(int row,int col,int number){
        freq[number -1]++;
        bitMaskRow[row] |= (1 << (number - 1)); // flip the bit (number-1) from the right to 1
        bitMaskCol[col] |= (1 << (number - 1));
        bitMaskSq[squareIndex(row,col)] |= (1 << (number - 1));
        usedInRow[row] &= ~(1 << (number - 1));
        usedInCol[col] &= ~(1 << (number - 1));
        usedInSq[squareIndex(row, col)] &= ~(1 << (number - 1));
    }
    private void removeStep(int row,int col,int number){
        freq[number - 1]--;
        bitMaskRow[row] &= ~(1 << (number - 1)); // get the ~ bit(the compliment) so you can switch the 1 into 0 using and assignment 1 << x means put one after x amount of bits
        bitMaskCol[col] &= ~(1 << (number - 1));
        bitMaskSq[squareIndex(row,col)] &= ~(1 << (number - 1));
        usedInRow[row] |= (1 << number-1);
        usedInCol[col] |= (1 << (number - 1));
        usedInSq[squareIndex(row, col)] |= (1 << (number - 1));
    }
    public void metaDataReader(int[][] board){
        for (int row = 0; row < Board.SIZEOFBOARD; row++) {
            for (int col = 0; col < Board.SIZEOFBOARD; col++){
                if(board[row][col] != 0){
                    freq[board[row][col]-1]++;
                    bitMaskRow[row] |= (1 << board[row][col]-1);
                    bitMaskCol[col] |= (1 <<  board[row][col]-1);
                    bitMaskSq[squareIndex(row,col)] |= (1<< board[row][col]-1);
                    usedInRow[row] ^= (1 << board[row][col]-1);
                    usedInCol[col] ^= (1 << board[row][col]-1);
                    usedInSq[squareIndex(row,col)] ^= (1 << board[row][col]-1);
                }
            }
        }
    }
    public boolean solveBoard(int[][] board){
        int max = (1<<Board.SIZEOFBOARD)-1;
        for (int i = 0; i < Board.SIZEOFBOARD;i++) {
            usedInRow[i]=max ;
            usedInCol[i]=max;
            usedInSq[i]=max;
        }
        freq = new int[Board.SIZEOFBOARD];
        solved = Board.copy(board);
        metaDataReader(board);
        return solve(board);
    }

    // todo:early exit if sum can't = 45
    //todo:naked singles check
    //todo:comment the code
}

