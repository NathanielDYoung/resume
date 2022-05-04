/**
Custom exception method returns Media already in library when thrown, and memorizes name and media of duplicate item
**/
public class MediaAlreadyInLibrary extends Exception{
   private String name;
   private Media media;
   
   public MediaAlreadyInLibrary(String name, Media media){
      super("Media already in library.");
      this.name = name;
      this.media = media;
   }
   
   public String getName(){
      return name;
   }
   
   public Media getMedia(){
      return media;
   }
}