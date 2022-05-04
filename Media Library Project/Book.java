/*
declare private variables title author and publisher and initialize them in Book constructor
which also send format, isbn and genre to parent class Media

override getType method to return Book

getter methods for title author and publisher

toString method prints title and author, along with other relevant information from Media's toString method

compareTo method checks to make sure passed object is also Book. If not, the book always come first and returns a -1,
otherwise compare by author and title
**/
public class Book extends Media{
   private String title;
   private String author;
   private String publisher;
   
   public Book(Format format, String isbn, String genre, String author, String title, String publisher){
      super(format, isbn, genre);
      this.title = title;
      this.author = author;
      this.publisher = publisher;
   }
   
   public String getType(){
      return "Book";
   }
   
   public String getTitle(){
      return title;
   }
   public String getAuthor(){
      return author;
   }
   public String getPublisher(){
      return publisher;
   }
   
   public String toString(){
      return "Title: " + getTitle() + ", Author: " + getAuthor() + ", " + super.toString();
   }
   
   public int compareTo(Media other){
      if(other instanceof Book){
         if(this.author.compareTo(((Book) other).getAuthor()) != 0){
            return this.author.compareTo(((Book) other).getAuthor());
         }
         else {
            return this.title.compareTo(((Book) other).getTitle());
         }
      }
      else{
         return -1;
      }
   }
}