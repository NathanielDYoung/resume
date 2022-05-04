/*
Generic Label method uses T and U, U is comparable to other variables of type U, and labels are comparable to other labels

declare class variables key and value as types T and U respectively, and supply getter methods for them

Label constructor sets key and value

override equals method to check if key or value are equal to eachother

compareTo method compares two label objects and returns positive, negative or 0 based off similarity
**/
public class Label<T, U extends Comparable<U>> implements Comparable<Label<T, U>>{
   private T key;
   private U value;
   
   public T getKey(){
      return key;
   }
   public U getValue(){
      return value;
   }
   
   public Label(T key, U value){
      this.key = key;
      this.value = value;
   }
   
   @Override
   public boolean equals(Object obj){
      if(!(obj instanceof Label)){
          return false;        
      }
      else{
         if((this.value.equals(((Label) obj).getValue()))){
            return true;
         }
         else{
            return false;
         }
      }
   }
   
   public int compareTo(Label<T, U> other){ 
      return this.value.compareTo(other.getValue());
   }
}