public class Scores{
   
   int rank = 0;
   int score = 0;
   String name = "";
   int moves = 0;
   int time = 0;
   int size = 0;
   public Scores(int rank, int score, String name, int moves, int time, int size){
      this.rank = rank;
      this.score = score;
      this.name = name;
      this.moves = moves;
      this.time = time;
      this.size = size;
   }
   
   public void setRank(int rank){
      this.rank = rank;
   }
   public void setScore(int score){
      this.score = score;
   }
   public void setName(String name){
      this.name = name;
   }
   public void setMoves(int moves){
      this.moves = moves;
   }
   public void setTime(int time){
      this.time = time;
   }
   public void setSize(int size){
      this.size = size;
   }
   
   public int getRank(){
      return rank;
   }
   public int getScore(){
      return score;
   }
   public String getName(){
      return name;
   }
   public int getMoves(){
      return moves;
   }
   public int getTime(){
      return time;
   }
   public int getSize(){
      return size;
   }
   
   
   public String toString(){
      return rank + "\t    " + score + "\t\t" + name + "\t\t" + moves + "\t         " + time + "        " + size;
   }
}