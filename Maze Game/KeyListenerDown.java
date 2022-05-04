public class KeyListenerDown extends MazeExtended implements EventHandler<KeyEvent>  
{
   public void handle(KeyEvent event)
   {
       GraphicsContext gc = canvas.getGraphicsContext2D();
       KeyCode savedKey = event.getCode();
       
       if (event.getCode() == KeyCode.UP) 
       {
         if(playerCurrX - 1 != -1){
            if(newNumbers[playerCurrX - 1][playerCurrY] == 0 && playerCurrX - 1 != -1){
               gc.setFill(Color.AQUA);
               gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize - squareSize, squareSize, squareSize);
               
               playerCurrX = playerCurrX - 1;
               gc.setFill(Color.WHITE);
               gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize + squareSize, squareSize, squareSize);
               totalMoves+=1;
            }
            else if(newNumbers[playerCurrX - 1][playerCurrY] == 5){
               newNumbers[playerCurrX-1][playerCurrY] = 0;
               gc.setFill(Color.AQUA);
               gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize - squareSize, squareSize, squareSize);
               
               playerCurrX = playerCurrX - 1;
               gc.setFill(Color.WHITE);
               gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize + squareSize, squareSize, squareSize);
               totalMoves+=1;
               keyCollected = true;
               gc.setFill(Color.GREEN);
               gc.fillRect(endY*squareSize, endX*squareSize, squareSize, squareSize);
            }
          }
       }
       
       if (event.getCode() == KeyCode.DOWN){
         if(playerCurrX + 1 < mazeSize+1){
            if(newNumbers[playerCurrX + 1][playerCurrY] == 0 && playerCurrX + 1 < mazeSize+1){
               gc.setFill(Color.AQUA);
               gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize + squareSize, squareSize, squareSize);
               
               playerCurrX = playerCurrX + 1;
               
               gc.setFill(Color.WHITE);
               gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize - squareSize, squareSize, squareSize);
               totalMoves+=1;
            }
            else if(newNumbers[playerCurrX + 1][playerCurrY] == 5 && playerCurrX + 1 < mazeSize+1){
               newNumbers[playerCurrX+1][playerCurrY] = 0;
               gc.setFill(Color.AQUA);
               gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize + squareSize, squareSize, squareSize);
               
               playerCurrX = playerCurrX + 1;
               
               gc.setFill(Color.WHITE);
               gc.fillRect(playerCurrY*squareSize, playerCurrX*squareSize - squareSize, squareSize, squareSize);
               totalMoves+=1;
               keyCollected = true;
               gc.setFill(Color.GREEN);
               gc.fillRect(endY*squareSize, endX*squareSize, squareSize, squareSize);

            }
         }
         else if(playerCurrX + 1 == mazeSize+1 && newNumbers[playerCurrX + 1][playerCurrY] == 0 && keyCollected == true){
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            flow.getChildren().clear();
            end = System.nanoTime();
            int finalTime = (int)((end-start)/(int)1000000000);
            int playerScore = ((totalMoves / finalTime) * mazeSize);
            
            Scores[] scoreArray = new Scores[10];
            Scores[] hold = new Scores[10];
            try {
               File scores = new File("Scores!!!");
               Scanner in = new Scanner(scores);
               int iterator = 0;
               while(in.hasNextLine()){
                  scoreArray[iterator] = new Scores(in.nextInt(), in.nextInt(), in.next(), in.nextInt(), in.nextInt(), in.nextInt());
                  iterator++;
               }
               int wins = 0;
               for(int i = 0; i < scoreArray.length; i++){
                  if(playerScore > scoreArray[i].getScore() && wins < 1){
                     hold[i] = new Scores(i+1, playerScore, pName, totalMoves, finalTime, mazeSize);
                     wins++;
                  }
                  else if(wins == 1){
                     hold[i] = new Scores(i+1, scoreArray[i-1].getScore(), scoreArray[i-1].getName(),scoreArray[i-1].getMoves(), scoreArray[i-1].getTime(), scoreArray[i-1].getSize());  
                  }
                  else{
                     hold[i] = scoreArray[i];
                  }
               }
            }
            catch(FileNotFoundException e){
               System.out.println("Could not find file 'Scores!!!'");
            }
            for(int i = 0; i < scoreArray.length; i++){
               win.setText(win.getText() + "\n" + hold[i].toString());
            }
            flow.setCenter(win);
            
            try{
               File scores = new File("Scores!!!");
               FileWriter writeScores = new FileWriter(scores);
               for(int i = 0; i < hold.length; i++){
                  if(i == 9){
                     writeScores.write(hold[i].toString());  
                  }
                  else{  
                     writeScores.write(hold[i].toString() + "\n");
                  }
               }
               writeScores.close();
            }
            catch(IOException e){
               System.out.println("Scorebaord file not found!");
               System.exit(0);
            }
         }
       }
       
       if (event.getCode() == KeyCode.LEFT) 
       {
         if(newNumbers[playerCurrX][playerCurrY - 1] == 0){
            gc.setFill(Color.AQUA);
            gc.fillRect(playerCurrY*squareSize - squareSize, playerCurrX*squareSize, squareSize, squareSize);
            
            playerCurrY = playerCurrY - 1;
            
            gc.setFill(Color.WHITE);
            gc.fillRect(playerCurrY*squareSize + squareSize, playerCurrX*squareSize, squareSize, squareSize);
            totalMoves+=1;
         }
         else if(newNumbers[playerCurrX][playerCurrY - 1] == 5){
            newNumbers[playerCurrX][playerCurrY - 1] = 0;
            gc.setFill(Color.AQUA);
            gc.fillRect(playerCurrY*squareSize - squareSize, playerCurrX*squareSize, squareSize, squareSize);
            
            playerCurrY = playerCurrY - 1;
            
            gc.setFill(Color.WHITE);
            gc.fillRect(playerCurrY*squareSize + squareSize, playerCurrX*squareSize, squareSize, squareSize);            
            totalMoves+=1;
            keyCollected = true;
            gc.setFill(Color.GREEN);
            gc.fillRect(endY*squareSize, endX*squareSize, squareSize, squareSize);
         }
       }
       
       if (event.getCode() == KeyCode.RIGHT) 
       {
         if(newNumbers[playerCurrX][playerCurrY + 1] == 0){
            gc.setFill(Color.AQUA);
            gc.fillRect(playerCurrY*squareSize + squareSize, playerCurrX*squareSize, squareSize, squareSize);
            
            playerCurrY = playerCurrY + 1;
            
            gc.setFill(Color.WHITE);
            gc.fillRect(playerCurrY*squareSize - squareSize, playerCurrX*squareSize, squareSize, squareSize);
            totalMoves+=1;
         }
         else if(newNumbers[playerCurrX][playerCurrY + 1] == 5){
            newNumbers[playerCurrX][playerCurrY+1] = 0;
            gc.setFill(Color.AQUA);
            gc.fillRect(playerCurrY*squareSize + squareSize, playerCurrX*squareSize, squareSize, squareSize);
            
            playerCurrY = playerCurrY + 1;
            
            gc.setFill(Color.WHITE);
            gc.fillRect(playerCurrY*squareSize - squareSize, playerCurrX*squareSize, squareSize, squareSize);           
            totalMoves+=1;
            keyCollected = true;
            gc.setFill(Color.GREEN);
            gc.fillRect(endY*squareSize, endX*squareSize, squareSize, squareSize);
         }
       }
   }
}