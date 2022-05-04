/**
interface with abstract matches method, implemented in searchfilter
**/
public interface MediaFilter{
   public boolean matches(Label<String, Media> other);
}