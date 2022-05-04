/*
import the used List and ArrayList collections

Movie is a subclass of Meida that has a constructor Movie that sends format, isbn, genre to Media class
initialised class variables title string director year and cast (list)
provide getters for class variables

Overrides getType method to return Movie

getter for trackList

toString prints the Movies title and year, as well as excess info from Media's toString method

compareTo method returns positive negative or 0 value, returns lower if other is book, to be ordered second behind books
returns higher if other is not movie or book
if other is also movie, then compares title and year to determine order 
**/
import java.util.List;
import java.util.ArrayList;
public class Movie extends Media{
   private String title;
   private String director;
   private int year;
   private List<String> cast = new ArrayList<>();
   
   public Movie(Format format, String isbn, String genre, String title, String director, int year, List<String> cast){
      super(format, isbn, genre);
      this.title = title;
      this.director = director;
      this.year = year;
      this.cast.addAll(cast);
   }
   
   public String getType(){
      return "Movie";
   }
   
   public String getTitle(){
      return title;
   }
   
   public int getYear(){
      return year;
   }
   
   public String toString(){
      return "Title: " + getTitle() + ", Year: " + getYear() + ", " + super.toString();
   }
   
   public int compareTo(Media other){
      if(other instanceof Movie){
         if(this.title.compareTo(((Movie) other).getTitle()) != 0){
            return this.title.compareTo(((Movie) other).getTitle());
         }
         else{
            return this.year - ((Movie) other).getYear();
         }
      }
      else if(other instanceof Book){
         return 1;
      }
      else{
         return -1;
      }
   }
}