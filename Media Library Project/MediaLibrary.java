/**
import used classes List, ArrayList and collections

create List of Labels, Labels are in format String(T), Media(U), call list library, and initialize in MediaLibrary constructor

add method throws MediaAlreadyInLibraryException if necessary
add method takes in a string and a name, and creates a label based off passed variables
add method checks to make sure there's no labels with same string, media, or ISBN, and throws error if false
if error is not thrown, label is added to library

filter method returns new List which only has search filters search term in the toString or in the string part of Label

sort method calls single line Collections.sort to easily use compareTo methods in subclasses of Media 
**/
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
public class MediaLibrary{
   List<Label<String, Media>> library;
   
   public MediaLibrary(){
      this.library = new ArrayList<>();
   }
   
   public void add(String name, Media media) throws MediaAlreadyInLibrary{
      Label<String, Media> l = new Label<>(name, media);
      int numMatches = 0;
      if(library.size() != 0){
         for(int i = 0; i < library.size(); i++){
            if(library.get(i).equals(l)){
               numMatches++;
               throw new MediaAlreadyInLibrary(name, media);
            }
         }
         if(numMatches == 0){
            library.add(l);
         }
      }
      else{
         library.add(l);
      }
   }
   public List<Label<String, Media>> filter(MediaFilter mediaFilter){
      List<Label<String, Media>> filteredLib = new ArrayList<>();
      for(int i = 0; i < library.size(); i++){
         if(mediaFilter.matches(library.get(i)) == true){
            filteredLib.add(library.get(i));   
         }   
      }
      return filteredLib;
   }
   
   public void sort(){
      Collections.sort(library);
   }
}