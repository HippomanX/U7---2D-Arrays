/*
A Simple Implementation of Conway's Game of Life

The game board is rectangular, with each cell being either:
dead = 0
alive = 1

Cells update using the Classic Conway GOL rules:
1) Any live cell with less than two live neighbours dies (underpopulation)
2) Any live cell with two or three live neighbours lives (next generation)
3) Any live cell with more than three live neighbours dies (overpopulation)
4) Any dead cell with exactly three live neighbours becomes a live cell (reproduction)
*/

public class GameOfLife{
   private int[][] board;
   
   //Constructor, initialize board
   public GameOfLife(int nRows, int nCols){
      //your code goes here
      board = new int[nRows][nCols];
      for (int x = 0; x < nRows; x++){
         for (int y = 0; y < nCols; y++){
            this.board[x][y] = 0;
         }
      }
   }
   
   //print a representation of the board
   public void print(){
      for(int i = 0; i < this.board.length; i++){
         for(int j = 0; j < this.board[0].length; j++){
            System.out.print(" [");
            System.out.print(this.board[i][j]);
            System.out.print("]");
         }
         System.out.println();
      }
   }
   public int numRows(){
      /*Return the number of rows on this GOL board
      Input:
         this: a GameOfLife object
      Output:
         return: the number of rows in this GOL board
      Ex.
      GameOfLife gol = new GameOfLife(25, 50);
      gol.numRows() -> 25
      */
      return this.board.length;
   }

   public int numCols(){
      /*Return the number of columns on this GOL board
      Input:
         this: a GameOfLife object
      Output:
         return: the number of columsn in this GOL board
      Ex.
      GameOfLife gol = new GameOfLife(25, 50);
      gol.numCols() -> 50
      */
      return this.board[0].length;
   }
   public boolean isDead(int row, int col){
      /*Check if the cell at (row, col) is dead
      Input:
         int row: the row coordinate of the cell to check
         int col: the column coordinate of the cell to check
         this: a GameOfLife object
      Output:
         return: whether or not the cell at (row, col) is dead
      Ex.
      GameOfLife gol = new GameOfLife(2, 3);
      ...some code modifies gol...
      gol.print()
      [1] [0] [0]
      [0] [0] [1]
      [0] [0] [0]
      gol.isDead(0, 0) -> false
      gol.isDead(0, 1) -> true
      */
      if (board[row][col] == 0) return true;
      else return false; 
   
   }
   public boolean isAlive(int row, int col){
      /*Check if the cell at (row, col) is alive
      Input:
         int row: the row coordinate of the cell to check
         int col: the column coordinate of the cell to check
         this: a GameOfLife object
      Output:
         return: whether or not the cell at (row, col) is alive
      Ex.
      GameOfLife gol = new GameOfLife(3, 3);
      ...some code modifies gol...
      gol.print()
      [1] [0] [0]
      [0] [0] [1]
      [0] [0] [0]
      gol.isAlive(0, 0) -> true
      gol.isAlive(0, 1) -> false
      */
      if (board[row][col] == 1) return true;
      else return false;
   }
   public void setDead(int row, int col){
      /*Set the cell at (row, col) to be dead
      Input:
         int row: the row coordinate of the cell
         int col: the column coordinate of the cell
         this: a GameOfLife object
      Output: None
      Side Effects: the cell at (row, col) is now dead
      Ex.
      GameOfLife gol = new GameOfLife(3, 3);
      ...some code to make all cells alive...
      gol.print()
      [1] [1] [1]
      [1] [1] [1]
      [1] [1] [1]
      gol.setDead(1, 1);
      gol.print()
      [1] [1] [1]
      [1] [0] [1]
      [1] [1] [1]
      */
      board[row][col] = 0;
   }

