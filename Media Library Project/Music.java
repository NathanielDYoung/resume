/*
import the used List and ArrayList collections

Music is a subclass of Meida that has a constructor Music that sends format, isbn, genre to Media class
initialised class variables artist title and year
provide getters for class variables

Overrides getType method to return Music

toString prints the Musics Artist, title and year, as well as excess info from Media's toString method

compareTo method returns positive negative or 0 value, 
returns lower if other is not Music since music is always at the end
if other is also music, then compares artist year and title respectively to determine order 
**/
public abstract class Music extends Media{
   private String artist;
   private String title;
   private int year;
   
   public Music(Format format, String isbn, String genre, String artist, String title, int year){
      super(format, isbn, genre);
      this.artist = artist;
      this.title = title;
      this.year = year;
   }
   
   public String getArtist(){
      return artist;
   }
   public String getTitle()
   {
      return title;
   }
   public int getYear(){
      return year;
   }
   public String toString(){
      return "Artist: " + getArtist() + ", Year: " + getYear() + ", Title: " + getTitle() + ", " + super.toString(); 
   }
   
  public int compareTo(Media other){
      if(other instanceof Music){
         if(this.artist.compareTo(((Music) other).getArtist()) != 0){
            return this.artist.compareTo(((Music) other).getArtist());
         }
         else if(this.year != ((Music) other).getYear()){            
            return this.year - ((Music) other).getYear();
         }
         else{   
            return this.title.compareTo(((Music) other).getTitle());
         }
      }
      else{
         return 1;
      }

   }
}