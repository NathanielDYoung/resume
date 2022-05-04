/*
import the used List and ArrayList collections

Album is a subclass of Music that has a constructor Album that sends format, isbn, genre, artist, title 
and year back to super class (Music), and initializes trackList to new array

Overrides getType method to return Album

getter for trackList

toString prints the Music's toString method first, and then adds the tracklist to the end
**/
import java.util.List;
import java.util.ArrayList;

public class Album extends Music{
   private List<String> trackList = new ArrayList<>();
   
   public Album(Format format, String isbn, String genre, String artist, String title, int year, List<String> trackList){
      super(format, isbn, genre, artist, title, year);
      this.trackList.addAll(trackList);
   }
   
   public String getType(){
      return "Album";
   }
   
   public List<String> getTrackList(){
      return trackList;
   }
   
   public String toString(){
      return super.toString() + ", Tracks: " + getTrackList();
   }
}