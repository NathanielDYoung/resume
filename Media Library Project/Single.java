/**
subclass of music sends format isbn genre artist, title and year to Music method

overrides getType to return single
**/
public class Single extends Music{
   public Single(Format format, String isbn, String genre, String artist, String title, int year){
      super(format, isbn, genre, artist, title, year);
   }
   
   public String getType(){
      return "Single";
   }
}