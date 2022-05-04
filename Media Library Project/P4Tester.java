// P4Tester.java
/** Example of using unit tests for this assignment.  To run them on the command line, make
 * sure that the junit-cs211.jar is in the same directory.
 *
 * On Mac/Linux:
 *  javac -cp .:junit-cs211.jar *.java         # compile everything
 *  java -cp .:junit-cs211.jar P4Tester        # run tests
 *
 * On windows replace colons with semicolons: (: with ;)
 *  demo$ javac -cp .;junit-cs211.jar *.java   # compile everything
 *  demo$ java -cp .;junit-cs211.jar P4Tester  # run tests
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;

public class P4Tester{
	public static void main(String[] args){
		org.junit.runner.JUnitCore.main("P4Tester");
	}

	@Test(timeout=1000)
	public void test_mediaIsAbstract(){
		Class<?> c = null; //Media.class;
		try{
			c = Class.forName("Media");
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("Media class not found", c != null);
		assertTrue("class Media should be abstract",Modifier.isAbstract(c.getModifiers()));
	}
	@Test(timeout=1000)
	public void test_mediaComparable(){
		Class<?> c = null;
		try{
			c = Class.forName("Media");
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("Media class not found", c != null);
		boolean foundComparableIface = false;
		boolean parameterizedCorrectly = false;
		for(Type t : c.getGenericInterfaces()){
			if(t instanceof ParameterizedType){
				ParameterizedType pt = (ParameterizedType)t;
				if(pt.getRawType().equals(Comparable.class)){
					foundComparableIface = true;
					for(Type st : pt.getActualTypeArguments()){
						if(st.equals(c)){
							parameterizedCorrectly = true;
							break;
						}
					}
				}
			}
		}
		assertTrue("Media class does not implement Comparable",foundComparableIface);
		assertTrue("Media class does not specify generic type for Comparable interface correctly", parameterizedCorrectly);
	}
	public static void checkField(String cName, String fieldName, String fieldTypeName, String getterName){
		Class<?> fieldType = null;
		try{
			fieldType = Class.forName(fieldTypeName);
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("Class "+fieldTypeName+" not found", fieldType != null);
		checkField(cName, fieldName, fieldType, getterName);
	}
	public static void checkField(String cName, String fieldName, Class<?> fieldType, String getterName){
		Class<?> c = null;
		try{
			c = Class.forName(cName);
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("Class "+cName+" not found", c!=null);
		checkField(c, fieldName, fieldType, getterName);
	}
	public static void checkField(Class<?> c, String fieldName, Class<?> fieldType, String getterName){
		Field f = null;
		try{
			f = c.getDeclaredField(fieldName);
		} catch(NoSuchFieldException nsfe){	}
		assertTrue("field "+fieldName+" does not exist in class "+c,f != null);
		assertTrue("field "+fieldName+" is not private in class "+c,Modifier.isPrivate(f.getModifiers()));
		assertTrue("field "+fieldName+" does not have the correct type (expected: "+fieldType+", found:"+f.getType()+") in class "+c,fieldType.equals(f.getType()));
		if(getterName != null){
			Method m = null;
			try{
				m = c.getDeclaredMethod(getterName);
			} catch(NoSuchMethodException nsme) { }
			assertTrue("No getter with name \""+getterName+"\" in class "+c,m !=null);
			assertTrue("getter \""+getterName+"\" in class "+c+" is not public", Modifier.isPublic(m.getModifiers()));
		}
	}
	@Test(timeout=1000)
	public void test_mediaFields(){
		checkField("Media", "isbn", "java.lang.String", null);
		checkField("Media", "genre", "java.lang.String", "getGenre");
		checkField("Media", "format", "Format", "getFormat");
	}

	@Test(timeout=1000)
	public void test_mediaGetType(){
		Method m = null;
		try{
			m = Class.forName("Media").getDeclaredMethod("getType");
		} catch(NoSuchMethodException nsme){ } catch(ClassNotFoundException cnfe){ }
		assertTrue("getType() not declared in class Media", m != null);
		if(m!=null){
			assertTrue("getType() is not abstract in class Media", Modifier.isAbstract(m.getModifiers()));
		}
	}
	@Test(timeout=1000)
	public void test_mediaConstructor(){
		Constructor<?> c = null;
		try{
			c = Class.forName("Media").getDeclaredConstructor(Class.forName("Format"), String.class, String.class);
		} catch(NoSuchMethodException nsme){ } catch(ClassNotFoundException cnfe){ }
		assertTrue("Media constructor with correct parameters not found", c!=null);
	}

	// Format tests
	@Test(timeout=1000)
	public void test_format(){
		Format fmt;
		fmt = Format.AUDIOBOOK;
		fmt = Format.BLURAY;
		fmt = Format.CD;
		fmt = Format.DOWNLOAD;
		fmt = Format.DVD;
		fmt = Format.HARDBACK;
		fmt = Format.LASERDISC;
		fmt = Format.PAPERBACK;
		fmt = Format.TAPE;
		fmt = Format.VHS;
		fmt = Format.VINYL;
	}

	// Music tests
	@Test(timeout=1000)
	public void test_musicIsAbstract(){
		Class<?> c = null;
		try{
			c = Class.forName("Music");
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("Music class not found", c != null);
		assertTrue("Music class should be abstract", Modifier.isAbstract(c.getModifiers()));
	}
	@Test(timeout=1000)
	public void test_musicFields(){
		checkField("Music", "artist", "java.lang.String", "getArtist");
		checkField("Music", "title", "java.lang.String", "getTitle");
		checkField("Music", "year", int.class, "getYear");
	}
	@Test(timeout=1000)
	public void test_musicConstructor(){
		Constructor<?> c = null;
		Class<?> cMusic = null;
		Class<?> cFormat = null;
		try{
			cMusic = Class.forName("Music");
			cFormat = Class.forName("Format");
			c = cMusic.getDeclaredConstructor(cFormat, String.class, String.class, String.class, String.class, int.class);
		} catch(NoSuchMethodException nsme){ } catch(ClassNotFoundException cnfe){ }
		assertTrue("Music class not found", cMusic != null);
		assertTrue("Format class not found", cFormat != null);
		assertTrue("Music constructor with correct parameters not found", c != null);
	}
	@Test(timeout=1000)
	public void test_musicInherit(){
		Class<?> c = null;
		try{
			c = Class.forName("Music");
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("Music class not found", c!=null);
		Class<?> cMed = null;
		try{
			cMed = Class.forName("Media");
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("Media class not found", cMed != null);
		boolean isSuper = (cMed == c.getSuperclass());
		assertTrue("Media is not a superclass of Music",isSuper);
	}

	//Album tests
	@Test(timeout=1000)
	public void test_album(){
		ArrayList<String> tl = new ArrayList<String>();
		tl.add("Track 1"); tl.add("Track 2"); tl.add("Track 3"); 
		Album a = new Album(Format.VINYL, "123-4-56-789012-3", "Unknown", "Unknown", "Untitled", 2021,tl);
		assertTrue("Album should be an instanceof Music",a instanceof Music);
		assertEquals("getType() in Album incorrect","Album",a.getType());
		String strRep = "Artist: Unknown, Year: 2021, Title: Untitled, Type: Album, ISBN: 123-4-56-789012-3, Genre: Unknown, Format: "+Format.VINYL;
		strRep = strRep+", Tracks: "+tl;
		assertEquals("Incorrect toString() for Album",strRep,a.toString());
	}

	//Single tests
	@Test(timeout=1000)
	public void test_single(){
		Single s = new Single(Format.CD, "098-7-65-432098-7", "Not known", "Anonymous", "Self-titled", 2005);
		assertTrue("Single should be an instanceof Music", s instanceof Music);
		assertEquals("getType() in Single incorrect", "Single", s.getType());
		String strRep = "Artist: Anonymous, Year: 2005, Title: Self-titled, Type: Single, ISBN: 098-7-65-432098-7, Genre: Not known, Format: "+Format.CD;
		assertEquals("Incorrect toString() for Single",strRep, s.toString());
	}

	//Movie tests
	@Test(timeout=1000)
	public void test_movie(){
		Format f = Format.DVD;
		String isbn = "234-5-67-890123-4";
		String genre = "avant-garde";
		String title = "inconnu";
		String dir = "Anonyme";
		int y = 1999;
		List<String> cast = new ArrayList<String>();
		cast.add("Actor 1");
		cast.add("Actor 2");
		cast.add("Actor 3");
		Movie m = new Movie(f,isbn,genre,title,dir,y,cast);
		assertTrue("Movie should be an instanceof Media", m instanceof Media);
		assertEquals("getType() in Movie incorrect", "Movie", m.getType());
		String strRep = "Title: "+title+", Year: "+y+", "+"Type: Movie, ISBN: "+isbn+", Genre: "+genre+", Format: "+f;
		assertEquals("Incorrect toString() for Movie",strRep, m.toString());
	}

	//Series tests
	@Test(timeout=1000)
	public void test_series(){
		Format f = Format.BLURAY;
		String isbn = "345-6-78-901234-5";
		String genre = "sitcom";
		String title = "That One Show";
		List<String> cast = new ArrayList<String>();
		cast.add("Actor 4");
		cast.add("Actor 5");
		cast.add("Actor 6");
		List<String> eps = new ArrayList<String>();
		eps.add("Episode 1");
		eps.add("Episode 2");
		eps.add("Episode 3");
		Series s = new Series(f,isbn,genre,title,cast,eps);
		assertTrue("Series should be an instanceof Media", s instanceof Media);
		assertEquals("getType() in Series incorrect", "Series", s.getType());
		String strRep = "Title: "+title+", Type: Series, ISBN: "+isbn+", Genre: "+genre+", Format: "+f;
		assertEquals("Incorrect toString() for Series", strRep, s.toString());
	}

	//Book tests
	@Test(timeout=1000)
	public void test_book(){
		Format f = Format.HARDBACK;
		String isbn = "456-7-89-012345-6";
		String genre = "Horror";
		String author = "John Smith";
		String title = "Not titled";
		String publisher = "Penguin Books";
		Book b = new Book(f,isbn,genre,author,title,publisher);
		assertTrue("Book should be an instanceof Media", b instanceof Media);
		assertEquals("getType() in Book incorrect", "Book", b.getType());
		String strRep = "Title: "+title+", Author: "+author+", Type: Book, ISBN: "+isbn+", Genre: "+genre+", Format: "+f;
		assertEquals("Incorrect toString() for Book", strRep, b.toString());
	}

	//Label tests
	@Test(timeout=1000)
	public void test_label(){
		Class<?> c = null;
		try{
			c = Class.forName("Label");
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("No class named Label found", c != null);
		boolean foundComparableIface = false;
		boolean comparableToLabel = false;
		for(Type t : c.getGenericInterfaces()){
			if(t instanceof ParameterizedType){
				ParameterizedType pt = (ParameterizedType)t;
				if(pt.getRawType().equals(Comparable.class)){
					foundComparableIface = true;
					for(Type st : pt.getActualTypeArguments()){
						if(st instanceof ParameterizedType){
							if(((ParameterizedType)st).getRawType().equals(c)){
								comparableToLabel = true;
								break;
							}
						}
					}
				}
			}
		}
		assertTrue("Label class does not implement Comparable",foundComparableIface);
		assertTrue("Label class does not specify generic type for Comparable correctly", comparableToLabel);
		boolean numGenericTypesCorrect = false;
		boolean firstTypeUpperBoundCorrect = false;
		boolean secondTypeUpperBoundIsComparable = false;
		boolean secondTypeUpperBoundComparableGeneric = false;
		TypeVariable<?>[] genericTypes = c.getTypeParameters();
		if(genericTypes.length == 2){
			numGenericTypesCorrect = true;
			if(genericTypes[0].getBounds().length > 0 && genericTypes[0].getBounds()[0].equals(Object.class)){
				firstTypeUpperBoundCorrect = true;
			}
			if(genericTypes[1].getBounds().length > 0){
				if(genericTypes[1].getBounds()[0] instanceof ParameterizedType){
					ParameterizedType pt2 = (ParameterizedType)genericTypes[1].getBounds()[0];
					if(pt2.getRawType().equals(Comparable.class)){
						secondTypeUpperBoundIsComparable = true;
						Type[] typeArgs = pt2.getActualTypeArguments();
						// System.out.println("+: "+pt2);
						for(Type t : typeArgs){
							if(t.equals(genericTypes[1])){
								secondTypeUpperBoundComparableGeneric = true;
								break;
							}
							// System.out.println("-: "+t+"=="+genericTypes[1]+"? "+t.equals(genericTypes[1]));
						}
					}
				}
			}
		}
		assertTrue("Label does not specify the correct number (2) of generic types", numGenericTypesCorrect);
		assertTrue("Label's first generic type should have no upper bound (besides Object)", firstTypeUpperBoundCorrect);
		assertTrue("Label's second generic type should have an upper bound of Comparable with correct parameter", secondTypeUpperBoundIsComparable);
		assertTrue("Label's second generic type does not correctly specify Comparable's parameter", secondTypeUpperBoundComparableGeneric);
		Format f = Format.HARDBACK;
		String isbn = "456-7-89-012345-6";
		String genre = "Horror";
		String author = "John Smith";
		String title = "Not titled";
		String publisher = "Penguin Books";
		Book b = new Book(f,isbn,genre,author,title,publisher);
		Label<String,Media> aLabeledMedia = new Label<String, Media>("My favorite book", b);
		Label<String,Media> anotherLabeledMedia = new Label<String, Media>("My second favorite book", b);
		assertEquals("Incorrect equals() for Label", aLabeledMedia, anotherLabeledMedia);
	}

	//MediaFilter tests
	@Test(timeout=1000)
	public void test_mediafilter(){
		Class<?> c = null;
		try{
			c = Class.forName("MediaFilter");
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("MediaFilter interface not found", c != null);
		assertTrue("MediaFilter is not an interface", c.isInterface());
		Method m = null;
		Format f = Format.HARDBACK;
		String isbn = "456-7-89-012345-6";
		String genre = "Horror";
		String author = "John Smith";
		String title = "Not titled";
		String publisher = "Penguin Books";
		Book b = new Book(f,isbn,genre,author,title,publisher);
		try{

			Class<?> c2 = new Label<String, Media>("My favorite book", b).getClass();
			m = c.getMethod("matches", c2);
		} catch(NoSuchMethodException nsme){ }
		assertTrue("MediaFilter's matches method does not exist, or has incorrect arguments", m != null);
	}

	//SearchFilter tests
	@Test(timeout=1000)
	public void test_searchfilter(){
		SearchFilter sf = new SearchFilter("Horror");
		assertTrue("SearchFilter does not implement MediaFilter", sf instanceof MediaFilter);
		Format f = Format.HARDBACK;
		String isbn = "456-7-89-012345-6";
		String genre = "Horror";
		String author = "John Smith";
		String title = "Not titled";
		String publisher = "Penguin Books";
		Book b = new Book(f,isbn,genre,author,title,publisher);
		Label<String,Media> aLabeledMedia = new Label<String, Media>("My favorite book", b);
		assertTrue("SearchFilter's matches() method does not match toString() correctly", sf.matches(aLabeledMedia));
	}
	public void checkLabeledMediaLists(List<Label<String, Media>> expectedList, List<Label<String, Media>> givenList){
		assertEquals("returned list unexpected size",expectedList.size(),givenList.size());
		for(int i=0;i<expectedList.size();i++){
			assertEquals("Unexpected content in returned list at position "+i,expectedList.get(i), givenList.get(i));
		}
	}
	//MediaLibrary tests
	@Test(timeout=1000)
	public void test_medialibrary(){
		MediaLibrary ml = new MediaLibrary();
		//a series
		Format f = Format.BLURAY;
		String isbn = "345-6-78-901234-5";
		String genre = "sitcom";
		String title = "That One Show";
		List<String> cast = new ArrayList<String>();
		cast.add("Actor 4");
		cast.add("Actor 5");
		cast.add("Actor 6");
		List<String> eps = new ArrayList<String>();
		eps.add("Episode 1");
		eps.add("Episode 2");
		eps.add("Episode 3");
		Series series = new Series(f,isbn,genre,title,cast,eps);
		//a single
		Single single = new Single(Format.CD, "098-7-65-432098-7", "Not known", "Anonymous", "Self-titled", 2005);
		//a movie
		f = Format.DVD;
		isbn = "234-5-67-890123-4";
		genre = "avant-garde";
		title = "inconnu";
		String dir = "Anonyme";
		int y = 1999;
		cast = new ArrayList<String>();
		cast.add("Actor 1");
		cast.add("Actor 2");
		cast.add("Actor 3");
		Movie mov = new Movie(f,isbn,genre,title,dir,y,cast);
		//an album
		ArrayList<String> tl = new ArrayList<String>();
		tl.add("Track 1"); tl.add("Track 2"); tl.add("Track 3"); 
		Album alb = new Album(Format.VINYL, "123-4-56-789012-3", "Unknown", "Unknown", "Untitled", 2021,tl);		
		//a book
		f = Format.HARDBACK;
		isbn = "456-7-89-012345-6";
		genre = "Horror";
		String author = "John Smith";
		title = "Not titled";
		String publisher = "Penguin Books";
		Book book = new Book(f,isbn,genre,author,title,publisher);
		try{
			ml.add("My favorite series", series);
			ml.add("My favorite single", single);
			ml.add("My favorite movie", mov);
			ml.add("My favorite album", alb);
			ml.add("My favorite book", book);
		} catch(MediaAlreadyInLibrary mail){
			assertTrue("Threw MediaAlreadyInLibrary unexpectedly", false);
		}
		SearchFilter sf = new SearchFilter("Horror");
		List<Label<String, Media>> filteredList = ml.filter(sf);
		List<Label<String, Media>> expectedList = new ArrayList<Label<String, Media>>();
		expectedList.add(new Label<String, Media>("My favorite book", book));
		checkLabeledMediaLists(expectedList,filteredList);
		List<Label<String, Media>> expectedSortedList = new ArrayList<Label<String, Media>>();
		expectedSortedList.add(new Label<String,Media>("My favorite book", book)); 
		expectedSortedList.add(new Label<String,Media>("My favorite movie", mov)); 
		expectedSortedList.add(new Label<String,Media>("My favorite series", series)); 
		expectedSortedList.add(new Label<String, Media>("My favorite single", single)); 
		expectedSortedList.add(new Label<String, Media>("My favorite album", alb));
		ml.sort();
		sf = new SearchFilter("ISBN"); //isbn is in every toString, should match all
		List<Label<String, Media>> listAfterSorting = ml.filter(sf);
		checkLabeledMediaLists(expectedSortedList, listAfterSorting);
		boolean exceptionThrownOnDuplicateAdd = false;
		Book duplicateISBN = new Book(Format.LASERDISC, "123-4-56-789012-3", "Some genre", "Somebody", "Something", "Some publisher");
		//all fields are different, EXCEPT for ISBN, which matches the album
		//should generate an exception when adding
		try{
			ml.add("A duplicate item", duplicateISBN);
		} catch(MediaAlreadyInLibrary mail){
			exceptionThrownOnDuplicateAdd = true;
		}
		assertTrue("No MediaAlreadyInLibrary exception thrown when adding an item with an existing ISBN",exceptionThrownOnDuplicateAdd);
	}

	//MediaAlreadyInLibrary tests
	@Test(timeout=1000)
	public void test_mediaalreadyinlibrary(){
		Class<?> c = null;
		try{
			c = Class.forName("MediaAlreadyInLibrary");
		} catch(ClassNotFoundException cnfe){ }
		assertTrue("MediaAlreadyInLibrary class not found", c != null);
		Format f = Format.HARDBACK;
		String isbn = "456-7-89-012345-6";
		String genre = "Horror";
		String author = "John Smith";
		String title = "Not titled";
		String publisher = "Penguin Books";
		Book b = new Book(f,isbn,genre,author,title,publisher);
		MediaAlreadyInLibrary mail = new MediaAlreadyInLibrary("My favorite book", b);
		checkField("MediaAlreadyInLibrary", "name", "java.lang.String", "getName");
		checkField("MediaAlreadyInLibrary", "media", "Media", "getMedia");
	}
	

}