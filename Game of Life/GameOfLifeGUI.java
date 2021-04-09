/*
An interactive GUI for Conway's Game of Life
*/

import java.awt.Color;
import java.awt.event.KeyEvent;

public class GameOfLifeGUI{
   private static GameOfLife game; //the game itself
   
   //parameters for setting up the window
   private static final int minWindowSize = 400; //minimum window side, pixels
   private static final int maxWindowSize = 800; //maximum window side, pixels
   
   //parameters for drawing
   private static double cellSize;                    //cell side length, in pixels, calculated after window setup
   private static final double gridRadius = 0.005;    //pen radius for drawing grid lines
   private static final Color gridColor = Color.GRAY; //pen color for drawing grid lines
   private static final int inputPause = 20;          //time to pause for sensing
   private static final int animatePause = 80;       //time to pause for animation
   
   public static void setupGUI(){
      //Ask the user what dimensions they want to use
      System.out.println("How many rows would you like?");
      int numRows = StdIn.readInt();
      System.out.println("How many columns would you like?");
      int numCols = StdIn.readInt();
      game = new GameOfLife(numRows, numCols);
      //figure out the window dimensions and cell size
      int minNum = Math.min(numRows, numCols);
      int maxNum = Math.max(numRows, numCols);
      //maximum dimension takes precedence
      int sCell = minWindowSize/minNum;
      while(sCell*maxNum < maxWindowSize){
         sCell++;
      }
      if(sCell > 1){//you overrun the best fit
         sCell--;
      }
      
      int windowWidth = numCols*sCell; //window dimensions in pixels
      int windowHeight = numRows*sCell;//window dimensions in pixels
      StdDraw.setCanvasSize(windowWidth, windowHeight);
      StdDraw.setXscale(0.0, windowWidth*1.0);
      StdDraw.setYscale(0.0, windowHeight*1.0);
      cellSize = 1.0*sCell; //store the cell size for drawing
      StdDraw.enableDoubleBuffering(); //make the animation smooth      
   }
   
   //draw the cell in row, col
   public static void drawCell(int row, int col, boolean alive){
      double x = (col + 0.5)*cellSize;
      double y = (game.numRows() - row  - 0.5)*cellSize;
      Color c = Color.WHITE;
      if(alive){
         c = Color.BLACK;
      }
      StdDraw.setPenColor(c);
      StdDraw.filledRectangle(x, y, cellSize/2, cellSize/2);
   }
   
   //draw all of the cells on the board
   public static void drawCells(){
      for(int i = 0; i < game.numRows(); i++){
         for(int j = 0; j < game.numCols(); j++){
            drawCell(i, j, game.isAlive(i, j));
         }
      }
   }
   
   //draw a grid on the board
   public static void drawGrid(){
      //store the previous settings so you can restore them
      double prevRadius = StdDraw.getPenRadius();
      Color prevColor = StdDraw.getPenColor();
      //set the pen to the proper size and color for grid lines
      StdDraw.setPenRadius(gridRadius);
      StdDraw.setPenColor(gridColor);
      //draw the grid
      int numRows = game.numRows();
      int numCols = game.numCols();
      for(int i = 1; i < numRows; i++){
         //draw the horizontal grid lines
         double y = i*cellSize;
         StdDraw.line(0.0, y, numCols*cellSize, y);
      }
      for(int j = 1; j < numCols; j++){
         //draw the vertical grid lines
         double x = j*cellSize;
         StdDraw.line(x, 0.0, x, numRows*cellSize);
      }
   }
   
   //Draw the board: cells and grid
   public static void drawBoard(){
      drawCells();
      drawGrid();
      StdDraw.show();
   }
   
   //the main method of the game
   public static void main(String[] args){
      //setup the GUI
      setupGUI();
      //state variables, the game is either paused or running
      boolean paused = true;        //the game is either paused or running
      boolean printed= false;       //keep track of printing state messages
      boolean mouseClicked = false; //whether the mouse is being clicked now
      boolean pPressed = false;     //whether the p key is being pressed now
      //keep track of how long since last frame for animation
      int timeSinceFrame = 0;
      while(true){
         StdDraw.pause(inputPause);   //wait to slow down animation
         timeSinceFrame += inputPause;//time since last frame updates
         drawBoard();                  //draw the board
         //if the user presses the p key, pause/unpause the game
         if(StdDraw.isKeyPressed(KeyEvent.VK_P)){
            if(pPressed == false){
               paused = !paused; //pause/unpause game
               printed = false;  //allow printing next message
               pPressed = true;  //don't log multiple for one press
            }
         }
         else{
            if(pPressed == true){
               pPressed = false;
            }
         }
         //When the game is paused
         if(paused){
            //print a pause message if you have not yet
            if(printed == false){
               System.out.println("--------------");
               System.out.println("Game is paused");
               System.out.println("Click cells to change state");
               System.out.println("Press p to unpause");
               System.out.println("--------------");
               printed = true;
            }
            //the user can click on cells to flip their state
            if(StdDraw.isMousePressed()){
               if(mouseClicked == false){
                  //figure out which cell they are clicking
                  int i = game.numRows() - (int)(StdDraw.mouseY()/cellSize) - 1;
                  int j = (int)(StdDraw.mouseX()/cellSize);
                  //flip the state of that cell
                  game.changeState(i, j);
                  //don't log multiple changes for each click
                  mouseClicked = true;
               }
            }
            //when the user releases the mouse
            else{
               if(mouseClicked == true){
                  mouseClicked = false;
               }
            }
         }
         //if the game is not paused update the cells using the rules
         else{
            //print a unpause message if you have not yet
            if(printed == false){
               System.out.println("--------------");
               System.out.println("Game is unpaused");
               System.out.println("Press p to pause");
               System.out.println("--------------");
               printed = true;
            }
            //animate if you have waited longer than the animate frame rate
            if(timeSinceFrame > animatePause){
               timeSinceFrame = 0;
               game.updateBoard(); //update the board using GOL
            }
            //this covers a rare corner case of integer overflow to negative
            else if(timeSinceFrame < 0){
               timeSinceFrame = 0;
            }
         }
      }
   }
}
   
   
