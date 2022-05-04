import javax.swing.*;
public class popUps extends MazeExtended{

   public static final JFrame bigParent = new JFrame();
   
   public static int mazeSizePop(){
      mazeSize = 0;
      while(mazeSize%2 == 0 || mazeSize == 1){
         String size = JOptionPane.showInputDialog(bigParent, "Enter a Maze Size greater than one (must be an odd number): ", "Size Pop-Up!", JOptionPane.PLAIN_MESSAGE);
         if(size == null){
            System.exit(0);
         }
         else if(size.length() > 0){
            try{
               mazeSize = Integer.parseInt(size);
            }
            catch(Exception e){
               System.out.println("Invalid Input! Expected: Integer, Received: String...");
            }
         }       
      }
      return mazeSize;
   }
   
   public static String namePop(){
      pName = "";
      while(pName.length() != 3){
         String name = JOptionPane.showInputDialog(bigParent, "Enter Initals (must be 3 letters): ", "Name Pop-Up!", JOptionPane.PLAIN_MESSAGE);
         if(name == null){
            mazeSizePop();
         }
         else if(name.length() >= 0){
            pName = name;
         }  
      }
      return pName;
   }
}