   public void setAlive(int row, int col){
      /*Set the cell at (row, col) to be alive
      Input:
         int row: the row coord of the cell to set
         int col: the column coord of the cell to set
         this: a GameOfLife object
      Output: none
      Side Effects: the cell at (row, col) is now alive
      Ex.
      GameOfLife gol = new GameOfLife(3, 3);
      gol.setAlive(1, 1);
      gol.print()
      [0] [0] [0]
      [0] [1] [0]
      [0] [0] [0]
      */
      board[row][col] = 1;
   }
   public void changeState(int row, int col){
      /*Change the state of the cell at (row, col), if it is alive
      make it dead, if it is dead, make it alive.
      Input:
         int row: the row coordinate of the cell to change
         int col: the column coordinate of the cell to change
         this: a GameOfLife object
      Output: none
      Side Effects: the state of the cell at (row, col) has flipped
      Ex.
      GameOfLife gol = new GameOfLife(3, 3);
      gol.changeState(1, 1)
      gol.print()
      [0] [0] [0]
      [0] [1] [0]
      [0] [0] [0]
      gol.changeState(1, 1)
      gol.print()
      [0] [0] [0]
      [0] [0] [0]
      [0] [0] [0]
      */
      if (this.board[row][col] == 1){
         this.board[row][col] = 0;
      }
      else{
         this.board[row][col] = 1;
      }
   }
   public int countLivingAbove(int row, int col){
      int counter = 0;
      if (this.board[row+1][col-1] == 1) counter++;
      if (this.board[row+1][col] == 1) counter++;
      if (this.board[row+1][col+1] == 1) counter++;
      return counter;
   }
   public int countLivingBelow(int row, int col){
      int counter = 0;
      if (this.board[row-1][col-1] == 1) counter++;
      if (this.board[row-1][col] == 1) counter++;
      if (this.board[row-1][col+1] == 1) counter++;
      return counter;
   }
   
   
   public int numLivingNeighbors(int row, int col){
      /*Return the number of living neighbor cells to the cell at
      (row, col). Neighbors are cells within 1 row or 1 column of
      the specified cell. Should not include the cell at (row, col)
      in the count of living cells.
      Input:
         int row: the row coordinate of the cell whose neighbors we are checking
         int col: the col coordinate of the cell whose neighbors we are checking
         this: a GameOfLife object
      Output:
         return: num. of living neighbors for the cell at (row, col)
      Ex.
      GameOfLife gol = new GameOfLife(4, 3)
      //...some code sets the value of individual cells
      gol.print()
      [0] [1] [0]
      [1] [1] [1]
      [0] [1] [0]
      [0] [1] [0]
      gol.numLivingNeighbors(2, 1) -> 4
      gol.numLivingNeighbors(0, 1) -> 3
      gol.numLivingNeighbors(3, 0) -> 2
      gol.numLivingNeighbors(0, 0) -> 3
      */
      int counter = 0;
      
      
      if (row == 0 && col == 0){
         if (this.board[row][col+1] == 1) counter++;
         if (this.board[row+1][col+1] == 1) counter++;
         if (this.board[row+1][col] == 1) counter++;
      }
      else if (row == 0 && col == this.numCols()-1){
         if (this.board[row][col-1] == 1) counter++;
         if (this.board[row+1][col] == 1) counter++;
         if (this.board[row+1][col-1] == 1) counter++;
      }
      else if (row == this.numRows()-1 && col == 0){
         if (this.board[row][col+1] == 1) counter++;
         if (this.board[row-1][col] == 1) counter++;
         if (this.board[row-1][col+1] == 1) counter++;
      }
      else if (row == this.numRows()-1 && col == this.numCols()-1){
         if (this.board[row][col-1] == 1) counter++;
         if (this.board[row-1][col-1] == 1) counter++;
         if (this.board[row-1][col] == 1) counter++;
      }
      else if (row == 0){
         counter += this.countLivingAbove(row, col);
         if (this.board[row][col-1] == 1) counter++;
         if (this.board[row][col+1] == 1) counter++;
      }
      else if (row == this.numRows()-1){
         counter += this.countLivingBelow(row, col);
         if (this.board[row][col-1] == 1) counter++;
         if (this.board[row][col+1] == 1) counter++;
      }
      else if (col == 0){
         if (this.board[row-1][col] == 1) counter++;
         if (this.board[row][col+1] == 1) counter++;
         if (this.board[row+1][col+1] == 1) counter++;
         if (this.board[row-1][col+1] == 1) counter++;
         if (this.board[row+1][col] == 1) counter++;
      }
      else if (col == this.numCols()-1){
         if (this.board[row][col-1] == 1) counter++;
         if (this.board[row+1][col-1] == 1) counter++;
         if (this.board[row-1][col-1] == 1) counter++;
         if (this.board[row+1][col] == 1) counter++;
         if (this.board[row-1][col] == 1) counter++;
      }
      else{
         counter += this.countLivingBelow(row, col);
         counter += this.countLivingAbove(row, col);
         if (this.board[row][col-1] == 1) counter++;
         if (this.board[row][col+1] == 1) counter++;
      }
      //if (this.board[row][col-1] == 1) counter++;
      
      //if (this.board[row][col+1] == 1) counter++;
      
      return counter;
   }
   public boolean shouldBeAlive(int row, int col){
      /*Determine whether the cell at (row, col) should be alive in
      the next round according to the rules of Game of Life:
      1) Any live cell with less than two live neighbors dies
      2) Any live cell with two or three live neighbors survives
      3) Any live cell with more than three live neighbors dies
      4) Any dead cell with exactly 3 live neighbors becomes alive
      Input:
         int row: the row coordinate of the cell to check
         int col: the col coordinate of the cell to check
         this: a GameOfLife object
      Output:
         return: whether or not the cell should be alive next round
      Ex.
      GameOfLife gol = new GameOfLife(4, 3);
      //...some code sets the cells...
      gol.print()
      [0] [1] [0]
      [1] [1] [1]
      [0] [1] [0]
      [0] [1] [0]
      gol.shouldBeAlive(0, 0) -> true  (reproduction)
      gol.shouldBeAlive(0, 1) -> true  (survival)
      gol.shouldBeAlive(1, 1) -> false (overpopulation)
      gol.shouldBeAlive(3, 1) -> false (underpopulation)
      */
      if (board[row][col] == 1){
         if (numLivingNeighbors(row, col) < 2) return false;
         if (numLivingNeighbors(row, col) <= 3 && numLivingNeighbors(row, col) >= 2) return true;
         else return false;
      }
      else if (numLivingNeighbors(row, col) == 3) return true;   
      return false; //should not happen   
   }
   public boolean[][] shouldBeAliveMatrix(){
      /*Return a 2D array with the same dimensions as the board
      where each entry is whether or not the cell at those
      coordinates should be alive or dead in the next round.
      Input:
         this: a GameOfLife object
      Output:
         return: a boolean[][] where each entry is whether or not
         the cell at those coordinates should be alive (true) or
         dead (false) in the next round.
      Ex.
      GameOfLife gol = new GameOfLife(4, 3);
      //...some code sets up the board...
      gol.print()
      [0] [1] [0]
      [1] [1] [1]
      [0] [1] [0]
      [0] [1] [0]
      boolean[][] alives = gol.shouldBeAlive();
      //alives has no .print() but if it did, it would look like:
      alives.print()
      [true]   [true]  [true]
      [true]   [false] [true]
      [false]  [false] [false]
      [false]  [false] [false]
      */
      boolean[][] newBoard = new boolean[this.numRows()][this.numCols()];
      for (int x = 0; x < this.numRows(); x++){
         for (int y = 0; y < this.numCols(); y++){
            newBoard[x][y] = this.shouldBeAlive(x, y);
         }
      }
      return newBoard;
   }
   public void updateBoard(){
      /*Updates the board of this GameOfLife object from one round 
      to the next.
      Input:
         this: a GameOfLife object to update
      Output: none
      Side Effects: the values in this GameOfLife's board have been
      updated using the rules of GOL.
      */
      boolean[][] updatedBoard = this.shouldBeAliveMatrix();
      for (int x = 0; x < this.numRows(); x++){
         for (int y = 0; y < this.numCols(); y++){
            if (updatedBoard[x][y] == false){
               this.board[x][y] = 0;
            }
            else{
               this.board[x][y] = 1;
            }
         }
      }
   }
   public static void main(String args[]){
      GameOfLife gol = new GameOfLife(3, 3);
      gol.setAlive(0, 1);
      gol.setAlive(1, 1);
      gol.setAlive(2, 1);
      gol.print();
         StdOut.println(" ");
      gol.updateBoard();
      //int test = this.numLivingNeighbors(1, 1);
         StdOut.println();
      gol.print();
         StdOut.println(" ");
      gol = new GameOfLife(3, 3);
      gol.setAlive(0, 0);
      gol.setAlive(0, 1);
      gol.setAlive(1, 0);
      gol.setAlive(1, 1);
      gol.print();
         StdOut.println(" ");
      gol.updateBoard();

   }


   


}