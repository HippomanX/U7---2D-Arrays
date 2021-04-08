public class TicTacToe{
   private String[][] board; //represents the game board
   //private int Rows;
   //private int Cols;
   public TicTacToe(int numRows, int numCols){
      /*Setup a new empty TicTacToe board with
      numRows rows and numCols columns.
      Input:
         int numRows: the number of rows on the board
         int numCols: the number of columns on the board
      Output:
         this: a new empty Tic Tac Toe board
      Ex.
      TicTacToe ttt = new TicTacToe(3, 3);
      ttt.print()
      [ ] [ ] [ ]
      [ ] [ ] [ ]
      [ ] [ ] [ ]
      */
      board = new String[numRows][numCols];
      for (int x = 0; x < numRows; x++){
         for (int y = 0; y < numCols; y++){
            this.board[x][y] = " ";
         }
      }
      
      
      //Cols = numCols;
   }
   
   public void print(){
      /*Prints out a representation of this board to StdOut
      Input:
         this: a TicTacToe object
      Output:
         StdOut: prints out a representation of this board
      Ex.
      TicTacToe ttt = new TicTacToe(3, 3);
      ...some moves later...
      ttt.print()
      [X] [ ] [O]
      [O] [X] [X]
      [ ] [ ] [x]
      */
      for (int i = 0; i < this.board.length; i++){
         for(int j = 0; j < this.board[i].length; j++){
            StdOut.print(" [" + this.board[i][j] + "]");
         }
         StdOut.println();
      }
   }
   public void setX(int row, int col){
      /*Set the square at (row, col) to "X"
      Input:
         this: a TicTacToe object
         int row: the row coordinate
         int col: the column coordinate
      Output: none
      Side Effects: the square at (row, col) is now "X"
      Ex.
      TicTacToe ttt = new TicTacToe(3, 3);
      ttt.setX(0, 2);
      ttt.print()
      [ ] [ ] [X]
      [ ] [ ] [ ]
      [ ] [ ] [ ]
      */
      this.board[row][col] = "X";
   }
   public void setO(int row, int col){
      /*Set the square at (row, col) to "O"
      Input:
         this: a TicTacToe object
         int row: the row coordinate
         int col: the column coordinate
      Output: none
      Side Effects: the square at (row, col) is now "O"
      Ex.
      TicTacToe ttt = new TicTacToe(3, 3);
      ttt.setO(2, 0);
      ttt.print()
      [ ] [ ] [ ]
      [ ] [ ] [ ]
      [O] [ ] [ ]
      */
      this.board[row][col] = "O";
   }
   public String getSquare(int row, int col){
      /*Get the value at (row, col)
      Input:
         this: a TicTacToe object
         int row: the row coordinate
         int col: the column coordinate
      Output:
         return: the value at (row, col)
      Ex.
      TicTacToe ttt = new TicTacToe(3, 3);
      ttt.setX(0, 2);
      ttt.setO(2, 0);
      ttt.print()
      [ ] [ ] [X]
      [ ] [ ] [ ]
      [O] [ ] [ ]
      ttt.getSquare(0, 2) -> X
      ttt.getSquare(2, 0) -> O
      ttt.getSquare(1, 1) -> " " (quotes just to show space)
      */
      return this.board[row][col];
   }
   public boolean isEmpty(int row, int col){
      /*Check if the square at (row, col) is empty
      Input:
         this: a TicTacToe object
         int row: the row coordinate
         int col: the column coordinate
      Output:
         return: whether or not the square at
                 (row, col) is empty
      Ex.
      TicTacToe ttt = new TicTacToe(3, 3);
      ttt.setX(0, 2);
      ttt.setO(2, 0);
      ttt.print()
      [ ] [ ] [X]
      [ ] [ ] [ ]
      [O] [ ] [ ]
      ttt.isEmpty(0, 2) -> false
      ttt.isEmpty(1, 1) -> true
      ttt.isEmpty(2, 0) -> false
      */
      if (this.board[row][col].equals("X") || this.board[row][col].equals("O")) return false;
      else return true;
   }
   public int numEmpty(){
      /*Count the number of empty squares left on the board
      Input:
         this: a TicTacToe object
      Output:
         return: the number of empty squares left on the board
      Ex.
      TicTacToe ttt = new TicTacToe(3, 3);
      ttt.numEmpty() -> 9
      ttt.setX(0, 0);
      ttt.numEmpty() -> 8
      ttt.setO(1, 1);
      ttt.numEmpty() -> 7
      */
      int emptyCounter = 0;
      for (int x = 0; x < this.board.length; x++){
         for (int y = 0; y < this.board[x].length; y++){
            if (this.board[x][y].equals(" ")){
               emptyCounter++;
            }
         }
      }
      return emptyCounter;
   }      





   
}