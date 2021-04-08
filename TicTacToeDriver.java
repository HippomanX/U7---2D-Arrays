public class TicTacToeDriver{
   private static TicTacToe gameBoard;
   private static boolean turnX;
   //setup the game according to the user's instructions
   public static void setupGame(){
      System.out.println("How many rows would you like on the game board?");
      int numRows = StdIn.readInt();
      System.out.println("How many columns would you like on the game board?");
      int numCols = StdIn.readInt();
      //setup the game board
      gameBoard = new TicTacToe(numRows, numCols);
      turnX = true;
   }
   //check whether board has 3 Os in a row in column col
   /*public boolean threeVerticalOs(int col){
      if (gameBoard.board[0][col].equals("O")){
         if (gameBoard.board[1][col].equals("O")){
            if (gameBoard.board[2][col].equals("O")){
               return true;
            }
         }
      }
      else return false;
   }
   //check whether board has 3 Os in a row in any column
   public boolean threeVerticalOs(){
      for(int i = 0; i < gameBoard[i].length(); i++){
         if (threeVerticalOs(i)) return true;
         
      }
      return false;
   }
   //check whether board has 3 Os in a row in row row
   public boolean threeHorizontalOs(int row){
      if (gameBoard.board[row][0].equals("O")){
         if (gameBoard.board[row][1].equals("O")){
            if (gameBoard.board[row][2].equals("O")){
               return true;
            }
         }
      }
      else return false;
   }
   //check whether board has 3 Os in a row in any row
   public boolean threeHorizontalOs(){
      for(int i = 0; i < gameBoard[i].length(); i++){
         if (threeHorizontalOs(i)) return true;
         
      }
      return false;
   }
   //check whether board has 3 Os in the diagonals
   public boolean threeDiagonalOs(){
   
   }
   //check if O has 3 in a row, any orientation
   public boolean threeInARowO(){
   
   }
   //check if O has 3 in a row, any orientation
   public boolean threeInARowX(){

   }*/
   //run all the steps for the X player's turn
   public static void xTurn(){
      //ask the player where they would like to move
      turnX = true;
      System.out.println("--------------X Player---------------");
      gameBoard.print(); //print the board
      System.out.println("Which row would you like to place on?");
      int row = StdIn.readInt();
      System.out.println("Which column would you like to place on?");
      int col = StdIn.readInt();
      //check if that space is free
      if(gameBoard.isEmpty(row, col)){
         gameBoard.setX(row, col);
      }
      else{//ask again
         System.out.println("Please choose an empty space");
         xTurn();
      }
   }
   
   //run all the steps for the O player's turn
   public static void oTurn(){
      turnX = false;
      //ask the player where they would like to move
      System.out.println("--------------O Player---------------");
      gameBoard.print(); //print the board
      System.out.println("Which row would you like to place on?");
      int row = StdIn.readInt();
      System.out.println("Which column would you like to place on?");
      int col = StdIn.readInt();
      //check if that space is free
      if(gameBoard.isEmpty(row, col)){
         gameBoard.setO(row, col);
      }
      else{//ask again
         System.out.println("Please choose an empty space");
         oTurn();
      }
   }
   
   //ask the players if the game is over. Return true if they say "y"
   //false if they say "n", and ask again if neither
   public static boolean askGameOver(){
      //ask the player if the game is over
      gameBoard.print(); //print out the board
      if (gameBoard.numEmpty() == 0){
         System.out.println("The game has ended. I hope you had fun playing!");
         return true;
      }
      System.out.println("Is the game over?");
      System.out.print("(y/n)");
      String reply = StdIn.readString();
      if(reply.equals("y")){
         if (turnX){
            System.out.println("Good job winning!, X!");}
         else{
            System.out.println("Good job winning!, O!");
         }
         return true;
         
      }
      else if (reply.equals("n")){
         return false;
      }
      else{
         System.out.println("Please type y or n");
         askGameOver();
      }
      return false; //should never get here
   }
         
   
   
   public static void main(String[] args){
      setupGame(); //ask the user how to setup the game
      
      //do the first 3 rounds before asking if anyone one
      
      //Round 1
      xTurn(); //X goes first
      oTurn(); //O goes second
      
      //Round 2
      xTurn(); //X goes first
      oTurn(); //O goes second
      
      //Round 3
      xTurn(); //X goes first
      //check if anyone won yet
      while(askGameOver() == false){//while the game is not over
         oTurn();
         if(askGameOver() == true){//if O won, break
            break;
         }
         else{//otherwise do X's turn
            xTurn();
         }
      }    
   }
}