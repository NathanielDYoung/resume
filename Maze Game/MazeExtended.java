import java.util.*;
import java.text.*;
import java.io.*;
import java.lang.*;
import javafx.application.*;
import javafx.event.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.animation.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import java.net.*;
import javafx.geometry.*;
import javafx.scene.text.*;
import java.awt.Dimension;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.Scanner;
import javax.swing.*;

public class MazeExtended extends Application{
   BorderPane flow = new BorderPane();
   Label win = new Label("Rank\t  Score\tName\tMoves\tTime\t    Size");
   Label key = new Label("key");
   public int playerCurrX;
   public int playerCurrY;
   public int count = 0;
   public final String[] DIRECTIONS = {"N", "S", "E", "W"};
   public static int mazeSize;
   public static String pName;
   public final int squareSize = 25;
   Canvas canvas = new Canvas((mazeSize+2)*squareSize,(mazeSize+2)*squareSize);
   public Integer[][] numbers;
   public Integer[][] newNumbers;
   public int endX;
   public int endY;
   public long start;
   public long end;
   
   public void start(Stage stage) throws FileNotFoundException{
      drawSquare();
      flow.getChildren().add(canvas);
      canvas.setOnKeyReleased(new KeyListenerDown());      
      Scene scene = new Scene(flow, ((mazeSize+2)*squareSize), ((mazeSize+2)*squareSize));
      stage.setScene(scene);
      stage.setTitle("Maze Game!");
      stage.show();
      canvas.requestFocus();
      stage.setOnCloseRequest(e -> System.exit(0));
   }   

