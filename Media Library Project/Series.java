/*
import the used List and ArrayList collections

Series is a subclass of Meida that has a constructor Series that sends format, isbn, genre to Media class
initialised class variables title cast and episodes
provide getters for class variables

Overrides getType method to return Series

toString prints the Series title as well as excess info from Media's toString method

compareTo method returns positive negative or 0 value, returns lower if other is Movie or Book, returns higher
if other is Music
**/

import java.util.List;
import java.util.ArrayList;
public class Series extends Media{
   private String title;
   private List<String> cast = new ArrayList<>();
   private List<String> episodes = new ArrayList<>();
   
   public Series(Format format, String isbn, String genre, String title, List<String> cast, List<String> episodes){
      super(format, isbn, genre);
      this.title = title;
      this.cast.addAll(cast);
      this.episodes.addAll(episodes);
   }
   
   public String getTitle(){
      return title;   
   }
   public String getType(){
      return "Series";
   }
   
   public String toString(){
      return "Title: " + getTitle() + ", " + super.toString();
   }
   
   public int compareTo(Media other){   
      if(other instanceof Series){
         return this.title.compareTo(((Series) other).getTitle());
      }
      else if(other instanceof Movie || other instanceof Book){
         return 1;
      }
      else {
         return -1;
      }
   }
}