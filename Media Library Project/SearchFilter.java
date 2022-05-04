/**
implements mediaFilter interface

class variable searchTerm is initialized in SearchFilter constructor

matches method overrides abstract matches, takes in label and compares key and value of label to check for searchTerm

returns true if searchterm is found, and false if not
**/
public class SearchFilter implements MediaFilter{
   private String searchTerm;
   
   public SearchFilter(String searchTerm){
      this.searchTerm = searchTerm;
   }
   
   public boolean matches(Label<String, Media> other){
      if(other.getKey().indexOf(searchTerm) >= 0){
         return true;
      }
      else if(other.getValue().toString().indexOf(searchTerm) >= 0){
         return true;
      }
      else{
         return false;
      }
   }
}