   public void drawSquare(){
      numbers = new Integer[mazeSize][mazeSize];
      newNumbers = new Integer[mazeSize+2][mazeSize+2];
      GraphicsContext gc = canvas.getGraphicsContext2D();
      Random rand = new Random();
                 
      playerCurrX = rand.nextInt(mazeSize-1);
      playerCurrY = rand.nextInt(mazeSize-1);
      while(playerCurrX%2 != 0 || playerCurrY != 0){
         playerCurrX = rand.nextInt(mazeSize-1);
         playerCurrY = rand.nextInt(mazeSize-1);
      }
      
      numbers[playerCurrX][playerCurrY] = 0;
      for(int i = 0; i < mazeSize; i++){
         for(int j = 0; j < mazeSize; j++){
            if(i%2 != 0 && j%2 != 0){
               numbers[i][j] = 1;
            }
         } 
      }     
            
      List<Integer> list = new ArrayList<Integer>();
      for(int i = 0; i < mazeSize; i++){
         for(int j = 0; j < mazeSize; j++){
            list.add(numbers[i][j]);
         }
      }
      
      while(list.contains(null)){
         list = new ArrayList<Integer>();
         for(int i = 0; i < mazeSize; i++){
            for(int j = 0; j < mazeSize; j++){
               list.add(numbers[i][j]);
            }
         }
         int randomIndex = (int)(Math.random()*DIRECTIONS.length);
         if(DIRECTIONS[randomIndex] == "N" && playerCurrX - 2 >= 0){
            playerCurrX-=2;
            if(numbers[playerCurrX][playerCurrY] == null){
               numbers[playerCurrX][playerCurrY] = 0;
               numbers[playerCurrX+1][playerCurrY] = 2;
            }
            else if(numbers[playerCurrX][playerCurrY] == 0 && numbers[playerCurrX+1][playerCurrY] == null){
               numbers[playerCurrX+1][playerCurrY] = 1;
            }
            else if(numbers[playerCurrX][playerCurrY] == 1){
               playerCurrX+=2;
            }
         }
         
         else if(DIRECTIONS[randomIndex] == "S" && playerCurrX + 2 != mazeSize && playerCurrX + 2 != mazeSize+1){
            playerCurrX+=2;
            if(numbers[playerCurrX][playerCurrY] == null){
               numbers[playerCurrX][playerCurrY] = 0;
               numbers[playerCurrX-1][playerCurrY] = 2;
            }
            else if(numbers[playerCurrX][playerCurrY] == 0 && numbers[playerCurrX-1][playerCurrY] == null){
               numbers[playerCurrX-1][playerCurrY] = 1;
            }
            else if(numbers[playerCurrX][playerCurrY] == 1){
               playerCurrX-=2;
            }
         }
         
         else if(DIRECTIONS[randomIndex] == "E" && playerCurrY + 2 != mazeSize && playerCurrY + 2 != mazeSize+1){
            playerCurrY+=2;
            if(numbers[playerCurrX][playerCurrY] == null){
               numbers[playerCurrX][playerCurrY] = 0;
               numbers[playerCurrX][playerCurrY-1] = 2;
            }
            else if(numbers[playerCurrX][playerCurrY] == 0 && numbers[playerCurrX][playerCurrY-1] == null){
               numbers[playerCurrX][playerCurrY-1] = 1;
            }
            else if(numbers[playerCurrX][playerCurrY] == 1){
               playerCurrY-=2;
            }
         }
         
         else if(DIRECTIONS[randomIndex] == "W" && playerCurrY - 2 != -1 && playerCurrY - 2 != -2){
            playerCurrY-=2;
            if(numbers[playerCurrX][playerCurrY] == null){
               numbers[playerCurrX][playerCurrY] = 0;
               numbers[playerCurrX][playerCurrY+1] = 2;
            }
            else if(numbers[playerCurrX][playerCurrY] == 0 && numbers[playerCurrX][playerCurrY+1] == null){
               numbers[playerCurrX][playerCurrY+1] = 1;
            }
            else if(numbers[playerCurrX][playerCurrY] == 1){
               playerCurrY+=2;
            }
         }         
      }
      for(int x = 0; x < mazeSize; x++){
         for(int y = 0; y < mazeSize; y++){
            if(numbers[x][y] == 2){
               numbers[x][y] = 0;
            }
         }
      }
      
      for(int x = 0; x < mazeSize+2; x++){
         for(int y = 0; y < mazeSize+2; y++){
            if(x == 0 || x == mazeSize+1 || y == 0 || y == mazeSize+1){
               newNumbers[x][y] = 1;
            }  
            else {
               newNumbers[x][y] = numbers[x-1][y-1];
            }
         }
      }
      
      int startX = 0;
      int startY = rand.nextInt(mazeSize+1);
      playerCurrX = startX;
      playerCurrY = startY;
      
      while(newNumbers[startX+1][startY] == 1 || startY == 0 || startY == mazeSize+1){
         startY = rand.nextInt(mazeSize+1);
      }
      
      endX = mazeSize+1;
      endY = rand.nextInt(mazeSize+1);
      while(newNumbers[endX-1][endY] == 1 || endY == 0 || endY == mazeSize+1){
         endY = rand.nextInt(mazeSize+1);
      }
      
      int keyLocX = rand.nextInt(mazeSize+1);
      int keyLocY = rand.nextInt(mazeSize+1);
      while(newNumbers[keyLocX][keyLocY] == 1 || keyLocX == 0 || keyLocX == mazeSize+1 || keyLocY == 0 || keyLocY == mazeSize+1){
         keyLocX = rand.nextInt(mazeSize-1);
         keyLocY = rand.nextInt(mazeSize-1);
      }
      newNumbers[keyLocX][keyLocY] = 5;
      newNumbers[endX][endY] = 0;
      for(int x = 0; x < mazeSize+2; x++){
         for(int y = 0; y < mazeSize+2; y++){
            if(newNumbers[x][y] == 1){
               gc.setFill(Color.BLACK);
               gc.fillRect(y*squareSize, x*squareSize, squareSize, squareSize);
            }
            else if(newNumbers[x][y] == 0){
              gc.setFill(Color.WHITE);
              gc.fillRect(y*squareSize, x*squareSize, squareSize, squareSize);
            }
               
            if(startX == x && startY == y){
               gc.setFill(Color.AQUA);
               gc.fillRect(y*squareSize, x*squareSize, squareSize, squareSize);
               playerCurrX = x;
               playerCurrY = y;
               count++;
            }
            if(endX == x && endY == y){
               gc.setFill(Color.RED);
               gc.fillRect(y*squareSize, x*squareSize, squareSize, squareSize);
            }
            if(keyLocX == x && keyLocY == y){
               gc.setFill(Color.GOLD);
               gc.fillRect(y*squareSize, x*squareSize, squareSize, squareSize);
               gc.setFill(Color.BLACK);
               gc.fillText("Key", y*squareSize+3, x*squareSize+squareSize-8);
            }
         }
      }
      start = System.nanoTime();
   }
      
public int totalMoves = 0;
boolean keyCollected = false;
// public class KeyListenerDown implements EventHandler<KeyEvent>  
// {
//    public void handle(KeyEvent event)
//    {
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//        KeyCode savedKey = event.getCode();
//        
//        if (event.getCode() == KeyCode.UP) 
//        {
//          if(playerCurrX - 1 != -1){
//             if(newNumbers[playerCurrX - 1][playerCurrY] == 0 && playerCurrX - 1 != -1){
//                gc.setFill(Color.AQUA);
//                gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize - squareSize, squareSize, squareSize);
//                
//                playerCurrX = playerCurrX - 1;
//                gc.setFill(Color.WHITE);
//                gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize + squareSize, squareSize, squareSize);
//                totalMoves+=1;
//             }
//             else if(newNumbers[playerCurrX - 1][playerCurrY] == 5){
//                newNumbers[playerCurrX-1][playerCurrY] = 0;
//                gc.setFill(Color.AQUA);
//                gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize - squareSize, squareSize, squareSize);
//                
//                playerCurrX = playerCurrX - 1;
//                gc.setFill(Color.WHITE);
//                gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize + squareSize, squareSize, squareSize);
//                totalMoves+=1;
//                keyCollected = true;
//                gc.setFill(Color.GREEN);
//                gc.fillRect(endY*squareSize, endX*squareSize, squareSize, squareSize);
//             }
//           }
//        }
//        
//        if (event.getCode() == KeyCode.DOWN){
//          if(playerCurrX + 1 < mazeSize+1){
//             if(newNumbers[playerCurrX + 1][playerCurrY] == 0 && playerCurrX + 1 < mazeSize+1){
//                gc.setFill(Color.AQUA);
//                gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize + squareSize, squareSize, squareSize);
//                
//                playerCurrX = playerCurrX + 1;
//                
//                gc.setFill(Color.WHITE);
//                gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize - squareSize, squareSize, squareSize);
//                totalMoves+=1;
//             }
//             else if(newNumbers[playerCurrX + 1][playerCurrY] == 5 && playerCurrX + 1 < mazeSize+1){
//                newNumbers[playerCurrX+1][playerCurrY] = 0;
//                gc.setFill(Color.AQUA);
//                gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize + squareSize, squareSize, squareSize);
//                
//                playerCurrX = playerCurrX + 1;
//                
//                gc.setFill(Color.WHITE);
//                gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize - squareSize, squareSize, squareSize);
//                totalMoves+=1;
//                keyCollected = true;
//                gc.setFill(Color.GREEN);
//                gc.fillRect(endY*squareSize, endX*squareSize, squareSize, squareSize);
// 
//             }
//          }
//          else if(playerCurrX + 1 == mazeSize+1 && newNumbers[playerCurrX + 1][playerCurrY] == 0 && keyCollected == true){
//             gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
//             flow.getChildren().clear();
//             end = System.nanoTime();
//             int finalTime = (int)((end-start)/(int)1000000000);
//             int playerScore = ((totalMoves / finalTime) * mazeSize);
//             
//             Scores[] scoreArray = new Scores[10];
//             Scores[] hold = new Scores[10];
//             try {
//                File scores = new File("Scores!!!");
//                Scanner in = new Scanner(scores);
//                int iterator = 0;
//                while(in.hasNextLine()){
//                   scoreArray[iterator] = new Scores(in.nextInt(), in.nextInt(), in.next(), in.nextInt(), in.nextInt(), in.nextInt());
//                   iterator++;
//                }
//                int wins = 0;
//                for(int i = 0; i < scoreArray.length; i++){
//                   if(playerScore > scoreArray[i].getScore() && wins < 1){
//                      hold[i] = new Scores(i+1, playerScore, pName, totalMoves, finalTime, mazeSize);
//                      wins++;
//                   }
//                   else if(wins == 1){
//                      hold[i] = new Scores(i+1, scoreArray[i-1].getScore(), scoreArray[i-1].getName(),scoreArray[i-1].getMoves(), scoreArray[i-1].getTime(), scoreArray[i-1].getSize());  
//                   }
//                   else{
//                      hold[i] = scoreArray[i];
//                   }
//                }
//             }
//             catch(FileNotFoundException e){
//                System.out.println("Could not find file 'Scores!!!'");
//             }
//             for(int i = 0; i < scoreArray.length; i++){
//                win.setText(win.getText() + "\n" + hold[i].toString());
//             }
//             flow.setCenter(win);
//             
//             try{
//                File scores = new File("Scores!!!");
//                FileWriter writeScores = new FileWriter(scores);
//                for(int i = 0; i < hold.length; i++){
//                   if(i == 9){
//                      writeScores.write(hold[i].toString());  
//                   }
//                   else{  
//                      writeScores.write(hold[i].toString() + "\n");
//                   }
//                }
//                writeScores.close();
//             }
//             catch(IOException e){
//                System.out.println("Scorebaord file not found!");
//                System.exit(0);
//             }
//          }
//        }
//        
//        if (event.getCode() == KeyCode.LEFT) 
//        {
//          if(newNumbers[playerCurrX][playerCurrY - 1] == 0){
//             gc.setFill(Color.AQUA);
//             gc.fillRect(playerCurrY*squareSize - squareSize, playerCurrX*squareSize, squareSize, squareSize);
//             
//             playerCurrY = playerCurrY - 1;
//             
//             gc.setFill(Color.WHITE);
//             gc.fillRect(playerCurrY*squareSize + squareSize, playerCurrX*squareSize, squareSize, squareSize);
//             totalMoves+=1;
//          }
//          else if(newNumbers[playerCurrX][playerCurrY - 1] == 5){
//             newNumbers[playerCurrX][playerCurrY - 1] = 0;
//             gc.setFill(Color.AQUA);
//             gc.fillRect(playerCurrY*squareSize - squareSize, playerCurrX*squareSize, squareSize, squareSize);
//             
//             playerCurrY = playerCurrY - 1;
//             
//             gc.setFill(Color.WHITE);
//             gc.fillRect(playerCurrY*squareSize + squareSize, playerCurrX*squareSize, squareSize, squareSize);            
//             totalMoves+=1;
//             keyCollected = true;
//             gc.setFill(Color.GREEN);
//             gc.fillRect(endY*squareSize, endX*squareSize, squareSize, squareSize);
//          }
//        }
//        
//        if (event.getCode() == KeyCode.RIGHT) 
//        {
//          if(newNumbers[playerCurrX][playerCurrY + 1] == 0){
//             gc.setFill(Color.AQUA);
//             gc.fillRect(playerCurrY*squareSize + squareSize, playerCurrX*squareSize, squareSize, squareSize);
//             
//             playerCurrY = playerCurrY + 1;
//             
//             gc.setFill(Color.WHITE);
//             gc.fillRect(playerCurrY*squareSize - squareSize, playerCurrX*squareSize, squareSize, squareSize);
//             totalMoves+=1;
//          }
//          else if(newNumbers[playerCurrX][playerCurrY + 1] == 5){
//             newNumbers[playerCurrX][playerCurrY+1] = 0;
//             gc.setFill(Color.AQUA);
//             gc.fillRect(playerCurrY*squareSize + squareSize, playerCurrX*squareSize, squareSize, squareSize);
//             
//             playerCurrY = playerCurrY + 1;
//             
//             gc.setFill(Color.WHITE);
//             gc.fillRect(playerCurrY*squareSize - squareSize, playerCurrX*squareSize, squareSize, squareSize);           
//             totalMoves+=1;
//             keyCollected = true;
//             gc.setFill(Color.GREEN);
//             gc.fillRect(endY*squareSize, endX*squareSize, squareSize, squareSize);
//          }
//        }
//    }
// }
     
      
public static void main(String[] args){
      popUps.mazeSizePop();
      popUps.namePop();
      launch(args);		
	}
}