/*
implement Comparable interface to allow Media to be compared to other Media objects

create private class variables isbn, genre and format, and initialize them in the Media constructor
Create getter methods for class variables

create abstract method getType

equals method compares isbn's of two media objects to confirm they are not equal

toString prints type, isbn genre and format
**/
public abstract class Media implements Comparable<Media>{
   private String isbn;
   private String genre;
   private Format format;
   
   public Media(Format format, String isbn, String genre){
      this.format = format;
      this.isbn = isbn;
      this.genre = genre;
   }
   public abstract String getType();
   
   final public Format getFormat(){
      return format;
   }
   
   final public String getIsbn(){
      return isbn;
   }
   
   final public String getGenre(){
      return genre;
   }  
   
   public boolean equals(Object obj){
      if(obj instanceof Media){
         return ((Media) obj).getIsbn().equals(isbn);
      }
      else{
         return false;
      }
   }
   
   public String toString(){
      return "Type: " + getType() + ", ISBN: " + getIsbn() + ", Genre: " + getGenre() + ", Format: " + getFormat();
   }
}